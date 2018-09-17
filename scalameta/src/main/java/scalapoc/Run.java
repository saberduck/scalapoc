package scalapoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Run {

  public static void main(String[] args) throws Exception {
    Scalameta.analyze(source("sample.scala"));

    // parse error
//    Scalameta.analyze(source("invalid.scala"));
  }

  private static String source(String filename) throws IOException {
    return new String(Files.readAllBytes(Paths.get("samples", filename)));
  }
}
