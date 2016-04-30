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
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

public class ConfigurationItemInputForm extends JFrame {

	private ConfigurationItem output;
	private Configuration outputConfiguration;
	// private ConfigurationItem internal;

	private JTextArea key;
	private JTextArea state;
	private JTextPane description;
	private JTextPane options;

	private JScrollPane descriptionScroll;
	private JScrollPane optionsScroll;

	private JLabel keyLabel;
	private JLabel stateLabel;
	private JLabel descriptionLabel;
	private JLabel optionsLabel;

	private JPanel keyPanel;
	private JPanel statePanel;
	private JPanel descriptionPanel;
	private JPanel optionsPanel;
	private JPanel buttonsPanel;
	private JPanel mainPanel;

	private JButton ok;
	private JButton cancel;

	private String error;
	private String noKeyError;
	private String noStateError;

	private JPanel outputPanel;
	private ActionListener outputListener;
	private List configLabels;
	private List configButtons;

	private static final long serialVersionUID = 1L;

	public ConfigurationItemInputForm(ConfigurationItem item,
			Configuration configuration, JPanel configPanel, List configLabels,
			List configButtons, ActionListener listener,
			Configuration messages, Configuration errors) {
		this.configLabels = configLabels;
		this.configButtons = configButtons;
		this.output = item;
		this.outputPanel = configPanel;
		this.outputListener = listener;
		this.outputConfiguration = configuration;
		if (messages == null || errors == null)
			this.setCaptions();
		else
			this.setCaptions(messages, errors);
		this.setElements();
	}

	public void setCaptions(Configuration messages, Configuration errors) {

		error = new String(errors.getConfig("error").toString());
		noKeyError = new String(errors.getConfig("no key").toString());
		noStateError = new String(errors.getConfig("no state").toString());

		keyLabel = new JLabel(messages.getConfig("key").toString());
		stateLabel = new JLabel(messages.getConfig("state").toString());
		descriptionLabel = new JLabel(messages.getConfig("description")
				.toString());
		optionsLabel = new JLabel(messages.getConfig("possible options")
				.toString());

		ok = new JButton(messages.getConfig("ok").toString());
		cancel = new JButton(messages.getConfig("cancel").toString());

		this.setTitle(messages.getConfig("add option title").toString());
	}

	public void setSizes() {
		int x = 250, y = 20;
		key.setMaximumSize(new Dimension(x, y));
		key.setPreferredSize(new Dimension(x, y));
		key.setMinimumSize(new Dimension(x, y));
		state.setMaximumSize(new Dimension(x, y));
		state.setPreferredSize(new Dimension(x, y));
		state.setMinimumSize(new Dimension(x, y));
		descriptionScroll.setMaximumSize(new Dimension(x, 3 * y));
		descriptionScroll.setPreferredSize(new Dimension(x, 3 * y));
		descriptionScroll.setMinimumSize(new Dimension(x, 3 * y));
		optionsScroll.setMaximumSize(new Dimension(x, 3 * y));
		optionsScroll.setPreferredSize(new Dimension(x, 3 * y));
		optionsScroll.setMinimumSize(new Dimension(x, 3 * y));
	}

	public void setCaptions() {

		error = new String("error");
		noKeyError = new String("Cannot generate option without a key.");
		noStateError = new String("Cannot generate option without a state.");

		keyLabel = new JLabel("Key");
		stateLabel = new JLabel("State");
		descriptionLabel = new JLabel("Description");
		optionsLabel = new JLabel("Possible options");

		ok = new JButton("OK");
		cancel = new JButton("Cancel");

		this.setTitle("Add option");
	}

	private void setElements() {
		// questionPanel.add(question);
		// questionPanel.setViewportView(question);

		key = new JTextArea();
		key.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		keyLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		keyPanel = new JPanel();
		keyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		keyPanel.setLayout(new BorderLayout());
		keyPanel.add(keyLabel, BorderLayout.WEST);
		keyPanel.add(key, BorderLayout.EAST);

		state = new JTextArea();
		state.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		stateLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		statePanel = new JPanel();
		statePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		statePanel.setLayout(new BorderLayout());
		statePanel.add(stateLabel, BorderLayout.WEST);
		statePanel.add(state, BorderLayout.EAST);

		description = new JTextPane();
		descriptionScroll = new JScrollPane();
		descriptionScroll.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		descriptionScroll.setViewportView(description);
		descriptionLabel
				.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		descriptionPanel = new JPanel();
		descriptionPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10,
				10));
		descriptionPanel.setLayout(new BorderLayout());
		descriptionPanel.add(descriptionLabel, BorderLayout.WEST);
		descriptionPanel.add(descriptionScroll, BorderLayout.EAST);

		options = new JTextPane();
		optionsScroll = new JScrollPane();
		optionsScroll.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		optionsScroll.setViewportView(options);
		optionsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		optionsPanel = new JPanel();
		optionsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		optionsPanel.setLayout(new BorderLayout());
		optionsPanel.add(optionsLabel, BorderLayout.WEST);
		optionsPanel.add(optionsScroll, BorderLayout.EAST);

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				okActionPerformed(evt);
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelActionPerformed(evt);
			}
		});
		buttonsPanel = new JPanel(new FlowLayout());
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonsPanel.add(ok);
		buttonsPanel.add(cancel);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		// mainPanel.
		mainPanel.add(keyPanel);
		mainPanel.add(statePanel);
		mainPanel.add(descriptionPanel);
		mainPanel.add(optionsPanel);
		mainPanel.add(buttonsPanel);

		this.setSizes();
		this.getContentPane().add(mainPanel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
	}

	private void cancelActionPerformed(ActionEvent evt) {
		this.output = null;
		this.dispose();
	}

	private void okActionPerformed(ActionEvent evt) {
		Set opts;
		if (key.getText().trim().length() <= 0) {
			JOptionPane.showMessageDialog(this, noKeyError, error,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (state.getText().trim().length() <= 0) {
			JOptionPane.showMessageDialog(this, noStateError, error,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (this.output == null)
			this.output = new ConfigurationItem();
		this.output.setKey(key.getText());
		this.output.setState(state.getText());
		this.output.setDescription(description.getText().replaceAll("\n",
				"<BR>"));
		if (options.getText().trim().length() == 0) {
			opts = null;
		} else {
			opts = new HashSet();
			String[] array = options.getText().trim().split("\n");
			for (int i = 0; i < array.length; i++)
				if (array[i].trim().length() == 0)
					opts.add(new String(" "));
				else
					opts.add(array[i].trim());
		}
		this.output.setPossible(opts);
		// if(output.getDescription().indexOf("\n")>0)System.out.println("bad monkey!");
		// System.out.println(output);
		this.outputPanel = ConfigurationFrame.generateOptionPanel(output,
				outputPanel.getName(), outputPanel, configLabels,
				configButtons, outputListener);
		this.outputConfiguration.addConfig(output);
		this.dispose();
	}
	/*
	 * public NewConfigurationItemForm(ConfigurationItem
	 * item,UniversalConfiguration messages,UniversalConfiguration errors){
	 * output = item; setCaptions(messages,errors); setElements(); }
	 */

	/*
	 * public static void main(String [] args){ //NewConfigurationItemForm form
	 * = new NewConfigurationItemForm(new ConfigurationItem());
	 * //form.setVisible(true); }
	 */

}
