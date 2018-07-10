package com.csoft.www.jotto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mText;
    private Button button1;
    private EditText edit1;
    private ListView mListView;
    ArrayAdapter adapter;
    //private Resources mResources = this.getResources();
    //private Resources res = this.getResources();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> arrAnswer = new ArrayList<String>();


        mText = (EditText)super.findViewById(R.id.editText);
        mText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mListView = (ListView)super.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrAnswer);
        mListView.setAdapter(adapter);

        button1 = (Button)super.findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String answer = "Совпало " + lettersCount(edit1.getText().toString(), res.getString(R.string.question)) + " букв";
                String str1 = getResources().getString(R.string.question);
                String count = lettersCount(getResources().getString(R.string.question), mText.getText().toString());
                String answer = "Совпало " + count + " букв";
                if (mText.getText().toString().equals(getResources().getString(R.string.question))) {
                    answer = "Ура! Слово угадано! Вы победили!";
                }
                adapter.add(mText.getText().toString()+"-"+count);
                adapter.setNotifyOnChange(true);
                ShowAlert(answer);
                //mListView.add
            }
        });

    }

    public void ShowAlert(String s){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

        alertDialog.setTitle(R.string.app_name);
        alertDialog.setMessage(s);

        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    public String lettersCount(String s1,String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int[] ch = new int[arr2.length];
        int count = 0;


       outer : for (int i=0;i<arr1.length;i++) {
            for (int j=0;j<arr2.length;j++){
                if ((arr1[i]==arr2[j])&(ch[j]!=arr2[j])) {count++; ch[j]=j;continue outer;}
            }
        }

        return String.valueOf(count);
    }
}
