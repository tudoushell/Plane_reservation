package Dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import util.impl.UserMapperlmp;

import entity.User;
import exception.PlaneException;
import Dao.UserDao;

public class UserDaoImpl implements UserDao{
	
	/**
	 * 添加用户 
	 */
	public boolean addUser(User user) {
		String sql = "insert into user values(?,?,?,?,?,?)";
		try{
			int flag = JDBCUtil.executeUpdate(sql, user.getId(),
														  				  user.getName(),
														  				  user.getLogin_name(),
														  				  user.getPassWord(),
														  				  user.getPhone_number(),
														  				  user.getAddress());
			if(flag != 0){
				return true;
			}
		}catch(SQLException e){
			e.getStackTrace();
		}
		return false;
	}
	
	/**
	 * 修改用户信息
	 */
	public boolean UpDateUser(User user) {
		String sql = "update user set id=?," +
														"name=?," +
														"password=?," +
														"phone_number=?," +
														"address=?" +
														" where login_name=?"; 
		try{
			int flag = JDBCUtil.executeUpdate(sql, user.getId(),
														  user.getName(),
														  user.getPassWord(),
														  user.getPhone_number(),
														  user.getAddress(),
														  user.getLogin_name());
			if(flag != 0){
				return true;
			}
		}catch(SQLException e){
			e.getStackTrace();
		}
		return false;
	}
	/**
	 * 根据登录名删除用户 
	 */
	public boolean delUser(String login_name) {
		String sql = "delete from user where login_name=?";
		try{
			int flag = JDBCUtil.executeUpdate(sql, login_name);
			
			if(flag != 0){
				return true;
			}
		}catch(SQLException e){
			e.getStackTrace();
		}
		
		return false;
	}
	/**
	 * 获取用户信息
	 */
	public User getUserByLoginName(String login_name) {
		String sql = "select * from user where login_name=?";
		try{
			List<User> list = new ArrayList<User>();
			List <Object> obj = JDBCUtil.executeQuery(sql, new UserMapperlmp(), login_name);
			if(obj.size() == 0){
				return null;
			}
			for (Object object : obj) {
				list.add((User)object);
			}
			return list.get(0);
		}catch(SQLException e){
			e.getStackTrace();
		}
		return null;
	}
	/**
	 * 列出所有的用户信息
	 */
	public List<User> listUser() {
		String sql = "select * from user";
		try{
			List<User> listUser = new ArrayList<User>();
			List <Object> obj = JDBCUtil.executeQuery(sql, new UserMapperlmp());
			for (Object object : obj) {
				listUser.add((User)object);
			}
			return listUser;
		}catch(SQLException e){
			e.getStackTrace();
		}
		return null;
	}

	
		/**
		 * 获取用户姓名
		 * 存在返回 true
		 */
		public boolean isUserName(String name) throws SQLException {
			
			String sql = "select * from user where  name=?";
			try{
				List<Object> list = JDBCUtil.executeQuery(sql, new UserMapperlmp(), name);
				if(list.size() != 0){
					return true;
				}
			}catch(SQLException e){
				e.getStackTrace();
			}
			return false;
		}
		
		
		/**
		 * 判断用户名是否存在
		 * 存在返回true
		 */
		public boolean isLoginName(String loginName) throws SQLException {
			String sql = "select * from user where  login_name=?";
			try{
				List<Object> list = JDBCUtil.executeQuery(sql, new UserMapperlmp(), loginName);
				if(list.size() != 0){
					return true;
				}
			}catch(SQLException e){
				e.getStackTrace();
			}
			return false;
		}
	
	

}
