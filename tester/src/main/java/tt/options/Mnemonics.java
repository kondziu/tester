package tt.options;

import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;

import java.util.Set;

import tt.config.annotations.From;

@From(file="tt/options/mnemonics.properties")
public class Mnemonics implements Properties {

    public Mnemonics() throws IllegalAccessException, ConfigException, AnnotationException {
        this.initializeProperties();
        this.fileMenu = new FileMenu();
        this.testMenu = new TestMenu();
        this.preferencesMenu = new PreferencesMenu();
        this.helpMenu = new HelpMenu();
    }

    // Buttons
    @Option
    public String startButton;

    @Option
    public String helpButton;

    @Option
    public String okButton;

    // Menu bar (top)
    @Option
    public String file;

    @Option
    public String test;

    @Option
    public String preferences;

    @Option
    public String help;

    // File drop-down menu (top)
    @From(file="tt/options/mnemonics.properties")
    public class FileMenu implements Properties {

        public FileMenu() throws IllegalAccessException, ConfigException, AnnotationException {
            this.initializeProperties();
        }

        @Option(property = "file.open")
        public String open;

        @Option(property = "file.internet")
        public String internet;

        @Option(property = "file.manage")
        public String manage;

        @Option(property = "file.quit")
        public String quit;
    }
    
    public FileMenu fileMenu;

    @From(file="tt/options/mnemonics.properties")
    public class TestMenu implements Properties {

        public TestMenu() throws IllegalAccessException, ConfigException, AnnotationException {
            this.initializeProperties();
        }

        // Test drop-down menu (top)
        @Option(property = "test.help")
        public String help;

        @Option(property = "test.start")
        public String start;

        @Option(property = "test.stop")
        public String stop;

        @Option(property = "test.training")
        public String training;

        @Option(property = "test.shuffle")
        public String shuffle;

        @Option(property = "test.reverse")
        public String reverse;

        @Option(property = "test.use_rules")
        public String useRules;

        @Option(property = "test.ignore_case")
        public String ignoreCase;

        @Option(property = "test.repetition")
        public String repetition;
    }

    public TestMenu testMenu;

    // Preferences drop-down menu (top)
    @From(file="tt/options/mnemonics.properties")
    public class PreferencesMenu implements Properties {

        public PreferencesMenu() throws IllegalAccessException, ConfigException, AnnotationException {
            this.initializeProperties();
        }
        
        @Option(property = "preferences.language")
        public String language;

        @Option(property = "preferences.logging")
        public String logging;

        @Option(property = "preferences.statistics")
        public String statistics;

        @Option(property = "preferences.configuration")
        public String configuration;

        @Option(property = "preferences.reload")
        public String reload;
    }

    public PreferencesMenu preferencesMenu;

    // Help drop-down menu (top)
    @From(file="tt/options/mnemonics.properties")
    public class HelpMenu implements Properties {

        public HelpMenu() throws IllegalAccessException, ConfigException, AnnotationException {
            this.initializeProperties();
        }

        @Option(property = "help.dictionary")
        public String dictionary;
        
        @Option(property = "help.about")
        public String about;
    }

    public HelpMenu helpMenu;
}