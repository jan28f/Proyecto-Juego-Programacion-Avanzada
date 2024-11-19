package puppy.code;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Clase abstracta encargada de representar objetos del juego como los bloques y paleta.
 */
public abstract class ObjectGame
{
    /**
     * Atributos de la clase.
     *
     */
    private int x;
    private int y;
    private int width;
    private int height;
    private int radio;

    /**
     * Constructor de la clase, se inicializa para un objeto circular.
     * @param x Numero entero que representa la posicion en el eje X del objeto
     * @param y Numero entero que representa la posicion en el eje Y del objeto.
     * @param radio Numero entero que representa el radio del objeto circular.
     */
    public ObjectGame(int x, int y, int radio)
    {
        this.x = x;
        this.y = y;
        this.radio = radio;
    }

    /**
     * Constructor de la clase, se inicializa un objeto no circular.
     * @param x Numero entero que representa la posicion en el eje X del objeto
     * @param y Numero entero que representa la posicion en el eje Y del objeto.
     * @param width Numero entero que representa el ancho del objeto.
     * @param height Numero entero que representa el alto del objeto.
     */
    public ObjectGame(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Setters
    /**
     * Establece la posicion del objeto en el eje x.
     * @param x Un numero entero, representa la nueva posicion del objeto en el eje x.
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Establece la posicion del objeto en el eje y.
     * @param y Un numero entero, representa la nueva posicion del objeto en el eje y.
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Establece el ancho del objeto.
     * @param width Un numero entero, representa el nuevo ancho del objeto.
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * Establece la altura del objeto.
     * @param height Un numero entero, representa el nuevo alto del objeto.
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * Establece el radio del objeto.
     * @param radio Un numero entero, representa el nuevo radio del objeto.
     */
    public void setRadio(int radio)
    {
        this.radio = radio;
    }

    // Getters

    /**
     * Obtiene la posicion del objeto en el eje x.
     * @return Un numero entero, representa la posicion del objeto en el eje x.
     */
    public int getX()
    {
        return x;
    }

    /**
     * Obtiene la posicion del objeto en el eje y.
     * @return Un numero entero, representa la posicion del objeto en el eje y.
     */
    public int getY()
    {
        return y;
    }

    /**
     * Obtiene el ancho del objeto.
     * @return Un numero entero, representa el ancho del objeto.
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Obtiene el alto del objeto.
     * @return Un numero entero, representa el alto del objeto.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Obtiene el radio del objeto.
     * @return Un numero entero, representa el radio del objeto.
     */
    public int getRadio()
    {
        return radio;
    }

    // Metodos
    /**
     * Metodo abstracto, encargado de dibujar el objeto en la posicion y con dimensiones indicadas.
     * @param shape Variable que representa el objeto a dibujar.
     */
    public abstract void draw(ShapeRenderer shape);
}
