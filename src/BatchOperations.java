import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BatchOperations {
    public static void batchInsert(String path, PerfectHashTableON<String, String> dictionary){
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.put(line, line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void batchDelete(String path, PerfectHashTableON<String, String> dictionary){
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.remove(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
