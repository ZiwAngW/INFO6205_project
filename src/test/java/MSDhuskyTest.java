import edu.neu.info6205.Util.ChineseComparator;
import edu.neu.info6205.Util.FileUtil;
import edu.neu.info6205.sort.MSDhusky;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MSDhuskyTest {
    @Test
    public void test1() throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",10);
        String[] b = FileUtil.readFileInRange("shuffledChinese.txt",10);
//        Collator b = Collator.getInstance(Locale.CHINA);
        ChineseComparator y=new ChineseComparator();

        MSDhusky e=new MSDhusky();
        e.sort(a);
        Arrays.sort(b, y::compare);
//        for(int i=0;i<a.length;i++){
//            System.out.println(a[i]+" "+b[i]);
//        }
        double correct=0;
        for(int i=0;i<a.length;i++){
            assertEquals(a[i],b[i]);
            if (a[i].equals(b[i])) {
                correct++;
            }
        }
        System.out.println(correct/a.length);

    }
}
