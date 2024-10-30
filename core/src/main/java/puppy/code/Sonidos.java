package puppy.code;

import java.io.File;

import javax.sound.sampled.*;

public class Sonidos{
	private Clip clip;
	
	public void cargarSonido(String ruta)
	{
		try {
			File rutaArchivo = new File(ruta);
			AudioInputStream sonido = AudioSystem.getAudioInputStream(rutaArchivo);
			clip = AudioSystem.getClip();
			clip.open(sonido);
			
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void reproducirSonido()
	{
		if (clip != null)
		{
			clip.setFramePosition(0);
			clip.start();
		}
	}
	
	
}