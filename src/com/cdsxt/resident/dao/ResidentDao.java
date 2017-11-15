package com.cdsxt.resident.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DB;
import com.cdsxt.resident.vo.Resident;


public class ResidentDao {
	public List<Resident> findAll(){
		List<Resident>residents = new ArrayList<Resident>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String sql = "select * from resident"; 
		 
		try {
			connection = DB.getConnection();
			statement = DB.getStatement(connection);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Resident resident = new Resident();
				rs2bean(resultSet, resident);
				residents.add(resident);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(connection, statement, resultSet);
		}
		return residents;
	}
	
	public List<Resident> dataByedu(int edu){
		List<Resident>residents = new ArrayList<Resident>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String sql = "select * from resident where edu="+edu;
		 
		try {
			connection = DB.getConnection();
			statement = DB.getStatement(connection);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Resident resident = new Resident();
				rs2bean(resultSet, resident);
				residents.add(resident);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(connection, statement, resultSet);
		}
		return residents;
	}
	
	public List<Resident> dataByage(int limit1,int limit2){
		List<Resident> residents = new ArrayList<Resident>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "select * from resident where age between ? and ?";
		 
		connection = DB.getConnection();
		preparedStatement = DB.getPreparedStatement(connection, sql);
		
		try {
			preparedStatement.setInt(1, limit1);
			preparedStatement.setInt(2, limit2);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Resident resident = new Resident();
				rs2bean(resultSet, resident);
				residents.add(resident);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(connection, preparedStatement, resultSet);
		}
		return residents;
	}
	
	public void del(int id){
		Connection connection = null;
		Statement statement = null;
		String sql = "delete from resident where id = "+id;
		System.out.println("ResidentDao.del()+sql:"+sql);
		
		try {
			connection = DB.getConnection();
			statement = DB.getStatement(connection);
			statement.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(connection, statement, null);
		}	
	}
	
	public void add(Resident resident){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "insert into resident(id,rname,gender,age,cid,edu,email,rent,phone,bdate,edate,rltprepath,rltrealpath,photoname) values (null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println(resident.getRname());
		
		try {
			connection = DB.getConnection();
			preparedStatement = DB.getPreparedStatement(connection, sql);
			//set的时候尽量保持与Resident类里的属性顺序一致，否则会导致属性插入顺序混乱
			preparedStatement.setString(1, resident.getRname());
			preparedStatement.setInt(2, resident.getGender());
			preparedStatement.setInt(3, resident.getAge());
			preparedStatement.setString(4,resident.getCid());
			preparedStatement.setInt(5,resident.getEdu());
			preparedStatement.setString(6,resident.getEmail());
			preparedStatement.setString(7,resident.getRent());
			preparedStatement.setString(8,resident.getPhone());
			preparedStatement.setDate(9,new java.sql.Date(resident.getBdate().getTime()));
			preparedStatement.setDate(10,new java.sql.Date(resident.getEdate().getTime()));
			preparedStatement.setString(11,resident.getRltprepath());
			preparedStatement.setString(12,resident.getRltrealpath());
			preparedStatement.setString(13,resident.getPhotoname());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(connection, preparedStatement, null);
		}
	}
	
	/**
	 * 根据id查找一个user
	 * @param id
	 * @return
	 */
	public Resident findById(int id){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String sql = "select * from resident where id ="+id;
		
		connection = DB.getConnection();
		statement = DB.getStatement(connection);
		Resident resident = new Resident();
		try {
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				rs2bean(resultSet, resident);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(connection, statement, resultSet);	
		}
		return resident;
	}
	
	/**
	 * 修改一个user
	 * @param user
	 */
	public void updateResident(Resident resident){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "update resident set rname=?,gender=?,age=?,cid=?,edu=?,email=?,rent=?,phone=?,bdate=?,edate=?,photoname=?,rltrealpath=?,rltprepath=? where id = ?";
		connection = DB.getConnection();
		preparedStatement = DB.getPreparedStatement(connection, sql);
		
		try {
			preparedStatement.setString(1, resident.getRname());
			preparedStatement.setInt(2, resident.getGender());
			preparedStatement.setInt(3, resident.getAge());
			preparedStatement.setString(4, resident.getCid());
			preparedStatement.setInt(5, resident.getEdu());
			preparedStatement.setString(6, resident.getEmail());
			preparedStatement.setString(7, resident.getRent());
			preparedStatement.setString(8, resident.getPhone());
			preparedStatement.setDate(9, new java.sql.Date(resident.getBdate().getTime()));
			preparedStatement.setDate(10, new java.sql.Date(resident.getEdate().getTime()));
			preparedStatement.setString(11, resident.getPhotoname());
			preparedStatement.setString(12,resident.getRltrealpath());
			preparedStatement.setString(13,resident.getRltprepath());
			preparedStatement.setInt(14, resident.getId());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(connection, preparedStatement, null);
		}
	}
	
	public List<Resident> search(String category,String key){
		List<Resident> residents = null;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String sql="";
		if(category.equals("edu")){
			if(key.equals("专科以下")){
			sql = "select * from resident where edu=1";
			}else if(key.equals("专科")){
				sql = "select * from resident where edu=2";
			}else if(key.equals("本科")){
				sql = "select * from resident where edu=3";
			}else if(key.equals("硕士")){
				sql = "select * from resident where edu=4";
			}else if(key.equals("硕士以上")){
				sql = "select * from resident where edu=5";
			}
		}else{
			sql = "select * from resident where "+category+" like '%"+key+"%'";
		}
		System.out.println("----- "+sql+" -----");
		try {
			connection = DB.getConnection();
			statement = DB.getStatement(connection);
			resultSet = statement.executeQuery(sql);
			residents = new ArrayList<Resident>();
			while (resultSet.next()) {
				Resident resident = new Resident();
				rs2bean(resultSet, resident);
				residents.add(resident);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(connection, statement, resultSet);
		}
		return residents;
	}
	
	public List<Resident> findByPage(int num,int pageSize){
		List<Resident> residents = new ArrayList<Resident>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "select * from resident limit ?,?";

		connection = DB.getConnection();
		preparedStatement = DB.getPreparedStatement(connection, sql);
		
		try {
			preparedStatement.setInt(1, (num-1)*pageSize);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Resident resident = new Resident();
				rs2bean(resultSet, resident);
				residents.add(resident);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(connection, preparedStatement, resultSet);
		}
		return residents;
	}
	
	public int count(){
		int count = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String sql = "select count(id) from resident"; 
		 
		try {
			connection = DB.getConnection();
			statement = DB.getStatement(connection);
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()){
				count = resultSet.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(connection, statement, resultSet);
		}
		return count;
	} 
	
	public void rs2bean(ResultSet resultSet ,Resident resident){
		try {
			resident.setId(resultSet.getInt("id"));
		    resident.setRname(resultSet.getString("rname"));
		    resident.setGender(resultSet.getInt("gender"));
		    resident.setAge(resultSet.getInt("age"));
		    resident.setCid(resultSet.getString("cid"));
		    resident.setEdu(resultSet.getInt("edu"));
		    resident.setEmail(resultSet.getString("email"));
		    resident.setRent(resultSet.getString("rent"));
		    resident.setPhone(resultSet.getString("phone"));
		    resident.setBdate(resultSet.getDate("bdate"));
		    resident.setEdate(resultSet.getDate("edate"));
		    resident.setPhotoname(resultSet.getString("photoname"));
		    resident.setRltrealpath(resultSet.getString("rltrealpath"));
		    resident.setRltprepath(resultSet.getString("rltprepath"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}