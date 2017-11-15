package com.cdsxt.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.cdsxt.login.User;
import util.DB;

@SuppressWarnings("unused")
public class LoginDao {

	public User find(String uname,String pwd){
		User user = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "select * from user where uname = ? and pwd = ?";
		ResultSet resultSet = null;
		
		try {
			connection = DB.getConnection();
			preparedStatement = DB.getPreparedStatement(connection, sql);
			preparedStatement.setString(1, uname);
			preparedStatement.setString(2, pwd);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setPwd(resultSet.getString("pwd"));
				user.setUname(resultSet.getString("uname"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DB.closeAll(connection, preparedStatement, null);
		}
		return user;
	}
	
}
