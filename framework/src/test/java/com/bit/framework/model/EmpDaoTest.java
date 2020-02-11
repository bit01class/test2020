package com.bit.framework.model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import com.bit.framework.model.entity.EmpVo;


public class EmpDaoTest {

	@Test
	public void test1() throws SQLException {
		EmpDao dao=new EmpDao();
		List<EmpVo> list = dao.selectAll();
		assertNotNull(list);
		assertTrue(list.size()>0);
		assertSame(14, list.size());
	}
	
	@Test
	public void test2() throws SQLException {
		
		EmpDao dao=new EmpDao();
		EmpVo bean=new EmpVo();
		bean.setEmpno(1111);
		bean.setEname("test");
		bean.setJob("tester");
		bean.setSal(1000);
		assertTrue(dao.insertOne(bean)>0);
	}

}











