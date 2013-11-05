package com.tuji.demo.resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import com.tuji.demo.bean.BusinessClass;
import com.tuji.demo.service.DBHelper;



@Path("ObjectModelService")
@Produces(MediaType.APPLICATION_XML)
public class ObjectModelService {
	
	
	private static ObjectModelService instance = new ObjectModelService();
	public static ObjectModelService getInstance()
	{
	   return instance;
	}
	
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public void createClass(String p_input) {
    	System.out.print(p_input);
    	XStream xstream = new XStream(new JettisonMappedXmlDriver());
    	BusinessClass businessclass=null;
    	xstream.alias("newbd", BusinessClass.class);
    	businessclass= (BusinessClass)xstream.fromXML(p_input);
    	System.out.print(businessclass.toString());
    	
    	DBHelper db = new DBHelper(
				"jdbc:postgresql://localhost/tuji_platform?user=postgres&password=sys",
				"org.postgresql.Driver");
    	String sql="select now() nowtime;";
    	List<Map<String, Object>> result;
		if ((result = db.executeQuery(sql)) != null) {
			//测试查询
			System.out.println(result.get(0).get("nowtime")+"yang");
		}
		
		//测试update
		sql=String.format("create table %s.%s  ( APP_ID CHAR(36) not null) ",
	      			businessclass.getTableSchema(),
	      			businessclass.getTableName()
	      		);
    	
		db.executeUpdate(sql);
    	
    	//测试select
    	/*
    	String sql="select now() nowtime;";
    	DataAccessService dataaccessservice = DataAccessService.getInstance();
    	  Connection connection = DataAccessService.getConnection();
    	  PreparedStatement statement = null;
    	  ResultSet resultSet = null;
    	  
    	  String nowtime = null;
    	 
    	  try {
    	   statement = connection.prepareStatement(sql);
    	   resultSet = statement.executeQuery();
    	   while(resultSet.next()){
    	    //user_id = resultSet.getInt("USER_ID");
    	    //user_name = resultSet.getString("USER_NAME");
    		   nowtime = resultSet.getString("NOWTIME");
    	   }
    	  } catch (SQLException e) {
    	   e.printStackTrace();
    	  } finally{
    		  dataaccessservice.close(connection, statement, resultSet);
    	  }
    	  System.out.print(nowtime);
    	  */
    	 
    	  //sql=String.format("create table %s.%s  ( APP_ID CHAR(36) not null) ",
      			
      		//	businessclass.getTableSchema(),
      		//	businessclass.getTableName()
      	//	);
    	/*  DataAccessService dataaccessservice = DataAccessService.getInstance();
    	  try {
			ResultSet resultSet=dataaccessservice.executeQuery(sql);
			String nowtime = null;
			while(resultSet.next()){
				nowtime = resultSet.getString("NOWTIME");
			}
			System.out.print(nowtime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	 
    	 
           // return Util.toJson(UserService.getInstance().createUser(
           //                 (User) Util.fromJson(user)));
    }
	
	/*@PUT
    @Path("{user}")
    @Produces(MediaType.APPLICATION_XML)
    public BusinessInvokeResult createClass(BusinessClass p_cls, boolean p_createTable)
	{
		//String sql;
	   // BusinessInvokeResult result = new BusinessInvokeResult();
	    
	    
	   // return result;
	}*/
    
   
}
