package openhaus.common;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@Column(name="Data")
	private String data;
	
	@Column(name="Date")
	private Date date;
	
	@Column(name="Likes")
	private int likes;
	
	@Column(name="Dislikes")
	private int dislikes;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Username")
	private Account account;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Thread_ID")
	private Thread thread;

	public Post() {
		super();
	}

	public Post(String data, Date date, int likes, int dislikes, Account account, Thread thread) {
		super();
		this.data = data;
		this.date = date;
		this.likes = likes;
		this.dislikes = dislikes;
		this.account = account;
		this.thread = thread;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

}
