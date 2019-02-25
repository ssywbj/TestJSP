package com.ssywbj.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo
 */
@WebServlet("/ServletDemo")
public class ServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDemo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// 获取请求的数据，并向控制台输出
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(request.getMethod() + "-->" + request.getServletPath() + "-->" + request.getContentType()
				+ "-->username:" + username + ", password:" + password);

		try {
			if (request.getContentType() == null) {
				return;
			}
			if (request.getContentType().contains("text/plain")) {
				byte[] data = FileUtil.readByte(request.getInputStream());
				System.out.println("receive plain text-->" + new String(data));
				FileUtil.saveInfoInFile(new File("D:" + File.separator + "uploadText.txt"), data);
			} else if (request.getContentType().contains("image/png")) {
				System.out.println("------receive image data---------");
				FileUtil.saveInfoInFile(new File("D:" + File.separator + "uploadImage.png"), request.getInputStream());
			} else if (request.getContentType().contains("multipart/form-data")) {
				System.out.println("------receive multipart/form-data---------");
				FileUtil.saveInfoInFile(new File("D:" + File.separator + "multipart.png"), request.getInputStream());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		PrintWriter out = response.getWriter();
		out.write("receive data, thinks");
		// out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
