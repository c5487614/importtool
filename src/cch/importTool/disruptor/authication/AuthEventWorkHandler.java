package cch.importTool.disruptor.authication;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import com.lmax.disruptor.WorkHandler;

import cch.importTool.service.PersonService;
import cch.importTool.utils.ApplicationContextUtil;
import cch.importTool.utils.Config;
import cch.importTool.utils.FileUtil;


public class AuthEventWorkHandler implements WorkHandler<AuthEvent>{

	@Autowired
	PersonService personService;
	private String name;
	public AuthEventWorkHandler(String name){
		this.name = name;
		//this.personService = (PersonService) ApplicationContextUtil.getBean("personService");
	}
	@Override
	public void onEvent(AuthEvent authEvent){
		// TODO Auto-generated method stub
		//LogUtil.log(name+": "+fileEvent.getPersonName());
		Config config = (Config) ApplicationContextUtil.getBean("config");
		String fileName = config.getLogPath()+File.separator+name+".txt";
		File file = FileUtil.createFile(fileName);
		FileUtil.writeStr(name + ": "+ authEvent.getPersonName(), file);
	}

}
