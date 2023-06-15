import org.kayura.cmd.CommandBus;
import org.kayura.cmd.DefaultCommandBus;
import org.junit.jupiter.api.Test;

public class ReflectTest {

  @Test
  public void interfaceTest() {
    System.out.println(DefaultCommandBus.class.isAssignableFrom(CommandBus.class));
    System.out.println(CommandBus.class.isAssignableFrom(DefaultCommandBus.class));
  }

}