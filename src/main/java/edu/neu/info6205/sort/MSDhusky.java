package edu.neu.info6205.sort;

import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;
import edu.neu.info6205.Util.ChineseComparator;
import edu.neu.info6205.Util.FileUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * main idea is copied from class slides
 * https://northeastern.instructure.com/courses/91206/files/12221171?module_item_id=6199667
 * very similiar to MSDByteArray but instead of ByteNode Object, it combines the bytearray from the CollationKey and bytearray from
 * Unicode form of the original characters. The array size are fixed for all kinds of input length.
 *
 * Not working properly, the idea was that having a fixed length of byte array to hold both word byte and collator key byte
 * it is easy to keep track of those two byte array but it somehow mess up with msd. please see the most
 * more correct one @ MSDhusk
 */
public class MSDhusky {

    public int byteAt(byte[] s, int d)
    {

//        System.out.println();
//        for(byte x:t){
//            System.out.printf("%x",x);
//            //if(s==-1) System.out.println("cccccccccc");
//        }
//        String chc=new String(getString(s));
//        System.out.println(chc);
//        System.out.println();
        if (d < s.length) return s[d]&0xFF;//for test only
        else return -1;
    }
    //FFFFFFFFFFFFFFFFFFFF
    public void sort(String[] a) throws IOException {
        Collator co = Collator.getInstance(ULocale.CHINA);
        byte[][] aux = new byte[a.length][];
        byte[][] test1=new byte[a.length][21];


        for(int i=0;i<a.length;i++){
            byte[] ch=co.getCollationKey(a[i]).toByteArray();
            byte[] c=a[i].getBytes(StandardCharsets.UTF_8);
            for(byte o:c){
                System.out.println(o);
            }

            System.arraycopy(ch,0,test1[i],0,ch.length); // trying to use array copy
            System.arraycopy(c,0,test1[i],12,c.length);
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
//            outputStream.write( ch );
//            outputStream.write( c );
//
//            test1 [i] = outputStream.toByteArray( );





        }





//        for(int k=0;k< test1.length;k++){
//            for(int i=0;i<11;i++){
//                System.out.printf("%x",test1[k][i]);
//            }
//            System.out.println();
//            for(int i=12;i<21;i++){
//                System.out.printf("%x",test1[k][i]);
//            }
//        }

        sort(test1, aux, 0, a.length -1, 0);
        for(int b=0;b<a.length;b++){
            a[b]=new String(getString(aux[b]));
        }
    }
    private byte[] getString(byte[] xs){

        return Arrays.copyOfRange(xs,12,21);//for test only
    }

    private void sort(byte[][] a, byte[][] aux, int lo, int hi, int d)
    {

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
                if(d<12) sort(a, aux, lo + count[r], lo + count[r + 1] - 1, d + 1); //count the first 12 bytes

    }
    public static void main(String[] args) throws IOException {
       String[] a = FileUtil.readFileInRange("shuffledChinese.txt",15);
        String[] b = FileUtil.readFileInRange("shuffledChinese.txt",15);
//        Collator b = Collator.getInstance(Locale.CHINA);
        ChineseComparator y=new ChineseComparator();
//        Collator co = Collator.getInstance(ULocale.CHINA);
//        byte[] t=co.getCollationKey("宋雪光").toByteArray();
//        for(byte x:t){
//            System.out.printf("%x",x);
//        }
//        System.out.println();
//       System.out.println(t.length);
        //袁继鹏,艾宏
       // String [] a={"袁继鹏","艾宏","宋雪光"};
        MSDhusky ti=new MSDhusky();
        ti.sort(a);
//        byte[]r=new byte[2];
//        Arrays.fill(r,(byte)-99);
//       System.out.printf("%x",r[0]);
//       System.out.println(r[0]==-1);

       Arrays.sort(b,(x1, x2)-> y.compare(x1,x2));
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]+", "+b[i]);
        }
//        for(String x:a){
//            System.out.println(x);
//        }
//        for(int i=0;i<a.length;i++){
//            assertEquals(a[i],b[i]);
//
//        }

    }
}