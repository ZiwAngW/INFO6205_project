package sort;

import Util.ChineseUtil;

import java.text.Collator;

import java.util.Locale;

public class LSDcollator {
    static Collator co = Collator.getInstance(Locale.CHINA);
    public int findLongestLength(String[] a) {
        int longest = 0;
        for (int i = 0; i < a.length; ++i) {
            byte[] l= ChineseUtil.toByteArray(a[i]);
            if (l.length > longest) {
                longest = l.length;
            }
        }
        return longest;
    }

    public int findByteAtInString(int d, String a) {
        byte[] source = ChineseUtil.toByteArray(a);
        if (d < 0 || d >= source.length) {
            return 0;
        }
        return source[d]&0xFF;
    }

    public void sort(String[] a) {
        int R = 1024;
        int N = a.length;
        int W = findLongestLength(a);
        String[] aux = new String[N];
        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++){
                int c = findByteAtInString(d, a[i]);
                count[c+1]++;
            }
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];
            for (int i = 0; i < N; i++){
                int c = findByteAtInString(d, a[i]);
                aux[count[c]++] = a[i];
            }
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }

//    public static void main(String[] args) throws IOException {
//        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",5);
//
////        Collator b = Collator.getInstance(Locale.CHINA);
//        System.out.println(Arrays.toString(a));
//        sort(a);
//        System.out.println(Arrays.toString(a));
//    }
}