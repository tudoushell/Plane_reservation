package exception;

public class PlaneException extends Exception{
	static final long serialVersionUID = -3387516993234L;

	public PlaneException() {
	
	}
	public PlaneException(String info){
			super(info);
	}
}
