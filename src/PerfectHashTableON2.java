import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PerfectHashTableON2<E> {
    private int numberOfCollisions;
    private int counter;
    private int size; // Size of the hash table
    boolean[][] hashMatrix; // Hash matrix for the hashing
    final private double loadFactor = 0.6; // Load factor for resizing
    E[] table;
    E[] tempTable;
    int itemCounter;
    long time;

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
        itemCounter=0;
        boolean collision;
        counter = 0;
        long startTime = System.currentTimeMillis();
        E[] tempTable = (E[]) new Object[size];
        int j = 0;
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] != null) {
                tempTable[j++] = this.table[i];
                this.itemCounter++;
            }
        }
        do {
            collision = false;
            boolean[][] hashFunction = HashingFunctions.generateHashMatrix(this.size); // Generate random hash function
            E[] ansTable = (E[]) new Object[size];
            for (E item : tempTable) {
                if (item != null) {
                    int key = item.hashCode();
                    int hashValue = HashingFunctions.multiplyMatrix(hashFunction, HashingFunctions.decimalToBinary(key))
                            % size;
                    if (ansTable[hashValue] != null) {
                        collision = true;
                        counter++;
                        break;
                    }
                    ansTable[hashValue] = item;
                }
            }
            if (!collision) {
                this.hashMatrix = hashFunction;
                this.table = ansTable;
            }
        } while (collision);
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }

    public void insert(E input) {
        time = 0;
        if (itemCounter > size * loadFactor) {
            size *= size;
            createPerfectHashTable();
        }
        int key = input.hashCode();
        int hashValue = HashingFunctions.multiplyMatrix(hashMatrix, HashingFunctions.decimalToBinary(key)) % size;
        if (table[hashValue] == null) {
            table[hashValue] = input;
            itemCounter++;
        } else {
            if (table[hashValue].equals(input)) {
                System.out.println("Duplicate Item");
            } else {
                for(int i=0;i< table.length;i++){
                    if (table[i]==null){
                        table[i]=input;
                        break;
                    }
                }
                System.out.println("Collision detected");
                this.numberOfCollisions++;
                createPerfectHashTable();
            }
        }
    }

    public void delete(E input) {
        int key = input.hashCode();
        int hashValue = HashingFunctions.multiplyMatrix(hashMatrix, HashingFunctions.decimalToBinary(key)) % size;
        if (table[hashValue] != null) {
            if (table[hashValue].equals(input)) {
                table[hashValue] = null;
                System.out.println("Element removed");
                itemCounter--;
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
        System.out.println("Number Of Elements = "+itemCounter);
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
        int oldSize = itemCounter;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                i++;
            }
            this.size = (int)Math.ceil(Math.sqrt(this.size)) + (i * i);
            createPerfectHashTable();
        } catch (IOException e) {

        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                insert((E) line);
            }
            System.out.println("Number of duplicates: " + (-(itemCounter - oldSize - i)));
            System.out.println("Inserted number of elements: " + (i+((itemCounter - oldSize - i))));
            System.out.println("Number of collisions: " + numberOfCollisions);
            this.numberOfCollisions = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void batchDeleteFromFile(String filePath) {
        int i = 0;
        int oldSize = itemCounter;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                i++;
            }
        } catch (IOException e) {

        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                delete((E) line);
            }
            System.out.println("Number of elements not found: " + (-(-itemCounter + oldSize - i)));
            System.out.println("Number of deleted items: "+(i-(-(-itemCounter + oldSize - i))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCollisions() {
        return counter;
    }

    public long getRebuildTime() {
        return time;
    }
    public int getSize(){
        return size;
    }
    public int getNumberOfElements(){
        return itemCounter;
    }
    public void setSize(int size){
        this.size = size;
    }
}
