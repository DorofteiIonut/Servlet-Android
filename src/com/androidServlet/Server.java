package com.androidServlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.models.JSONConvertor;
import com.models.Object;

import jdk.nashorn.internal.parser.JSONParser;

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
		Object jsonResp=new Object("Ionut","Sugeo");
		System.out.println(request.getRequestURL());
		String userName=request.getParameter("userName");
		String infoRequest=request.getParameter("info");
		System.out.println(userName + "   "+infoRequest);
		if(userName==null) {
			System.out.println("Null");
		}else {
			if(infoRequest!=null) {
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
					
				    response.getWriter().write(jsonResp.JsonConvert(jsonResp));
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
