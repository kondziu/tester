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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

public class LineItemInputForm extends JFrame {

	private static final long serialVersionUID = -2233945322139625772L;
	private JTextArea hint;
	private JTextPane question;
	private JTextPane answer;
	private ButtonGroup types;

	private JScrollPane questionScroll;
	private JScrollPane answerScroll;

	private JLabel hintLabel;
	private JLabel questionLabel;
	private JLabel answerLabel;
	private JLabel typesLabel;

	private JPanel hintPanel;
	private JPanel questionPanel;
	private JPanel answerPanel;
	private JPanel typesPanel;
	private JPanel buttonsPanel;
	private JPanel mainPanel;
	private JPanel radioButtonPanel;

	private JButton ok;
	private JButton cancel;
	private JButton browse;

	private ConfigurationStorage configuration;
	private LineItem current;
	private Main main;

	public LineItemInputForm(Main main, LineItem current,
			ConfigurationStorage configuration) {
		this.configuration = configuration;
		this.current = current;
		this.main = main;
		this.setCaptions();
		this.setElements();
		this.showCurrent();

	}

	private void showCurrent() {
		if (current != null) {
			if (current.question != null)
				question.setText(current.question);
			if (current.hint != null)
				hint.setText(current.hint);
			String answers = new String("");
			if (current.answerList != null)
				for (Iterator i = current.answerList.iterator(); i.hasNext();) {
					answers += (String) i.next();
					if (i.hasNext())
						answers += "\n";
				}
			answer.setText(answers);
		}
	}

	public void setSizes() {
		int x = 250, y = 20;
		hint.setMaximumSize(new Dimension(x, y));
		hint.setPreferredSize(new Dimension(x, y));
		hint.setMinimumSize(new Dimension(x, y));
		questionScroll.setMaximumSize(new Dimension(x, 3 * y));
		questionScroll.setPreferredSize(new Dimension(x, 3 * y));
		questionScroll.setMinimumSize(new Dimension(x, 3 * y));
		answerScroll.setMaximumSize(new Dimension(x, 3 * y));
		answerScroll.setPreferredSize(new Dimension(x, 3 * y));
		answerScroll.setMinimumSize(new Dimension(x, 3 * y));
		radioButtonPanel.setMaximumSize(new Dimension(x, 2 * y));
		radioButtonPanel.setPreferredSize(new Dimension(x, 2 * y));
		radioButtonPanel.setMinimumSize(new Dimension(x, 2 * y));
	}

	public void setCaptions() {

		hintLabel = new JLabel(configuration.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.HINT));
		questionLabel = new JLabel(configuration.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.QUESTION));
		answerLabel = new JLabel(configuration.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.ANSWERS));
		typesLabel = new JLabel(configuration.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.TYPE));
		ok = new JButton(configuration.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.OK));
		cancel = new JButton(configuration.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.CANCEL));
		browse = new JButton(configuration.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.BROWSE));
		this.setTitle(configuration.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.ADD));
	}

	private void setElements() {

		hint = new JTextArea();
		hint.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		hintLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		hintPanel = new JPanel();
		hintPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		hintPanel.setLayout(new BorderLayout());
		hintPanel.add(hintLabel, BorderLayout.WEST);
		hintPanel.add(hint, BorderLayout.EAST);

		question = new JTextPane();
		questionScroll = new JScrollPane();
		questionScroll.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		questionScroll.setViewportView(question);
		questionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		questionPanel = new JPanel();
		questionPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		questionPanel.setLayout(new BorderLayout());
		questionPanel.add(questionLabel, BorderLayout.WEST);
		questionPanel.add(questionScroll, BorderLayout.EAST);

		answer = new JTextPane();
		answerScroll = new JScrollPane();
		answerScroll.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		answerScroll.setViewportView(answer);
		answerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		answerPanel = new JPanel();
		answerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		answerPanel.setLayout(new BorderLayout());
		answerPanel.add(answerLabel, BorderLayout.WEST);
		answerPanel.add(answerScroll, BorderLayout.EAST);

		String type = null;
		if (current != null)
			type = current.type;

		types = new ButtonGroup();

		radioButtonPanel = createRadioPanel(type,
				configuration.regexConfiguration.getDetailedConfig());
		radioButtonPanel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		typesLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		typesPanel = new JPanel();
		typesPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		typesPanel.setLayout(new BorderLayout());
		typesPanel.add(typesLabel, BorderLayout.WEST);
		typesPanel.add(radioButtonPanel, BorderLayout.EAST);

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
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				browseActionPerformed(evt);
			}
		});

		buttonsPanel = new JPanel(new FlowLayout());
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonsPanel.add(ok);
		buttonsPanel.add(browse);
		buttonsPanel.add(cancel);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(questionPanel);
		mainPanel.add(hintPanel);
		mainPanel.add(answerPanel);
		mainPanel.add(typesPanel);
		mainPanel.add(buttonsPanel);

		this.setSizes();
		this.getContentPane().add(mainPanel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
	}

	private void okActionPerformed(ActionEvent evt) {

		String q = question.getText().replaceAll("[\n\r]", " ").replaceAll(
				"  ", " ").trim();

		if (q == null || q.length() == 0) {
			JOptionPane.showMessageDialog(main,
					configuration.errorConfiguration
							.getConfig(ConfigurationDefaultErrors.NO_QUESTION),
					configuration.errorConfiguration
							.getConfig(ConfigurationDefaultErrors.ERROR),
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String[] arr = answer.getText().trim().split("\n");
		List a = new LinkedList();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].replaceAll("\n", " ").trim();
			if (arr[i].length() > 0)
				a.add(arr[i]);
		}
		if (a.size() == 0) {
			JOptionPane
					.showMessageDialog(
							main,
							configuration.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.NO_VALID_ANSWERS),
							configuration.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR),
							JOptionPane.ERROR_MESSAGE);
			return;
		}

		String h = hint.getText().trim();

		String t = null;
		Component[] radios = radioButtonPanel.getComponents();
		for (int i = 0; i < radios.length; i++) {
			JRadioButton radio = (JRadioButton) radios[i];
			if (radio.isSelected())
				t = radio.getText();
		}
		if (t != null && t != "text" && t != "") {
			File file = new File(q);
			if (!file.exists()) {
				JOptionPane
						.showMessageDialog(
								main,
								configuration.errorConfiguration
										.getConfig(ConfigurationDefaultErrors.ERROR_READING_FILE)
										+ "\n\"" + q + "\"",
								configuration.errorConfiguration
										.getConfig(ConfigurationDefaultErrors.ERROR),
								JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		// t="text";
		if (current == null)
			current = new LineItem(configuration.regexConfiguration);
		current.question = q.trim();
		current.hint = h.trim();
		current.type = t.trim();
		current.answerList = a;
		main.refreshQuestion();
		main.askToSaveInput = true;
		this.dispose();
	}

	private void cancelActionPerformed(ActionEvent evt) {
		this.dispose();
	}

	private void browseActionPerformed(ActionEvent evt) {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser
				.setCurrentDirectory(new File(
						this.configuration.pathConfiguration
								.getConfig(ConfigurationDefaultPath.FILE_CHOOSER_DEFAULT_PATH)));
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			question.setText(file.toString());
		}
	}

	/**
	 * Creates a panel with radio buttons for each possible type
	 * 
	 * @param currentType
	 *            current files type, or null if not applicable(use constants)
	 * @param allTypes
	 *            map of Strings of all regular expressions.
	 * @return JPanel panel of radio buttons, for each type
	 */
	private JPanel createRadioPanel(String currentType, Map regex) {
		JPanel panel = new JPanel();
		{
			String type = "text";
			String symbol = "";
			JRadioButton radio = new JRadioButton();
			radio.setText(type);
			radio.setName(symbol);
			if (currentType != null)
				if (currentType.compareToIgnoreCase("text") == 0)
					radio.setSelected(true);
			panel.add(radio);
			types.add(radio);
		}

		for (Iterator i = regex.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			if (key.startsWith("type:")) {
				boolean select = false;
				String type = key.replaceFirst("type:", "").trim();
				// String symbol =
				// ((ConfigurationItem)regex.get(key)).getState().toString();
				JRadioButton radio = new JRadioButton();
				radio.setText(type);
				radio.setName(key);
				if (currentType != null)
					select = currentType.compareToIgnoreCase(type) == 0;
				radio.setSelected(select);
				panel.add(radio);
				types.add(radio);
			}
		}
		return panel;
	}

}
