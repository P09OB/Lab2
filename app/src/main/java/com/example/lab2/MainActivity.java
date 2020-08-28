package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button nextBut;
    private TextView preguntaText;
    private EditText respuestaEditText;
    private TextView scoreText;
    private TextView timeText;
    private TextView califiText;
    private Button againButt;
    private TextView wrongText;
    private TextView greatText;

    private int numero1;
    private int numero2;
    private int idPregunta;
    private int puntaje;
    private int contador;
    private int greatScore;
    private int wrongScore;
    private boolean start = true;
    private boolean restar = false;
    private boolean sumar = false;


    ArrayList<Pregunta> preguntas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextBut = findViewById(R.id.nextBut);
        preguntaText = findViewById(R.id.preguntaText);
        respuestaEditText = findViewById(R.id.respuestaEditText);
        scoreText = findViewById(R.id.scoreText);
        timeText = findViewById(R.id.timeText);
        califiText = findViewById(R.id.califiText);
        againButt = findViewById(R.id.againButt);
        wrongText = findViewById(R.id.wrongText);
        greatText = findViewById(R.id.greatText);


        puntaje = 0;
        contador = 30;
        preguntas = new ArrayList<Pregunta>();
        makeQuestion();
        nextBut.setOnClickListener(this);
        againButt.setOnClickListener(this);
        tryAgain();

    }


    public void makeQuestion () {
        numero1= (int) (Math.random()*10 +1);
        numero2= (int) (Math.random()*10 +1);
        idPregunta= (int) (Math.random()*4);

        preguntas.add(new Pregunta(idPregunta,numero1,numero2));

        for(Pregunta p: preguntas ){

            p.crearPreguntas();
            preguntaText.setText(p.GeneraPregunta());
            Log.e("mal",""+p.GeneraPregunta());

        }
    }

    public void tryAgain() {

        new Thread(
                () ->{

                    while(start){

                        contador--;


                        runOnUiThread(() -> timeText.setText(""+contador));
                        runOnUiThread(() -> againButt.setVisibility(View.GONE));
                        runOnUiThread(() -> respuestaEditText.setVisibility(View.VISIBLE));
                        runOnUiThread(() -> nextBut.setVisibility(View.VISIBLE));
                        runOnUiThread(() -> preguntaText.setVisibility(View.VISIBLE));

                        if(contador <= 0){
                            start = false;
                            
                            runOnUiThread(() -> againButt.setVisibility(View.VISIBLE));
                            runOnUiThread(() -> respuestaEditText.setVisibility(View.GONE));
                            runOnUiThread(() -> nextBut.setVisibility(View.GONE));
                            runOnUiThread(() -> preguntaText.setVisibility(View.GONE));
                            runOnUiThread(() -> califiText.setText("TIME OUT"));


                        }


                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }
        ).start();


    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){

            case R.id.nextBut:

                String answer = respuestaEditText.getText().toString();

                for(Pregunta p: preguntas ) {


                    Log.e("mal",p.getRespuesta()+ " ");
                    if (answer.equals( Integer.toString(p.getRespuesta()))) {
                        sumar = true;
                        restar = false;


                        makeQuestion();


                    } else{
                        restar = true;
                        sumar = false;






                    }
                }

                if(sumar == true){
                    califiText.setText("Great");
                    puntaje +=10;
                    greatScore +=1;
                    greatText.setText(""+greatScore);

                }

                if (restar == true){

                    califiText.setText("Wrong");
                    wrongScore +=1;
                    wrongText.setText(""+wrongScore);
                    if (puntaje > 0){

                        puntaje -=10;
                    }

                }

                scoreText.setText( " " + puntaje);


                break;


            case R.id.againButt:


                califiText.setText(" ");
                contador=30;
                start = true;


                tryAgain();

                break;
        }





    }
}