package org.kayura.uasp.workflow;

import org.kayura.type.IdName;

public interface InnerExprHandler {

  IdName getIdName();

  boolean execute(InnerExprArgs args);

}
