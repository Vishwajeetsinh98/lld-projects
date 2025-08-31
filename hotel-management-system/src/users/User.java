package users;

import booking.Booking;
import hotel.Branch;
import hotel.RoomType;
import system.HMSProxy;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    protected final String userName;
    protected final String password;
    protected final UserRole role;
    protected final HMSProxy hotelManagementSystem;

    public User(String userName, String password, UserRole role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.hotelManagementSystem = HMSProxy.getInstance();
    }

    public UserRole getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }

    public boolean checkPassword(String entered) {
        return entered.equals(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        return userName.equals(((User) o).userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
