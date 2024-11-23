package puppy.code;

import java.util.List;

public class ContextCamBlock {
    private CambioBloques cambioBloques;

    public ContextCamBlock(CambioBloques cambioBloques) {
        this.cambioBloques = cambioBloques;
    }
    public void cambiar(CambioBloques cambioBloques) {
        this.cambioBloques = cambioBloques;
    }
    public void ejecutarCambioEstado(List<Block> blocks) {
        cambioBloques.cambioEstado(blocks);
    }
}
