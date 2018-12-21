package com.academiaandroid.formasprimitivas;

//Ejemplo aplicación Android en el que construiremos formas primitivas en 2D, y objetos gráficos en 3D
//con el uso de la API OpenGL ES. Además se implementan las opciones de iluminación, definición de materiales e
//interacción con el usuario.
//
//academiaandroid.com
//
//by José Antonio Gázquez Rodríguez


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity{

	//Se declara la clase encargada de proporcionar la vista o view dónde se construirán los gráficos.
	private GLSurfaceView view;
	
	//Se definen los elementos necesarios para la selección de la forma a pintar.
	private RadioGroup radioGroupFormas, radioGroupFormas3D;
	private RadioButton radioCuadrado, radioTriangulo,radioCubo3D, radioPiramide3D;
	
	//Variable de tipo int para controlar la forma seleccionada.
	int flag;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        //Enlazamos los elementos con sus recursos a nivel de layout.
        radioGroupFormas = (RadioGroup)findViewById(R.id.radioGroupFormas);
        radioCuadrado = (RadioButton)findViewById(R.id.radioCuadrado);
        radioTriangulo = (RadioButton)findViewById(R.id.radioTriangulo);        
        radioGroupFormas3D = (RadioGroup)findViewById(R.id.radioGroupFormas3D);
        radioCubo3D = (RadioButton)findViewById(R.id.radioCubo3D);
        radioPiramide3D = (RadioButton)findViewById(R.id.radioPiramide3D);
        
        //Se especifica que ningún elemento esté preseleccionado.
        radioGroupFormas.clearCheck();            
        
        //Se define el evento encargado de cazar el cambio de estado del componente RadioGroup, 
        //controlando que RadioButton ha sido seleccionado.
        radioGroupFormas.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(radioCuadrado.isChecked())
				{
					flag = 0;					
				}else if(radioTriangulo.isChecked())
				{
					flag = 1;				
				}
				//Se instancia la clase GLSurfaceView, recibiendo el contexto de la aplicación como parámetro.
				view = new GLSurfaceView(getApplicationContext());
				
				//El objeto GLSurfaceView invoca al método setRenderer, encargado de iniciar la construcción
				//del objeto gráfico.
				view.setRenderer(new GLRender(flag,getApplicationContext()));
				setContentView(view);
			}
		});
        
        //Se especifica que ningún elemento esté preseleccionado.
        radioGroupFormas3D.clearCheck();
        
        //Se define el evento encargado de cazar el cambio de estado del componente RadioGroup, 
        //controlando que RadioButton ha sido seleccionado.
        radioGroupFormas3D.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(radioCubo3D.isChecked())
				{
					flag = 2;					
				}else if(radioPiramide3D.isChecked())
				{
					flag = 3;					
				}
				//Se instancia la clase GLSurfaceView, recibiendo el contexto de la aplicación como parámetro.
				view = new HiloEventosUsuario(flag,getApplicationContext());				
				setContentView(view);
			}
		});
    }
}
