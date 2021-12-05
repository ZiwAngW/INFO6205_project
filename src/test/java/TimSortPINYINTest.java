import edu.neu.info6205.Util.FileUtil;
import edu.neu.info6205.Util.PinyinUtil;
import edu.neu.info6205.sort.TimSortPINYIN;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class TimSortPINYINTest {
    @Test
    public void testSort() {
        String[] xs = new String[3];
        xs[0]="好";//hao
        xs[1]="东";//Dong
        xs[2]="西";//xi
        TimSortPINYIN test=new TimSortPINYIN();
        test.sort(xs);

        assertTrue("success",xs[0].equals("东"));
        assertTrue("success",xs[1].equals("好"));
        assertTrue("success",xs[2].equals("西"));
    }

    @Test //since tim sort is pinyin order
    public void test_fileSort() throws IOException {
        String[] xs= FileUtil.readFileInRange("resource/shuffledChinese.txt",100);
        for(int i=0;i<xs.length;i++){
            xs[i] = PinyinUtil.getPinyin(xs[i]);
        }
        TimSortPINYIN test=new TimSortPINYIN();
        test.sort(xs);

        Arrays.sort(xs);

        for(int i=0;i<100;i++){
            assertTrue("success",xs[i].equals(xs[i]));
        }
    }

}
