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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
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

public class Main extends javax.swing.JFrame {
 

	/**
	 * Main interface for the Tester application. To be renamed later.
	 * @author K. Siek
	 * @version 2.0
	 */
	private static final long serialVersionUID = 9218503094273330361L;
	/** Creates new form VisualInterface */
    public Main() {
    	configurationAndSettings = new ConfigurationStorage("files\\files.txt");
    	comparison = new LineComparator(configurationAndSettings);
    	//System.out.println(configurationAndSettings.regexConfiguration);    	
        initComponents();
    }
    /**
	 * Sets captions to all the components
	 * @author K. Siek
	 * @version 2.0
	 */
    public void setComponentTexts(){
    	
    	setTitle((String)configurationAndSettings.messageConfiguration.getConfig("form title"));
    	
    	answer.setText("");
    	gradeLabel.setText("");
    	points.setText("? / ? /- ?");
    	question.setText("");
    	
    	ok.setText((String)configurationAndSettings.messageConfiguration.getConfig("ok"));
    	startTest.setText((String)configurationAndSettings.messageConfiguration.getConfig("start test"));
    	helpMeButton.setText((String)configurationAndSettings.messageConfiguration.getConfig("help me"));
    	grade.setText((String)configurationAndSettings.messageConfiguration.getConfig("score"));
    	file.setText((String)configurationAndSettings.messageConfiguration.getConfig("file"));
    	open.setText((String)configurationAndSettings.messageConfiguration.getConfig("open"));
    	quit.setText((String)configurationAndSettings.messageConfiguration.getConfig("quit"));
    	test.setText((String)configurationAndSettings.messageConfiguration.getConfig("test"));
        helpMe.setText((String)configurationAndSettings.messageConfiguration.getConfig("help me"));
        start.setText((String)configurationAndSettings.messageConfiguration.getConfig("start test"));
        shuffle.setText((String)configurationAndSettings.messageConfiguration.getConfig("shuffle"));
        training.setText((String)configurationAndSettings.messageConfiguration.getConfig("training mode"));
        reverse.setText((String)configurationAndSettings.messageConfiguration.getConfig("reverse"));
        erase.setText((String)configurationAndSettings.messageConfiguration.getConfig("erase repetition"));
        edit.setText((String)configurationAndSettings.messageConfiguration.getConfig("edit question"));
        preferences.setText((String)configurationAndSettings.messageConfiguration.getConfig("preferences"));
        language.setText((String)configurationAndSettings.messageConfiguration.getConfig("language version"));
        configuration.setText((String)configurationAndSettings.messageConfiguration.getConfig("configuration"));
        statistics.setText((String)configurationAndSettings.messageConfiguration.getConfig("statistics"));
        handbook.setText((String)configurationAndSettings.messageConfiguration.getConfig("handbook"));
        about.setText((String)configurationAndSettings.messageConfiguration.getConfig("about"));
        help.setText((String)configurationAndSettings.messageConfiguration.getConfig("help"));
        remove.setText((String)configurationAndSettings.messageConfiguration.getConfig("remove"));
        manage.setText((String)configurationAndSettings.messageConfiguration.getConfig("manage"));
        internet.setText((String)configurationAndSettings.messageConfiguration.getConfig("internet"));
       	reload.setText((String)configurationAndSettings.messageConfiguration.getConfig("reload"));
       	useRules.setText((String)configurationAndSettings.messageConfiguration.getConfig("use rules"));
        ignoreCase.setText((String)configurationAndSettings.messageConfiguration.getConfig("ignore case"));
        repetitionType.setText((String)configurationAndSettings.messageConfiguration.getConfig("forced repetition"));
        stop.setText((String)configurationAndSettings.messageConfiguration.getConfig("stop"));
        goodAnswer = new ImageIcon((String)configurationAndSettings.pathConfiguration.getConfig("good icon"));
        poorAnswer = new ImageIcon((String)configurationAndSettings.pathConfiguration.getConfig("poor icon"));
    }
    public void reloadCaptions() {
		setComponentTexts();
	}
    /**
	 * Sets enalbed and disabled components to their propoer states at the beginning of usage.
	 * @author K. Siek
	 * @version 2.0
	 */
    public void setEnabledComponents(){
    	ok.setEnabled(false);
    	startTest.setEnabled(true);
    	start.setEnabled(true);
    	helpMe.setEnabled(false);
    	helpMeButton.setEnabled(false);
    	reload.setEnabled(true);
    	
    	boolean configureEnable=((String)configurationAndSettings.optionConfiguration.getConfig("student configures")).compareToIgnoreCase("on")==0;
    	
    	training.setEnabled(configureEnable);
    	useRules.setEnabled(configureEnable);    
    	ignoreCase.setEnabled(configureEnable);
    	repetitionType.setEnabled(configureEnable);
    	    	
        training.setSelected(((String)configurationAndSettings.optionConfiguration.getConfig("mode")).compareToIgnoreCase("practice")==0);        
        shuffle.setSelected(((String)configurationAndSettings.optionConfiguration.getConfig("shuffle")).compareToIgnoreCase("on")==0);        
        reverse.setSelected(((String)configurationAndSettings.optionConfiguration.getConfig("reverse")).compareToIgnoreCase("on")==0);
        
        stop.setEnabled(((String)configurationAndSettings.optionConfiguration.getConfig("student stops test")).compareToIgnoreCase("on")==0);
        erase.setEnabled(((String)configurationAndSettings.optionConfiguration.getConfig("erase repetition")).compareToIgnoreCase("on")==0);
        
        edit.setEnabled(((String)configurationAndSettings.optionConfiguration.getConfig("student edits or removes")).compareToIgnoreCase("on")==0);
        remove.setEnabled(((String)configurationAndSettings.optionConfiguration.getConfig("student edits or removes")).compareToIgnoreCase("on")==0);
    }
    /**
	 * Sets actions to components.
	 * @author K. Siek
	 * @version 2.0
	 */
    private void setComponentActions(){
    	about.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){showAboutActionPerformed(evt);}});
    	startTest.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){startTestActionPerformed(evt);}});
    	start.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){startTestActionPerformed(evt);}});
    	configuration.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){configurationTestActionPerformed(evt);}});
    	ok.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){okTestActionPerformed(evt);}});
    	help.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){helpMeActionPerformed(evt);}});
    	helpMeButton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){helpMeActionPerformed(evt);}});
    	open.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){openActionPerformed(evt);}});
    	manage.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){manageActionPerformed(evt);}});
    	internet.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){setForDownloadActionPerformed(evt);}});
    	quit.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){quitActionPerformed(evt);}});
    	language.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){languageActionPerformed(evt);}});
    	training.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){switchTrainingActionPerformed(evt);}});
    	reload.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){reloadActionPerformed(evt);}});
    	shuffle.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){switchShuffleActionPerformed(evt);}});
    	reverse.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){switchReverseActionPerformed(evt);}});
    	stop.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){stopActionPerformed(evt);}});
    	//useRules
        //ignoreCase;
        //repetitionType;
    }
    protected void stopActionPerformed(ActionEvent evt) {
		//ASK IF REALLY WANTS TO STOP
    	//ASK IF WANTS TO SAVE
    	int stop = JOptionPane.showConfirmDialog(this,"END?","end title",JOptionPane.OK_CANCEL_OPTION);
    	if(stop==JOptionPane.OK_OPTION){
    		int save = JOptionPane.showConfirmDialog(this,"SAVE?","end title",JOptionPane.OK_CANCEL_OPTION);
    		if(save==JOptionPane.OK_OPTION){
    			String path = (String) JOptionPane.showInputDialog(this,"WHERE?","end title",JOptionPane.OK_CANCEL_OPTION, null/*icon*/,null,null);
    			Output out = new OutputFile(path); 
    			try{
					out.generate(inputList);
				}
    			catch (IOException e) {
    				JOptionPane.showMessageDialog(this,"ERROR WRITING FILE","RROR",JOptionPane.ERROR_MESSAGE);					
				}
    		}
    	}    	
	}
	protected void reloadActionPerformed(ActionEvent evt) {		
    	reloadCaptions();
		setVariables();
		setEnabledComponents();
    	
	}
	protected void switchTrainingActionPerformed(ActionEvent evt) {
    	boolean success=true;
		if(training.isSelected())
			success = configurationAndSettings.optionConfiguration.getDetailedConfig("mode").setState("standard");
		else 
			success = configurationAndSettings.optionConfiguration.getDetailedConfig("mode").setState("test");
		if(!success){
			//TODO
			JOptionPane.showMessageDialog(this,"no success conduuctink opereushun");
			training.setSelected(
				((String)configurationAndSettings.optionConfiguration.getConfig("mode")).compareToIgnoreCase("standard")==0
					);
		}
	}
    protected void switchReverseActionPerformed(ActionEvent evt) {
    	boolean success=true;
		if(reverse.isSelected())
			success = configurationAndSettings.optionConfiguration.getDetailedConfig("reverse").setState("on");
		else 
			success = configurationAndSettings.optionConfiguration.getDetailedConfig("reverse").setState("off");
		if(!success){
			//TODO
			JOptionPane.showMessageDialog(this,"no success conduuctink opereushun");
			reverse.setSelected(
				((String)configurationAndSettings.optionConfiguration.getConfig("reverse")).compareToIgnoreCase("on")==0
					);
		}
	}
    protected void switchShuffleActionPerformed(ActionEvent evt) {
    	boolean success=true;
		if(shuffle.isSelected())
			success = configurationAndSettings.optionConfiguration.getDetailedConfig("shuffle").setState("on");
		else 
			success = configurationAndSettings.optionConfiguration.getDetailedConfig("shuffle").setState("off");
		if(!success){
			//TODO
			JOptionPane.showMessageDialog(this,"no success conduuctink opereushun");
			shuffle.setSelected(
				((String)configurationAndSettings.optionConfiguration.getConfig("shuffle")).compareToIgnoreCase("on")==0
					);
		}
	}
	protected void languageActionPerformed(ActionEvent evt) {
		new LanguageVersionFrame(this,configurationAndSettings);		
	}
	/**
	 * Opens input manager
	 * @author K. Siek
	 * @version 2.003
	 */
    protected void openActionPerformed(ActionEvent evt) {
    	final JFileChooser fileChooser = new JFileChooser();    	
    	if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
    		File file = fileChooser.getSelectedFile();
    		this.configurationAndSettings.pathConfiguration.getDetailedConfig("input").setState(file.toString());
    		System.out.println("new input: "+file);
    	}		
	}
    protected void manageActionPerformed(ActionEvent evt) {   	
    	DynamicDirectoryTreeFrame.createAndShowGUI(configurationAndSettings);		
	}
    protected void setForDownloadActionPerformed(ActionEvent evt) {
    	String address = (String) JOptionPane.showInputDialog(
    			this,
    			this.configurationAndSettings.messageConfiguration.getConfig("set address for download").toString(),
    			this.configurationAndSettings.messageConfiguration.getConfig("download").toString(),
    			JOptionPane.OK_CANCEL_OPTION,
    			null/*icon*/,
    			null,/*selection*/
    			this.configurationAndSettings.pathConfiguration.getConfig("default address").toString()
    			/*default*/
    			);
    	if(address!=null&&address.length()>0)    		
    		try {
    			URL url = new URL(address);
    			URLConnection urlConnection = url.openConnection();    		
    			new InputStreamReader(urlConnection.getInputStream());
    			this.configurationAndSettings.pathConfiguration.getDetailedConfig("input").setState(address);
    		} 
    		catch (MalformedURLException e){
    			JOptionPane.showMessageDialog(
    					this,
    					this.configurationAndSettings.errorConfiguration.getConfig("malformed url").toString()
    					+"\n"+address,
    					this.configurationAndSettings.errorConfiguration.getConfig("error").toString(),
    					JOptionPane.ERROR_MESSAGE);
    		}
    		catch (IOException e) {				
    			JOptionPane.showMessageDialog(
    					this,
    					this.configurationAndSettings.errorConfiguration.getConfig("no connection to url").toString()
    					+"\n"+address,
    					this.configurationAndSettings.errorConfiguration.getConfig("error").toString(),
    					JOptionPane.ERROR_MESSAGE);
			}
    		
	}
    protected void quitActionPerformed(ActionEvent evt) {
    	this.dispose();
	}
	/**
	 * Runs component constructors.
	 * @author K. Siek
	 * @version 2.0
	 */
	public void constructComponents(){
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
        statistics = new JMenuItem();
        configuration = new JMenuItem();
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
        useRules = new  JCheckBoxMenuItem();
        ignoreCase = new JCheckBoxMenuItem();
        repetitionType = new JCheckBoxMenuItem();
        picture = null;
    }
	/**
	 * Sets key shortcuts to components
	 * @author K. Siek
	 * @version 2.0
	 */
	private void setComponentMnemonics(){
				
		//OK REACTS TO ENTER IN ANSWER FIELD
		answer.addKeyListener(new java.awt.event.KeyListener(){
				public void keyPressed(KeyEvent e){}	
				public void keyReleased(KeyEvent e){} 
				public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()=='\n'&&ok.isEnabled())				
				okTestActionPerformed(null);
			}});		
	}
	/**
	 * Runs methods initiating components.
	 * @author K. Siek
	 * @version 2.0
	 */
    private void initComponents() {
            		
    	this.constructComponents();
        this.setComponentTexts();
        this.setComponentActions();
        this.setComponentMnemonics();
        this.setComponentSizes();
        this.setEnabledComponents();
        this.setVariables();
        this.setMenu();
        //points.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.addComponents();       
        
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(resizable);
        this.getContentPane().add(mainPanel);
        this.pack();
        //handbook.setIcon(new ImageIcon("C:\\Documents and Settings\\Joe\\Pulpit\\book07.gif"));
        
    }
    /**
	 * Saves configuration changes and EXITS!
	 * Whatch out it uses System.exit(0);
	 * @author K. Siek
	 * @version 2.003
	 */
    public void dispose(){
    	//if changed
    	try {
			this.configurationAndSettings.saveToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//super.dispose();
		System.exit(0);
    }
    /**
	 * Sets up all visible components on the content pane.
	 * @author K. Siek
	 * @version 2.002
	 */
    private void addComponents(){
       question.setEditable(false);
        
        gradePanel.setMaximumSize(new java.awt.Dimension(10, 10));
        gradeLabel.setFont(new java.awt.Font("Arial", 1, 42));
        gradeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        points.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        grade.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        //points.setAlignmentY(SwingConstants.CENTER);
        
        gradePanel.setLayout(new BoxLayout(gradePanel,BoxLayout.Y_AXIS));
        topPanel.setLayout(new FlowLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));        
        bottomPanel.setLayout(new FlowLayout());
        buttonPanel.setLayout(new BorderLayout());
              
        gradePanel.add(grade);
        gradePanel.add(gradeLabel);
        gradePanel.add(points);
       
        questionPanel.add(question);
        questionPanel.setViewportView(question);

        topPanel.add(gradePanel);        
        topPanel.add(questionPanel);

        buttonPanel.add(startTest,BorderLayout.WEST);
        buttonPanel.add(helpMeButton,BorderLayout.EAST);
        
        bottomPanel.add(buttonPanel);    
        bottomPanel.add(separatorLabel1);        
        bottomPanel.add(ok);             
        
        mainPanel.add(topPanel);
        mainPanel.add(answer);
        mainPanel.add(separatorLabel2);
        mainPanel.add(bottomPanel);
        
        //SPACE AT THE BOTTOM
        
        mainPanel.add(bottomLabel);
    }
	/**
	 * Sets all component sizes
	 * @author K. Siek
	 * @version 2.002
	 */
    private void setMenu(){
        //FILE MENU
        file.add(open);
        file.add(internet);
        file.add(manage);
        file.add(quit);
        menu.add(file);        
        
        //TEST MENU
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
        
        //PREFERENCES MENU
        preferences.add(language);        
        preferences.add(statistics);
        preferences.add(configuration);
        preferences.add(separator3);
        preferences.add(reload);
        menu.add(preferences);
      
        //HELP MENU
        help.add(handbook);
        help.add(about);        
        menu.add(help);

        //ENTIRE MENU
        setJMenuBar(menu);
    }
    private void setComponentSizes() {
    	
        grade.setMaximumSize(new Dimension(110,40));    
        grade.setPreferredSize(new Dimension(110,40));
        grade.setMinimumSize(new Dimension(110,40));
        
        gradeLabel.setMaximumSize(new Dimension(110,50));
        gradeLabel.setPreferredSize(new Dimension(110,50));
        gradeLabel.setMinimumSize(new Dimension(110,50));
        
        points.setMaximumSize(new Dimension(110,40));
        points.setPreferredSize(new Dimension(110,40));
        points.setMinimumSize(new Dimension(110,40));
        
        question.setMaximumSize(new Dimension(250,100));
        question.setPreferredSize(new Dimension(250,100));
        question.setMinimumSize(new Dimension(250,100));
        
        questionPanel.setMaximumSize(new Dimension(250,120));
        questionPanel.setPreferredSize(new Dimension(250,120));
        questionPanel.setMinimumSize(new Dimension(250,120));       
        
        //startTest.setMaximumSize(new Dimension(90,30));
        //startTest.setPreferredSize(new Dimension(90,30));
        //startTest.setMinimumSize(new Dimension(90,30));
        
        helpMeButton.setMaximumSize(new Dimension(startTest.getPreferredSize()));
        helpMeButton.setPreferredSize(new Dimension(startTest.getPreferredSize()));
        helpMeButton.setMinimumSize(new Dimension(startTest.getPreferredSize()));
        
        answer.setMaximumSize(new Dimension(390,startTest.getPreferredSize().height));
        answer.setPreferredSize(new Dimension(390,startTest.getPreferredSize().height));
        answer.setMinimumSize(new Dimension(390,startTest.getPreferredSize().height));
        
        ok.setMaximumSize(new Dimension(startTest.getPreferredSize()));
        ok.setPreferredSize(new Dimension(startTest.getPreferredSize()));
        ok.setMinimumSize(new Dimension(startTest.getPreferredSize()));
        
        separatorLabel1.setMaximumSize(new Dimension(100,10));    
        separatorLabel1.setPreferredSize(new Dimension(100,10));
        separatorLabel1.setMinimumSize(new Dimension(100,10));      
        
        separatorLabel2.setMaximumSize(new Dimension(100,10));    
        separatorLabel2.setPreferredSize(new Dimension(100,10));
        separatorLabel2.setMinimumSize(new Dimension(100,10)); 
        
        bottomLabel.setMaximumSize(new Dimension(100,10));    
        bottomLabel.setPreferredSize(new Dimension(100,10));
        bottomLabel.setMinimumSize(new Dimension(100,10)); 
       
	}
	/**
	 * Checks whether the answer is correct.
	 * @author K. Siek
	 * @version 2.0
	 * @param String checked element
	 * @param List containing Strings which are bounds of correctness for element 
	 * @return boolean element is correct
	 */
    private boolean isCorrect(String text, List answerList) {
    	return comparison.compare(answerList,text);
	}
    /**
	 * Shows a dialogue box with the correct answer.
	 * @author K. Siek
	 * @version 2.0
	 * @param ActionEvent evt
	 */
    protected void helpMeActionPerformed(ActionEvent evt){
    	String answers = "";
    	for(Iterator i = current.answerList.iterator();i.hasNext();){
    		String answer=(String)i.next();
    		answers+=answer;
    		if(i.hasNext()){
    			//String or = (String)configurationAndSettings.messageConfiguration.getConfig("or");
    			//if(or==null)or="";
    			answers+="\n\n"; 
    		}
    	}
    	JOptionPane.showMessageDialog(
                this,
                answers,
                (String)configurationAndSettings.messageConfiguration.getConfig("help"),
                JOptionPane.INFORMATION_MESSAGE
                );	
    }
    /**
	 * Evaluates the answer.
	 * @author K. Siek
	 * @version 2.0
	 * @param ActionEvent evt
	 */
    protected void okTestActionPerformed(ActionEvent evt) {	
    	if(isCorrect(answer.getText(),current.answerList)){
    		JOptionPane.showMessageDialog(
    				this,
    				(String)configurationAndSettings.messageConfiguration.getConfig("good work"),
    				(String)configurationAndSettings.messageConfiguration.getConfig("good work title"),
    				JOptionPane.OK_OPTION,
    				goodAnswer);	
    		doneList.add(current);
    		if(firstAttempt){
    			done = new Integer(done.intValue()+1);
    			incrementSoFarAndToGoCounters();    			
    		}
    		loadNextQuestionAndSetButtons(); 
    		firstAttempt=true;    		
    	}
    	else{
    		JOptionPane.showMessageDialog(
    				this,
    				(String)configurationAndSettings.messageConfiguration.getConfig("poor work"),
    				(String)configurationAndSettings.messageConfiguration.getConfig("poor work title"),
    				JOptionPane.OK_OPTION,
    				poorAnswer);
    		if(testMode){
    			incrementSoFarAndToGoCounters();
    			loadNextQuestionAndSetButtons();    			
    			firstAttempt=true;
    		}
    		else
    			if(firstAttempt){
    				if(forcedRepetition)inputList.add(current); 
    				else redoList.add(current);
    				firstAttempt=false;
    				help.setEnabled(true);
    				helpMeButton.setEnabled(true);
    				incrementSoFarAndToGoCounters();
    			}	
    	}
	}
    /**
	 * Runs VisualConfiguration Class.
	 * @author K. Siek
	 * @version 2.0
	 * @param ActionEvent evt
	 */
	protected void configurationTestActionPerformed(ActionEvent evt) {
		new ConfigurationFrame(configurationAndSettings);
	}
	  /**
	 * Shows information about the author.
	 * @author K. Siek
	 * @version 2.0
	 * @param ActionEvent evt
	 */
    protected void showAboutActionPerformed(ActionEvent evt) {
    	JOptionPane.showMessageDialog(
                this,
                (String)configurationAndSettings.messageConfiguration.getConfig("about title")+"\n"+
                (String)configurationAndSettings.messageConfiguration.getConfig("about date")+"\n"+
                (String)configurationAndSettings.messageConfiguration.getConfig("about description")+"\n"+
                (String)configurationAndSettings.messageConfiguration.getConfig("about author"),
                (String)configurationAndSettings.messageConfiguration.getConfig("about"),
                JOptionPane.INFORMATION_MESSAGE
                );		
	}
    /**
	 * Sets boolean variables, by configuration.
	 * @author K. Siek
	 * @version 2.0
	 */
    private void setVariables(){
		firstAttempt=true;
		testMode=((String)configurationAndSettings.optionConfiguration.getConfig("mode")).compareTo("test")==0;
		forcedRepetition=((String)configurationAndSettings.optionConfiguration.getConfig("repetition type")).compareTo("forced")==0;
		shuffleEnabled=((String)configurationAndSettings.optionConfiguration.getConfig("shuffle")).compareTo("on")==0;
		reverseEnabled=((String)configurationAndSettings.optionConfiguration.getConfig("reverse")).compareTo("on")==0;
		resizable =((String)configurationAndSettings.optionConfiguration.getConfig("resizible window")).compareTo("on")==0;
			
    }
    /**
	 * Starts the test.
	 * @author K. Siek
	 * @version 2.0
	 * @param ActionEvent evt
	 */
	private void startTestActionPerformed(ActionEvent evt) {
				//TURN OFF NECESSARY BUTTONS
		setVariables();
		
		reload.setEnabled(false);
		
		ok.setEnabled(true);
        helpMe.setEnabled(false);
        helpMeButton.setEnabled(false);
        startTest.setEnabled(false);
        stop.setEnabled(testCanBeStopped);
        start.setEnabled(false);
				//LOAD INPUT FILE
				Input inputFile;
				String inputPath = (String)configurationAndSettings.pathConfiguration.getConfig("input");				
				try{
					new URL(inputPath);					
					inputFile = new InputURL(inputPath);
				}catch (MalformedURLException e1){							
					inputFile = new InputFile(inputPath);
				}				
				try {					
					inputList = (List)
					(new LineParser(
							inputFile.obtain(),
							configurationAndSettings.regexConfiguration
					)).convert();					
				} catch (IOException e) {					
					JOptionPane.showMessageDialog(
							this,
			                (String)configurationAndSettings.errorConfiguration.getConfig("error reading file")+" \""+(String)configurationAndSettings.pathConfiguration.getConfig("input")+"\""
			                +"\n(Concerning "+(String)configurationAndSettings.errorConfiguration.getConfig("concerning input")+")",			                
			                (String)configurationAndSettings.errorConfiguration.getConfig("Configuration File Error"),
			                JOptionPane.ERROR_MESSAGE
			                );
					setEnabledComponents();
					return;
				}
				redoList = new LinkedList();
				doneList = new LinkedList();
				
				//INIT STATS
				done = new Integer(0);
				soFar = new Integer(0); 
				toGo = new Integer(inputList.size());
				if(!inputList.isEmpty())
					refreshStats();
				else{
					//TODO
					//ERROR!!!!! NO LIST!!!!
				}
				
				//SHUFFLE, REVERSE
				if(shuffleEnabled)Collections.shuffle(inputList);
				if(reverseEnabled)Collections.reverse(inputList);				
				
				//SCREEN INPUT FILE LINE 1.	
				showNextQuestion();
	}
	 /**
	 * Screens the text or image for the next question.
	 * @author K. Siek
	 * @version 2.0
	 */
	private void showNextQuestion(){
		if(current!=null)
			if(current.type.compareTo("image")==0)
				picture.dispose();
		
		current = (LineItem)inputList.remove(0);
		String questionText = new String(current.question);
		if(((String)configurationAndSettings.optionConfiguration.getConfig("show hint")).compareToIgnoreCase("on")==0&&current.hint.compareTo("")!=0&&current.hint!=null)
				questionText=questionText+"\n\n"+
				configurationAndSettings.messageConfiguration.getConfig("hint")+
				" "+current.hint;
				
		if(current.type.compareTo("image")==0){			
			String pictureTitle = null;
			String errorTitle = null;
			String errorMsg = null;
			question.setText("");
			picture = new PictureFrame(current.question,pictureTitle,errorTitle,errorMsg);
			picture.requestFocusInWindow();
		}
		else
			question.setText(questionText);	
		answer.setText("");
		answer.requestFocus();
	}
	 /**
	 * Refreshes the counters, increments.
	 * @author K. Siek
	 * @version 2.0
	 */
	private void incrementSoFarAndToGoCounters(){
		soFar = new Integer(soFar.intValue()+1);	
		toGo = new Integer(inputList.size());
		//firstAttempt=true;
		refreshStats();
	}
	 /**
	 * Sets buttons and calls a method to load the enxt question.
	 * @author K. Siek
	 * @version 2.0
	 */
	private void loadNextQuestionAndSetButtons(){		
		if(!inputList.isEmpty()){
			//firstAttempt=true;
			helpMe.setEnabled(false);
	    	helpMeButton.setEnabled(false);
			ok.setEnabled(true);
			showNextQuestion();			
		}
		else{
			helpMe.setEnabled(false);
	    	helpMeButton.setEnabled(false);
			ok.setEnabled(false);
			startTest.setEnabled(true);
			start.setEnabled(true);
			reload.setEnabled(true);
			answer.setText("");
			question.setText("");
		}	
	}
	 /**
	 * Refreshes counters
	 * @author K. Siek
	 * @version 2.0
	 */
	private void refreshStats(){
		//percentage = new Double((float)done.intValue()/(float)soFar.intValue());
		percentage = new Double(((double)done.intValue()/(double)soFar.intValue())*100);
		if(percentage.isNaN()||percentage.isInfinite())percentage = new Double(0D);
		//percentage = new Double (82.7);
		String perc = (new Integer(percentage.intValue())).toString();		
		gradeLabel.setText(perc+"%");
	    points.setText(done.toString()+" / "+soFar.toString()+" / -"+toGo.toString());
	}
	 /**
	 * Runs the program.
	 * @author K. Siek
	 * @version 2.0
	 * @param none whatsoever
	 */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }   		

    private JTextField answer;
    
    private JLabel grade;
    private JLabel gradeLabel;
    private JPanel gradePanel;
    private JLabel points;
    
    private JMenuItem about;    
    private JMenuItem configuration;
    private JMenuItem edit;
    private JMenuItem erase;
    private JMenuItem handbook;
    private JMenuItem helpMe;
    private JMenuItem language;
    private JMenuItem quit;
    private JMenuItem remove;
    private JMenuItem start;
    private JMenuItem statistics;
    private JMenuItem open;
    private JMenuItem internet;
    private JMenuItem manage;
    private JMenuItem reload;
    private JMenuItem stop;
    private JCheckBoxMenuItem reverse;
    private JCheckBoxMenuItem shuffle;
    private JCheckBoxMenuItem training;
    private JCheckBoxMenuItem useRules;
    private JCheckBoxMenuItem ignoreCase;
    private JCheckBoxMenuItem repetitionType;
 
    private JMenu test;
    private JMenu preferences;
    private JMenu file;
    private JMenu help;
    private JMenuBar menu;
    
    private JButton helpMeButton;
    private JButton startTest;  
    private JButton ok;
   
    private JTextPane question;
    private JScrollPane questionPanel;

    private JSeparator separator1;
    private JSeparator separator2;
    private JSeparator separator3;
    
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    
    private JLabel separatorLabel1;
    private JLabel bottomLabel;
    private JLabel separatorLabel2;
    
    private PictureFrame picture; 
    
    private Integer done;
    private Integer soFar;
    private Integer toGo;
    private Double  percentage;
    
    private List inputList;
    private List redoList;
    private List doneList;
    
    private LineItem current;
    
    private ImageIcon goodAnswer;
    private ImageIcon poorAnswer;
    
    private ConfigurationStorage configurationAndSettings;
    private LineComparator comparison;
    private boolean testCanBeStopped;
    private boolean firstAttempt;
    private boolean testMode;
    private boolean forcedRepetition;
    private boolean shuffleEnabled;
    private boolean reverseEnabled;
    private boolean resizable;	
}
