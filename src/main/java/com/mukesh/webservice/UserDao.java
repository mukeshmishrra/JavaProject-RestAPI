package com.mukesh.webservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserDao {
	Connection con = null;

	//creating connection...
	//create constructor
	public UserDao() {
		// database configuration
		String url = "jdbc:mysql://localhost:3306/restapi_using_jersey";
		String username = "root";
		String password = "root";
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//fetching records from database
	public List<Users> getAllUsers() {
		List<Users> user = new ArrayList<>();
		String sql = "select * from users";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Users u = new Users();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setCity(rs.getString(3));
				user.add(u);

			}

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("getting all record wala error hun main");
		}
		
		return user;

	}
		
	// insert data into database	
	public void saveData(Users u) {
		String sql = "insert into users values(?,?,?)";
		try {
			PreparedStatement p = con.prepareStatement(sql);
			p.setInt(1, u.getId());
			p.setString(2, u.getName());
			p.setString(3, u.getCity() );
			
			p.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("saveData wale function ka error hun main");
		}		
	}

	// getting single user by id
	public Users getUserById(int id) {
		String sql = "select * from users where id" + id;
		Users u = new Users();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setCity(rs.getString(3));
			}

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("getting all record wala error hun main");
		}

		return u;
	}

	
	//delete user from database
	public void deleteUser(int id) {
		String sql ="delete from users where id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	
	//update records
	public void updateUser( Users u) {
		String sql="update users set name=?,city=? where id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getCity());
			ps.setInt(3, u.getId());
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println(e);
			
		}
		
	}
	
}