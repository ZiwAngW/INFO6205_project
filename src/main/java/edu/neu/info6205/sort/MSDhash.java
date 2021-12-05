package edu.neu.info6205.sort;

import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;
import edu.neu.info6205.Util.ChineseComparator;
import edu.neu.info6205.Util.FileUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * main idea is copied from class slides
 * https://northeastern.instructure.com/courses/91206/files/12221171?module_item_id=6199667
 * very similiar to MSDByteArray but instead of ByteNode Object, it stores in hashmap.
 */

public class MSDhash {
    public int byteAt(byte[] s, int d)
    {
        if (d < s.length) return s[d]&0xFF;
        else return -1;
    }
    public void sort(String[] a)
    {
        Collator co = Collator.getInstance(ULocale.CHINA);
        byte[][] aux = new byte[a.length][];
        HashMap<byte[],String> ob= new HashMap<>();
        byte[][] test=new byte[a.length][];
        for(int i=0;i< a.length;i++){
            test[i]=co.getCollationKey(a[i]).toByteArray();
            ob.put(test[i],a[i]);
        }
        sort(test, aux, 0, a.length -1, 0);
        for(int j=0;j<test.length;j++){
            a[j]=ob.get(test[j]);
        }
    }

    private void sort(byte[][] a, byte[][] aux, int lo, int hi, int d)
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

    public static void main(String[] args) throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",5);
        String[] b = FileUtil.readFileInRange("shuffledChinese.txt",5);
//        Collator b = Collator.getInstance(Locale.CHINA);
        ChineseComparator y=new ChineseComparator();

        MSDhash e=new MSDhash();
        e.sort(a);
        Arrays.sort(b,(x1,x2)-> y.compare(x1,x2));
        for(String x:a){
            System.out.println(x);
        }
        for(int i=0;i<a.length;i++){
            assertEquals(a[i],b[i]);
        }

    }
}
