import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerfectHashTableONTest {

    @Test
    @DisplayName("simple insert [O(N)]")
    public void simpleInsertOn(){
        var HashON = new PerfectHashTableON<>();
        String s = "book";
        HashON.put(s,s);
        assertEquals(HashON.get(s), s);
    }

    @Test
    @DisplayName("simple insert [O(N^2)]")
    public void simpleInsertOn2(){
        var HashON2 = new PerfectHashTableON2<>();
        String s = "book";
        HashON2.insert(s);
        assertEquals(HashON2.search(s), s);
    }

    @Test
    @DisplayName("empty insert [O(N)]")
    public void emptyInsertOn(){
        var HashON = new PerfectHashTableON<>();
        String s = "";
        HashON.put(s, s);
        assertEquals(HashON.get(s), s);
    }

    @Test
    @DisplayName("empty insert [O(N^2)]")
    public void emptyInsertOn2(){
        var HashON2 = new PerfectHashTableON2<>();
        String s = "";
        HashON2.insert(s);
        assertEquals(HashON2.search(s), s);
    }

    @Test
    @DisplayName("Delete From Empty Table [O(N)]")
    public void DeleteFromEmptyTableOn(){
        var HashON = new PerfectHashTableON<>();
        String s = "book";
        HashON.remove(s);
        assertNotEquals(HashON.get(s), s);
    }

    @Test
    @DisplayName("Delete After insert [O(N)]")
    public void DeleteAfterInsertingTableOn(){
        var HashON = new PerfectHashTableON<>();
        String s = "book";
        HashON.put(s,s);
        assertEquals(HashON.get(s), s);
        HashON.remove(s);
        assertNotEquals(HashON.get(s), s);
    }

    @Test
    @DisplayName("Delete From Empty Table [O(N^2)]")
    public void DeleteFromEmptyTableOn2(){
        var HashON2 = new PerfectHashTableON2<>();
        String s = "book";
        HashON2.delete(s);
        assertNotEquals(HashON2.search(s), s);
    }

    @Test
    @DisplayName("Delete After insert [O(N^2)]")
    public void DeleteAfterInsertingTableOn2(){
        var HashON2 = new PerfectHashTableON2<>();
        String s = "book";
        HashON2.insert(s);
        assertEquals(HashON2.search(s), s);
        HashON2.delete(s);
        assertNotEquals(HashON2.search(s), s);
    }

    @Test
    @DisplayName("Repeated insertion doesn't matter [O(N)]")
    public void RepeatedInsertionsOn(){
        var HashON = new PerfectHashTableON<>();
        String s = "book";
        int repetitions = 10;
        for(int i = 0; i < repetitions; i++){
            HashON.put(s,s);
        }
        HashON.remove(s);
        assertNotEquals(HashON.get(s), s);
    }

    @Test
    @DisplayName("Repeated insertion doesn't matter [O(N^2)]")
    public void RepeatedInsertionsOn2(){
        var HashON2 = new PerfectHashTableON2<>();
        String s = "book";
        int repetitions = 10;
        for(int i = 0; i < repetitions; i++){
            HashON2.insert(s);
        }
        HashON2.delete(s);
        assertNotEquals(HashON2.search(s), s);
    }

    @Test
    @DisplayName("Batch Insertion [O(N)]")
    public void BatchInsertionOn() throws IOException {
        var HashON = new PerfectHashTableON<String, String>();
        var bo = new BatchOperations();

        String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\insertTest.txt";

        BufferedReader reader = new BufferedReader(new FileReader(batchPath));

        List<String> wordsList = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");

            Collections.addAll(wordsList, words);
        }
        reader.close();

        bo.batchInsert(batchPath, HashON);

        for (String word : wordsList) {
            assertEquals(HashON.get(word), word);
        }
    }

    @Test
    @DisplayName("Batch Deletion [O(N)]")
    public void BatchDeletionOn() throws IOException {
        var HashON = new PerfectHashTableON<String, String>();
        var bo = new BatchOperations();

        String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\insertTest.txt";

        BufferedReader reader = new BufferedReader(new FileReader(batchPath));

        List<String> wordsList = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");

            Collections.addAll(wordsList, words);
        }
        reader.close();

        bo.batchDelete(batchPath, HashON);

        for (String word : wordsList) {
            assertNotEquals(HashON.get(word), word);
        }
    }


    @Test
    @DisplayName("Batch Insertion [O(N^2)]")
    public void BatchInsertionOn2() throws IOException {
        var HashON2 = new PerfectHashTableON2<>();
        String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\insertTest.txt";

        BufferedReader reader = new BufferedReader(new FileReader(batchPath));

        List<String> wordsList = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");

            Collections.addAll(wordsList, words);
        }
        reader.close();

        HashON2.batchInsertFromFile(batchPath);
        for (String word : wordsList) {
            assertEquals(HashON2.search(word), word);
        }
    }
        @Test
        @DisplayName("Batch Deletion [O(N^2)]")
        public void BatchDeletionOn2() throws IOException {
            var HashON2 = new PerfectHashTableON2<>();
            String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\deleteTest.txt";

            BufferedReader reader = new BufferedReader(new FileReader(batchPath));

            List<String> wordsList = new ArrayList<>();

            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                Collections.addAll(wordsList, words);
            }
            reader.close();

            HashON2.batchDeleteFromFile(batchPath);
            for (String word : wordsList) {
                assertNotEquals(HashON2.search(word), word);
            }
        }



        // O(N) with same chars starts here
        @Test
        @DisplayName("Batch Insertion With Same Chars [O(N)]")
        public void BatchInsertionWithSameCharsOn() throws IOException {
            var HashON = new PerfectHashTableON<String, String>();
            var bo = new BatchOperations();
            String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\CustomInsert.txt";

            BufferedReader reader = new BufferedReader(new FileReader(batchPath));

            List<String> wordsList = new ArrayList<>();

            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                Collections.addAll(wordsList, words);
            }
            reader.close();

            bo.batchInsert(batchPath, HashON);
            for (String word : wordsList) {
                assertEquals(HashON.get(word), word);
            }
        }

    @Test
    @DisplayName("Batch Deletion With Same Chars [O(N)]")
    public void BatchDeletionWithSameCharsOn() throws IOException {
        var HashON = new PerfectHashTableON<String, String>();
        var bo = new BatchOperations();
        String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\CustomInsert.txt";
        String batchPath_2 = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\CustomInsert_2.txt";

        BufferedReader reader_1 = new BufferedReader(new FileReader(batchPath));
        BufferedReader reader_2 = new BufferedReader(new FileReader(batchPath_2));
        List<String> wordsList_1 = new ArrayList<>();
        List<String> wordsList_2 = new ArrayList<>();

        String line;

        while ((line = reader_1.readLine()) != null) {
            String[] words_1 = line.split("\\s+");

            Collections.addAll(wordsList_1, words_1);
        }

        while ((line = reader_2.readLine()) != null) {
            String[] words_2 = line.split("\\s+");

            Collections.addAll(wordsList_2, words_2);
        }

        reader_1.close();
        reader_2.close();
        bo.batchInsert(batchPath, HashON);
        bo.batchInsert(batchPath_2, HashON);
//        bo.batchDelete(batchPath_2, HashON);

        for (String word : wordsList_1) {
            assertEquals(HashON.get(word), word);
        }

        for (String word : wordsList_2) {
            assertEquals(HashON.get(word), word);
        }

    }

    @Test
    @DisplayName("multiple Batch Insertion With Same Chars [O(N)]")
    public void MultipleBatchInsertionWithSameCharsOn() throws IOException {
        var HashON = new PerfectHashTableON<String, String>();
        var bo = new BatchOperations();
        String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\CustomInsert.txt";

        BufferedReader reader = new BufferedReader(new FileReader(batchPath));

        List<String> wordsList = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");

            Collections.addAll(wordsList, words);
        }
        reader.close();

        bo.batchInsert(batchPath, HashON);
        bo.batchInsert(batchPath, HashON);
        bo.batchDelete(batchPath, HashON);

        for (String word : wordsList) {
            assertNotEquals(HashON.get(word), word);
        }
    }
        // O(N) with same chars ends here
    @Test
    @DisplayName("Batch Insertion With Same Chars [O(N^2)]")
    public void BatchInsertionWithSameCharsOn2() throws IOException {
        var HashON2 = new PerfectHashTableON2<>();
        String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\CustomInsert.txt";

        BufferedReader reader = new BufferedReader(new FileReader(batchPath));

        List<String> wordsList = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");

            Collections.addAll(wordsList, words);
        }
        reader.close();

        HashON2.batchInsertFromFile(batchPath);
        for (String word : wordsList) {
            assertEquals(HashON2.search(word), word);
        }
    }

    @Test
    @DisplayName("multiple Batch Insertion With Same Chars [O(N^2)]")
    public void MultipleBatchInsertionWithSameCharsOn2() throws IOException {
        var HashON2 = new PerfectHashTableON2<>();
        String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\CustomInsert.txt";

        BufferedReader reader = new BufferedReader(new FileReader(batchPath));

        List<String> wordsList = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");

            Collections.addAll(wordsList, words);
        }
        reader.close();

        HashON2.batchInsertFromFile(batchPath);
        HashON2.batchInsertFromFile(batchPath);
        HashON2.batchDeleteFromFile(batchPath);
        for (String word : wordsList) {
            assertNotEquals(HashON2.search(word), word);
        }
    }

    @Test
    @DisplayName("Batch Deletion With Same Chars [O(N^2)]")
    public void BatchDeletionWithSameCharsOn2() throws IOException {
        var HashON2 = new PerfectHashTableON2<>();
        String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\CustomInsert.txt";
        String batchPath_2 = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\CustomInsert_2.txt";

        BufferedReader reader_1 = new BufferedReader(new FileReader(batchPath));
        BufferedReader reader_2 = new BufferedReader(new FileReader(batchPath_2));
        List<String> wordsList_1 = new ArrayList<>();
        List<String> wordsList_2 = new ArrayList<>();

        String line;

        while ((line = reader_1.readLine()) != null) {
            String[] words_1 = line.split("\\s+");

            Collections.addAll(wordsList_1, words_1);
        }

        while ((line = reader_2.readLine()) != null) {
            String[] words_2 = line.split("\\s+");

            Collections.addAll(wordsList_2, words_2);
        }

        reader_1.close();
        reader_2.close();

        HashON2.batchInsertFromFile(batchPath);
        HashON2.batchInsertFromFile(batchPath_2);
        HashON2.batchDeleteFromFile(batchPath_2);

        for (String word : wordsList_1) {
            assertEquals(HashON2.search(word), word);
        }

        for (String word : wordsList_2) {
            assertNotEquals(HashON2.search(word), word);
        }
    }



    // Fill here
    @Test
    @DisplayName("Huge batch insertion os size 200+ O(N):")
    public void HugeBatchInsertion_200_ON() throws IOException {
        var HashON = new PerfectHashTableON<String, String>();
        var bo = new BatchOperations();
        String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\bigInsertion(200+).txt";

        BufferedReader reader = new BufferedReader(new FileReader(batchPath));

        List<String> wordsList = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");

            Collections.addAll(wordsList, words);
        }
        reader.close();

        bo.batchInsert(batchPath, HashON);
        for (String word : wordsList) {
            assertEquals(HashON.get(word), word);
        }

    }

    @Test
    @DisplayName("Huge Batch Insertion [O(N^2)] 200+")
    public void HugeBatchInsertion_200_On2() throws IOException {
        var HashON2 = new PerfectHashTableON2<>();
        String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\bigInsertion(200+).txt";

        BufferedReader reader = new BufferedReader(new FileReader(batchPath));

        List<String> wordsList = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");

            Collections.addAll(wordsList, words);
        }
        reader.close();

        HashON2.batchInsertFromFile(batchPath);
        for (String word : wordsList) {
            assertEquals(HashON2.search(word), word);
        }
    }

    @Test
    @DisplayName("Huge Batch Insertion [O(N^2)] 500+")
    public void HugeBatchInsertion_500_On2() throws IOException {
        var HashON2 = new PerfectHashTableON2<>();
        String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\bigInsertion(500+).txt";

        BufferedReader reader = new BufferedReader(new FileReader(batchPath));

        List<String> wordsList = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");

            Collections.addAll(wordsList, words);
        }
        reader.close();

        HashON2.batchInsertFromFile(batchPath);
        for (String word : wordsList) {
            assertEquals(HashON2.search(word), word);
        }
    }

    @Test
    @DisplayName("Huge Batch Insertion [O(N^2)] 1000+")
    public void HugeBatchInsertion_1000_On2() throws IOException {
        var HashON2 = new PerfectHashTableON2<>();
        String batchPath = "C:\\Users\\ALI\\Documents\\GitHub\\Hashing\\src\\bigInsertion(1000+).txt";

        BufferedReader reader = new BufferedReader(new FileReader(batchPath));

        List<String> wordsList = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");

            Collections.addAll(wordsList, words);
        }
        reader.close();

        HashON2.batchInsertFromFile(batchPath);
        for (String word : wordsList) {
            assertEquals(HashON2.search(word), word);
        }
    }

    

}