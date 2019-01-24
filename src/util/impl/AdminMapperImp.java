package util.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Admin;

import util.RowMapperObject;

public class AdminMapperImp implements RowMapperObject{

	public Object rowMapperObject(ResultSet rs) throws SQLException {
		Admin admin = new Admin();
		admin.setId(rs.getString("id"));
		admin.setLogin_name(rs.getString("login_name"));
		admin.setPassword(rs.getString("password"));
		admin.setName(rs.getString("name"));
		admin.setPhone_number(rs.getString("phone_number"));
		return admin;
	}
	
}
