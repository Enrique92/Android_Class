package com.academiaandroid.formasprimitivas;

//Ejemplo aplicaci�n Android en el que construiremos formas primitivas en 2D, y objetos gr�ficos en 3D
//con el uso de la API OpenGL ES. Adem�s se implementan las opciones de iluminaci�n, definici�n de materiales e
//interacci�n con el usuario.
//
//academiaandroid.com
//
//by Jos� Antonio G�zquez Rodr�guez

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

//Clase donde se establecen los par�metros para construir un cuadrado de 2 dimensiones.
public class Cuadrado{
	
	//Declaramos un buffer de tipo float.
	private FloatBuffer fBuffer;
	//Array de tipo float donde se definen las coordenadas de cada uno de los v�rtices
	//que forman la cara del cuadrado.
	private float[] vertices = {
			-1.0f,1.0f,0.0f,
			-1.0f,-1.0f,0.0f,
			1.0f,1.0f,0.0f,
			1.0f,-1.0f,0.0f
	};
	
	//Se define el constructor, d�nde se inicializa la clase ByteBuffer, al que se le asigna un espacio
	//en memoria de 4 bytes por float.
	public Cuadrado()
	{
		ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*4);
		buffer.order(ByteOrder.nativeOrder());
		//Conversi�n a byte de los tipos float.
		fBuffer = buffer.asFloatBuffer();
		//Se copian los datos dentro del buffer.
		fBuffer.put(vertices);
		fBuffer.position(0);
	}
	
	//M�todo donde se definen los par�metros para pintar el cubo. 
	public void draw(GL10 gl) {
		    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		    gl.glColor4f(0.0f, 1.0f, 1.0f, 0.5f);
		    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fBuffer);
		    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
		    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		}
}
