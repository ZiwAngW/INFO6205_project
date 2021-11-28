package sort;


import java.text.Collator;
import java.util.Locale;

public class MSDcollator {
    static Collator co = Collator.getInstance(Locale.CHINA);
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
        int R = 1024;
        if (hi <= lo) return;
        int[] count = new int[R+2];
        for (int i = lo; i <= hi; i++)
            count[byteAt(a[i], d) + 2]++;
        for (int r = 0; r < R+1; r++)
            count[r+1] += count[r];
        for (int i = lo; i <= hi; i++)
            aux[count[byteAt(a[i], d) + 1]++] = a[i];
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];
        for (int r = 0; r < R; r++)
            sort(a, aux, lo + count[r], lo + count[r+1] - 1, d+1);
    }
//    public static void main(String args[]) throws IOException {
//        MSDcollator test=new MSDcollator();
//        String[] x= FileUtil.readFileInRange("shuffledChinese.txt",5);
//        for(String a:x){
//            System.out.println(a);
//        }
//        System.out.println();
//        test.sort(x);
//        for(String a:x){
//            System.out.println(a);
//        }
//    }
}
