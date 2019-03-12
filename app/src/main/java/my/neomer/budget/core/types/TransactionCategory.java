package my.neomer.budget.core.types;

import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import my.neomer.budget.R;
import my.neomer.budget.core.AppContext;

public final class TransactionCategory implements Cloneable {

    private int id;
    private String name;
    private Bitmap image;
    private List<Pattern> patterns;

    public TransactionCategory(int id, String name, Bitmap image) {
        this.id = id;
        this.name = name;
        this.image = image;
        patterns = null;
    }

    public TransactionCategory(int id, String name, Bitmap image, int patternResource) {
        this.id = id;
        this.name = name;
        this.image = image;

        loadPatterns(patternResource);
    }

    private void loadPatterns(int resource) {
        XmlResourceParser xml = AppContext.getInstance().getResources().getXml(resource);
        try {
            if (xml.getEventType() == XmlResourceParser.START_TAG) {
                String s = xml.getName();

                if (s.equals("Category")) {
                    xml.next();
                    if(xml.getName() != null && xml.getName().equalsIgnoreCase("Pattern")){
                        patterns.add(Pattern.compile(xml.getText()));
                    }
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid(String needle) {
        if (patterns == null) {
            return false;
        }
        for(Pattern p : patterns) {
            if (p.matcher(needle).matches()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        TransactionCategory category = new TransactionCategory(getId(), getName(), getImage());
        category.patterns = patterns;
        return  category;
    }
}
