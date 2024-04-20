import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PerfectHashTableON2<E> {

    private int size; // Size of the hash table
    boolean[][] hashMatrix; // Hash matrix for the hashing
    final private double loadFactor = 0.75; // Load factor for resizing
    E[] table;
    Map<E, Integer> elements = new HashMap<>();

    public PerfectHashTableON2() {
        this.size = 10;
        this.hashMatrix = HashingFunctions.generateHashMatrix(this.size);
        this.table = (E[]) new Object[size];
    }

    public PerfectHashTableON2(int cap) {
        if(this.size<=0)
            throw new IllegalArgumentException("Size must be positive");
        this.size = cap * cap;
        this.hashMatrix = HashingFunctions.generateHashMatrix(this.size);
        this.table = (E[]) new Object[size];
    }

    // Method to perform perfect hashing using the O(N^2) method
    public void createPerfectHashTable() {
        boolean collision;
        do {
            collision = false;
            boolean[][] hashFunction = HashingFunctions.generateHashMatrix(this.size); // Generate random hash function
            E[] table = (E[]) new Object[size];
            for (E item : elements.keySet()) {
                int key = item.hashCode();
                int hashValue = HashingFunctions.multiplyMatrix(hashFunction, HashingFunctions.decimalToBinary(key)) % size;
                if (table[hashValue] != null) {
                    collision = true;
                    break;
                }
                table[hashValue] = item;
            }
            if (!collision) {
                this.hashMatrix = hashFunction;
                this.table = table;
            }
        } while (collision);
    }

    public void insert(E input) {
        elements.put(input, 1);
        if (elements.size() > size * loadFactor) {
            size *= 2;
            createPerfectHashTable();
        }
        int key = input.hashCode();
        int hashValue = HashingFunctions.multiplyMatrix(hashMatrix, HashingFunctions.decimalToBinary(key)) % size;
        if (table[hashValue] == null) {
            table[hashValue] = input;
        } else {
            System.out.println("Collision detected");
            createPerfectHashTable();
        }
    }

    public void delete(E input) {
        elements.remove(input);
        int key = input.hashCode();
        int hashValue = HashingFunctions.multiplyMatrix(hashMatrix, HashingFunctions.decimalToBinary(key)) % size;
        if (table[hashValue] != null) {
            if (table[hashValue].equals(input)) {
                table[hashValue] = null;
                System.out.println("Element removed");

            } else
                System.out.println("Element not found");

        } else {
            System.out.println("Element not found");
        }

    }

    public void display() {
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                System.out.println(table[i]);
            }
        }
    }

    public void search(E input) {
        int key = input.hashCode();
        int hashValue = HashingFunctions.multiplyMatrix(hashMatrix, HashingFunctions.decimalToBinary(key)) % size;
        if (table[hashValue] != null) {
            if (table[hashValue].equals(input)) {
                System.out.println("Element found");
            } else
                System.out.println("Element not found");

        } else {
            System.out.println("Element not found");
        }
    }

    public void batchInsertFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                insert((E) line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void batchDeleteFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                delete((E) line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
