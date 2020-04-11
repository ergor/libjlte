package netb.no.libjlte;

import netb.no.libjlte.dom.HtmlElement;
import netb.no.libjlte.dom.Tag;
import netb.no.libjlte.dom.Text;

import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.parser.DTD;
import javax.swing.text.html.parser.DocumentParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

public class Main<T> {

    enum JlteAttrs {
        ITERATE("jlte-iter");

        private String string;

        JlteAttrs(String string) {
            this.string = string;
        }

        public String getString() {
            return string;
        }
    }

    private T controller;

    public Main(T controller) {
        this.controller = controller;
    }

    public HtmlElement process(BufferedReader documentSource) throws IOException {

        MutableReference<HtmlElement> domTreeRef = new MutableReference<>(HtmlElement.class, null);
        JlteCallback callbacks = new JlteCallback(domTreeRef);
        DocumentParser documentParser = new DocumentParser(DTD.getDTD("html"));
        documentParser.parse(documentSource, callbacks, false);
        HtmlElement domTree = domTreeRef.get();
        if (domTree == null) {
            throw new TemplateException("jlte: domtree is null. are all the tags balanced?");
        }
        unwindLoops((Tag) domTree);
        return domTree;
    }

    private void unwindLoops(Tag parent, Tag tag) {

        AttributeSet attrs = parent.getAttrs();
        Object loopAttr = attrs.getAttribute(JlteAttrs.ITERATE.string);
        if (loopAttr != null) {
            String[] tokens = ((String) loopAttr).split(":");
            if (tokens.length != 2) {
                throw new TemplateException("jlte: iterate syntax error. syntax: \"item : array\"");
            }
            String itemName = tokens[0].trim();
            String arrayName = tokens[1].trim();
            // remove current tag from parent + remove iterate attr, then:

            doUnwind(parent, itemName, arrayName);
        }

        for (HtmlElement child : tag.getChildren()) {
            if (child instanceof Text) {
                continue;
            }
            unwindLoops(tag, (Tag) child);
        }
    }

    private void doUnwind(Tag parent, Tag repeatedTag, String itemName, String arrayName) {
        // in every child, replace every occurence of itemName with arrayName[i]
        parent.addChild(repeatedTag);
    }

    private void something(String arrayName, String property) throws InvocationTargetException, IllegalAccessException {
        Method arrayGetter = ReflectionUtil.findGetter(controller, arrayName);
        Object[] array = (Object[]) arrayGetter.invoke(controller);

        for (int i = 0; i < array.length; i++) {
            Object object = array[i];
            Object value;
            if (property == null) {
                value = object;
            } else {
                Method propertyGetter = ReflectionUtil.findGetter(object, property);
                value = propertyGetter.invoke(object);
            }
        }
    }
}
