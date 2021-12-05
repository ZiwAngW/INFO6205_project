import edu.neu.info6205.Util.Swaper;
import org.junit.Assert;
import org.junit.Test;

public class SwapperTest {

    @Test
    public void test1(){
        String[] a= {"ab","bc","cd"};
        Swaper.swap(a,0,2);
        Assert.assertEquals("cd",a[0]);
        Assert.assertEquals("ab",a[2]);
    }

}
