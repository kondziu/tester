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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class ConfigurationFrame extends javax.swing.JFrame {

	/**
	 * Visual Configuration class is a civilized way to fidget with options.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	private static final long serialVersionUID = -7009469646906283407L;

	/**
	 * Creates new form VisualInterface
	 * 
	 * @param ConfigurationStorage
	 *            inherited configuration
	 */
	ConfigurationFrame(ConfigurationStorage configurationAndSettings) {
		this.inheritedConfigurationAndSettings = configurationAndSettings;

		this.configurationAndSettings = new ConfigurationStorage(new HashMap(
				configurationAndSettings.configToMap()));
		initComponents();
	}

	/**
	 * Initiates components and shows form.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	private void initComponents() {

		JScrollPane scrollPane;

		optionLabels = new LinkedList();
		optionButtons = new LinkedList();
		// errorLabels = new LinkedList();
		// errorButtons = new LinkedList();
		// messageLabels = new LinkedList();
		// messageButtons = new LinkedList();
		pathLabels = new LinkedList();
		pathButtons = new LinkedList();
		regexLabels = new LinkedList();
		regexButtons = new LinkedList();
		rulesLabels = new LinkedList();
		rulesButtons = new LinkedList();
		buttonPanel = new JPanel();
		mainPanel = new JPanel();
		saveButton = new JButton(configurationAndSettings.messageConfiguration
				.getConfig("save"));
		closeButton = new JButton(configurationAndSettings.messageConfiguration
				.getConfig("close"));
		addButton = new JButton(configurationAndSettings.messageConfiguration
				.getConfig("add"));

		configurationPane = new JTabbedPane();

		optionsPanel = new JPanel();
		regexPanel = new JPanel();
		rulesPanel = new JPanel();
		pathsPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		saveButton.setEnabled(false);

		saveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveSettingActionPerformed(evt);
			}
		});
		closeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeSettingActionPerformed(evt);
			}
		});
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSettingActionPerformed(evt);
			}
		});
		// errorsPanel = new JPanel();
		// messagesPanel = new JPanel();
		// prepareTab(configurationAndSettings.errorConfiguration.getConfig(),errorLabels,errorButtons,errorsPanel);
		// prepareTab(configurationAndSettings.messageConfiguration.getConfig(),messageLabels,messageButtons,messagesPanel);

		prepareTab("option",
				inheritedConfigurationAndSettings.optionConfiguration
						.getDetailedConfig(), optionLabels, optionButtons,
				optionsPanel);
		prepareTab("path", inheritedConfigurationAndSettings.pathConfiguration
				.getDetailedConfig(), pathLabels, pathButtons, pathsPanel);
		prepareTab("regex",
				inheritedConfigurationAndSettings.regexConfiguration
						.getDetailedConfig(), regexLabels, regexButtons,
				regexPanel);
		prepareTab("rules",
				inheritedConfigurationAndSettings.rulesConfiguration
						.getDetailedConfig(), rulesLabels, rulesButtons,
				rulesPanel);

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(optionsPanel);
		scrollPane.setName("option");
		configurationPane.addTab(configurationAndSettings.messageConfiguration
				.getConfig("general options"), null, scrollPane,
				configurationAndSettings.messageConfiguration
						.getConfig("general options tooltip"));
		scrollPane = new JScrollPane();
		scrollPane.setName("path");
		scrollPane.setViewportView(pathsPanel);
		configurationPane.addTab(configurationAndSettings.messageConfiguration
				.getConfig("file paths"), null, scrollPane,
				configurationAndSettings.messageConfiguration
						.getConfig("file paths tooltip"));
		scrollPane = new JScrollPane();
		scrollPane.setName("regex");
		scrollPane.setViewportView(regexPanel);
		configurationPane.addTab(configurationAndSettings.messageConfiguration
				.getConfig("regular expressions"), null, scrollPane,
				configurationAndSettings.messageConfiguration
						.getConfig("regular expressions tooltip"));
		scrollPane = new JScrollPane();
		scrollPane.setName("rules");
		scrollPane.setViewportView(rulesPanel);
		configurationPane.addTab(configurationAndSettings.messageConfiguration
				.getConfig("rules"), null, scrollPane,
				configurationAndSettings.messageConfiguration
						.getConfig("rules tooltip"));

		// scrollPane = new JScrollPane();
		// scrollPane.setViewportView(messagesPanel);
		// configurationPane.addTab((String)configurationAndSettings.messageConfiguration.getConfig("captions and messages"),
		// null,
		// scrollPane,(String)configurationAndSettings.messageConfiguration.getConfig("captions and messages tooltip"));
		// scrollPane = new JScrollPane();
		// scrollPane.setViewportView(errorsPanel);
		// configurationPane.addTab((String)configurationAndSettings.messageConfiguration.getConfig("error messages"),
		// null,
		// scrollPane,(String)configurationAndSettings.messageConfiguration.getConfig("error messages tooltip"));

		buttonPanel.add(addButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(closeButton);
		// buttonPanel.add(fileButton);

		mainPanel.add(configurationPane);
		mainPanel.add(buttonPanel);
		getContentPane().add(mainPanel);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(true);
		setTitle(configurationAndSettings.messageConfiguration
				.getConfig("configuration title"));
		pack();
		setVisible(true);

		setSize(400, 400);
	}

	/**
	 * Shows (not yet) a confirmation dialogue box and disposes of frame.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param ActionEvent
	 *            evt
	 */
	private void closeSettingActionPerformed(ActionEvent evt) {
		if (true)
			// TODO MAKE ME ASK FIRST!!!
			this.dispose();
	}

	/**
	 * Shows a frame to fill in
	 * 
	 * @author K. Siek
	 * @version 2.003
	 * @param ActionEvent
	 *            evt
	 */
	private void addSettingActionPerformed(ActionEvent evt) {
		ConfigurationItem item = new ConfigurationItem();
		String section = configurationPane.getSelectedComponent().getName();

		JPanel configPanel = (JPanel) ((JScrollPane) configurationPane
				.getSelectedComponent()).getViewport().getComponent(0);

		List configLabels = null, configButtons = null;

		if (section.compareTo("option") == 0) {
			configLabels = optionLabels;
			configButtons = optionButtons;
		} else if (section.compareTo("rules") == 0) {
			configLabels = rulesLabels;
			configButtons = rulesButtons;
		} else if (section.compareTo("path") == 0) {
			configLabels = pathLabels;
			configButtons = pathButtons;
		} else if (section.compareTo("regex") == 0) {
			configLabels = regexLabels;
			configButtons = regexButtons;
		}

		new ConfigurationItemInputForm(item,
				((Configuration) configurationAndSettings.configToMap().get(
						section)), configPanel, configLabels, configButtons,
				new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						changeSettingActionPerformed(evt);
					}
				}, configurationAndSettings.messageConfiguration,
				configurationAndSettings.errorConfiguration);
		// ,inheritedConfigurationAndSettings.messageConfiguration,inheritedConfigurationAndSettings.errorConfiguration);
		// .addConfig(item);
	}

	/**
	 * Generates one option panel to put into the scroll pane.
	 * 
	 * @author K. Siek
	 * @version 2.003
	 * @param ConfigurationItem
	 *            item
	 * @param String
	 *            section
	 * @param JTabbedPane
	 *            configurationPane
	 * @param List
	 *            configLabels
	 * @param List
	 *            configButtons
	 * @param ActionListener
	 *            changeStatus
	 * @return JPanel
	 */
	public static JPanel generateOptionPanel(ConfigurationItem item,
			String section, JPanel configPanel, List configLabels,
			List configButtons, ActionListener changeStatus) {
		Object key = item.getKey();
		JLabel label = new JLabel((String) key);
		JButton button = new JButton((String) item.getState());
		JPanel panel = new JPanel(new BorderLayout());
		String tooltip = item.getDescription();
		if (tooltip == null)
			tooltip = "n\\a";
		button.setName(section + " ??section?? " + (String) key);
		// List configLabels=null;
		// List configButtons=null;
		/*
		 * if(section.compareTo("option")==0){configLabels=optionLabels;
		 * configButtons=optionButtons;} else
		 * if(section.compareTo("rules")==0){configLabels=rulesLabels;
		 * configButtons=rulesButtons;} else
		 * if(section.compareTo("path")==0){configLabels=pathLabels;
		 * configButtons=pathButtons;} else
		 * if(section.compareTo("regex")==0){configLabels=regexLabels;
		 * configButtons=regexButtons;}
		 */

		configLabels.add(label);
		configButtons.add(button);
		panel.add(label, BorderLayout.WEST);
		panel.add(button, BorderLayout.EAST);
		configPanel.add(panel);
		panel.setToolTipText(tooltip);
		button.addActionListener(changeStatus);

		return configPanel;
	}

	/**
	 * Saves changes to configuration. (The object is passed from this frame, to
	 * replace he inherited data)
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param ActionEvent
	 *            evt
	 */
	private void saveSettingActionPerformed(ActionEvent evt) {
		if (true) {
			saveButton.setEnabled(false);
			inheritedConfigurationAndSettings = configurationAndSettings;

		}
		/*
		 * if(((String)configurationAndSettings.optionConfiguration.getConfig("debug"
		 * )).compareToIgnoreCase("on")==0){ FileOutput file = new
		 * FileOutput("debugConfig.txt"); file.generate(zumzink); }*
		 */
	}

	/**
	 * Run class separatelly. No changes are saved to file.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 *            file path to path configuration file
	 */
	public static void main(String args[]) {
		if (args.length > 0) {
			ConfigurationStorage inherited = new ConfigurationStorage(args[0]);
			ConfigurationFrame box = new ConfigurationFrame(inherited);
			box.setVisible(true);
		} else
			System.out
					.println("pass file path of path configuration through parameter");
	}

	/**
	 * Prepares a tab for a tabbed pane. Creates buttons, tooltips and labels.
	 * 
	 * @author K. Siek
	 * @version 2.001
	 * @param String
	 *            section name (i.e. option,path,regex,rules)
	 * @param Map
	 *            detailed configuration map holding ConfiguratonItem elements
	 * @param List
	 *            of labels with map's keys as captions
	 * @param List
	 *            of buttons with map's items as captions
	 * @param JPanel
	 *            panel
	 */
	public void prepareTab(String section, Map configuration,
			List configLabels, List configButtons, JPanel configPanel) {
		configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.Y_AXIS));
		configPanel.setName(section);
		for (Iterator i = configuration.keySet().iterator(); i.hasNext();) {
			Object key = i.next();
			JLabel label = new JLabel((String) key);
			JButton button = new JButton(
					(String) ((ConfigurationItem) configuration.get(key))
							.getState());
			JPanel panel = new JPanel(new BorderLayout());
			String tooltip = ((ConfigurationItem) configuration.get(key))
					.getDescription();
			if (tooltip == null)
				tooltip = "n\\a";
			button.setName(section + " ??section?? " + (String) key);
			configLabels.add(label);
			configButtons.add(button);
			panel.add(label, BorderLayout.WEST);
			panel.add(button, BorderLayout.EAST);
			configPanel.add(panel);
			panel.setToolTipText(tooltip);
			button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					changeSettingActionPerformed(evt);
				}
			});
		}
	}

	/**
	 * Changes settings.
	 * 
	 * @author K. Siek
	 * @version 2.001
	 * @param ActionEvent
	 *            evt
	 */
	private void changeSettingActionPerformed(ActionEvent evt) {
		String buttonText = ((JButton) (evt.getSource())).getText();
		String buttonInfo[] = ((JButton) (evt.getSource())).getName().split(
				"\\?\\?section\\?\\?");
		String section = buttonInfo[0].trim();
		String key = buttonInfo[1].trim();
		String dialogue = null;
		boolean OK = false;

		while (!OK) {
			dialogue = (String) JOptionPane.showInputDialog(this, "Setting: "
					+ key, "Change Settings", JOptionPane.INFORMATION_MESSAGE,
					null, null, buttonText);
			ConfigurationItem item = (ConfigurationItem) (((Configuration) configurationAndSettings
					.configToMap().get(section)).getDetailedConfig().get(key));
			if (!item.isPossible(dialogue) && dialogue != null) {
				String possibilities = "";
				for (Iterator i = item.getPossibilities().iterator(); i
						.hasNext();)
					possibilities += "\n" + i.next().toString();
				JOptionPane.showMessageDialog(this,
						configurationAndSettings.errorConfiguration
								.getConfig("not possible option choice")
								+ possibilities,
						configurationAndSettings.errorConfiguration
								.getConfig("error"),
						JOptionPane.INFORMATION_MESSAGE);
			} else
				OK = true;
		}

		if (dialogue != null)
			dialogue = dialogue.trim();
		if ((dialogue != null) && (dialogue.length() > 0)
				&& (dialogue.compareToIgnoreCase(buttonText) != 0)) {
			saveButton.setEnabled(true);
			Map sectionMap = ((Configuration) (configurationAndSettings
					.configToMap().get(section))).getDetailedConfig();
			((JButton) (evt.getSource())).setText(dialogue);
			ConfigurationItem item = (ConfigurationItem) sectionMap.get(key);
			item.setState(dialogue);
			// sectionMap.put(key,dialogue);
			// System.out.println(sectionMap);
		}

	}

	// private JScrollPane scrollPane;
	private JPanel buttonPanel;
	private JButton saveButton;
	private JButton closeButton;
	// private JButton fileButton;
	private JButton addButton;
	private JTabbedPane configurationPane;
	private JPanel optionsPanel;
	private JPanel mainPanel;

	private JPanel regexPanel;
	private JPanel rulesPanel;
	private JPanel pathsPanel;
	private ConfigurationStorage inheritedConfigurationAndSettings;
	private ConfigurationStorage configurationAndSettings;
	private List optionLabels;
	private List optionButtons;
	// private List errorLabels;
	// private List errorButtons;
	// private List messageLabels;
	// private List messageButtons;
	private List pathLabels;
	private List pathButtons;
	private List regexLabels;
	private List regexButtons;
	private List rulesLabels;
	private List rulesButtons;
}
