package my.neomer.budget.core.types;

import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.graphics.Bitmap;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import my.neomer.budget.R;
import my.neomer.budget.core.AppContext;
import my.neomer.budget.models.AbstractEntity;

public final class TransactionCategory extends AbstractEntity<Integer> implements Cloneable {

    private int name;
    private int image;
    private List<Pattern> patterns;

    public TransactionCategory() {
        super();
    }

    public TransactionCategory(int id, int name, int image) {
        super(id);
        this.name = name;
        this.image = image;
        patterns = null;
    }

    public TransactionCategory(int id, int name, int image, int patternResource) {
        super(id);
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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

    @Override
    public void map(Cursor c) {
        int idColIdx = c.getColumnIndex("id");
        int nameColIdx = c.getColumnIndex("name");
        int imageColIdx = c.getColumnIndex("image");

        setId(c.getInt(idColIdx));
        setName(c.getInt(nameColIdx));
        setImage(c.getInt(imageColIdx));
    }
}
