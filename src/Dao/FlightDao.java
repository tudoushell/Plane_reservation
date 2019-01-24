package Dao;

import java.sql.SQLException;
import java.util.List;

import entity.Flight;

public interface FlightDao {
	/**
	 * 
	 * 增加航班
	 * 
	 * @return 是否增加成功
	 */
	boolean addFlight(Flight flight) throws SQLException;

	/**
	 * 删除航班
	 * 
	 * @param id
	 *            航班号
	 * @return 是否删除成功
	 */
	boolean delFlightById(String id) throws SQLException;

	/**
	 * 修改航班信息
	 * 
	 * @return 是否修改成功
	 */
	boolean upDateFlight(Flight flight) throws SQLException;

	/**
	 * 查找航班
	 * 
	 * @param id
	 *            航班号
	 * @return 航班的信息
	 */
	Flight getFlightById(String id) throws SQLException;

	/**
	 * 
	 * @return 获取所的航班信息
	 */

	List<Flight> listFlight() throws SQLException;
	
	/*
	 * 列出所有没有过期的航班
	 */
	List<Flight> listFlyFlight() throws SQLException;
}
