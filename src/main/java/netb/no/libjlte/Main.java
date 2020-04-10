package netb.no.libjlte;

import javax.swing.text.html.parser.DTD;
import javax.swing.text.html.parser.DocumentParser;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    public static String process(BufferedReader documentSource) throws IOException {

        JlteCallback callbacks = new JlteCallback();
        DocumentParser documentParser = new DocumentParser(DTD.getDTD("html"));
        documentParser.parse(documentSource, callbacks, false);
        return null;
    }
}
