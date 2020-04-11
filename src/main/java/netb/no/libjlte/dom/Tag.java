package netb.no.libjlte.dom;

import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

public class Tag extends HtmlElement {
    private HTML.Tag tag;
    private AttributeSet attrs;
    private List<HtmlElement> children;

    public Tag(HTML.Tag tag, MutableAttributeSet attrs) {
        this.tag = tag;
        this.attrs = attrs.copyAttributes();
        this.children = new ArrayList<>();
    }

    public void addChild(HtmlElement element) {
        children.add(element);
    }

    public List<HtmlElement> getChildren() {
        return children;
    }

    public AttributeSet getAttrs() {
        return attrs;
    }

    @Override
    public String toString() {
        return String.format("<%s>", tag);
    }
}
