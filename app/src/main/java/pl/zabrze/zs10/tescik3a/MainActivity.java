package pl.zabrze.zs10.tescik3a;

import static android.view.View.INVISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pytanie> pytania = new ArrayList<>();
    private TextView textView;
    private RadioButton [] radioButtons = new RadioButton[3];
    private RadioGroup radioGroup;
    private Button buttonSprawdz;
    private Button buttonDalej;

    private int aktualne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pytania.add(new Pytanie("Na jakim systemie operacyjnym został zbudowany Android",
                "Windows","Dos","Linux",2));
        pytania.add(new Pytanie("Nazwa wersji Andorida to często",
                "ciasteczko.","owoc","warzywko",0));
        pytania.add(new Pytanie("Językiem rekomendowanym przez Google do pisania \n aplikacji na Androida  jest",
                "Java","Kotlin","C++",1));

        textView = findViewById(R.id.textViewPytanie);
        radioButtons[0] = findViewById(R.id.radioButton);
        radioButtons[1] = findViewById(R.id.radioButton2);
        radioButtons[2] = findViewById(R.id.radioButton3);

        wyswietlPytanie(0);
        buttonDalej = findViewById(R.id.buttonDalej);
        buttonDalej.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aktualne++;
                        if(aktualne == pytania.size()){
                            buttonDalej.setVisibility(INVISIBLE);
                            aktualne--;
                        }
                        wyswietlPytanie(aktualne);
                    }
                }
        );
        radioGroup = findViewById(R.id.radioGroup);
        buttonSprawdz = findViewById(R.id.buttonSprawdz);
        buttonSprawdz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       int idZaznaczone = radioGroup.getCheckedRadioButtonId();
                      // RadioButton radioButton = findViewById(idZaznaczone);
                        for (int i = 0; i <radioButtons.length; i++) {
                           if( radioButtons[i].getId() == idZaznaczone){
                                pytania.get(aktualne).sprawdzOdpowiedz(i);
                                if(pytania.get(aktualne).isCzyUdzielonoPoprawnejOdpowiedzi()){
                                    Toast.makeText(MainActivity.this, "dobraOdpowiedz", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "ZlaOdpowiedz", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    }
                }
        );

    }

    private void wyswietlPytanie(int i){
        textView.setText(pytania.get(i).getTrescPytania());
        for (int j = 0; j < radioButtons.length; j++) {
            radioButtons[j].setText(pytania.get(i).getOdpowiedzi()[j]);
        }
    }

    private void sprawdz(){}



}