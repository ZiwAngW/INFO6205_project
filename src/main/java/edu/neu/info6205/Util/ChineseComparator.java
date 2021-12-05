package edu.neu.info6205.Util;

import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ULocale;

import java.util.Comparator;

public class ChineseComparator implements Comparator<String> {
	Collator collator = Collator.getInstance(ULocale.CHINA);

    public int compare(String s1, String s2) {
        return collator.compare(s1, s2);
    }

}
