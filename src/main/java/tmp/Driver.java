package tmp;

import Util.FileUtil;
import Util.PinyinUtil;
import sort.DualPivotQuick;
import sort.LSDcollator;
import sort.MSDcollator;
import sort.TimSort;
import tmp.husky.PureHuskySort;
import tmp.husky.huskySortUtils.HuskyCoderFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    public static void main(String[] args) throws IOException {
        String[] chineseStringList = FileUtil.readFileInRange("shuffledChinese.txt",10000);
//        List<String> chineseStringTmpList = FileUtil.readFile("shuffledChinese.txt");
//        String[] chineseStringList = chineseStringTmpList.toArray(new String[0]);
//        String[] pinyinStringList = Arrays.stream(chineseStringList).map(PinyinUtil::getPinyin).toArray(String[]::new);
        System.out.println("original array is: ");
        System.out.println(Arrays.toString(chineseStringList));
        int n = chineseStringList.length;

        String[] chineseStringList1 = Arrays.copyOf(chineseStringList,n);
        MSDcollator msDcollator = new MSDcollator();
        System.out.println("MSD sort");
        msDcollator.sort(chineseStringList1);
        System.out.println("After MSD sort");
        System.out.println(Arrays.asList(chineseStringList1));

        //        String[] aa = {"刘思文", "刘思源","刘斯文","刘四文"};
        String[] chineseStringList2 = Arrays.copyOf(chineseStringList,n);
        System.out.println("tim sort");
        TimSort t1 = new TimSort();
        t1.timSort(chineseStringList2);
        System.out.println("After tim sort");
        System.out.println(Arrays.toString(chineseStringList2));

        String[] chineseStringList3 = Arrays.copyOf(chineseStringList,n);
        DualPivotQuick test=new DualPivotQuick();
        System.out.println("Dual-pivot Quicksort");
        test.Sort(chineseStringList3);
        System.out.println("after Dual-pivot Quicksort");
        System.out.println(Arrays.asList(chineseStringList3));

        System.out.println("husky sort");
        String[] chineseStringList4 = Arrays.copyOf(chineseStringList,n);
        Map<String,String> pinyinMap = new HashMap<>();
        for(int i =0;i<chineseStringList4.length;i++){
            pinyinMap.put(PinyinUtil.getPinyin(chineseStringList4[i]),chineseStringList4[i]);
            chineseStringList4[i] = PinyinUtil.getPinyin(chineseStringList4[i]);
        }
        final PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
//        final PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.unicodeCoder, false, false);
        sorter.sort(chineseStringList4);
        for(int i =0;i<chineseStringList4.length;i++){
            chineseStringList4[i] = pinyinMap.get(chineseStringList4[i]);
        }
        System.out.println("After husky sort");
        System.out.println(Arrays.asList(chineseStringList4));

        LSDcollator lsDradix = new LSDcollator();
        String[] chineseStringList5 = Arrays.copyOf(chineseStringList,n);
        System.out.println("LSD sort");
        lsDradix.sort(chineseStringList5);
        System.out.println("After LSD sort");
        System.out.println(Arrays.asList(chineseStringList5));
    }
}
