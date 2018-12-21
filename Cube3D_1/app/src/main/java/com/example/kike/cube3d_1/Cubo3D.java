package com.example.kike.cube3d_1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

//Clase donde se establecen los par�metros para construir un cubo en 3 dimensiones.
public class Cubo3D {

    //Se declara e inicializa una variable de tipo int, para posteriormente recorrer el n�mero de caras
    //asign�ndole los colores definidos.
    int carasCubo = 6;
    //Declaramos un buffer de tipo float.
    private FloatBuffer fBuffer;

    //Array de tipo float donde se definen las coordenadas de cada uno de los v�rtices
    //que forman las diferentes caras del cubo. Un cubo se compone de 6 caras, y cada cara presenta 4 v�rtices
    //con las coordenadas X, Y y Z.
    private float[] vertices = {
            //Coordenadas cara delantera cubo1
            -1.0f, -1.0f, 1.0f,  // 0. izquierda-abajo-frontal
            1.0f, -1.0f, 1.0f,  // 1. derecha-abajo-frontal
            -1.0f, 1.0f, 1.0f,  // 2. izquierda-arriba-frontal
            1.0f, 1.0f, 1.0f,  // 3. derecha-arriba-frontal

            //Coordenadas cara delantera cubo2
            1.0f, -1.0f, 1.0f,  // 0. izquierda-abajo-frontal
            3.0f, -1.0f, 1.0f,  // 1. derecha-abajo-frontal
            1.0f, 1.0f, 1.0f,  // 2. izquierda-arriba-frontal
            3.0f, 1.0f, 1.0f,  // 3. derecha-arriba-frontal

            //Coordenadas cara delantera cubo3
            3.0f, -1.0f, 1.0f,  // 0. izquierda-abajo-frontal
            5.0f, -1.0f, 1.0f,  // 1. derecha-abajo-frontal
            3.0f, 1.0f, 1.0f,  // 2. izquierda-arriba-frontal
            5.0f, 1.0f, 1.0f,  // 3. derecha-arriba-frontal

            //Coordenadas cara delantera cubo4
            -1.0f, 1.0f, 1.0f,  // 0. izquierda-abajo-frontal
            1.0f, 1.0f, 1.0f,  // 1. derecha-abajo-frontal
            -1.0f, 3.0f, 1.0f,  // 2. izquierda-arriba-frontal
            1.0f, 3.0f, 1.0f,  // 3. derecha-arriba-frontal

            //Coordenadas cara delantera cubo5
            1.0f, 1.0f, 1.0f,  // 0. izquierda-abajo-frontal
            3.0f, 1.0f, 1.0f,  // 1. derecha-abajo-frontal
            1.0f, 3.0f, 1.0f,  // 2. izquierda-arriba-frontal
            3.0f, 3.0f, 1.0f,  // 3. derecha-arriba-frontal

            //Coordenadas cara delantera cubo6
            3.0f, 1.0f, 1.0f,  // 0. izquierda-abajo-frontal
            5.0f, 1.0f, 1.0f,  // 1. derecha-abajo-frontal
            3.0f, 3.0f, 1.0f,  // 2. izquierda-arriba-frontal
            5.0f, 3.0f, 1.0f,  // 3. derecha-arriba-frontal

            //Coordenadas cara delantera cubo7
            -1.0f, 3.0f, 1.0f,  // 0. izquierda-abajo-frontal
            1.0f, 3.0f, 1.0f,  // 1. derecha-abajo-frontal
            -1.0f, 5.0f, 1.0f,  // 2. izquierda-arriba-frontal
            1.0f, 5.0f, 1.0f,  // 3. derecha-arriba-frontal

            //Coordenadas cara delantera cubo8
            1.0f, 3.0f, 1.0f,  // 0. izquierda-abajo-frontal
            3.0f, 3.0f, 1.0f,  // 1. derecha-abajo-frontal
            1.0f, 5.0f, 1.0f,  // 2. izquierda-arriba-frontal
            3.0f, 5.0f, 1.0f,  // 3. derecha-arriba-frontal

            //Coordenadas cara delantera cubo9
            3.0f, 3.0f, 1.0f,  // 0. izquierda-abajo-frontal
            5.0f, 3.0f, 1.0f,  // 1. derecha-abajo-frontal
            3.0f, 5.0f, 1.0f,  // 2. izquierda-arriba-frontal
            5.0f, 5.0f, 1.0f,  // 3. derecha-arriba-frontal

            //Coordenadas cara trasera cubo1
            1.0f, -1.0f, -1.0f,  // 6. derecha-abajo-atr�s
            -1.0f, -1.0f, -1.0f,  // 4. izquierda-abajo-atr�s
            1.0f,  1.0f, -1.0f,  // 7. derecha-superior-atr�s
            -1.0f,  1.0f, -1.0f,  // 5. izquierda-superior-atr�s

            //Coordenadas cara trasera cubo2
            1.0f, -1.0f, -1.0f,  // 6. derecha-abajo-atr�s
            -1.0f, -1.0f, -1.0f,  // 4. izquierda-abajo-atr�s
            1.0f,  1.0f, -1.0f,  // 7. derecha-superior-atr�s
            -1.0f,  1.0f, -1.0f,  // 5. izquierda-superior-atr�s

            //Coordenadas cara trasera cubo3
            1.0f, -1.0f, -1.0f,  // 6. derecha-abajo-atr�s
            -1.0f, -1.0f, -1.0f,  // 4. izquierda-abajo-atr�s
            1.0f,  1.0f, -1.0f,  // 7. derecha-superior-atr�s
            -1.0f,  1.0f, -1.0f,  // 5. izquierda-superior-atr�s

            /**
                //Coordenadas cara trasera
                1.0f, -1.0f, -1.0f,  // 6. derecha-abajo-atr�s
                -1.0f, -1.0f, -1.0f,  // 4. izquierda-abajo-atr�s
                1.0f,  1.0f, -1.0f,  // 7. derecha-superior-atr�s
                -1.0f,  1.0f, -1.0f,  // 5. izquierda-superior-atr�s

                //Coordenadas cara izquierda
                -1.0f, -1.0f, -1.0f,  // 4. izquierda-abajo-atr�s
                -1.0f, -1.0f,  1.0f,  // 0. izquierda-abajo-frontal
                -1.0f,  1.0f, -1.0f,  // 5. izquierda-superior-atr�s
                -1.0f,  1.0f,  1.0f,  // 2. izquierda-superior-frontal

                //Coordenadas cara derecha
                1.0f, -1.0f,  1.0f,  // 1. derecha-abajo-frontal
                1.0f, -1.0f, -1.0f,  // 6. derecha-abajo-atr�s
                1.0f,  1.0f,  1.0f,  // 3. derecha-superior-frontal
                1.0f,  1.0f, -1.0f,  // 7. derecha-superior-atr�s

                //Coordenadas cara  superior
                -1.0f,  1.0f,  1.0f,  // 2. izquierda-superior-frontal
                1.0f,  1.0f,  1.0f,  // 3. derecha-superior-frontal
                -1.0f,  1.0f, -1.0f,  // 5. izquierda-superior-atr�s
                1.0f,  1.0f, -1.0f,  // 7. derecha-superior-atr�s

                //Coordenadas cara inferior
                -1.0f, -1.0f, -1.0f,  // 4. izquierda-abajo-atr�s
                1.0f, -1.0f, -1.0f,  // 6. derecha-abajo-atr�s
                -1.0f, -1.0f,  1.0f,  // 0. izquierda-abajo-frontal
                1.0f, -1.0f,  1.0f   // 1. derecha-abajo-frontal
             **/
    };

    // Array de tipo float donde se definen los colores de las 6 caras del cubo.
    //Formato de colores RGBA--> Rojo(R), Verde(V), Azul(B) y grado de transparencia u opacidad (Alpha).
    private float[][] colores = {
            {1.0f, 0.0f, 0.0f, 1.0f},  //Color Rojo
            {0.0f, 1.0f, 0.0f, 1.0f},  //Color Verde
            {0.0f, 0.0f, 1.0f, 1.0f},  //Color Azul
            {1.0f, 1.0f, 0.0f, 1.0f},  //Color Amarillo
            {1.0f, 0.5f, 0.0f, 1.0f},  //Color Naranja
            {1.0f, 1.0f, 1.0f, 0.0f}   //Color Blanco
    };

    //Se define el constructor, d�nde se inicializa la clase ByteBuffer, al que se le asigna un espacio
    //en memoria de 4 bytes por float.
    public Cubo3D() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length * 4);
        buffer.order(ByteOrder.nativeOrder());
        //Conversion a byte de los tipos float.
        fBuffer = buffer.asFloatBuffer();
        //Se copian los datos dentro del buffer.
        fBuffer.put(vertices);
        fBuffer.position(0);
    }

    //Metodo donde se definen los parametros para pintar el cubo.
    public void draw(GL10 gl) {
        //Define los pol�gonos frontal y trasero enfrentados.
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fBuffer);
        //Se habilita la definici�n de materiales.
        gl.glEnable(GL10.GL_COLOR_MATERIAL);

        //Recorremos el numero de caras, para asignarle el color a cada una de ellas.
        for (int cara = 0; cara < carasCubo; cara++) {
            gl.glColor4f(colores[cara][0], colores[cara][1], colores[cara][2], colores[cara][3]);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, cara * 4, 4);
        }
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
