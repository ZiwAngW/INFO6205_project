package tmp;

import Util.FileUtil;
import Util.PinyinUtil;

import java.io.IOException;
import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

public class LSD {

    public static int findLongestLength(String[] a) {
        int longest = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i].length() > longest) {
                longest = a[i].length();
            }
        }
        return longest;
    }

    public static int findCharAtInString(int d, String a) {
        if (d < 0 || d >= a.length()) {
            return 0;
        }
        return a.charAt(d);
    }

    public static void sort(String[] a) {
        int R = 256;
        int N = a.length;
        int W = findLongestLength(a);
        String[] aux = new String[N];
        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++){
                int c = findCharAtInString(d, a[i]);
                count[c+1]++;
            }
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];
            for (int i = 0; i < N; i++){
                int c = findCharAtInString(d, a[i]);
                aux[count[c]++] = a[i];
            }
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }

    public static void main(String[] args) throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",10);
        String[] b = Arrays.stream(a).map(PinyinUtil::getPinyin).toArray(String[]::new);
//        Collator b = Collator.getInstance(Locale.CHINA);
        System.out.println(Arrays.toString(b));
        sort(b);
        System.out.println(Arrays.toString(b));
    }
}
