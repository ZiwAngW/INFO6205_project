import edu.neu.info6205.Util.FileUtil;
import edu.neu.info6205.sort.DualPivotQuick;
import org.junit.Test;

import java.io.IOException;
import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.Assert.assertTrue;

public class QuickSortDualPivotTest {
	@Test
	public void testSort() throws Exception{
		String[] xs = new String[3];
		xs[0]="好";//hao
		xs[1]="东";//Dong
		xs[2]="西";//xi
		DualPivotQuick test=new DualPivotQuick();
		test.sort(xs);

		assertTrue("success",xs[0].equals("东"));
		assertTrue("success",xs[1].equals("好"));
		assertTrue("success",xs[2].equals("西"));
	}
	@Test
	public void test_fileSort() throws IOException {
		String[] xs=FileUtil.readFileInRange("resource/shuffledChinese.txt",5);
		String[] xp=FileUtil.readFileInRange("resource/shuffledChinese.txt",5);

		DualPivotQuick test=new DualPivotQuick();
		test.sort(xs);

		Collator collator = Collator.getInstance(Locale.CHINA);
		Arrays.sort(xp,(x1, x2)->collator.compare(x1,x2));

		for(int i=0;i<5;i++){
			assertTrue("success",xs[i].equals(xp[i]));
		}

	}

}
