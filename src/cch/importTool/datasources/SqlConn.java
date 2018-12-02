package cch.importTool.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import cch.importTool.utils.CommonUtils;

/**
 * Project Name : hbcaDailyReport<br>
 * Package Name : package cn.org.hbca.daily.datasource;<br>
 * File Name : SqlConn.java<br>
 * Type Name : SqlConn<br>
 * Created on : 2017-11-16下午04:48:42<br>
 * Created by : Chunhui Chen <br>
 * Note : <br>
 * 
 * 
 */
public class SqlConn {

	Connection conn = null;
	String dbServer = null;
	String dbPort = null;
	String dbName = null;
	String dbUser = null;
	String dbPassword = null;
	String connectionStr = null;
	String driver = null;
	public SqlConn(){
		
	
    	try {
    		Properties p = CommonUtils.getCfg();
    		dbUser = p.getProperty("jdbc.username");
    		dbPassword = p.getProperty("jdbc.password");
    		connectionStr = p.getProperty("jdbc.url");
    		driver = p.getProperty("jdbc.driver");
    		
    		Class.forName(driver).newInstance();
    		conn = DriverManager.getConnection(connectionStr,dbUser,dbPassword);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}

	}
	
	public void testConnection(){
		String sql = "SELECT * FROM CheckInOut";
		try{
			Statement stm = conn.createStatement();
			
			ResultSet rs = stm.executeQuery(sql);
			System.out.println(rs.getFetchSize());
			while(rs.next()){
				System.out.println(rs.getString("CheckTime"));
			}
			rs.close();
			stm.close();
			conn.close();
		}catch(Exception ex){
			ex.printStackTrace();
    	}
		
	}
	public boolean executeSql(String sql){
		Statement stm = null;
		try{
			stm = conn.createStatement();
			
			boolean bSucc = stm.execute(sql);
			return bSucc;
			
		}catch(Exception ex){
			ex.printStackTrace();
    	}finally{
			try {
				stm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	}
    	return false;
		
	}
	public ResultSet executeQuery(String sql){
		try {
			Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = stm.executeQuery(sql);
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return null;	
	}
	
	public void closeRs(ResultSet rs){
		try {
			if(rs!=null&&!rs.isClosed()){
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConn(){
		try {
			if(!conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		SqlConn conn = new SqlConn();
		conn.testConnection();
		conn.testConnection();
		conn.testConnection();
	}
}
