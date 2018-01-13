package com.androidServlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Object;

/**
 * Servlet implementation class Server
 */
@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Server() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getRequestURL());

		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String dataJson = buffer.toString();
		ObjectMapper objMap = new ObjectMapper();
		Object obj=objMap.readValue(dataJson, Object.class);
		System.out.println("Request body:"+obj.toString());

		try {
			if (obj.getUserName() == null || obj.getUserName() == "") {
				throw new Error("No user name found");
			} else {
				if (obj.getLocationName() != null && obj.getLocationName()!="") {
					try {
						String filePath = "D:\\Proiect\\History\\" + obj.getUserName() + ".txt";
						File newFile = new File(filePath);
						if (newFile.createNewFile()) {
							System.out.println("Fisier Creat");
							try {
								FileWriter fileWriter = new FileWriter(newFile.getAbsolutePath());
								BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
								bufferWriter.write(obj.getLocationName());
								bufferWriter.close();
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						} else {
							System.out.println("File exists");
							try {
								FileWriter fileWriter = new FileWriter(newFile.getAbsolutePath(), true);
								BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
								bufferWriter.write("\r\n" + obj.getLocationName());
								bufferWriter.close();
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}
						response.setStatus(200);
					} catch (Exception e) {
						throw new Error(e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setStatus(500);
		}
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
