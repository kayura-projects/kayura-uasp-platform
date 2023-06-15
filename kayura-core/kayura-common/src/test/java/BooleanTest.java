import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Test;

public class BooleanTest {

  @SuppressWarnings("ConstantValue")
  @Test
  public void falseTest() {

    System.out.println(BooleanUtils.isNotTrue(null));
    System.out.println(BooleanUtils.isNotTrue(false));

    System.out.println(Boolean.FALSE.equals(null));
    System.out.println(Boolean.FALSE.equals(false));
    System.out.println(Boolean.FALSE.equals(true));

  }

}
