package com.bit.framework;

import java.sql.SQLException;

import com.bit.framework.model.EmpDao;

public class Test {

	public static void main(String[] args) throws SQLException {
		EmpDao dao=new EmpDao();
		System.out.println(dao.getConnection()!=null);
	}

}
