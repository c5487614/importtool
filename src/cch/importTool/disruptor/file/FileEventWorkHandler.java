package cch.importTool.disruptor.file;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.lmax.disruptor.WorkHandler;

import cch.importTool.datasources.SqlConn;
import cch.importTool.disruptor.authication.AuthEvent;
import cch.importTool.init.InitDisruptor;
import cch.importTool.model.Person;
import cch.importTool.service.PersonService;
import cch.importTool.utils.ApplicationContextUtil;
import cch.importTool.utils.CommonUtils;
import cch.importTool.utils.Config;
import cch.importTool.utils.FileUtil;
import cch.importTool.utils.LogUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class FileEventWorkHandler implements WorkHandler<FileEvent>{

	@Autowired
	PersonService personService;
	private String name;
	public FileEventWorkHandler(String name){
		this.name = name;
		//this.personService = (PersonService) ApplicationContextUtil.getBean("personService");
	}
	@Override
	public void onEvent(FileEvent fileEvent) throws Exception {
		// TODO Auto-generated method stub
		//Thread.sleep(500);
		System.out.println(this.name + ": " + fileEvent.getFileFullPath());
		Workbook workbook=Workbook.getWorkbook(new File(fileEvent.getFileFullPath())); 
        //2:获取第一个工作表sheet
        Sheet sheet=workbook.getSheet(0);
        //3:获取数据
        System.out.println("行："+sheet.getRows());
        System.out.println("列："+sheet.getColumns());
        int iCount = sheet.getRows();
        //iCount = 10;
        for(int i=0;i<iCount;i++){
        	Cell cell0 = sheet.getCell(0, i);
        	Cell cell1 = sheet.getCell(1, i);
        	Cell cell2 = sheet.getCell(2, i);
        	Cell cell3 = sheet.getCell(3, i);
        	Person person = new Person();
        	
        	person.setId(CommonUtils.generateGUID());
        	person.setIdCardNum(cell1.getContents());
        	person.setImportTag(fileEvent.getImportTag());
        	person.setImportTime(new Date());
        	person.setMobile(cell2.getContents());
        	person.setName(cell0.getContents());
        	person.setPassword(cell3.getContents());
        	if(personService==null){
        		this.personService = (PersonService) ApplicationContextUtil.getBean("personService");
        		personService.addPerson(person);
        	}else{
        		personService.addPerson(person);
        	}
        	
        }
        //最后一步：关闭资源
        workbook.close();

        sendAuthEventFromDB(fileEvent.getImportTag(),name);
        
        //InitDisruptor.authEventProducer.onData(authEvent);
	}
	
	public static void sendAuthEventFromDB(String importTag,String threadName){
		Config config = (Config) ApplicationContextUtil.getBean("config");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = config.getLogPath()+File.separator+threadName+"_"+sdf.format(new Date())+".txt";
		File file = FileUtil.createFile(fileName);
		
		SqlConn conn = new SqlConn();
		//person begin
		FileUtil.writeStr("sendAuthEventFromDB person Begin",file);
		
		String sql = "select Count(*) recordCount from Person where ImportTag='"+importTag+"'";
		ResultSet rs = null;
		try {
			rs = conn.executeQuery(sql);
			rs.next();
			long recordCount = rs.getLong("recordCount");
			conn.closeRs(rs);
			FileUtil.writeStr("total records " + recordCount ,file);
			
			sql = "select * from Person where ImportTag='"+importTag+"'";
			rs = conn.executeQuery(sql);
			int iCount = 0;
			while(rs.next()){
				AuthEvent authEvent = new AuthEvent();
				authEvent.setId(rs.getString("Id"));
				authEvent.setLegalPersonIdCardNum("");
				authEvent.setLegalPersonName("");
				authEvent.setMobile(rs.getString("Mobile"));
				authEvent.setPersonIdCardNum(rs.getString("IdCardNum"));
				authEvent.setPersonName(rs.getString("Name"));
				authEvent.setType("person");
				InitDisruptor.authEventProducer.onData(authEvent);
				String remain = "authRingBuffer left :"+ InitDisruptor.authRingBuffer.remainingCapacity();
				FileUtil.writeStr("person records " + (iCount++)+", "+remain ,file);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.closeRs(rs);
			conn.closeConn();
		}
		//person end
		
		//legal person begin
		conn = new SqlConn();
		FileUtil.writeStr("sendAuthEventFromDB legal person Begin",file);
		sql = "select Count(*) recordCount from LegalPerson where ImportTag='"+importTag+"'";
		ResultSet rsLegal = null;
		try {
			rsLegal = conn.executeQuery(sql);
			rsLegal.next();
			long recordCount = rsLegal.getLong("recordCount");
			conn.closeRs(rsLegal);
			FileUtil.writeStr("total records " + recordCount ,file);
			
			sql = "select * from Person where ImportTag='"+importTag+"'";
			rsLegal = conn.executeQuery(sql);
			int iCount = 0;
			while(rsLegal.next()){
				AuthEvent authEvent = new AuthEvent();
				authEvent.setId(rsLegal.getString("Id"));
				authEvent.setLegalPersonIdCardNum(rsLegal.getString("CompanyIdNum"));
				authEvent.setLegalPersonName(rsLegal.getString("CompanyName"));
				authEvent.setMobile(rsLegal.getString("Mobile"));
				authEvent.setPersonIdCardNum(rsLegal.getString("IdCardNum"));
				authEvent.setPersonName(rsLegal.getString("Name"));
				authEvent.setType("legalperson");
				InitDisruptor.authEventProducer.onData(authEvent);
				FileUtil.writeStr("legal person records " + iCount++ ,file);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.closeRs(rsLegal);
			conn.closeConn();
		}
		//legal person end
	}

}
