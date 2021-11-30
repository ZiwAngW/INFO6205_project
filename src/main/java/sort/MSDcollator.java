package sort;

import Util.FileUtil;
import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;

import java.io.IOException;
//import java.text.Collator;
//import java.util.Locale;

public class MSDcollator {
   static Collator co = Collator.getInstance(ULocale.CHINA);
//    static Collator co = Collator.getInstance(Locale.CHINA);
    public int byteAt(String s, int d)
    {
        byte[] source = co.getCollationKey(s).toByteArray();
        if (d < source.length) return source[d]&0xFF;
        else return -1;
    }
    public void sort(String[] a)
    {
        String[] aux = new String[a.length];
        sort(a, aux, 0, a.length - 1, 0);
    }

    private void sort(String[] a, String[] aux, int lo, int hi, int d)
    {
        int R = 256;
        if (hi <= lo) return;
        int[] count = new int[R+2];
        for (int i = lo; i <= hi; i++)
            count[byteAt(a[i], d) + 2]++;
        for (int r = 0; r < R+1; r++)
            count[r+1] += count[r];
        for (int i = lo; i <= hi; i++)
            aux[count[byteAt(a[i], d) + 1]++] = a[i];
        if (hi + 1 - lo >= 0) System.arraycopy(aux, 0, a, lo, hi + 1 - lo);
        for (int r = 0; r < R; r++)
            sort(a, aux, lo + count[r], lo + count[r+1] - 1, d+1);
    }

}