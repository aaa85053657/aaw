package com.aaw.sys.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.molos.listener.SpringInit;

import com.aaw.bean.ProductElement;
import com.aaw.sys.service.IProductElementService;

/**
 * Servlet implementation class InitPicServlet
 */
public class InitPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductElementService service = (IProductElementService) SpringInit
			.getApplicationContext().getBean("productElementService");

	private static String pathRoot = "/usr/local/img/";

	@Override
	public void init() throws ServletException {
		List<ProductElement> list = service.list();
		for (ProductElement p : list) {
			if (p.getSamplepath() == null || p.getSamplepath().equals("")
					|| !new File(p.getSamplepath()).exists()) {
				String path = pathRoot + p.getAttribute().getPath() + "/"
						+ p.getName().trim() + ".png";
				File file = new File(path);
				if (file.exists() && !file.isDirectory()) {
					p.setSamplepath(path);
					service.update(p);
				} else {
					p.setSamplepath(null);
					service.update(p);
				}
			}

		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitPicServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
