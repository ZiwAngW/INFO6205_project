package sort;

import Util.FileUtil;
import Util.WordNode;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.IOException;
import java.util.Arrays;

public class LSDWordNode {
    public int findLongestLength(WordNode[] a) {
        int longest = 0;
        for(WordNode x:a){
            if(x.getPinyin().length()>longest){
                longest=x.getPinyin().length();
            }
        }
        return longest;
    }

    public int findCharAtInString(int d, WordNode a) {
        if (d < 0 || d >= a.getPinyin().length()) {
            return 0;
        }
        return a.getPinyin().charAt(d);
    }

    public void sort(String[] a) {
        WordNode[] wordNodeArray = new WordNode[a.length];
        try {
            for (int i = 0; i < a.length; i++) {
                wordNodeArray[i] = new WordNode(a[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        sort(wordNodeArray);
        for (int i = 0; i < a.length; i++) {
            a[i] = wordNodeArray[i].getChineseChar();
        }
    }

    public void sort(WordNode[] a) {
        int R = 256;
        int N = a.length;
        int W = findLongestLength(a);
        WordNode[] aux = new WordNode[N];
        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++){
                int c = findCharAtInString(d, a[i]);
                count[c+1]++;
            }
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];
            for (int i = 0; i < N; i++){
                int c = findCharAtInString(d, a[i]);
                aux[count[c]++] = a[i];
            }
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }

    public static void main(String[] args) throws IOException {
        LSDWordNode a = new LSDWordNode();
        WordNode[] c = FileUtil.readFileInRangeNode("shuffledChinese.txt",10);
//        String[] b = Arrays.stream(a).map(PinyinUtil::getPinyin).toArray(String[]::new);
//        Collator b = Collator.getInstance(Locale.CHINA);
        Arrays.asList(c).forEach(System.out::print);
        System.out.println();
        a.sort(c);
        Arrays.asList(c).forEach(System.out::print);
    }

}
