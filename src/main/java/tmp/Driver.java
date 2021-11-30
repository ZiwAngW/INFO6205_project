package tmp;

import Benchmark.Benchmark;
import Benchmark.Benchmark_Timer;
import Util.*;
import sort.*;
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
        int[] size = {250000, 500000, oneMillion, 2 * oneMillion, 4 * oneMillion};
//        int[] size = {2 * oneMillion };
        for (int arraySize : size) {
            String[] chineseStringList;
            WordNode[] chineseStringNodeList;
            Node[] chineseStringByteNodeList;
            if (arraySize < (2 * oneMillion)) {
                chineseStringList = FileUtil.readFileInRange("shuffledChinese.txt", arraySize);
                chineseStringNodeList = FileUtil.readFileInRangeNode("shuffledChinese.txt", arraySize);
                chineseStringByteNodeList = FileUtil.readFileInRangeByteNode("shuffledChinese.txt", arraySize);
            } else {
                chineseStringList = new String[arraySize];
                chineseStringNodeList = new WordNode[arraySize];
                chineseStringByteNodeList = new Node[arraySize];
                for (int j = 0; j < arraySize / oneMillion; j++) {
                    System.arraycopy(FileUtil.readFileInRange("shuffledChinese.txt", oneMillion), 0, chineseStringList, j * oneMillion, oneMillion);
                    System.arraycopy(FileUtil.readFileInRangeNode("shuffledChinese.txt", oneMillion), 0, chineseStringNodeList, j * oneMillion, oneMillion);
                    System.arraycopy(FileUtil.readFileInRangeByteNode("shuffledChinese.txt", oneMillion), 0, chineseStringByteNodeList, j * oneMillion, oneMillion);
                }
            }
            int n = chineseStringList.length;
            /*
            msd sort benchmark
             */
//            sortType = "msd";
//            Benchmark<Object> benchmark1 = new Benchmark_Timer<>(sortType, a -> {
//                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//                return null;
//            }
//                    , a -> new MSDcollator().sort(chineseStringListForSort));
//            mean = benchmark1.run(1, iteration, true);
//            getResultString(sortType, iteration, arraySize, mean);
            sortType = "msd wordnode";
            Benchmark<Object> benchmark10 = new Benchmark_Timer<>(sortType, a -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
                return null;
            }
                    , a -> new MSDradix().sort(chineseStringListForSort));
            mean = benchmark10.run(1, iteration, true);
            getResultString(sortType, iteration, arraySize, mean);
            sortType = "msd bytenode";
            Benchmark<Object> benchmark100 = new Benchmark_Timer<>(sortType, a -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
                return null;
            }
                    , a -> new tmp.MSD2().sort(chineseStringListForSort));
            mean = benchmark100.run(1, iteration, true);
            getResultString(sortType, iteration, arraySize, mean);
            /*
            tim sort benchmark
             */
            sortType = "tim";
            Benchmark<Object> benchmark2 = new Benchmark_Timer<>(sortType, a -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
                return null;
            }
                    , a -> new TimSort().sort(chineseStringListForSort));
            mean = benchmark2.run(1, iteration, true);
            getResultString(sortType, iteration, arraySize, mean);
            /*
            quick sort benchmark
             */
            sortType = "quick";
            Benchmark<Object> benchmark3 = new Benchmark_Timer<>(sortType, a -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
                return null;
            }
                    , a -> new DualPivotQuick().sort(chineseStringListForSort));
            mean = benchmark3.run(1, iteration, true);
            getResultString(sortType, iteration, arraySize, mean);
            /*
            husky sort benchmark
             */
            sortType = "husky";
            Map<String, String> pinyinMap = new HashMap<>();
            Benchmark<Object> benchmark4 = new Benchmark_Timer<>(sortType, b -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
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
            mean = benchmark4.run(1, iteration, true);
            getResultString(sortType, iteration, arraySize, mean);
            /*
            lsd sort benchmark
             */
//            sortType = "lsd";
//            chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//            Benchmark<Object> benchmark5 = new Benchmark_Timer<>(sortType,a -> {
//                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//                return null;
//            }, a -> new LSDcollator().sort(chineseStringListForSort));
//            mean = benchmark5.run(1, iteration, true);
//            getResultString(sortType, iteration, arraySize, mean);
            sortType = "lsd wordnode";
            chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
            Benchmark<Object> benchmark50 = new Benchmark_Timer<>(sortType,a -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
                return null;
            }, a -> new LSDradix().sort(chineseStringListForSort));
            mean = benchmark50.run(1, iteration, true);
            getResultString(sortType, iteration, arraySize, mean);
            sortType = "lsd bytenode";
            Benchmark<Object> benchmark500 = new Benchmark_Timer<>(sortType,a -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
                return null;
            }, a -> new LSDByteArray().sort(chineseStringListForSort));
            mean = benchmark500.run(1, iteration, true);
            getResultString(sortType, iteration, arraySize, mean);
        }
    }

    public static void correctness() throws IOException {
        String[] chineseStringList = FileUtil.readFileInRange("shuffledChinese.txt", 250000);
//        int oneMillion = 1000000;
//        String[] chineseStringList = new String[4 * oneMillion];
//        for (int i = 0; i < 4; i++) {
//            System.arraycopy(FileUtil.readFileInRange("shuffledChinese.txt", oneMillion), 0, chineseStringList, i * oneMillion, oneMillion);
//        }
//        String[] chineseStringList = {"刘持平", "洪文胜", "樊辉辉", "苏会敏", "高民政", "曹玉德", "袁继鹏", "舒冬梅", "杨腊香", "许凤山"};
        //        String[] chineseStringList = {"刘思文", "刘思源","刘斯文","刘四文"};
//        List<String> chineseStringTmpList = FileUtil.readFile("shuffledChinese.txt");
//        String[] chineseStringList = chineseStringTmpList.toArray(new String[0]);
//        String[] pinyinStringList = Arrays.stream(chineseStringList).map(PinyinUtil::getPinyin).toArray(String[]::new);
        System.out.println("original array is: ");
//        System.out.println(Arrays.toString(chineseStringList));
        printFirstHundredElement(chineseStringList);
        int n = chineseStringList.length;

        String[] chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
        correctNessMDS(chineseStringListForSort);
//
        chineseStringListForSort= Arrays.copyOf(chineseStringList, n);
        correctNessTim(chineseStringListForSort);
//
//        chineseStringListForSort= Arrays.copyOf(chineseStringList, n);
//        correctNessQuick(chineseStringListForSort);
//
//        chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//        correctNessHusky(chineseStringListForSort);

        chineseStringListForSort= Arrays.copyOf(chineseStringList, n);
        correctNessLDS(chineseStringListForSort);
        chineseStringListForSort= Arrays.copyOf(chineseStringList, n);
        correctNessMDSByte(chineseStringListForSort);
        chineseStringListForSort= Arrays.copyOf(chineseStringList, n);
        correctNessLDSByte(chineseStringListForSort);

    }

    private static void correctNessMDS(String[] arr) {
        MSDradix msDradix = new MSDradix();
        System.out.println("MSD sort");
        msDradix.sort(arr);
        System.out.println("After MSD sort");
//        System.out.println(Arrays.asList(chineseStringList1));
        printFirstHundredElement(arr);
    }

    private static void correctNessMDSByte(String[] arr) {
        MSD2 msd2 = new MSD2();
        System.out.println("MSD byte sort");
        msd2.sort(arr);
        System.out.println("After MSD byte sort");
//        System.out.println(Arrays.asList(chineseStringList1));
        printFirstHundredElement(arr);
    }

    private static void correctNessTim(String[] arr) {
        TimSort timSort = new TimSort();
        System.out.println("tim sort");
        timSort.sort(arr);
        System.out.println("After tim sort");
//        System.out.println(Arrays.toString(chineseStringList2));
        printFirstHundredElement(arr);
    }

    private static void correctNessQuick(String[] arr) {
        DualPivotQuick dualPivotQuick = new DualPivotQuick();
        System.out.println("Dual-pivot Quicksort");
        dualPivotQuick.sort(arr);
        System.out.println("after Dual-pivot Quicksort");
//        System.out.println(Arrays.asList(chineseStringList3));
        printFirstHundredElement(arr);
    }

    private static void correctNessHusky(String[] arr) {
        final PureHuskySort<String> pureHuskySort = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        System.out.println("husky sort");
        Map<String, String> pinyinMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            pinyinMap.put(PinyinUtil.getPinyinWithTone(arr[i]), arr[i]);
            arr[i] = PinyinUtil.getPinyinWithTone(arr[i]);
        }

//        final PureHuskySort<String> pureHuskySort = new PureHuskySort<>(HuskyCoderFactory.chineseCoder, false, false);
        pureHuskySort.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = pinyinMap.get(arr[i]);
        }
        System.out.println("After husky sort");
//        System.out.println(Arrays.asList(chineseStringList4));
        printFirstHundredElement(arr);
    }

    private static void correctNessLDS(String[] arr) {
        LSDradix lsDradix = new LSDradix();
        System.out.println("LSD sort");
        lsDradix.sort(arr);
        System.out.println("After LSD sort");
//        System.out.println(Arrays.asList(chineseStringList5));
        printFirstHundredElement(arr);
    }

    private static void correctNessLDSByte(String[] arr) {
        LSDByteArray lsdByteArray = new LSDByteArray();
        System.out.println("LSD byte sort");
        lsdByteArray.sort(arr);
        System.out.println("After LSD byte sort");
//        System.out.println(Arrays.asList(chineseStringList5));
        printFirstHundredElement(arr);
    }

    private static void getResultString(String sortType, int iteration, int arraySize, double mean) {
        System.out.println(sortType + " sort with " + iteration + " iteration and " + arraySize + " elements, the mean is " + mean + " ms");
    }

    private static void printFirstHundredElement(String[] arr) {
        for (int i = 0; i < 100; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
