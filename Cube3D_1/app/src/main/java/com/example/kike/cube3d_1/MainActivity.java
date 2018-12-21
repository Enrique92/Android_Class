package com.example.kike.cube3d_1;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.RadioButton;

public class MainActivity extends Activity {
    //Se declara la clase encargada de proporcionar la vista o view d�nde se construir�n los gr�ficos.
    private GLSurfaceView view;
    //Se definen los elementos necesarios para la selecci�n de la forma a pintar.
    private RadioButton radioCubo3D;
    //Variable de tipo int para controlar la forma seleccionada.
    int flag = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Se instancia la clase GLSurfaceView, recibiendo el contexto de la aplicación como parámetro.
        view = new GLSurfaceView(getApplicationContext());
        //El objeto GLSurfaceView invoca al método setRenderer, encargado de iniciar la construcción
        //del objeto gráfico.
        view.setRenderer(new GLRender(flag, getApplicationContext()));
        setContentView(view);

        //Se define el evento encargado de cazar el cambio de estado del componente RadioGroup,
        //controlando que RadioButton ha sido seleccionado.
        //Se instancia la clase GLSurfaceView, recibiendo el contexto de la aplicaci�n como par�metro.
        view = new HiloEventosUsuario(flag, getApplicationContext());
        setContentView(view);
    }
}
