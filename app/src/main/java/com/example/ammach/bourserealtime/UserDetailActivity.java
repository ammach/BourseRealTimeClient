package com.example.ammach.bourserealtime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import classes.Change;
import helpers.ServeurChange;

public class UserDetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public  class MyAdapter extends BaseAdapter {

        ArrayList<Change> changes;
        public MyAdapter(ArrayList<Change> changesPasse) {
            this.changes=changesPasse;
        }

        @Override
        public int getCount() {
            return changes.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null) {
                LayoutInflater inflater=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView=inflater.inflate(R.layout.row, null);
            }
            final TextView txtRowName=(TextView) convertView.findViewById(R.id.txtRowName);
            final TextView txtRowPrice=(TextView) convertView.findViewById(R.id.txtRowPrice);
            final TextView txtRowutctime=(TextView) convertView.findViewById(R.id.txtRowutctime);


            txtRowName.setText(changes.get(position).getName());
            txtRowPrice.setText(changes.get(position).getPrice());
            txtRowutctime.setText(changes.get(position).getUtctime());


            return convertView;
        }

        public ArrayList<Change> getChanges() {
            return changes;
        }

        public void setChanges(ArrayList<Change> changes) {
            this.changes = changes;
        }
    }

    TextView username;
    ListView listview;
    MyAdapter adapter;
    public static Handler handler;
    ArrayList<Change> changes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String name=getIntent().getStringExtra("name");
        String password=getIntent().getStringExtra("password");
        Toast.makeText(UserDetailActivity.this,name+" "+password, Toast.LENGTH_SHORT).show();

        View header=navigationView.getHeaderView(0);
        username= (TextView) header.findViewById(R.id.username);
        username.setText(name);

        new ServeurChange();
        changes=new ArrayList<Change>();
        changes.add(new Change("attente en cours","attente en cours","attente en cours"));
        changes.add(new Change("attente en cours","attente en cours","attente en cours"));
        changes.add(new Change("attente en cours","attente en cours","attente en cours"));
        changes.add(new Change("attente en cours","attente en cours","attente en cours"));

        adapter=new MyAdapter(changes);
        listview=(ListView) findViewById(R.id.listview);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                ArrayList<Object> changesAtt= (ArrayList<Object>) msg.obj;
                if (changesAtt.get(0).getClass().getSimpleName().equals("Change")){
                    changes=new ArrayList<Change>();
                    for (int i = 0; i <changesAtt.size() ; i++) {
                        changes.add((Change) changesAtt.get(i));
                    }
                Toast.makeText(UserDetailActivity.this, ""+changesAtt.size(), Toast.LENGTH_SHORT).show();
                }
                adapter.setChanges(changes);
                getAdapter().notifyDataSetChanged();
            }
        };





        listview.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_detail, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.actions) {
            startActivity(new Intent(this,ActionsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public MyAdapter getAdapter() {
        return adapter;
    }
}
