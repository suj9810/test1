package com.tech.springwebt_mail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tech.springwebt_mail.dto.MemberDTO;



public class MemberDAO {
DataSource dataSource;
	
	public MemberDAO() {
		try {
			Context context=new InitialContext();
			dataSource=(DataSource) context.lookup("java:comp/env/jdbc/springxe");	
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public String getMemberEmail(String id) {
		String sql="select email from jmember where id = ?";
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con = dataSource.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {	if(con!=null) con.close();	} catch (Exception e) {e.printStackTrace();}
			try {	if(ps!=null) ps.close();	} catch (Exception e) {e.printStackTrace();}
			try {	if(rs!=null) rs.close();	} catch (Exception e) {e.printStackTrace();}
		}
		return null;//데이터베이스 오류
	}
	public int join(MemberDTO memberDTO) {
		int idCheckResult=idCheck(memberDTO.getId());
		int nnCheckResult=nicknameCheck(memberDTO.getNickname());
		if(idCheckResult==0 && nnCheckResult==0) {//가입 가능할 때
			String sql="insert into jmember values(?,?,?,?,?,?,0)";
			Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				con = dataSource.getConnection();
				ps=con.prepareStatement(sql);
				ps.setString(1, memberDTO.getId());
				ps.setString(2, memberDTO.getPw());
				ps.setString(3, memberDTO.getNickname());
				ps.setString(4, memberDTO.getEmail());
				ps.setString(5, memberDTO.getShpwd());
				ps.setString(6, memberDTO.getBcpwd());
				System.out.println("insert done-----------");
				return ps.executeUpdate();//가입 성공, 1 반환
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {	if(con!=null) con.close();	} catch (Exception e) {e.printStackTrace();}
				try {	if(ps!=null) ps.close();	} catch (Exception e) {e.printStackTrace();}
				try {	if(rs!=null) rs.close();	} catch (Exception e) {e.printStackTrace();}
			}
			return 0;//데이터베이스 오류, 회원가입 실패
		}else if(idCheckResult==1) {//이미 가입한 ID, 가입 불가
			return -1;
		}else if(nnCheckResult==1) {//이미 존재하는 닉네임, 가입 불가
			return -2;
		}
		return -3;
	}
	public int nicknameCheck(String changeName) {
		String sql="select * from jmember where nickname = ?";
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String str=null;
		try {
			con = dataSource.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, changeName);
			rs=ps.executeQuery();
			if(rs.next()){
				return 1;//이미 존재하는 닉네임
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {	if(con!=null) con.close();	} catch (Exception e) {e.printStackTrace();}
			try {	if(ps!=null) ps.close();	} catch (Exception e) {e.printStackTrace();}
			try {	if(rs!=null) rs.close();	} catch (Exception e) {e.printStackTrace();}
		}
		return 0;//가입 가능
	}

	private int idCheck(String id) {
		String sql="select * from jmember where id = ?";
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String str=null;
		try {
			con = dataSource.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				return 1;//이미 가입한 아이디
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {	if(con!=null) con.close();	} catch (Exception e) {e.printStackTrace();}
			try {	if(ps!=null) ps.close();	} catch (Exception e) {e.printStackTrace();}
			try {	if(rs!=null) rs.close();	} catch (Exception e) {e.printStackTrace();}
		}
		return 0;//가입 가능
	}
	public void setMemberEmailChecked(String id) {
		String sql="update jmember set  mailcheck = 1 where id = ?";
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con = dataSource.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {	if(con!=null) con.close();	} catch (Exception e) {e.printStackTrace();}
			try {	if(ps!=null) ps.close();	} catch (Exception e) {e.printStackTrace();}
			try {	if(rs!=null) rs.close();	} catch (Exception e) {e.printStackTrace();}
		}
	}

}
