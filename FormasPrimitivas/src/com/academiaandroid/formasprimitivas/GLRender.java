package com.academiaandroid.formasprimitivas;

//Ejemplo aplicaci�n Android en el que construiremos formas primitivas en 2D, y objetos gr�ficos en 3D
//con el uso de la API OpenGL ES. Adem�s se implementan las opciones de iluminaci�n, definici�n de materiales e
//interacci�n con el usuario.
//
//academiaandroid.com
//
//by Jos� Antonio G�zquez Rodr�guez


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;
import android.view.MotionEvent;

//Clase que implementa la interfaz Renderer, encargada de 
//realizar las diferentes llamadas para dibujar la imagen, adem�s de construir la vista
//donde ser� representada.
public class GLRender implements GLSurfaceView.Renderer{
	
	//Variable para encender o no la iluminaci�n en la escena.
	boolean encenderFoco = true; 
	
	//Variables para controlar el tipo de iluminaci�n y la posici�n del foco en la escena.
	private float[] luzAmbiente = {0.2f, 0.2f, 0.2f, 1.0f};
	private float[] luzDifusa = {3.0f, 3.0f, 3.0f, 3.0f};
	private float[] luzPosicion = {1.0f, 1.0f, 2.0f, 1.0f};
	
	//Se declaran las diferentes clases, donde se definen los par�metros de construcci�n de los objetos gr�ficos.
	private Cuadrado cuadrado;
	private Cubo3D cubo3D;
	private Triangulo triangulo;
	private Piramide3D piramide3D;
	
	//Variable de tipo int que contiene el valor de la forma seleccionada en la Activity principal.
	int flag;
	
	//Variables de tipo float para controlar el �ngulo de rotaci�n de las figuras representadas.
	public float anguloX, anguloY, anguloZ;

	//Se declaran e inicializan dos variables de tipo float para establecer los valores del �ngulo y velocidad de 
	//giro del elemento representado.
	private static float angulo = 65;
	private static float velocidadGiroEjeX = -1.5f;
	
	//Constructor donde se inicializan las clases de los diferentes elementos a construir.
	//Recibe como par�metro el elemento seleccionado en la Activity principal.
	public GLRender(int flag, Context context)
	{
		this.cuadrado = new Cuadrado();
		this.cubo3D = new Cubo3D();
		this.triangulo = new Triangulo();
		this.piramide3D = new Piramide3D();
		this.flag = flag;
	}
	
	
	//M�todo que ser� invocado una vez para configurar la vista.
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
		//Habilitamos la iluminaci�n.
		gl.glEnable(GL10.GL_LIGHTING);
		
		//Activamos la primera luz individualmente.
		gl.glEnable(GL10.GL_LIGHT0);
		
		//Introducimos las caracter�sticas de la luz: luz ambiente, luz difusa y posici�n.
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, luzAmbiente,0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, luzDifusa,0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, luzPosicion,0);//Si el �ltimo par�metro es 1, la luz se sit�a en las
		//coordenadas indicadas en los tres primeros, si es 0 se sit�a en el infinito.
		
		//Por �ltimo, especificamos el modo en que OpenGL realizar� el sombreado sobre el objeto.
		gl.glShadeModel(GL10.GL_SMOOTH);
	}

	
	//M�todo que ser� invocado si la geometr�a de la 	
	//vista cambia.
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {		
		//Se comprueba que la altura no sea igual a 0.
		if(height == 0) {                       			
			height = 1;                        			
		}
		//M�todo que fija la vista recibiendo como par�metros la esquina inferior izquierda del rect�ngulo 
		//de visualizaci�n, por defecto (0,0), y los par�metros de ancho y alto.
		gl.glViewport(0, 0, width, height); 
		//Se indica que se trabajar� con la matriz PROJECTION.
		gl.glMatrixMode(GL10.GL_PROJECTION);
		//Se carga la identidad de la matriz. 
		gl.glLoadIdentity();		
		//Establece la matriz de proyecci�n en perspectiva.
		GLU.gluPerspective(gl, 55.0f, (float)width / (float)height, 0.1f, 100.0f);			
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity(); 			
	}
	
	//M�todo que permite construir los diferentes objetos gr�ficos dentro de la vista definida.
	public void seleccionForma(int flag, GL10 gl)
	{
		//Limpia el buffer para prestablecer los valores de profundidad y color.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		//Al inicializar la variable encenderFoco en true, siempre estar� habilitado el foco definido.
		if (encenderFoco) {
	         gl.glEnable(GL10.GL_LIGHTING);
	      } else {
	         gl.glDisable(GL10.GL_LIGHTING);
	      }
        gl.glLoadIdentity();
        //Permite establecer el movimiento de translaci�n indicando las coordenadas del eje X, Y y Z.
        gl.glTranslatef(0.0f, 0.0f, -5.0f); 
        
         
		 if(flag == 0)//Elemento seleccionado en la Activity principal: Cuadrado 2D.
	        {
			 	//Invocamos el m�todo del objeto cuadrado encargado de establecer los par�metros del gr�fico a dibujar.
			 	cuadrado.draw(gl);
	        	
	        }else if(flag == 1)//Elemento seleccionado en la Activity principal: Tri�ngulo 2D.
	        {
	        	//Invocamos el m�todo del objeto triangulo encargado de establecer los par�metros del gr�fico a dibujar.
	        	triangulo.draw(gl);
	        	
	        }else if (flag == 2)//Elemento seleccionado en la Activity principal: Interacci�n usuario con objetos 3D.
	        {	
	        	
	        	//Par�metros de rotaci�n, traslaci�n y escalado del objeto cubo.
	        	//Permite establecer el movimiento de translaci�n indicando las coordenadas del eje X, Y y Z.
	        	gl.glTranslatef(-3.0f, 0.0f, -5.0f);
	        	//Permite establecer el tama�o de la figura indicando las coordenadas del eje X, Y y Z.
	        	gl.glScalef(0.8f, 0.8f, 0.8f); 
	        		        	
	        	
	        	//Permite establecer el grado de rotaci�n, indicando adem�s del �ngulo con respecto a un punto,
	        	//las coordenadas del eje X, Y y Z.      	            
	            //Rotaci�n del objeto cubo a trav�s del sensor de la pantalla t�ctil.
	            gl.glRotatef(anguloX, 1.0f, 1.0f, 1.0f);
	            gl.glRotatef(anguloY, 1.0f, 1.0f, 1.0f);
	            gl.glRotatef(anguloZ, 1.0f, 1.0f, 1.0f);       
	            
	            //Invocamos el m�todo del objeto cubo3D encargado de stablecer los par�metros del gr�fico a dibujar.
	        	cubo3D.draw(gl);
	        	 
	        	//Par�metros de rotaci�n, traslaci�n y escalado del objeto pir�mide.
	            gl.glScalef(0.8f, 0.8f, 0.8f); 
	        	gl.glTranslatef(-3.0f, 0.0f, -5.0f);
	        	//Rotaci�n del objeto pir�mide a trav�s del sensor de la pantalla t�ctil.
	        	gl.glRotatef(anguloX, 1.0f, 1.0f, 1.0f);
	        	gl.glRotatef(anguloY, 1.0f, 1.0f, 1.0f);
	        	gl.glRotatef(anguloZ, 1.0f, 1.0f, 1.0f);
        	
	        	//Invocamos el m�todo del objeto piramide3D encargado de establecer los par�metros del gr�fico a dibujar.
	        	piramide3D.draw(gl);
	        	
	        }else if(flag == 3)//Elemento seleccionado en la Activity principal: Pir�mide 3D.
	        {
	        	gl.glTranslatef(-1.5f, 0.0f, -6.0f);
	        	//Permite establecer el tama�o de la figura indicando las coordenadas del eje X, Y y Z.
	        	gl.glScalef(0.8f, 0.8f, 0.8f); 
	        	//Permite establecer el grado de rotaci�n, indicando adem�s del �ngulo con respecto a un punto,
	        	//las coordenadas del eje X, Y y Z.
	            gl.glRotatef(angulo, 0.1f, 1.0f, -0.1f);
	            //Invocamos el m�todo del objeto piramide3D encargado de establecer los par�metros del gr�fico a dibujar.
	            piramide3D.draw(gl);
	        	angulo += velocidadGiroEjeX;
	        }
	}

	//M�todo que ser� invocado para cada redibujado de 	la vista. En este ejemplo se realiza la llamada 
	//al m�todo que construir� el objeto.
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
	
	//M�todo que ser� invocado por el evento encargado de controlar los cambios que se produzcan en 
	//el sensor de la pantalla t�ctil. Recibe como argumento la clase MotionEvent.
	public void girarObjetos(MotionEvent event)
	{	
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE://Controla los movimientos por la pantalla.
			anguloX = event.getX();
			anguloY = event.getY();	
			Log.d ("DEBUG","ACTION_MOVE"); 
			break;
			
		case MotionEvent.ACTION_DOWN://Controla la pulsaci�n de la pantalla.
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
