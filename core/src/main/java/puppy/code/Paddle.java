package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Clase Paddle extendida de ObjectGame, representa la paleta del juego.
 */
public class Paddle extends ObjectGame
{
    /**
     * Constructor de la clase.
     * @param x Un numero entero, representa la posicion del objeto en el eje x.
     * @param y Un numero entero, representa la posicion del objeto en el eje y.
     * @param ancho Un numero entero, representa el ancho del objeto.
     * @param alto Un numero entero, representa el alto del objeto.
     */
    public Paddle(int x, int y, int ancho, int alto) {
    	super(x, y, ancho, alto);
    }

    /**
     * Implementacion del metodo abstracto heredado draw, se dibuja la paleta.
     * @param shape Variable que representa la paleta a dibujar.
     */
	public void draw(ShapeRenderer shape)
    {
        shape.setColor(Color.BLUE);
        int x2 = x; //= Gdx.input.getX();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
            x2 =x-8;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            x2=x+8;
        // y = Gdx.graphics.getHeight() - Gdx.input.getY();
        if (x2 > 0 && x2+width < Gdx.graphics.getWidth()) {
            x = x2;
        }
        shape.rect(x, y, width, height);
    }
}