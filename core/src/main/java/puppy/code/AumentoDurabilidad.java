package puppy.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AumentoDurabilidad implements CambioBloques{
    @Override
    public void cambioEstado(List <Block> bloques)
    {
        for (int i = 0; i < bloques.size(); i++)
        {
            Random rand = new Random();
            int random = rand.nextInt(bloques.size());
            bloques.get(random).setDurabilidad(bloques.get(random).getDurabilidad() + 1);
        }
    }
}
