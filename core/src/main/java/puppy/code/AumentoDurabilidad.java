package puppy.code;

import java.util.ArrayList;
import java.util.List;

public class AumentoDurabilidad implements CambioBloques{
    @Override
    public void cambioEstado(List <Block> bloques)
    {
        for (int i = 0; i < bloques.size(); i++)
        {
            bloques.get(i).setDurabilidad(bloques.get(i).getDurabilidad() + 1);
        }
    }
}

