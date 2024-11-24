package puppy.code;

public class BloqueDuradero extends Block
{
    public BloqueDuradero(int x, int y, int ancho, int alto, int durabilidadExtra)
    {
        super(x, y, ancho, alto);
        setDurabilidad(getDurabilidad() + durabilidadExtra);
    }
}
