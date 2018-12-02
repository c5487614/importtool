package cch.importTool.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class LogUtil {
	
	public static String logPath;
	public static void log(String info){
		if(logPath==null||"".equals(logPath)){
			Config config = (Config) ApplicationContextUtil.getBean("config");
			if(config==null){
				logPath = "D:/logs/";
			}else{
				logPath = config.getLogPath();
			}
			System.out.println("------------" + logPath);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2log = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sdf.format(new Date())+".txt";
		boolean isAppend =false;
		File logDirectory = new File(logPath);
		File logFile = new File(logPath+File.separator+fileName); 
		if(!logDirectory.exists()){
			logDirectory.mkdirs();
		}
		if(!logFile.exists()){
			try {
				System.out.println("xxxxxxx");
				System.out.println(logFile.createNewFile());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			isAppend = true;
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(logFile,isAppend);
			if(!info.endsWith("\r\n")){
				info = info + "\r\n";
			}
			String txt = sdf2log.format(new Date()) + ": "  +info ;
			byte[] data = txt.getBytes("utf-8");
			fos.write(data);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				fos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				fos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
	public static void main(String[] args){

	}

}
