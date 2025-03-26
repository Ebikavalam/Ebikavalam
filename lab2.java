import java.util.*;
class Customer {
    private int id;
    public String name, email, address, customerType, status, contactMethod, notes;
    public long phoneNumber;
    public String date; 

    public void enterDetails() {
        Scanner scanner = new Scanner(System.in);
        String emailFormat = "^[a-zA-Z0-9.]+@[a-zA-Z0-9]+\\.com$";

        while (true) {
            System.out.print("Enter Customer ID (5 digits): ");
            id = scanner.nextInt();
            if (id >= 10000 && id < 100000) {
                break;
            } else {
                System.out.println("Invalid ID. Please enter a 5-digit ID.");
            }
        }

        scanner.nextLine();

        System.out.print("Enter Customer Name: ");
        name = scanner.nextLine();

        while (true) {
            System.out.print("Enter Email ID: ");
            email = scanner.nextLine();
            if (Pattern.matches(emailFormat, email)) {
                break;
            } else {
                System.out.println("Invalid Email ID. Please use a valid format (e.g., example@domain.com).");
            }
        }

        while (true) {
            System.out.print("Enter Mobile Number (10 digits): ");
            phoneNumber = scanner.nextLong();
            if (phoneNumber >= 1000000000L && phoneNumber <= 9999999999L) {
                break;
            } else {
                System.out.println("Invalid Mobile Number. Please enter a 10-digit number.");
            }
        }

        scanner.nextLine(); 

        System.out.print("Enter Address: ");
        address = scanner.nextLine();
        System.out.print("Enter Customer Type: ");
        customerType = scanner.nextLine();
        System.out.print("Enter Status(Active/Inaxtive): ");
        status = scanner.nextLine();
        System.out.print("Enter Preferred Contact Method: ");
        contactMethod = scanner.nextLine();
        System.out.print("Enter Additional Notes: ");
        notes = scanner.nextLine();
        System.out.print("Enter Date (dd/mm/yyyy): ");
        date = scanner.nextLine();
    }

    public void displayDetails() {
        System.out.println("\n--- Customer Details ---");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("Customer Type: " + customerType);
        System.out.println("Status: " + status);
        System.out.println("Preferred Contact Method: " + contactMethod);
        System.out.println("Notes: " + notes);
        System.out.println("Date: " + date);
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.enterDetails();
        customer.displayDetails();
    }
}
