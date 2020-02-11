package com.bit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.bit.framework.BitController;
import com.bit.model.DeptDao;

public class MainController implements BitController {

	@Override
	public String execute(HttpServletRequest req) throws ServletException,
			IOException {
		
		try {
			DeptDao dao=new DeptDao();
			req.setAttribute("list", dao.selectAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "ex01";
	}

}








