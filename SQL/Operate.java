package SQL;

class Operate{
	private String name = null;
	private String password = null;
	private String admin = null;
	
	public void setName(String name){
		this.name = name;
	}
	public void setpassword(String password){
		this.password = password;
	}
	public void setadmin(String admin){
		this.admin = admin;
	}
	public String getName(){
		return name;
	}
	
	public String getpassword(){
		return password;
	}
	
	public String getadmin(){
		return admin;
	}
}