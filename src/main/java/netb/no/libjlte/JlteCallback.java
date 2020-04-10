package netb.no.libjlte;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class JlteCallback extends HTMLEditorKit.ParserCallback {

    /**
     * Callback for when text is encountered in between tags.
     */
    @Override
    public void handleText(char[] chars, int i) {
        super.handleText(chars, i);
    }

    /**
     * Callback for when a tag defined in {@link javax.swing.text.html.parser.DTD} is encountered.
     */
    @Override
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet mutableAttributeSet, int i) {
        super.handleStartTag(tag, mutableAttributeSet, i);
    }

    /**
     * Callback for when a tag defined in {@link javax.swing.text.html.parser.DTD} is ended.
     */
    @Override
    public void handleEndTag(HTML.Tag tag, int i) {
        super.handleEndTag(tag, i);
    }

    /**
     * "Simple Tags" seems to be any tag not defined in {@link javax.swing.text.html.parser.DTD}.
     * There is only a limited set defined in that DTD. For instance, the p tag is defined, but div is not.
     * This method handles both start and end tags for "simple" tags. If it is an end tag, it will be flagged
     * as such in the {@link MutableAttributeSet} param.
     */
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
