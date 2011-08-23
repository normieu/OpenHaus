package openhaus.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@Column(name = "Title")
	private String title;

	@OneToMany(mappedBy = "topic", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Thread> threads = new HashSet<Thread>();

	public Topic() {
		super();
	}

	public Topic(String title) {
		super();
		this.title = title;
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

	public Set<Thread> getThreads() {
		return threads;
	}

	public void addThread(Thread thread) {
		thread.setTopic(this);
		threads.add(thread);
	}

	public void removeThread(Thread thread) {
		for (Thread t : threads) {
			if (t.equals(thread))
				threads.remove(t);
		}
	}

	public void removeThread(int threadId) {
		for (Thread t : threads) {
			if (t.getId() == threadId)
				threads.remove(t);
		}
	}

}
