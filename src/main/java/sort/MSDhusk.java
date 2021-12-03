package sort;

import Util.ChineseComparator;
import Util.FileUtil;
import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class MSDhusk {
    public int byteAt(byte[] s, int d) {
        if (d < s.length) return s[d] & 0xFF; // when doing msd the right part of array seems not important ?
        else return -1;
    }

    public void sort(String[] a) {
        Collator co = Collator.getInstance(ULocale.CHINA);
        byte[][] aux = new byte[a.length][];

        byte[][] test = new byte[a.length][];
        for (int i = 0; i < a.length; i++) {
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                outputStream.write(co.getCollationKey(a[i]).toByteArray());
                outputStream.write(a[i].getBytes(StandardCharsets.UTF_8));
                test[i] = outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        sort(test, aux, 0, a.length - 1, 0);
        for (int j = 0; j < test.length; j++) {

            a[j] = new String(getByteString(test[j]));
        }
    }

    public byte[] getByteString(byte[] a) { // since the key byte and word byte are different, need to find better way to do this
        if (a.length < 20 && a.length >= 15) {
            //System.out.println("less than 20");
            return Arrays.copyOfRange(a, 9, a.length);
        } else if (a.length < 15) {

            return Arrays.copyOfRange(a, 8, a.length);
        } else {
            return Arrays.copyOfRange(a, 11, a.length);
        }

    }

    private void sort(byte[][] a, byte[][] aux, int lo, int hi, int d) {
        int R = 256;
        if (hi <= lo) return;
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++)
            count[byteAt(a[i], d) + 2]++;
        for (int r = 0; r < R + 1; r++)
            count[r + 1] += count[r];
        for (int i = lo; i <= hi; i++)
            aux[count[byteAt(a[i], d) + 1]++] = a[i];
        if (hi + 1 - lo >= 0) System.arraycopy(aux, 0, a, lo, hi + 1 - lo);
        for (int r = 0; r < R; r++)
            sort(a, aux, lo + count[r], lo + count[r + 1] - 1, d + 1);
    }

    public static void main(String[] args) throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt", 1000000);
        String[] b = FileUtil.readFileInRange("shuffledChinese.txt", 1000000);
//        Collator b = Collator.getInstance(Locale.CHINA);
        ChineseComparator y = new ChineseComparator();

        MSDhusk e = new MSDhusk();
        e.sort(a);
        Arrays.sort(b, (x1, x2) -> y.compare(x1, x2));
//        for(int i=0;i<a.length;i++){
//            System.out.println(a[i]+" "+b[i]);
//        }
        double correct = 0;
        for (int i = 0; i < a.length; i++) {
            //assertEquals(a[i],b[i]);
            if (a[i].equals(b[i])) {
                correct++;
            }
        }
        System.out.println(correct / a.length * 100 + " % correct");

    }
}
