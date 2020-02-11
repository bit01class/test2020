package com.bit.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchServlet extends HttpServlet{
	Logger logger=Logger.getLogger("com.bit.core.DispatchServlet");
	BitHandleMapping mapping;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		mapping=new BitHandleMapping();
		String file=config.getInitParameter("properties");
		java.util.Properties prop=new Properties();
		
		Class<? extends DispatchServlet> clzz = getClass();
		ClassLoader loader = clzz.getClassLoader();
		InputStream is=loader.getResourceAsStream(file);
		
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Set keys = prop.keySet();
		Iterator<String> ite = keys.iterator();
		while(ite.hasNext()){
			String key=ite.next();
			String value=prop.getProperty(key);
			mapping.addUrl(key, value);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doBit(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doBit(req,resp);
	}
	

	
	private void doBit(HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException, IOException {
		logger.info("dispatch run...");
		
		String url=req.getRequestURI();
		String contextPath=req.getContextPath();
		url=url.substring(contextPath.length());
		

		BitController controller=mapping.getController(url);
		if(controller==null){
			resp.sendError(resp.SC_NOT_FOUND);
			return;
		}
		
		
		String viewName=controller.execute(req);
		String prefix="/WEB-INF/view/";
		String suffix=".jsp";
		
		
		req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
	}
}





























