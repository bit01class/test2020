package com.bit.framework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class JdbcTemplate {
	DataSource dataSource;
	
	public JdbcTemplate(DataSource dataSource) {
		this.dataSource=dataSource;
	}

	public List execute(String sql,RowMapper row) throws SQLException{
		return execute(sql,row,new Object[]{});
	}
	public List execute(String sql,RowMapper row,Object[] obj) throws SQLException{
		List list=new ArrayList ();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			conn=dataSource.getConnection();
			pstmt=conn.prepareStatement(sql);
			for(int i=0; i<obj.length; i++){
				pstmt.setObject(i+1, obj[i]);
			}
			rs=pstmt.executeQuery();
			while(rs.next()){
				
				list.add(row.mapper(rs));
			}
		}finally{
			if(conn!=null)conn.close();
		}
		return list;
	}
	
	
	
	public int update(String sql,Object[] obj) throws SQLException{
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=dataSource.getConnection();
			pstmt=conn.prepareStatement(sql);
			for(int i=0; i<obj.length; i++){
				pstmt.setObject(i+1, obj[i]);
			}
			return pstmt.executeUpdate();
		}finally{
			if(conn!=null)conn.close();
		}
	}
}
