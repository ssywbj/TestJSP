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
	private boolean mIsPrintPath = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDemo() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (mIsPrintPath) {
			System.out
					.println("context path: " + request.getContextPath() + ", servlet path: " + request.getServletPath()
							+ ", request url: " + request.getRequestURL() + "\nproject real path: "
							+ request.getServletContext().getRealPath("") + ", request uri:  " + request.getRequestURI()
							+ "\nserver real path: "
							+ new File(request.getServletContext().getRealPath(request.getRequestURI()))
									.getAbsolutePath()
							+ "\nclass path: " + getClass().getResource("/").getPath() + ", user.dir: "
							+ System.getProperty("user.dir"));
		}

		// 获取请求的数据，并向控制台输出
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("request method: " + request.getMethod() + ", content type: " + request.getContentType()
				+ ", username: " + username + ", password: " + password);

		// request.getServletContext().getRealPath(path)

		try {
			if (request.getContentType() == null) {
				return;
			}

			File dir = new File(request.getServletContext().getRealPath("pictures"));
			if (request.getContentType().contains("text/plain")) {
				byte[] data = FileUtil.readByte(request.getInputStream());
				System.out.println("receive plain text-->" + new String(data));
				FileUtil.saveInfoInFile(new File(dir, "uploadText.txt"), data);
			} else if (request.getContentType().contains("image/png")) {
				System.out.println("------receive image data---------");
				FileUtil.saveInfoInFile(new File(dir, "uploadImage.png"), request.getInputStream());
			} else if (request.getContentType().contains("multipart/form-data")) {
				System.out.println("------receive multipart/form-data---------");
				FileUtil.saveInfoInFile(new File(dir, "multipart.png"), request.getInputStream());
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
		doGet(request, response);
	}

}
