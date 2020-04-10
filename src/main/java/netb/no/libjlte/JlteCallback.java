package netb.no.libjlte;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class JlteCallback extends HTMLEditorKit.ParserCallback {
    @Override
    public void handleText(char[] chars, int i) {
        super.handleText(chars, i);
    }

    @Override
    public void handleComment(char[] chars, int i) {
        super.handleComment(chars, i);
    }

    @Override
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet mutableAttributeSet, int i) {
        super.handleStartTag(tag, mutableAttributeSet, i);
    }

    @Override
    public void handleEndTag(HTML.Tag tag, int i) {
        super.handleEndTag(tag, i);
    }

    @Override
    public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet mutableAttributeSet, int i) {
        super.handleSimpleTag(tag, mutableAttributeSet, i);
    }

    @Override
    public void handleError(String s, int i) {
        super.handleError(s, i);
    }

    @Override
    public void handleEndOfLineString(String s) {
        super.handleEndOfLineString(s);
    }
}
