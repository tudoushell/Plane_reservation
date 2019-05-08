package manager.Impl;

import java.sql.SQLException;

import objectfactory.ObjectFactory;

import entity.Admin;
import entity.Flight;

import Dao.AdminDao;
import Dao.FlightDao;
import Dao.Impl.AdminDaoImpl;
import Dao.Impl.FlightDaoImpl;
import manager.AdminManager;






public class AdminManagerImp implements AdminManager{
	
	FlightDao flightDao = (FlightDao) ObjectFactory.getObject("FlightDao");
	AdminDao adminDao = (AdminDao) ObjectFactory.getObject("AdminDao");
	/**
	 * 更新航班信息
	 */

	public boolean upDateFlight(Flight flight) {
//		FlightDao flightDao = new FlightDaoImpl();
		try {
			boolean flag = flightDao.upDateFlight(flight);
			if(flag){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	
	/**
	 * 根据航班号来删除航班
	 */
	public boolean delFilght(String flightNum) {
//		FlightDao flightDao = new FlightDaoImpl();
		try {
			boolean flag = flightDao.delFlightById(flightNum);
			if(flag){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 添加航班
	 * @author soft01
	 *
	 */
	public boolean addFlight(Flight flight) {
//		FlightDao flightDao = new FlightDaoImpl();
		try {
			boolean flag = flightDao.addFlight(flight);
			if(flag){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	/*
	 * 用于判断管理员的登录
	 * (non-Javadoc)
	 * @see manager.AdminManager#adminLogin(java.lang.String, java.lang.String)
	 */
	public boolean adminLogin(String loginName, String passWord) {
		AdminDao adminDao = new AdminDaoImpl();
		try {
			Admin admin = adminDao.getAdminByLoginName(loginName);
			if(admin == null){
				return false;
			}else if(! admin.getPassword().equals(passWord)){
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
