package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.autono.NewNoArgs;

public class CalcNewNoCommand extends Command {

  private NewNoArgs args;
  private boolean fixed;

  public NewNoArgs getArgs() {
    return args;
  }

  public CalcNewNoCommand setArgs(NewNoArgs args) {
    this.args = args;
    return this;
  }

  public boolean isFixed() {
    return fixed;
  }

  public CalcNewNoCommand setFixed(boolean fixed) {
    this.fixed = fixed;
    return this;
  }
}
