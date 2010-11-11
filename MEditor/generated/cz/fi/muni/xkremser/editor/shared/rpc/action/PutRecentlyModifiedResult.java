package cz.fi.muni.xkremser.editor.shared.rpc.action;

import com.gwtplatform.dispatch.shared.Result;

public class PutRecentlyModifiedResult implements Result { 
  private boolean found;

  protected PutRecentlyModifiedResult() { }

  public PutRecentlyModifiedResult(boolean found) { 
    this.found = found;
  }

  public boolean isFound() {
    return found;
  }

  @Override
  public boolean equals(Object other) {
    if (other != null && other.getClass().equals(this.getClass())) {
          PutRecentlyModifiedResult o = (PutRecentlyModifiedResult) other;
      return true
          && o.found == this.found
        ;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + getClass().hashCode();
    hashCode = (hashCode * 37) + new Boolean(found).hashCode();
    return hashCode;
  }

  @Override
  public String toString() {
    return "PutRecentlyModifiedResult["
                 + found
    + "]";
  }

}
