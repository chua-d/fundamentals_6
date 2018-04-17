package com.example.danceciliochua.lesson4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private String mSpinnerlabel = "";
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner)findViewById(R.id.label_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array, android.R.layout.simple_spinner_item);
        phoneTextView = (TextView)findViewById(R.id.text_phonelabel);

        if( spinner != null ) {
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        }

    }

    public void showText(View view) {
        EditText editText = (EditText)findViewById(R.id.editText_main);
        if(editText != null) {
            String showString = (editText.getText().toString() + " - " + mSpinnerlabel);
            Toast.makeText(this, showString, Toast.LENGTH_SHORT).show();
            phoneTextView.setText(showString);

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSpinnerlabel = parent.getItemAtPosition(position).toString();
        showText(view);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d(TAG, getString(R.string.nothing_selected));

    }
}
