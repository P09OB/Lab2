package com.example.lab2;

public class Pregunta {

    private int generarPregunta;
  private String pregunta;
  private int respuesta;
  private int numero1;
  private int numero2;



    public Pregunta(int generarPregunta, int numero1, int numero2) {

        this.generarPregunta= generarPregunta;
        this.numero1 = numero1;
        this.numero2=numero2;

    }



    public void crearPreguntas(){

        switch (generarPregunta){
                   //SUMA
            case 0: pregunta = numero1 + " "+ "+" +" "+ numero2;

                    respuesta = numero1 + numero2;
                break;
                // RESTA
            case 1: pregunta = numero1 + " "+ "-" +" "+ numero2;

                respuesta = numero1 - numero2;


                break;

                //MULTIPLICACIÓN

            case 2: pregunta = numero1 + " "+ "X" +" "+ numero2;

                respuesta = numero1 * numero2;



                break;

                //DIVISIÓN

            case 3:

                if(numero2 >= 1) {


                    pregunta = numero1 + " " + "÷" + " " + numero2;

                    respuesta = numero1 / numero2;

                }
                break;

        }

    }


    public String GeneraPregunta (){

        return pregunta ;



    }

    public String getPregunta() {
        return pregunta;
    }

    public int getRespuesta() {
        return respuesta;
    }
}
