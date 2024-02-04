package tt.gui;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;

import tt.options.gui.Element;

public class GuiElement {

    static class Action extends AbstractAction {
        Consumer<ActionEvent> actionLogic;

        Action (Element element, Consumer<ActionEvent> actionLogic) {
            super(element.label, null); // TODO icon
            this.putValue(Action.SHORT_DESCRIPTION, element.description);
            this.actionLogic = actionLogic;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.actionLogic.accept(e);
        }
    }

    public static JButton from(Element element, Consumer<ActionEvent> actionLogic) {
        var action = new GuiElement.Action(element, actionLogic);
        var button = new JButton(action);

        button.getActionMap().put(element.actionLabel(), action);
        
        element.mnemonic.ifPresent(button::setMnemonic);

        element.key.ifPresent(key -> {
            var inputs = button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            inputs.put(key, element.actionLabel());
        });

        return button;
    }
}