package com.academiaandroid.formasprimitivas;

//Ejemplo aplicaci�n Android en el que construiremos formas primitivas en 2D, y objetos gr�ficos en 3D
//con el uso de la API OpenGL ES. Adem�s se implementan las opciones de iluminaci�n, definici�n de materiales e
//interacci�n con el usuario.
//
//academiaandroid.com
//
//by Jos� Antonio G�zquez Rodr�guez


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity{

	//Se declara la clase encargada de proporcionar la vista o view d�nde se construir�n los gr�ficos.
	private GLSurfaceView view;
	
	//Se definen los elementos necesarios para la selecci�n de la forma a pintar.
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
        
        //Se especifica que ning�n elemento est� preseleccionado.
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
				//Se instancia la clase GLSurfaceView, recibiendo el contexto de la aplicaci�n como par�metro.
				view = new GLSurfaceView(getApplicationContext());
				
				//El objeto GLSurfaceView invoca al m�todo setRenderer, encargado de iniciar la construcci�n
				//del objeto gr�fico.
				view.setRenderer(new GLRender(flag,getApplicationContext()));
				setContentView(view);
			}
		});
        
        //Se especifica que ning�n elemento est� preseleccionado.
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
				//Se instancia la clase GLSurfaceView, recibiendo el contexto de la aplicaci�n como par�metro.
				view = new HiloEventosUsuario(flag,getApplicationContext());				
				setContentView(view);
			}
		});
    }
}
