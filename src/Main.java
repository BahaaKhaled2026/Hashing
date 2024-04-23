import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Prompt the user to choose the hashing method
            System.out.println("Choose a perfect hashing method:");
            System.out.println("1. O(N)");
            System.out.println("2. O(N^2)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mode = scanner.nextInt();
            switch (mode) {
                case 1:
                    PerfectHashTableON<String, String> dictionary;
                    System.out.println("Do you want to provide the size of a given set of elements? (y/n)");
                    char answer = scanner.next().charAt(0);
                    while (answer != 'y' && answer != 'n') {
                        System.out.println("Invalid input. Please enter 'y' or 'n'.");
                        answer = scanner.next().charAt(0);
                    }
                    if (answer == 'y') {
                        System.out.println("Enter the number of elements: ");
                        int numElements = scanner.nextInt();
                        dictionary = new PerfectHashTableON<>(numElements);
                    } else {
                        dictionary = new PerfectHashTableON<>();
                    }
                    boolean flag = true;
                    while (flag) {
                        System.out.println("\nChoose an operation:");
                        System.out.println("1. Insert");
                        System.out.println("2. Delete");
                        System.out.println("3. Search");
                        System.out.println("4. Display");
                        System.out.println("5. Batch Insert from file");
                        System.out.println("6. Batch Delete from file");
                        System.out.println("7. Exit");

                        int choice = scanner.nextInt();
                        while (choice < 1 || choice > 7) {
                            System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                            choice = scanner.nextInt();
                        }
                        switch (choice) {
                            case 1:
                                System.out.println("Enter Element to insert:");
                                String insertElement = scanner.next();
                                dictionary.put(insertElement, insertElement);
                                System.out.println("Collisions: " + dictionary.getCollisions());
                                break;
                            case 2:
                                System.out.println("Enter Element to delete:");
                                String deleteElement = scanner.next();
                                if(dictionary.remove(deleteElement)){
                                    System.out.println("Element deleted successfully.");
                                }
                                else{
                                    System.out.println("Element not found.");
                                }
                                break;
                            case 3:
                                System.out.println("Enter Element to search:");
                                String searchElement = scanner.next();
                                String value = dictionary.get(searchElement);
                                if (value != null) {
                                    System.out.println("True");
                                } else {
                                    System.out.println("False");
                                }
                                break;
                            case 4:
                                System.out.println("Hash table contents:");
                                dictionary.print();
                                break;
                            case 5:
                                scanner.nextLine();
                                System.out.println("Enter file path for batch insert: ");
                                String insertFilePath = scanner.nextLine();
                                Path path = Paths.get(insertFilePath);
                                while (!path.toFile().exists()) {
                                    System.out.println("File does not exist. Please enter a valid file path.");
                                    insertFilePath = scanner.nextLine();
                                    path = Paths.get(insertFilePath);
                                }
                                BatchOperations.batchInsert(insertFilePath, dictionary);
                                break;
                            case 6:
                                scanner.nextLine();
                                System.out.println("Enter file path for batch delete: ");
                                String deleteFilePath = scanner.nextLine();
                                Path path2 = Paths.get(deleteFilePath);
                                while (!path2.toFile().exists()) {
                                    System.out.println("File does not exist. Please enter a valid file path.");
                                    deleteFilePath = scanner.nextLine();
                                    path2 = Paths.get(deleteFilePath);
                                }
                                BatchOperations.batchDelete(deleteFilePath, dictionary);
                                break;
                            case 7:
                                flag = false;
                                break;
                            default:
                        }
                    }
                    break;
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
                                    if (!perfectHashTableON2.search(searchElement).equals("")) {
                                        System.out.println("We found \" " + perfectHashTableON2.search(searchElement) + " \"");
                                    } else {
                                        System.out.println("Element not found");
                                    }
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
                        boolean flag2 = true;
                        while (flag2) {
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
                                    if (!perfectHashTableON2.search(searchElement).equals("")) {
                                        System.out.println("We found \" " + perfectHashTableON2.search(searchElement) + " \"");
                                    } else {
                                        System.out.println("Element not found");
                                    }
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
                                    flag2 = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                            }
                        }
                    } else {
                        System.out.println("Invalid choice. Please choose 1 or 2 or 3.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose 1 or 2.");
            }
        }
    }
}
