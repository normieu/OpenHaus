package openhaus.server.managers.sessions;

import java.util.List;

import openhaus.common.Post;
import openhaus.common.Thread;
import openhaus.common.Topic;
import openhaus.server.managers.AbstractManager;
import openhaus.server.managers.TopicManager;
import openhaus.server.managers.exceptions.TransactionException;

public class TopicManagerSession extends AbstractManager implements TopicManager {

	@Override
	public void addTopic(Topic topic) throws TransactionException {
		add(topic);
	}

	@Override
	public void updateTopic(Topic topic) throws TransactionException {
		update(topic);
	}

	@Override
	public Topic getTopic(int topicId) throws TransactionException {
		return (Topic) get(Topic.class, topicId);
	}

	@Override
	public Topic getTopic(String title) throws TransactionException {
		List<Topic> topics = getAllTopics();
		for (Topic t : topics) {
			if (t.getTitle().equalsIgnoreCase(title))
				return t;
		}
		return null;
	}

	@Override
	public void removeTopic(Topic topic) throws TransactionException {
		remove(topic);
	}

	@Override
	public List<Topic> getAllTopics() throws TransactionException {
		return getAll(Topic.class);
	}

	@Override
	public void addThread(Thread thread) throws TransactionException {
		add(thread);
	}

	@Override
	public void updateThread(Thread thread) throws TransactionException {
		update(thread);
	}

	@Override
	public Thread getThread(int threadId) throws TransactionException {
		return (Thread) get(Thread.class, threadId);
	}

	@Override
	public Thread getThread(String title) throws TransactionException {
		List<Thread> threads = getAllThreads();
		for (Thread t : threads) {
			if (t.getTitle().equalsIgnoreCase(title))
				return t;
		}
		return null;
	}

	@Override
	public void removeThread(Thread thread) throws TransactionException {
		remove(thread);
	}

	@Override
	public List<Thread> getAllThreads() throws TransactionException {
		return getAll(Thread.class);
	}

	@Override
	public void addPost(Post post) throws TransactionException {
		add(post);
	}

	@Override
	public void updatePost(Post post) throws TransactionException {
		update(post);
	}

	@Override
	public Post getPost(int postId) throws TransactionException {
		return (Post) get(Post.class, postId);
	}

	@Override
	public void removePost(Post post) throws TransactionException {
		remove(post);
	}

	@Override
	public List<Post> getAllPosts() throws TransactionException {
		return getAll(Post.class);
	}

}
