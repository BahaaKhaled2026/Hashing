import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BatchOperations {
    public static void batchInsert(String path, PerfectHashTableON<String, String> dictionary){
        int numOfCollisions = 0;
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!data.isEmpty()) {
            dictionary.resize(data.size());
        }
        int elementsAdded = 0;
        for (String line : data) {
            if(dictionary.put(line, line)){
                numOfCollisions += dictionary.getCollisions();
                elementsAdded++;
            }
        }
        System.out.println("\nElements added: " + elementsAdded);
        System.out.println("Duplicates: " + (data.size() - elementsAdded));
        System.out.println("Collisions: " + numOfCollisions);
    }
    public static void batchDelete(String path, PerfectHashTableON<String, String> dictionary){
        int elementsDeleted = 0;
        int elementsNotFound = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(dictionary.remove(line)){
                    elementsDeleted++;
                }
                else{
                    elementsNotFound++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nElements deleted: " + elementsDeleted);
        System.out.println("Elements not found: " + elementsNotFound);
    }
}
