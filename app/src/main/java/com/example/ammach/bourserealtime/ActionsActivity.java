package com.example.ammach.bourserealtime;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import classes.Action;
import helpers.ServeurAction;

public class ActionsActivity extends AppCompatActivity {


    ///////////////////////////////////////////////////////////////////////////
    // adapter du listview
    ///////////////////////////////////////////////////////////////////////////
    class MyAdapter extends BaseAdapter {

        ArrayList<Action> actions;
        public MyAdapter(ArrayList<Action> actionsPasse) {
            this.actions=actionsPasse;
        }

        @Override
        public int getCount() {
            return actions.size();
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
                convertView=inflater.inflate(R.layout.row_actions, null);
            }
            TextView txtActionName=(TextView) convertView.findViewById(R.id.txtActionName);
            TextView txtActionPrice=(TextView) convertView.findViewById(R.id.txtActionPrice);
            TextView txtActionChange=(TextView) convertView.findViewById(R.id.txtActionChange);
            TextView txtActionDay_high=(TextView) convertView.findViewById(R.id.txtActionDay_high);
            TextView txtActionDay_low=(TextView) convertView.findViewById(R.id.txtActionDay_low);

            txtActionName.setText(actions.get(position).getName());
            txtActionPrice.setText(actions.get(position).getPrice());
            txtActionChange.setText(actions.get(position).getChange());
            txtActionDay_high.setText(actions.get(position).getDay_high());
            txtActionDay_low.setText(actions.get(position).getDay_low());



            return convertView;
        }

        public ArrayList<Action> getActions() {
            return actions;
        }

        public void setActions(ArrayList<Action> actions) {
            this.actions = actions;
        }
    }



    ListView listview;
    MyAdapter adapter;
    public static Handler handler;
    ArrayList<Action> actions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);

        getSupportActionBar().setTitle("Liste des Actions");


        //ecoute de reponse du serveur liste des actions
        new ServeurAction();
        actions=new ArrayList<>();
        actions.add(new Action("attente en cours.","attente en cours","attente en cours","attente en cours","attente en cours"));
        actions.add(new Action("attente en cours.","attente en cours","attente en cours","attente en cours","attente en cours"));
        actions.add(new Action("attente en cours","attente en cours","attente en cours","attente en cours","attente en cours"));
        actions.add(new Action("attente en cours","attente en cours","attente en cours","attente en cours","attente en cours"));

        listview=(ListView) findViewById(R.id.listview_actions);
        adapter=new MyAdapter(actions);

        //attente de la liste des actions pour l'afficher
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                ArrayList<Object> actionsAtt= (ArrayList<Object>) msg.obj;
                if (actionsAtt.get(0).getClass().getSimpleName().equals("Action")){
                    actions=new ArrayList<Action>();
                    for (int i = 0; i <actionsAtt.size() ; i++) {
                        actions.add((Action) actionsAtt.get(i));
                    }
                }
                adapter.setActions(actions);
                getAdapter().notifyDataSetChanged();
            }
        };
        listview.setAdapter(adapter);
    }

    public MyAdapter getAdapter() {
        return adapter;
    }
}
