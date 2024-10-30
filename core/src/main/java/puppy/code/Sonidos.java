package puppy.code;

import javax.sound.sampled.*;
import java.io.InputStream;

public class Sonidos {
    private Clip clip;

    public void cargarSonido(String ruta) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream(ruta);
            if (audioSrc == null) {
                throw new Exception("Archivo de audio no encontrado: " + ruta);
            }
            AudioInputStream sonido = AudioSystem.getAudioInputStream(audioSrc);
            clip = AudioSystem.getClip();
            clip.open(sonido);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reproducirSonido() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
