import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;
import edu.neu.info6205.Util.ChineseComparator;
import edu.neu.info6205.Util.FileUtil;
import edu.neu.info6205.sort.MSDWordNode;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MSDtestWithNode {

    @Test
    public void GeneralTest1() {
//        WordNode [] a ={new WordNode("这"),new WordNode("一"),new WordNode("晚")};
        String [] a ={"晚","一","这"};
        String [] b ={"晚","一","这"};
        MSDWordNode x= new MSDWordNode();
        x.sort(a);
        ChineseComparator test= new ChineseComparator();
        Arrays.sort(b,(x1,x2)->test.compare(x1,x2));
        for(int i =0;i<3;i++){
            assertEquals(a[i],b[i]);
        }
    }

    @Test
    // wordnode implementation
    public void GeneralTest2() throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",10);
        String[] b = FileUtil.readFileInRange("shuffledChinese.txt",10);
        Collator collator = Collator.getInstance(ULocale.CHINA);
        Arrays.sort(a,(x1, x2)->collator.compare(x1,x2));
        MSDWordNode x= new MSDWordNode();
        x.sort(b);

        for(int i =0;i<10;i++){
            assertEquals(b[i],a[i]);
        }
    }
}
