package tt.options.gui;

import java.util.UUID;

public class Button extends AbstractElement {
    // Memoized unique label.
    public String uniqueLabel;

    // Generates a unique label which is then memoized.
    public String actionLabel() {
        if (uniqueLabel==null) {
            this.uniqueLabel = this.label + "Action@" + UUID.randomUUID();
        }
        return uniqueLabel;
    }
}
