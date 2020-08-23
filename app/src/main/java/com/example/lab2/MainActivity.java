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

    private int numero1;
    private int numero2;
    private int idPregunta;
    private int puntaje;
    private int contador;
    private boolean start = true;


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

        puntaje = 0;
        contador = 0;
        preguntas = new ArrayList<Pregunta>();
        makeQuestion();
        nextBut.setOnClickListener(this);


        new Thread(
                () ->{

                    while(start){

                        contador++;
                        runOnUiThread(() -> timeText.setText(""+contador));
                        runOnUiThread(() -> againButt.setVisibility(View.GONE));

                        if(contador >= 10){
                            start = false;
                        }


                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }
        ).start();

        if(contador >= 30){

            againButt.setVisibility(View.VISIBLE);

        }
    }


    public void makeQuestion () {
        numero1= (int) Math.floor(Math.random()*10 +1);
        numero2= (int) Math.floor(Math.random()*10 +1);
        idPregunta= (int) Math.floor(Math.random()*2 +1);
        preguntas.add(new Pregunta(idPregunta,numero1,numero2));

        for(Pregunta p: preguntas ){


            p.crearPreguntas();
            preguntaText.setText(p.GeneraPregunta());
        }
    }

    @Override
    public void onClick(View view) {

        String answer = respuestaEditText.getText().toString();

        for(Pregunta p: preguntas ) {



            Log.e("mal",p.getRespuesta()+ " ");
            if (answer.equals( Integer.toString(p.getRespuesta()))) {
                califiText.setText("Great");
                //Toast.makeText(this,"Correcto",Toast.LENGTH_LONG).show();
                puntaje +=10;
                makeQuestion();


            } else {

                //Toast.makeText(this,"Mal",Toast.LENGTH_LONG).show();
                califiText.setText("Wrong");
                if (puntaje > 0){

                    puntaje -=10;
                }

            }
        }


        //preguntas.remove(0);
        Log.e("tamamo", " "+  preguntas.size());

        scoreText.setText( " " + puntaje);



    }
}