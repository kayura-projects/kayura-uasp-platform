package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;

import java.util.List;

public class ImageDownloadCommand extends Command {

  private String formula;
  private List<String> imageTypes;

  public String getFormula() {
    return formula;
  }

  public ImageDownloadCommand setFormula(String formula) {
    this.formula = formula;
    return this;
  }

  public List<String> getImageTypes() {
    return imageTypes;
  }

  public ImageDownloadCommand setImageTypes(List<String> imageTypes) {
    this.imageTypes = imageTypes;
    return this;
  }
}
