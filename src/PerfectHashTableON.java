import java.util.ArrayList;
import java.util.Random;

public class PerfectHashTableON {

    private int[] keys; // Array to store keys
    private int size; // Size of the hash table
    private int[][] hashMatrix; // Hash matrix for the first-level hashing
    private Random random;

    public PerfectHashTableON(int[] keys) {
        this.keys = keys;
        this.size = keys.length;
        this.hashMatrix = HashingFunctions.generateHashMatrix((int) Math.ceil(Math.log(size) / Math.log(2)));
        this.random = new Random();
    }

    // Method to perform perfect hashing using the O(N) method
    public void createPerfectHashTable() {
        // First-level hashing
        int[][] firstLevelTable = new int[size][];
        for (int i = 0; i < keys.length; i++) {
            int hashValue = HashingFunctions.multiplyMatrix(hashMatrix, HashingFunctions.decimalToBinary(keys[i]))%size;
            if (firstLevelTable[hashValue] == null) {
                firstLevelTable[hashValue] = new int[]{keys[i]};
            } else {
                ArrayList<Integer> collisionList = new ArrayList<>();
                for (int k : firstLevelTable[hashValue]) {
                    collisionList.add(k);
                }
                collisionList.add(keys[i]);
                firstLevelTable[hashValue] = collisionList.stream().mapToInt(Integer::intValue).toArray();
            }
        }
        // Second-level hashing
        for (int i = 0; i < firstLevelTable.length; i++) {
            if (firstLevelTable[i] != null && firstLevelTable[i].length > 1) {
                PerfectHashTableON2 secondLevelTable = new PerfectHashTableON2(firstLevelTable[i]);
                secondLevelTable.createPerfectHashTable();
                firstLevelTable[i] = secondLevelTable.hashMatrix[0];
            }
        }
        // Store the result
        this.hashMatrix = firstLevelTable;
    }

    // Method to print the hash table
    public void printHashTable() {
        if (hashMatrix == null) {
            System.out.println("Hash table is not created yet!");
            return;
        }
        System.out.println("Hash Table:");
        for (int i = 0; i < hashMatrix.length; i++) {
            if (hashMatrix[i] == null) {
                System.out.println("null");
            } else {
                for (int j = 0; j < hashMatrix[i].length; j++) {
                    System.out.print(hashMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
