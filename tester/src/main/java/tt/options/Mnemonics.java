package tt.options;

import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;

import tt.config.annotations.From;

@From(file="tt/options/mnemonics.properties")
public class Mnemonics implements Properties {

    public Mnemonics() throws ConfigException, AnnotationException {
        this.initialize();
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


    public class FileMenu implements Properties {

        public FileMenu() throws ConfigException, AnnotationException {}

        @Option
        public String open;

        @Option
        public String internet;

        @Option
        public String manage;

        @Option
        public String quit;
    }
    
    public FileMenu fileMenu;

    public class TestMenu implements Properties {

        public TestMenu() throws ConfigException, AnnotationException {}

        // Test drop-down menu (top)
        @Option
        public String help;

        @Option
        public String start;

        @Option
        public String stop;

        @Option
        public String training;

        @Option
        public String shuffle;

        @Option
        public String reverse;

        @Option
        public String useRules;

        @Option
        public String ignoreCase;

        @Option
        public String repetition;
    }

    public TestMenu testMenu;

    // Preferences drop-down menu (top)
    @From(file="tt/options/mnemonics.properties")
    public class PreferencesMenu implements Properties {
        
        @Option
        public String language;

        @Option
        public String logging;

        @Option
        public String statistics;

        @Option
        public String configuration;

        @Option
        public String reload;
    }

    public PreferencesMenu preferencesMenu;

    // Help drop-down menu (top)
    @From(file="tt/options/mnemonics.properties")
    public class HelpMenu implements Properties {

        public HelpMenu() throws ConfigException, AnnotationException {}

        @Option
        public String dictionary;
        
        @Option
        public String about;
    }

    public HelpMenu helpMenu;
}