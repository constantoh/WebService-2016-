package com.mocomsys.sangsoo.dao;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.geronimo.mail.util.Base64DecoderStream;

import com.mocomsys.sangsoo.vo.MyMailFlag;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class GetMail {
	
	public MyMailFlag[] receiveMailBox(String id, String pwd){
		
		
		Properties properties = System.getProperties();
		Session session = Session.getDefaultInstance(properties, null);
		Store store = null;
		Folder folder = null;
		MyMailFlag[] mmf = null;
		try {
			store = session.getStore("imap");
			store.connect("mail.mocomsys.com", id, pwd);
			folder = store.getDefaultFolder();
			
			folder = folder.getFolder("INBOX");
			
			folder.open(Folder.READ_WRITE);
			
			Message[] messages = folder.getMessages();
			int messagesLength = messages.length;
			mmf = new MyMailFlag[messagesLength];
			System.out.println("total length : " + messagesLength);
			for(int i=0; i<messagesLength; i++)
			{
				mmf[i] = new MyMailFlag();
	    		mmf[i].setIsAttach("");
	    		mmf[i].setAttachName("");
	    		mmf[i].setContent("");

				System.out.println("#"+(i+1));

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = dateFormat.format(messages[i].getReceivedDate());
				System.out.println("received date : " + messages[i].getReceivedDate());
				String title = FilenameBase64Decode(messages[i].getSubject());
				String name = FilenameBase64Decode(InternetAddress.toString(messages[i].getFrom()));
				String content = "";
				
				mmf[i].setName(name);
				mmf[i].setTitle(title);
				mmf[i].setReceivedDate(date);
				System.out.println("from : " + name);
				System.out.println("subject : " + title);
				System.out.println("receivedData : " + date);
				
				if(messages[i].isSet(Flag.SEEN)){
					//System.out.println("이미 읽음");
					mmf[i].setNewMail("");
				}else{
					//System.out.println("노 읽음");
					mmf[i].setNewMail("NEW");
				}
				if(messages[i].isSet(Flag.DELETED)){
					//System.out.println("삭제 됨");
				}else{
					//System.out.println("노 삭제");
					if(messages[i].getContent() instanceof MimeMultipart){
						System.out.println("(mime)");
					    MimeMultipart mimeMultipart = (MimeMultipart) messages[i].getContent();
					     
					    /////////////////////////////////////////////////////////////////////////////////////////////////////
					    
					    ////////////////////////////////////////////////////////////////////////////////////////////////////
					    
						//mmf[i].setType(mimeMultipart.getBodyPart(i).getContentType());

					    System.out.println("count " + mimeMultipart.getCount()); //메일 본문에 나눠진 구역 개수
					     
					    for(int j = 0; j < mimeMultipart.getCount(); j++){
					    	System.out.println("#count" + (j+1));
					    	
					    	System.out.println("(type) : " + mimeMultipart.getBodyPart(j).getContentType());
					    	if(mimeMultipart.getBodyPart(j).getContentType().equals("text/plain; charset=utf-8")
					    			|| mimeMultipart.getBodyPart(j).getContentType().equals("text/html; charset=utf-8")){
						    	 System.out.println("type : " + mimeMultipart.getBodyPart(j).getContentType());//나눠진 구역의 타입
						    	 content = mimeMultipart.getBodyPart(j).getContent().toString();
						    	 System.out.println("text : " + content);
						    	 mmf[i].setContent(content);
					    	 }else if(mimeMultipart.getBodyPart(j).getContentType().contains("multipart/alternative;")){
					    		 
					    	 }
					    	 if(mimeMultipart.getBodyPart(j).getDisposition() != null
						               && mimeMultipart.getBodyPart(j).getDisposition().equals("attachment") == true){
					    		int fileNameIndex = mimeMultipart.getBodyPart(j).getContentType().indexOf("name");
					    		String fileName = mimeMultipart.getBodyPart(j).getContentType().substring(fileNameIndex+5);
					    		fileName = FilenameBase64Decode(fileName);
					    		{
					    			/*
					    			System.out.println("hello");
					    			result = result + getTextFromMimeMultipart((MimeMultipart)mimeMultipart.getContent());
					    			*/
					    		}
							    System.out.println("fileName : " + fileName);
							    mmf[i].setAttachName(fileName);
							    	
					    		mmf[i].setIsAttach("첨부");
						        // 첨부 파일인 경우 아래와 같이 InputStream 으로 받아서 저장이 가능하다.
						        // base64 로 인코딩 되었다고 해도 InputStream 으로 받을때 내부에서 디코딩 되서 나온다.
					    		
					    		String destFilePath = "C:/Users/Administrator/Downloads/" + fileName;
					    		FileOutputStream output = new FileOutputStream(destFilePath);
					    		
					    		InputStream input = mimeMultipart.getBodyPart(j).getInputStream();
					    		
					    		byte[] buffer = new byte[4096];
					    		
					    		int byteRead;
					    		
					    		while((byteRead = input.read(buffer))!=-1){
					    			output.write(buffer, 0, byteRead);
					    		}
					    		output.close();
					    		}
					     }
					}else{
						content+= messages[i].getContent();//.toString();
						System.out.println("(text)\n" + content);
				    	mmf[i].setContent(content);
					}
				}
			}
//			System.out.println("#######################################");
//			for(int i=0; i<messagesLength; i++){
//				System.out.println("#" + (i+1) + "  new : " + mmf[i].getNewMail() +  ", name : " + mmf[i].getName() + ", title : " + mmf[i].getTitle()
//						+ ", date : " + mmf[i].getReceivedDate() + ", attach : " + mmf[i].getIsAttach());
//			}
//			System.out.println("#######################################");
		} catch (Exception e) {
			System.out.println("mail ERROR : " + e);
			e.printStackTrace();
		} finally {
			try {
				folder.close(true);
				store.close();
				System.out.println("end");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mmf;
	}
	public MyMailFlag[] sendMailBox(){
		MyMailFlag[] mail = null;
		
		return mail;
	}
	public String transferMail(String from, String to, String cc, String subject, String content){
		String result = "false";
		
		Properties properties = System.getProperties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.host", "mail.mocomsys.com");
		properties.put("mail.smtp.auth", "true");

		Authenticator authenticator = null;
		properties.put("mail.smtp.auth", "true");
		authenticator = new SMTPAuthenticator("intern2@mocomsys.com", "mocomsys");
		
		
		Session session = Session.getInstance(properties, authenticator);
		
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			if(!cc.trim().equals("")){
				msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));
			}
			msg.setSubject(subject);
			msg.setText(content);
			msg.setHeader("X-Mailer", "PSERANG");
			msg.setHeader("Content-type", "text/plain); charset=utf-8");
			msg.setSentDate(new Date());
			
			Transport.send(msg);
			result = "success";
		} catch (Exception e) {
			System.out.println("TransferMail ERROR : " + e);
			e.printStackTrace();
		}
		
		return result;
	}
	class SMTPAuthenticator extends Authenticator {

		PasswordAuthentication passwordAuthentication;

		SMTPAuthenticator(String userName, String password) {
			passwordAuthentication = new PasswordAuthentication(userName, password);
		}
		public PasswordAuthentication getPasswordAuthentication() {
			return passwordAuthentication;
		}
	}
	
	public static String FilenameBase64Decode(String fileName) throws UnsupportedEncodingException, Base64DecodingException {
        String result = "";
        String tmpStr = "";
        String[] decodeInfo;
        int startIndex = 0;
        int endIndex = 0;
        boolean decodingFlag = true;
        
        //1. 문자열을 디코딩 해야 하는지 판단하고
        if(fileName.indexOf("=?") == -1){
            decodingFlag = false;
            result = fileName;
        }

        while (decodingFlag) {
            if ((startIndex = fileName.indexOf("=?")) != -1) {
                endIndex = fileName.indexOf("?=");
                //2. 문자열에서 구분자를 제거해주고
                tmpStr = fileName.substring(startIndex + "=?".length(), endIndex);
                //3. 인코딩 정보들을 분리한 후에
                decodeInfo = tmpStr.split("\\?");
                //4. Base64로 디코딩 해준다
                result += new String(Base64.decode(decodeInfo[2]), decodeInfo[0]);
                fileName = fileName.substring(endIndex + "?=".length());
            } else {
                break;
            }
        }
        return result;
    }
}
