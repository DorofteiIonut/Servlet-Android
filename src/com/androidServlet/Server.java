package com.androidServlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Server
 */
@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Server() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getRequestURL());
		response.getWriter().append("<p>Hello in SerlvetAndroid App</p>");
		String userName=request.getParameter("userName");
		String infoRequest=request.getParameter("info");
		System.out.println(userName + "   "+infoRequest);
		if(userName==null) {
			response.getWriter().append("<p>No user name received</p>");
		}else {
			response.getWriter().append("Welcome:"+userName);	
			if(infoRequest!=null) {
				response.getWriter().append("<p/>");
				response.getWriter().append("Informatoion are: "+infoRequest);
				try {
					String filePath="D:\\Proiect\\History\\"+userName+".txt";
					File newFile= new File(filePath);
					if(newFile.createNewFile()) {
						System.out.println("Fisier Creat");
						try {
						FileWriter fileWriter=new FileWriter(newFile.getAbsolutePath());
						BufferedWriter bufferWriter=new BufferedWriter(fileWriter);
						bufferWriter.write(infoRequest);
						bufferWriter.close();
						}catch (Exception e) {
						 System.out.println(e.getMessage());
						}
					}
					else {
						System.out.println("File exists");
						try {
							FileWriter fileWriter=new FileWriter(newFile.getAbsolutePath(),true);
							BufferedWriter bufferWriter=new BufferedWriter(fileWriter);
							bufferWriter.write("\r\n"+infoRequest);
							bufferWriter.close();
							}catch (Exception e) {
							 System.out.println(e.getMessage());
							}
					}
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
