package openhaus.common;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Thread {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@Column(name = "Title")
	private String title;

	@Column(name = "Date")
	private Date date;

	@Column(name = "Views")
	private int views;

	@Column(name = "Open")
	private boolean open;

	@Column(name = "Sticky")
	private boolean sticky;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Username")
	private Account account;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Topic_ID")
	private Topic topic;

	@OneToMany(mappedBy = "thread", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Post> posts = new HashSet<Post>();

	public Thread() {
		super();
	}

	public Thread(String title, Date date, int views, boolean open, boolean sticky, Account account, Topic topic) {
		super();
		this.title = title;
		this.date = date;
		this.views = views;
		this.open = open;
		this.sticky = sticky;
		this.account = account;
		this.topic = topic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isSticky() {
		return sticky;
	}

	public void setSticky(boolean sticky) {
		this.sticky = sticky;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void addPost(Post post) {
		post.setThread(this);
		posts.add(post);
	}

	public void removePost(int postId) {
		for (Post p : posts) {
			if (p.getId() == postId) {
				posts.remove(p);
			}
		}
	}

	public void removePost(Post post) {
		for (Post p : posts) {
			if (p.equals(post)) {
				posts.remove(p);
			}
		}
	}

}
