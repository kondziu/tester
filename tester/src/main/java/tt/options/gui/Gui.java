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
    public Element start;
    
    @Option
    public Element help;

    @Option
    public Element ok;
}