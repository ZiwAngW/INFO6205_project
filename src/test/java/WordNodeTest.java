import Util.WordNode;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WordNodeTest {

    @Test
    public void nodeSetup() throws BadHanyuPinyinOutputFormatCombination {
        WordNode test=new WordNode("你好吗");
//        System.out.println(test.getPinyin()+ " Pinyin");
//        System.out.println(test.getChineseChar()+" Chinese char");
        assertTrue(test.getPinyin().equals("nihaoma"));

    }
    @Test
    public void arrayNode() throws BadHanyuPinyinOutputFormatCombination {
        WordNode[] test=new WordNode[3];
        String[] x={"测试","测试一","测试二"};
        for(int i =0;i<3;i++){
            test[i]=new WordNode(x[i]);
        }
        for(int i=0;i<3;i++){
            assertTrue(test[i].getChineseChar().equals(x[i]));
        }


    }
}
