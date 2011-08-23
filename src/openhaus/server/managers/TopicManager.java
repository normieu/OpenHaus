package openhaus.server.managers;

import java.util.List;

import openhaus.common.Topic;
import openhaus.common.Thread;
import openhaus.common.Post;

import openhaus.server.managers.exceptions.TransactionException;

public interface TopicManager {

	// Topic

	void addTopic(Topic topic) throws TransactionException;

	void updateTopic(Topic topic) throws TransactionException;

	Topic getTopic(int topicId) throws TransactionException;

	Topic getTopic(String title) throws TransactionException;

	void removeTopic(Topic topic) throws TransactionException;

	List<Topic> getAllTopics() throws TransactionException;

	// Thread

	void addThread(Thread thread) throws TransactionException;

	void updateThread(Thread thread) throws TransactionException;

	Thread getThread(int threadId) throws TransactionException;

	Thread getThread(String title) throws TransactionException;

	void removeThread(Thread thread) throws TransactionException;

	List<Thread> getAllThreads() throws TransactionException;

	// Post

	void addPost(Post post) throws TransactionException;

	void updatePost(Post post) throws TransactionException;

	Post getPost(int postId) throws TransactionException;

	void removePost(Post post) throws TransactionException;

	List<Post> getAllPosts() throws TransactionException;

}
