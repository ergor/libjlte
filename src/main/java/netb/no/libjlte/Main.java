package netb.no.libjlte;

import javax.swing.text.html.parser.DTD;
import javax.swing.text.html.parser.DocumentParser;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    public static HtmlElement process(BufferedReader documentSource) throws IOException {

        MutableReference<HtmlElement> domTreeRef = new MutableReference<>(HtmlElement.class, null);
        JlteCallback callbacks = new JlteCallback(domTreeRef);
        DocumentParser documentParser = new DocumentParser(DTD.getDTD("html"));
        documentParser.parse(documentSource, callbacks, false);
        HtmlElement domTree = domTreeRef.get();
        if (domTree == null) {
            throw new TemplateException("jlte: domtree is null. are all the tags balanced?");
        }
        return domTree;
    }
}
