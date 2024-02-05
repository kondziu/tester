package tt.options.gui;

import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;

import tt.config.annotations.From;

@From(file="tt/options/gui.properties")
public class Gui implements Properties {

    public Gui() throws ConfigException, AnnotationException {
        this.initialize();
    }

    @Option
    public Button start;
    
    @Option
    public Button help;

    @Option
    public Button ok;

    @Option
    public TopMenu menu;
    public static class TopMenu implements Properties {
        @Option
        public File file;
    
        @Option
        public Test test;

        @Option
        public Preferences preferences;

        @Option
        public Help help;
    }

    public static class File extends Menu {
        // open, internet, manage, quit

        @Option
        public MenuItem open;

        @Option
        public MenuItem internet;

        @Option
        public MenuItem manage;

        @Option
        public MenuItem quit;
    }

    public static class Test extends Menu {
        // start, stop, help
        // training mode, shuffle, reverse, use rules, ignore case, force repitition
        // remove, edit question, erase repetition

        @Option
        public MenuItem start;
    
        @Option
        public MenuItem stop;

        @Option
        public MenuItem help;

        @Option
        public CheckedMenuItem training;

        @Option
        public CheckedMenuItem shuffle;

        @Option
        public CheckedMenuItem reverse;

        @Option
        public CheckedMenuItem useRules;

        @Option
        public CheckedMenuItem ignoreCase;

        @Option
        public CheckedMenuItem repetition;

        @Option
        public MenuItem removeQuestion;

        @Option
        public MenuItem editQuestion;

        @Option
        public MenuItem eraseRepetition;
    }

    public static class Preferences extends Menu {
        // language version, logging, configuration
        // reload
        @Option
        public MenuItem language;

        @Option
        public CheckedMenuItem logging;

        @Option
        public MenuItem configuration;

        @Option
        public MenuItem reload;
    }

    public static class Help extends Menu {
        // dictionary, about

        @Option
        public MenuItem dictionary;

        @Option
        public MenuItem about;
    }
}