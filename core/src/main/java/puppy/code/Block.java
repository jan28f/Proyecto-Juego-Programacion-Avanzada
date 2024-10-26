package puppy.code;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;
import com.badlogic.gdx.graphics.Color;

/**
 * Clase Block extendida de ObjectGame, representa un bloque del juego.
 */
public class Block extends ObjectGame
{
    Color cc;
    boolean destroyed;

    /**
     * Constructor de la clase.
     * @param x Un numero entero, representa la posicion del objeto en el eje x.
     * @param y Un numero entero, representa la posicion del objeto en el eje y.
     * @param width Un numero entero, representa el ancho del objeto.
     * @param height Un numero entero, representa el alto del objeto.
     */
    public Block(int x, int y, int width, int height)
    {
        super(x, y, width, height);
        destroyed = false;
        Random r = new Random(x+y);
        cc = new Color(0.1f+r.nextFloat(1), r.nextFloat(1), r.nextFloat(1), 10);
    }

    /**
     * Implementacion del metodo abstracto heredado draw, se dibuja el bloque.
     * @param shape Variable que representa el bloque a dibujar.
     */
    public void draw(ShapeRenderer shape)
    {
    	shape.setColor(cc);
        shape.rect(x, y, width, height);
    }
}