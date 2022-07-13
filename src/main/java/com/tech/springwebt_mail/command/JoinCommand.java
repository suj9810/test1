package com.tech.springwebt_mail.command;

import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.springwebt_mail.dao.MemberDAO;
import com.tech.springwebt_mail.dto.MemberDTO;
import com.tech.springwebt_mail.util.CryptoUtil;
import com.tech.springwebt_mail.util.EmailSHA;
import com.tech.springwebt_mail.util.Gmail;



public class JoinCommand implements SCommand {
	MemberDAO dao;
	
	@Override
	public String execute(Model model) {
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String nickname=request.getParameter("nickname");
		String email=request.getParameter("em");

		
		String shpwd="";
		String bcpwd="";
		try {
//			SHA256 sha=SHA256.getInsatnce();
//			shpwd = sha.getSha256(pw.getBytes());
			shpwd = CryptoUtil.sha512(pw);
			
//			bcpwd = BCrypt.hashpw(shpwd, BCrypt.gensalt());
			bcpwd = CryptoUtil.encryptAES256(pw, shpwd);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String shpwd="shskfjskjfksf";
		//String bcpwd="bcskfjskjfskj";
			
		dao=new MemberDAO();
		int result=dao.join(new MemberDTO(id, pw, nickname, email,shpwd,bcpwd));
		if(result==1) {//성공
			/*request.getSession().setAttribute("memberNickname", nickname);*/
			emailSendAction(id);
//			return "member/sendMail";
			return "redirect:/";
		}else if(result==-1){//ID 중복
			return "iddupl";
		}else if(result==-2){//닉네임 중복
			return "nndupl";
		}else {
			return "error";
		}
	}
	
	private void emailSendAction(String id) {
		String host="http://localhost:9500/springwebt_mail/";
		
		String from="she9810@gmail.com";
		
		String to=dao.getMemberEmail(id);
		String code=new EmailSHA().getSHA256(to);
		String subject="회원가입 인증을 위한 메일입니다.";
		String content="다음 링크 클릭 이메일 인증을 진행하세요."+"<a href='"+host+"emailCheck?code="+code+"&id="+id+"'><b>이메일 인증하기</b></a>";
		
		
		Properties p=new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "465");
//		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		try{
			Authenticator auth=new Gmail();
			Session ses=Session.getInstance(p,auth);
			ses.setDebug(true);
			MimeMessage msg=new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr=new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr=new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html;charset=UTF-8");
			Transport.send(msg);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
