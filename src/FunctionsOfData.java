import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FunctionsOfData {
    public static void writeToFile(String fileName, ArrayList<Double> data) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            for (Double number : data) {
                writer.write(number + " ");
            }
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void generateRandomInput(int[] input, int size) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            input[i] = random.nextInt();
        }
    }
    public static double generateAverageCollisions(int[] input, int tableSize, int numTrials) {
        int numCollisions = 0;
        for (int k = 0; k < numTrials; k++) {
            PerfectHashTableON<Integer, Integer> dictionary = new PerfectHashTableON<>(tableSize);
//            PerfectHashTableON2<Integer> dictionary = new PerfectHashTableON2<>(tableSize);
            for (int num : input) {
                dictionary.put(num, num);
//                dictionary.insert(num);
                numCollisions += dictionary.getCollisions();
            }
        }
        return (double) numCollisions / numTrials;
    }
}
