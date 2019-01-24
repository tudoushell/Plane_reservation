package util.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

import util.RowMapperObject;

public class UserMapperlmp implements RowMapperObject{

	public Object rowMapperObject(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setLogin_name(rs.getString("login_name"));
		user.setPassWord(rs.getString("password"));
		user.setPhone_number(rs.getString("phone_number"));
		user.setAddress(rs.getString("address"));
		return user;
	}

}
