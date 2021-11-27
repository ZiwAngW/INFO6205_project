package sort;

import Util.WordNode;

public class MSDradix {
    private static int charAt(WordNode s, int d)
    {
        if (d < s.getPinyin().length()) return s.getPinyin().charAt(d);
        else return -1;
    }
    public static void sort(WordNode[] a)
    {
        WordNode[] aux = new WordNode[a.length];
        sort(a, aux, 0, a.length - 1, 0);
    }

    private static void sort(WordNode[] a, WordNode[] aux, int lo, int hi, int d)
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
}
