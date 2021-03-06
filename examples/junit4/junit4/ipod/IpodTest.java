package junit4.ipod;

import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.io.FileUtils;
import examples.ipod.after.Ipod;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.IOException;
import java.io.File;

public class IpodTest {
  @Test
  public void testConsoleOutput() throws IOException {
    PrintStream originalOut = System.out;
    try {
      System.setOut(new PrintStream("fixtures/ipod.out.actual"));
      Ipod.main(new String[]{});
      System.out.flush();
    } finally {
      System.setOut(originalOut);
    }
    String actual = FileUtils.readFileToString(new File("fixtures/ipod.out.actual"));
    String expected = FileUtils.readFileToString(new File("fixtures/ipod.out.expected"));
    assertEquals(expected, actual);
  }

  @Test
  public void testTotalDuration() {
    Ipod ipod = new Ipod();
    assertEquals(714, ipod.totalDuration());    
  }

  @Test
  public void testPrettyTimeShort() {
    assertEquals("00:01", Ipod.prettyTime(1));
  }

  @Test
  public void testPrettyTimeLong() {
    assertEquals("01:01", Ipod.prettyTime(61));
  }
}
