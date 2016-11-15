package in.ac.ldrp.xenesis.xenesis;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

import in.ac.ldrp.xenesis.xenesis.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private DatabaseHelper databaseHelper;
    ArrayList<MainCard> arrayList=new ArrayList<>();
    String name[];
    int[] image_id = {R.drawable.computer,R.drawable.civil,R.drawable.ec, R.drawable.electrical, R.drawable.mech,R.drawable.mca,R.drawable.x_mathematica};
  //  String name[]={"Computer","Electronics","Electrical","Mechanical","Workshop"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView)findViewById(R.id.title);
        textView.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        textView.setText("XENESIS");

        databaseHelper = new DatabaseHelper(this);
      //  Toast.makeText(getApplicationContext(),count,Toast.LENGTH_LONG).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        name = getResources().getStringArray(R.array.main);

           // Log.d("for","forloop"+ctr);
        int ctr = 0;
        for (String Name : name) {
            MainCard mainCard = new MainCard(image_id[ctr], Name);
            ctr++;
            arrayList.add(mainCard);
        }
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainCardAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);
    }

    public void call(int pos){
        if(pos==0){
            Intent in = new Intent(this,ComputerDept.class);
            in.putExtra("id","1");
            in.putExtra("name","Computer Department");
            startActivity(in);
        }
        else if(pos==1){
            Intent in = new Intent(this,CivilDept.class);
            in.putExtra("id","2");
            in.putExtra("name","Civil Department");
            startActivity(in);
        }
        else if(pos==2){
            Intent in = new Intent(this,ElectronicsDept.class);
            in.putExtra("id","3");
            in.putExtra("name","Electronics Department");
            startActivity(in);
        }
        else if(pos==3){
            Intent in = new Intent(this,ElectricalDept.class);
            in.putExtra("id","4");
            in.putExtra("name","Electrical Department");
            startActivity(in);
        }
        else if(pos==4){
            Intent in = new Intent(this,MechanicalDept.class);
            in.putExtra("id","5");
            in.putExtra("name","Mechanical Department");
            startActivity(in);
        }
        else if(pos==5){
            Intent in = new Intent(this,McaDept.class);
            in.putExtra("id","6");
            in.putExtra("name","MCA Department");
            startActivity(in);
        }
        else if(pos==6){
            Intent in = new Intent(this,Science.class);
            in.putExtra("id","7");
            in.putExtra("name","S&H Department");
            startActivity(in);
        }

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
       // getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if (id == R.id.aboutLDRP) {
            Intent in = new Intent(this,AboutLDRP.class);
            startActivity(in);
            // Handle the camera action
        } else if (id == R.id.aboutXen) {
            Intent in = new Intent(this,AboutXen.class);
            startActivity(in);
        }
        else if(id==R.id.contact){
            Intent in = new Intent(this,ContactUs.class);
            startActivity(in);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
