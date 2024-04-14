import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
    private final PerfectHashTable hashTable;

    public Dictionary(PerfectHashTable hashTable) {
        this.hashTable = hashTable;
    }

    public void insert(String key) {
        hashTable.insert(key.hashCode());
    }

    public void delete(String key) {
        hashTable.delete(key.hashCode());
    }

    public boolean search(String key) {
        return hashTable.search(key.hashCode());
    }


    public void batchInsert(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                insert(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void batchDelete(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                delete(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
