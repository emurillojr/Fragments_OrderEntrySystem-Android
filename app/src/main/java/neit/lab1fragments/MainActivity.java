package neit.lab1fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener, ItemFragment.OnListFragmentInteractionListener {

    static ArrayList<Order> CandyOrders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onListFragmentInteraction(Order item) {
        // send all .get*() from order to public void LoadFragmentData in BlankFragment.java
        // when i click on order in the listview fragment, get everything from that order send it back to blankfragment
        BlankFragment myBlankFragment = (BlankFragment)getSupportFragmentManager().findFragmentById(R.id.blankfragment);
        myBlankFragment.LoadFragmentData(item.getFirstName().toString(), // string
                                         item.getLastName().toString(), // string
                                         item.getTypeOfChocolate(), // string
                                         item.getBars(), // int
                                         item.getShippintType() // boolean
        );
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // toast is from/conected to onButtonPressed(Uri.parse("Order Added, Current Orders: " + CandyOrders.size())); in blankfragment
        Toast toast = Toast.makeText(this, uri.toString(),Toast.LENGTH_LONG);
        toast.show();

    }
}
