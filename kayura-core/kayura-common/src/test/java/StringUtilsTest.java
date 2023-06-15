import org.kayura.utils.StringUtils;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {

  @Test
  public void split1Test() {
    String[] split = StringUtils.split("xia,ab11,bbc", ",");
    System.out.println("StringUtils.split(\"xia,ab11,bbc\", \",\")");
    for (String s : split) {
      System.out.println(s);
    }
  }

  @Test
  public void split2Test() {
    String[] split = "xia,ab11,bbc".split(",");
    System.out.println("\"xia,ab11,bbc\".split(abc, \",\")");
    for (String s : split) {
      System.out.println(s);
    }
  }

}
