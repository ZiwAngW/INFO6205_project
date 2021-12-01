import Util.ChineseComparator;
import Util.FileUtil;
import org.junit.Test;
import sort.MSDhash;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MSDtestWithHashMap {
    @Test
    public void test1() throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",1000000);
        String[] b = FileUtil.readFileInRange("shuffledChinese.txt",1000000);
//        Collator b = Collator.getInstance(Locale.CHINA);
        ChineseComparator y=new ChineseComparator();

        MSDhash e=new MSDhash();
        e.sort(a);
        Arrays.sort(b,(x1, x2)-> y.compare(x1,x2));
        for(int i=0;i<a.length;i++){
            assertEquals(a[i],b[i]);
        }

    }
}
