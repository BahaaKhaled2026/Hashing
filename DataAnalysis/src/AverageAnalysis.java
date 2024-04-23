import java.util.ArrayList;

public class AverageAnalysis {
    public static void batchInsertAnalysis(boolean isON2){
        int inputSize = 400;
        int numTrials = 7;
        ArrayList<Double> data = new ArrayList<>();
        ArrayList<Long> time = new ArrayList<>();
        ArrayList<Long> reBuildTime = new ArrayList<>();
        for(int i = inputSize; i<=10000;i+=1000){
            int[] input = new int[i];
            FunctionsOfData.generateRandomInput(input, i);
            double avr = FunctionsOfData.generateAverageBatchInsertCollisions(input, numTrials, isON2, time, reBuildTime);
            data.add(avr);
        }
        if(isON2){
            FunctionsOfData.writeDataToFile("src\\Data\\BatchInsertON2.txt", data);
            FunctionsOfData.writeTimeToFile("src\\Data\\BatchInsertON2Time.txt", time);
            FunctionsOfData.writeTimeToFile("src\\Data\\BatchInsertON2RebuildTime.txt", reBuildTime);
        }
        else{
            FunctionsOfData.writeDataToFile("src\\Data\\BatchInsertON.txt", data);
            FunctionsOfData.writeTimeToFile("src\\Data\\BatchInsertONTime.txt", time);
            FunctionsOfData.writeTimeToFile("src\\Data\\BatchInsertONRebuildTime.txt", reBuildTime);
        }
    }
    public static void singleInsertAnalysis(boolean isON2){
        int tableSize = 400;
        int inputSize = 400;
        int numTrials = 20;
        ArrayList<Double> data;
        ArrayList<Long> time = new ArrayList<>();
        ArrayList<Long> reBuildTime = new ArrayList<>();
        for (int i = tableSize; i <= 5000; i += 500) { // change table size
            data = new ArrayList<>();
            for (int j = inputSize; j <= 4000; j += 500) { // change input size
                int[] input = new int[j];
                FunctionsOfData.generateRandomInput(input, j);
                double avr = FunctionsOfData.generateAverageCollisions(input, i, numTrials, isON2, time, reBuildTime);
                data.add(avr);
            }
            if (isON2) {
                FunctionsOfData.writeDataToFile("src\\Data\\singleInsertON2.txt", data);
                FunctionsOfData.writeTimeToFile("src\\Data\\singleInsertON2Time.txt", time);
                FunctionsOfData.writeTimeToFile("src\\Data\\singleInsertON2RebuildTime", reBuildTime);
            } else {
                FunctionsOfData.writeDataToFile("src\\Data\\singleInsertON.txt", data);
                FunctionsOfData.writeTimeToFile("src\\Data\\singleInsertONTime.txt", time);
                FunctionsOfData.writeTimeToFile("src\\Data\\singleInsertONRebuildTime", reBuildTime);
            }
        }
    }
}
