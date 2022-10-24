package com.example.perceptioncheck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SpellTracker extends AppCompatActivity {

    private EditText spellName;
    private EditText spellLevel;
    private EditText spellRadius;
    private Button btn;
    private ListView lView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spell_tracker);
        doThing();
        SharedPreferences sharedpref = getSharedPreferences("SpellList", MODE_PRIVATE);
        int arrayListSize = sharedpref.getInt("array_size",0);
        if(arrayListSize !=0){
            for(int i =0; i<arrayListSize;i++){
                arrayList.add(sharedpref.getString("Spell " +i,  null));}
            adapter.notifyDataSetChanged();

        }

    }
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences sharedpref =  getSharedPreferences("SpellList", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putInt("array_size",arrayList.size());
        for(int i=0; i<arrayList.size();i++)
            editor.putString("Spell "+i,arrayList.get(i));
        editor.apply();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        SharedPreferences sharedpref =  getSharedPreferences("SpellList", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putInt("array_size",arrayList.size());
        for(int i=0; i<arrayList.size();i++)
            editor.putString("Spell "+i,arrayList.get(i));
        editor.apply();
    }
    public void doThing(){
        spellName = (EditText) findViewById(R.id.spellName);
        spellLevel =(EditText) findViewById(R.id.spellLevel);
        spellRadius = (EditText) findViewById(R.id.spellRadius);
        btn = (Button) findViewById(R.id.addButton);
        lView = (ListView) findViewById(R.id.spellList);



        //SPELL TRACKER METHODS3
        arrayList= new ArrayList<>();
        adapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        lView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String spelldetails = "Spell: ";
                spelldetails += spellName.getText().toString() + "   Level: ";
                spelldetails += spellLevel.getText().toString() + "  Radius: ";
                spelldetails += spellRadius.getText().toString();

                arrayList.add(spelldetails);
                adapter.notifyDataSetChanged();
            }
        });


        //SPELL TRACKER METHODS END
    }

    public void toCharacterSheet(View view){
        Intent switchActivity = new Intent(this, MainActivity.class);
        startActivity(switchActivity);
    }
    public void clearSpells(View view){
        arrayList.clear();
        adapter.notifyDataSetChanged();
    }
}
