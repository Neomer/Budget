package my.neomer.budget.activities.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import my.neomer.budget.R;
import my.neomer.budget.activities.BaseBudgetActivity;
import my.neomer.budget.core.DataLoader;
import my.neomer.budget.core.DatabaseTransactionsLoader;
import my.neomer.budget.core.sms.SmsReaderService;
import my.neomer.budget.core.sms.SmsReaderUpdateListener;
import my.neomer.budget.models.Transaction;

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

    @Override
    protected void loadViews() {
        transactionRecyclerView = findViewById(R.id.transactionRecyclerView);
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
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
