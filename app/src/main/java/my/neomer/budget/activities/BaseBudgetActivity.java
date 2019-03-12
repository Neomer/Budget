package my.neomer.budget.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import my.neomer.budget.core.AppContext;

public abstract class BaseBudgetActivity extends AppCompatActivity {

    protected abstract void loadViews();

    protected abstract void postCreationActions();

    protected abstract int getContentResource();

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResource());

        AppContext.getInstance().setResources(this.getResources());

        loadViews();
        postCreationActions();
    }
}
