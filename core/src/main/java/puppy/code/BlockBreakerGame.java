package puppy.code;

import com.badlogic.gdx.ApplicationAdapter;

public class BlockBreakerGame extends ApplicationAdapter {
	private Nivel nivelActual;

	@Override
	public void create()
	{
		nivelActual = new BlockBreakerNivel();
	}

	@Override
	public void render()
	{
		nivelActual.ejecutarNivel();
	}

	@Override
	public void dispose()
	{
		nivelActual.dispose();
	}
}
