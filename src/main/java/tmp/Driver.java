package tmp;

import Benchmark.Benchmark;
import Benchmark.Benchmark_Timer;
import Util.*;
import sort.*;
import husky.PureHuskySort;
import husky.huskySortUtils.HuskyCoderFactory;

import java.io.IOException;
import java.util.*;

public class Driver {

    public static String[] chineseStringListForSort;

    public static void main(String[] args) throws IOException {
        benchmark();
//        benchmark2();
//        tmpTest();
//        correctness();
    }

    public static void benchmark() throws IOException {
        //        250k, 500k and 1M, 2M, 4M
        int iteration = 10;
        int oneMillion = 1000000;
        double mean;
        String sortType;
//        int[] size = {250000, 500000, oneMillion, 2 * oneMillion, 4 * oneMillion};
        int[] size = {oneMillion, 1500000, 2 * oneMillion, 3 * oneMillion, 4 * oneMillion};
//        int[] size = {1500000,2 * oneMillion };
        for (int arraySize : size) {
            String[] chineseStringList;
            if (arraySize <= oneMillion) {
                chineseStringList = FileUtil.readFileInRange("shuffledChinese.txt", arraySize);
            } else {
                int remain = arraySize;
                chineseStringList = new String[arraySize];
                for (int j = 0; j < arraySize / oneMillion; j++) {
                    System.arraycopy(FileUtil.readFileInRange("shuffledChinese.txt", Math.min(remain, oneMillion)), 0, chineseStringList, j * oneMillion, Math.min(remain, oneMillion));
                    remain -= oneMillion;
                    if (remain < oneMillion) {
                        System.arraycopy(FileUtil.readFileInRange("shuffledChinese.txt", remain), 0, chineseStringList, (j + 1) * oneMillion, remain);
                    }
                }
            }
            int n = chineseStringList.length;
            /*
            msd sort benchmark
             */
            sortType = "msd collator and " + arraySize + " elements";
            Benchmark<Object> benchmark1 = new Benchmark_Timer<>(sortType, a -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
                return null;
            }
                    , a -> new MSDcollator().sort(chineseStringListForSort));
            mean = benchmark1.run(1, iteration, true);
            System.out.println(mean);
            sortType = "msd pinyin and " + arraySize + " elements";
            Benchmark<Object> benchmark10 = new Benchmark_Timer<>(sortType, a -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
                return null;
            }
                    , a -> new MSDWordNode().sort(chineseStringListForSort));
            mean = benchmark10.run(1, iteration, true);
            System.out.println(mean);
            sortType = "msd byte node and " + arraySize + " elements";
            Benchmark<Object> benchmark100 = new Benchmark_Timer<>(sortType, a -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
                return null;
            }
                    , a -> new MSDByteArray().sort(chineseStringListForSort));
            mean = benchmark100.run(1, iteration, true);
            System.out.println(mean);

            /*
            tim sort benchmark
             */
//            sortType = "tim and " + arraySize + " elements";
//            Benchmark<Object> benchmark2 = new Benchmark_Timer<>(sortType, a -> {
//                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//                return null;
//            }
//                    , a -> new TimSort().sort(chineseStringListForSort));
//            mean = benchmark2.run(1, iteration, true);
//            System.out.println(mean);
//            sortType = "tim pinyin and " + arraySize + " elements";
//            Benchmark<Object> benchmark20 = new Benchmark_Timer<>(sortType, a -> {
//                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//                return null;
//            }
//                    , a -> new TimSortPINYIN().sort(chineseStringListForSort));
//            mean = benchmark20.run(1, iteration, true);
//            System.out.println(mean);

            /*
            quick sort benchmark
             */
//            sortType = "quick and " + arraySize + " elements";
//            Benchmark<Object> benchmark3 = new Benchmark_Timer<>(sortType, a -> {
//                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//                return null;
//            }
//                    , a -> new DualPivotQuick().sort(chineseStringListForSort));
//            mean = benchmark3.run(1, iteration, true);
//            System.out.println(mean);
//            sortType = "quick pinyin and " + arraySize + " elements";
//            Benchmark<Object> benchmark30 = new Benchmark_Timer<>(sortType, a -> {
//                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//                return null;
//            }
//                    , a -> new DualPivotQuickPINYIN().sort(chineseStringListForSort));
//            mean = benchmark30.run(1, iteration, true);
//            System.out.println(mean);

            /*
            husky sort benchmark
             */
//            sortType = "husky and " + arraySize + " elements";
//            Map<String, String> pinyinMap = new HashMap<>();
//            Benchmark<Object> benchmark4 = new Benchmark_Timer<>(sortType, b -> {
//                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//                for (int k = 0; k < chineseStringListForSort.length; k++) {
//                    pinyinMap.put(PinyinUtil.getPinyin(chineseStringListForSort[k]), chineseStringListForSort[k]);
//                    chineseStringListForSort[k] = PinyinUtil.getPinyin(chineseStringListForSort[k]);
//                }
//                return null;
//            }, a -> new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false).sort(chineseStringListForSort), b -> {
//                for (int k = 0; k < chineseStringListForSort.length; k++) {
//                    chineseStringListForSort[k] = pinyinMap.get(chineseStringListForSort[k]);
//                }
//            });
//            mean = benchmark4.run(1, iteration, true);
//            System.out.println(mean);

            /*
            lsd sort benchmark
             */
//            sortType = "lsd and " + arraySize + " elements";
//            Benchmark<Object> benchmark5 = new Benchmark_Timer<>(sortType, a -> {
//                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//                return null;
//            }, a -> new LSDcollator().sort(chineseStringListForSort));
//            mean = benchmark5.run(1, iteration, true);
//            System.out.println(mean);
//            sortType = "lsd pinyin and " + arraySize + " elements";
//            Benchmark<Object> benchmark50 = new Benchmark_Timer<>(sortType,a -> {
//                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//                return null;
//            }, a -> new LSDradix().sort(chineseStringListForSort));
//            mean = benchmark50.run(1, iteration, true);
//            System.out.println(mean);
//            sortType = "lsd byte node and " + arraySize + " elements";
//            Benchmark<Object> benchmark500 = new Benchmark_Timer<>(sortType, a -> {
//                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//                return null;
//            }, a -> new LSDByteArray().sort(chineseStringListForSort));
//            mean = benchmark500.run(1, iteration, true);
//            System.out.println(mean);
        }
    }

    public static void benchmark2() throws IOException {
        //        250k, 500k and 1M, 2M, 4M
        int iteration = 10;
        int oneMillion = 1000000;
        double mean;
        String sortType;
//        int[] size = {250000, 500000, oneMillion, 2 * oneMillion, 4 * oneMillion};
        int[] size = {oneMillion, 1500000, 2 * oneMillion, 3 * oneMillion, 4 * oneMillion};
//        int[] size = {1500000,2 * oneMillion };
        for (int arraySize : size) {
            String[] chineseStringList;
            if (arraySize <= oneMillion) {
                chineseStringList = FileUtil.readFileInRange("shuffledChinese.txt", arraySize);
            } else {
                int remain = arraySize;
                chineseStringList = new String[arraySize];
                for (int j = 0; j < arraySize / oneMillion; j++) {
                    System.arraycopy(FileUtil.readFileInRange("shuffledChinese.txt", Math.min(remain, oneMillion)), 0, chineseStringList, j * oneMillion, Math.min(remain, oneMillion));
                    remain -= oneMillion;
                    if (remain < oneMillion) {
                        System.arraycopy(FileUtil.readFileInRange("shuffledChinese.txt", remain), 0, chineseStringList, (j + 1) * oneMillion, remain);
                    }
                }
            }
            int n = chineseStringList.length;
            sortType = "msd husk and " + arraySize + " elements";
            Benchmark<Object> benchmark1 = new Benchmark_Timer<>(sortType, a -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
                return null;
            }, a -> new MSDhusk().sort(chineseStringListForSort));
            mean = benchmark1.run(1, iteration, true);
            System.out.println(mean);
            sortType = "msd husky and " + arraySize + " elements";
            Benchmark<Object> benchmark2 = new Benchmark_Timer<>(sortType, a -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
                return null;
            }, a -> {
                try {
                    new MSDhusky().sort(chineseStringListForSort);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            mean = benchmark2.run(1, iteration, true);
            System.out.println(mean);
            sortType = "msd byte and " + arraySize + " elements";
            Benchmark<Object> benchmark3 = new Benchmark_Timer<>(sortType, a -> {
                chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
                return null;
            }, a -> new MSDByteArray().sort(chineseStringListForSort));
            mean = benchmark3.run(1, iteration, true);
            System.out.println(mean);
        }
    }

    public static void tmpTest() throws IOException {
        String[] chineseStringList = FileUtil.readFileInRange("shuffledChinese.txt", 1000000);
        ByteNode[] c = FileUtil.readFileInRangeByteNode("shuffledChinese.txt", 1000000);
        WordNode[] d = FileUtil.readFileInRangeNode("shuffledChinese.txt", 1000000);
        Map<Integer, Integer> a = new HashMap<>(1000000);
        Map<Integer, Integer> b = new HashMap<>(1000000);
        for (int i = 0; i < 20; i++) {
            b.put(i, 0);
        }
        for (WordNode wordNode : d) {
            b.put(wordNode.getPinyin().length(), b.get(wordNode.getPinyin().length()) + 1);
        }
        double all = 0;
        for (Map.Entry<Integer, Integer> entry : b.entrySet()) {
            double div = (double) entry.getValue() / 1000000;
            all += (double) entry.getKey() * div;
        }
        System.out.println(all);
        System.exit(0);
//        String[] chineseStringList = {"阿安", "阿斌", "阿滨", "阿彬"};
        String[] arr = new String[chineseStringList.length];
        String[] arrr = new String[chineseStringList.length];
        String[] arrrr = new String[chineseStringList.length];

        int n = chineseStringList.length;
        arr = Arrays.copyOf(chineseStringList, n);
        arrr = Arrays.copyOf(chineseStringList, n);
        arrrr = Arrays.copyOf(chineseStringList, n);
        MSDWordNode MSDWordNode = new MSDWordNode();
        System.out.println("msd sort");
        MSDWordNode.sort(arr);
        System.out.println("after msd sort");
        printFirstHundredElement(arr);

        TimSortPINYIN timSortPINYIN = new TimSortPINYIN();
        System.out.println("tim pinyin sort");
        timSortPINYIN.sort(arrr);
        System.out.println("After tim pinyin sort");
        printFirstHundredElement(arrr);

        DualPivotQuickPINYIN dualPivotQuickPINYIN = new DualPivotQuickPINYIN();
        System.out.println("Dual-pivot Quicksort");
        dualPivotQuickPINYIN.sort(arrrr);
        System.out.println("after  Dual-pivot Quicksort");
        printFirstHundredElement(arrrr);

        for (int i = 0; i < chineseStringList.length; i++) {
            if (!arr[i].equals(arrr[i])) {
                System.out.println("i： " + i + " msd: " + arr[i - 1] + ", " + arr[i] + "tim: " + arrr[i - 1] + ", " + arrr[i]);
                break;
            }
        }
        for (int i = 0; i < chineseStringList.length; i++) {
            if (!arr[i].equals(arrrr[i])) {
                System.out.println("i： " + i + " msd: " + arr[i - 1] + ", " + arr[i] + "quick: " + arrrr[i - 1] + ", " + arrrr[i]);
                break;
            }
        }
        for (int i = 0; i < chineseStringList.length; i++) {
            if (!arrr[i].equals(arrrr[i])) {
                System.out.println("i： " + i + " tim: " + arrr[i - 1] + ", " + arrr[i] + "quick: " + arrrr[i - 1] + ", " + arrrr[i]);
                break;
            }
        }
//        System.out.println(Arrays.asList(chineseStringList1));
    }

    public static void correctness() throws IOException {
        String[] chineseStringList = FileUtil.readFileInRange("shuffledChinese.txt", 1000000);
        String[] tmpList = new String[chineseStringList.length];
//        int oneMillion = 1000000;
//        String[] chineseStringList = new String[4 * oneMillion];
//        for (int i = 0; i < 4; i++) {
//            System.arraycopy(FileUtil.readFileInRange("shuffledChinese.txt", oneMillion), 0, chineseStringList, i * oneMillion, oneMillion);
//        }
//        String[] chineseStringList = {"刘持平", "洪文胜", "樊辉辉", "苏会敏", "高民政", "曹玉德", "袁继鹏", "舒冬梅", "杨腊香", "许凤山"};
//        String[] chineseStringList = {"刘思文", "刘思源","刘斯文","刘四文"};
//        String[] x= {"阿鼎","阿迪雅"};
//        List<String> chineseStringTmpList = FileUtil.readFile("shuffledChinese.txt");
//        String[] chineseStringList = chineseStringTmpList.toArray(new String[0]);
        System.out.println("original array is: ");
//        System.out.println(Arrays.toString(chineseStringList));
        printFirstHundredElement(chineseStringList);
        int n = chineseStringList.length;

//        String[] chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//        correctNessMDS(chineseStringListForSort);

        chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
        correctNessMDSCollator(chineseStringListForSort);
//
//        chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//        correctNessTim(chineseStringListForSort);
//
//        chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//        correctNessQuick(chineseStringListForSort);
//
//        chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
//        correctNessHusky(chineseStringListForSort);

//        chineseStringListForSort= Arrays.copyOf(chineseStringList, n);
//        correctNessLDS(chineseStringListForSort);

        chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
        correctNessMDSByte(chineseStringListForSort);

//        for(int i = 0;i<chineseStringList.length;i++){
//            if(!tmpList[i].equals(chineseStringListForSort[i])){
//                System.out.println(i);
//            }
//        }

        chineseStringListForSort = Arrays.copyOf(chineseStringList, n);
        correctNessLDSByte(chineseStringListForSort);

    }

    private static void correctNessMDS(String[] arr) {
        MSDWordNode MSDWordNode = new MSDWordNode();
        System.out.println("MSD sort");
        MSDWordNode.sort(arr);
        System.out.println("After MSD sort");
//        System.out.println(Arrays.asList(chineseStringList1));
        printFirstHundredElement(arr);
    }

    private static void correctNessMDSCollator(String[] arr) {
        MSDcollator msDcollator = new MSDcollator();
        System.out.println("MSD byte sort");
        msDcollator.sort(arr);
        System.out.println("After MSD byte sort");
//        System.out.println(Arrays.asList(chineseStringList1));
        printFirstHundredElement(arr);
    }

    private static void correctNessMDSByte(String[] arr) {
        MSDByteArray msdByteArray = new MSDByteArray();
        System.out.println("MSD byte sort");
        msdByteArray.sort(arr);
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
        sort.DualPivotQuick dualPivotQuick = new sort.DualPivotQuick();
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
        LSDWordNode LSDWordNode = new LSDWordNode();
        System.out.println("LSD sort");
        LSDWordNode.sort(arr);
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
//        for (int i = 0; i < 10; i++) {
//            System.out.print(UTF8Util.convertUTF8ToString(arr[i]) + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < 10; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < 3; i++) {
//            System.out.print(UTF8Util.unicodeToString(arr[i]) + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < 3; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();
//    }
    }
}
