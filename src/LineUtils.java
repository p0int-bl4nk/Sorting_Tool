package sorting;

import java.util.ArrayList;

import static sorting.Main.*;

public class LineUtils {
    static int totalLines = 0;
    static ArrayList<String> input = new ArrayList<>();

    static void getInput() {
        while (scanner.hasNextLine()) {
            input.add(scanner.nextLine());
            totalLines++;
        }
    }

    static void sortLines() {
        if (!(lineType && natural)) {
            return;
        }
        getInput();

        MergeSortString.mergeSort(input, 0, totalLines);
        if (useOutputFile) {
            writer.println("Total lines: " + totalLines + ".");
            writer.println("Sorted data: ");
            for (var i : input) {
                writer.println(i);
            }
            writer.close();
        } else {
            System.out.println("Total lines: " + totalLines + ".");
            System.out.println("Sorted data: ");
            for (var i : input) {
                System.out.println(i);
            }
        }
    }

    static void sortLinesByCount() {
        if (!(lineType && byCount)) {
            return;
        }
        getInput();

        var lineMap = createMapStringKey(input);
        lineMap = valueSort(lineMap);

        if (useOutputFile) {
            writer.println("Total lines: " + totalLines + ".");
            for (var entry : lineMap.entrySet()) {
                writer.printf("%s: %d time(s), %d%%\n", entry.getKey(), entry.getValue(), entry.getValue() * 100 / totalLines);
            }
            writer.close();
        } else {
            System.out.println("Total lines: " + totalLines + ".");
            for (var entry : lineMap.entrySet()) {
                System.out.printf("%s: %d time(s), %d%%\n", entry.getKey(), entry.getValue(), entry.getValue() * 100 / totalLines);
            }
        }
    }
}
