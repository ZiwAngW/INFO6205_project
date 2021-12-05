package edu.neu.info6205.sort;

import com.ibm.icu.text.CollationKey;
import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;
import edu.neu.info6205.Util.ChineseUtil;

import java.io.IOException;
import java.util.Arrays;

/**
 * main idea is copied from class slides with a minor trick to let LSD handle input with different length
 * https://northeastern.instructure.com/courses/91206/files/12221171?module_item_id=6199667
 * lsd algorithm with transfer the input to CollationKey and use CollationKey.toByteArray for count
 */
public class LSDCollator {
    static Collator co = Collator.getInstance(ULocale.CHINA);
    public int findLongestLength(CollationKey[] a) {
        int longest = 0;
        for (CollationKey s : a) {
            byte[] l = s.toByteArray();
            if (l.length > longest) {
                longest = l.length;
            }
        }
        return longest;
    }

    public int findByteAtInString(int d, CollationKey a) {
        byte[] source = a.toByteArray();
        if (d < 0 || d >= source.length) {
            return 0;
        }
        return source[d]&0xFF;
    }

    public void sort(String[] a) {

        CollationKey[] collationKeys = new CollationKey[a.length];
        for(int i = 0;i<a.length;i++){
            collationKeys[i] = co.getCollationKey(a[i]);
        }
        int R = 256;
        int N = collationKeys.length;
        int W = findLongestLength(collationKeys);
        CollationKey[] aux = new CollationKey[N];
        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (CollationKey s : collationKeys) {
                int c = findByteAtInString(d, s);
                count[c + 1]++;
            }
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];
            for (CollationKey s : collationKeys) {
                int c = findByteAtInString(d, s);
                aux[count[c]++] = s;
            }
            System.arraycopy(aux, 0, collationKeys, 0, N);
        }
        for(int i = 0;i<a.length;i++){
            a[i] = collationKeys[i].getSourceString();
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
