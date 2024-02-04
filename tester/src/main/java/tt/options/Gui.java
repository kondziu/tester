package tt.options;

import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;
import tt.gui.KeyStrokeConvereter;

import java.util.Optional;

import javax.swing.KeyStroke;

import tt.config.annotations.AbstractProperties;
import tt.config.annotations.From;

@From(file="tt/options/gui.properties")
public class Gui extends AbstractProperties {

    public Gui() throws ConfigException, AnnotationException {}

    public static class GuiElement extends AbstractProperties {

        public GuiElement() throws ConfigException, AnnotationException {}

        @Option
        public String text;

        @Option
        public String description;
        
        @Option(converter = KeyStrokeConvereter.class)
        public Optional<KeyStroke> key;

        @Option
        public String mnemonic;
    }

    @Option
    public GuiElement help;
}