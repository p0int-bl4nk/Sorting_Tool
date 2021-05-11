package sorting;

import java.util.ArrayList;

import static sorting.Main.*;

public class WordUtils {
    static int totalWords = 0;
    static ArrayList<String> input = new ArrayList<>();

    static void getInput() {
        while (scanner.hasNext()) {
            input.add(scanner.next());
            totalWords++;
        }
    }

    static void sortWords() {
        if (!(wordType && natural)) {
            return;
        }
        getInput();

        MergeSortString.mergeSort(input, 0, totalWords);

        if (useOutputFile) {
            writer.println("Total words: " + totalWords + ".");
            writer.println("Sorted data: ");
            for (var i : input) {
                writer.println(i);
            }
            writer.close();
        } else {
            System.out.println("Total words: " + totalWords + ".");
            System.out.print("Sorted data: ");
            for (var i : input) {
                System.out.print(i + " ");
            }
        }
    }

    static void sortWordsByCount() {
        if (!(wordType && byCount)) {
            return;
        }
        getInput();

        var wordMap = createMapStringKey(input);
        wordMap = valueSort(wordMap);

        if (useOutputFile) {
            writer.println("Total words: " + totalWords + ".");
            for (var entry : wordMap.entrySet()) {
                writer.printf("%s: %d time(s), %d%%\n", entry.getKey(), entry.getValue(), entry.getValue() * 100 / totalWords);
            }
            writer.close();
        } else {
            System.out.println("Total words: " + totalWords + ".");
            for (var entry : wordMap.entrySet()) {
                System.out.printf("%s: %d time(s), %d%%\n", entry.getKey(), entry.getValue(), entry.getValue() * 100 / totalWords);
            }
        }
    }

}
