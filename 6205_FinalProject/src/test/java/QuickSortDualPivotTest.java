package test.java;

import static org.junit.Assert.assertFalse;


import org.junit.jupiter.api.Test;

import main.sort.DaulPivotQuick;

public class QuickSortDualPivotTest {
	@Test
	public void testSort() throws Exception{
		String[] xs = new String[3];
		xs[0]="好";//hao
		xs[1]="东";//Dong
		xs[2]="西";//xi
		DaulPivotQuick test=new DaulPivotQuick();
		test.Sort(xs);
	
		assertFalse(xs[0].equals("东"));
		assertFalse(xs[1].equals("好"));
		assertFalse(xs[2].equals("西"));
	}

}
