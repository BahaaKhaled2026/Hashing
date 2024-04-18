import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example keys
        int[] keys = {10, 20, 30, 40, 50};

        // Prompt the user to choose the hashing method
        System.out.println("Choose a perfect hashing method:");
        System.out.println("1. O(N)");
        System.out.println("2. O(N^2)");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Use O(N) method
                PerfectHashTableON hashTableON = new PerfectHashTableON(keys);
                hashTableON.createPerfectHashTable();
                hashTableON.printHashTable();
                break;
            case 2:
                // Use O(N^2) method
                PerfectHashTableON2 hashTableON2 = new PerfectHashTableON2(keys);
                hashTableON2.createPerfectHashTable();
                hashTableON2.printHashTable();
                break;
            default:
                System.out.println("Invalid choice. Please choose 1 or 2.");
        }
    }
}
