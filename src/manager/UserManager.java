package manager;

import entity.User;
import exception.PlaneException;

public interface UserManager {
		
		/**
		 * 判断用户名是否存在
		 * @param name
		 * @return
		 */
		boolean isName(String name)  throws PlaneException;
		
		/**
		 * 判断登录名是否存在
		 * @param loginName
		 * @return boolean
		 * @throws PlaneException
		 */
		boolean isLoginName(String loginName) throws PlaneException;
		
		/**
		 * 添加用户
		 * @param user
		 * @return boolean
 		 */
		boolean addUser(User user);
		
		/**
		 * 根据登录名获取用户信息
		 * @param login_name
		 * @return
		 * @throws PlaneException
		 */
		User getUserByLoginName(String login_name) throws PlaneException;
		
		/**
		 * 判断是否登录成功
		 * @param login_name
		 * @param password
		 * @return boolean
		 */
		
		boolean userLogin(String login_name,String password);
		
		
		boolean UpDateUser(User user);
	
}
