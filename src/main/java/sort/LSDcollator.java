package sort;



import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;

public class LSDcollator {
    static Collator co = Collator.getInstance(ULocale.CHINA);
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

//    public static void main(String[] args) throws IOException {
//        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",5);
//
////        Collator b = Collator.getInstance(Locale.CHINA);
//        System.out.println(Arrays.toString(a));
//        sort(a);
//        System.out.println(Arrays.toString(a));
//    }
}
