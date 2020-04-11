package netb.no.libjlte;

import netb.no.libjlte.dom.HtmlElement;
import netb.no.libjlte.dom.Tag;
import netb.no.libjlte.dom.Text;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import java.util.ArrayDeque;
import java.util.Deque;


public class JlteCallback extends HTMLEditorKit.ParserCallback {

    MutableReference<HtmlElement> domTree;
    Deque<HtmlElement> nodeStack = new ArrayDeque<>();

    public JlteCallback(MutableReference<HtmlElement> domTree) {
        this.domTree = domTree;
    }

    /**
     * Callback for when text is encountered in between tags.
     */
    @Override
    public void handleText(char[] chars, int i) {
        HtmlElement head = nodeStack.peek();
        if (head instanceof Tag) {
            ((Tag) head).addChild(new Text(chars));
        } else {
            throw new TemplateException("jlte: expected text segment to be child of html tag (was child of another text segment)");
        }
    }

    /**
     * Callback for when a tag defined in {@link javax.swing.text.html.parser.DTD} is encountered.
     */
    @Override
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet mutableAttributeSet, int i) {
        nodeStack.push(new Tag(tag, mutableAttributeSet));
    }

    /**
     * Callback for when a tag defined in {@link javax.swing.text.html.parser.DTD} is ended.
     */
    @Override
    public void handleEndTag(HTML.Tag tag, int i) {
        HtmlElement node = nodeStack.pop();
        HtmlElement head = nodeStack.peek();
        if (head != null) {
            ((Tag) head).addChild(node);
        } else {
            domTree.set(node);
        }
    }

    /**
     * "Simple Tags" seems to be any tag not defined in {@link javax.swing.text.html.parser.DTD}.
     * There is only a limited set defined in that DTD. For instance, the p tag is defined, but div is not.
     * This method handles both start and end tags for "simple" tags. If it is an end tag, it will be flagged
     * as such in the {@link MutableAttributeSet} param.
     */
    @Override
    public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet mutableAttributeSet, int i) {
        Object endTagAttr = mutableAttributeSet.getAttribute(HTML.Attribute.ENDTAG);
        if (endTagAttr == null) { // start tag
            handleStartTag(tag, mutableAttributeSet, i);
        } else { // end tag
            handleEndTag(tag, i);
        }
    }

    @Override
    public void handleError(String s, int i) {
        if (s.contains("tag.unrecognized") || s.contains("end.unrecognized")) {
            return;
        } else if (s.contains("invalid.tagatt")) {
            return;
        }
        throw new TemplateException("jlte: unhandled error \"" + s + "\" at " + i);
    }
}
