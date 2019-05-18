package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class JInternalFrameCheckList extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JInternalFrameCheckList frame = new JInternalFrameCheckList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JInternalFrameCheckList() {
		setBounds(100, 100, 450, 300);

	}

}
