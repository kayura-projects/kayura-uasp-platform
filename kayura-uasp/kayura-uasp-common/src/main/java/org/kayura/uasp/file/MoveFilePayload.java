package org.kayura.uasp.file;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class MoveFilePayload {

  @NotEmpty
  private List<String> linkIds;
  @NotBlank
  private String folderId;

  public List<String> getLinkIds() {
    return linkIds;
  }

  public MoveFilePayload setLinkIds(List<String> linkIds) {
    this.linkIds = linkIds;
    return this;
  }

  public String getFolderId() {
    return folderId;
  }

  public MoveFilePayload setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }
}
