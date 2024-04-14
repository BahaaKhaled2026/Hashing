import java.util.Random;

public class Quadratic implements PerfectHashTable {
    private int[] table;
    private int size;
    private int collisionCount; // Track the number of collisions
    private static final double LOAD_FACTOR_THRESHOLD = 0.8; // Adjust load factor threshold as needed
    private static final int INITIAL_CAPACITY = 8; // Initial capacity of the hash table
    private Random random;

    public Quadratic() {
        size = INITIAL_CAPACITY * INITIAL_CAPACITY;
        table = new int[size];
        random = new Random();
        collisionCount = 0; // Initialize collision count
    }

    private void resizeTable() {
        int newSize = size * 2; // Incrementally increase the size of the table
        int[] newTable = new int[newSize];

        // Rehash keys into the new table
        for (int key : table) {
            if (key != 0) {
                int hashValue = hash(key, newSize);
                int i = 0;
                while (newTable[hashValue] != 0) {
                    hashValue = (hashValue + i * i) % newSize; // Quadratic probing
                    i++;
                }
                newTable[hashValue] = key;
            }
        }

        size = newSize;
        table = newTable;
    }

    private int hash(int key, int tableSize) {
        int hashValue = (key * key) % tableSize; // Using key * key to ensure uniqueness
        return hashValue >= 0 ? hashValue : (hashValue + tableSize) % tableSize; // Ensure non-negativity
    }

    @Override
    public boolean search(int key) {
        int hashValue = hash(key, size);
        int i = 0;
        while (table[hashValue] != 0) {
            if (table[hashValue] == key) {
                return true; // Key found
            }
            hashValue = (hashValue + i * i) % size; // Quadratic probing
            i++;
        }
        return false; // Key not found
    }

    private int countElements() {
        int count = 0;
        for (int key : table) {
            if (key != 0) {
                count++;
            }
        }
        return count;
    }

    private double calculateLoadFactor() {
        return (double) countElements() / size;
    }


    @Override
    public void insert(int key) {
        if (calculateLoadFactor() >= LOAD_FACTOR_THRESHOLD) {
            resizeTable();
        }

        int hashValue = hash(key, size);
        int i = 0;
        while (table[hashValue] != 0) {
            collisionCount++; // Increment collision count
            hashValue = (hashValue + i * i) % size; // Quadratic probing
            i++;
        }
        table[hashValue] = key;
    }

    @Override
    public void delete(int key) {
        int hashValue = hash(key, size);
        if (table[hashValue] == key) {
            table[hashValue] = 0;
        }
    }

    public int getSpaceUsage() {
        return size * Integer.BYTES;
    }

    public int getCollisionCount() {
        return collisionCount;
    }
}
