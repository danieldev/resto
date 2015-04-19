package fr.telecom.resto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

import fr.telecom.resto.Adapter.ImagePagerAdapter;


public class WelcomeActivity extends Activity {

    int[] mResources = {
            R.drawable.welcome,
            R.drawable.entree_foie_gras,
            R.drawable.main_dish_boeuf,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ViewPager viewer=(ViewPager)findViewById(R.id.welcomeImages);
        ImagePagerAdapter imgAdapter=new ImagePagerAdapter(this,mResources);
        viewer.setAdapter(imgAdapter);

        Spinner tableNum=(Spinner)findViewById(R.id.spinnerTableNum);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.tableNum_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        tableNum.setAdapter(adapter);

//        NumberPicker numPerson = (NumberPicker) findViewById(R.id.numberPerson);
//        numPerson.setMinValue(1);
//        numPerson.setValue(1);
//        numPerson.setMaxValue(8);
//        numPerson.setWrapSelectorWheel(true);

        Button enter= (Button) findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(mainIntent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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
}
