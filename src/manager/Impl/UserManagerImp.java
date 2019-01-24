package manager.Impl;

import java.sql.SQLException;
import java.util.List;

import Dao.UserDao;
import Dao.Impl.UserDaoImpl;

import manager.UserManager;
import util.JDBCUtil;
import util.impl.UserMapperlmp;
import entity.User;
import exception.PlaneException;

public class UserManagerImp implements UserManager{
	
	/**
	 * 判断名字是否存在
	 * 存在返回true，
	 * 否则返回false
	 */
	public boolean isName(String name) throws PlaneException{
		UserDao userDao = new UserDaoImpl();
		try{
			boolean flag = userDao.isUserName(name);
			if(flag == true){
				return true;
			}
		}catch(SQLException e){
			throw new PlaneException("出错");
		}
		return false;
	}
	
	/**
	 * 判断登录名是否存在
	 * 存在返回true
	 */
	public boolean isLoginName(String loginName) throws PlaneException {
		UserDao userDao =  new UserDaoImpl();
		try {
			boolean flag = userDao.isLoginName(loginName);
			if(flag == true){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 将用户信息添加到数据库中
	 * 
	 */
	public boolean addUser(User user) {
		UserDao userDao =  new UserDaoImpl();
		
		try {
			boolean flag = userDao.addUser(user);
			if(flag == true){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 根据登录名获取用户信息
	 */
	public User getUserByLoginName(String loginName) throws PlaneException {
		UserDao userDao =  new UserDaoImpl();
		try {
			User user = userDao.getUserByLoginName(loginName);
			if(user == null){
				return null;
			}else{
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 用于判断用户登录是否成功
	 */
	public boolean userLogin(String loginName, String password) {
		UserDao userDao =  new UserDaoImpl();
		User user = null;
		try {
			user = userDao.getUserByLoginName(loginName);
			if(user == null){
				return false;
			}else if(!user.getPassWord().equals(password)){
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 修改用户信息
	 */
	public boolean UpDateUser(User user) {
		UserDao userDao =  new UserDaoImpl();
		try {
			boolean flag = userDao.UpDateUser(user);
			if(flag == true){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
