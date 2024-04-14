import java.util.ArrayList;
import java.util.List;

public class Linear implements PerfectHashTable {
    private List<Integer>[] firstLevelTable;
    private int size;
    private int collisionCount;
    private static final double LOAD_FACTOR_THRESHOLD = 0.8; // Adjust load factor threshold as needed
    private static final int INITIAL_CAPACITY = 8; // Initial capacity of the hash table

    public Linear() {
        size = INITIAL_CAPACITY;
        initializeTables();
    }

    private void initializeTables() {
        firstLevelTable = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            firstLevelTable[i] = new ArrayList<>();
        }
    }

    private void resizeTables() {
        int newSize = size * 2; // Incrementally increase the size of the table
        List<Integer>[] newFirstLevelTable = new ArrayList[newSize];

        // Rehash keys into the new tables
        for (int i = 0; i < size; i++) {
            if (firstLevelTable[i] != null) {
                for (int key : firstLevelTable[i]) {
                    int index = hash(key, newSize);
                    if (newFirstLevelTable[index] == null) {
                        newFirstLevelTable[index] = new ArrayList<>();
                    }
                    newFirstLevelTable[index].add(key);
                }
            }
        }

        size = newSize;
        firstLevelTable = newFirstLevelTable;
    }

    private int hash(int key, int tableSize) {
        return Math.abs(key % tableSize); // Ensure non-negative index
    }

    @Override
    public boolean search(int key) {
        int index = hash(key, size);
        List<Integer> list = firstLevelTable[index];
        return list != null && list.contains(key);
    }

    @Override
    public void insert(int key) {
        if (firstLevelTable == null) {
            initializeTables();
        }
        if (calculateLoadFactor() >= LOAD_FACTOR_THRESHOLD) {
            resizeTables();
        }

        int index = hash(key, size);
        if (firstLevelTable[index] == null) {
            firstLevelTable[index] = new ArrayList<>();
        } else {
            collisionCount++; // Increment collision count if there's already a list at this index
        }
        firstLevelTable[index].add(key);
    }
    private int countElements() {
        int count = 0;
        for (List<Integer> list : firstLevelTable) {
            if (list != null) {
                count += list.size();
            }
        }
        return count;
    }

    private double calculateLoadFactor() {
        return (double) countElements() / size;
    }

    @Override
    public void delete(int key) {
        int index = hash(key, size);
        List<Integer> list = firstLevelTable[index];
        if (list != null) {
            list.remove(Integer.valueOf(key));
        }
    }

    public int getSpaceUsage() {
        int spaceUsage = 0;
        for (List<Integer> list : firstLevelTable) {
            if (list != null) {
                spaceUsage += list.size() * Integer.BYTES;
            }
        }
        return size * Integer.BYTES + spaceUsage;
    }

    public int getCollisionCount() {
        return collisionCount;
    }
}
