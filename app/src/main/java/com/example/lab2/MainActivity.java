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
    private int numero1;
    private int numero2;
    private int idPregunta;
    private int puntaje;


    ArrayList<Pregunta> preguntas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextBut = findViewById(R.id.nextBut);
        preguntaText = findViewById(R.id.preguntaText);
        respuestaEditText = findViewById(R.id.respuestaEditText);
        scoreText = findViewById(R.id.scoreText);

        puntaje = 0;
        preguntas = new ArrayList<Pregunta>();
        numero1= (int) Math.floor(Math.random()*10 +1);
        numero2= (int) Math.floor(Math.random()*10 +1);
        idPregunta= (int) Math.floor(Math.random()*2 +1);


        preguntas.add(new Pregunta(idPregunta,numero1,numero2));

        for(Pregunta p: preguntas ){
            p.crearPreguntas();
            preguntaText.setText(p.GeneraPregunta());
        }

        nextBut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String answer = respuestaEditText.getText().toString();

        for(Pregunta p: preguntas ) {

            Log.e("mal",p.getRespuesta()+ " ");
            if (answer.equals( Integer.toString(p.getRespuesta()))) {

                Toast.makeText(this,"Correcto",Toast.LENGTH_LONG).show();
                puntaje +=10;
                //preguntas.add(new Pregunta(idPregunta,numero1,numero2));


            } else {

                Toast.makeText(this,"Mal",Toast.LENGTH_LONG).show();
                puntaje -=10;

            }
        }

        scoreText.setText( " " + puntaje);



    }
}