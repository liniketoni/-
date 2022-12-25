package model;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_id;
	private String pass_word;
	private String name;
	private String age;
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	private boolean admin = false;
    
//    
//    public User() {}
    public User(String user_id,String pass_word,boolean admin) {
    	this.user_id = user_id;
    	this.pass_word = pass_word;
    	this.admin = admin;
    }
    
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPass_word() {
		return pass_word;
	}
	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	@Override
	public String toString() {
		return "User [user_id=" + this.getUser_id() + ", pass_word=" + this.getPass_word() + ", admin=" + this.isAdmin() + "]";
	}
    
	
}
