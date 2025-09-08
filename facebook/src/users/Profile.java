package users;

import interfaces.PrivacyController;
import privacy.PrivacyControlled;
import privacy.PrivacyType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Profile extends PrivacyControlled {
    private String name;
    private String currentAddress;
    private String phone;
    private String email;

    private final List<WorkExperience> experiences;
    private final List<Education> educations;
    private final List<Address> addresses;

    public Profile(User user) {
        super((PrivacyController) user);
        experiences = new ArrayList<>();
        educations = new ArrayList<>();
        addresses = new ArrayList<>();
        setPrivacyType(PrivacyType.PRIVATE);
    }

    @Override
    public void render(User viewer) {
        super.render(viewer);
        System.out.println("XXXXXXXXXX Rendering profile XXXXXXXXXX");

        System.out.println("Name: " + name);
        System.out.println("Current Address: " + currentAddress);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Work experiences:");
        for (WorkExperience experience : experiences) {
            System.out.println(experience.company());
            System.out.println("\t" + experience.title());
            System.out.println(experience.start() + " - " + experience.end());
            System.out.println("--------------------------------------");
        }

        System.out.println("\nAlso lived in:");
        for (Address address : addresses) {
            System.out.println(address.city);
            System.out.println(address.start() + " - " + address.end());
            System.out.println("--------------------------------------");
        }

        System.out.println("\nEducation:");
        for (Education education : educations) {
            System.out.println(education.school);
            System.out.println("\t" + education.program);
            System.out.println(education.start() + " - " + education.end());
            System.out.println("--------------------------------------");
        }

        System.out.println("==========================================================");
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCurrentAddress() { return currentAddress; }

    public void setCurrentAddress(String currentAddress) { this.currentAddress = currentAddress; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<WorkExperience> getExperiences() { return experiences; }
    public void addWorkExperience(String company, String title, LocalDate start, LocalDate end) {
        experiences.add(new WorkExperience(company, title, start, end));
    }

    public void removeWorkExperience(WorkExperience experience) {
        experiences.remove(experience);
    }

    public List<Education> getEducations() { return educations; }
    public void addEducation(String school, String program, LocalDate start, LocalDate end) {
        educations.add(new Education(school, program, start, end));
    }

    public void removeEducation(Education education) {
        educations.remove(education);
    }

    public List<Address> getAddresses() { return addresses; }
    public void addAddress(String city, LocalDate start, LocalDate end) {
        addresses.add(new Address(city, start, end));
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
    }

    public record WorkExperience(String company, String title, LocalDate start, LocalDate end) {}
    public record Education(String school, String program, LocalDate start, LocalDate end) {}
    public record Address(String city, LocalDate start, LocalDate end) {}

}
