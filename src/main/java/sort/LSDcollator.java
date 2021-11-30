package sort;



import Util.ChineseUtil;
import Util.FileUtil;
import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;

import java.io.IOException;
import java.util.Arrays;

public class LSDcollator {
    static Collator co = Collator.getInstance(ULocale.CHINA);
//    static Collator co = Collator.getInstance(Locale.CHINA);
    public int findLongestLength(String[] a) {
        int longest = 0;
        for (String s : a) {
            byte[] l = co.getCollationKey(s).toByteArray();
            if (l.length > longest) {
                longest = l.length;
            }
        }
        return longest;
    }

    public int findByteAtInString(int d, String a) {
        byte[] source = co.getCollationKey(a).toByteArray();
        if (d < 0 || d >= source.length) {
            return 0;
        }
        return source[d]&0xFF;
    }

    public void sort(String[] a) {
        int R = 256;
        int N = a.length;
        int W = findLongestLength(a);
        String[] aux = new String[N];
        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (String s : a) {
                int c = findByteAtInString(d, s);
                count[c + 1]++;
            }
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];
            for (String s : a) {
                int c = findByteAtInString(d, s);
                aux[count[c]++] = s;
            }
            System.arraycopy(aux, 0, a, 0, N);
        }
    }

    public static void main(String[] args) throws IOException {
//        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",5);
//        LSDcollator lsDcollator = new LSDcollator();
//        System.out.println(Arrays.toString(a));
//        lsDcollator.sort(a);
//        System.out.println(Arrays.toString(a));
        String a = "阿清";
        String aa = "阿请";
//        System.out.println(Arrays.toString(co.getCollationKey(a).toByteArray()));
        System.out.println(Arrays.toString(ChineseUtil.toByteArray(a)));
//        System.out.println(Arrays.toString(co.getCollationKey(aa).toByteArray()));
        System.out.println(Arrays.toString(ChineseUtil.toByteArray(aa)));
        byte[] arr = {117, 127, 87, -73, 126, -95, 1, 7, 1, 7, 0};
        for(byte b:arr){
            System.out.print(b & 0xFF);
            System.out.print(" ");
        }
    }
}
