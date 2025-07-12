package platform.users;

import collections.managers.PageCollectionManager;
import collections.managers.UserCollectionManager;

public class CreatorFactory {

    public static final int USER = 1;
    public static final int PAGE = 2;

    public static void addCreator(int type, String name, String contact) {
        switch (type) {
            case USER -> {
                User user = new User(name, contact);
                UserCollectionManager.getInstance().add(user);
            }
            case PAGE -> {
                Page page = new Page(name, contact);
                PageCollectionManager.getInstance().add(page);
            }
        }
    }
}
