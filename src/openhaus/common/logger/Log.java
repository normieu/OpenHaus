package openhaus.common.logger;

public class Log {

	private static final boolean debug = true;

	public static void print(String s) {
		if (debug)
			System.out.print(s);
	}

	public static void println(String s) {
		if (debug)
			System.out.println(s);
	}

	public static void println() {
		if (debug)
			System.out.println();
	}

}
