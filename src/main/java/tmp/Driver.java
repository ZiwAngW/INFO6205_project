package tmp;

import Benchmark.Benchmark;
import Benchmark.Benchmark_Timer;
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

    public static String[] chineseStringListForSort;

    public static void main(String[] args) throws IOException {
        benchmark();
//        correctness();
    }

    public static void benchmark() throws IOException {
        //        250k, 500k and 1M, 2M, 4M
        int iteration = 5;
        int oneMillion = 1000000;
        double mean;
        String sortType;
//        int[] size = {250000, 500000, oneMillion, 2 * oneMillion, 4 * oneMillion};
        int[] size = {5,100};
        for (int arraySize : size) {
            String[] chineseStringList;
            if (arraySize < (2 * oneMillion)) {
                chineseStringList = FileUtil.readFileInRange("shuffledChinese.txt", arraySize);
            } else {
                chineseStringList = new String[arraySize];
                for (int j = 0; j < arraySize / oneMillion; j++) {
                    System.arraycopy(FileUtil.readFileInRange("shuffledChinese.txt", oneMillion), 0, chineseStringList, (Math.max((j * oneMillion - 1), 0)), oneMillion);
                }
            }
            int n = chineseStringList.length;
            /*
            msd sort benchmark
             */
            sortType = "msd";
            chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
            Benchmark<Object> benchmark1 = new Benchmark_Timer<>(sortType, a -> new MSDcollator().sort(chineseStringListForSort));
            mean = benchmark1.run(1, iteration);
            getResultString(sortType,iteration,arraySize,mean);
            /*
            tim sort benchmark
             */
            sortType = "tim";
            chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
            Benchmark<Object> benchmark2 = new Benchmark_Timer<>(sortType, a -> new TimSort().sort(chineseStringListForSort));
            mean = benchmark2.run(1, iteration);
            getResultString(sortType,iteration,arraySize,mean);
            /*
            quick sort benchmark
             */
            sortType = "quick";
            chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
            Benchmark<Object> benchmark3 = new Benchmark_Timer<>(sortType, a -> new DualPivotQuick().sort(chineseStringListForSort));
            mean = benchmark3.run(1, iteration);
            getResultString(sortType,iteration,arraySize,mean);
            /*
            husky sort benchmark
             */
            sortType = "husky";
            chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
            Map<String, String> pinyinMap = new HashMap<>();
            Benchmark<Object> benchmark4 = new Benchmark_Timer<>(sortType, b -> {
                for (int k = 0; k < chineseStringListForSort.length; k++) {
                    pinyinMap.put(PinyinUtil.getPinyin(chineseStringListForSort[k]), chineseStringListForSort[k]);
                    chineseStringListForSort[k] = PinyinUtil.getPinyin(chineseStringListForSort[k]);
                }
                return null;
            }, a -> new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false), b -> {
                for (int k = 0; k < chineseStringListForSort.length; k++) {
                    chineseStringListForSort[k] = pinyinMap.get(chineseStringListForSort[k]);
                }
            });
            mean = benchmark4.run(1, iteration);
            getResultString(sortType,iteration,arraySize,mean);
            /*
            lsd sort benchmark
             */
            sortType = "lsd";
            chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
            Benchmark<Object> benchmark5 = new Benchmark_Timer<>(sortType, a -> new LSDcollator().sort(chineseStringListForSort));
            mean = benchmark5.run(1, iteration);
            getResultString(sortType,iteration,arraySize,mean);
        }
    }

    public static void correctness() throws IOException {
        String[] chineseStringList = FileUtil.readFileInRange("shuffledChinese.txt", 250000);
//        List<String> chineseStringTmpList = FileUtil.readFile("shuffledChinese.txt");
//        String[] chineseStringList = chineseStringTmpList.toArray(new String[0]);
//        String[] pinyinStringList = Arrays.stream(chineseStringList).map(PinyinUtil::getPinyin).toArray(String[]::new);
        System.out.println("original array is: ");
//        System.out.println(Arrays.toString(chineseStringList));
        int n = chineseStringList.length;

//        MSD sort
        String[] chineseStringList1 = Arrays.copyOf(chineseStringList, n);
        MSDcollator msDcollator = new MSDcollator();
        msDcollator.sort(chineseStringList1);
        System.out.println("After MSD sort");
        System.out.println(Arrays.asList(chineseStringList1));

//        tim sort
        //        String[] aa = {"刘思文", "刘思源","刘斯文","刘四文"};
        String[] chineseStringList2 = Arrays.copyOf(chineseStringList, n);
        TimSort timSort = new TimSort();
        timSort.sort(chineseStringList2);
        System.out.println("After tim sort");
        System.out.println(Arrays.toString(chineseStringList2));

//        Dual-pivot Quicksort
        String[] chineseStringList3 = Arrays.copyOf(chineseStringList, n);
        DualPivotQuick dualPivotQuick = new DualPivotQuick();
        dualPivotQuick.sort(chineseStringList3);
        System.out.println("after Dual-pivot Quicksort");
        System.out.println(Arrays.asList(chineseStringList3));

//        husky sort
        String[] chineseStringList4 = Arrays.copyOf(chineseStringList, n);
//        String[] chineseStringList4 = {"阿琴","艾宏"};
        Map<String, String> pinyinMap = new HashMap<>();
        for (int i = 0; i < chineseStringList4.length; i++) {
            pinyinMap.put(PinyinUtil.getPinyin(chineseStringList4[i]), chineseStringList4[i]);
            chineseStringList4[i] = PinyinUtil.getPinyin(chineseStringList4[i]);
        }
        final PureHuskySort<String> pureHuskySort = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
//        final PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.unicodeCoder, false, false);
        pureHuskySort.sort(chineseStringList4);
        for (int i = 0; i < chineseStringList4.length; i++) {
            chineseStringList4[i] = pinyinMap.get(chineseStringList4[i]);
        }
        System.out.println("After husky sort");
        System.out.println(Arrays.asList(chineseStringList4));

//        LSD sort
        LSDcollator lsDcollator = new LSDcollator();
        String[] chineseStringList5 = Arrays.copyOf(chineseStringList, n);
        lsDcollator.sort(chineseStringList5);
        System.out.println("After LSD sort");
        System.out.println(Arrays.asList(chineseStringList5));
    }

    private static void getResultString(String sortType,int iteration,int arraySize,double mean){
        System.out.println(sortType+" sort with " + iteration + " iteration and " + arraySize + " elements, the mean is " + mean + " ms");
    }
}
