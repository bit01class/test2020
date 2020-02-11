package com.bit.framework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class JdbcTemplate<T> {
	DataSource dataSource;
	
	public JdbcTemplate(DataSource dataSource) {
		this.dataSource=dataSource;
	}

	public T executeOne(String sql,RowMapper<T> row,Object ... obj) throws SQLException{
		return execute(sql, row, obj).get(0);
	}
	
	public List<T> execute(String sql,RowMapper<T> row,Object ... obj) throws SQLException{
		List<T> list=new ArrayList<T>();
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
	
	
	
	public int update(String sql,Object ... obj) throws SQLException{
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
