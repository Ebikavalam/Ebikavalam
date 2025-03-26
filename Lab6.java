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
    private int uploadedByUserId;

    public FileDocument(int documentId, String name, String description, String type, String associatedEntity,
                         String uploadDate, int fileSize, String fileURL, String status, int uploadedByUserId) {
        super(name, description);

        if (documentId < 1000 || documentId > 9999) {
            this.documentId = 0;
            System.out.println("Invalid Document ID provided. Setting to 0.");
        } else {
            this.documentId = documentId;
        }

        if (type.isEmpty() || (!type.equals("Invoice") && !type.equals("Agreement") && !type.equals("Proposal"))) {
            this.type = "Unknown";
            System.out.println("Invalid type provided. Type will be 'Unknown'.");
        } else {
            this.type = type;
        }

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
            System.out.println("Invalid file size provided. File size will be 0 KB.");
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
            this.status = "Unknown";
            System.out.println("Invalid status provided. Status will be 'Unknown'.");
        } else {
            this.status = status;
        }

        if (uploadedByUserId <= 0) {
            this.uploadedByUserId = 0;
            System.out.println("Invalid user ID provided. User ID will be 0.");
        } else {
            this.uploadedByUserId = uploadedByUserId;
        }
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
        System.out.println("Uploaded By User ID: " + uploadedByUserId);
    }
}

public class Lab6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of documents to input: ");
        int numDocuments = scanner.nextInt();
        scanner.nextLine();

        FileDocument[] documents = new FileDocument[numDocuments];

        for (int i = 0; i < numDocuments; i++) {
            System.out.println("\nEnter details for document " + (i + 1) + ":");

            int documentId;
            do {
                System.out.print("Document ID (4-digits): ");
                documentId = scanner.nextInt();
                if (documentId < 1000 || documentId > 9999) {
                    System.out.println("Invalid ID. Please enter a 4-digit ID.");
                }
            } while (documentId < 1000 || documentId > 9999);

            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.println("Uppercase Name: " + name.toUpperCase());
            System.out.println("Name Length: " + name.length());

            System.out.print("Description: ");
            String description = scanner.nextLine();
            System.out.println("Substring of Description (first 5 chars): " + 
                (description.length() >= 5 ? description.substring(0, 5) : description));

            System.out.print("Type (Invoice/Contract/Proposal): ");
            String type = scanner.nextLine();
            type = type.replace("Contract", "Agreement");
            System.out.println("Modified Type: " + type);

            System.out.print("Associated Entity: ");
            String associatedEntity = scanner.nextLine();
            
            System.out.print("Upload Date: ");
            String uploadDate = scanner.nextLine();
            StringBuffer sbDate = new StringBuffer(uploadDate);
            uploadDate = sbDate.reverse().toString();
            System.out.println("Reversed Upload Date: " + uploadDate);

            sbDate.insert(0, "Date: ");
            System.out.println("Inserted Date Prefix: " + sbDate.toString());

            System.out.print("File Size (in KB): ");
            int fileSize = scanner.nextInt();
            scanner.nextLine();

            System.out.print("File URL: ");
            String fileURL = scanner.nextLine();
            StringBuffer sbUrl = new StringBuffer(fileURL);
            fileURL = sbUrl.append("/download").toString();
            System.out.println("Modified File URL: " + fileURL);

            if (sbUrl.length() > 5) {
                sbUrl.delete(0, 5);
                System.out.println("File URL after Deletion: " + sbUrl.toString());
            }

            System.out.print("Status (Active/Archived): ");
            String status = scanner.nextLine();
            
            System.out.print("Enter a status message: ");
            String statusMessage = scanner.nextLine();
            StringBuffer sbStatus = new StringBuffer(statusMessage);

            sbStatus.replace(0, Math.min(5, sbStatus.length()), "Updated");
            System.out.println("Replaced Status Message: " + sbStatus.toString());

            System.out.print("Uploaded By User ID: ");
            int uploadedByUserId = scanner.nextInt();
            scanner.nextLine();

            documents[i] = new FileDocument(documentId, name, description, type, associatedEntity, 
                uploadDate, fileSize, fileURL, status, uploadedByUserId);
        }

        System.out.println("\nDocument List:");
        for (FileDocument document : documents) {
            document.displayDetails();
        }

        scanner.close();
    }
}
