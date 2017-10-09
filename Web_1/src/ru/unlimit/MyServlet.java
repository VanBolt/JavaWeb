package ru.unlimit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/MyServlet", description = "Мое описание",displayName = "displayName")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("firstName");
		String secondName = request.getParameter("secondName");
		//String job = request.getParameter("job");
		String[] jobs = request.getParameterValues("job");
		String gender = request.getParameter("gender");
		String age18 = request.getParameter("age18");
		
		if(gender == null){
			gender = "-";
		}
		if(age18 == null){
			age18 = "-";	
		}
		
		System.out.println(name);
		System.out.println(secondName);
		System.out.println(jobs.length);
		//System.out.println(job);
		System.out.println(gender);
		System.out.println(age18);

		//response.setContentType("text/html");
		//response.setCharacterEncoding("UTF-8");
	//or	
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<h3>Worker's profile<h3>");
		out.println(name+"<br>");
		out.println(secondName+"<br>");
		//out.println(job+"<br>");
		out.println(jobs.length+"<br>");
		out.println(Arrays.deepToString(jobs)+"<br>");
		out.println(gender+"<br>");
		out.println(age18+"<br>");
		out.close();
	}
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String Name = request.getParameter("firstName");
		String SecondName = request.getParameter("secondName");

		//response.setContentType("text/html");
		//response.setCharacterEncoding("UTF-8");
	//or	
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<h3>Hello from MyServlet.POST</h3>"+" "+Name+" "+SecondName);
		out.close();
	}

}
