package puppy.code;

import java.util.Random;

public class FabricaBloquesAleatorios implements FabricaBloques
{
    private Random generadorNumeroAleatorio;

    public Block crearBloque(int x, int y, int ancho, int alto)
    {
        if (generadorNumeroAleatorio == null)
        {
            generadorNumeroAleatorio = new Random();
        }
        int numeroAleatorio = generadorNumeroAleatorio.nextInt(2);
        switch (numeroAleatorio)
        {
            case 0:
                return new BloqueSimple(x, y, ancho, alto);
            case 1:
                return new BloqueDuradero(x, y, ancho, alto,generadorNumeroAleatorio.nextInt(10));
            default:
                return new BloqueSimple(x, y, ancho, alto);
        }
    }
}
