import java.util.Random;

public class Quadratic implements PerfectHashTable {
    private int[] table;
    private int size;
    private int collisionCount;

    public Quadratic(int[] keys) {
        size = keys.length * keys.length;
        table = new int[size];
        Random random = new Random();

        while (true) {
            collisionCount = 0; // Initialize collision count
            int[] tempTable = new int[size];
            boolean collision = false;

            for (int key : keys) {
                int hashValue = hash(key, random);
                int i = 0;
                while (tempTable[hashValue] != 0) {
                    collision = true;
                    collisionCount++; // Increment collision count
                    hashValue = (hashValue + i * i) % size; // Quadratic probing
                    i++;
                }
                tempTable[hashValue] = key;
            }

            if (!collision) {
                table = tempTable;
                break;
            }
        }
    }

    private int hash(int key, Random random) {
        int hashValue = (key * random.nextInt(size)) % size;
        return hashValue >= 0 ? hashValue : hashValue + size; // Ensure non-negativity
    }


    @Override
    public boolean search(int key) {
        return table[key % size] != 0;
    }

    @Override
    public void insert(int key) {
        int hashValue = hash(key, new Random());
        int i = 0;
        while (table[hashValue] != 0) {
            hashValue = (hashValue + i * i) % size; // Quadratic probing
            i++;
        }
        table[hashValue] = key;
    }

    @Override
    public void delete(int key) {
        int hashValue = hash(key, new Random());
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
