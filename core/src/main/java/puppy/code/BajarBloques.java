package puppy.code;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BajarBloques implements CambioBloques {

    private Timer tiempo;
    private boolean tareaProgramada = false;
    private TimerTask tarea;

    public BajarBloques() {
        tiempo = new Timer();

    }

    @Override
    public void cambioEstado(List <Block> bloques) {
        if (tareaProgramada == false) {
            tarea = new TimerTask() {
                @Override
                public void run() {
                    for (int i = 0; i < bloques.size(); i++) {
                        bloques.get(i).setY(bloques.get(i).getY() - 30);
                    }
                }
            };

            tiempo.scheduleAtFixedRate(tarea,30000, 10000);
            tareaProgramada = true;
        }
    }
    public void reiniciar(List <Block> bloques) {
        if (tareaProgramada == true) {
            tarea.cancel();
            tareaProgramada = false;
        }
        tiempo = new Timer();
        cambioEstado(bloques);
    }
}
