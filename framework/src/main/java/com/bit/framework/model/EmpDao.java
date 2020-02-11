package com.bit.framework.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

import com.bit.framework.JdbcTemplate;
import com.bit.framework.RowMapper;
import com.bit.framework.model.entity.EmpVo;

public class EmpDao {
	DataSource dataSource;
	
	RowMapper<EmpVo> row=new RowMapper<EmpVo>() {

		@Override
		public EmpVo mapper(ResultSet rs) throws SQLException {
			EmpVo bean=new EmpVo();
			bean.setEmpno(rs.getInt("empno"));
			bean.setEname(rs.getString("ename"));
			bean.setJob(rs.getString("job"));
			bean.setSal(rs.getInt("sal"));
			bean.setHiredate(rs.getDate("hiredate"));
			return bean;
		}
	};

	public EmpDao() throws SQLException{
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="scott";
		String password="tiger";
		OracleDataSource dataSource=new OracleDataSource();
		dataSource.setURL(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		this.dataSource=dataSource;
	}
	
	public List selectAll() throws SQLException{
		String sql="select * from emp";
		JdbcTemplate<EmpVo> template =new JdbcTemplate<EmpVo>(dataSource);
		
		return template.execute(sql, row);
	}
	
	public EmpVo selectOne(int empno) throws SQLException{
		String sql="select * from emp where empno=?";
		JdbcTemplate<EmpVo> template=new JdbcTemplate<EmpVo>(dataSource);
		return template.executeOne(sql, row, empno);
	}
	
	public int insertOne(EmpVo bean) throws SQLException{
		String sql="insert into emp (empno,ename,job,hiredate,sal) ";
		sql+=" values (?,?,?,sysdate,?)";
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		return template.update(sql, bean.getEmpno(),bean.getEname()
				,bean.getJob(),bean.getSal());
	}
}












