import java.util.ArrayList;
import java.util.Arrays;

public class PerfectHashTableON<K, V>{
    public static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private int counter;
    private ArrayList<Entry<K, V>>[] table;
    private int[] maxElementsInBucket;
    private int size;
    private int[] realUsedSpaceOfBucket;
    private static final double MAX_LOAD_FACTOR = 0.7;
    private static final int INITIAL_CAPACITY = 10;
    private static final int MAX_ELEMENTS_IN_BUCKET = 3;
    private boolean[][] firstLevelHashMatrix;
    private boolean[][][] secondLevelHashMatrix;
    private long time;

    public PerfectHashTableON() {
        this(INITIAL_CAPACITY);
    }

    public PerfectHashTableON(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        if (size < 2) {
            size = 2;
        }
        table = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new ArrayList<>();
        }
        for(int i = 0; i < size; i++){
            for(int j = 0; j < MAX_ELEMENTS_IN_BUCKET; j++){
                table[i].add(null);
            }
        }
        secondLevelHashMatrix = new boolean[size][][];
        maxElementsInBucket = new int[size];
        Arrays.fill(maxElementsInBucket, MAX_ELEMENTS_IN_BUCKET);
        this.size = 0;
        firstLevelHashMatrix = HashingFunctions.generateHashMatrix(size);
        realUsedSpaceOfBucket = new int[size];
    }

    public boolean put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        time = 0;
        counter = 0;
        int firstLevelHashIndex = HashingFunctions.multiplyMatrix(firstLevelHashMatrix, HashingFunctions.decimalToBinary(key.hashCode())) % table.length;
        if (realUsedSpaceOfBucket[firstLevelHashIndex] == 0) {
            table[firstLevelHashIndex].set(0, new Entry<>(key, value));
            size++;
            realUsedSpaceOfBucket[firstLevelHashIndex]++;
            double loadFactor = (double) size / table.length;
            if (loadFactor > MAX_LOAD_FACTOR) {
                resize(0);
            }
            return true;
        }
        else {
            if(secondLevelHashMatrix[firstLevelHashIndex] != null){
                int secondLevelHashIndex = HashingFunctions.multiplyMatrix(secondLevelHashMatrix[firstLevelHashIndex], HashingFunctions.decimalToBinary(key.hashCode())) % maxElementsInBucket[firstLevelHashIndex];
                if (table[firstLevelHashIndex].get(secondLevelHashIndex) == null) {
                    table[firstLevelHashIndex].set(secondLevelHashIndex, new Entry<>(key, value));
                    realUsedSpaceOfBucket[firstLevelHashIndex]++;
                    if ((double) realUsedSpaceOfBucket[firstLevelHashIndex] / maxElementsInBucket[firstLevelHashIndex] > MAX_LOAD_FACTOR) {
                        maxElementsInBucket[firstLevelHashIndex] = (int)Math.pow(maxElementsInBucket[firstLevelHashIndex]+1, 2);
                        resizeBucket(firstLevelHashIndex);
                    }
                    return true;
                }
                else if(table[firstLevelHashIndex].get(secondLevelHashIndex).getKey().equals(key)){
                    if(table[firstLevelHashIndex].get(secondLevelHashIndex).getValue().equals(value)){
                        return false;
                    }
                    table[firstLevelHashIndex].get(secondLevelHashIndex).setValue(value);
                    return true;
                }
            }
            else if(secondLevelHashMatrix[firstLevelHashIndex] == null && realUsedSpaceOfBucket[firstLevelHashIndex] == 1){
                if(table[firstLevelHashIndex].get(0).getKey().equals(key)){
                    if(table[firstLevelHashIndex].get(0).getValue().equals(value)){
                        return false;
                    }
                    table[firstLevelHashIndex].get(0).setValue(value);
                    return true;
                }
            }
            boolean collision;
            ArrayList<Entry<K, V>> temp;
            boolean[][] secondLevelHashFunction;
            int usedSpace;
            long startTime = System.currentTimeMillis();
            do {
                secondLevelHashFunction = HashingFunctions.generateHashMatrix(maxElementsInBucket[firstLevelHashIndex]);
                collision = false;
                temp = new ArrayList<>();
                for (int i = 0; i < maxElementsInBucket[firstLevelHashIndex]; i++) {
                    temp.add(null);
                }
                int secondLevelHashIndex = HashingFunctions.multiplyMatrix(secondLevelHashFunction, HashingFunctions.decimalToBinary(key.hashCode())) % maxElementsInBucket[firstLevelHashIndex];
                temp.set(secondLevelHashIndex, new Entry<>(key, value));
                usedSpace = 1;
                for (Entry<K, V> entry : table[firstLevelHashIndex]) {
                    if (entry == null) {
                        continue;
                    }
                    int index = HashingFunctions.multiplyMatrix(secondLevelHashFunction, HashingFunctions.decimalToBinary(entry.getKey().hashCode())) % maxElementsInBucket[firstLevelHashIndex];
                    if (temp.get(index) != null && !temp.get(index).getKey().equals(entry.getKey())) {
                        collision = true;
                        counter++;
//                        System.out.println("Collision is detected");
                        break;
                    }
                    if (temp.get(index) == null) {
                        temp.set(index, entry);
                        usedSpace++;
                    }
                }
            } while (collision);
            long endTime = System.currentTimeMillis();
            time += endTime - startTime;
            table[firstLevelHashIndex] = temp;
            secondLevelHashMatrix[firstLevelHashIndex] = secondLevelHashFunction;
            realUsedSpaceOfBucket[firstLevelHashIndex] = usedSpace;
        }
        double loadFactor = (double) size / table.length;
        if (loadFactor > MAX_LOAD_FACTOR) {
            resize(0);
        } else if ((double) realUsedSpaceOfBucket[firstLevelHashIndex] / maxElementsInBucket[firstLevelHashIndex] > MAX_LOAD_FACTOR) {
            maxElementsInBucket[firstLevelHashIndex] = (int)Math.pow(maxElementsInBucket[firstLevelHashIndex]+1, 2);
            resizeBucket(firstLevelHashIndex);
        }
        return true;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int firstLevelHashIndex = HashingFunctions.multiplyMatrix(firstLevelHashMatrix, HashingFunctions.decimalToBinary(key.hashCode())) % table.length;
        if (realUsedSpaceOfBucket[firstLevelHashIndex] == 0) {
//            System.out.println("Element is not found");
            return null;
        } else if (realUsedSpaceOfBucket[firstLevelHashIndex] == 1) {
            if(secondLevelHashMatrix[firstLevelHashIndex] == null){
                if (table[firstLevelHashIndex].get(0).getKey().equals(key)) {
//                    System.out.println("Element is found");
                    return table[firstLevelHashIndex].get(0).getValue();
                } else {
//                    System.out.println("Element is not found");
                    return null;
                }
            }
            int secondLevelHashIndex = HashingFunctions.multiplyMatrix(secondLevelHashMatrix[firstLevelHashIndex], HashingFunctions.decimalToBinary(key.hashCode())) % maxElementsInBucket[firstLevelHashIndex];
            if (table[firstLevelHashIndex].get(secondLevelHashIndex) == null) {
//                System.out.println("Element is not found");
                return null;
            } else if (table[firstLevelHashIndex].get(secondLevelHashIndex).getKey().equals(key)) {
//                System.out.println("Element is found");
                return table[firstLevelHashIndex].get(secondLevelHashIndex).getValue();
            } else {
//                System.out.println("Element is not found");
                return null;
            }
        } else {
            int secondLevelHashIndex = HashingFunctions.multiplyMatrix(secondLevelHashMatrix[firstLevelHashIndex], HashingFunctions.decimalToBinary(key.hashCode())) % maxElementsInBucket[firstLevelHashIndex];
            if (table[firstLevelHashIndex].get(secondLevelHashIndex) == null) {
//                System.out.println("Element is not found");
                return null;
            } else {
//                System.out.println("Element is found");
                return table[firstLevelHashIndex].get(secondLevelHashIndex).getValue();
            }
        }
    }

    public boolean remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int firstLevelHashIndex = HashingFunctions.multiplyMatrix(firstLevelHashMatrix, HashingFunctions.decimalToBinary(key.hashCode())) % table.length;
        if (realUsedSpaceOfBucket[firstLevelHashIndex] == 0) {
//            System.out.println("Element is not exist in the table");
            return false;
        } else if (realUsedSpaceOfBucket[firstLevelHashIndex] == 1) {
            if(secondLevelHashMatrix[firstLevelHashIndex] == null){
                if (table[firstLevelHashIndex].get(0).getKey().equals(key)) {
                    table[firstLevelHashIndex].set(0, null);
                    realUsedSpaceOfBucket[firstLevelHashIndex]--;
                    size--;
//                    System.out.println("Element is removed");
                    return true;
                } else {
//                    System.out.println("Element is not exist in the table");
                    return false;
                }
            }
            int secondLevelHashIndex = HashingFunctions.multiplyMatrix(secondLevelHashMatrix[firstLevelHashIndex], HashingFunctions.decimalToBinary(key.hashCode())) % maxElementsInBucket[firstLevelHashIndex];
            if (table[firstLevelHashIndex].get(secondLevelHashIndex) == null) {
//                System.out.println("Element is not exist in the table");
                return false;
            } else if (table[firstLevelHashIndex].get(secondLevelHashIndex).getKey().equals(key)) {
                table[firstLevelHashIndex].set(secondLevelHashIndex, null);
                realUsedSpaceOfBucket[firstLevelHashIndex]--;
//                System.out.println("Element is removed");
                return true;
            } else {
//                System.out.println("Element is not exist in the table");
                return false;
            }
        } else {
            int secondLevelHashIndex = HashingFunctions.multiplyMatrix(secondLevelHashMatrix[firstLevelHashIndex], HashingFunctions.decimalToBinary(key.hashCode())) % maxElementsInBucket[firstLevelHashIndex];
            if (table[firstLevelHashIndex].get(secondLevelHashIndex) == null) {
//                System.out.println("Element is not exist in the table");
                return false;
            } else {
                table[firstLevelHashIndex].set(secondLevelHashIndex, null);
                realUsedSpaceOfBucket[firstLevelHashIndex]--;
//                System.out.println("Element is removed");
                return true;
            }
        }
    }

    public void resize(int length) {
        ArrayList<Entry<K, V>>[] temp = table;
        if(length == 0) {
            table = new ArrayList[table.length * 2];
        }
        else{
            if(table.length-size < 2*length) {
                table = new ArrayList[table.length + 2 * length];
            }
            else{
                table = new ArrayList[table.length];
            }
        }
        for (int i = 0; i < table.length; i++) {
            table[i] = new ArrayList<>();
        }
        for (ArrayList<Entry<K, V>> entries : table) {
            for (int j = 0; j < MAX_ELEMENTS_IN_BUCKET; j++) {
                entries.add(null);
            }
        }
        size = 0;
        maxElementsInBucket = new int[table.length];
        realUsedSpaceOfBucket = new int[table.length];
        Arrays.fill(maxElementsInBucket, MAX_ELEMENTS_IN_BUCKET);
        firstLevelHashMatrix = HashingFunctions.generateHashMatrix(table.length);
        secondLevelHashMatrix = new boolean[table.length][][];
        for (ArrayList<Entry<K, V>> bucket : temp) {
            for (Entry<K, V> entry : bucket) {
                if (entry == null) {
                    continue;
                }
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void resizeBucket(int index) {
        ArrayList<Entry<K, V>> temp = table[index];
        table[index] = new ArrayList<>();
        for(int i = 0; i < maxElementsInBucket[index]; i++){
            table[index].add(null);
        }
//        secondLevelHashMatrix[index] = HashingFunctions.generateHashMatrix(maxElementsInBucket[index]);
        for (Entry<K, V> entry : temp) {
            if (entry == null) {
                continue;
            }
            put(entry.getKey(), entry.getValue());
        }
    }

    public void print() {
        int i = 1;
        for (ArrayList<Entry<K, V>> entries : table) {
            for (Entry<K, V> entry : entries) {
                if (entry == null) {
                    continue;
                } else {
                    System.out.println(i + "- " + entry.getKey());
                    i++;
                }
            }
        }
    }

    public int getCollisions() {
        return counter;
    }
    public long getRebuildTime(){
        return time;
    }
    public int getSize(){
        return table.length;
    }
}
