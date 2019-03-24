package my.neomer.budget.core;

import android.content.res.Resources;

import my.neomer.budget.core.database.DatabaseHelper;

public class AppContext {

    //region Singleton
    private static final AppContext ourInstance = new AppContext();
    public static AppContext getInstance() {
        return ourInstance;
    }
    //endregion

    private AppContext() {
    }

    private Resources resources;
    private DatabaseHelper databaseHelper;

    public Resources getResources() {
        return resources;
    }
    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }
    public void setDatabaseHelper(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
}
