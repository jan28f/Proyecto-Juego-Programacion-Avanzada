package puppy.code;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;
import com.badlogic.gdx.graphics.Color;

/**
 * Clase Block extendida de ObjectGame, representa un bloque del juego.
 */
public class Block extends ObjectGame implements Colisionable
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

    /**
     * Se verifica y elimina el bloque en caso de existir colision con una pelota.
     * @param pelota Pelota a verificar si colisiona con el bloque.
     */
    public void colisionaCon(PingBall pelota)
    {
        if (verificarColision(pelota))
        {
            // Se calcula la distancia del rectangulo al centro de la pelota en ambos ejes.
            double proximidadX = Math.max(x, Math.min(pelota.getX(), x + width));
            double proximidadY = Math.max(y, Math.min(pelota.getY(), y + height));
            proximidadX = Math.abs(pelota.getX() - proximidadX);
            proximidadY = Math.abs(pelota.getY() - proximidadY);

            // Si pega en la esquina del bloque.
            if (proximidadX == proximidadY)
            {
                pelota.setXSpeed(-pelota.getXSpeed());
                pelota.setYSpeed(-pelota.getYSpeed());
            }
            // Si pega por los lados del bloque.
            else if (proximidadX > proximidadY)
            {
                pelota.setXSpeed(-pelota.getXSpeed());
            }
            // Si pega por arriba o debajo del bloque.
            else
            {
                pelota.setYSpeed(-pelota.getYSpeed());
            }

            destroyed = true;
            
        }
    }

    /**
     * Implementacion del metodo heredado de la interfaz, se verifica existe colision entre el bloque y la pelota.
     * @param pelota Pelota a verificar si colisiona con el bloque.
     * @return true si existe colision entre los elementos, false en caso contrario.
     */
    public boolean verificarColision(PingBall pelota)
    {
        boolean intersectaX = (x + width >= pelota.getX() - pelota.getRadio()) && (x <= pelota.getX() + pelota.getRadio());
        boolean intersectaY = (y + height >= pelota.getY() - pelota.getRadio()) && (y <= pelota.getY() + pelota.getRadio());
        return intersectaX && intersectaY;
    }
}
