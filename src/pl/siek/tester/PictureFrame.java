/*  
 *  This file is part of Tester.
 *
 *  Tester is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Tester is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Tester.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2013 Konrad Siek <konrad.siek@gmail.com>
 */
 
package pl.siek.tester;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PictureFrame extends javax.swing.JFrame {
	/**
	 * Shows a picture.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	private static final long serialVersionUID = -7576671790216901439L;
	private ImageIcon icon;
	private JPanel panel;
	private JLabel label;

	/**
	 * constructs minimal and most default version with default error messages.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 *            image path
	 */
	public PictureFrame(String path) {
		initiateComponents(path, null, null, null, null, null);
	}

	/**
	 * Constructs constructs version with custom messages.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 *            image path
	 * @param String
	 *            picture frame title
	 * @param String
	 *            error box title
	 * @param String
	 *            error message
	 */
	public PictureFrame(String path, String pictureTitle, String errorTitle,
			String errorMsg) {
		initiateComponents(path, null, null, pictureTitle, errorTitle, errorMsg);
	}

	protected PictureFrame(String path, int height, int width,
			String pictureTitle, String errorTitle, String errorMsg) {
		initiateComponents(path, new Integer(height), new Integer(width),
				pictureTitle, errorTitle, errorMsg);
	}

	/**
	 * Initiates components.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 *            image path
	 * @param Integer
	 *            height //does not work
	 * @param Integer
	 *            width //does not work
	 * @param String
	 *            picture frame title
	 * @param String
	 *            error box title
	 * @param String
	 *            error message
	 */
	private void initiateComponents(String path, Integer height, Integer width,
			String pictureTitle, String errorTitle, String errorMsg) {
		icon = new ImageIcon(path);
		panel = new JPanel();
		if ((icon.getIconHeight() <= 0) || (icon.getIconWidth() <= 0)) {
			label = new JLabel("o.O");
			String message, title;
			if (errorMsg != null)
				message = new String(errorMsg);
			else
				message = new String("File: " + path
						+ "\n does not exist\n or is invalid.");
			if (errorTitle != null)
				title = new String(errorTitle);
			else
				title = new String("Error");
			JOptionPane.showMessageDialog(this, message, title,
					JOptionPane.ERROR_MESSAGE);
		} else {
			label = new JLabel(icon);
			/*
			 * if(!(height==null||width==null))
			 * icon.getImage().getScaledInstance()
			 */
		}
		panel.add(label);
		if (pictureTitle != null)
			this.setTitle(pictureTitle);
		else
			this.setTitle("Picture");
		this.getContentPane().add(panel);
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new PictureFrame("c:\\picture.jpgx", "Picchor", "Eat brains!",
						"Your brain is the size of a peanut. Go! See!")
						.setVisible(true);
			}
		});

	}

}