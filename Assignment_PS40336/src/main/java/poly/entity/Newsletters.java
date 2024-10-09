package poly.entity;

public class Newsletters {
	private String email;
	private boolean status;
	
	public Newsletters() {
		super();
	}
	
	public Newsletters(String email, boolean status) {
		super();
		this.email = email;
		this.status = status;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
