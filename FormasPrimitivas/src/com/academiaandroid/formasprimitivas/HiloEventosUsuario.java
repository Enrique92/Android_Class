package com.academiaandroid.formasprimitivas;

//Ejemplo aplicación Android en el que construiremos formas primitivas en 2D, y objetos gráficos en 3D
//con el uso de la API OpenGL ES. Además se implementan las opciones de iluminación, definición de materiales e
//interacción con el usuario.
//
//academiaandroid.com
//
//by José Antonio Gázquez Rodríguez

import android.content.Context;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

//Clase que hereda de la clase base GLSurfaceView, desde la que controlaremos los cambios que
//se realicen sobre la pantalla táctil.
public class HiloEventosUsuario extends GLSurfaceView{
	
	GLRender render;
	SensorManager sensorManager;
	int flag;

	public HiloEventosUsuario(int flag, Context context) {
		super(context);
		render = new GLRender(flag,context);
		setRenderer(render);
	}
	
	//Evento encargado de controlar las acciones que se realicen al tocar la pantalla,
	//encargado de lanzar un nuevo hilo para invocar al método girarObjetos de la clase GLRender.
	@Override
	public boolean onTouchEvent(final MotionEvent event) {
		if( event != null ){	
		queueEvent(new Runnable() {
			
			@Override
			public void run() {
					render.girarObjetos(event);					
				}
		});
		return true;
		}
	return super.onTouchEvent( event );
	}
}
