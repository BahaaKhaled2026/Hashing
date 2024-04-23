import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PerfectHashTableON2<E> {
    private int numberOfCollisions;
    private int counter;
    private int size; // Size of the hash table
    int[][] hashMatrix; // Hash matrix for the hashing
    final private double loadFactor = 0.6; // Load factor for resizing
    E[] table;
    Map<E, Integer> elements = new HashMap<>();

    public PerfectHashTableON2() {
        this.size = 500;
        this.hashMatrix = HashingFunctions.generateHashMatrix(this.size);
        this.table = (E[]) new Object[size];
        this.numberOfCollisions = 0;
    }

    public PerfectHashTableON2(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        this.size = cap * cap;
        this.hashMatrix = HashingFunctions.generateHashMatrix(this.size);
        this.table = (E[]) new Object[size];
        this.numberOfCollisions = 0;

    }

    // Method to perform perfect hashing using the O(N^2) method
    public void createPerfectHashTable() {
        boolean collision;
        counter = 0;
        do {
            collision = false;
            int[][] hashFunction = HashingFunctions.generateHashMatrix(this.size); // Generate random hash function
            E[] table = (E[]) new Object[size];
            for (E item : elements.keySet()) {
                int key = item.hashCode();
                int hashValue = HashingFunctions.multiplyMatrix(hashFunction, HashingFunctions.decimalToBinary(key)) % size;
                if (table[hashValue] != null) {
                    collision = true;
                    counter++;
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
            size *= size;
            createPerfectHashTable();
        }
        int key = input.hashCode();
        int hashValue = HashingFunctions.multiplyMatrix(hashMatrix, HashingFunctions.decimalToBinary(key)) % size;
        if (table[hashValue] == null) {
            table[hashValue] = input;
        } else {
            System.out.println("Collision detected");
            this.numberOfCollisions++;
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

    public String search(E input) {
        int key = input.hashCode();
        int hashValue = HashingFunctions.multiplyMatrix(hashMatrix, HashingFunctions.decimalToBinary(key)) % size;
        if (table[hashValue] != null) {
            if (table[hashValue].equals(input)) {
                return input.toString();
            } else
                return "";
        } else
            return "";

    }

    public void batchInsertFromFile(String filePath) {
        int i = 0;
        int oldSize = elements.size();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null){
                i++;
            }
            this.size =this.size+ (i * i);
            createPerfectHashTable();
        } catch (IOException e) {
            
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                insert((E) line);
            }
            System.out.println("Number of duplicates: " + (-(elements.size()-oldSize - i)));
            System.out.println("Number of collisions: " + numberOfCollisions);
            this.numberOfCollisions = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void batchDeleteFromFile(String filePath) {
        int i = 0;
        int oldSize = elements.size();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null){
                i++;
            }
        } catch (IOException e) {
            
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                delete((E) line);
            }
            System.out.println("Number of elements not found: " + (-(-elements.size()+oldSize - i)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCollisions() {
        return counter;
    }
}
