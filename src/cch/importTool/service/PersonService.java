package cch.importTool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cch.importTool.dao.PersonMapper;
import cch.importTool.model.Person;

@Service
public class PersonService implements IPersonService {
	@Autowired
	PersonMapper personMapper;
	public boolean addPerson(Person model){
		int iRet = -1;
		iRet = personMapper.insert(model);
		if(iRet>0){
			return true;
		}
		return false;
	}
}
