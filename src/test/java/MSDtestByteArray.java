
import Util.FileUtil;
import org.junit.Test;
import sort.MSDByteArray;


import java.io.IOException;

import java.util.Arrays;
import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;

import static org.junit.Assert.assertEquals;

public class MSDtestByteArray {


    @Test
    public void GeneralTest() throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",100000);
        String[] b = FileUtil.readFileInRange("shuffledChinese.txt",100000);
        Collator collator = Collator.getInstance(ULocale.CHINA);
        Arrays.sort(a,(x1, x2)->collator.compare(x1,x2));
        MSDByteArray x= new MSDByteArray();
        x.sort(b);

        for(int i =0;i<10;i++){
            assertEquals(b[i],a[i]);
        }
    }
}
