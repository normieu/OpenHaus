package openhaus.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6699720978541520510L;

	@Id
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password")
	private String password;

	public Account() {
		super();
	}

	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean comparePassword(String pass) {
		return password.equals(pass);
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
