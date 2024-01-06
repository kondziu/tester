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
 
/*
 * VisualInterface.java
 *
 * Created on 7 maj 2006, 13:25
 */

package pl.siek.tester;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class Main extends JFrame {
	/**
	 * Main interface for the Tester application. To be renamed later.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	private static final long serialVersionUID = 9218503094273330361L;
	public static final String VERSION = "2.5.1";
	public static final String DATE = "3 Nov 2006 / 13 Aug 2009 / 18 Jan 2017";
	private static final String PATH_FILE_PATH = "files" + File.separator
			+ "paths.txt";

	/**
	 * Runs the program.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param none
	 *            whatsoever
	 */
	public static void main(String args[]) {
		System.out.println(new File(".").getAbsolutePath());
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Main().setVisible(true);
			}
		});
	}

	public boolean askToSaveInput;
	private JMenuItem about;
	private JTextField answer;
	private JLabel bottomLabel;
	private JPanel bottomPanel;
	private JPanel buttonPanel;
	private LineComparator comparison;
	private JMenuItem configuration;
	private ConfigurationStorage configurationAndSettings;
	private LineItem current;
	private Integer done;
	private List doneList;
	private JMenuItem edit;
	private JMenuItem erase;
	private JMenu file;
	private boolean firstAttempt;
	private boolean forcedRepetition;
	private ImageIcon goodAnswer;
	private JLabel grade;
	private JLabel gradeLabel;
	private JPanel gradePanel;
	private JMenuItem handbook;
	private JMenu help;
	private JMenuItem helpMe;
	private JButton helpMeButton;
	private JCheckBoxMenuItem ignoreCase;
	private List inputList;
	private JMenuItem internet;
	private JMenuItem language;
	private JPanel mainPanel;
	private JMenuItem manage;
	private JMenuItem dictionary;
	private JMenuBar menu;
	private JButton ok;
	private JMenuItem open;
	private Double percentage;
	private PictureFrame picture;
	private JLabel points;
	private ImageIcon poorAnswer;
	private JMenu preferences;
	private JTextPane question;
	private JScrollPane questionPanel;
	private JMenuItem quit;
	private List redoList;
	private JMenuItem reload;
	private JMenuItem remove;
	private JCheckBoxMenuItem repetitionType;
	private boolean resizable;
	private JCheckBoxMenuItem reverse;
	private boolean reverseEnabled;
	private JSeparator separator1;
	private JSeparator separator2;
	private JSeparator separator3;
	private JLabel separatorLabel1;
	private JLabel separatorLabel2;
	private JCheckBoxMenuItem shuffle;
	private boolean shuffleEnabled;
	private Integer soFar;
	private JMenuItem start;
	private JButton startTest;
	private JCheckBoxMenuItem statistics;
	private JMenuItem stop;
	private JMenu test;
	private boolean testCanBeStopped;
	private boolean testCanBeEdited;
	private boolean testMode;
	private Integer toGo;
	private JPanel topPanel;
	private JCheckBoxMenuItem training;
	private JCheckBoxMenuItem useRules;
	private boolean studentConfigures;
	private Clock clock;
	private JPanel statusPanel;
	private JLabel statusLabel;
	private JLabel clockLabel;
	private boolean showPercentage;

	private Log log;

	/** Creates new form VisualInterface */
	public Main() {
		configurationAndSettings = new ConfigurationStorage(PATH_FILE_PATH);
		comparison = new LineComparator(configurationAndSettings);
		initComponents();
		statusLabel
				.setPreferredSize(new Dimension(this.getPreferredSize().width
						- 15 - clockLabel.getWidth(), 20));
		log = new Log(this, configurationAndSettings);
		log.open(); // TODO ERRORS
		log.send("Starting program."); // TODO ERRORS
	}

	/**
	 * Sets up all visible components on the content pane.
	 * 
	 * @author K. Siek
	 * @version 2.002
	 */
	private void addComponents() {
		question.setEditable(false);

		gradePanel.setMaximumSize(new java.awt.Dimension(10, 10));
		gradeLabel.setFont(new java.awt.Font("Arial", 1, 42));
		gradeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		points.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		grade.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		// points.setAlignmentY(SwingConstants.CENTER);

		gradePanel.setLayout(new BoxLayout(gradePanel, BoxLayout.Y_AXIS));
		topPanel.setLayout(new FlowLayout());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		bottomPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new BorderLayout());

		statusPanel.setLayout(new BorderLayout());
		clockLabel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		statusLabel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));

		gradePanel.add(grade);
		if (showPercentage) {
			gradePanel.add(gradeLabel);
		}
		gradePanel.add(points);

		questionPanel.add(question);
		questionPanel.setViewportView(question);

		topPanel.add(gradePanel);
		topPanel.add(questionPanel);

		buttonPanel.add(startTest, BorderLayout.WEST);
		buttonPanel.add(helpMeButton, BorderLayout.EAST);

		bottomPanel.add(buttonPanel);
		bottomPanel.add(separatorLabel1);
		bottomPanel.add(ok);

		mainPanel.add(topPanel);
		mainPanel.add(answer);
		mainPanel.add(separatorLabel2);
		mainPanel.add(bottomPanel);

		// SPACE AT THE BOTTOM

		mainPanel.add(bottomLabel);

		if (configurationAndSettings.optionConfiguration.getConfig(
				ConfigurationDefaultOptions.SHOW_STATUS_AND_CLOCK)
				.compareToIgnoreCase("on") == 0) {
			statusPanel.add(clockLabel, BorderLayout.WEST);
			statusPanel.add(statusLabel, BorderLayout.EAST);
			mainPanel.add(statusPanel);
		}
	}

	/**
	 * Runs VisualConfiguration Class.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param ActionEvent
	 *            evt
	 */
	private void configurationTestActionPerformed(ActionEvent evt) {
		new ConfigurationFrame(configurationAndSettings);
	}

	/**
	 * Runs component constructors.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	public void constructComponents() {
		ok = new JButton();
		answer = new JTextField();
		startTest = new JButton();
		helpMeButton = new JButton();
		gradePanel = new JPanel();
		gradeLabel = new JLabel();
		grade = new JLabel();
		questionPanel = new JScrollPane();
		question = new JTextPane();
		points = new JLabel();
		menu = new JMenuBar();
		file = new JMenu();
		open = new JMenuItem();
		manage = new JMenuItem();
		internet = new JMenuItem();
		quit = new JMenuItem();
		reload = new JMenuItem();
		test = new JMenu();
		helpMe = new JMenuItem();
		separator1 = new JSeparator();
		separator3 = new JSeparator();
		start = new JMenuItem();
		training = new JCheckBoxMenuItem();
		shuffle = new JCheckBoxMenuItem();
		reverse = new JCheckBoxMenuItem();
		erase = new JMenuItem();
		separator2 = new JSeparator();
		edit = new JMenuItem();
		remove = new JMenuItem();
		stop = new JMenuItem();
		preferences = new JMenu();
		language = new JMenuItem();
		statistics = new JCheckBoxMenuItem();
		configuration = new JMenuItem();
		dictionary = new JMenuItem();
		help = new JMenu();
		handbook = new JMenuItem();
		about = new JMenuItem();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		mainPanel = new JPanel();
		buttonPanel = new JPanel();
		separatorLabel1 = new JLabel();
		separatorLabel2 = new JLabel();
		bottomLabel = new JLabel();
		statusLabel = new JLabel();
		clockLabel = new JLabel();
		statusPanel = new JPanel();
		useRules = new JCheckBoxMenuItem();
		ignoreCase = new JCheckBoxMenuItem();
		repetitionType = new JCheckBoxMenuItem();
		picture = null;
		clock = new Clock(clockLabel);

		question.setContentType("text/html");
	}

	/**
	 * Saves configuration changes and EXITS! Whatch out it uses System.exit(0);
	 * 
	 * @author K. Siek
	 * @version 2.003
	 */
	public void dispose() {
		if (configurationAndSettings.optionConfiguration.getConfig(
				ConfigurationDefaultOptions.SAVE_CONFIG_ON_EXIT)
				.compareToIgnoreCase("on") == 0)
			try {
				this.configurationAndSettings.saveToFile();
			} catch (Exception e) {
				e.printStackTrace();
				if (this.configurationAndSettings.optionConfiguration
						.getConfig(
								ConfigurationDefaultOptions.INDICATE_ERROR_ON_EXIT)
						.compareToIgnoreCase("on") == 0)
					JOptionPane
							.showConfirmDialog(
									this,
									this.configurationAndSettings.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.ERROR_WRITING_FILE),
									this.configurationAndSettings.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.WARNING),
									JOptionPane.DEFAULT_OPTION);
			}
		System.exit(0);
	}

	/**
	 * Shows a dialogue box with the correct answer.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param ActionEvent
	 *            evt
	 */
	protected void helpMeActionPerformed(ActionEvent evt) {
		String answers = "";

		if (testMode) {
			incrementSoFarAndToGoCounters();
			loadNextQuestionAndSetButtons();
			firstAttempt = true;
		} else if (firstAttempt) {
			if (forcedRepetition)
				inputList.add(current);
			else
				redoList.add(current);
			firstAttempt = false;
			help.setEnabled(true);
			helpMeButton.setEnabled(true);
			incrementSoFarAndToGoCounters();
		}

		for (Iterator i = current.answerList.iterator(); i.hasNext();) {
			String answer = (String) i.next();
			answers += answer;
			if (i.hasNext()) {
				// String or =
				// (String)configurationAndSettings.messageConfiguration.getConfig("or");
				// if(or==null)or="";
				answers += "\n\n";
			}
		}
		JOptionPane.showMessageDialog(this, answers,
				configurationAndSettings.messageConfiguration
						.getConfig(ConfigurationDefaultMessages.HELP),
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Refreshes the counters, increments.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	private void incrementSoFarAndToGoCounters() {
		soFar = new Integer(soFar.intValue() + 1);
		toGo = new Integer(inputList.size());
		// firstAttempt=true;
		refreshStats();
	}

	/**
	 * Runs methods initiating components.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	private void initComponents() {

		this.constructComponents();
		this.setComponentTexts(true);
		this.setComponentActions();
		this.setComponentMnemonics();
		this.setComponentSizes();
		this.setVariables();
		this.setEnabledComponents();
		this.setMenu();
		// points.setHorizontalAlignment(SwingConstants.CENTER);

		this.addComponents();

		this
				.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(resizable);
		this.getContentPane().add(mainPanel);
		this.pack();
		// handbook.setIcon(new
		// ImageIcon("C:\\Documents and Settings\\Joe\\Pulpit\\book07.gif"));

	}

	/**
	 * Checks whether the answer is correct.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 *            checked element
	 * @param List
	 *            containing Strings which are bounds of correctness for element
	 * @return boolean element is correct
	 */
	private boolean isCorrect(String text, List answerList) {
		return comparison.compare(answerList, text);
	}

	/**
	 * Sets new language and reloads.
	 * 
	 * @author K. Siek
	 * @version 2.1
	 */
	protected void languageActionPerformed(ActionEvent evt) {
		new LanguageVersionFrame(this, configurationAndSettings);
	}

	/**
	 * Sets buttons and calls a method to load the next question.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	private void loadNextQuestionAndSetButtons() {
		if (!inputList.isEmpty()) {
			// firstAttempt=true;
			// saveChangesInTest();
			boolean setEnabled = configurationAndSettings.optionConfiguration
					.getConfig(ConfigurationDefaultOptions.ALLOW_EARLY_HELP)
					.compareToIgnoreCase("on") == 0;
			helpMe.setEnabled(setEnabled);
			helpMeButton.setEnabled(setEnabled);
			ok.setEnabled(true);
			showNextQuestion();
		} else {
			setButtonsAfterStop();
			JOptionPane
					.showMessageDialog(
							this,
							configurationAndSettings.messageConfiguration
									.getConfig(ConfigurationDefaultMessages.END_TEST),
							configurationAndSettings.messageConfiguration
									.getConfig(ConfigurationDefaultMessages.INFORMATION),
							JOptionPane.INFORMATION_MESSAGE);
			if (askToSaveInput)
				if (JOptionPane.OK_OPTION == JOptionPane
						.showConfirmDialog(
								this,
								configurationAndSettings.messageConfiguration
										.getConfig(ConfigurationDefaultMessages.SAVE_CHANGES),
								configurationAndSettings.messageConfiguration
										.getConfig(ConfigurationDefaultMessages.CONFIRM),
								JOptionPane.INFORMATION_MESSAGE,
								JOptionPane.OK_CANCEL_OPTION)) {
					saveChangesInTest();
				}
			potentiallySaveRepetition();
			log.send("Stopping test (progress: " + done + "/" + soFar + "/" + toGo + ", grade: " + percentage + ").");		}
	}

	private void saveChangesInTest() {
		Output file = new OutputFile(configurationAndSettings.pathConfiguration
				.getConfig(ConfigurationDefaultPath.INPUT));
		try {
			file.generate(doneList);
		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(
							this,
							configurationAndSettings.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR_WRITING_FILE),
							configurationAndSettings.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR),
							JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void manageActionPerformed(ActionEvent evt) {
		DynamicDirectoryTreeFrame.createAndShowGUI(configurationAndSettings);
	}

	/**
	 * Evaluates the answer.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param ActionEvent
	 *            evt
	 */
	private void okActionPerformed(ActionEvent evt) {
		String answerText = answer.getText();
		String magicWord = configurationAndSettings.optionConfiguration.getConfig("magic word");
		if (answerText.equals(magicWord)) {
			this.toGo = this.toGo - 1;
			loadNextQuestionAndSetButtons();
			firstAttempt = true;
		} else if (isCorrect(answerText, current.answerList)) {
			JOptionPane
					.showMessageDialog(
							this,
							configurationAndSettings.messageConfiguration
									.getConfig(ConfigurationDefaultMessages.GOOD_WORK),
							configurationAndSettings.messageConfiguration
									.getConfig(ConfigurationDefaultMessages.GOOD_WORK_TITLE),
							JOptionPane.OK_OPTION, goodAnswer);
			if (!doneList.contains(current))
				doneList.add(current);
			if (firstAttempt) {
				done = new Integer(done.intValue() + 1);
				incrementSoFarAndToGoCounters();
			}
			loadNextQuestionAndSetButtons();
			firstAttempt = true;
		} else {
			JOptionPane
					.showMessageDialog(
							this,
							configurationAndSettings.messageConfiguration
									.getConfig(ConfigurationDefaultMessages.POOR_WORK),
							configurationAndSettings.messageConfiguration
									.getConfig(ConfigurationDefaultMessages.POOR_WORK_TITLE),
							JOptionPane.OK_OPTION, poorAnswer);
			if (testMode) {
				incrementSoFarAndToGoCounters();
				loadNextQuestionAndSetButtons();
				firstAttempt = true;
			} else if (firstAttempt) {
				if (forcedRepetition)
					inputList.add(current);
				else
					redoList.add(current);
				firstAttempt = false;
				help.setEnabled(true);
				helpMeButton.setEnabled(true);
				incrementSoFarAndToGoCounters();
			}
		}
	}

	/**
	 * Opens input manager
	 * 
	 * @author K. Siek
	 * @version 2.003
	 */
	private boolean openActionPerformed(ActionEvent evt) {
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser
				.setCurrentDirectory(new File(
						this.configurationAndSettings.pathConfiguration
								.getConfig(ConfigurationDefaultPath.FILE_CHOOSER_DEFAULT_PATH)));
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			this.configurationAndSettings.pathConfiguration.getDetailedConfig(
					ConfigurationDefaultPath.INPUT).setState(file.toString());
			log.send("Opening test " + file.getAbsolutePath() + ".");
			return true;
		}
		return false;
	}

	private void quitActionPerformed() {
		//log.send("Exiting program.");
		log.send("Exiting program (progress: " + done + "/" + soFar + "/" + toGo + ", grade: " + percentage + ").");
		this.dispose();
	}

	/**
	 * Refreshes counters
	 * 
	 * @author K. Siek
	 * @version 2.2
	 */
	private void refreshStats() {
		if (showPercentage) {
			percentage = new Double(((double) done.intValue() / (double) soFar
					.intValue()) * 100);
			if (percentage.isNaN() || percentage.isInfinite())
				percentage = new Double(0D);
			String perc = (new Integer(percentage.intValue())).toString();
			gradeLabel.setText(perc + "%");
		}
		points.setText(done.toString() + " / " + soFar.toString() + " / -"
				+ toGo.toString());
	}

	private void reloadActionPerformed(ActionEvent evt) {
		setComponentTexts(true);
		setVariables();
		setEnabledComponents();

	}

	public void reloadLanguage(String messagePath, String errorPath) {
		String currentFile = null;
		if (messagePath != null)
			try {
				configurationAndSettings.messageConfiguration
						.setPath(messagePath);
				currentFile = messagePath;
				configurationAndSettings.messageConfiguration.loadFile();
				configurationAndSettings.messageConfiguration = new Configuration(
						ConfigurationDefault
								.fillHoles(
										configurationAndSettings.messageConfiguration
												.getDetailedConfig(),
										ConfigurationDefaultMessages
												.getDefaultConfig()));
			} catch (IOException e) {
				JOptionPane
						.showMessageDialog(
								this,
								configurationAndSettings.errorConfiguration
										.getConfig(ConfigurationDefaultErrors.ERROR_READING_FILE)
										+ " " + currentFile,
								configurationAndSettings.errorConfiguration
										.getConfig(ConfigurationDefaultErrors.ERROR),
								JOptionPane.ERROR_MESSAGE);
			}
		else
			this.configurationAndSettings.messageConfiguration = new Configuration(
					ConfigurationDefaultMessages.getDefaultConfig());

		if (messagePath != null)
			try {
				configurationAndSettings.errorConfiguration.setPath(errorPath);
				currentFile = errorPath;
				configurationAndSettings.errorConfiguration.loadFile();
				configurationAndSettings.errorConfiguration = new Configuration(
						ConfigurationDefault.fillHoles(
								configurationAndSettings.errorConfiguration
										.getDetailedConfig(),
								ConfigurationDefaultErrors.getDefaultConfig()));
			} catch (IOException e) {
				JOptionPane
						.showMessageDialog(
								this,
								configurationAndSettings.errorConfiguration
										.getConfig(ConfigurationDefaultErrors.ERROR_READING_FILE)
										+ " " + currentFile,
								configurationAndSettings.errorConfiguration
										.getConfig(ConfigurationDefaultErrors.ERROR),
								JOptionPane.ERROR_MESSAGE);
			}
		else
			this.configurationAndSettings.errorConfiguration = new Configuration(
					ConfigurationDefaultErrors.getDefaultConfig());

		setComponentTexts(false);
	}

	/**
	 * Sets actions to components.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	private void setComponentActions() {
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				showAboutActionPerformed(evt);
			}
		});
		startTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				startTestActionPerformed(evt);
			}
		});
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				startTestActionPerformed(evt);
			}
		});
		configuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				configurationTestActionPerformed(evt);
			}
		});
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				okActionPerformed(evt);
			}
		});
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				helpMeActionPerformed(evt);
			}
		});
		helpMeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				helpMeActionPerformed(evt);
			}
		});
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				openActionPerformed(evt);
			}
		});
		manage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				manageActionPerformed(evt);
			}
		});
		internet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setForDownloadActionPerformed(evt);
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				quitActionPerformed();
			}
		});
		language.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				languageActionPerformed(evt);
			}
		});
		training.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchTrainingActionPerformed(evt);
			}
		});
		statistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchStatisticsActionPerformed(evt);
			}
		});
		repetitionType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchRepetitionActionPerformed(evt);
			}
		});
		reload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				reloadActionPerformed(evt);
			}
		});
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				stopActionPerformed(evt);
			}
		});

		reverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchActionPerformed(evt);
			}
		});
		shuffle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchActionPerformed(evt);
			}
		});
		useRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchActionPerformed(evt);
			}
		});
		ignoreCase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchActionPerformed(evt);
			}
		});

		reverse.setActionCommand(ConfigurationDefaultOptions.REVERSE);
		shuffle.setActionCommand(ConfigurationDefaultOptions.SHUFFLE);
		useRules.setActionCommand(ConfigurationDefaultOptions.USE_RULES);
		ignoreCase.setActionCommand(ConfigurationDefaultOptions.IGNORE_CASE);

		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				editItemActionPerformed(evt);
			}
		});
		erase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				eraseActionPerformed(evt);
			}
		});
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				removeItemActionPerformed(evt);
			}
		});

		handbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				handbookActionPerformed(evt);
			}
		});
		dictionary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dictionaryActionPerformed(evt);
			}
		});

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				quitActionPerformed();
			}
		});
	}

	private void runMedia(String mediaPath) {
		try {
			Runtime.getRuntime().exec(
					configurationAndSettings.pathConfiguration
							.getConfig(ConfigurationDefaultPath.MEDIA_PLAYER)
							+ " " + mediaPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block IF DOESN'T WORK, LET CHOOSE MEDIA
			// PLAYER
			System.out.println("vid not started");
			// e.printStackTrace();
		}
	}

	private void dictionaryActionPerformed(ActionEvent evt) {
		new DictionaryFrame(configurationAndSettings);
	}

	private void handbookActionPerformed(ActionEvent evt) {
		runMedia(configurationAndSettings.pathConfiguration
				.getConfig(ConfigurationDefaultPath.HANDBOOK));
	}

	private void eraseActionPerformed(ActionEvent evt) {
		File file = new File(configurationAndSettings.pathConfiguration
				.getConfig(ConfigurationDefaultPath.REPETITION));
		file.delete();
	}

	/**
	 * asks whether certain and removes question from list.
	 * 
	 * @author K. Siek
	 * @version 2.1
	 */
	private void removeItemActionPerformed(ActionEvent evt) {
		int confirm = JOptionPane
				.showConfirmDialog(
						this,
						configurationAndSettings.messageConfiguration
								.getConfig(ConfigurationDefaultMessages.DO_YOU_WANT_TO_REMOVE)
								+ ": \n" + current.toString(),
						configurationAndSettings.messageConfiguration
								.getConfig(ConfigurationDefaultMessages.REMOVE),
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);
		if (confirm == JOptionPane.OK_OPTION) {
			this.askToSaveInput = true;
			this.loadNextQuestionAndSetButtons();
		}
	}

	/**
	 * shows form to edit, sets marker
	 * 
	 * @author K. Siek
	 * @version 2.1
	 */
	private void editItemActionPerformed(ActionEvent evt) {
		new LineItemInputForm(this, current, configurationAndSettings);
	}

	/**
	 * Sets key shortcuts to components
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	private void setComponentMnemonics() {

		startTest.setMnemonic(KeyEvent.VK_S);
		helpMeButton.setMnemonic(KeyEvent.VK_F1);
		ok.setMnemonic(KeyEvent.VK_ENTER);

		file.setMnemonic(KeyEvent.VK_F);
		open.setMnemonic(KeyEvent.VK_O);
		internet.setMnemonic(KeyEvent.VK_N);
		manage.setMnemonic(KeyEvent.VK_M);
		quit.setMnemonic(KeyEvent.VK_Q);

		test.setMnemonic(KeyEvent.VK_T);
		start.setMnemonic(KeyEvent.VK_S);
		stop.setMnemonic(KeyEvent.VK_T);
		helpMe.setMnemonic(KeyEvent.VK_H);
		training.setMnemonic(KeyEvent.VK_M);
		shuffle.setMnemonic(KeyEvent.VK_F);
		reverse.setMnemonic(KeyEvent.VK_R);
		useRules.setMnemonic(KeyEvent.VK_U);
		ignoreCase.setMnemonic(KeyEvent.VK_C);
		repetitionType.setMnemonic(KeyEvent.VK_F);

		preferences.setMnemonic(KeyEvent.VK_P);
		language.setMnemonic(KeyEvent.VK_V);
		statistics.setMnemonic(KeyEvent.VK_S);
		configuration.setMnemonic(KeyEvent.VK_C);
		reload.setMnemonic(KeyEvent.VK_R);

		help.setMnemonic(KeyEvent.VK_H);
		dictionary.setMnemonic(KeyEvent.VK_D);
		handbook.setMnemonic(KeyEvent.VK_H);
		about.setMnemonic(KeyEvent.VK_A);

		// OK REACTS TO ENTER IN ANSWER FIELD
		answer.addKeyListener(new java.awt.event.KeyListener() {
			public void keyPressed(KeyEvent e) {
				// Intentionally left empty.
			}

			public void keyReleased(KeyEvent e) {
				// Intentionally left empty.
			}

			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n' && ok.isEnabled())
					okActionPerformed(null);
			}
		});
	}

	private void setComponentSizes() {

		grade.setMaximumSize(new Dimension(110, 40));
		grade.setPreferredSize(new Dimension(110, 40));
		grade.setMinimumSize(new Dimension(110, 40));

		gradeLabel.setMaximumSize(new Dimension(110, 50));
		gradeLabel.setPreferredSize(new Dimension(110, 50));
		gradeLabel.setMinimumSize(new Dimension(110, 50));

		points.setMaximumSize(new Dimension(110, 40));
		points.setPreferredSize(new Dimension(110, 40));
		points.setMinimumSize(new Dimension(110, 40));

		String questionOption = this.configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.QUESTION_WINDOW_WIDTH);
		int questionWidth = 250, questionHeight = 100;
		try {
			int temp = Integer.parseInt(questionOption);
			questionWidth = temp;
		} catch (Exception e) {
			// Intentionally left empty.
		}
		questionOption = this.configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.QUESTION_WINDOW_HEIGHT);
		try {
			int temp = Integer.parseInt(questionOption);
			questionHeight = temp;
		} catch (Exception e) {
			// Intentionally left empty.
		}

		question.setMaximumSize(new Dimension(questionWidth, questionHeight));
		question.setPreferredSize(new Dimension(questionWidth, questionHeight));
		question.setMinimumSize(new Dimension(questionWidth, questionHeight));

		questionPanel.setMaximumSize(new Dimension(questionWidth,
				questionHeight + 20));
		questionPanel.setPreferredSize(new Dimension(questionWidth,
				questionHeight + 20));
		questionPanel.setMinimumSize(new Dimension(questionWidth,
				questionHeight + 20));

		Dimension uniformButtonDimension;
		if (startTest.getPreferredSize().width > helpMeButton
				.getPreferredSize().width)
			uniformButtonDimension = startTest.getPreferredSize();
		else
			uniformButtonDimension = helpMeButton.getPreferredSize();

		// THIS WAS REVERSED FOR DAD TO PUT HIS LONG CAPTIONS ON
		startTest.setMaximumSize(uniformButtonDimension);
		startTest.setPreferredSize(uniformButtonDimension);
		startTest.setMinimumSize(uniformButtonDimension);

		helpMeButton.setMaximumSize(uniformButtonDimension);
		helpMeButton.setPreferredSize(uniformButtonDimension);
		helpMeButton.setMinimumSize(uniformButtonDimension);

		String answerOption = this.configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.ANSWER_WINDOW_WIDTH);
		int answerWidth = 390;
		try {
			int temp = Integer.parseInt(answerOption);
			answerWidth = temp;
		} catch (Exception e) {
			// Intentionally left empty.
		}

		answer.setMaximumSize(new Dimension(answerWidth, startTest
				.getPreferredSize().height));
		answer.setPreferredSize(new Dimension(answerWidth, startTest
				.getPreferredSize().height));
		answer.setMinimumSize(new Dimension(answerWidth, startTest
				.getPreferredSize().height));

		ok.setMaximumSize(new Dimension(startTest.getPreferredSize()));
		ok.setPreferredSize(new Dimension(startTest.getPreferredSize()));
		ok.setMinimumSize(new Dimension(startTest.getPreferredSize()));

		separatorLabel1.setMaximumSize(new Dimension(100, 10));
		separatorLabel1.setPreferredSize(new Dimension(100, 10));
		separatorLabel1.setMinimumSize(new Dimension(100, 10));

		separatorLabel2.setMaximumSize(new Dimension(100, 10));
		separatorLabel2.setPreferredSize(new Dimension(100, 10));
		separatorLabel2.setMinimumSize(new Dimension(100, 10));

		bottomLabel.setMaximumSize(new Dimension(100, 10));
		bottomLabel.setPreferredSize(new Dimension(100, 10));
		bottomLabel.setMinimumSize(new Dimension(100, 10));

		// System.out.println(statusPanel.getPreferredSize().width);

		clockLabel.setPreferredSize(new Dimension(
				startTest.getPreferredSize().width - 20, 20));
		// statusLabel.setPreferredSize(new
		// Dimension(statusPanel.getPreferredSize().width-20-clockLabel.getWidth(),20));

		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}

	/**
	 * Sets captions to all the components
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	public void setComponentTexts(boolean initialRun) {

		setTitle("Tester Program v" + Main.VERSION);

		if (initialRun) {
			answer.setText("");
			gradeLabel.setText("");
			points.setText("? / ? /- ?");
			question.setText("");
		}

		ok.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.OK));
		startTest.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.START_TEST));
		helpMeButton.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.HELP_ME));
		grade.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.SCORE));
		file.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.FILE));
		open.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.OPEN));
		quit.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.QUIT));
		test.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.TEST));
		helpMe.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.HELP_ME));
		start.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.START_TEST));
		shuffle.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.SHUFFLE));
		training.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.TRAINING_MODE));
		reverse.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.REVERSE));
		erase.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.ERASE_REPETITION));
		edit.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.EDIT_QUESTION));
		preferences.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.PREFERENCES));
		language.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.LANGUAGE_VERSION));
		configuration.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.CONFIGURATION));
		statistics.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.STATISTICS));
		handbook.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.HANDBOOK));
		about.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.ABOUT));
		help.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.HELP));
		remove.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.REMOVE));
		manage.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.MANAGE));
		internet.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.INTERNET));
		reload.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.RELOAD));
		useRules.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.USE_RULES));
		ignoreCase.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.IGNORE_CASE));
		repetitionType.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.FORCED_REPETITION));
		stop.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.STOP));
		dictionary.setText(configurationAndSettings.messageConfiguration
				.getConfig(ConfigurationDefaultMessages.DICTIONARY));
		goodAnswer = new ImageIcon(configurationAndSettings.pathConfiguration
				.getConfig(ConfigurationDefaultPath.GOOD_ICON));
		poorAnswer = new ImageIcon(configurationAndSettings.pathConfiguration
				.getConfig(ConfigurationDefaultPath.POOR_ICON));

		ConfigurationFileChooser
				.configure(this.configurationAndSettings.messageConfiguration);
	}

	/**
	 * Sets enalbed and disabled components to their propoer states at the
	 * beginning of usage.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	public void setEnabledComponents() {
		setButtonsAfterStop();

		configuration.setEnabled(studentConfigures);

		statistics.setEnabled(studentConfigures);

		useRules.setEnabled(studentConfigures);
		ignoreCase.setEnabled(studentConfigures);
		configuration.setEnabled(studentConfigures);
		repetitionType.setEnabled(true);
		training.setEnabled(true);

		statistics.setSelected(configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.SAVE_LOG)
				.compareToIgnoreCase("on") == 0);
		training.setSelected(configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.MODE)
				.compareToIgnoreCase("standard") == 0);
		shuffle.setSelected(configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.SHUFFLE)
				.compareToIgnoreCase("on") == 0);
		reverse.setSelected(configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.REVERSE)
				.compareToIgnoreCase("on") == 0);
		useRules.setSelected(configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.USE_RULES)
				.compareToIgnoreCase("on") == 0);
		ignoreCase.setSelected(configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.IGNORE_CASE)
				.compareToIgnoreCase("on") == 0);
		repetitionType.setSelected(configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.REPETITION_TYPE)
				.compareToIgnoreCase("forced") == 0);

		erase.setEnabled(configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.ERASE_REPETITION)
				.compareToIgnoreCase("on") == 0);
		language.setEnabled(configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.CHOOSABLE_LANGUAGE)
				.compareToIgnoreCase("on") == 0);
		dictionary.setEnabled(configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.DICTIONARY_ACCESSIBLE)
				.compareToIgnoreCase("on") == 0);

		// edit.setEnabled(configurationAndSettings.optionConfiguration.getConfig(ConfigurationDefaultOptions.STUDENT_EDITS_OR_REMOVES).compareToIgnoreCase("on")==0);
		// remove.setEnabled(configurationAndSettings.optionConfiguration.getConfig(ConfigurationDefaultOptions.STUDENT_EDITS_OR_REMOVES).compareToIgnoreCase("on")==0);

		manage.setEnabled(configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.STUDENT_MANAGES_FILES)
				.compareToIgnoreCase("on") == 0);

		if (configurationAndSettings.optionConfiguration.getConfig(
				ConfigurationDefaultOptions.SHOW_STATUS_AND_CLOCK)
				.compareToIgnoreCase("on") == 0)
			clock.init();
	}

	private void setForDownloadActionPerformed(ActionEvent evt) {
		String address = (String) JOptionPane
				.showInputDialog(
						this,
						this.configurationAndSettings.messageConfiguration
								.getConfig(ConfigurationDefaultMessages.SET_ADDRESS_FOR_DOWNLOAD),
						this.configurationAndSettings.messageConfiguration
								.getConfig(ConfigurationDefaultMessages.DOWNLOAD),
						JOptionPane.INFORMATION_MESSAGE,
						null/* icon */,
						null,/* selection */
						this.configurationAndSettings.pathConfiguration
								.getConfig(ConfigurationDefaultPath.DEFAULT_ADDRESS));
		if (address != null && address.length() > 0)
			try {
				URL url = new URL(address);
				URLConnection urlConnection = url.openConnection();
				new InputStreamReader(urlConnection.getInputStream());
				this.configurationAndSettings.pathConfiguration
						.getDetailedConfig(ConfigurationDefaultPath.INPUT)
						.setState(address);
				log.send("Opening test " + url.toExternalForm() + ".");
			} catch (MalformedURLException e) {
				JOptionPane
						.showMessageDialog(
								this,
								this.configurationAndSettings.errorConfiguration
										.getConfig(ConfigurationDefaultErrors.MALFORMED_URL)
										+ "\n" + address,
								this.configurationAndSettings.errorConfiguration
										.getConfig(ConfigurationDefaultErrors.ERROR),
								JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane
						.showMessageDialog(
								this,
								this.configurationAndSettings.errorConfiguration
										.getConfig(ConfigurationDefaultErrors.NO_CONNECTION_TO_URL)
										+ "\n" + address,
								this.configurationAndSettings.errorConfiguration
										.getConfig(ConfigurationDefaultErrors.ERROR),
								JOptionPane.ERROR_MESSAGE);
			}
	}

	/**
	 * Sets all component sizes
	 * 
	 * @author K. Siek
	 * @version 2.002
	 */
	private void setMenu() {
		if (configurationAndSettings.optionConfiguration.getConfig(
				ConfigurationDefaultOptions.SHOW_MENU)
				.compareToIgnoreCase("on") == 0) {
			// FILE MENU
			file.add(open);
			file.add(internet);
			file.add(manage);
			file.add(quit);
			menu.add(file);

			// TEST MENU
			test.add(start);
			test.add(stop);
			test.add(helpMe);
			test.add(separator1);
			test.add(training);
			test.add(shuffle);
			test.add(reverse);
			test.add(useRules);
			test.add(ignoreCase);
			test.add(repetitionType);
			test.add(separator2);
			test.add(remove);
			test.add(edit);
			test.add(erase);
			menu.add(test);

			// PREFERENCES MENU
			preferences.add(language);
			preferences.add(statistics);
			preferences.add(configuration);
			preferences.add(separator3);
			preferences.add(reload);
			menu.add(preferences);

			// HELP MENU
			help.add(handbook);
			help.add(dictionary);
			help.add(about);
			menu.add(help);

			// ENTIRE MENU
			setJMenuBar(menu);
		}
	}

	/**
	 * Sets boolean variables, by configuration.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	private void setVariables() {
		askToSaveInput = false;
		firstAttempt = true;
		showPercentage = configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.SHOW_PERCENTAGE).equals(
						"on");
		studentConfigures = configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.STUDENT_CONFIGURES)
				.compareToIgnoreCase("on") == 0;
		testMode = configurationAndSettings.optionConfiguration.getConfig(
				ConfigurationDefaultOptions.MODE).compareTo("test") == 0;
		forcedRepetition = configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.REPETITION_TYPE)
				.compareTo("forced") == 0;
		shuffleEnabled = configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.SHUFFLE).compareTo("on") == 0;
		reverseEnabled = configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.REVERSE).compareTo("on") == 0;
		resizable = configurationAndSettings.optionConfiguration.getConfig(
				ConfigurationDefaultOptions.RESIZIBLE_WINDOW).compareTo("on") == 0;
		testCanBeStopped = configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.STUDENT_STOPS_TEST)
				.compareToIgnoreCase("on") == 0;
		testCanBeEdited = configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.STUDENT_EDITS_OR_REMOVES)
				.compareToIgnoreCase("on") == 0;
	}

	/**
	 * Shows information about the author.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param ActionEvent
	 *            evt
	 */
	private void showAboutActionPerformed(ActionEvent evt) {
		JOptionPane
				.showMessageDialog(
						this,
						configurationAndSettings.messageConfiguration
								.getConfig(ConfigurationDefaultMessages.ABOUT_TITLE)
								+ "\n"
								+ configurationAndSettings.messageConfiguration
										.getConfig(ConfigurationDefaultMessages.ABOUT_DATE)
								+ "\n"
								+ configurationAndSettings.messageConfiguration
										.getConfig(ConfigurationDefaultMessages.ABOUT_DESCRIPTION)
								+ "\n" + "Konrad Siek",
						// configurationAndSettings.messageConfiguration.getConfig(ConfigurationDefaultMessages.ABOUT_AUTHOR),
						configurationAndSettings.messageConfiguration
								.getConfig(ConfigurationDefaultMessages.ABOUT),
						JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Screens the text or image for the next question.
	 * 
	 * @author K. Siek
	 * @version 2.1
	 */
	public void refreshQuestion() {

		String questionText = new String(current.question);
		if (configurationAndSettings.optionConfiguration.getConfig(
				ConfigurationDefaultOptions.SHOW_HINT)
				.compareToIgnoreCase("on") == 0
				&& current.hint.compareTo("") != 0 && current.hint != null)
			questionText = questionText
					+ "\n\n"
					+ configurationAndSettings.messageConfiguration
							.getConfig(ConfigurationDefaultMessages.HINT)
					+ ": " + current.hint;

		if (current.type != null
				&& current.type.compareTo(ConfigurationDefaultRegex.IMAGE) == 0) {
			String pictureTitle = null;
			String errorTitle = null;
			String errorMsg = null;
			question.setText("");
			picture = new PictureFrame(current.question, pictureTitle,
					errorTitle, errorMsg);
			picture.requestFocusInWindow();
		} else if (current.type != null
				&& (current.type.compareTo(ConfigurationDefaultRegex.SOUND) == 0)
				|| (current.type.compareTo(ConfigurationDefaultRegex.MEDIA) == 0)) {
			question.setText("");
			this.runMedia(current.question);
		} else
			question.setText(questionText);
		answer.setText("");
		answer.requestFocus();
	}

	/**
	 * Screens the text or image for the next question.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	private void showNextQuestion() {
		if (current != null)
			if (current.type != null && current.type.compareTo("image") == 0)
				picture.dispose();

		current = (LineItem) inputList.remove(0);
		this.refreshQuestion();
	}

	/**
	 * Sets buttons at the end of test
	 * 
	 * @author K. Siek
	 * @version 2.003
	 */
	private void setButtonsAfterStop() {
		useRules.setEnabled(studentConfigures);
		training.setEnabled(true);
		ignoreCase.setEnabled(studentConfigures);
		repetitionType.setEnabled(studentConfigures);
		reverse.setEnabled(true);
		shuffle.setEnabled(true);
		repetitionType.setEnabled(true);

		edit.setEnabled(false);
		remove.setEnabled(false);
		helpMe.setEnabled(false);
		helpMeButton.setEnabled(false);
		ok.setEnabled(false);
		startTest.setEnabled(true);
		start.setEnabled(true);
		reload.setEnabled(true);
		answer.setText("");
		question.setText("");
		stop.setEnabled(false);
	}

	private void setButtonsBeforeStart() {
		useRules.setEnabled(false);
		training.setEnabled(false);
		ignoreCase.setEnabled(false);
		repetitionType.setEnabled(false);
		reverse.setEnabled(false);
		shuffle.setEnabled(false);
		repetitionType.setEnabled(false);

		edit.setEnabled(testCanBeEdited);
		remove.setEnabled(testCanBeEdited);

		boolean setEnabled = configurationAndSettings.optionConfiguration
				.getConfig(ConfigurationDefaultOptions.ALLOW_EARLY_HELP)
				.compareToIgnoreCase("on") == 0;

		helpMe.setEnabled(setEnabled);
		helpMeButton.setEnabled(setEnabled);
		ok.setEnabled(true);
		startTest.setEnabled(false);
		start.setEnabled(false);
		reload.setEnabled(false);
		stop.setEnabled(testCanBeStopped);
	}

	/**
	 * Starts the test.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param ActionEvent
	 *            evt
	 */
	private void startTestActionPerformed(ActionEvent evt) {
		// TURN OFF NECESSARY BUTTONS
		setVariables();
		setButtonsBeforeStart();

		Input inputFile = null;
		String inputPath = null;
		// LOAD INPUT FILE
		if (!forcedRepetition) {
			String repPath = configurationAndSettings.pathConfiguration
					.getConfig(ConfigurationDefaultPath.REPETITION);
			inputFile = new InputFile(repPath);
			try {
				List list = inputFile
						.obtain(configurationAndSettings.optionConfiguration
								.getConfig(ConfigurationDefaultOptions.FILE_ENCODING));
				if (list.isEmpty())
					throw new IOException();
				JOptionPane
						.showMessageDialog(
								this,
								configurationAndSettings.messageConfiguration
										.getConfig(ConfigurationDefaultMessages.DOING_REPETITION),
								configurationAndSettings.messageConfiguration
										.getConfig(ConfigurationDefaultMessages.REPETITION),
								JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				JOptionPane
						.showMessageDialog(
								this,
								configurationAndSettings.messageConfiguration
										.getConfig(ConfigurationDefaultMessages.OUT_OF_REPETITION)
										+ "\n"
										+ configurationAndSettings.messageConfiguration
												.getConfig(ConfigurationDefaultMessages.PROGRESSING_TO_INPUT),
								configurationAndSettings.messageConfiguration
										.getConfig(ConfigurationDefaultMessages.REPETITION),
								JOptionPane.INFORMATION_MESSAGE);
				inputPath = configurationAndSettings.pathConfiguration
						.getConfig(ConfigurationDefaultPath.INPUT);
				try {
					new URL(inputPath);
					inputFile = new InputURL(inputPath);
				} catch (MalformedURLException e1) {
					inputFile = new InputFile(inputPath);
				}
			}
		} else {
			inputPath = configurationAndSettings.pathConfiguration
					.getConfig(ConfigurationDefaultPath.INPUT);
			try {
				new URL(inputPath);
				inputFile = new InputURL(inputPath);
			} catch (MalformedURLException e1) {
				inputFile = new InputFile(inputPath);
			}
		}

		try {
			inputList = (List) (new LineParser(
					inputFile
							.obtain(configurationAndSettings.optionConfiguration
									.getConfig(ConfigurationDefaultOptions.FILE_ENCODING)),
					configurationAndSettings.regexConfiguration)).convert();
		} catch (ConverterException e) {
			JOptionPane
					.showMessageDialog(
							this,
							configurationAndSettings.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.WRONG_FILE_FORMAT)
									+ "\n" + e.getLine(),
							configurationAndSettings.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR),
							JOptionPane.ERROR_MESSAGE);
			setButtonsAfterStop();
			return;
		} catch (IOException e1) {
			String startTest = configurationAndSettings.optionConfiguration
					.getConfig(ConfigurationDefaultOptions.START_TEST);
			if (startTest.equals("local")) {
				openActionPerformed(evt);
				setButtonsAfterStop();
				return;
			} else if (startTest.equals("web")) {
				setForDownloadActionPerformed(evt);
				setButtonsAfterStop();
				return;
			} else {
				Object[] options = { ConfigurationDefaultMessages.FILE,
						ConfigurationDefaultMessages.DOWNLOAD,
						ConfigurationDefaultMessages.CANCEL };
				int n = JOptionPane
						.showOptionDialog(
								this,
								configurationAndSettings.errorConfiguration
										.getConfig(ConfigurationDefaultErrors.FILE_DOESNT_EXIST_OR_EMPTY)
										+ "\n"
										+ configurationAndSettings.messageConfiguration
												.getConfig(ConfigurationDefaultMessages.CHOOSE_A_DIFFERENT_FILE),
								configurationAndSettings.messageConfiguration
										.getConfig(ConfigurationDefaultMessages.CONFIRM),
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (n == JOptionPane.YES_OPTION) {
					openActionPerformed(evt);
				}
				if (n == JOptionPane.NO_OPTION) {
					setForDownloadActionPerformed(evt);
				}
				setButtonsAfterStop();
				return;
			}
		}

		redoList = new LinkedList();
		doneList = new LinkedList();

		// INIT STATS
		done = new Integer(0);
		soFar = new Integer(0);
		toGo = new Integer(inputList.size());
		if (!inputList.isEmpty())
			refreshStats();
		else {
			openActionPerformed(null);
			startTestActionPerformed(evt);
			return;
		}

		// SHUFFLE, REVERSE
		if (shuffleEnabled)
			Collections.shuffle(inputList);
		if (reverseEnabled)
			Collections.reverse(inputList);

		log.send("Starting test.");

		// SCREEN INPUT FILE LINE 1.
		showNextQuestion();
	}

	private void stopActionPerformed(ActionEvent evt) {
		log.send("Stopping test (progress: " + done + "/" + soFar + "/" + toGo + ", grade: " + percentage + ").");
		int stop = JOptionPane
				.showConfirmDialog(
						this,
						configurationAndSettings.messageConfiguration
								.getConfig(ConfigurationDefaultMessages.ARE_YOU_SURE_YOU_WANT_TO_STOP),
						configurationAndSettings.messageConfiguration
								.getConfig(ConfigurationDefaultMessages.CONFIRM),
						JOptionPane.OK_CANCEL_OPTION);
		if (stop == JOptionPane.OK_OPTION) {
			int save = JOptionPane
					.showConfirmDialog(
							this,
							configurationAndSettings.messageConfiguration
									.getConfig(ConfigurationDefaultMessages.WOULD_YOU_LIKE_TO_SAVE),
							configurationAndSettings.messageConfiguration
									.getConfig(ConfigurationDefaultMessages.CONFIRM),
							JOptionPane.OK_CANCEL_OPTION);
			if (save == JOptionPane.OK_OPTION) {
				final JFileChooser fileChooser = new JFileChooser();
				fileChooser
						.setCurrentDirectory(new File(
								this.configurationAndSettings.pathConfiguration
										.getConfig(ConfigurationDefaultPath.FILE_CHOOSER_DEFAULT_PATH)));
				int returnVal = fileChooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String path = file.toString();
					Output out = new OutputFile(path);
					try {
						List saveList = new LinkedList(inputList);
						saveList.add(0, current);
						out.generate(saveList);
					} catch (IOException e) {
						JOptionPane
								.showMessageDialog(
										this,
										configurationAndSettings.errorConfiguration
												.getConfig(ConfigurationDefaultErrors.ERROR_WRITING_FILE),
										configurationAndSettings.errorConfiguration
												.getConfig(ConfigurationDefaultErrors.ERROR),
										JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			setButtonsAfterStop();
			potentiallySaveRepetition();
		}
	}

	private void potentiallySaveRepetition() {
		if (!forcedRepetition) {
			Output file = new OutputFile(
					configurationAndSettings.pathConfiguration
							.getConfig(ConfigurationDefaultPath.REPETITION));
			try {
				file.generate(redoList);
			} catch (IOException e) {
				JOptionPane
						.showMessageDialog(
								this,
								configurationAndSettings.optionConfiguration
										.getConfig(ConfigurationDefaultErrors.ERROR_WRITING_FILE),
								configurationAndSettings.optionConfiguration
										.getConfig(ConfigurationDefaultErrors.ERROR),
								JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void switchActionPerformed(ActionEvent evt) {
		String command = evt.getActionCommand();
		JCheckBoxMenuItem sender = (JCheckBoxMenuItem) evt.getSource();
		boolean success = true;

		if (sender.isSelected())
			success = configurationAndSettings.optionConfiguration
					.getDetailedConfig(command).setState("on");
		else
			success = configurationAndSettings.optionConfiguration
					.getDetailedConfig(command).setState("off");
		if (!success) {
			JOptionPane
					.showMessageDialog(
							this,
							configurationAndSettings.optionConfiguration
									.getConfig(ConfigurationDefaultErrors.OPERATION_UNSUCCESSFUL),
							configurationAndSettings.optionConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR),
							JOptionPane.ERROR_MESSAGE);
			sender.setSelected(configurationAndSettings.optionConfiguration
					.getConfig(command).compareToIgnoreCase("on") == 0);
		}
	}

	private void switchRepetitionActionPerformed(ActionEvent evt) {
		boolean success = true;
		if (repetitionType.isSelected())
			success = configurationAndSettings.optionConfiguration
					.getDetailedConfig(
							ConfigurationDefaultOptions.REPETITION_TYPE)
					.setState("forced");
		else
			success = configurationAndSettings.optionConfiguration
					.getDetailedConfig(
							ConfigurationDefaultOptions.REPETITION_TYPE)
					.setState("standard");
		if (!success) {
			JOptionPane
					.showMessageDialog(
							this,
							configurationAndSettings.optionConfiguration
									.getConfig(ConfigurationDefaultErrors.OPERATION_UNSUCCESSFUL),
							configurationAndSettings.optionConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR),
							JOptionPane.ERROR_MESSAGE);
			repetitionType
					.setSelected(configurationAndSettings.optionConfiguration
							.getConfig(
									ConfigurationDefaultOptions.REPETITION_TYPE)
							.compareToIgnoreCase("forced") == 0);
		}
	}

	private void switchStatisticsActionPerformed(ActionEvent evt) {
		boolean success = true;
		if (statistics.isSelected()) {
			success = configurationAndSettings.optionConfiguration
					.getDetailedConfig(ConfigurationDefaultOptions.SAVE_LOG)
					.setState("on");
			//this.log.turnOn();
			log.open();
		} else {
			success = configurationAndSettings.optionConfiguration
					.getDetailedConfig(ConfigurationDefaultOptions.SAVE_LOG)
					.setState("off");
			//this.log.turnOff();
			log.close();
		}
		if (!success) {
			JOptionPane
					.showMessageDialog(
							this,
							configurationAndSettings.optionConfiguration
									.getConfig(ConfigurationDefaultErrors.OPERATION_UNSUCCESSFUL),
							configurationAndSettings.optionConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR),
							JOptionPane.ERROR_MESSAGE);
			training.setSelected(configurationAndSettings.optionConfiguration
					.getConfig(ConfigurationDefaultOptions.SAVE_LOG)
					.compareToIgnoreCase("on") == 0);
		}
	}

	private void switchTrainingActionPerformed(ActionEvent evt) {
		boolean success = true;
		if (training.isSelected())
			success = configurationAndSettings.optionConfiguration
					.getDetailedConfig(ConfigurationDefaultOptions.MODE)
					.setState("standard");
		else
			success = configurationAndSettings.optionConfiguration
					.getDetailedConfig(ConfigurationDefaultOptions.MODE)
					.setState("test");
		if (!success) {
			JOptionPane
					.showMessageDialog(
							this,
							configurationAndSettings.optionConfiguration
									.getConfig(ConfigurationDefaultErrors.OPERATION_UNSUCCESSFUL),
							configurationAndSettings.optionConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR),
							JOptionPane.ERROR_MESSAGE);
			training.setSelected(configurationAndSettings.optionConfiguration
					.getConfig(ConfigurationDefaultOptions.MODE)
					.compareToIgnoreCase("standard") == 0);
		}
	}
}
