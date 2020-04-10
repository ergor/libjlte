package netb.no.libjlte;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

public class Tag extends HtmlElement {
    private HTML.Tag tag;
    private MutableAttributeSet attrs;
    private List<HtmlElement> children;

    public Tag(HTML.Tag tag, MutableAttributeSet attrs) {
        this.tag = tag;
        this.attrs = attrs;
        this.children = new ArrayList<>();
    }

    public void addChild(HtmlElement element) {
        children.add(element);
    }

    @Override
    public String toString() {
        return String.format("<%s>", tag);
    }
}
