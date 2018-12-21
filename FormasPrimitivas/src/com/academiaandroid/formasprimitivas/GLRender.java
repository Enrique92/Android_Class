package com.academiaandroid.formasprimitivas;

//Ejemplo aplicación Android en el que construiremos formas primitivas en 2D, y objetos gráficos en 3D
//con el uso de la API OpenGL ES. Además se implementan las opciones de iluminación, definición de materiales e
//interacción con el usuario.
//
//academiaandroid.com
//
//by José Antonio Gázquez Rodríguez


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;
import android.view.MotionEvent;

//Clase que implementa la interfaz Renderer, encargada de 
//realizar las diferentes llamadas para dibujar la imagen, además de construir la vista
//donde será representada.
public class GLRender implements GLSurfaceView.Renderer{
	
	//Variable para encender o no la iluminación en la escena.
	boolean encenderFoco = true; 
	
	//Variables para controlar el tipo de iluminación y la posición del foco en la escena.
	private float[] luzAmbiente = {0.2f, 0.2f, 0.2f, 1.0f};
	private float[] luzDifusa = {3.0f, 3.0f, 3.0f, 3.0f};
	private float[] luzPosicion = {1.0f, 1.0f, 2.0f, 1.0f};
	
	//Se declaran las diferentes clases, donde se definen los parámetros de construcción de los objetos gráficos.
	private Cuadrado cuadrado;
	private Cubo3D cubo3D;
	private Triangulo triangulo;
	private Piramide3D piramide3D;
	
	//Variable de tipo int que contiene el valor de la forma seleccionada en la Activity principal.
	int flag;
	
	//Variables de tipo float para controlar el ángulo de rotación de las figuras representadas.
	public float anguloX, anguloY, anguloZ;

	//Se declaran e inicializan dos variables de tipo float para establecer los valores del ángulo y velocidad de 
	//giro del elemento representado.
	private static float angulo = 65;
	private static float velocidadGiroEjeX = -1.5f;
	
	//Constructor donde se inicializan las clases de los diferentes elementos a construir.
	//Recibe como parámetro el elemento seleccionado en la Activity principal.
	public GLRender(int flag, Context context)
	{
		this.cuadrado = new Cuadrado();
		this.cubo3D = new Cubo3D();
		this.triangulo = new Triangulo();
		this.piramide3D = new Piramide3D();
		this.flag = flag;
	}
	
	
	//Método que será invocado una vez para configurar la vista.
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
		//Habilitamos la iluminación.
		gl.glEnable(GL10.GL_LIGHTING);
		
		//Activamos la primera luz individualmente.
		gl.glEnable(GL10.GL_LIGHT0);
		
		//Introducimos las características de la luz: luz ambiente, luz difusa y posición.
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, luzAmbiente,0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, luzDifusa,0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, luzPosicion,0);//Si el último parámetro es 1, la luz se sitúa en las
		//coordenadas indicadas en los tres primeros, si es 0 se sitúa en el infinito.
		
		//Por último, especificamos el modo en que OpenGL realizará el sombreado sobre el objeto.
		gl.glShadeModel(GL10.GL_SMOOTH);
	}

	
	//Método que será invocado si la geometría de la 	
	//vista cambia.
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {		
		//Se comprueba que la altura no sea igual a 0.
		if(height == 0) {                       			
			height = 1;                        			
		}
		//Método que fija la vista recibiendo como parámetros la esquina inferior izquierda del rectángulo 
		//de visualización, por defecto (0,0), y los parámetros de ancho y alto.
		gl.glViewport(0, 0, width, height); 
		//Se indica que se trabajará con la matriz PROJECTION.
		gl.glMatrixMode(GL10.GL_PROJECTION);
		//Se carga la identidad de la matriz. 
		gl.glLoadIdentity();		
		//Establece la matriz de proyección en perspectiva.
		GLU.gluPerspective(gl, 55.0f, (float)width / (float)height, 0.1f, 100.0f);			
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity(); 			
	}
	
	//Método que permite construir los diferentes objetos gráficos dentro de la vista definida.
	public void seleccionForma(int flag, GL10 gl)
	{
		//Limpia el buffer para prestablecer los valores de profundidad y color.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		//Al inicializar la variable encenderFoco en true, siempre estará habilitado el foco definido.
		if (encenderFoco) {
	         gl.glEnable(GL10.GL_LIGHTING);
	      } else {
	         gl.glDisable(GL10.GL_LIGHTING);
	      }
        gl.glLoadIdentity();
        //Permite establecer el movimiento de translación indicando las coordenadas del eje X, Y y Z.
        gl.glTranslatef(0.0f, 0.0f, -5.0f); 
        
         
		 if(flag == 0)//Elemento seleccionado en la Activity principal: Cuadrado 2D.
	        {
			 	//Invocamos el método del objeto cuadrado encargado de establecer los parámetros del gráfico a dibujar.
			 	cuadrado.draw(gl);
	        	
	        }else if(flag == 1)//Elemento seleccionado en la Activity principal: Triángulo 2D.
	        {
	        	//Invocamos el método del objeto triangulo encargado de establecer los parámetros del gráfico a dibujar.
	        	triangulo.draw(gl);
	        	
	        }else if (flag == 2)//Elemento seleccionado en la Activity principal: Interacción usuario con objetos 3D.
	        {	
	        	
	        	//Parámetros de rotación, traslación y escalado del objeto cubo.
	        	//Permite establecer el movimiento de translación indicando las coordenadas del eje X, Y y Z.
	        	gl.glTranslatef(-3.0f, 0.0f, -5.0f);
	        	//Permite establecer el tamaño de la figura indicando las coordenadas del eje X, Y y Z.
	        	gl.glScalef(0.8f, 0.8f, 0.8f); 
	        		        	
	        	
	        	//Permite establecer el grado de rotación, indicando además del ángulo con respecto a un punto,
	        	//las coordenadas del eje X, Y y Z.      	            
	            //Rotación del objeto cubo a través del sensor de la pantalla táctil.
	            gl.glRotatef(anguloX, 1.0f, 1.0f, 1.0f);
	            gl.glRotatef(anguloY, 1.0f, 1.0f, 1.0f);
	            gl.glRotatef(anguloZ, 1.0f, 1.0f, 1.0f);       
	            
	            //Invocamos el método del objeto cubo3D encargado de stablecer los parámetros del gráfico a dibujar.
	        	cubo3D.draw(gl);
	        	 
	        	//Parámetros de rotación, traslación y escalado del objeto pirámide.
	            gl.glScalef(0.8f, 0.8f, 0.8f); 
	        	gl.glTranslatef(-3.0f, 0.0f, -5.0f);
	        	//Rotación del objeto pirámide a través del sensor de la pantalla táctil.
	        	gl.glRotatef(anguloX, 1.0f, 1.0f, 1.0f);
	        	gl.glRotatef(anguloY, 1.0f, 1.0f, 1.0f);
	        	gl.glRotatef(anguloZ, 1.0f, 1.0f, 1.0f);
        	
	        	//Invocamos el método del objeto piramide3D encargado de establecer los parámetros del gráfico a dibujar.
	        	piramide3D.draw(gl);
	        	
	        }else if(flag == 3)//Elemento seleccionado en la Activity principal: Pirámide 3D.
	        {
	        	gl.glTranslatef(-1.5f, 0.0f, -6.0f);
	        	//Permite establecer el tamaño de la figura indicando las coordenadas del eje X, Y y Z.
	        	gl.glScalef(0.8f, 0.8f, 0.8f); 
	        	//Permite establecer el grado de rotación, indicando además del ángulo con respecto a un punto,
	        	//las coordenadas del eje X, Y y Z.
	            gl.glRotatef(angulo, 0.1f, 1.0f, -0.1f);
	            //Invocamos el método del objeto piramide3D encargado de establecer los parámetros del gráfico a dibujar.
	            piramide3D.draw(gl);
	        	angulo += velocidadGiroEjeX;
	        }
	}

	//Método que será invocado para cada redibujado de 	la vista. En este ejemplo se realiza la llamada 
	//al método que construirá el objeto.
	@Override
	public void onDrawFrame(GL10 gl) {
		seleccionForma(flag, gl);
	}

	public void modificarAngulo(float anguloX, float anguloY, float anguloZ)
	{
		this.anguloX -= anguloX;
		this.anguloY -= anguloY;
		this.anguloZ -= anguloZ; 
	}
	
	//Método que será invocado por el evento encargado de controlar los cambios que se produzcan en 
	//el sensor de la pantalla táctil. Recibe como argumento la clase MotionEvent.
	public void girarObjetos(MotionEvent event)
	{	
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE://Controla los movimientos por la pantalla.
			anguloX = event.getX();
			anguloY = event.getY();	
			Log.d ("DEBUG","ACTION_MOVE"); 
			break;
			
		case MotionEvent.ACTION_DOWN://Controla la pulsación de la pantalla.
			modificarAngulo(anguloX - event.getX(), anguloY - event.getY(),0.0f);
			anguloX = event.getX();
			anguloY = event.getY();
			Log.d("DEBUG","ACTION_DOWN"); 
			break;
			
		case MotionEvent.ACTION_UP://Controla cuando dejas de pulsar la pantalla.
			modificarAngulo(anguloX + event.getX(), anguloY + event.getY(),0.0f);
			anguloX = event.getX();					
			anguloY = event.getY();
			Log.d("DEBUG","ACTION_UP"); 
			break;

		default:
			break;
		}	
	}
}
