package cch.importTool.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class CommonUtils {
	public static final String generateGUID(){ 
		UUID uuid=UUID.randomUUID();
	    return uuid.toString();
	}
	public static Properties getCfg() throws FileNotFoundException{
		
		InputStream inputStream = CommonUtils.class.getClassLoader().getResourceAsStream("config.properties");
		if(inputStream==null){
			return null;
		}
		Properties p = new Properties();
		try {   
			p.load(inputStream);   
		} catch (IOException e1) {   
			e1.printStackTrace();   
		}   
		return p;
	}
}
