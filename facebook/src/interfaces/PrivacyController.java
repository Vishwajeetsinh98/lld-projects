package interfaces;

import privacy.PrivacyControlled;
import users.User;

public interface PrivacyController {
    boolean checkHasPermissions(PrivacyControlled subject, User viewer);
}
