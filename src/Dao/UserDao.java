package Dao;

import java.sql.SQLException;
import java.util.List;

import entity.User;

public interface UserDao {
		
	/**
	 * 添加用户
	 * @return 是否添加成功
	 */
	boolean addUser(User user)  throws SQLException;
	
	/**
	 * 删除用户 
	 * @param id 根据用户的id来删除 
	 * @return 是否删除成功
	 */
	boolean delUser(String login_name)  throws SQLException;

	/**
	 * 获取用户信息
	 * @param LoginName 根据用户
	 * @return User
	 */
	User getUserByLoginName(String login_name) throws SQLException;
	
	/**
	 * 修改用户信息
	 * @param id 
	 * @return
	 */
	boolean UpDateUser(User user) throws SQLException;
	
	/**
	 * 获取的所有的用户信息
	 * @return 用户信息
	 */
	List<User> listUser()  throws SQLException;
	
	/**
	 * 判断用户姓名存在
	 * @param name  姓名
	 * @return  存在true 
	 * @throws SQLException
	 */
	
	boolean isUserName(String name) throws SQLException;
	
	/**
	 * 判断登录名是否在
	 * @param loginName
	 * @return
	 * @throws SQLException
	 */
	boolean isLoginName(String loginName) throws SQLException;
}
