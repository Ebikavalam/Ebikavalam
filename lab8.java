import java.util.*;

class Account {
    private int accountId;
    private String organizationName;
    private String industry;
    private double annualRevenue;
    private String contactList;
    private String address;
    private String website;
    private String status;
    private int numberOfEmployees;
    private String notes;

    public Account(int accountId, String organizationName, String industry, double annualRevenue, String contactList,
               String address, String website, String status, int numberOfEmployees, String notes) {
        if (accountId <= 0) throw new IllegalArgumentException("Account ID must be positive.");
        if (organizationName.isEmpty()) throw new IllegalArgumentException("Organization Name cannot be empty.");
        if (industry.isEmpty()) throw new IllegalArgumentException("Industry cannot be empty.");
        if (annualRevenue < 0) throw new IllegalArgumentException("Annual Revenue cannot be negative.");
        if (numberOfEmployees < 0) throw new IllegalArgumentException("Number of Employees cannot be negative.");
        if (!status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Inactive")) {
            throw new IllegalArgumentException("Status must be either 'Active' or 'Inactive'.");
        }
        this.accountId = accountId;
        this.organizationName = organizationName;
        this.industry = industry;
        this.annualRevenue = annualRevenue;
        this.contactList = contactList;
        this.address = address;
        this.website = website;
        this.status = status;
        this.numberOfEmployees = numberOfEmployees;
        this.notes = notes;
    }

    public synchronized void displayAccount() {
        System.out.println("\nAccount ID: " + accountId);
        System.out.println("Organization Name: " + organizationName);
        System.out.println("Industry: " + industry);
        System.out.println("Annual Revenue: " + annualRevenue);
        System.out.println("Contact List: " + contactList);
        System.out.println("Address: " + address);
        System.out.println("Website: " + website);
        System.out.println("Status: " + status);
        System.out.println("Number of Employees: " + numberOfEmployees);
        System.out.println("Notes: " + notes);
    }
}

class DocumentProcessor extends Thread {
    private Account account;

    public DocumentProcessor(Account account) {
        this.account = account;
    }

    
    public void run() {
        System.out.println("Processing document for Account: " + account);
        account.displayAccount();
    }
}

class DocumentDisplayer implements Runnable {
    private Account account;

    public DocumentDisplayer(Account account) {
        this.account = account;
    }

    
    public void run() {
        account.displayAccount();
    }
}

public class lab8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of accounts to input: ");
        int numAccounts = scanner.nextInt();
        scanner.nextLine();
        
        Account[] accounts = new Account[numAccounts];

        for (int i = 0; i < numAccounts; i++) {
            System.out.println("Enter details for account " + (i + 1) + ":");
            
            int accountId;
            while (true) {
                System.out.print("Account ID: ");
                if (scanner.hasNextInt()) {
                    accountId = scanner.nextInt();
                    scanner.nextLine();
                    if (accountId > 0) break;
                }
                System.out.println("Invalid input. Account ID must be a positive integer.");
                scanner.nextLine();
            }

            System.out.print("Organization Name: ");
            String organizationName = scanner.nextLine();
            
            System.out.print("Industry: ");
            String industry = scanner.nextLine();
            
            double annualRevenue;
            while (true) {
                System.out.print("Annual Revenue: ");
                if (scanner.hasNextDouble()) {
                    annualRevenue = scanner.nextDouble();
                    scanner.nextLine();
                    if (annualRevenue >= 0) break;
                }
                System.out.println("Invalid input. Annual Revenue cannot be negative.");
                scanner.nextLine();
            }

            System.out.print("Contact List: ");
            String contactList = scanner.nextLine();
            
            System.out.print("Address: ");
            String address = scanner.nextLine();
            
            System.out.print("Website: ");
            String website = scanner.nextLine();
            
            String status;
            while (true) {
                System.out.print("Status (Active/Inactive): ");
                status = scanner.nextLine();
                if (status.equalsIgnoreCase("Active") || status.equalsIgnoreCase("Inactive")) break;
                System.out.println("Invalid input. Status must be 'Active' or 'Inactive'.");
            }
            
            int numberOfEmployees;
            while (true) {
                System.out.print("Number of Employees: ");
                if (scanner.hasNextInt()) {
                    numberOfEmployees = scanner.nextInt();
                    scanner.nextLine();
                    if (numberOfEmployees >= 0) break;
                }
                System.out.println("Invalid input. Number of Employees cannot be negative.");
                scanner.nextLine();
            }
            
            System.out.print("Notes: ");
            String notes = scanner.nextLine();

            accounts[i] = new Account(accountId, organizationName, industry, annualRevenue, contactList, address, website, status, numberOfEmployees, notes);
        }
        
        System.out.println("\nProcessing and Displaying Accounts...");
        
        for (Account acc : accounts) {
            DocumentProcessor processor = new DocumentProcessor(acc);
            Thread displayerThread = new Thread(new DocumentDisplayer(acc));
            
            processor.setPriority(Thread.MAX_PRIORITY);
            displayerThread.setPriority(Thread.MIN_PRIORITY);
            
            processor.start();
            displayerThread.start();
            
            
        }

        scanner.close();
    }
}
