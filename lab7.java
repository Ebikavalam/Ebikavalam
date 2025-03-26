import java.util.*;

abstract class Document {
    protected String name;
    protected String description;

    public Document(String name, String description) {
        if (name.isEmpty()) {
            this.name = "Unknown";
            System.out.println("Name must be filled");
        } else {
            this.name = name;
        }

        if (description.isEmpty()) {
            this.description = "Unknown";
            System.out.println("Description must be filled");
        } else {
            this.description = description;
        }
    }

    public abstract void displayDetails();

    public final void displayName() {
        System.out.println("Name : " + name);
    }
}

class FileDocument extends Document {
    private int documentId;
    private String type;
    private String associatedEntity;
    private String uploadDate;
    private int fileSize;
    private String fileURL;
    private String status;

    public FileDocument(int documentId, String name, String description, String type, String associatedEntity,
                         String uploadDate, int fileSize, String fileURL, String status) 
                         throws IllegalArgumentException {
        super(name, description);

        if (documentId < 1000 || documentId > 9999) {
            throw new IllegalArgumentException("Document ID must be a 4-digit number.");
        }
        this.documentId = documentId;

        if (type.isEmpty() || (!type.equals("Invoice") && !type.equals("Contract") && !type.equals("Proposal"))) {
            throw new IllegalArgumentException("Invalid type. Choose Invoice, Contract, or Proposal.");
        }
        this.type = type;

        if (associatedEntity.isEmpty()) {
            this.associatedEntity = "Not specified";
            System.out.println("Invalid associated entity provided. Associated entity will be 'Not specified'.");
        } else {
            this.associatedEntity = associatedEntity;
        }

        if (uploadDate.isEmpty()) {
            this.uploadDate = "Not specified";
            System.out.println("Invalid upload date provided. Upload date will be 'Not specified'.");
        } else {
            this.uploadDate = uploadDate;
        }

        if (fileSize <= 0) {
            this.fileSize = 0;
            System.out.println("Invalid file size provided. File size will be set to 0 KB.");
        } else {
            this.fileSize = fileSize;
        }

        if (fileURL.isEmpty()) {
            this.fileURL = "Not specified";
            System.out.println("Invalid file URL provided. File URL will be 'Not specified'.");
        } else {
            this.fileURL = fileURL;
        }

        if (status.isEmpty() || (!status.equals("Active") && !status.equals("Archived"))) {
            throw new IllegalArgumentException("Invalid status. Choose Active or Archived.");
        }
        this.status = status;
    }

    public void displayDetails() {
        displayName();
        System.out.println("Document ID: " + documentId);
        System.out.println("Description: " + description);
        System.out.println("Type: " + type);
        System.out.println("Associated Entity: " + associatedEntity);
        System.out.println("Upload Date: " + uploadDate);
        System.out.println("File Size: " + fileSize + " KB");
        System.out.println("File URL: " + fileURL);
        System.out.println("Status: " + status);
    }
}

public class lab7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter the number of documents to input: ");
            int numDocuments = 0;
            while (true) {
                try {
                    numDocuments = scanner.nextInt();
                    if (numDocuments <= 0) throw new InputMismatchException();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a positive integer.");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();

            FileDocument[] documents = new FileDocument[numDocuments];

            for (int i = 0; i < numDocuments; i++) {
                try {
                    System.out.println("Enter details for document " + (i + 1) + ":");

                    int documentId;
                    while (true) {
                        try {
                            System.out.print("Document ID (4-digits): ");
                            documentId = scanner.nextInt();
                            if (documentId >= 1000 && documentId <= 9999) break;
                            System.out.println("Invalid ID. Please enter a 4-digit number.");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Enter a numeric 4-digit value.");
                            scanner.nextLine(); 
                        }
                    }
                    scanner.nextLine();

                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Description: ");
                    String description = scanner.nextLine();
                    
                    System.out.print("Type (Invoice/Contract/Proposal): ");
                    String type = scanner.nextLine();
                    
                    System.out.print("Associated Entity: ");
                    String associatedEntity = scanner.nextLine();
                    
                    System.out.print("Upload Date: ");
                    String uploadDate = scanner.nextLine();
                    
                    System.out.print("File Size (in KB): ");
                    int fileSize = scanner.nextInt();
                    scanner.nextLine();
                        
                    System.out.print("File URL: ");
                    String fileURL = scanner.nextLine();
                
                    System.out.print("Status (Active/Archived): ");
                    String status = scanner.nextLine();

                    documents[i] = new FileDocument(documentId, name, description, type, associatedEntity, uploadDate, fileSize, fileURL, status);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    i--; 
                }
            }

            System.out.println("\nDocument List:");
            for (FileDocument document : documents) {
                document.displayDetails();
            }
        } finally {
            scanner.close();
            System.out.println("Scanner closed.");    
        }
    }
}
