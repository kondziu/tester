package tt.gui;

import java.awt.event.ActionEvent;
import java.util.Optional;
import java.util.function.Consumer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class GuiElement extends AbstractAction {
    Consumer<ActionEvent> action;
    GuiElement(String label, String shortDescription, Optional<KeyStroke> keyStroke, Consumer<ActionEvent> action){
        super(label, null);
        this.putValue(Action.SHORT_DESCRIPTION, shortDescription);
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action.accept(e);
    }

    public static final JButton button(String label, String shortDescription, Optional<KeyStroke> keyStroke, Consumer<ActionEvent> action) {
        var element = new GuiElement(label, shortDescription, keyStroke, action);
        var button = new JButton(element);
        keyStroke.ifPresent(stroke -> 
          button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(stroke, label + "Action")
        );
        return button;
    }
}

// HelpAction helpMeAction = new HelpAction();
// this.helpMeButton = new JButton(helpMeAction);	
// helpMeButton.getActionMap().put("helpMeAction", helpMeAction);
// this.configurationAndSettings.keys.help.ifPresent(stroke -> 
//     helpMeButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(stroke, "helpMeAction")
// );
// helpMeAction.putValue(Action.SHORT_DESCRIPTION, "Help me!");