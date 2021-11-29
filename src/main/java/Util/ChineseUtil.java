package Util;

import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;

public class ChineseUtil {
    private static final Collator collator = Collator.getInstance(ULocale.CHINA);
    public static byte[] toByteArray(String string){
        return collator.getCollationKey(string).toByteArray();
    }
}
