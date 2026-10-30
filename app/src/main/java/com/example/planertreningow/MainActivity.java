package com.example.planertreningow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int LicznikSerii = 0;
    int LacznaLiczbaSerii = 0;
    int aktualneCwiczenie = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        TextView NazwaCwiczeniaTextView = findViewById(R.id.textViewnazwaCwiczenia);
        TextView LicznikSeriiTextView = findViewById(R.id.textViewLicznikSerii);
        TextView LiczbaLacznieWykonanychSeriiTextView = findViewById(R.id.textViewLacznaLiczbaWykonanychSerii);
        ImageView AktualneCwiczenieImageView = findViewById(R.id.imageViewAktualneCwiczenie);
        ImageView SportowiecImageView = findViewById(R.id.imageView);

        Button LosujCwiczenieButton = findViewById(R.id.buttonLosujCwiczenie);
        Button PlusButton = findViewById(R.id.buttonPlus);
        Button MinusButton = findViewById(R.id.buttonMinus);
        Button NastepneCwiczenieButton = findViewById(R.id.buttonNastepneCwiczenie);
        Button ResetButton = findViewById(R.id.buttonReset);

        SportowiecImageView.setImageResource(R.drawable.sportowiec);
        AktualneCwiczenieImageView.setImageResource(R.drawable.cwiczenie0);

        LicznikSeriiTextView.setText(String.valueOf(LicznikSerii));
        LiczbaLacznieWykonanychSeriiTextView.setText("Łączna liczba serii: " + String.valueOf(LacznaLiczbaSerii));


        LosujCwiczenieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cwiczenie = LosujCwiczenie();

                String cwiczenieZdjecie = "cwiczenie" + String.valueOf(cwiczenie);

                int imageResId = getResources().getIdentifier(
                        cwiczenieZdjecie,
                        "drawable",
                        getPackageName()
                );

                String napis = "";

                switch (cwiczenie){
                    case 1: napis = "Przysiad"; break;
                    case 2: napis = "Pompki"; break;
                    case 3: napis = "Plank"; break;
                    case 4: napis = "Wykroki"; break;
                    case 5: napis = "Burpees"; break;
                    case 6: napis = "Podciąganie"; break;
                }

                if (imageResId != 0) {
                    AktualneCwiczenieImageView.setImageResource(imageResId);
                }
                NazwaCwiczeniaTextView.setText(napis);
                LicznikSerii = 0;
                LicznikSeriiTextView.setText(String.valueOf(LicznikSerii));
            }
        });

        PlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LicznikSerii += 1;
                LicznikSeriiTextView.setText(String.valueOf(LicznikSerii));
            }
        });

        MinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LicznikSerii != 0){
                    LicznikSerii -= 1;
                    LicznikSeriiTextView.setText(String.valueOf(LicznikSerii));
                }
            }
        });

        NastepneCwiczenieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AktualizujLicznikSerii();

                int cwiczenie = LosujCwiczenie();

                String cwiczenieZdjecie = "cwiczenie" + String.valueOf(cwiczenie);

                int imageResId = getResources().getIdentifier(
                        cwiczenieZdjecie,
                        "drawable",
                        getPackageName()
                );

                String napis = "";

                switch (cwiczenie){
                    case 1: napis = "Przysiad"; break;
                    case 2: napis = "Pompki"; break;
                    case 3: napis = "Plank"; break;
                    case 4: napis = "Wykroki"; break;
                    case 5: napis = "Burpees"; break;
                    case 6: napis = "Podciąganie"; break;
                }
                NazwaCwiczeniaTextView.setText(napis);

                if (imageResId != 0) {
                    AktualneCwiczenieImageView.setImageResource(imageResId);
                }
                AktualizujLicznikSerii();
                LicznikSeriiTextView.setText(String.valueOf(LicznikSerii));
                LiczbaLacznieWykonanychSeriiTextView.setText("Łączna liczba serii: " + String.valueOf(LacznaLiczbaSerii));
            }
        });

        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LicznikSerii = 0;
                LacznaLiczbaSerii = 0;
                aktualneCwiczenie = 0;

                LicznikSeriiTextView.setText(String.valueOf(LicznikSerii));
                LiczbaLacznieWykonanychSeriiTextView.setText("Łączna liczba serii: " + String.valueOf(LacznaLiczbaSerii));

                AktualneCwiczenieImageView.setImageResource(R.drawable.cwiczenie0);
                NazwaCwiczeniaTextView.setText("Losuj ćwiczenie");
            }
        });
    }

    void AktualizujLicznikSerii(){
        LacznaLiczbaSerii += LicznikSerii;
        LicznikSerii = 0;
    }

    int LosujCwiczenie()
    {
        Random random = new Random();

        int min = 1;
        int max = 6;

        int wylosowaneCwiczenie = random.nextInt(max - min + 1) + min;

        while (wylosowaneCwiczenie == aktualneCwiczenie)
        {
            wylosowaneCwiczenie = random.nextInt(max - min + 1) + min;
        }
        aktualneCwiczenie = wylosowaneCwiczenie;

        return wylosowaneCwiczenie;
    }
}