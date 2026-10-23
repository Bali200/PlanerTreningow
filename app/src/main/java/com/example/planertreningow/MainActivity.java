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

public class MainActivity extends AppCompatActivity {

    int LicznikSerii = 0;
    int LacznaLiczbaSerii = 0;

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

        SportowiecImageView.setImageResource(R.drawable.sportowiec);
        AktualneCwiczenieImageView.setImageResource(R.drawable.cwiczenie0);

        LicznikSeriiTextView.setText(String.valueOf(LicznikSerii));
        LiczbaLacznieWykonanychSeriiTextView.setText("Łączna liczba serii:" + String.valueOf(LacznaLiczbaSerii));

        LosujCwiczenieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int min = 1;
                int max = 6;
                int range = max - min + 1;
                int cwiczenie = (int)(Math.random() * range) + min;

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
                AktualizujLicznikSerii();
                LicznikSeriiTextView.setText(String.valueOf(LicznikSerii));
                LiczbaLacznieWykonanychSeriiTextView.setText("Łączna liczba serii:" + String.valueOf(LacznaLiczbaSerii));
            }
        });

        PlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LicznikSerii += 1;
                LicznikSeriiTextView.setText(String.valueOf(LicznikSerii));
            }
        });
    }

    void AktualizujLicznikSerii(){
        LacznaLiczbaSerii += LicznikSerii;
        LicznikSerii = 0;
    }

}