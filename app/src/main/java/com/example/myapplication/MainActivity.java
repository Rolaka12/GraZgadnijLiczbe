package com.example.myapplication;

import static java.lang.Integer.parseInt;
import static java.sql.Types.NULL;
import static java.util.Optional.empty;

import android.annotation.SuppressLint;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView liczbaProb, najlepszyWynik;
    Button sprawdz, reset;
    LinearLayout tlo;
    EditText wpisanaLiczbaTekst;
    public int liczbaProbLiczba;
    public int wpisanaLiczba;
    public String wpisanaLiczbaString, komunikat;
    public int wylosowanaLiczba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        liczbaProb = findViewById(R.id.liczbaProb);
        wpisanaLiczbaTekst = findViewById(R.id.wpisanaLiczbaTekst);
        najlepszyWynik = findViewById(R.id.najlepszyWynik);
        tlo = findViewById(R.id.main);
        Random random = new Random();
        wylosowanaLiczba = random.nextInt(100) + 1;
        liczbaProbLiczba = 0;
        sprawdz = findViewById(R.id.sprawdz);
        reset = findViewById(R.id.reset);
        sprawdz.setOnClickListener(v -> {
            if (liczbaProbLiczba == 10) {
                Toast.makeText(this, "Wylosowana liczba to: " + wylosowanaLiczba, Toast.LENGTH_LONG).show();
                Reset();
            }else if(liczbaProbLiczba < 10) {
                if (wpisanaLiczbaTekst.getText().toString() != "") {
                    wpisanaLiczbaString = wpisanaLiczbaTekst.getText().toString();
                    wpisanaLiczba = parseInt(wpisanaLiczbaString);
                    if (wpisanaLiczba < wylosowanaLiczba) {
                        if (wpisanaLiczba + 10 < wylosowanaLiczba) {
                            tlo.setBackgroundColor(Color.RED);
                        } else if (wpisanaLiczba + 10 > wylosowanaLiczba) {
                            tlo.setBackgroundColor(Color.YELLOW);
                        }
                        komunikat = "Wpisana liczba jest mniejsza niż wylosowana liczba";
                        liczbaProbLiczba += 1;
                        liczbaProb.setText("Liczba prob: " + liczbaProbLiczba);
                        wpisanaLiczbaTekst.setText("");
                    } else if (wpisanaLiczba > wylosowanaLiczba) {
                        if (wpisanaLiczba - 10 > wylosowanaLiczba) {
                            tlo.setBackgroundColor(Color.RED);
                        } else if (wpisanaLiczba - 10 < wylosowanaLiczba) {
                            tlo.setBackgroundColor(Color.YELLOW);
                        }
                        komunikat = "Wpisana liczba jest wieksza niż wylosowana liczba";
                        liczbaProbLiczba += 1;
                        wpisanaLiczbaTekst.setText("");
                        liczbaProb.setText("Liczba prob: " + liczbaProbLiczba);
                    } else {
                        tlo.setBackgroundColor(Color.GREEN);
                        komunikat = "Wpisana liczba jest równa wylosowanej liczbie";
                        liczbaProbLiczba += 1;
                        liczbaProb.setText("Liczba prob: " + liczbaProbLiczba);
                        if (liczbaProbLiczba == 1) {
                            najlepszyWynik.setText("Najlepszy wynik: " + liczbaProbLiczba + " proba");
                        } else if (liczbaProbLiczba > 4) {
                            najlepszyWynik.setText("Najlepszy wynik: " + liczbaProbLiczba + " prob");
                        } else {
                            najlepszyWynik.setText("Najlepszy wynik: " + liczbaProbLiczba + " proby");
                        }
                        liczbaProbLiczba = 10;
                    }
                    Toast.makeText(this, komunikat, Toast.LENGTH_LONG).show();
                }

            }
        });
        reset.setOnClickListener(v -> {
            liczbaProb.setText("Liczba prob: 0");
            wpisanaLiczbaTekst.setText("");
            wylosowanaLiczba = random.nextInt(100) + 1;
            liczbaProbLiczba = 0;
            tlo.setBackgroundColor(Color.WHITE);
        });
    }
    public void Reset(){
        liczbaProb.setText("Liczba prob: 0");
        wpisanaLiczbaTekst.setText("");
        Random random = new Random();
        wylosowanaLiczba = random.nextInt(100) + 1;
        liczbaProbLiczba = 0;
        tlo.setBackgroundColor(Color.WHITE);
    }
}