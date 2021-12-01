import Util.ChineseComparator;
import Util.FileUtil;
import org.junit.Test;
import sort.MSDhusk;

import java.io.IOException;
import java.util.Arrays;

public class MSDhuskTest {
    @Test
    public void test1() throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",1000000);
        String[] b = FileUtil.readFileInRange("shuffledChinese.txt",1000000);
//        Collator b = Collator.getInstance(Locale.CHINA);
        ChineseComparator y=new ChineseComparator();

        MSDhusk e=new MSDhusk();
        e.sort(a);
        Arrays.sort(b,(x1, x2)-> y.compare(x1,x2));
//        for(int i=0;i<a.length;i++){
//            System.out.println(a[i]+" "+b[i]);
//        }
        double correct=0;
        for(int i=0;i<a.length;i++){
            //assertEquals(a[i],b[i]);
            if(a[i].equals(b[i])){
                correct++;
            }
        }
        System.out.println(correct/a.length);

    }
}
