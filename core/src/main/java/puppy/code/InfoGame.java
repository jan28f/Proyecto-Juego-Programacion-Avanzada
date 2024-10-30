package puppy.code;

/**
 * Clase InfoGame, encargada de representar la informacion de la partida.
 */
public class InfoGame
{
    /**
     * Variables de clase.
     */
    private static InfoGame instancia;
    private int puntaje;
    private int mejorPuntaje;
    private int vidas;
    private int nivel;

    /**
     * Constructor privado de la clase.
     */
    private InfoGame()
    {
        puntaje = 0;
        mejorPuntaje = 0;
        vidas = 3;
        nivel = 1;
    }

    /**
     * Consigue la instacia de clase InfoGame.
     * @return Instancia de InfoGame.
     */
    public static InfoGame getIntancia()
    {
        if (instancia == null)
        {
            instancia = new InfoGame();
        }
        return instancia;
    }

    // Setters
    /**
     * Establece un nuevo puntaje.
     * @param puntaje Un numero entero, representa el nuevo puntaje.
     */
    public void setPuntaje(int puntaje)
    {
        this.puntaje = puntaje;
    }

    /**
     * Establece el nuevo mejor puntaje.
     * @param mejorPuntaje Un numero entero, representa el nuevo mejor puntaje.
     */
    public void setMejorPuntaje(int mejorPuntaje)
    {
        this.mejorPuntaje = mejorPuntaje;
    }

    /**
     * Establece la nueva cantidad de vidas.
     * @param vidas Un numero entero, representa la nueva cantidad de vidas.
     */
    public void setVidas(int vidas)
    {
        this.vidas = vidas;
    }

    /**
     * Establece el nuevo nivel.
     * @param nivel Un numero entero, representa el nuevo nivel.
     */
    public void setNivel(int nivel)
    {
        this.nivel = nivel;
    }

    // Getters

    /**
     * Consigue el puntaje actual.
     * @return Un numero entero, representa el puntaje actual.
     */
    public int getPuntaje()
    {
        return puntaje;
    }

    /**
     * Consigue el mejor puntaje actual.
     * @return Un numero entero, representa el mejor puntaje actual.
     */
    public int getMejorPuntaje()
    {
        return mejorPuntaje;
    }

    /**
     * Consigue la cantidad de vidas actuales.
     * @return Un numero entero, representa las vidas actuales.
     */
    public int getVidas()
    {
        return vidas;
    }

    /**
     * Consigue el nivel actual.
     * @return Un numero entero, representa el nivel actual.
     */
    public int getNivel()
    {
        return nivel;
    }

    // Metodos

    /**
     * Incrementa en uno el nivel.
     */
    public void siguienteNivel()
    {
        nivel++;
    }

    /**
     * Incrementa el puntaje.
     */
    public void aumentarPuntaje()
    {
        puntaje++;
    }

    /**
     * Incrementa en uno la vida.
     */
    public void incrementarVidas()
    {
        vidas++;
    }

    /**
     * Disminuye en uno la vida.
     */
    public void disminuirVidas()
    {
        vidas--;
    }

    /**
     * Reinicia el puntaje, nivel y vida.
     */
    public void reiniciar()
    {
        puntaje = 0;
        vidas = 3;
        nivel = 1;
    }
}