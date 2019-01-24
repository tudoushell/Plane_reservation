package entity;
/*
 *船班信息
 * 
 */
public class Flight {
	  //航班号 
	   private String flight_number;
	  //出发时间 
	   private String takeoff_time;
	   //飞行时间
	   private String flying_time;
	   //出发地
	   private String start_place;
	   //目的地
	   private String end_place;
	   //余票
	   private int tickets;
	   //票价
	   private float price;
	   
	public Flight() {
		super();
	}
	
	public Flight(String flightNumber, String takeoffTime, String flyingTime, String startPlace, String endPlace, int tickets, float price) {
		super();
		flight_number = flightNumber;
		takeoff_time = takeoffTime;
		flying_time = flyingTime;
		start_place = startPlace;
		end_place = endPlace;
		this.tickets = tickets;
		this.price = price;
	}
	public String getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(String flightNumber) {
		flight_number = flightNumber;
	}
	public String getTakeoff_time() {
		return takeoff_time;
	}
	public void setTakeoff_time(String takeoffTime) {
		takeoff_time = takeoffTime;
	}
	public String getFlying_time() {
		return flying_time;
	}
	public void setFlying_time(String flyingTime) {
		flying_time = flyingTime;
	}
	public String getStart_place() {
		return start_place;
	}
	public void setStart_place(String startPlace) {
		start_place = startPlace;
	}
	public String getEnd_place() {
		return end_place;
	}
	public void setEnd_place(String endPlace) {
		end_place = endPlace;
	}
	public int getTickets() {
		return tickets;
	}
	public void setTickets(int tickets) {
		this.tickets = tickets;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "航班号: "+ flight_number +"出发时间: "+ takeoff_time +"飞行时间: "+ flying_time 
		 			 +"出发地: "+ start_place +"目的地: "+ end_place +"余票: "+ tickets +"票价: "+ price;
	}
	   
	   
	
}
