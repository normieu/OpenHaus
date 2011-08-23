package openhaus.ui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Launcher {

	public Launcher() {

	}

	public void launch() {
		System.setProperty("sun.java2d.translaccel", "true");
		System.setProperty("sun.java2d.windows", "true");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Frame frame = new Frame();

				Swapper.INSTANCE.init();
				Swapper.INSTANCE.setFrame(frame);
				Swapper.INSTANCE.swap(Swapper.LOGIN_SCREEN);
			}
		});
	}
}
