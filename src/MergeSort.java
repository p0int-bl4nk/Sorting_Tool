package sorting;

import java.util.ArrayList;

class MergeSortInteger {
    public static void mergeSort(ArrayList<Integer> array, int left, int right) {
        if (right <= left + 1) {
            return;
        }
        int middle = (left + right) >>> 1;
        mergeSort(array, left, middle);
        mergeSort(array, middle, right);

        merge(array, left, middle, right);
    }
    private static void merge(ArrayList<Integer> array, int left, int middle, int right) {
        int i = left;
        int j = middle;
        int k = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        while (i < middle && j < right) {
            if (array.get(i) <= array.get(j)) {
                temp.add(array.get(i));
                i++;
            } else {
                temp.add(array.get(j));
                j++;
            }
            k++;
        }

        for ( ; i < middle; i++, k++) {
            temp.add(array.get(i));
        }
        for ( ; j < right; j++, k++) {
            temp.add(array.get(j));
        }

        copyArray(temp, array, left);
    }
    private static void copyArray(ArrayList<Integer> temp, ArrayList<Integer> array, int arrayPos) {
        for (Integer integer : temp) {
            array.set(arrayPos++, integer);
        }
    }
 }

class MergeSortString {
    public static void mergeSort(ArrayList<String> array, int left, int right) {
        if (right <= left + 1) {
            return;
        }
        int middle = (left + right) >>> 1;
        mergeSort(array, left, middle);
        mergeSort(array, middle, right);

        merge(array, left, middle, right);
    }
    private static void merge(ArrayList<String> array, int left, int middle, int right) {
        int i = left;
        int j = middle;
        int k = 0;
        ArrayList<String> temp = new ArrayList<>();
        while (i < middle && j < right) {
            if (array.get(i).compareTo(array.get(j)) < 0) {
                temp.add(array.get(i));
                i++;
            } else {
                temp.add(array.get(j));
                j++;
            }
            k++;
        }

        for ( ; i < middle; i++, k++) {
            temp.add(array.get(i));
        }
        for ( ; j < right; j++, k++) {
            temp.add(array.get(j));
        }

        copyArray(temp, array, left);
    }
    private static void copyArray(ArrayList<String> temp, ArrayList<String> array, int arrayPos) {
        for (String i : temp) {
            array.set(arrayPos++, i);
        }
    }
}
