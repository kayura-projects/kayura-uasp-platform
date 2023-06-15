import org.kayura.utils.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

public class TempTest {

  @Test
  public void test1() {
    short s1 = 32767;
    s1 += 1;
    System.out.println(s1);
  }

  @Test
  public void test2() {
    @Nullable String[] split = StringUtils.splitByWholeSeparator("abc,,,432,432,543,,,china,show,,,", ",");
    for (String s : split) {
      System.out.println("---> " + s);
    }
  }

  @Test
  public void test3() {
    @Nullable String[] split = StringUtils.splitByWholeSeparator("", ",");
    for (String s : split) {
      System.out.println("---> " + s);
    }
  }
}
