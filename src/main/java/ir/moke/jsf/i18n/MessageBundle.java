package ir.moke.jsf.i18n;

import javax.faces.context.FacesContext;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by mahsom on 10/21/17.
 */
public class MessageBundle extends ResourceBundle {
    private static final String BUNDLE_NAME = "i18n.jsf";
    private static final Utf8Control UTF_8_CONTROL = new Utf8Control();

    public MessageBundle() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        setParent(ResourceBundle.getBundle(BUNDLE_NAME, locale, UTF_8_CONTROL));
    }

    @Override
    protected Object handleGetObject(String key) {
        return parent.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }
}
