package puppy.code;

public abstract class Nivel
{
    private boolean nivelIniciado = false;

    public final void ejecutarNivel()
    {
        if (!nivelIniciado)
        {
            inicializarNivel();
            nivelIniciado = true;
        }
        if (!nivelFinalizado())
        {
            revisarEntradas();
            actualizarElementos();
            renderizarElementos();
        }
        else
        {
            finalizarNivel();
            nivelIniciado = false;
        }
    }

    protected abstract void inicializarNivel();
    protected abstract boolean nivelFinalizado();
    protected abstract void revisarEntradas();
    protected abstract void actualizarElementos();
    protected abstract void renderizarElementos();
    protected abstract void finalizarNivel();
    public abstract void dispose();
}
