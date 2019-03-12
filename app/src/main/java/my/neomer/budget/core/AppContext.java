package my.neomer.budget.core;

import android.content.res.Resources;

public class AppContext {
    private static final AppContext ourInstance = new AppContext();

    public static AppContext getInstance() {
        return ourInstance;
    }

    private AppContext() {
    }

    private Resources resources;

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
