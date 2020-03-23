package user;

public class User {
	private String username;
	private String password;
	private String phonenumber;
	private String flag;
	
	public User() {
		
	}
	
	public User(String username, String password, String phonenumber) {
		this.username=username;
		this.password=password;
		this.phonenumber=phonenumber;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String toString() {
        String s = this.username +  " " + this.password + " " + this.phonenumber + " " + this.flag;
        return s;
    }

}
