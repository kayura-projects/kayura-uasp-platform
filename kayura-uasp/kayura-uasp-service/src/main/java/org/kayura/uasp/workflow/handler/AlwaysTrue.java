package org.kayura.uasp.workflow.handler;

import org.kayura.type.IdName;
import org.kayura.uasp.workflow.InnerExprArgs;
import org.kayura.uasp.workflow.InnerExprHandler;
import org.springframework.stereotype.Component;

@Component
public class AlwaysTrue implements InnerExprHandler {

  static final IdName ID_NAME =
    IdName.create().setId("AlwaysTrue").setName("总是为“真”的计算结果");

  @Override
  public IdName getIdName() {
    return ID_NAME;
  }

  @Override
  public boolean execute(InnerExprArgs args) {
    return true;
  }

}
