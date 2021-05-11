package sorting;

import java.io.*;
import java.util.*;

public class Main {
    static boolean longType = false;
    static boolean wordType = false;
    static boolean lineType = false;
    static boolean byCount = false;
    static boolean natural = false;
    static String inputFileName = null;
    static boolean useInputFile = false;
    static String outputFileName = null;
    static boolean useOutputFile = false;
    static Scanner scanner = new Scanner(System.in);
    static PrintWriter writer = null;

    public static void main(final String[] args) {
        Set<String> dataType = Set.of(
                "long",
                "word",
                "line"
        );

        Set<String> sortingType = Set.of(
                "natural",
                "byCount"
        );

        if (args.length == 1) {
            WordUtils.sortWords();
            return;
        }

        Map<String, String> choice = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String argument = args[i];
            String type = ++i < args.length ? args[i] : "default";
            if ("-sortingType".equals(argument)) {
                if (sortingType.contains(type)) {
                    choice.put(argument, type);
                } else {
                    System.out.println("No sorting type defined!");
                    return;
                }
            } else if ("-dataType".equals(argument)) {
                if (dataType.contains(type)) {
                    choice.put(argument, type);
                } else {
                    System.out.println("No data type defined!");
                    return;
                }
            } else if ("-inputFile".equals(argument)) {
                if ("default".equals(type)) {
                    System.out.println("No input file defined!");
                    return;
                }
                inputFileName = type;
                useInputFile = true;
            } else if ("-outputFile".equals(argument)) {
                if ("default".equals(type)) {
                    System.out.println("No output file defined!");
                    return;
                }
                outputFileName = type;
                useOutputFile = true;
            } else {
                System.out.println(argument + " is not a valid parameter. It will be skipped.");
            }
        }

        switch (choice.getOrDefault("-dataType", "default")) {
            case "long":
                longType = true;
                break;
            case "word":
                wordType = true;
                break;
            case "line":
                lineType = true;
                break;
            default:
                wordType = true;
                break;
        }
        switch (choice.getOrDefault("-sortingType", "default")) {
            case "natural":
                natural = true;
                break;
            case "byCount":
                byCount = true;
                break;
            default:
                natural = true;
                break;
        }

        if (!(setScannerToFileIfProvided() && setWriterIfFileProvided())) {
            return;
        }


        LongUtils.sortIntegers();
        LongUtils.sortIntegersByCount();

        WordUtils.sortWords();
        WordUtils.sortWordsByCount();

        LineUtils.sortLines();
        LineUtils.sortLinesByCount();
    }

    static SortedMap<String, Integer> createMapStringKey(ArrayList<String> numbers) {
        SortedMap<String, Integer> map = new TreeMap<>();
        for (var i : numbers) {
            Integer frequency = map.getOrDefault(i, 0);
            if (frequency != 0) {
                map.put(i, ++frequency);
            } else {
                map.put(i, 1);
            }
        }
        return map;
    }

    static <K, V extends Comparable<V> > SortedMap<K, V> valueSort(final Map<K, V> map) {
        
        @SuppressWarnings("ComparatorMethodParameterNotUsed") Comparator<K> valueComparator = (k1, k2) -> {
            int comp = map.get(k1).compareTo(
                    map.get(k2));
            if (comp == 0)
                return 1;
            else
                return comp;
        };

        // SortedMap created using the comparator
        SortedMap<K, V> sorted = new TreeMap<>(valueComparator);

        sorted.putAll(map);

        return sorted;
    }

    static boolean setScannerToFileIfProvided() {
        if (useInputFile) {
            File file = new File(inputFileName);
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.printf("\nFile not found!\nFile name: %s\n", inputFileName);
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    static boolean setWriterIfFileProvided() {
        if (useOutputFile) {
            try {
                writer = new PrintWriter(outputFileName);
            } catch (IOException e) {
                System.out.println("Error: Couldn't write to " + outputFileName + "!");
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
