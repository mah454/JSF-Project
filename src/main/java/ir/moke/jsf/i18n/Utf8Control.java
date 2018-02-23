package ir.moke.jsf.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by mahsom on 10/21/17.
 */
public class Utf8Control extends ResourceBundle.Control {

    private static final String BUNDLE_EXTENSION = "properties";
    private static final String CHARSET = "UTF-8";

    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, BUNDLE_EXTENSION);

        ResourceBundle bundle = null;
        InputStream stream = null;
        if (reload) {
            URL url = loader.getResource(resourceName);
            if (url != null) {
                URLConnection urlConnection = url.openConnection();
                if (urlConnection != null) {
                    urlConnection.setUseCaches(false);
                    stream = urlConnection.getInputStream();
                }
            }
        } else {
            stream = loader.getResourceAsStream(resourceName);
        }

        if (stream != null) {
            try {
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, CHARSET));
            } finally {
                stream.close();
            }
        }
        return bundle;
    }
}
