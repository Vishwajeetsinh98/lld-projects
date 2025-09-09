package privacy;

import interfaces.PrivacyController;
import users.User;

public abstract class PrivacyControlled {

    protected PrivacyType privacyType;
    protected PrivacyControlled parent;
    protected final PrivacyController privacyController;

    public PrivacyControlled(PrivacyControlled parent, PrivacyController privacyController) {
        privacyType = PrivacyType.PUBLIC;
        this.parent = parent;
        this.privacyController = privacyController;
    }

    public PrivacyControlled(PrivacyController privacyController) {
        this(null, privacyController);
    }

    public PrivacyType getPrivacyType() { return privacyType; }

    public void setPrivacyType(PrivacyType privacyType) { this.privacyType = privacyType; }

    public boolean check(User viewer) {
        if (privacyType == PrivacyType.PUBLIC && parent == null) {
            return true;
        }
        if (privacyType == PrivacyType.PRIVATE) {
            if (!privacyController.checkHasPermissions(this, viewer)) {
                System.out.println("[PrivacyControlled] " + viewer.getUsername() + " doesn't have access to this content");
                return false;
            }
        }
        return parent == null || parent.check(viewer);
    }

    public void render(User viewer, int tabs) {
        if (!check(viewer))
            throw new IllegalArgumentException("User " + viewer + " cannot see this content");
    }

    public void render(User viewer) {
        if (!check(viewer))
            throw new IllegalArgumentException("User " + viewer + " cannot see this content");
    }

    public PrivacyControlled getParent() {
        return parent;
    }
}
