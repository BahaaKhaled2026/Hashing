import java.util.Random;

public class HashingFunctions {

    // Method to generate a random binary matrix for hashing
    public static int[][] generateHashMatrix(int tableSize) {
        Random random = new Random();
        int numRows = (int) Math.ceil(Math.log(tableSize) / Math.log(2)); // Number of rows = logN
        int numCols = Integer.SIZE; // Number of columns = 32 bits (for the domain)
        int[][] hashMatrix = new int[numRows][numCols];

        // Fill the matrix with random 0s and 1s
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                hashMatrix[i][j] = random.nextInt(2);
            }
        }
        return hashMatrix;
    }

    // Method to convert a decimal number to its binary representation
    public static int[] decimalToBinary(int decimal) {
        int[] binary = new int[Integer.SIZE];
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            binary[i] = decimal & 1;
            decimal >>= 1;
        }
        return binary;
    }

    // Method to perform matrix multiplication for hashing
    public static int multiplyMatrix(int[][] matrix, int[] vector) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numCols != vector.length) {
            throw new IllegalArgumentException("Matrix and vector dimensions are not compatible for multiplication");
        }

        int result= 0;

        for (int i = 0; i < numRows; i++) {
            int sum = 0;
            for (int j = 0; j < numCols; j++) {
                sum += matrix[i][j] * vector[j];
            }
            result += sum % 2 << i;
        }

//        int result = 0;
//        for (int i = 0; i < resultVector.length; i++) {
//            if (resultVector[i]) {
//                result |= (1 << i);
//            }
//        }
        return result;
    }
}