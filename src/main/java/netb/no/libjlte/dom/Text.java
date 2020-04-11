package netb.no.libjlte.dom;

public class Text extends HtmlElement {
    private String text;

    public Text(char[] text) {
        this.text = new String(text);
    }
}
