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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class DictionaryFrame extends JFrame {

	private static final long serialVersionUID = 4297347599446063412L;
	JPanel panel;
	JScrollPane textPanel;
	JPanel buttonPanel;
	JButton findButton;
	JButton setDirButton;
	JPanel findPanel;
	JTextPane text;
	JTextField search;
	ConfigurationStorage config;
	File[] directories;

	private void createComponents() {
		buttonPanel = new JPanel();
		panel = new JPanel();
		textPanel = new JScrollPane();
		findPanel = new JPanel();
		findButton = new JButton();
		setDirButton = new JButton();
		search = new JTextField();
		text = new JTextPane();

	}

	private void setCaptions() {
		findButton.setText(config.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.FIND));
		setDirButton.setText(config.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.SET_DIRECTORIES));
		setTitle(config.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.DICTIONARY));
	}

	private void showFrame() {

		panel.setLayout(new BorderLayout());

		buttonPanel.add(new JLabel(config.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.SEARCH)));
		buttonPanel.add(search);
		buttonPanel.add(findButton);
		buttonPanel.add(setDirButton);

		search.setPreferredSize(new Dimension(150, 27));
		textPanel.setPreferredSize(new Dimension(300, 300));

		textPanel.setViewportView(text);

		panel.add(textPanel, BorderLayout.SOUTH);
		panel.add(buttonPanel, BorderLayout.NORTH);

		getContentPane().add(panel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		// show();
	}

	private void setDirectories() {
		String temp = config.pathConfiguration
				.getConfig(ConfigurationDefaultPath.DICTIONARIES);
		String[] dirPaths = temp.split(File.pathSeparator);
		directories = new File[dirPaths.length];
		for (int i = 0; i < dirPaths.length; i++)
			directories[i] = new File(dirPaths[i]);
	}

	public DictionaryFrame(ConfigurationStorage config) {
		this.config = config;
		createComponents();
		setCaptions();
		setActions();
		showFrame();
		setDirectories();
	}

	private void find() {
		List results = find(search.getText(), directories);
		text.setText("");
		textPanel.setVisible(true);
		for (Iterator i = results.iterator(); i.hasNext();)
			text.setText(text.getText() + i.next().toString() + "\n");
		if (results.isEmpty())
			text.setText(config.messageConfiguration
					.getConfig(ConfigurationDefaultMessages.NO_MATCH));
	}

	private void setActions() {
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				find();
			}
		});
		setDirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				final JFileChooser fileChooser = new JFileChooser();
				fileChooser
						.setCurrentDirectory(new File(
								config.pathConfiguration
										.getConfig(ConfigurationDefaultPath.FILE_CHOOSER_DEFAULT_PATH)));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setMultiSelectionEnabled(true);
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					directories = fileChooser.getSelectedFiles();

					String dirs = new String();
					for (int i = 0; i < directories.length; i++) {
						dirs += directories[i].getPath();
						if (i < directories.length - 1)
							dirs += File.pathSeparator;
					}
					config.pathConfiguration.getDetailedConfig(
							ConfigurationDefaultPath.DICTIONARIES).setState(
							dirs);
				}
			}
		});
		search.addKeyListener(new java.awt.event.KeyListener() {
			public void keyPressed(KeyEvent e) {
				// Intentionally left empty.
			}

			public void keyReleased(KeyEvent e) {
				// Intentionally left empty.
			}

			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n')
					find();
			}
		});
	}

	public List find(String string, File[] dirs) {
		List list = new LinkedList();
		for (int i = 0; i < dirs.length; i++)
			visitDirs(dirs[i], list, string);
		return list;
	}

	public void visitDirs(File dirFile, List list, String search) {
		if (dirFile == null)
			return;
		if (dirFile.isDirectory()) {
			String[] children = dirFile.list();
			for (int j = 0; j < children.length; j++)
				visitDirs(new File(dirFile, children[j]), list, search);
		} else if (dirFile.isFile()) {
			try {
				InputFile input = new InputFile(dirFile.getPath());
				List temp = (List) (new LineParser(
						input
								.obtain(config.optionConfiguration
										.getConfig(ConfigurationDefaultOptions.FILE_ENCODING)),
						config.regexConfiguration)).convert();
				for (Iterator i = temp.iterator(); i.hasNext();) {
					LineItem item = (LineItem) i.next();
					if (item.toString().indexOf(search) >= 0)
						list.add(item);
				}
				// System.out.println("attended "+dirFile);
			} catch (Exception e) {
				// System.out.println("not attended "+dirFile);
			}
		}
	}
}
