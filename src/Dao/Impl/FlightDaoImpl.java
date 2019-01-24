package Dao.Impl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.JDBCUtil;
import util.impl.FlightMapperlmp;

import entity.Flight;
import Dao.FlightDao;

public class FlightDaoImpl implements FlightDao{
	/**
	 * 航班添加 
	 */
	public boolean addFlight(Flight flight) {
		String sql = "insert into flight values(?,?,?,?,?,?,?)";
		try{
				int flag = JDBCUtil.executeUpdate(sql,  flight.getFlight_number(),
																			    flight.getTakeoff_time(),
																			    flight.getFlying_time(),
																			    flight.getStart_place(),
																			   	flight.getEnd_place(),
																			   	flight.getTickets(),
																			   	flight.getPrice()
																			 );
				if(flag == 0){
					System.out.println("添加失败");
				}else{
					System.out.println("添加成功");
					return true;
				}
		}catch(SQLException e){
			e.getStackTrace();
		}
		return false;
	}

	/**
	 * 根据航班号删除航班信息
	 * 
	 */
	public boolean delFlightById(String id) {
		String sql = "delete from flight where flight_number=?";
		try{
			int flag = JDBCUtil.executeUpdate(sql, id);
			if(flag == 0){
				System.out.println("删除失败");
			}else{
				System.out.println("删除成功");
				return true;
			}
	}catch(SQLException e){
		e.getStackTrace();
	}
		return false;
	}
	
	/**
	 * 根据航班号来获取信息
	 * 
	 */
	public Flight getFlightById(String id) {
		String sql = "select * from flight where flight_number=?";
		try{
			List<Flight> list = new ArrayList<Flight>();
			List<Object> obj = JDBCUtil.executeQuery(sql, new FlightMapperlmp() , id);
			if(obj.size() == 0){
				return null;
			}
			for (Object objs : obj) {
				list.add((Flight)objs);
				
			}
			return list.get(0);
	}catch(SQLException e){
		e.getStackTrace();
	}
		return null;
	}
	
	/**
	 * 修改航班的信息
	 * 
	 */
	public boolean upDateFlight(Flight flight) {
		String sql = "update flight set takeoff_time=?," +
														"flying_time=?," +
														"start_place=?," +
														"end_place=?," +
														"tickets=?," +
														"price=? " +
														"where flight_number=?";
		try{
			 int flag = JDBCUtil.executeUpdate(sql, flight.getTakeoff_time(),
					 														flight.getFlying_time(),
					 														flight.getStart_place(),
					 														flight.getEnd_place(),
					 														flight.getTickets(),
					 														flight.getPrice(),
					 														flight.getFlight_number());
			 if(flag != 0){
				 System.out.println("修改成功");
				 return true;
			 }
		}catch(SQLException e){
			e.getStackTrace();
		}
		return false;
	}
	
	/**
	 * 获取航班的所有的信息
	 */
	public List<Flight> listFlight() {
		String sql = "select * from flight";
		try{
			List<Flight> listFlight = new ArrayList<Flight>();
			List<Object> obj = JDBCUtil.executeQuery(sql, new FlightMapperlmp());
			if(obj.size() == 0){
				return null;
			}
			for (Object objs : obj) {
				listFlight.add((Flight)objs);
			}
			return listFlight;
		}catch(SQLException e){
			e.getStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取没有过期的航班信息
	 */
	public List<Flight> listFlyFlight() throws SQLException {
		String sql = "select * from flight where takeoff_time>now()";
		try{
			List<Flight> listFlight = new ArrayList<Flight>();
			List<Object> obj = JDBCUtil.executeQuery(sql, new FlightMapperlmp());
			if(obj.size() == 0){
				return null;
			}
			for (Object objs : obj) {
				listFlight.add((Flight)objs);
			}
			return listFlight;
		}catch(SQLException e){
			e.getStackTrace();
		}
		return null;
	}

}
