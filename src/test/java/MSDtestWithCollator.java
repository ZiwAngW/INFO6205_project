import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;
import edu.neu.info6205.Util.FileUtil;
import edu.neu.info6205.sort.MSDCollator;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MSDtestWithCollator {
    @Test
    public void GeneralTest1()  {
        String [] a ={"这","一","晚"};
        String [] b ={"晚","一","这"};
        MSDCollator x= new MSDCollator();
        x.sort(a);
        for(int i =0;i<3;i++){
            assertEquals(a[i],b[i]);
        }
    }
    @Test
    public void GeneralTest2() throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",1000000);
        String[] test =  FileUtil.readFileInRange("shuffledChinese.txt",1000000);
        Collator collator = Collator.getInstance(ULocale.CHINA);
        Arrays.sort(a,(x1, x2)->collator.compare(x1,x2));
        MSDCollator x= new MSDCollator();
        x.sort(test);

        for(int i =0;i<10;i++){
            assertEquals(test[i],a[i]);
        }
    }
    @Test
    public void ByteAtTest(){
        Collator collator = Collator.getInstance(ULocale.CHINA);
        byte[] test1=collator.getCollationKey("啊").toByteArray();
        MSDCollator x= new MSDCollator();
        for(int i=0;i<test1.length;i++){
            assertEquals(x.byteAt(collator.getCollationKey("啊"),i),test1[i]&0xFF);
        }
    }
}
