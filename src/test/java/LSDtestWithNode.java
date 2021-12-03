import Util.FileUtil;
import Util.WordNode;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;
import sort.LSDWordNode;


import java.io.IOException;

import java.util.Arrays;
import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;

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
        Arrays.sort(a,(x1, x2)->collator.compare(x1,x2));
        LSDWordNode x= new LSDWordNode();
        x.sort(test);
        for(int i =0;i<10;i++){
            assertEquals(test[i].getChineseChar(),a[i]);
        }
    }
}
