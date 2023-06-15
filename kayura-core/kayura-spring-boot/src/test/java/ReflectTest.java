import org.kayura.security.LoginUser;
import org.kayura.security.LoginUserId;
import org.kayura.security.core.LoginUserImpl;
import org.junit.jupiter.api.Test;

public class ReflectTest {

  @Test
  public void interfaceTest() {
    System.out.println(LoginUser.class.isAssignableFrom(LoginUser.class));
    System.out.println(LoginUser.class.isAssignableFrom(LoginUserImpl.class));
    System.out.println(LoginUserId.class.isAssignableFrom(LoginUserImpl.class));
  }

}