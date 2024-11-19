package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Clase Paddle extendida de ObjectGame, representa la paleta del juego.
 */
public class Paddle extends ObjectGame implements Colisionable
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
        shape.rect(getX(), getY(), getWidth(), getHeight());
    }

    public void moverIzquierda()
    {
        int posNueva = getX() - 8;
        if (posNueva > 0 && posNueva + getWidth() < Gdx.graphics.getWidth()) {
            setX(posNueva);
        }
    }
    public void moverDerecha()
    {
        int posNueva = getX() + 8;
        if (posNueva > 0 && posNueva + getWidth() < Gdx.graphics.getWidth()) {
            setX(posNueva);
        }
    }
    public void moverLigeroIzquierda()
    {
        int posNueva = getX() - 3;
        if (posNueva > 0 && posNueva + getWidth() < Gdx.graphics.getWidth()) {
            setX(posNueva);
        }
    }
    public void moverLigeroDerecha()
    {
        int posNueva = getX() + 3;
        if (posNueva > 0 && posNueva + getWidth() < Gdx.graphics.getWidth()) {
            setX(posNueva);
        }
    }

    /**
     * Se verifica si existe colision entre la pelota y la paleta, para cambiar la direccion de la pelota.
     * @param pelota Pelota a verificar si colisiona con el elemento.
     */
    public void colisionaCon(PingBall pelota)
    {
        if(verificarColision(pelota) && pelota.getYSpeed() < 0)
        {
            // Cambio de direccion de la pelota.
            pelota.setXSpeed(calcularVelocidadX(getX(), pelota.getX(), getWidth()));
            pelota.setYSpeed(-pelota.getYSpeed());
            // Posicionamiento de la pelota sobre la paleta.
            pelota.setY(getY() + getHeight() + pelota.getRadio());

            pelota.setColor(Color.GREEN);
        }
        else
        {
            pelota.setColor(Color.WHITE);
        }
    }

    /**
     * Metodo privado, calcula la nueva velocidad de la pelota respecto al eje x segun donde esta golpee la paleta.
     * @param paletaPosX Un numero entero, representa la posicion de la paleta respecto al eje X.
     * @param pelotaPosX Un numero entero, representa la posicion de la pelota respecto al eje X.
     * @param paletaAncho Un numero entero, representa el ancho de la paleta.
     * @return Un numero entero, representa la nueva velocidad de la pelota respecto al eje X.
     */
    private int calcularVelocidadX(int paletaPosX, int pelotaPosX, int paletaAncho)
    {
        float centroPaleta = paletaPosX + paletaAncho / 2.0f;
        float distancia = (pelotaPosX - centroPaleta) / (paletaAncho / 2.0f);

        float nuevaVelocidadX = distancia * 5;
        if (nuevaVelocidadX > 3)
        {
            nuevaVelocidadX = 3;
        }
        else if (nuevaVelocidadX < -3)
        {
            nuevaVelocidadX = -3;
        }

        return (int)nuevaVelocidadX;
    }

    /**
     * Se verifica si existe la colision entre la paleta y la pelota.
     * @param pelota Pelota a verificar si colisiona con la paleta.
     * @return true si existe colision entre los elementos, false en caso contrario.
     */
    public boolean verificarColision(PingBall pelota)
    {
        boolean intersectaX = (getX() + getWidth() >= pelota.getX() - pelota.getRadio()) && (getX() <= pelota.getX() + pelota.getRadio());
        boolean intersectaY = (getY() + getHeight() >= pelota.getY() - pelota.getRadio()) && (getY() <= pelota.getY() + pelota.getRadio());
        return intersectaX && intersectaY;
    }
}