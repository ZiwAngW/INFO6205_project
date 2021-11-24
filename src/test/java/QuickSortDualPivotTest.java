import org.junit.Test;
import sort.DaulPivotQuick;
import static org.junit.Assert.assertTrue;

public class QuickSortDualPivotTest {
	@Test
	public void testSort() throws Exception{
		String[] xs = new String[3];
		xs[0]="好";//hao
		xs[1]="东";//Dong
		xs[2]="西";//xi
		DaulPivotQuick test=new DaulPivotQuick();
		test.Sort(xs);

		assertTrue("success",xs[0].equals("东"));
		assertTrue("success",xs[1].equals("好"));
		assertTrue("success",xs[2].equals("西"));

	}

}
