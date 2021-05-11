package sorting;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;


import static sorting.Main.*;

public class LongUtils {
    static ArrayList<Integer> input = new ArrayList<>();
    static int totalNumbers = 0;

    static void getInput() {
        while (scanner.hasNext()) {
            String next = scanner.next();
            try {
                input.add(Integer.parseInt(next));
            } catch (NumberFormatException e) {
                System.out.println(next + " is not a long. It will be skipped.");
                continue;
            }
            totalNumbers++;
        }
    }

    static  void sortIntegers() {
        if (!(longType && natural)) {
            return;
        }
        getInput();

        MergeSortInteger.mergeSort(input, 0, input.size());

        if (useOutputFile) {
            writer.println("Total numbers: " + totalNumbers + ".");
            writer.print("Sorted data: ");
            for (var i : input) {
                writer.print(i + " ");
            }
        } else {
            System.out.println("Total numbers: " + totalNumbers + ".");
            System.out.print("Sorted data: ");
            for (var i : input) {
                System.out.print(i + " ");
            }
        }
    }

    static void sortIntegersByCount() {
        if (!(longType && byCount)) {
            return;
        }
        getInput();

        var numberMap = createMapIntegerKey(input);
        numberMap = valueSort(numberMap);

        if (useOutputFile) {
            writer.println("Total numbers: " + totalNumbers + ".");
            for (var entry : numberMap.entrySet()) {
                writer.printf("%d: %d time(s), %d%%\n", entry.getKey(), entry.getValue(), entry.getValue() * 100 / totalNumbers);
            }
        } else {
            System.out.println("Total numbers: " + totalNumbers + ".");
            for (var entry : numberMap.entrySet()) {
                System.out.printf("%d: %d time(s), %d%%\n", entry.getKey(), entry.getValue(), entry.getValue() * 100 / totalNumbers);
            }
        }
    }

    static SortedMap<Integer, Integer> createMapIntegerKey(ArrayList<Integer> numbers) {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        for (int i : numbers) {
            Integer frequency = map.getOrDefault(i, 0);
            if (frequency != 0) {
                map.put(i, ++frequency);
            } else {
                map.put(i, 1);
            }
        }
        return map;
    }

}
