import edu.neu.info6205.Util.PinyinUtil;
import org.junit.Assert;
import org.junit.Test;

public class PinyinUtilTest {
    @Test
    public void test1() {
        String[] a = {"谢", "足", "岳"};
        Assert.assertEquals("XIE", PinyinUtil.getPinyin(a[0]));
        Assert.assertEquals("ZU", PinyinUtil.getPinyin(a[1]));
        Assert.assertEquals("YUE", PinyinUtil.getPinyin(a[2]));
    }
}
