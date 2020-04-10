import netb.no.libjlte.Main;
import org.junit.Test;

import java.io.*;

public class Tests {

    @Test
    public void testBasicXhtml() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("html-basic.html");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        Main.process(bufferedReader);
    }
}
