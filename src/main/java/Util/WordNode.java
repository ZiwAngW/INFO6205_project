package Util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class WordNode {
    private String ChineseChar;
    private String Pinyin;
    public WordNode(String word) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat defaultFormat =  new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        ChineseChar=word;
        Pinyin=PinyinHelper.toHanYuPinyinString(word,defaultFormat,"",true);
    }
    public WordNode(){
        ChineseChar=null;
        Pinyin=null;
    }
    public void setNode(String word)throws BadHanyuPinyinOutputFormatCombination{
        HanyuPinyinOutputFormat defaultFormat =  new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        ChineseChar=word;
        Pinyin=PinyinHelper.toHanYuPinyinString(word,defaultFormat,"",true);
    }
    public String getChineseChar(){
        return ChineseChar;
    }
    public String getPinyin(){
        return Pinyin;
    }
    @Override
    public String toString(){
        return ChineseChar+","+Pinyin;
    }

}
