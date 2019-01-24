package Dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import util.impl.AdminMapperImp;

import entity.Admin;
import Dao.AdminDao;

public class AdminDaoImpl implements AdminDao {

	/**
	 * 添加管理员
	 */
	public boolean addAdmin(Admin admin) throws SQLException {
		String sql = "insert into admin(id,login_name,password,name,phone_number) values(?,?,?,?,?)";
		try {
			int a = JDBCUtil.executeUpdate(sql, admin.getId(), admin.getLogin_name(), admin.getPassword(), admin.getName(), admin.getPhone_number());
			if (a > 0) {
				System.out.println("创建成功");
				return true;
			}
		} catch (SQLException e) {
			e.getStackTrace();
			throw new SQLException("无法操作，请联系管理员");
		}
		return false;
	}

	/**
	 * 删除管理员
	 */
	public boolean delAdminByLoginName(String login_name) throws SQLException {
		String sql = "delete from admin where login_name=?";
		try {
			int a = JDBCUtil.executeUpdate(sql, login_name);
			if (a == 0) {
				System.out.println("管理员不存在");
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.getStackTrace();
			throw new SQLException("无法操作");
		}
		return false;
	}

	/**
	 * 通过登录名来获取管理员信息
	 */
	public Admin getAdminByLoginName(String login_name) {
		String sql = "select * from admin where login_name=?";
		try {
			List<Admin> list = new ArrayList<Admin>();
			List<Object> objects = JDBCUtil.executeQuery(sql, new AdminMapperImp(), login_name);
			for (Object obj : objects) {
				list.add((Admin) obj);
			}
			return list.get(0);
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		System.out.println("该管理员不存在");
		return null;
	}
	/**
	 * 列出所有管理员信息
	 */
	public List<Admin> listAdmin() {
		String sql ="select * from admin";
		try{
			List<Admin> listAdmin = new ArrayList<Admin>();
			List<Object> objects = JDBCUtil.executeQuery(sql, new AdminMapperImp());
			for(Object obj: objects){
				listAdmin.add((Admin)obj);
			}
			return listAdmin;
		}catch(SQLException e){
			e.getStackTrace();
		}
		return null;
	}
   /**
    * 修改管理员的信息
    */
	public boolean upDateAdmin(Admin admin) {
		String sql = "update admin "+
							"login_name=?," +
							"password=?," +
							"name=?," +
							"phone_number=?" +
							"where id=?";
		try{
			int a = JDBCUtil.executeUpdate(sql, admin.getLogin_name(),
																	  admin.getPassword(),
																	  admin.getName(),
																	  admin.getPhone_number(),
																	  admin.getId()
																);
			if(a == 0){
				System.out.println("修改失败");
			}else{
				System.out.println("修改成功");
				return true;
			}
		}catch(SQLException e){
			e.getStackTrace();
		}
		return false;
	}

}
