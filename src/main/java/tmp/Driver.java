package tmp;

import Util.FileUtil;
import Util.PinyinUtil;
import sort.TimSort;
import tmp.husky.PureHuskySort;
import tmp.husky.huskySortUtils.HuskyCoderFactory;

import java.io.IOException;
import java.util.Arrays;

public class Driver {
    public static void main(String[] args) throws IOException {
        String[] a = FileUtil.readFileInRange("shuffledChinese.txt",10);
        String[] b = Arrays.stream(a).map(PinyinUtil::getPinyin).toArray(String[]::new);
        System.out.println(Arrays.toString(b));
        int n = b.length;
        String[] c = Arrays.copyOf(b,n);
        System.out.println("deep copy..");

//        System.out.println("tim sort");
//        TimSort t1 = new TimSort();
//        System.out.println(Arrays.toString(a));
//        t1.timSort(a, n);
//        System.out.println("After tim sort");
//        System.out.println(Arrays.toString(a));

        System.out.println("husky sort");
        final PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        System.out.println(Arrays.asList(c));
        sorter.sort(c);
        System.out.println("After husky sort");
        System.out.println(Arrays.asList(c));

        String[] d = Arrays.copyOf(b,n);
        System.out.println("LSD sort");
        System.out.println(Arrays.asList(d));
        sorter.sort(d);
        System.out.println("After LSD sort");
        System.out.println(Arrays.asList(d));

        String[] e = Arrays.copyOf(b,n);
        System.out.println("MSD sort");
        System.out.println(Arrays.asList(e));
        sorter.sort(e);
        System.out.println("After MSD sort");
        System.out.println(Arrays.asList(e));

        //TODO Dual-pivot Quicksort
    }
}
