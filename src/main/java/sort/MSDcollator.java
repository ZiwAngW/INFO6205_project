package sort;


import com.ibm.icu.text.CollationKey;
import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;

public class MSDcollator {
   static Collator co = Collator.getInstance(ULocale.CHINA);

    public int byteAt(CollationKey s, int d)
    {
        byte[] source = s.toByteArray();
        if (d < source.length) return source[d]&0xFF;
        else return -1;
    }
    public void sort(String[] a)
    {
        CollationKey[] collationKeys = new CollationKey[a.length];
        for(int i = 0;i<a.length;i++){
            collationKeys[i] = co.getCollationKey(a[i]);
        }
        CollationKey[] aux = new CollationKey[a.length];
        sort(collationKeys, aux, 0, a.length - 1, 0);
        for(int i = 0;i<a.length;i++){
            a[i] = collationKeys[i].getSourceString();
        }
    }

    private void sort(CollationKey[] a, CollationKey[] aux, int lo, int hi, int d)
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