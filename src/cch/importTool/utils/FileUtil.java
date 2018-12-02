package cch.importTool.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

	public static File createFile(String filePath){
		System.out.println(filePath);
		File file = new File(filePath);
		try {
			file.createNewFile();
			return file;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean writeStr(String data,File file){
		try {
			RandomAccessFile randomFile = new RandomAccessFile(file, "rw");
			randomFile.seek(randomFile.length());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			randomFile.write((sdf.format(new Date())+": "+ data+"\r\n").getBytes("UTF8"));
			randomFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
