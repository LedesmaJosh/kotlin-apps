package com.example.perceptioncheck;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    private EditText characterName;
    private EditText Age;
    private EditText Race;
    private EditText Class;
    private EditText strValue;
    private EditText StrModifier;
    private EditText DexValue;
    private EditText DexModifier;
    private EditText ConValue;
    private EditText ConModifier;
    private EditText IntValue;
    private EditText IntModifier;
    private EditText WisValue;
    private EditText WisModifier;
    private EditText ChaValue;
    private EditText ChaModifier;
    private int lockValue = 1;
    private ImageButton lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_sheet);
        startupUI();
        SharedPreferences sharedPref = getSharedPreferences("CharacterInfo", MODE_PRIVATE);

        setName(sharedPref.getString("Name", null));
        setAge(sharedPref.getString("Age", null));
        setRace(sharedPref.getString("Race",null));
        setClass(sharedPref.getString("Class",null));
        setStrValue(sharedPref.getString("Str",null));
        setDexValue(sharedPref.getString("Dex",null));
        setConValue(sharedPref.getString("Con",null));
        setIntValue(sharedPref.getString("Int",null));
        setWisValue(sharedPref.getString("Wis", null));
        setChaValue(sharedPref.getString("Cha",null));
        setStrModifier(sharedPref.getString("StrMod",null));
        setDexModifier(sharedPref.getString("DexMod",null));
        setConModifier(sharedPref.getString("ConMod",null));
        setIntModifier((sharedPref.getString("IntMod",null)));
        setWisModifier(sharedPref.getString("WisMod",null));
        setChaModifier(sharedPref.getString("ChaMod",null));
        lockValue=(sharedPref.getInt("lockValue", 1));
        locktextNoBtn();
    }
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences("CharacterInfo", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Name",getName());
        editor.putString("Age",getAge());
        editor.putString("Race",getRace());
        editor.putString("Class",getchaClass() );
        editor.putString("Str",getStr());
        editor.putString("Dex",getDex());
        editor.putString("Con",getCon());
        editor.putString("Int",getInt());
        editor.putString("Wis",getWis());
        editor.putString("Cha",getCha() );
        editor.putString("StrMod",getStrMod());
        editor.putString("DexMod",getDexMod());
        editor.putString("ConMod",getConMod());
        editor.putString("IntMod",getIntMod());
        editor.putString("WisMod",getWisMod());
        editor.putString("ChaMod",getChaMod());
        editor.putInt("lockValue",lockValue);
        editor.apply();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        SharedPreferences sharedPref = getSharedPreferences("CharacterInfo", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Name",getName());
        editor.putString("Age",getAge());
        editor.putString("Race",getRace());
        editor.putString("Class",getchaClass() );
        editor.putString("Str",getStr());
        editor.putString("Dex",getDex());
        editor.putString("Con",getCon());
        editor.putString("Int",getInt());
        editor.putString("Wis",getWis());
        editor.putString("Cha",getCha() );
        editor.putString("StrMod",getStrMod());
        editor.putString("DexMod",getDexMod());
        editor.putString("ConMod",getConMod());
        editor.putString("IntMod",getIntMod());
        editor.putString("WisMod",getWisMod());
        editor.putString("ChaMod",getChaMod());
        editor.putInt("lockValue",lockValue);
        editor.apply();

    }
    public void toSpellTracker(View view){
        Intent switchActivity = new Intent(this, SpellTracker.class);
        startActivity(switchActivity);
    }
    public void locktextNoBtn(){
        if((lockValue%2)==0){
            characterName.setEnabled(false);
            Age.setEnabled(false);
            Race.setEnabled(false);
            Race.setEnabled(false);
            Class.setEnabled(false);
            strValue.setEnabled(false);
            DexValue.setEnabled(false);
            ConValue.setEnabled(false);
            IntValue.setEnabled(false);
            WisValue.setEnabled(false);
            ChaValue.setEnabled(false);
            setStrModifier(calculateModifier(Integer.parseInt(strValue.getText().toString())));
            setDexModifier(calculateModifier(Integer.parseInt(DexValue.getText().toString())));
            setConModifier(calculateModifier(Integer.parseInt(ConValue.getText().toString())));
            setIntModifier(calculateModifier(Integer.parseInt(IntValue.getText().toString())));
            setWisModifier(calculateModifier(Integer.parseInt(WisValue.getText().toString())));
            setChaModifier(calculateModifier(Integer.parseInt(ChaValue.getText().toString())));
            lock.setImageResource(R.drawable.textlock);
        }
        if((lockValue%2)==1){
            characterName.setEnabled(true);
            Age.setEnabled(true);
            Race.setEnabled(true);
            Race.setEnabled(true);
            Class.setEnabled(true);
            strValue.setEnabled(true);
            DexValue.setEnabled(true);
            ConValue.setEnabled(true);
            IntValue.setEnabled(true);
            WisValue.setEnabled(true);
            ChaValue.setEnabled(true);
            lock.setImageResource(R.drawable.textunlock);
        }
    }
    public void lockTexts(View view){
        lockValue++;
        if((lockValue%2)==0){
            characterName.setEnabled(false);
            Age.setEnabled(false);
            Race.setEnabled(false);
            Race.setEnabled(false);
            Class.setEnabled(false);
            strValue.setEnabled(false);
            DexValue.setEnabled(false);
            ConValue.setEnabled(false);
            IntValue.setEnabled(false);
            WisValue.setEnabled(false);
            ChaValue.setEnabled(false);
            setStrModifier(calculateModifier(Integer.parseInt(strValue.getText().toString())));
            setDexModifier(calculateModifier(Integer.parseInt(DexValue.getText().toString())));
            setConModifier(calculateModifier(Integer.parseInt(ConValue.getText().toString())));
            setIntModifier(calculateModifier(Integer.parseInt(IntValue.getText().toString())));
            setWisModifier(calculateModifier(Integer.parseInt(WisValue.getText().toString())));
            setChaModifier(calculateModifier(Integer.parseInt(ChaValue.getText().toString())));
            lock.setImageResource(R.drawable.textlock);

        }
        if((lockValue%2)==1){
            characterName.setEnabled(true);
            Age.setEnabled(true);
            Race.setEnabled(true);
            Race.setEnabled(true);
            Class.setEnabled(true);
            strValue.setEnabled(true);
            DexValue.setEnabled(true);
            ConValue.setEnabled(true);
            IntValue.setEnabled(true);
            WisValue.setEnabled(true);
            ChaValue.setEnabled(true);
            lock.setImageResource(R.drawable.textunlock);
        }
    }
    public void startupUI(){
        characterName = (EditText) findViewById(R.id.characterName);
        Age = (EditText) findViewById(R.id.characterAge);
        Race = (EditText) findViewById(R.id.characterRace);
        Class = (EditText) findViewById(R.id.characterClass);
        strValue = (EditText) findViewById(R.id.characterStr);
        StrModifier = (EditText) findViewById(R.id.strModifier);
        DexValue = (EditText) findViewById(R.id.characterDex);
        DexModifier = (EditText) findViewById(R.id.dexModifier);
        ConValue  = (EditText) findViewById(R.id.characterCon);
        ConModifier = (EditText) findViewById(R.id.conModifier);
        IntValue = (EditText) findViewById(R.id.characterInt);
        IntModifier = (EditText) findViewById(R.id.intModifier);
        WisValue = (EditText) findViewById(R.id.characterWis);
        WisModifier = (EditText) findViewById(R.id.wisModifier);
        ChaValue  = (EditText) findViewById(R.id.characterCha);
        ChaModifier  = (EditText) findViewById(R.id.chaModifier);
        lock = (ImageButton) findViewById(R.id.createCharacter);
        StrModifier.setKeyListener(null);
        DexModifier.setKeyListener(null);
        ConModifier.setKeyListener(null);
        IntModifier.setKeyListener(null);
        WisModifier.setKeyListener(null);
        ChaModifier.setKeyListener(null);
    }
    public String calculateModifier(int stat){
        String mod = "0";
        if(stat ==1){
            mod ="-5";
        }
        if(stat == 2 || stat == 3){
            mod = "-4";
        }
        if(stat == 4 || stat ==5){
            mod = "-3";
        }
        if(stat == 6 || stat ==7 ){
            mod = "-2";
        }
        if(stat == 8 || stat == 9){
            mod= "-1";
        }
        if(stat == 10 || stat ==11){
            mod = "0";
        }
        if(stat == 12 || stat == 13){
            mod = "1";
        }
        if(stat == 14 || stat ==15){
            mod = "2";
        }
        if(stat == 16 || stat == 17){
            mod = "3";
        }
        if(stat == 18 || stat ==19){
            mod = "4";
        }
        if(stat == 20 || stat == 21){
            mod = "5";
        }
        if(stat == 22 || stat == 23){
            mod = "6";
        }
        if(stat == 24 || stat ==25){
            mod= "7";
        }
        if(stat == 26 || stat ==27){
            mod=  "8";
        }
        if(stat == 28 || stat == 29){
            mod = "9";
        }
        if(stat == 30){
            mod = "10";
        }
        return mod;
    }


    public void setName(String name){
        characterName.setText(name);
    }
    public void setAge(String age){
        Age.setText(age);
    }
    public void setRace(String race){
        Race.setText(race);
    }
    public void setClass(String chaclass){
        Class.setText(chaclass);
    }
    public void setStrValue(String str){
        strValue.setText(str);
    }
    public void setStrModifier(String mod){
        StrModifier.setText(mod);
    }
    public void setDexValue(String dex){
        DexValue.setText(dex);
    }
    public void setDexModifier(String dex){
        DexModifier.setText(dex);
    }
    public void setConValue(String con){
        ConValue.setText(con);
    }
    public void setConModifier(String mod){
        ConModifier.setText(mod);
    }
    public void setIntValue(String INT){
        IntValue.setText(INT);
    }
    public void setIntModifier(String mod){
        IntModifier.setText(mod);
    }
    public void setWisValue(String wis){
        WisValue.setText(wis);
    }
    public void setWisModifier(String mod){
        WisModifier.setText(mod);
    }
    public void setChaValue(String cha){
        ChaValue.setText(cha);
    }
    public void setChaModifier(String mod){
        ChaModifier.setText(mod);
    }
    public String getName(){
        return characterName.getText().toString();
    }
    public String getAge(){
        return Age.getText().toString();
    }
    public String getRace(){
        return Race.getText().toString();
    }
    public String getchaClass(){
        return Class.getText().toString();
    }
    public String getStr(){
        return strValue.getText().toString();
    }
    public String getDex(){
        return DexValue.getText().toString();
    }
    public String getCon(){
        return ConValue.getText().toString();
    }
    public String getInt(){
        return IntValue.getText().toString();
    }
    public String getWis(){
        return WisValue.getText().toString();
    }
    public String getCha(){
        return ChaValue.getText().toString();
    }
    public String getStrMod(){
        return StrModifier.getText().toString();
    }
    public String getDexMod(){
        return DexModifier.getText().toString();
    }
    public String getConMod(){
        return ConModifier.getText().toString();
    }
    public String getIntMod(){
        return IntModifier.getText().toString();
    }
    public String getWisMod(){
        return WisModifier.getText().toString();
    }
    public String getChaMod(){
        return ChaModifier.getText().toString();
    }
}