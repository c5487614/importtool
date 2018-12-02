package cch.importTool.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext appContext;
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		this.appContext = arg0;
	}

	public static Object getBean(String beanName ) {
		//for main debug added by Mark Chen 20180228
		if(appContext==null){
			return null;
		}
//		String[] list = appContext.getBeanDefinitionNames();
//		for(String str :list){
//			System.out.println(str);
//		}
		return appContext.getBean(beanName); 
	}
}
