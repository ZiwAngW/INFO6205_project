import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;
import edu.neu.info6205.Util.FileUtil;
import edu.neu.info6205.sort.LSDCollator;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LSDtestWithCollator {
    @Test
    public void GeneralTest1()  {
        String [] a ={"这","一","晚"};
        String [] b ={"晚","一","这"};
        LSDCollator x= new LSDCollator();
        x.sort(a);
        for(int i =0;i<3;i++){
            assertEquals(a[i],b[i]);
        }
    }
    @Test
    public void GeneralTest2() throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",10);
        String[] test =  FileUtil.readFileInRange("shuffledChinese.txt",10);
        Collator collator = Collator.getInstance(ULocale.CHINA);
        Arrays.sort(a,(x1, x2)->collator.compare(x1,x2));
        LSDCollator x= new LSDCollator();
        x.sort(test);

        for(int i =0;i<10;i++){
            assertEquals(test[i],a[i]);
        }
    }
    @Test
    public void findByteAtTest(){
        Collator collator = Collator.getInstance(ULocale.CHINA);
        byte[] test1=collator.getCollationKey("哦").toByteArray();
        LSDCollator x= new LSDCollator();
        for(int i=0;i<test1.length;i++){
            assertEquals(x.findByteAtInString(i,collator.getCollationKey("哦")),test1[i]&0xFF);

        }
    }
}
