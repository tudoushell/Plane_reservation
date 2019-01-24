package manager;

import java.util.List;

import entity.Flight;

public interface FlightManger {
	
	/**
	 * 列出所有的航班信息
	 * @return  list<Flight>
	 */
	List<Flight> listFlyFlight();
	
	/**
	 * 更新航班信息
	 * @param flight
	 * @return boolean
	 */
	boolean upDateFlight(Flight flight); 
}
