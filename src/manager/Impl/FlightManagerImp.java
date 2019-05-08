package manager.Impl;

import java.sql.SQLException;
import java.util.List;

import objectfactory.ObjectFactory;

import Dao.FlightDao;
import Dao.Impl.FlightDaoImpl;

import entity.Flight;
import manager.FlightManger;

public class FlightManagerImp implements FlightManger{
	FlightDao flightDao = (FlightDao) ObjectFactory.getObject("FlightDao");

	/**
	 * 列出没有过期的航班 
	 */
	public List<Flight> listFlyFlight() {
//		FlightDao flightDao = new FlightDaoImpl();
		try {
			List<Flight> listFilght = flightDao.listFlyFlight();
			if(listFilght == null){
				return null;
			}else{
				return listFilght;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 更新航班信息
	 */
	public boolean upDateFlight(Flight flight) {
//		FlightDao flightDao = new FlightDaoImpl();
		try {
			boolean flag = flightDao.upDateFlight(flight);
			if(flag == true){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
