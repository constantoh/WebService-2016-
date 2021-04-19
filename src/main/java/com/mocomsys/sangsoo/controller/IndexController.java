package com.mocomsys.sangsoo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping(value = "/Index.do")
	public String Index(HttpServletRequest request, HttpServletResponse response, Model model){
		
		System.out.println("IndexController");
		
		String token = request.getParameter("token");
		
		
		String result = callApi(request, response, token);
		
		return result;
		}
	
	public String callApi(HttpServletRequest request, HttpServletResponse response, String token){
		
		try {
			URL url = new URL("https://openapi.naver.com/calendar/createSchedule.json");
			System.out.println("token : " + token);
			//response.setHeader("Authorization", );
		//	response.setHeader("X-Naver-Client-Id", "COKNVHCQPO0R3Dba6p6D");
		//	response.setHeader("X-Naver-Client-Secret", "p62JZ_ItBB");

			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			
			conn.setDoInput(true);
			conn.setDoOutput(true);

			//conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//conn.setRequestProperty(", value);
			conn.setRequestProperty("Authorization", "Bearer " + token);
			conn.setRequestProperty("X-Naver-Client-Id", "COKNVHCQPO0R3Dba6p6D");
			conn.setRequestProperty("X-Naver-Client-Secret", "p62JZ_ItBB2");

			
			StringBuffer parameter = new StringBuffer();
			parameter.append("calendarId=64510514");
			String ical = "BEGIN:VCALENDAR\r\nVERSION:2.0\r\nPRODID:-//moco//sangsoo19/KO\r\nCALSCALE:GREGORIAN\r\n"
					+ "BEGIN:VTIMEZONE\r\nTZID:Asia/Seoul\r\nBEGIN:STANDARD\r\nDTSTART:19700101T000000\r\nTZOFFSETFROM:+0900\r\n"
					+ "TZOFFSETTO:+0900\r\nTZNAME:KST\r\nEND:STANDARD\r\nEND:VTIMEZONE\r\nBEGIN:VEVENT\r\nSEQUENCE:0\r\nCLASS:PUBLIC\r\nTRANSP:OPAQUE\r\n"
					+ "ORGANIZER;CN=SANGSOO:MAILTO:a@example.com\r\nATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;CN=SS:MAILTO:b@example.com\r\nUID:20160819T112901@sangsoo.com\r\nDTSTART;TZID=Asia/Seoul:20160807T120000\r\nDTEND;TZID=Asia/Seoul:20160807T140000\r\n"
					+ "SUMMARY:SSSSSSSSS\r\nDESCRIPTION:DDDDDDDDDDDDd\r\nLOCATION:HHHHHHHH\r\nRRULE:FREQ=WEEKLY;BYDAY=FR;INTERVAL=1;UNTIL=20161030\r\nCREATED:20160802T131750Z\r\n"
					+ "LASTMODIFIED:20160802T131750Z\r\nDTSTAMP:20160802T131750Z\r\nEND:VEVENT\r\nEND:VCALENDAR";
			//ical = ical.replace(' ', '+');
			ical="&scheduleIcalString=".concat(URLEncoder.encode(ical));
			parameter.append(ical);
			
			
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(parameter.toString());
			wr.flush();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

			String line;
			while((line = rd.readLine())!=null){
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println("Index controller ERROR " + e);
			e.printStackTrace();
		}
		return "ex";
	}
}
