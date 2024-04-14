import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Linear implements PerfectHashTable {
    private List<Integer>[] firstLevelTable;
    private List<Integer>[] secondLevelTables;
    private int size;
    private int collisionCount;

    public Linear(int[] keys) {
        size = keys.length;

        // Initialize first-level table
        firstLevelTable = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            firstLevelTable[i] = new ArrayList<>();
        }

        // Populate first-level table
        for (int key : keys) {
            int index = hash(key);
            firstLevelTable[index].add(key);
        }

        // Initialize second-level tables and rehash with linear probing
        secondLevelTables = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            if (firstLevelTable[i].size() > 0) {
                secondLevelTables[i] = new ArrayList<>();
                int M = firstLevelTable[i].size() * firstLevelTable[i].size();
                Random random = new Random();
                while (true) {
                    boolean collision = false;
                    int[] tempTable = new int[M];
                    for (int key : firstLevelTable[i]) {
                        int hashValue = key % M;
                        while (tempTable[hashValue] != 0) {
                            collision = true;
                            collisionCount++; // Increment collision count
                            hashValue = (hashValue + 1) % M; // Linear probing
                        }
                        tempTable[hashValue] = key;
                    }
                    if (!collision) {
                        for (int value : tempTable) {
                            if (value != 0) {
                                secondLevelTables[i].add(value);
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private int hash(int key) {
        int hashValue = key % size;
        return hashValue >= 0 ? hashValue : hashValue + size; // Ensure non-negativity
    }


    @Override
    public boolean search(int key) {
        int index = hash(key);
        if (secondLevelTables[index] != null) {
            for (int value : secondLevelTables[index]) {
                if (value == key) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void insert(int key) {
        int index = hash(key);
        if (secondLevelTables[index] == null) {
            secondLevelTables[index] = new ArrayList<>();
        }
        secondLevelTables[index].add(key);
    }

    @Override
    public void delete(int key) {
        int index = hash(key);
        if (secondLevelTables[index] != null) {
            secondLevelTables[index].removeIf(value -> value == key);
        }
    }

    public int getSpaceUsage() {
        int spaceUsage = 0;
        for (List<Integer> list : secondLevelTables) {
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
