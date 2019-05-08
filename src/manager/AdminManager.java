package manager;

import entity.Flight;

public interface AdminManager {
		
		/**
		 * 更新航班信息
		 * @param flight
		 * @return boolean
		 */
	
		boolean upDateFlight(Flight flight);
		/*
		 * 根据航班号来删除航班
		 */
		boolean delFilght(String flightNum);
		
		/**
		 * 添加航班
		 * @param flight
		 * @return boolean
		 */
	
		boolean addFlight(Flight flight);
	
	
		/**
		 * 判断管理员是否登录成功
		 * @param loginName
		 * @param passWord
		 * @return boolean
		 */
		boolean adminLogin(String loginName,String passWord);
}
