import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class RandomStringTest {

  @Test
  public void test1() {
    System.out.println("randomAlphabetic: " + RandomStringUtils.randomAlphabetic(16));
    System.out.println("randomAscii: " + RandomStringUtils.randomAscii(16));
    System.out.println("randomNumeric: " + RandomStringUtils.randomNumeric(16));
    System.out.println("randomGraph: " + RandomStringUtils.randomGraph(16));
    System.out.println("randomAlphanumeric: " + RandomStringUtils.randomAlphanumeric(16));
    System.out.println("randomAlphanumeric_LowerCase: " + RandomStringUtils.randomAlphanumeric(16).toLowerCase());
  }

}
