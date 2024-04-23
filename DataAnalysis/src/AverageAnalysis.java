import java.util.ArrayList;

public class AverageAnalysis {
    public static void batchInsertAnalysis(boolean isON2){
        int inputSize = 400;
        int numTrials = 7;
        ArrayList<Integer> data = new ArrayList<>();
        ArrayList<Long> time = new ArrayList<>();
        ArrayList<Long> reBuildTime = new ArrayList<>();
        ArrayList<Integer> space = new ArrayList<>();
        for(int i = inputSize; i<=20000;i+=1000){
            int[] input = new int[i];
            FunctionsOfData.generateRandomInput(input, i);
            int avr = FunctionsOfData.generateAverageBatchInsertCollisions(input, numTrials, isON2, time, reBuildTime, space);
            data.add(avr);
        }
        if(isON2){
            FunctionsOfData.writeDataToFile("DataAnalysis\\src\\Data\\BatchInsertON2.txt", data,false);
            FunctionsOfData.writeTimeToFile("DataAnalysis\\src\\Data\\BatchInsertON2Time.txt", time, false);
            FunctionsOfData.writeTimeToFile("DataAnalysis\\src\\Data\\BatchInsertON2RebuildTime.txt", reBuildTime, false);
            FunctionsOfData.writeSpaceToFile("DataAnalysis\\src\\Data\\BatchInsertON2Space.txt", space, false);
        }
        else{
            FunctionsOfData.writeDataToFile("DataAnalysis\\src\\Data\\BatchInsertON.txt", data, false);
            FunctionsOfData.writeTimeToFile("DataAnalysis\\src\\Data\\BatchInsertONTime.txt", time, false);
            FunctionsOfData.writeTimeToFile("DataAnalysis\\src\\Data\\BatchInsertONRebuildTime.txt", reBuildTime, false);
            FunctionsOfData.writeSpaceToFile("DataAnalysis\\src\\Data\\BatchInsertONSpace.txt", space, false);
        }
    }
    public static void singleInsertAnalysis(boolean isON2){
        int tableSize = 200;
        int inputSize = 50;
        int numTrials = 20;
        ArrayList<Integer> data;
        ArrayList<Long> time = new ArrayList<>();
        ArrayList<Long> reBuildTime = new ArrayList<>();
        ArrayList<Integer> space = new ArrayList<>();
        for (int i = tableSize; i <= 2000; i += 200) { // change table size
            data = new ArrayList<>();
            for (int j = inputSize; j <= 500; j +=50 ) { // change input size
                int[] input = new int[j];
                FunctionsOfData.generateRandomInput(input, j);
                int avr = FunctionsOfData.generateAverageCollisions(input, i, numTrials, isON2, time, reBuildTime, space);
                data.add(avr);
            }
            if (isON2) {
                FunctionsOfData.writeDataToFile("DataAnalysis\\src\\Data\\singleInsertON2.txt", data,true);
                FunctionsOfData.writeTimeToFile("DataAnalysis\\src\\Data\\singleInsertON2Time.txt", time, true);
                FunctionsOfData.writeTimeToFile("DataAnalysis\\src\\Data\\singleInsertON2RebuildTime", reBuildTime, true);
                FunctionsOfData.writeSpaceToFile("DataAnalysis\\src\\Data\\singleInsertON2Space.txt", space, true);
            } else {
                FunctionsOfData.writeDataToFile("DataAnalysis\\src\\Data\\singleInsertON.txt", data,true);
                FunctionsOfData.writeTimeToFile("DataAnalysis\\src\\Data\\singleInsertONTime.txt", time, true);
                FunctionsOfData.writeTimeToFile("DataAnalysis\\src\\Data\\singleInsertONRebuildTime", reBuildTime, true);
                FunctionsOfData.writeSpaceToFile("DataAnalysis\\src\\Data\\singleInsertONSpace.txt", space, true);
            }
        }
    }
}
