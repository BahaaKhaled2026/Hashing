import java.util.Random;
import java.lang.Math;

public class PerfectHashTableON2 {

    private int[] keys; // Array to store keys
    private int size; // Size of the hash table
    int[][] hashMatrix; // Hash matrix for the hashing

    public PerfectHashTableON2(int[] keys) {
        this.keys = keys;
        this.size = keys.length * keys.length; // Table size is quadratic in the size of keys
        this.hashMatrix = HashingFunctions.generateHashMatrix((int) Math.ceil(Math.log(keys.length) / Math.log(2)));
    }

    // Method to perform perfect hashing using the O(N^2) method
    public void createPerfectHashTable() {
        boolean collision;
        do {
            collision = false;
            int[][] hashFunction = HashingFunctions.generateHashMatrix((int) Math.ceil(Math.log(size) / Math.log(2))); // Generate random hash function
            int[] table = new int[size];
            for (int key : keys) {
                int hashValue = HashingFunctions.multiplyMatrix(hashFunction, HashingFunctions.decimalToBinary(key)) % size;
                if (table[hashValue] != 0) {
                    collision = true;
                    break;
                }
                table[hashValue] = key;
            }
            if (!collision) {
                // Store the hash function and table if no collision
                this.hashMatrix = hashFunction;
            }
        } while (collision);
    }

    // Method to print the hash table
    public void printHashTable() {
        if (hashMatrix == null) {
            System.out.println("Hash table is not created yet!");
            return;
        }
        System.out.println("Hash Table:");
        for (int i = 0; i < hashMatrix[0].length; i++) {
            System.out.print(hashMatrix[0][i] + " ");
        }
        System.out.println();
    }
}
