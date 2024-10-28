package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Clase PingBall, representa una pelota.
 */
public class PingBall extends ObjectGame
{
	/**
	 * Variable de clase.
	 */
	private int xSpeed;
	private int ySpeed;
	private Color color = Color.WHITE;
	private boolean estaQuieto;

	/**
	 * Constructor de la clase.
	 * @param x Un numero entero, representa la posicion del objeto en el eje x.
	 * @param y Un numero entero, representa la posicion del objeto en el eje y.
	 * @param radio Un numero entero, representa el radio de la pelota.
	 * @param xSpeed Un numero entero, representa la velocidad de la pelota respecto al eje x.
	 * @param ySpeed Un numero entero, representa la velocidad de la pelota respecto al eje y.
	 * @param iniciaQuieto Un booleano, representa el estado de movimiento de la pelota.
	 */
	public PingBall(int x, int y, int radio, int xSpeed, int ySpeed, boolean iniciaQuieto)
	{
		super(x, y, radio);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		estaQuieto = iniciaQuieto;
	}

	// Setters

	/**
	 * Establece la velocidad de la pelota respecto al eje x.
	 * @param xSpeed Un numero entero, la nueva velocidad de la pelota respecto al eje x.
	 */
	public void setXSpeed(int xSpeed)
	{
		this.xSpeed = xSpeed;
	}

	/**
	 * Establece la velocidad de la pelota respecto al eje y.
	 * @param ySpeed Un numero entero, la nueva velocidad de la pelota respecto al eje y.
	 */
	public void setYSpeed(int ySpeed)
	{
		this.ySpeed = ySpeed;
	}

	/**
	 * Establece el color de la pelota.
	 * @param color El nuevo color de la pelota.
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}

	/**
	 * Establece si esta en movimiento la pelota.
	 * @param bb Un booleano, representa si esta en movimiento la pelota.
	 */
	public void setEstaQuieto(boolean bb)
	{
		estaQuieto = bb;
	}

	// Getters
	/**
	 * Consigue la velocidad de la pelota respecto al eje x.
	 * @return Un numero entero, representa la velocidad de la pelota respecto al eje x.
	 */
	public int getXSpeed()
	{
		return xSpeed;
	}

	/**
	 * Consigue la velocidad de la pelota respecto al eje y.
	 * @return Un numero entero, representa la velocidad de la pelota respecto al eje y.
	 */
	public int getYSpeed()
	{
		return ySpeed;
	}

	/**
	 * Consigue el estado de movimiento de la pelota.
	 * @return Un valor booleano, representa si la pelota esta en movimiento o no.
	 */
	public boolean estaQuieto()
	{
		return estaQuieto;
	}

	// Metodos

	/**
	 * Establece la posicion de la pelota en los puntos x e y.
	 * @param x Un numero entero, representa la posicion de la pelota en eje x.
	 * @param y Un numero entero, representa la posicion de la pelota en eje y.
	 */
	public void setXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Implementacion del metodo abstracto heredado draw, se dibuja la pelota.
	 * @param shape Variable que representa la pelota a dibujar.
	 */
	public void draw(ShapeRenderer shape)
	{
		shape.setColor(color);
		shape.circle(x, y, radio);
	}

	/**
	 * Actualiza el estado de la pelota.
	 */
	public void update()
	{
		if (estaQuieto) return;
		x += xSpeed;
		y += ySpeed;
		if (x-radio < 0 || x+radio > Gdx.graphics.getWidth())
		{
			xSpeed = -xSpeed;
		}
		if (y+radio > Gdx.graphics.getHeight())
		{
			ySpeed = -ySpeed;
		}
	}
}