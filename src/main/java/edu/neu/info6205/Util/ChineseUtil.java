package edu.neu.info6205.Util;

import com.ibm.icu.text.CollationKey;
import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;

public class ChineseUtil {
    private static final Collator collator = Collator.getInstance(ULocale.CHINA);
    public static byte[] toByteArray(String string){
        return collator.getCollationKey(string).toByteArray();
    }

    public static CollationKey getCollatorKey(String string){
        return collator.getCollationKey(string);
    }

//    public static void main(String[] args) {
//        CollationKey a = getCollatorKey("刘持平");
//        System.out.println(a);
//    }
}
