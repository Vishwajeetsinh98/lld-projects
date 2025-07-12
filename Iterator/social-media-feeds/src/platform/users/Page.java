package platform.users;

public class Page extends Creator {
    private final String handler;
    public Page(String name, String handler) {
        super(name);
        this.handler = handler;
    }

    public String getHandler() {return this.handler;}

    @Override
    public String toString() {
        return "[PAGE]"+
                "\nName: " + name +
                "\nHandlerName: " + handler +
                "\nNumFollowers: " + followers.size() +
                "\n===========================================================";
    }

}
