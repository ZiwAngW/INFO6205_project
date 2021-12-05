package edu.neu.info6205.sort;

import edu.neu.info6205.Util.WordNode;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * main idea is copied from class slides
 * https://northeastern.instructure.com/courses/91206/files/12221171?module_item_id=6199667
 * MSD algorithm with transfer the input into PINYIN with PIINYIN4j and use pinyin for count
 */
public class MSDWordNode {
    //fix
    //due to 绿 transfer to pinyin has a special character, it's at some position in ascii table
    //so it cannot be set to 26 directly
    private int charAt(WordNode s, int d) {
        if (d < s.getPinyin().length()) return s.getPinyin().charAt(d);
        else return 58;
    }

    public void sort(String[] a) {
        WordNode[] aux = new WordNode[a.length];
        WordNode[] wordNodeArray = new WordNode[a.length];
        try {
            for (int i = 0; i < a.length; i++) {
                wordNodeArray[i] = new WordNode(a[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        sort(wordNodeArray, aux, 0, a.length - 1, 0);
        for (int i = 0; i < a.length; i++) {
            a[i] = wordNodeArray[i].getChineseChar();
        }
    }

    private void sort(WordNode[] a, WordNode[] aux, int lo, int hi, int d) {
        int R = 64;
        if (hi <= lo) return;
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            int ab = charAt(a[i], d) - 59;
            count[ab + 2]++;
        }
        for (int r = 0; r < R + 1; r++)
            count[r + 1] += count[r];
        for (int i = lo; i <= hi; i++) {
            int ab = charAt(a[i], d) - 59;
            aux[count[ab + 1]++] = a[i];
        }
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];
        for (int r = 0; r < R; r++)
            sort(a, aux, lo + count[r], lo + count[r + 1] - 1, d + 1);
    }
}
