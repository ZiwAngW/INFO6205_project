package edu.neu.info6205.Util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
    /**
     * 根据传入的字符串(包含汉字),得到拼音
     * 沧晓 -> CANGXIAO
     * 沧  晓*& -> CANGXIAO
     * 沧晓f5 -> CANGXIAO
     *
     * @param str 字符串
     * @return
     */
    public static String getPinyin(String str) {

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuilder sb = new StringBuilder();

        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            // 如果是空格, 跳过
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (c >= -127 && c < 128 || !(c >= 0x4E00 && c <= 0x9FA5)) {
                // 肯定不是汉字
                sb.append(c);
            } else {
                String s = "";
                try {
                    // 通过char得到拼音集合. 单 -> dan, shan
                    s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
                    sb.append(s);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                    sb.append(s);
                }
            }
        }

        return sb.toString();
    }

    public static String getPinyinWithTone(String str) {

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);

        StringBuilder sb = new StringBuilder();

        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            // 如果是空格, 跳过
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (c >= -127 && c < 128 || !(c >= 0x4E00 && c <= 0x9FA5)) {
                // 肯定不是汉字
                sb.append(c);
            } else {
                String s = "";
                try {
                    // 通过char得到拼音集合. 单 -> dan, shan
                    s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
                    sb.append(s);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                    sb.append(s);
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] x = {"阿鼎", "阿迪雅"};
        for(String a:x){
            System.out.print(a+"    ");
        }
        System.out.println();
        for(String a:x){
            System.out.print(PinyinUtil.getPinyin(a)+"   ");
        }
    }
}