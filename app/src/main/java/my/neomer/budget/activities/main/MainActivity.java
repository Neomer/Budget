package my.neomer.budget.activities.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.neomer.budget.R;
import my.neomer.budget.activities.BaseBudgetActivity;
import my.neomer.budget.core.DataLoader;
import my.neomer.budget.core.DatabaseTransactionsLoader;
import my.neomer.budget.core.sms.SmsReaderService;
import my.neomer.budget.core.sms.SmsReaderUpdateListener;
import my.neomer.budget.core.types.Money;
import my.neomer.budget.models.Transaction;
import my.neomer.budget.widgets.DefaultMoneyTextFormatter;

public class MainActivity extends BaseBudgetActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SmsReaderUpdateListener,
        TransactionsRecyclerViewAdapter.OnItemClickListener
{

    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 0;

    private RecyclerView transactionRecyclerView;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TransactionsRecyclerViewAdapter transactionsRecyclerViewAdapter;
    private List<DataLoader<Transaction>> dataLoaders;
    private CollapsingToolbarLayout collapsingToolbar;
    private PieChart chartView;
    private AppBarLayout appBarLayout;
    private List<Transaction> transactionList;

    @Override
    protected void loadViews() {
        transactionRecyclerView = findViewById(R.id.transactionRecyclerView);
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        collapsingToolbar = findViewById(R.id.collapsingToolbar);
        chartView = findViewById(R.id.chartView);
        appBarLayout = findViewById(R.id.appBarLayout);
    }

    private void createChart() {
        if (chartView == null) {
            return;
        }

        chartView.setDragDecelerationFrictionCoef(0.9f);
        chartView.setDrawHoleEnabled(true);
        chartView.setHoleColor(getResources().getColor(R.color.colorPrimary));
        updateChartValues();
    }

    private void updateChartValues() {
        if (chartView == null) {
            return;
        }

        if (transactionList != null) {
            Map<String, Double> categories = new HashMap<>();
            for (Transaction t : transactionList) {
                if (t.getAmount().getAmount() < 0) {
                    String tName = getResources().getString(t.getCategory() != null ? t.getCategory().getName() : R.string.empty_category);
                    if (categories.containsKey(tName)) {
                        double val = categories.get(tName);
                        categories.put(tName, val + t.getAmount().getAmount());
                    } else {
                        categories.put(tName, t.getAmount().getAmount());
                    }
                }
            }


            List<PieEntry> entries = new ArrayList<PieEntry>();
            for (String c : categories.keySet()) {
                entries.add(new PieEntry(Math.abs(categories.get(c).floatValue()), c));
            }
            PieDataSet dataSet = new PieDataSet(entries, null);
            dataSet.setColors(ColorTemplate.PASTEL_COLORS);

            PieData pieData = new PieData(dataSet);
            pieData.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return new DefaultMoneyTextFormatter().formatValue(new Money(value, null));
                }
            });
            pieData.setValueTextColor(Color.BLACK);
            pieData.setValueTextSize(13f);

            chartView.setData(pieData);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        SmsReaderService.getInstance().registerUpdateListener(this);
        SmsReaderService.getInstance().update();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SmsReaderService.getInstance().clearUpdateListener();
    }

    @Override
    protected void postCreationActions() {
        setSupportActionBar(toolbar);

        createChart();
        setupCollapsingToolbar();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        transactionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataLoaders = new ArrayList<>();
        dataLoaders.add(new DatabaseTransactionsLoader());

        List<Transaction> resultList = new ArrayList<>();
        /*
        for (DataLoader<Transaction> loader : dataLoaders) {
            resultList.addAll(loader.loadData());
        }
        */
        transactionsRecyclerViewAdapter = new TransactionsRecyclerViewAdapter(resultList, this);
        transactionRecyclerView.setAdapter(transactionsRecyclerViewAdapter);

        SmsReaderService.getInstance().setContext(this);
    }

    private void setupCollapsingToolbar() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permissions.length == 0) {
            return;
        }
        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_READ_SMS:
            {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsReaderService.getInstance().update();
                } else {
                    SmsReaderService.getInstance().clearUpdateListener();
                }
                break;
            }

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void updateList(List<Transaction> transactionList) {
        transactionsRecyclerViewAdapter.setTransactionList(transactionList);
        this.transactionList = transactionList;
        updateChartValues();
    }

    @Override
    public void requestSmsPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_SMS}, MY_PERMISSIONS_REQUEST_READ_SMS);
    }

    @Override
    public void onItemClick(int position) {
        Transaction t = transactionsRecyclerViewAdapter.getTransactionList().get(position);
        Toast.makeText(this, String.valueOf(t.getAmount().getAmount()), Toast.LENGTH_SHORT).show();
    }
}
