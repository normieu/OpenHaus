package openhaus.common.test;

import java.util.List;

import openhaus.common.Thread;
import openhaus.common.logger.Log;
import openhaus.server.managers.TopicManager;
import openhaus.server.managers.exceptions.TransactionException;
import openhaus.server.managers.sessions.TopicManagerSession;

public class Test {

	public static void main(String[] args) {

		// ------------------------->Account creation
		//AccountManager accManager = new AccountManagerSession();
		// try {
		// accManager.addAccount(new Account("admin", "admin"));
		// } catch (TransactionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (NonUniqueException e) {
		// // TODO Auto-generated catch block
		// Log.println("Username already exists!");
		// e.printStackTrace();
		// }

		// ----------------------------->Topic creation
		TopicManager tManager = new TopicManagerSession();
		// try {
		// Account acc = accManager.getAccount("admin");
		// Topic topic = new Topic("Topic 3");
		// Thread t = new Thread("Thread 3", Date.valueOf("1993-01-01"), 0, true,
		// false, acc, topic);
		// Post p = new Post("cccc", Date.valueOf("1993-01-01"), 0, 0, acc, t);
		// t.addPost(p);
		// topic.addThread(t);
		// tManager.addTopic(topic);
		// } catch (TransactionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//

		// ----------------------------->Topic update
		// try {
		// Account acc = accManager.getAccount("admin");
		// Topic topic = tManager.getTopic(6);
		// Thread t1 = new Thread("Thread 4.1", Date.valueOf("1993-01-01"), 0,
		// true, false, acc, topic);
		// Thread t2 = new Thread("Thread 4.2", Date.valueOf("1993-01-01"), 0,
		// true, false, acc, topic);
		// Post p1 = new Post("Post 4.1", Date.valueOf("1993-01-01"), 0, 0, acc,
		// t1);
		// Post p2 = new Post("Post 4.2", Date.valueOf("1993-01-01"), 0, 0, acc,
		// t1);
		// t1.addPost(p1);
		// t1.addPost(p2);
		// topic.addThread(t1);
		// topic.addThread(t2);
		//
		// tManager.updateTopic(topic);
		// } catch (TransactionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// ----------------------------->List Topics
//		try {
//			List<Topic> topics = tManager.getAllTopics();
//			for (Topic t : topics) {
//				Log.println(t.getTitle());
//			}
//		} catch (TransactionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// ----------------------------->List Threads
		try {
			List<Thread> threads = tManager.getAllThreads();
			for (Thread t : threads) {
				Log.println(t.getTitle());
			}
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ----------------------------->List Posts
		// try {
		// List<Post> posts = tManager.getAllPosts();
		// for (Post p : posts) {
		// Log.println(p.getData());
		// }
		// } catch (TransactionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}
}
