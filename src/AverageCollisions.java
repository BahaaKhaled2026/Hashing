import java.util.ArrayList;

public class AverageCollisions {
    public static void main(String[] args) {
        int tableSize = 1000;
        int inputSize = 400;
        int numTrials = 20;
        ArrayList<Double> data;
        String fileName = "src\\data3ON.txt";
        for (int i = tableSize; i <= 5000; i += 500) { // change table size
            data = new ArrayList<>();
            for (int j = inputSize; j <= 4000; j += 500) { // change input size
                int[] input = new int[j];
                FunctionsOfData.generateRandomInput(input, j);
                double avr = FunctionsOfData.generateAverageCollisions(input, i, numTrials);
                data.add(avr);
            }
            FunctionsOfData.writeToFile(fileName, data);
        }
    }
}

