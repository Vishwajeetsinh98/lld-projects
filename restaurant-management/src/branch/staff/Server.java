package branch.staff;

import branch.Branch;
import system.User;
import system.UserRole;

public class Server implements User {
    // getCalledToTable
    // Take order
    // update order
    // notify kitchen
    // get notified by kitchen
    // create bill

    private final String name;
    private final UserRole userRole;
    private Branch branch;

    public Server(String name, Branch branch) {
        this.name = name;
        userRole = UserRole.SERVER;
        this.branch = branch;
    }

    public void giveMenu() {
        System.out.println("[Server] " + name + " showing menu.");
        branch.getMenu().showMenu();
    }



    @Override
    public UserRole getUserRole() { return userRole; }
    public String getName() { return name; }
    public void setBranch(Branch branch) { this.branch = branch; }
}
