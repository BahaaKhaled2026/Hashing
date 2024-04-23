import java.util.Random;

public class HashingFunctions {

    // Method to generate a random binary matrix for hashing
    public static boolean[][] generateHashMatrix(int tableSize) {
        Random random = new Random();
        int numRows = (int) Math.ceil(Math.log(tableSize) / Math.log(2)); // Number of rows = logN
        int numCols = Integer.SIZE; // Number of columns = 32 bits (for the domain)
        boolean[][] hashMatrix = new boolean[numRows][numCols];

        // Fill the matrix with random 0s and 1s
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                hashMatrix[i][j] = random.nextBoolean();
            }
        }
        return hashMatrix;
    }

    // Method to convert a decimal number to its binary representation
    public static boolean[] decimalToBinary(int decimal) {
        boolean[] binary = new boolean[Integer.SIZE];
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            binary[i] = (decimal & 1) == 1;
            decimal >>= 1;
        }
        return binary;
    }

    // Method to perform matrix multiplication for hashing
    public static int multiplyMatrix(boolean[][] matrix, boolean[] vector) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numCols != vector.length) {
            throw new IllegalArgumentException("Matrix and vector dimensions are not compatible for multiplication");
        }

        boolean[] resultVector = new boolean[numRows];

        for (int i = 0; i < numRows; i++) {
            boolean sum = false;
            for (int j = 0; j < numCols; j++) {
                sum ^= matrix[i][j] && vector[j];
            }
            resultVector[i] = sum;
        }

        int result = 0;
        for (int i = 0; i < resultVector.length; i++) {
            if (resultVector[i]) {
                result |= (1 << i);
            }
        }
        return result;
    }
}