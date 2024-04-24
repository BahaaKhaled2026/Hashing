import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FunctionsOfData {
    public static void writeDataToFile(String fileName, ArrayList<Integer> data, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append))) {
            for (Integer number : data) {
                writer.write(number + " ");
            }
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeTimeToFile(String fileName, ArrayList<Long> data, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append))) {
            for (long number : data) {
                writer.write(number + " ");
            }
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    public static void writeSpaceToFile(String fileName, ArrayList<Integer> data, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append))) {
            for (int number : data) {
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

    public static int generateAverageCollisions(int[] input, int tableSize, int numTrials, boolean isON2, ArrayList<Long>time, ArrayList<Long>reBuildTime, ArrayList<Integer>space) {
        int numCollisions = 0;
        long elapsedTimeMillis = 0;
        long reBuildTimeMillis = 0;
        int spaceUsed = 0;
        if (!isON2) {
            for (int k = 0; k < numTrials; k++) {
                PerfectHashTableON<Integer, Integer> dictionary = new PerfectHashTableON<>(tableSize);
                for (int num : input) {
                    long startTime = System.currentTimeMillis();
                    dictionary.put(num, num);
                    long endTime = System.currentTimeMillis();
                    reBuildTimeMillis += dictionary.getRebuildTime();
                    elapsedTimeMillis += endTime - startTime;
                    numCollisions += dictionary.getCollisions();
                }
                spaceUsed += dictionary.getSize();
            }
        } else {
            for (int k = 0; k < numTrials; k++) {
                PerfectHashTableON2<Integer> dictionary = new PerfectHashTableON2<>(tableSize);
                for (int num : input) {
                    long startTime = System.currentTimeMillis();
                    dictionary.insert(num);
                    long endTime = System.currentTimeMillis();
                    reBuildTime.add(dictionary.getRebuildTime());
                    elapsedTimeMillis += endTime - startTime;
                    numCollisions += dictionary.getCollisions();
                }
                spaceUsed += dictionary.getSize();
            }
        }
        space.add(spaceUsed/numTrials);
        reBuildTime.add(reBuildTimeMillis/numTrials);
        time.add(elapsedTimeMillis/numTrials);
        return  numCollisions / numTrials;
    }

    public static int generateAverageBatchInsertCollisions(int[] input, int numTrials, boolean isON2,ArrayList<Long>time, ArrayList<Long>reBuildTime, ArrayList<Integer>space){
        int numCollisions = 0;
        long elapsedTimeMillis = 0;
        long reBuildTimeMillis = 0;
        int spaceUsed = 0;
        if (!isON2) {
            PerfectHashTableON<Integer,Integer>dictionary = new PerfectHashTableON<>();
            for (int k = 0; k < numTrials; k++) {
                long startTime = System.currentTimeMillis();
                dictionary.resize(input.length);
                reBuildTimeMillis += dictionary.getRebuildTime();
                for (int num : input) {
                    dictionary.put(num, num);
                    reBuildTimeMillis += dictionary.getRebuildTime();
                    numCollisions += dictionary.getCollisions();
                }
                long endTime = System.currentTimeMillis();
                elapsedTimeMillis += endTime - startTime;
                spaceUsed += dictionary.getSize();
            }
        } else {
            PerfectHashTableON2<Integer> dictionary = new PerfectHashTableON2<>();
            for (int k = 0; k < numTrials; k++) {
                long startTime = System.currentTimeMillis();
                dictionary.setSize(dictionary.getSize()+(input.length*input.length));
                reBuildTimeMillis += dictionary.getRebuildTime();
                dictionary.createPerfectHashTable();
                for (int num : input) {
                    dictionary.insert(num);
                    reBuildTimeMillis += dictionary.getRebuildTime();
                    numCollisions += dictionary.getCollisions();
                }
                long endTime = System.currentTimeMillis();
                elapsedTimeMillis += endTime - startTime;
                spaceUsed += dictionary.getSize();
            }
        }
        reBuildTime.add(reBuildTimeMillis/numTrials);
        time.add(elapsedTimeMillis/numTrials);
        space.add(spaceUsed/numTrials);
        return numCollisions / numTrials;
    }
}
