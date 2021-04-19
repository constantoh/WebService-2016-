package com.mocomsys.sangsoo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.cfg.beanvalidation.GroupsPerOperation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CreateScheduleController2 {
	
	String token;
	
	String title;
	String location;
	String description;
	
	String year1;
	String month1;
	String day1;
	String hour1;
	String minute1;
	String date1;
	
	String year2;
	String month2;
	String day2;
	String hour2;
	String minute2;
	String date2;
	
	String repeat_info;
	String frequency_info;
	String[] day_info;
	String untilYear;
	String untilMonth;
	String untilDay;
	String untilDate;
	
	String currentDate;
	
	@RequestMapping(value = "/CreateSchedule2.do")
	public String CreateSchedule(HttpServletRequest request, HttpServletResponse response, Model model){
		String result=null;
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("**************** createScheduleController2 ************************");
		
		HttpSession session = request.getSession(true);
		token = (String) session.getAttribute("token");
		token = token.replace(" ", "+");
		title = (String) request.getParameter("title");
		location = (String) request.getParameter("location");
		description = (String) request.getParameter("description");
		
		year1 = (String) request.getParameter("year1");
		month1 = (String) request.getParameter("month1");
		if(month1.equals("1") || month1.equals("2") || month1.equals("3") || month1.equals("4") || month1.equals("5")
				|| month1.equals("6") || month1.equals("7") || month1.equals("8") || month1.equals("9")){
			month1= "0".concat(month1);
		}
		day1 = (String) request.getParameter("day1");
		if(day1.equals("1") || day1.equals("2") || day1.equals("3") || day1.equals("4") || day1.equals("5")
				|| day1.equals("6") || day1.equals("7") || day1.equals("8") || day1.equals("9")){
			day1= "0".concat(day1);
		}
		hour1 = (String) request.getParameter("hour1");
		if(hour1.equals("1") || hour1.equals("2") || hour1.equals("3") || hour1.equals("4") || hour1.equals("5")
				|| hour1.equals("6") || hour1.equals("7") || hour1.equals("8") || hour1.equals("9")){
			hour1= "0".concat(hour1);
		}
		minute1 = (String) request.getParameter("minute1");
		if(minute1.equals("1") || minute1.equals("2") || minute1.equals("3") || minute1.equals("4") || minute1.equals("5")
				|| minute1.equals("6") || minute1.equals("7") || minute1.equals("8") || minute1.equals("9")){
			minute1= "0".concat(minute1);
		}
		date1 = year1.concat(month1).concat(day1).concat("T").concat(hour1).concat(minute1).concat("00");
		year2 = (String) request.getParameter("year2");
		
		month2 = (String) request.getParameter("month2");
		if(month2.equals("1") || month2.equals("2") || month2.equals("3") || month2.equals("4") || month2.equals("5")
				|| month2.equals("6") || month2.equals("7") || month2.equals("8") || month2.equals("9")){
			month2= "0".concat(month2);
		}
		day2 = (String) request.getParameter("day2");
		if(day2.equals("1") || day2.equals("2") || day2.equals("3") || day2.equals("4") || day2.equals("5")
				|| day2.equals("6") || day2.equals("7") || day2.equals("8") || day2.equals("9")){
			day2= "0".concat(day2);
		}
		hour2 = (String) request.getParameter("hour2");
		if(hour2.equals("1") || hour2.equals("2") || hour2.equals("3") || hour2.equals("4") || hour2.equals("5")
				|| hour2.equals("6") || hour2.equals("7") || hour2.equals("8") || hour2.equals("9")){
			hour2= "0".concat(hour2);
		}
		minute2 = (String) request.getParameter("minute2");
		if(minute2.equals("1") || minute2.equals("2") || minute2.equals("3") || minute2.equals("4") || minute2.equals("5")
				|| minute2.equals("6") || minute2.equals("7") || minute2.equals("8") || minute2.equals("9")){
			minute2= "0".concat(minute2);
		}

		date2 = year2.concat(month2).concat(day2).concat("T").concat(hour2).concat(minute2).concat("00");

		repeat_info = (String) request.getParameter("repeat_info");
		if(repeat_info.equals("YesRepeat")){
			frequency_info = (String) request.getParameter("frequency_info");
			day_info = request.getParameterValues("day_info");
			
			untilYear = (String) request.getParameter("untilYear");
			untilMonth = (String) request.getParameter("untilMonth");
			untilDay = (String) request.getParameter("untilDay");
			untilDate = untilYear.concat(untilMonth).concat(untilDay);
			
			System.out.println("repeat info : " + repeat_info);
			System.out.println("freqency info : " + frequency_info);
			for(int i=0; i<day_info.length; i++){
				System.out.println("day_info{ " + i + "): " + day_info[i]);
			}
			System.out.println("untildate : " + untilDate);
		}
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyyMMddHHmmss", Locale.KOREA );
		Date currentTime = new Date ( );
		String dTime = formatter.format ( currentTime );
		String tempString = dTime.substring(0, 7);
		String temp2String = dTime.substring(8);
		System.out.println("1 : " + tempString + ", 2 : " + temp2String);
		currentDate = tempString.concat("T").concat(temp2String).concat("Z");
	
		System.out.println ("current Time : " + currentDate); 
		System.out.println("title : " + title);
		System.out.println("location : " + location);
		System.out.println("description : " + description);
		System.out.println("date1 : " + date1);
		System.out.println("date2 : "+ date2);	
		
		
		
		System.out.println("token : " + token);
		
		result = callApi(request, response, token);

		JSONObject root;
		try {
			root = new JSONObject(result);
			result = root.getString("result");
		} catch (JSONException e) {
			System.out.println("JSON ERROR : " + e);
			e.printStackTrace();
		}
		System.out.println("result : " + result);
		
		if(result.equals("success")){
			return "insertScheduleSuccess";
		}else{
			return "errorCreateSchedule";
		}
	}
	public String callApi(HttpServletRequest request, HttpServletResponse response, String token){
		//System.out.println("***************** callApi *****************************");
		String result = "";
		String id = "COKNVHCQPO0R3Dba6p6D";
		String secret = "p62JZ_ItBB2";

		try {
			URL url = new URL("https://openapi.naver.com/calendar/createSchedule.json");

			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			conn.setRequestProperty("Authorization", "Bearer " + token);
			conn.setRequestProperty("X-Naver-Client-Id", id);
			conn.setRequestProperty("X-Naver-Client-Secret", secret);

			StringBuffer parameter = new StringBuffer();
			parameter.append("calendarId=64510514");
			String ical = "BEGIN:VCALENDAR\r\nVERSION:2.0\r\nPRODID:-//moco//sangsoo19/KO\r\nCALSCALE:GREGORIAN\r\n"
					+ "BEGIN:VTIMEZONE\r\nTZID:Asia/Seoul\r\nBEGIN:STANDARD\r\nDTSTART:19700101T000000\r\nTZOFFSETFROM:+0900\r\n"
					+ "TZOFFSETTO:+0900\r\nTZNAME:KST\r\nEND:STANDARD\r\nEND:VTIMEZONE\r\nBEGIN:VEVENT\r\nSEQUENCE:0\r\nCLASS:PUBLIC\r\nTRANSP:OPAQUE\r\n"
					+ "ORGANIZER;CN=SANGSOO:MAILTO:a@example.com\r\nATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;CN=SS:MAILTO:b@example.com\r\nUID:"+ currentDate +"@sangsoo.com\r\nDTSTART;TZID=Asia/Seoul:"+ date1 +"\r\nDTEND;TZID=Asia/Seoul:" + date2 + "\r\n"
					+ "SUMMARY:" + title + "\r\nDESCRIPTION:"+description+"\r\nLOCATION:"+location+"\r\n";
			if(repeat_info.equals("YesRepeat")){
				if(frequency_info.equals("1")){
					ical+= "RRULE:FREQ=WEEKLY;BYDAY=";
					for(int i=0; i<day_info.length; i++){
						ical+=day_info[i];
						if(i!=day_info.length-1){
							ical+=",";
						}
					}
					ical+=";INTERVAL=1;UNTIL="+ untilDate +"\r\nCREATED:"+ currentDate +"\r\n";
				}else if(frequency_info.equals("2")){
					ical+= "RRULE:FREQ=WEEKLY;BYDAY=";
					for(int i=0; i<day_info.length; i++){
						ical+=day_info[i];
						if(i!=day_info.length-1){
							ical+=",";
						}
					}
					ical+=";INTERVAL=2;UNTIL="+ untilDate +"\r\nCREATED:"+ currentDate +"\r\n";
				}else if(frequency_info.equals("MONTH")){
					ical+= "RRULE:FREQ=MONTHLY;BYDAY=";
					for(int i=0; i<day_info.length; i++){
						ical+=day_info[i];
						if(i!=day_info.length-1){
							ical+=",";
						}
					}
					ical+=";UNTIL="+ untilDate +"\r\nCREATED:"+ currentDate +"\r\n";
				}else if(frequency_info.equals("YEAR")){
					ical+= "RRULE:FREQ=YEARLY;BYDAY=";
					for(int i=0; i<day_info.length; i++){
						ical+=day_info[i];
						if(i!=day_info.length-1){
							ical+=",";
						}
					}
					ical+=";INTERVAL=1;UNTIL="+ untilDate +"\r\nCREATED:"+ currentDate +"\r\n";
				}
			}
			ical +="LASTMODIFIED:" + currentDate + "\r\nDTSTAMP:" + currentDate + "\r\nEND:VEVENT\r\nEND:VCALENDAR";
			//ical = ical.replace(' ', '+');
			ical="&scheduleIcalString=".concat(URLEncoder.encode(ical));
			parameter.append(ical);
			
			
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(parameter.toString());
			wr.flush();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while((line = rd.readLine())!=null){
				result+=line;
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println("Index controller ERROR " + e);
			e.printStackTrace();
		}
		return result;
	}

}