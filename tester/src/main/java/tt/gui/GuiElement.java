package tt.gui;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import tt.options.gui.AbstractElement;
import tt.options.gui.Button;
import tt.options.gui.CheckedMenuItem;
import tt.options.gui.Menu;
import tt.options.gui.MenuItem;

public class GuiElement {

    static class Action extends AbstractAction {
        Consumer<ActionEvent> actionLogic;

        Action (AbstractElement element, Consumer<ActionEvent> actionLogic) {
            super(element.label, null); // TODO icon
            this.putValue(Action.SHORT_DESCRIPTION, element.description);
            this.actionLogic = actionLogic;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.actionLogic.accept(e);
        }
    }

    public static JButton from(Button element, Consumer<ActionEvent> actionLogic) {
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

    public static JMenuItem from(MenuItem element, Consumer<ActionEvent> actionLogic) {
        var action = new GuiElement.Action(element, actionLogic);
        var item = new JMenuItem(action);

        element.key.ifPresent(item::setAccelerator);
        element.mnemonic.ifPresent(item::setMnemonic);

        return item;
    }

    public static JCheckBoxMenuItem from(CheckedMenuItem element, Consumer<ActionEvent> actionLogic) {
        var action = new GuiElement.Action(element, actionLogic);
        var item = new JCheckBoxMenuItem(action);

        element.key.ifPresent(item::setAccelerator);
        element.mnemonic.ifPresent(item::setMnemonic);

        return item;
    }

    public static JCheckBoxMenuItem from(CheckedMenuItem element, Consumer<ActionEvent> actionLogic, String actionCommand) {
        var action = new GuiElement.Action(element, actionLogic);
        var item = new JCheckBoxMenuItem(action);

        element.key.ifPresent(item::setAccelerator);
        element.mnemonic.ifPresent(item::setMnemonic);

        item.setActionCommand(actionCommand);

        return item;
    }

    public static JMenu from(Menu element) {
        var menu = new JMenu();

        menu.setText(element.label);
        menu.setToolTipText(element.description);

        element.key.ifPresent(menu::setAccelerator);
        element.mnemonic.ifPresent(menu::setMnemonic);

        return menu;
    }
}