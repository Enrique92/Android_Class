package com.academiaandroid.formasprimitivas;

//Ejemplo aplicación Android en el que construiremos formas primitivas en 2D, y objetos gráficos en 3D
//con el uso de la API OpenGL ES. Además se implementan las opciones de iluminación, definición de materiales e
//interacción con el usuario.
//
//academiaandroid.com
//
//by José Antonio Gázquez Rodríguez


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Piramide3D {
	
	//Declaramos un buffer de tipo float para un array de vértices
	private FloatBuffer verticeBuffer;
	//Declaramos un buffer de tipo float para un array de colores.
	private FloatBuffer colorBuffer; 
	//Declaramos un buffer de tipo float para un array de índices
	private ByteBuffer indexBuffer;  
	
	//Variables para controlar el tipo de iluminación del foco en la escena.
	private float[] luzAmbiente = {0.5f, 0.5f, 0.5f, 1.0f};
	private float[] luzDifusa = {3.0f, 3.0f, 3.0f, 3.0f};
	
	//Array de tipo float donde se definen las coordenadas de cada uno de los vértices
	//que forman las diferentes caras de la pirámide.
	private float[] vertices = {
			  -1.0f, -1.0f, -1.0f,  // 0. izquierda-abajo-atrás
		       1.0f, -1.0f, -1.0f,  // 1. derecha-abajo-atrás
		       1.0f, -1.0f,  1.0f,  // 2. derecha-abajo-frontal
		      -1.0f, -1.0f,  1.0f,  // 3. izquierda-abajo-frontal
		       0.0f,  1.0f,  0.0f   // 4. superior
	};
	
	// Array de tipo float donde se definen los colores de las 5 caras de la pirámide. 
	//Formato de colores RGBA--> Rojo(R), Verde(V), Azul(B) y grado de transparencia u opacidad (Alpha).
	private float[] colores = { 
		      0.0f, 0.0f, 1.0f, 1.0f,  // 0. Azul
		      0.0f, 1.0f, 0.0f, 1.0f,  // 1. Verde
		      0.0f, 0.0f, 1.0f, 1.0f,  // 2. Azul
		      0.0f, 1.0f, 0.0f, 1.0f,  // 3. Verde
		      1.0f, 0.0f, 0.0f, 1.0f   // 4. Rojo
		   };
	
	//Array de tipo float con los índices de los vértices de las cinco caras de la pirámide.	  
	private byte[] indices = {
	      2, 4, 3,   // Coordenadas cara delantera
	      1, 4, 2,   // Coordenadas cara derecha
	      0, 4, 1,   // Coordenadas cara trasera
	      4, 0, 3    // Coordenadas cara izquierda
	};
		   
	//Se define el constructor, dónde se inicializa la clase ByteBuffer, al que se le asigna un espacio
	//en memoria de 4 bytes por float.	   
	public Piramide3D()
    {
		ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*4);
		buffer.order(ByteOrder.nativeOrder());	
		verticeBuffer = buffer.asFloatBuffer();
		verticeBuffer.put(vertices);
		verticeBuffer.position(0);
		
		
		  //Se almacenan los valores de los diferentes colores en el buffer.
	      ByteBuffer cbb = ByteBuffer.allocateDirect(colores.length * 4);
	      cbb.order(ByteOrder.nativeOrder());
	      colorBuffer = cbb.asFloatBuffer();
	      colorBuffer.put(colores);
	      colorBuffer.position(0);
	  
	      //Se almacenan los valores de los diferentes indices en el buffer.
	      indexBuffer = ByteBuffer.allocateDirect(indices.length);
	      indexBuffer.put(indices);
	      indexBuffer.position(0);
    }
	
	
	   //Método donde se definen los parámetros para pintar la pirámide. 
	   public void draw(GL10 gl) {
		  //Define el polígono frontal.
	      gl.glFrontFace(GL10.GL_CCW);	  
	      //Habilita los arrays y define los buffers.
	      gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	      gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticeBuffer);
	      
	      //Se habilita la definición de materiales.
	      gl.glEnable(GL10.GL_COLOR_MATERIAL);
	      //Posteriormente se definen las propiedades de los materiales.
		  gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_DIFFUSE, luzDifusa,0);
		  gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_AMBIENT, luzAmbiente,0);
	      
	      gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
	      gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);	      
	      gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE,indexBuffer);	
	 
	      gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	      gl.glDisableClientState(GL10.GL_COLOR_ARRAY);	      
	   }
	
}
