package com.bit.framework.model;

import static org.junit.Assert.*;

import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Test;

public class EmpDaoTest {

	@Test
	public void test() throws SQLException {
		EmpDao dao=new EmpDao();
		Assert.assertNotNull(dao.getConnection());
	}

}
