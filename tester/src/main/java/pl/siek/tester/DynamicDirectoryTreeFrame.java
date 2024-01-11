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

/*
 * This code is based on an example provided by Richard Stanford, 
 * a tutorial reader.
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;

public class DynamicDirectoryTreeFrame extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4085624285445200018L;

	private static JFrame frame;

	public static String ADD_COMMAND = "add";
	public static String REMOVE_COMMAND = "remove";
	public static String CLOSE_COMMAND = "clear";
	public static String SET_COMMAND = "set";
	public static String DOWNLOAD_COMMAND = "download";
	public static String MKDIR_COMMAND = "mkdir";
	// private static String DOWNLOAD_COMMAND = "download";

	private JLabel setVariable;
	private ConfigurationItem input;

	private DynamicDirectoryTree treePanel;

	private File rootDir;

	private ConfigurationStorage configuration;

	public Map buttonMap;

	public DynamicDirectoryTreeFrame(ConfigurationStorage configuration) {
		super(new BorderLayout());
		this.configuration = configuration;

		/* temp */
		rootDir = new File(this.configuration.pathConfiguration.getConfig(
				"input directory").toString());
		String fileIconPath = this.configuration.pathConfiguration.getConfig(
				"file icon").toString();
		String dirIconPath = this.configuration.pathConfiguration.getConfig(
				"dir icon").toString();
		String rootIconPath = this.configuration.pathConfiguration.getConfig(
				"root icon").toString();
		buttonMap = new HashMap();
		// Create the components.
		treePanel = new DynamicDirectoryTree(rootDir.getName(), new ImageIcon(
				rootIconPath), new ImageIcon(dirIconPath), new ImageIcon(
				fileIconPath), this.configuration.messageConfiguration
				.getConfig("file").toString(),
				this.configuration.errorConfiguration.getConfig("unknown")
						.toString(), this.configuration.errorConfiguration
						.getConfig("error").toString(),
				this.configuration.messageConfiguration.getConfig("quick view")
						.toString(), this.buttonMap);
		populateTree(treePanel);

		input = configuration.pathConfiguration.getDetailedConfig("input");
		setVariable = new JLabel(this.configuration.pathConfiguration
				.getConfig("input").toString());
		JLabel setConstant = new JLabel("<HTML><i>"
				+ this.configuration.messageConfiguration.getConfig("set")
						.toString() + ":</i>");
		JPanel setPanel = new JPanel();
		setPanel.setLayout(new BorderLayout());
		setPanel
				.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		setPanel.add(setConstant, BorderLayout.NORTH);
		setPanel.add(setVariable, BorderLayout.EAST);

		JButton addButton = new JButton(this.configuration.messageConfiguration
				.getConfig("add").toString());
		addButton.setActionCommand(ADD_COMMAND);
		addButton.addActionListener(this);

		JButton removeButton = new JButton(
				this.configuration.messageConfiguration.getConfig("remove")
						.toString());
		removeButton.setActionCommand(REMOVE_COMMAND);
		removeButton.addActionListener(this);

		JButton closeButton = new JButton(
				this.configuration.messageConfiguration.getConfig("close")
						.toString());
		closeButton.setActionCommand(CLOSE_COMMAND);
		closeButton.addActionListener(this);

		JButton setButton = new JButton(this.configuration.messageConfiguration
				.getConfig("set").toString());
		setButton.setActionCommand(SET_COMMAND);
		setButton.addActionListener(this);

		JButton downloadButton = new JButton(
				this.configuration.messageConfiguration.getConfig("download")
						.toString());
		downloadButton.setActionCommand(DOWNLOAD_COMMAND);
		downloadButton.addActionListener(this);

		JButton mkDirButton = new JButton(
				this.configuration.messageConfiguration.getConfig("mkdir")
						.toString());
		mkDirButton.setActionCommand(MKDIR_COMMAND);
		mkDirButton.addActionListener(this);

		buttonMap.put(ADD_COMMAND, addButton);
		buttonMap.put(REMOVE_COMMAND, removeButton);
		buttonMap.put(CLOSE_COMMAND, closeButton);
		buttonMap.put(SET_COMMAND, setButton);
		buttonMap.put(DOWNLOAD_COMMAND, downloadButton);
		buttonMap.put(MKDIR_COMMAND, mkDirButton);

		// Lay everything out.
		treePanel.setPreferredSize(new Dimension(300, 150));
		add(treePanel, BorderLayout.CENTER);

		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(setPanel);
		panel.add(setButton);
		panel.add(addButton);
		panel.add(mkDirButton);
		panel.add(removeButton);
		panel.add(downloadButton);
		panel.add(closeButton);

		add(panel, BorderLayout.LINE_END);

	}

	public void populateTree(DynamicDirectoryTree treePanel) {
		if (rootDir.isDirectory()) {
			String[] children = rootDir.list();
			children = sortByType(rootDir, children);
			for (int i = 0; i < children.length; i++) {
				visitAllDirsAndFiles(new File(rootDir, children[i]), null);
			}
		} else {
			System.out.println("invalid root dir");
			frame.dispose();
		}

	}

	public void visitAllDirsAndFiles(File dirFile,
			DefaultMutableTreeNode parentNode) {
		if (dirFile == null)
			return;
		DefaultMutableTreeNode dirNode = null;
		// if(parentNode!=null)
		dirNode = treePanel.addObject(parentNode, dirFile);
		if (dirFile.isDirectory()) {
			String[] children = dirFile.list();
			children = sortByType(dirFile, children);
			for (int i = 0; i < children.length; i++) {
				visitAllDirsAndFiles(new File(dirFile, children[i]), dirNode);
			}
		}
	}

	private String[] sortByType(File dir, String[] array) {
		List dirs = new ArrayList();
		List files = new ArrayList();
		for (int i = 0; i < array.length; i++) {
			if ((new File(dir, array[i]).isDirectory()))
				dirs.add(array[i]);
			else
				files.add(array[i]);
		}
		Collections.sort(files);
		Collections.sort(dirs);
		int i = 0;
		for (Iterator iter = dirs.iterator(); iter.hasNext(); i++)
			array[i] = (String) iter.next();
		for (Iterator iter = files.iterator(); iter.hasNext(); i++)
			array[i] = (String) iter.next();
		return array;
	}

	private void copyFile(File sourceFile, File targetFile) throws IOException {
		FileInputStream fis = new FileInputStream(sourceFile);
		FileOutputStream fos = new FileOutputStream(targetFile);
		byte[] buf = new byte[1024];
		int i = 0;
		while ((i = fis.read(buf)) != -1) {
			fos.write(buf, 0, i);
		}
		fis.close();
		fos.close();
	}

	private boolean removeDir(File dir) {
		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			for (int i = 0; i < children.length; i++)
				if (children[i].isFile())
					children[i].delete();
				else if (children[i].isDirectory())
					removeDir(children[i]);
			return dir.delete();
		}
		return false;
	}

	private void copyFile(URL sourceURL, File targetFile) throws IOException {
		URLConnection urlConnection = sourceURL.openConnection();
		InputStream fis = urlConnection.getInputStream();
		FileOutputStream fos = new FileOutputStream(targetFile);
		byte[] buf = new byte[1024];
		int i = 0;
		while ((i = fis.read(buf)) != -1) {
			fos.write(buf, 0, i);
		}
		fis.close();
		fos.close();
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (ADD_COMMAND.equals(command)) {
			// Add button clicked
			final JFileChooser fileChooser = new JFileChooser();
			fileChooser
					.setCurrentDirectory(new File(
							this.configuration.pathConfiguration
									.getConfig(ConfigurationDefaultPath.FILE_CHOOSER_DEFAULT_PATH)));
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePanel
						.getLastSelectedPathComponent();
				// if(!((File)node.getUserObject()).isDirectory())node=(DefaultMutableTreeNode)node.getParent();
				File sourceFile = fileChooser.getSelectedFile();

				File targetDir;
				if (node.isRoot())
					targetDir = rootDir;
				else
					targetDir = (File) node.getUserObject();

				File targetFile = new File(targetDir.getPath()
						+ File.separatorChar + sourceFile.getName());
				// int option = JOptionPane.CLOSED_OPTION;
				// if(targetFile.exists()) //TODO HANDLE FILE ALREAD EXISTS
				/*
				 * option = JOptionPane.showConfirmDialog( frame,
				 * this.configuration
				 * .errorConfiguration.getConfig("file already exists"
				 * ).toString()+" "+((File)((DefaultMutableTreeNode)treePanel.
				 * getLastSelectedPathComponent
				 * ()).getUserObject()).getName()+"\n"
				 * +this.configuration.errorConfiguration
				 * .getConfig("do you want to replace it").toString(),
				 * this.configuration
				 * .errorConfiguration.getConfig("warning").toString(),
				 * JOptionPane.YES_NO_OPTION);
				 * if(option==JOptionPane.YES_OPTION||!targetFile.exists())
				 */
				try {
					// if(!targetFile.exists())
					treePanel.addObject(targetFile);
					copyFile(sourceFile, targetFile);
				} catch (IOException exc) {
					// TODO HANDLE NO SUCH FILE
					System.out.println("Error copying file");
				}
			}
		}

		else if (REMOVE_COMMAND.equals(command)) {
			// Remove button clicked
			int option = JOptionPane.showConfirmDialog(frame,
					this.configuration.messageConfiguration.getConfig(
							"Are you sure you want to remove").toString()
							+ " "
							+ ((File) ((DefaultMutableTreeNode) treePanel
									.getLastSelectedPathComponent())
									.getUserObject()).getName(),
					this.configuration.messageConfiguration.getConfig("remove")
							.toString(), JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePanel
						.getLastSelectedPathComponent();

				File targetFile = (File) node.getUserObject();
				boolean successfull;
				if (targetFile.isDirectory())
					successfull = removeDir(targetFile);
				else
					successfull = targetFile.delete();
				if (!successfull)
					System.out.println("deleting not successful");// TODO HANDLE
				// NOT
				// DELETED
				else
					treePanel.removeCurrentNode();
			}
		}

		else if (CLOSE_COMMAND.equals(command)) {
			// Clear button clicked.
			frame.dispose();
		}

		else if (SET_COMMAND.equals(command)) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePanel
					.getLastSelectedPathComponent();
			if (!node.isRoot()) {
				File file = (File) node.getUserObject();
				if (file.isFile()) {
					boolean success = input.setState(file.toString());
					if (!success)// TODO HANDLE
						System.out.println("input not set successfully");
					else
						setVariable.setText(file.toString());
				}
			}
		}

		else if (MKDIR_COMMAND.equals(command)) {
			// make dir button clicked.
			String dirName = (String) JOptionPane.showInputDialog(frame,
					this.configuration.messageConfiguration.getConfig("mkdir")
							.toString(),
					this.configuration.messageConfiguration.getConfig("mkdir")
							.toString(), JOptionPane.PLAIN_MESSAGE, null, null,
					null);
			if (dirName == null)
				return;

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePanel
					.getLastSelectedPathComponent();
			if (!node.isRoot() && !((File) node.getUserObject()).isDirectory())
				node = (DefaultMutableTreeNode) node.getParent();
			File parentDir = (File) node.getUserObject();
			File newDir = new File(parentDir.toString() + File.separatorChar
					+ dirName);
			if (newDir.mkdir())
				treePanel.addObject(newDir);
			else
				// TODO
				System.out.println("cannot make dir");

		}

		else if (DOWNLOAD_COMMAND.equals(command)) {
			// Download file from URL button clicked.
			String address = (String) JOptionPane.showInputDialog(frame,
					this.configuration.messageConfiguration
							.getConfig("address").toString(),
					this.configuration.messageConfiguration.getConfig(
							"download").toString(), JOptionPane.PLAIN_MESSAGE,
					null, null, this.configuration.pathConfiguration.getConfig(
							"default address").toString());
			// TODO DIALOGUE GET ADRESS d'URL
			if (address != null)
				try {
					URL url = new URL(address);
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePanel
							.getLastSelectedPathComponent();
					File targetDir = (File) node.getUserObject();
					File targetFile = new File(targetDir.getPath()
							+ File.separatorChar + url.getFile());

					/*
					 * if(targetFile.exists()) //TODO HANDLE FILE ALREAD EXISTS
					 * System.out.println("file already exists");
					 */

					/*
					 * int option = JOptionPane.showConfirmDialog( frame,
					 * this.configuration
					 * .errorConfiguration.getConfig("file already exists"
					 * ).toString()+" "
					 * +((File)((DefaultMutableTreeNode)treePanel
					 * .getLastSelectedPathComponent
					 * ()).getUserObject()).getName()+"\n"
					 * +this.configuration.errorConfiguration
					 * .getConfig("do you want to replace it").toString(),
					 * this.configuration
					 * .errorConfiguration.getConfig("warning").toString(),
					 * JOptionPane.YES_NO_OPTION);
					 * if(option==JOptionPane.YES_OPTION||!targetFile.exists())
					 */
					{
						copyFile(url, targetFile);
						treePanel.addObject(targetFile);
					}
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	static void createAndShowGUI(ConfigurationStorage config) {
		// Create and set up the window.
		frame = new JFrame(config.messageConfiguration.getConfig(
				"dynamic tree title").toString());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		DynamicDirectoryTreeFrame newContentPane = new DynamicDirectoryTreeFrame(
				config);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(new ConfigurationStorage());
			}
		});
	}
}
