import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;
import edu.neu.info6205.Util.FileUtil;
import edu.neu.info6205.Util.WordNode;
import edu.neu.info6205.sort.LSDWordNode;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LSDtestWithNode {
    @Test
    public void GeneralTest1() throws BadHanyuPinyinOutputFormatCombination {
        WordNode[] a ={new WordNode("这"),new WordNode("一"),new WordNode("晚")};
        String [] b ={"晚","一","这"};
        LSDWordNode x= new LSDWordNode();
        x.sort(a);
        for(int i =0;i<3;i++){
            assertEquals(a[i].getChineseChar(),b[i]);
        }
    }
    @Test
    // wordnode implementation
    public void GeneralTest2() throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",10);
        WordNode[] test =  FileUtil.readFileInRangeNode("shuffledChinese.txt",10);
        Collator collator = Collator.getInstance(ULocale.CHINA);
        Arrays.sort(a, collator::compare);
        LSDWordNode x= new LSDWordNode();
        x.sort(test);
        for(int i =0;i<10;i++){
            assertEquals(test[i].getChineseChar(),a[i]);
        }
    }
}
