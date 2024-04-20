import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to choose the hashing method
        System.out.println("Choose a perfect hashing method:");
        System.out.println("1. O(N)");
        System.out.println("2. O(N^2)");
        System.out.print("Enter your choice: ");
        int mode = scanner.nextInt();

        switch (mode) {
            case 1:
//                System.out.println("Do you want to provide the size of a given set of elements?");
//                System.out.println("1. Yes");
//                System.out.println("2. No");
//                System.out.print("Enter your choice: ");
//                int choice2 = scanner.nextInt();
//                if (choice2 == 1) {
//                    System.out.print("Enter the number of elements: ");
//                    int numElements = scanner.nextInt();
//                    PerfectHashTableON perfectHashTableON = new PerfectHashTableON(numElements);
//                    while (true) {
//                        System.out.println("\nChoose an operation:");
//                        System.out.println("1. Insert");
//                        System.out.println("2. Delete");
//                        System.out.println("3. Search");
//                        System.out.println("4. Display");
//                        System.out.println("5. Batch Insert from file");
//                        System.out.println("6. Batch Delete from file");
//                        System.out.println("7. Exit");
//
//                        int choice = scanner.nextInt();
//                        scanner.nextLine(); // Consume newline
//
//                        switch (choice) {
//                            case 1:
//                                System.out.println("Enter element to insert:");
//                                String insertElement = scanner.nextLine();
//                                perfectHashTableON.insert(insertElement);
//                                break;
//                            case 2:
//                                System.out.println("Enter element to delete:");
//                                String deleteElement = scanner.nextLine();
//                                perfectHashTableON.delete(deleteElement);
//                                break;
//                            case 3:
//                                System.out.println("Enter element to search:");
//                                String searchElement = scanner.nextLine();
//                                perfectHashTableON.search(searchElement);
//                                break;
//                            case 4:
//                                System.out.println("Hash table contents:");
//                                perfectHashTableON.display();
//                                break;
//                            case 5:
//                                System.out.println("Enter file path for batch insert:");
//                                String insertFilePath = scanner.nextLine();
//                                perfectHashTableON.batchInsertFromFile(insertFilePath);
//                                break;
//                            case 6:
//                                System.out.println("Enter file path for batch delete:");
//                                String deleteFilePath = scanner.nextLine();
//                                perfectHashTableON.batchDeleteFromFile(deleteFilePath);
//                                break;
//                            case 7:
//                                System.out.println("Exiting...");
//                                System.exit(0);
//                            default:
//                                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
//                        }
//                    }
//                } else if (choice2 == 2) {
//                    PerfectHashTableON2 perfectHashTableON2 = new PerfectHashTableON2();
//                    perfectHashTableON2.createPerfectHashTable();
//                    while (true) {
//                        System.out.println("\nChoose an operation:");
//                        System.out.println("1. Insert");
//                        System.out.println("2. Delete");
//                        System.out.println("3. Search");
//                        System.out.println("4. Display");
//                        System.out.println("5. Batch Insert from file");
//                        System.out.println("6. Batch Delete from file");
//                        System.out.println("7. Exit");
//
//                        int choice = scanner.nextInt();
//                        scanner.nextLine(); // Consume newline
//
//                        switch (choice) {
//                            case 1:
//                                System.out.println("Enter element to insert:");
//                                String insertElement = scanner.nextLine();
//                                perfectHashTableON2.insert(insertElement);
//                                break;
//                            case 2:
//                                System.out.println("Enter element to delete:");
//                                String deleteElement = scanner.nextLine();
//                                perfectHashTableON2.delete(deleteElement);
//                                break;
//                            case 3:
//                                System.out.println("Enter element to search:");
//                                String searchElement = scanner.nextLine();
//                                perfectHashTableON2.search(searchElement);
//                                break;
//                            case 4:
//                                System.out.println("Hash table contents:");
//                                perfectHashTableON2.display();
//                                break;
//                            case 5:
//                                System.out.println("Enter file path for batch insert:");
//                                String insertFilePath = scanner.nextLine();
//                                perfectHashTableON2.batchInsertFromFile(insertFilePath);
//                                break;
//                            case 6:
//                                System.out.println("Enter file path for batch delete:");
//                                String deleteFilePath = scanner.nextLine();
//                                perfectHashTableON2.batchDeleteFromFile(deleteFilePath);
//                                break;
//                            case 7:
//                                System.out.println("Exiting...");
//                                System.exit(0);
//                            default:
//                                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
//                        }
//                    }
//                } else {
//                    System.out.println("Invalid choice. Please choose 1 or 2.");
//                }
//                break;
            case 2:
                System.out.println("Do you want to provide the size of a given set of elements?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("Enter your choice: ");
                int choice2 = scanner.nextInt();
                if (choice2 == 1) {
                    System.out.print("Enter the number of elements: ");
                    int numElements = scanner.nextInt();
                    PerfectHashTableON2 perfectHashTableON2 = new PerfectHashTableON2<String>(numElements);
                    perfectHashTableON2.createPerfectHashTable();
                    while (true) {
                        System.out.println("\nChoose an operation:");
                        System.out.println("1. Insert");
                        System.out.println("2. Delete");
                        System.out.println("3. Search");
                        System.out.println("4. Display");
                        System.out.println("5. Batch Insert from file");
                        System.out.println("6. Batch Delete from file");
                        System.out.println("7. Exit");

                        int choice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (choice) {
                            case 1:
                                System.out.println("Enter element to insert:");
                                String insertElement = scanner.nextLine();
                                perfectHashTableON2.insert(insertElement);
                                break;
                            case 2:
                                System.out.println("Enter element to delete:");
                                String deleteElement = scanner.nextLine();
                                perfectHashTableON2.delete(deleteElement);
                                break;
                            case 3:
                                System.out.println("Enter element to search:");
                                String searchElement = scanner.nextLine();
                                perfectHashTableON2.search(searchElement);
                                break;
                            case 4:
                                System.out.println("Hash table contents:");
                                perfectHashTableON2.display();
                                break;
                            case 5:
                                System.out.println("Enter file path for batch insert:");
                                String insertFilePath = scanner.nextLine();
                                perfectHashTableON2.batchInsertFromFile(insertFilePath);
                                break;
                            case 6:
                                System.out.println("Enter file path for batch delete:");
                                String deleteFilePath = scanner.nextLine();
                                perfectHashTableON2.batchDeleteFromFile(deleteFilePath);
                                break;
                            case 7:
                                System.out.println("Exiting...");
                                System.exit(0);
                            default:
                                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                        }
                    }
                } else if (choice2 == 2) {
                    PerfectHashTableON2 perfectHashTableON2 = new PerfectHashTableON2<String>();
                    perfectHashTableON2.createPerfectHashTable();
                    while (true) {
                        System.out.println("\nChoose an operation:");
                        System.out.println("1. Insert");
                        System.out.println("2. Delete");
                        System.out.println("3. Search");
                        System.out.println("4. Display");
                        System.out.println("5. Batch Insert from file");
                        System.out.println("6. Batch Delete from file");
                        System.out.println("7. Exit");

                        int choice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (choice) {
                            case 1:
                                System.out.println("Enter element to insert:");
                                String insertElement = scanner.nextLine();
                                perfectHashTableON2.insert(insertElement);
                                break;
                            case 2:
                                System.out.println("Enter element to delete:");
                                String deleteElement = scanner.nextLine();
                                perfectHashTableON2.delete(deleteElement);
                                break;
                            case 3:
                                System.out.println("Enter element to search:");
                                String searchElement = scanner.nextLine();
                                perfectHashTableON2.search(searchElement);
                                break;
                            case 4:
                                System.out.println("Hash table contents:");
                                perfectHashTableON2.display();
                                break;
                            case 5:
                                System.out.println("Enter file path for batch insert:");
                                String insertFilePath = scanner.nextLine();
                                perfectHashTableON2.batchInsertFromFile(insertFilePath);
                                break;
                            case 6:
                                System.out.println("Enter file path for batch delete:");
                                String deleteFilePath = scanner.nextLine();
                                perfectHashTableON2.batchDeleteFromFile(deleteFilePath);
                                break;
                            case 7:
                                System.out.println("Exiting...");
                                System.exit(0);
                            default:
                                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                        }
                    }
                } else {
                    System.out.println("Invalid choice. Please choose 1 or 2.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please choose 1 or 2.");
        }
    }
}
