package sort;
import Util.ChineseComparator;
import Util.Swaper;
//Creadit https://cs.stackexchange.com/questions/24092/dual-pivot-quicksort-reference-implementation
public class DaulPivotQuick {
	void sort(String[] A, int left, int right) {
		ChineseComparator t=new ChineseComparator();
	    if (right > left) {
	        if (t.compare(A[left], A[right])>0) Swaper.swap(A, left, right);
	        String p = A[left];
	        String q = A[right];
	        int l = left + 1, g = right - 1, k = l;
	        while (k <= g) {
	            if (t.compare(A[k],p)<0) {
	                Swaper.swap(A, k, l);
	                ++l;
	            } else if (t.compare(A[k],q)>=0) {
	                while (t.compare(A[g], q)>0 && k < g) --g;
	                Swaper.swap(A, k, g);
	                --g;
	                if (t.compare(A[k], p)<0) {
	                    Swaper.swap(A, k, l);
	                    ++l;
	                }
	            }
	            ++k;
	        }
	        --l; 
	        ++g;
	        Swaper.swap(A, left, l);
	        Swaper.swap(A, right, g);
	        sort(A, left, l - 1);
	        sort(A, l + 1, g - 1);
	        sort(A, g + 1, right);
	    }
	}
	public void Sort(String [] arr) {
		sort(arr,0,arr.length-1);
	}
	
}
