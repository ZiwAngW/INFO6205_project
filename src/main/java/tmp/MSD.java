package tmp;

import Util.FileUtil;
import Util.PinyinUtil;

import java.io.IOException;
import java.util.Arrays;

public class MSD {
    private static int charAt(String s, int d)
    {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }
    public static void sort(String[] a)
    {
        String[] aux = new String[a.length];
        sort(a, aux, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, String[] aux, int lo, int hi, int d)
    {
        int R = 256;
        if (hi <= lo) return;
        int[] count = new int[R+2];
        for (int i = lo; i <= hi; i++)
            count[charAt(a[i], d) + 2]++;
        for (int r = 0; r < R+1; r++)
            count[r+1] += count[r];
        for (int i = lo; i <= hi; i++)
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];
        for (int r = 0; r < R; r++)
            sort(a, aux, lo + count[r], lo + count[r+1] - 1, d+1);
    }

    public static void main(String[] args) throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",5);
        String[] b = Arrays.stream(a).map(PinyinUtil::getPinyin).toArray(String[]::new);
//        Collator b = Collator.getInstance(Locale.CHINA);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        sort(b);
        System.out.println(Arrays.toString(b));
        Arrays.sort(b);
        System.out.println(Arrays.toString(b));
    }
}
