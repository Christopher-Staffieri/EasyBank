package easybank.Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * catches IOException if file cant be read or dosnt exist (Checked Exception).
 * @param out The current output stream being used.
 */
public class AppendingObjectOutputStream extends ObjectOutputStream {
  public AppendingObjectOutputStream(OutputStream out) throws IOException {
    super(out);
  }

  /**
   * Instead of writing the normal header reset it.
   */
  @Override
  protected void writeStreamHeader() throws IOException {
    reset();
  }

}