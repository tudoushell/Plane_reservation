package Dao;

import java.sql.SQLException;
import java.util.List;

import entity.Admin;

public interface AdminDao {
	
	
	/**
	 * 添加管理员
	 * 
	 */
	boolean addAdmin(Admin admin) throws SQLException;
	
	/**
	 * 删除管理员
	 */
	 boolean delAdminByLoginName(String login_name) throws SQLException;
	
	/**
	 * 根据管理员的登录名来查找
	 */
	  Admin getAdminByLoginName(String login_name) throws SQLException;
	  
	  /**
	   * 修改管理员
	   * @param id
	   * @return
	   */
	  boolean upDateAdmin(Admin admin) throws SQLException;
	
	/**
	 * 获取所的管理员
	 */
	 List<Admin> listAdmin();

}
