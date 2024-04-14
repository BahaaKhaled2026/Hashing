import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Select the perfect hashing method:");
            System.out.println("1. O(N^2) Space");
            System.out.println("2. O(N) Space");
            System.out.print("Enter your choice (1 or 2): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid choice. Please enter either 1 or 2.");
                System.out.println("Select the perfect hashing method:");
                System.out.println("1. O(N^2) Space");
                System.out.println("2. O(N) Space");
                System.out.print("Enter your choice (1 or 2): ");
                scanner.next(); // Consume the invalid input
            }
            choice = scanner.nextInt();

            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice. Please enter either 1 or 2.");
            }
        } while (choice != 1 && choice != 2);

        PerfectHashTable hashTable = null;

        if (choice == 1) {
            System.out.println("You have selected O(N^2) Space.");
            int[] keys = {5, 10, 15, 20}; // Sample keys
            hashTable = new Linear(keys);
        } else if (choice == 2) {
            System.out.println("You have selected O(N) Space.");
            int[] keys = {5, 10, 15, 2}; // Sample keys
            hashTable = new Quadratic(keys);
        }

        Dictionary dictionary = new Dictionary(hashTable);
        operateDictionary(scanner, dictionary);

        if (hashTable instanceof Linear) {
            Linear linearHashTable = (Linear) hashTable;
            System.out.println("Space usage: " + linearHashTable.getSpaceUsage() + " bytes");
            System.out.println("Number of collisions: " + linearHashTable.getCollisionCount());
        } else if (hashTable instanceof Quadratic) {
            Quadratic quadraticHashTable = (Quadratic) hashTable;
            System.out.println("Space usage: " + quadraticHashTable.getSpaceUsage() + " bytes");
            System.out.println("Number of collisions: " + quadraticHashTable.getCollisionCount());
        }
    }

    private static void operateDictionary(Scanner scanner, Dictionary dictionary) {
        while (true) {
            System.out.println("\nSelect operation:");
            System.out.println("1. Search");
            System.out.println("2. Insert");
            System.out.println("3. Delete");
            System.out.println("4. Batch Insert");
            System.out.println("5. Batch Delete");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int operation;
            while (true) {
                try {
                    operation = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    break; // Exit the loop if input is valid
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); // Consume invalid input
                }
            }

            switch (operation) {
                case 1:
                    System.out.print("Enter key to search: ");
                    String keyToSearch = scanner.nextLine();
                    boolean found = dictionary.search(keyToSearch);
                    System.out.println("Key found: " + found);
                    break;
                case 2:
                    System.out.print("Enter key to insert: ");
                    String keyToInsert = scanner.nextLine();
                    dictionary.insert(keyToInsert);
                    System.out.println("Key inserted.");
                    break;
                case 3:
                    System.out.print("Enter key to delete: ");
                    String keyToDelete = scanner.nextLine();
                    dictionary.delete(keyToDelete);
                    System.out.println("Key deleted.");
                    break;
                case 4:
                    System.out.print("Enter path to file containing keys to insert: ");
                    String insertFilePath = scanner.nextLine();
                    dictionary.batchInsert(insertFilePath);
                    System.out.println("Batch insert completed.");
                    break;
                case 5:
                    System.out.print("Enter path to file containing keys to delete: ");
                    String deleteFilePath = scanner.nextLine();
                    dictionary.batchDelete(deleteFilePath);
                    System.out.println("Batch delete completed.");
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid operation.");
            }
        }
    }
}
