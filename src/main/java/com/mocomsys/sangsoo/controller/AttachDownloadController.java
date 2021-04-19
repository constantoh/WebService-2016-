package com.mocomsys.sangsoo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AttachDownloadController {

	@RequestMapping(value = "/AttachDownload.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String attachDownload(HttpServletRequest request, HttpServletResponse response, Model model){
		response.setContentType("text/html;charset=utf-8");
		String fileName=null;
		try {
			 fileName = (String) request.getParameter("fileName");
			byte[] b = fileName.getBytes("ISO-8859-1");
			fileName = new String(b, "utf-8");
			request.setCharacterEncoding("UTF-8");
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try{
			String title = (String)request.getParameter("title");
			System.out.println("title : " + title);
			
			
			System.out.println("attachDonwoloadController@@@, fileName : " + fileName);
			
			response.setHeader("Content-Disposition", "attachment; filename="
			          + java.net.URLEncoder.encode(fileName, "UTF-8") + ";");
			
			File file = new File("C:/Users/Administrator/Downloads/"+fileName);
			FileInputStream fileIn = new FileInputStream(file);
			ServletOutputStream out = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			while(fileIn.read(buffer, 0, 4096)!=-1){
				out.write(buffer, 0, 4096);
			}
			fileIn.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("Download ERROR : " + e);
			e.printStackTrace();
		}
		
		
		
		return "";
	}
}
