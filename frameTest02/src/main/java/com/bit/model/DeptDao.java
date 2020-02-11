package com.bit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

import com.bit.framework.JdbcTemplate;
import com.bit.framework.RowMapper;
import com.bit.model.entity.DeptVo;

public class DeptDao {
	DataSource dataSource;

	public DeptDao() throws SQLException {
		OracleDataSource dataSource=new OracleDataSource();
		dataSource.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUser("scott");
		dataSource.setPassword("tiger");
		this.dataSource=dataSource;
	}
	
	public List<DeptVo> selectAll() throws SQLException{
		String sql="select * from dept";
		JdbcTemplate<DeptVo> template=null;
		template=new JdbcTemplate<DeptVo>(dataSource);
		return template.execute(sql, new RowMapper<DeptVo>() {
			
			@Override
			public DeptVo mapper(ResultSet rs) throws SQLException {
				DeptVo bean=new DeptVo();
				bean.setDeptno(rs.getInt("deptno"));
				bean.setDname(rs.getString("dname"));
				bean.setLoc(rs.getString("loc"));
				return bean;
			}
		});
	}
}









