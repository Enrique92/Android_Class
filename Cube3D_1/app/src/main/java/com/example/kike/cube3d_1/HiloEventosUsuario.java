package com.example.kike.cube3d_1;

import android.content.Context;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

//Clase que hereda de la clase base GLSurfaceView, desde la que controlaremos los cambios que
//se realicen sobre la pantalla t�ctil.
public class HiloEventosUsuario extends GLSurfaceView {

    GLRender render;
    SensorManager sensorManager;
    int flag;

    public HiloEventosUsuario(int flag, Context context) {
        super(context);
        render = new GLRender(flag, context);
        setRenderer(render);
    }

    //Evento encargado de controlar las acciones que se realicen al tocar la pantalla,
    //encargado de lanzar un nuevo hilo para invocar al m�todo girarObjetos de la clase GLRender.
    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (event != null) {
            queueEvent(new Runnable() {

                @Override
                public void run() {
                    render.girarObjetos(event);
                }
            });
            return true;
        }
        return super.onTouchEvent(event);
    }
}
