package com.example.bv;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //TextView formula で;
    TextView result;

    EditText flopBetSize;
    double flopBetSizeD;
    EditText turnBetSize;
    double turnBetSizeD;
    EditText riverBetSize;
    double riverBetSizeD;
    EditText valueEquity;
    double valueEquityD;
    EditText bulffEquity;
    double bulffEquityD;
    TextView flopValuePercent;
    TextView flopBulffPercent;
    TextView turnValuePercent;
    TextView turnBulffPercent;
    TextView riverValuePercent;
    TextView riverBulffPercent;

    double flopPotOdds;
    double turnPotOdds;
    double riverPotOdds;

    double flopBeta;
    double turnBeta;
    double riverBeta;

    double flopBetaRes;
    double turnBetaRes;
    double riverBetaRes;
    double flopValuePercentRes ;
    double turnValuePercentRes;
    double riverValuePercentRes;
    double flopBulffPercentRes ;
    double turnBulffPercentRes;
    double riverBulffPercentRes ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flopBetSize = findViewById(R.id.editFlopBet);
        turnBetSize = findViewById(R.id.editTurnBet);
        riverBetSize = findViewById(R.id.editRiverBet);
        valueEquity = findViewById(R.id.valueEquity);
        bulffEquity = findViewById(R.id.bulffEquity);

        Default();
        tapOK();


    }


    public void Default(){
        flopBetSize.setText("33.3");
        turnBetSize.setText("75");
        riverBetSize.setText("150");
        valueEquity.setText("100");
        bulffEquity.setText("0");
    }

    public void setFlopBetSize(){
        //入力された文字列を数値に変えて計算に使えるようにする
        flopBetSizeD = Double.parseDouble(flopBetSize.getText().toString()) / 100;
    }
    public  void setTurnBetSize(){
        turnBetSizeD = Double.parseDouble(turnBetSize.getText().toString()) / 100;
    }
    public  void setRiverBetSize(){
        riverBetSizeD = Double.parseDouble(riverBetSize.getText().toString()) / 100;
    }

    public  void setValueEquity(){
        valueEquityD = Double.parseDouble(valueEquity.getText().toString()) / 100;
    }
    public  void setbulffEquity(){
        bulffEquityD = Double.parseDouble(bulffEquity.getText().toString()) / 100;
    }
    public void calc(){
         flopPotOdds = flopBetSizeD / ((flopBetSizeD * 2) + 1);
         turnPotOdds = turnBetSizeD / ((turnBetSizeD * 2) + 1);
         riverPotOdds = riverBetSizeD / ((riverBetSizeD * 2) + 1);
         flopBeta = 1 - flopPotOdds;//1-odds = beta
         turnBeta = 1 - turnPotOdds;
         riverBeta = 1 - riverPotOdds;

         flopBetaRes = flopBeta * turnBeta * riverBeta;
         turnBetaRes = turnBeta * riverBeta;
         riverBetaRes = riverBeta;

         flopValuePercentRes = (bulffEquityD - flopBetaRes) / (bulffEquityD - valueEquityD);
         turnValuePercentRes = (bulffEquityD - turnBetaRes) / (bulffEquityD - valueEquityD);
         riverValuePercentRes = (bulffEquityD - riverBetaRes) / (bulffEquityD - valueEquityD);

         flopValuePercentRes *= 100;
         turnValuePercentRes *= 100;
         riverValuePercentRes *= 100;

         flopValuePercentRes = ((double)Math.round(flopValuePercentRes * 10))/10;
         turnValuePercentRes = ((double)Math.round(turnValuePercentRes * 10))/10;
         riverValuePercentRes = ((double)Math.round(riverValuePercentRes * 10))/10;

         flopBulffPercentRes = 100 - flopValuePercentRes;
         turnBulffPercentRes = 100 - turnValuePercentRes;
         riverBulffPercentRes = 100 - riverValuePercentRes;

        flopBulffPercentRes = ((double)Math.round(flopBulffPercentRes * 10))/10;
        turnBulffPercentRes = ((double)Math.round(turnBulffPercentRes * 10))/10;
        riverBulffPercentRes = ((double)Math.round(riverBulffPercentRes * 10))/10;

         setResult();

    }

    public void setResult(){
        flopValuePercent = findViewById(R.id.flopValuePercent);
        flopBulffPercent = findViewById(R.id.flopBulffPercent);
        turnValuePercent = findViewById(R.id.turnValuePercent);
        turnBulffPercent = findViewById(R.id.turnBulffPercent);
        riverValuePercent = findViewById(R.id.riverValuePercent);
        riverBulffPercent = findViewById(R.id.riverBulffPercent);

        flopValuePercent.setText( Double.toString(flopValuePercentRes) + "%" );
        flopBulffPercent.setText( Double.toString(flopBulffPercentRes)  + "%");
        turnValuePercent.setText( Double.toString(turnValuePercentRes)  + "%");
        turnBulffPercent.setText( Double.toString(turnBulffPercentRes)  + "%");
        riverValuePercent.setText( Double.toString(riverValuePercentRes)  + "%");
        riverBulffPercent.setText( Double.toString(riverBulffPercentRes)  + "%");



    }


    public void tapOK(){
        setFlopBetSize();
        setTurnBetSize();
        setRiverBetSize();
        setValueEquity();
        setbulffEquity();
        calc();
    }

    public void tapOK(View view){

        try{
            setFlopBetSize();
            setTurnBetSize();
            setRiverBetSize();
            setValueEquity();
            setbulffEquity();
            calc();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "値を全て入力してください", Toast.LENGTH_LONG).show();
        }

    }

    public void tapAC(View view){
        try{
            flopBetSize.setText(null);
            turnBetSize.setText(null);
            riverBetSize.setText(null);
            valueEquity.setText(null);
            bulffEquity.setText(null);

            flopValuePercent.setText(null);
            flopBulffPercent.setText(null);
            turnValuePercent.setText(null);
            turnBulffPercent.setText(null);
            riverValuePercent.setText(null);
            riverBulffPercent.setText(null);

        }catch (Exception e){
            ;
        }
    }
}