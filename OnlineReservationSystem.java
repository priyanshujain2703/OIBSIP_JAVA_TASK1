import java.util.*;

class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Reservation {
    String passengerName, trainName, source, destination, date, classType, pnr;

    Reservation(String passengerName, String trainName, String source, String destination, String date, String classType, String pnr) {
        this.passengerName = passengerName;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.classType = classType;
        this.pnr = pnr;
    }
}

    public class OnlineReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static Map<String, Reservation> reservations = new HashMap<>();

    public static void main(String[] args) {
        // Default user
        users.add(new User("admin", "1234"));

        System.out.println("=== Online Reservation System ===");

        int choice;
        do {
            System.out.println("\n1. Login");
            System.out.println("2. Register New User");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    if (login()) {
                        loggedInMenu(); // menu after login
                    } else {
                        System.out.println("Login Failed!");
                    }
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    System.out.println("Thank you for using the system.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    // Login method
    static boolean login() {
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        for (User u : users) {
            if (u.username.equals(uname) && u.password.equals(pass)) {
                System.out.println("Login successful!");
                return true;
            }
        }
        return false;
    }

    // Register new user
    static void registerUser() {
        System.out.print("Choose a username: ");
        String uname = sc.nextLine();

        // check if username already exists
        for (User u : users) {
            if (u.username.equals(uname)) {
                System.out.println("Username already taken! Try another.");
                return;
            }
        }

        System.out.print("Choose a password: ");
        String pass = sc.nextLine();

        users.add(new User(uname, pass));
        System.out.println("User registered successfully! Now you can login.");
    }

    // Menu shown after login
    static void loggedInMenu() {
        int choice;
        do {
            System.out.println("\n1. Book Ticket\n2. Cancel Ticket\n3. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: bookTicket(); break;
                case 2: cancelTicket(); break;
                case 3: System.out.println("Logged out."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    // Book a ticket
    static void bookTicket() {
        System.out.print("Enter passenger name: ");
        String name = sc.nextLine();
        System.out.print("Enter train name: ");
        String train = sc.nextLine();
        System.out.print("Enter source: ");
        String src = sc.nextLine();
        System.out.print("Enter destination: ");
        String dest = sc.nextLine();
        System.out.print("Enter date (dd-mm-yyyy): ");
        String date = sc.nextLine();
        System.out.print("Enter class type: ");
        String classType = sc.nextLine();

        String pnr = "PNR" + new Random().nextInt(10000);
        Reservation r = new Reservation(name, train, src, dest, date, classType, pnr);
        reservations.put(pnr, r);

        System.out.println("Ticket booked successfully! Your PNR: " + pnr);
    }

    // Cancel a ticket
    static void cancelTicket() {
        System.out.print("Enter PNR to cancel: ");
        String pnr = sc.nextLine();

        if (reservations.containsKey(pnr)) {
            reservations.remove(pnr);
            System.out.println("Ticket with PNR " + pnr + " cancelled successfully.");
        } else {
            System.out.println("Invalid PNR. Ticket not found.");
        }
    }
}
