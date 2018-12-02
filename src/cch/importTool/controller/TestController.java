package cch.importTool.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cch.importTool.disruptor.file.FileEvent;
import cch.importTool.disruptor.file.FileEventWorkHandler;
import cch.importTool.init.InitDisruptor;
import cch.importTool.service.IPersonService;
import cch.importTool.utils.ApplicationContextUtil;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	IPersonService personService;

	@RequestMapping(value = "/test.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String testConfig(HttpServletRequest http) throws FileNotFoundException{
		System.out.println("hello");
		return "hello";
	}
	@RequestMapping(value = "/test2.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String test2(HttpServletRequest http) throws FileNotFoundException{
		FileEventWorkHandler.sendAuthEventFromDB("thread 3","name");
		
		return "hello";
	}
	@RequestMapping(value = "/addWar.do",produces="application/json;charset=UTF-8")
	public @ResponseBody Map<String,Object> addImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(isMultipart){
			MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
			
			//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String path = request.getServletContext().getRealPath("/");
			String fileName = sdf.format(now);
			String env = multipartRequest.getParameter("env");
			
			String fileFullPath = path +"uploadFiles" + File.separator + env +fileName + ".xls";
			MultipartFile multipartFile = multipartRequest.getFile("file_war");
			File targetFile = new File(fileFullPath);
			if(!targetFile.getParentFile().exists()){
				targetFile.getParentFile().mkdirs();
			}
			multipartFile.transferTo(new File(fileFullPath));
			
			FileEvent fileEvent = new FileEvent();
			fileEvent.setFileFullPath(fileFullPath);
			fileEvent.setFileName(fileName);
			InitDisruptor.fileEventProducer.onData(fileEvent);
			

			map.put("errorCode", "0");
			map.put("errorMsg", "成功");

			return map;
		}
		
		
		return null;
	}
}
