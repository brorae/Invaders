package engine;

import java.awt.Insets;

import javax.swing.JFrame;

import screen.Screen;

/**
 * Implements a frame to show screens on.
 *
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 *
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {

	/** Frame width. */
	private int width;
	/** Frame height. */
	private int height;
	/** Screen currently shown. */
	private Screen currentScreen;

	/**
	 * Initializes the new frame.
	 *
	 * @param width
	 *            Frame width.
	 * @param height
	 *            Frame height.
	 */
	public Frame(final int width, final int height) {
		setSize(width, height);
		setResizable(false); //스크린 크기 수정하려면 여기부분 손봐야할듯.
		setDefaultCloseOperation(EXIT_ON_CLOSE); //윈도우창 종료시 프로세스까지 깔끔하게 종료됨.

		setLocationRelativeTo(null); //윈도우창을 화면 가운데에 띄우는 역할.
		setVisible(true); //창을 화면에 보여줄건지설정. false시 창이 보이지않음.

		Insets insets = getInsets(); //상단바 만드는거같은뎁..
		this.width = width - insets.left - insets.right;
		this.height = height - insets.top + insets.bottom;
		setTitle("TASTY-GUYS Invaders");

		addKeyListener(Core.getInputManager()); //키값 입력받기.
	}

	/**
	 * Sets current screen.
	 *
	 * @param screen
	 *            Screen to show.
	 * @return Return code of the finished screen.
	 */
	public final int setScreen(final Screen screen) {
		currentScreen = screen;
		currentScreen.initialize();
		return currentScreen.run();
	}

	/**
	 * Getter for frame width.
	 *
	 * @return Frame width.
	 */
	public final int getWidth() {
		return this.width;
	}

	/**
	 * Getter for frame height.
	 *
	 * @return Frame height.
	 */

	public final int getHeight() {
		return this.height;
	}
}
