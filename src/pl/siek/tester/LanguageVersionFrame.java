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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class LanguageVersionFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 6135520092345115946L;
	private JPanel messagePanel;
	private JPanel errorPanel;
	private JPanel buttonPanel;
	private JPanel mainPanel;
	private ButtonGroup messageButtonGroup;
	private ButtonGroup errorButtonGroup;
	private JLabel messageLabel;
	private JLabel errorLabel;
	private JButton cancel;
	private JButton reload;
	private String chosenMessages;
	private String chosenErrors;
	private ConfigurationItem formerMessages;
	private ConfigurationItem formerErrors;
	private String msgDir;
	private String errDir;
	private Main parent;
	private String reloadCaption, titleCaption, cancelCaption, defaultCaption,
			errorsCaption, messagesCaption;

	public LanguageVersionFrame(Main parent, ConfigurationStorage configuration) {

		reloadCaption = configuration.messageConfiguration.getConfig("reload");
		titleCaption = configuration.messageConfiguration
				.getConfig("language version title");
		cancelCaption = configuration.messageConfiguration.getConfig("cancel");
		defaultCaption = configuration.messageConfiguration
				.getConfig("default");
		errorsCaption = configuration.messageConfiguration
				.getConfig("error message files");
		messagesCaption = configuration.messageConfiguration
				.getConfig("message and caption files");

		msgDir = configuration.pathConfiguration.getConfig("message directory");
		formerMessages = configuration.pathConfiguration
				.getDetailedConfig("messages");
		errDir = configuration.pathConfiguration.getConfig("error directory");
		formerErrors = configuration.pathConfiguration
				.getDetailedConfig("errors");

		initComponents();
		this.parent = parent;
	}

	private void initComponents() {
		messagePanel = new JPanel();
		errorPanel = new JPanel();
		buttonPanel = new JPanel();
		mainPanel = new JPanel();
		reload = new JButton();
		cancel = new JButton();
		messageButtonGroup = new ButtonGroup();
		errorButtonGroup = new ButtonGroup();
		messageLabel = new JLabel();
		errorLabel = new JLabel();

		messagePanel
				.setLayout(new BoxLayout(messagePanel, BoxLayout.PAGE_AXIS));
		errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setLayout(new BorderLayout());

		setElementCaptions();

		messagePanel.add(messageLabel);
		errorPanel.add(errorLabel);
		messagePanel.setName("messages");
		errorPanel.setName("errors");

		generateRadioButtons(errDir, errorButtonGroup, errorPanel, formerErrors
				.getState().toString());
		generateRadioButtons(msgDir, messageButtonGroup, messagePanel,
				formerMessages.getState().toString());

		reload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				reloadActionPerformed(evt);
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelActionPerformed(evt);
			}
		});

		buttonPanel.add(reload);
		buttonPanel.add(cancel);

		mainPanel.add(messagePanel, BorderLayout.NORTH);
		mainPanel.add(errorPanel, BorderLayout.WEST);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		getContentPane().add(mainPanel);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
	}

	protected void cancelActionPerformed(ActionEvent evt) {
		dispose();
	}

	protected void reloadActionPerformed(ActionEvent evt) {
		String separator, passedMsgs, passedErrors;

		if (chosenMessages != null) {
			if (msgDir.endsWith(File.separator))
				separator = null;
			else
				separator = File.separator;
			formerMessages.setState(msgDir + separator + chosenErrors);
			passedMsgs = formerMessages.getState().toString();
		} else {
			formerMessages.setState("");
			passedMsgs = null;
		}

		if (chosenErrors != null) {
			if (msgDir.endsWith(File.separator))
				separator = null;
			else
				separator = File.separator;
			formerErrors.setState(errDir + File.separator + chosenMessages);
			passedErrors = formerErrors.getState().toString();
		} else {
			formerErrors.setState("");
			passedErrors = null;
		}

		parent.reloadLanguage(passedMsgs, passedErrors);
		dispose();
	}

	private void generateRadioButtons(String path, ButtonGroup buttonGroup,
			JPanel panel, String formerVersion) {
		List fileList = getFiles(new File(path));
		JRadioButton def = new JRadioButton();
		def.setText("(" + defaultCaption + ")");
		def.addActionListener(this);
		buttonGroup.add(def);
		panel.add(def);

		for (Iterator i = fileList.iterator(); i.hasNext();) {
			File file = (File) i.next();
			JRadioButton radio = new JRadioButton();
			radio.setText(file.getName());
			radio.addActionListener(this);
			radio.setName(file.toString());
			if (formerVersion.endsWith(file.getName()))
				radio.setSelected(true);
			buttonGroup.add(radio);
			panel.add(radio);
		}
	}

	private List getFiles(File dir) {
		List outputList = null;
		if (dir.isDirectory()) {
			String[] children = dir.list();
			outputList = new LinkedList();
			for (int i = 0; i < children.length; i++) {
				File childFile = new File(children[i]);
				if (childFile.getName().endsWith(".txt")
						|| childFile.getName().endsWith(".cfg")
						|| childFile.getName().endsWith(".lang"))
					outputList.add(childFile);
			}
		}
		return outputList;
	}

	private void setElementCaptions() {
		setTitle(titleCaption);
		cancel.setText(cancelCaption);
		reload.setText(reloadCaption);
		messageLabel.setText(messagesCaption);
		errorLabel.setText(errorsCaption);
	}

	public void actionPerformed(ActionEvent e) {
		if (((JRadioButton) e.getSource()).getParent().getName().compareTo(
				"messages") == 0) {
			chosenMessages = ((JRadioButton) e.getSource()).getName();
		} else {
			chosenErrors = ((JRadioButton) e.getSource()).getName();
		}
	}
}
