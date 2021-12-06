import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;
import edu.neu.info6205.Util.FileUtil;
import edu.neu.info6205.sort.TimSort;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class TimSortTest {
    @Test
    public void testSort() {
        String[] xs = new String[3];
        xs[0]="好";//hao
        xs[1]="东";//Dong
        xs[2]="西";//xi
        TimSort test=new TimSort();
        test.sort(xs);

        assertTrue("success",xs[0].equals("东"));
        assertTrue("success",xs[1].equals("好"));
        assertTrue("success",xs[2].equals("西"));
    }
    @Test //since tim sort is pinyin order
    public void test_fileSort() throws IOException {
        String[] xs= FileUtil.readFileInRange("shuffledChinese.txt",100);
        String[] xp=FileUtil.readFileInRange("shuffledChinese.txt",100);

        TimSort test=new TimSort();
        test.sort(xs);

        Collator collator = Collator.getInstance(ULocale.CHINA);
        Arrays.sort(xp,(x1, x2)->collator.compare(x1,x2));

        for(int i=0;i<100;i++){
            assertTrue("success",xs[i].equals(xp[i]));
        }

    }

}
