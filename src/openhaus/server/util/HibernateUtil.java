package openhaus.server.util;

import java.util.Properties;
import java.util.TimeZone;

import openhaus.common.Account;
import openhaus.common.Topic;
import openhaus.common.Thread;
import openhaus.common.Post;
import openhaus.server.managers.exceptions.TransactionException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		init();
	}

	public static void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		//mysql account credectials
		if (!tryToBuildSessionFactory("root", ""))
			throw new RuntimeException("connection unsuccessful");
	}

	private static boolean tryToBuildSessionFactory(String username, String password) throws ExceptionInInitializerError {
		try {
			Properties p = new Properties();
			p.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			
			//openhaus-database name
			p.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/openhaus");
			p.setProperty("hibernate.show_sql", "false");
			p.setProperty("hibernate.connection.username", username);
			p.setProperty("hibernate.connection.password", password);
			// p.setProperty("hibernate.search.default.indexBase",
			// "./lucene-index");
			// p.setProperty("hibernate.search.default.directory_provider",
			// "filesystem");

			Configuration conf = new Configuration();

			// entities
			conf.setProperties(p);
			conf.addAnnotatedClass(Account.class);
			conf.addAnnotatedClass(Topic.class);
			conf.addAnnotatedClass(Thread.class);
			conf.addAnnotatedClass(Post.class);

			sessionFactory = conf.buildSessionFactory();

			return true;
		} catch (Throwable ex) {
			ex.printStackTrace();
			sessionFactory = null;
			return false;
		}
	}

	public static void addAdminAccount() throws TransactionException {
		// AccountManagerSession am = new AccountManagerSession();
		// am.addAccount(new Account("admin", "admin"));
	}

	public static Session startSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	public static void endSession() {
		sessionFactory.close();
	}

	public static boolean isConnected() {
		return sessionFactory != null;
	}

	public static boolean evaluate(String username, String password) {
		if (!isConnected())
			return tryToBuildSessionFactory(username, password);
		else
			return false;
	}

	public static void close() {

		if (isConnected()) {
			sessionFactory.close();
			sessionFactory = null;
		}
	}
}
