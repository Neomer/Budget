package my.neomer.budget.core.types;

import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import my.neomer.budget.R;
import my.neomer.budget.core.AppContext;

public final class TransactionCategory implements Cloneable {

    private int id;
    private int name;
    private Bitmap image;
    private List<Pattern> patterns;

    public TransactionCategory(int id, int name, Bitmap image) {
        this.id = id;
        this.name = name;
        this.image = image;
        patterns = null;
    }

    public TransactionCategory(int id, int name, Bitmap image, int patternResource) {
        this.id = id;
        this.name = name;
        this.image = image;

        loadPatterns(patternResource);
    }

    private void loadPatterns(int resource) {
        patterns = new ArrayList<>();

        XmlResourceParser xml = AppContext.getInstance().getResources().getXml(resource);
        try {
            while (xml.getEventType() != XmlResourceParser.END_DOCUMENT) {
                if (xml.getEventType() == XmlResourceParser.START_TAG) {
                    String name = xml.getName();
                    if (name.toLowerCase().equals("pattern")) {
                        while (xml.getEventType() != XmlResourceParser.TEXT && xml.getEventType() != XmlResourceParser.END_DOCUMENT) {
                            xml.next();
                        }
                        if (xml.getEventType() == XmlResourceParser.TEXT) {
                            String text = xml.getText();
                            if (text != null) {
                                patterns.add(Pattern.compile(text, Pattern.CASE_INSENSITIVE));
                            }
                        }
                    }
                }
                xml.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public boolean isValid(String needle) {
        if (patterns == null) {
            return false;
        }
        for(Pattern p : patterns) {
            if (p.matcher(needle).find()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        TransactionCategory category = new TransactionCategory(getId(), getName(), getImage());
        return  category;
    }
}
