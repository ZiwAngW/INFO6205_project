import Util.FileUtil;
import Util.WordNode;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;
import sort.MSDradix;

import java.io.IOException;
import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class MSDtestWithNode {

    @Test
    public void GeneralTest1() throws BadHanyuPinyinOutputFormatCombination {
//        WordNode [] a ={new WordNode("这"),new WordNode("一"),new WordNode("晚")};
        String [] a ={"晚","一","这"};
        String [] b ={"晚","一","这"};
        MSDradix x= new MSDradix();
        x.sort(a);
        Arrays.sort(b);
        for(int i =0;i<3;i++){
            assertEquals(a[i],b[i]);
        }
    }

    @Test
    public void GeneralTest2() throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",10);
        String[] b = FileUtil.readFileInRange("shuffledChinese.txt",10);
        Collator collator = Collator.getInstance(Locale.CHINA);
        Arrays.sort(a,(x1, x2)->collator.compare(x1,x2));
        MSDradix x= new MSDradix();
        x.sort(b);

        for(int i =0;i<10;i++){
            assertEquals(b[i],a[i]);
        }
    }
}
