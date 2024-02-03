package tt.options;

import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;

import java.util.Set;

import tt.config.annotations.From;

@From(file="tt/options/gui.properties")
public class Gui implements Properties {

    public static class GuiElement implements Properties {
        @Option
        public String text;

        @Option
        public String description;
    }

    public Gui() throws IllegalAccessException, ConfigException, AnnotationException {
        this.initializeProperties();
    }

    @Option
    public GuiElement help;
}