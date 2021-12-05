package edu.neu.info6205.sort;

import edu.neu.info6205.Util.WordNode;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/**
 * Creadit https://www.geeksforgeeks.org/timsort/
 * use pinyin as string, and use JAVA builtin String.compareTo() method
 *  * to compare the unicode difference of two string
 */

public class TimSortPINYIN {
    static int RUN = 32;

    int min(int a, int b) {
        return Math.min(a, b);
    }

    /* This function sorts array from starting index to ending index which is of size atmost RUN */
    void insertionSort(WordNode[] a, int beg, int end) /* function to sort an array with insertion sort */ {
        int i, j;
        WordNode temp;
        for (i = beg + 1; i <= end; i++) {
            temp = a[i];
            j = i - 1;

            while (j >= beg && (temp.getPinyin().compareTo(a[j].getPinyin()) < 0)) {
                a[j + 1] = a[j];
                j = j - 1;
            }
            a[j + 1] = temp;
        }
    }

    /* Function to merge the sorted runs */
    void merge(WordNode[] a, int beg, int mid, int end) {
        int i, j, k;
        int n1 = mid - beg + 1;
        int n2 = end - mid;
        //temporary arrays
        WordNode[] LeftArray = new WordNode[n1];
        WordNode[] RightArray = new WordNode[n2];
        /* copy data to temp arrays */
        for (i = 0; i < n1; i++)
            LeftArray[i] = a[beg + i];
        for (j = 0; j < n2; j++)
            RightArray[j] = a[mid + 1 + j];
        i = 0;
        j = 0;
        k = beg;
        while (i < n1 && j < n2) {
            if (LeftArray[i].getPinyin().compareTo(RightArray[j].getPinyin()) < 0) {
                a[k] = LeftArray[i];
                i++;
            } else {
                a[k] = RightArray[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            a[k] = LeftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            a[k] = RightArray[j];
            j++;
            k++;
        }
    }

    /* function to implement tim sort */
    public void sort(String[] a) {

        WordNode[] nodeArr = new WordNode[a.length];
        try {
            for (int i = 0; i < a.length; i++) {
                nodeArr[i] = new WordNode(a[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        int n = a.length;
        /* Sort individual subarrays of size RUN */
        for (int i = 0; i < n; i += RUN)
            insertionSort(nodeArr, i, min((i + RUN - 1), (n - 1)));
        // Start merging from size RUN (or 32).
        for (int size = RUN; size < n; size = 2 * size) {
            for (int beg = 0; beg < n; beg += 2 * size) {
                /* find ending point of left subarray. The starting point of right sub array is mid + 1 */
                int mid = beg + size - 1;
                int end = min((beg + 2 * size - 1), (n - 1));

                /* Merge subarray a[beg...mid] and a[mid+1...end] */
                if (mid < end)
                    merge(nodeArr, beg, mid, end);
            }
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = nodeArr[i].getChineseChar();
        }
    }
}
