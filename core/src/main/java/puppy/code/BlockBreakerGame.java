package puppy.code;

import java.util.ArrayList;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class BlockBreakerGame extends ApplicationAdapter {
    private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	private ShapeRenderer shape;
	private PingBall ball;
	private Paddle pad;
	private ArrayList<Block> blocks = new ArrayList<>();
	private InfoGame infoPartida;

		@Override
		public void create ()
		{
			camera = new OrthographicCamera();
		    camera.setToOrtho(false, 800, 480);
		    batch = new SpriteBatch();
		    font = new BitmapFont();
		    font.getData().setScale(2.0f, 1.5f);
			infoPartida = InfoGame.getIntancia();
		    crearBloques(2 + infoPartida.getNivel());

		    shape = new ShapeRenderer();
		    ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10, 3, 3 , true);
		    pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10);
		}
		public void crearBloques(int filas) {
			blocks.clear();
			int blockWidth = 70;
		    int blockHeight = 26;
		    int y = Gdx.graphics.getHeight();
		    for (int cont = 0; cont<filas; cont++ ) {
		    	y -= blockHeight+10;
		    	for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
		            blocks.add(new Block(x, y, blockWidth, blockHeight));
		        }
		    }
		}
		public void dibujaTextos()
		{
			//actualizar matrices de la cámara
			camera.update();
			//actualizar
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			//Dibuja el texto en pantalla
			font.draw(batch, "Puntaje: " + infoPartida.getPuntaje(), 10, 45);
			font.draw(batch, "Mejor puntaje: " + infoPartida.getMejorPuntaje(), 10, 25);
			font.draw(batch, "Nivel: " + infoPartida.getNivel(), Gdx.graphics.getWidth()-10, 45);
			font.draw(batch, "Vidas : " + infoPartida.getVidas(), Gdx.graphics.getWidth()-10, 25);
			//font.draw(batch, "X: " + ball.getXSpeed(), 300, 25);
			//font.draw(batch, "Y: " + ball.getYSpeed(), 400, 25);
			batch.end();

		}

		@Override
		public void render()
		{
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        shape.begin(ShapeRenderer.ShapeType.Filled);
	        pad.draw(shape);
	        // monitorear inicio del juego
	        if (ball.estaQuieto())
			{
	        	ball.setXY(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11);
	        	if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT))
					ball.setEstaQuieto(false);
	        }
			else
				ball.update();

	        //verificar si se fue la bola x abajo
	        if (ball.getY() < 0)
			{
				infoPartida.disminuirVidas();
	        	ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 3, 3, true);
				Sonidos caidas = new Sonidos();
	        		caidas.cargarSonido("C:\\Users\\Jose Mena\\Documents\\workspace\\Proyecto-Juego-Programacion-Avanzada-main\\lwjgl3\\src\\musica\\080047_lose_funny_retro_video-game-80925.wav");
	        		if (vidas > 0)
	        			caidas.reproducirSonido();
	        }

	        // verificar game over
	        if (infoPartida.getVidas() <= 0)
			{
				// Reinicia puntaje, vidas y nivel.
				infoPartida.reiniciar();
	        	crearBloques(2 + infoPartida.getNivel());
	        	//ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
				Sonidos perdidas = new Sonidos();
	        		perdidas.cargarSonido("C:\\Users\\Jose Mena\\Documents\\workspace\\Proyecto-Juego-Programacion-Avanzada-main\\lwjgl3\\src\\musica\\game-over-39-199830.wav");
	        		perdidas.reproducirSonido();

	        }

	        // verificar si el nivel se terminó
	        if (blocks.size() == 0)
			{
				Sonidos nextLevel = new Sonidos();
	        		nextLevel.cargarSonido("C:\\Users\\Jose Mena\\Documents\\workspace\\Proyecto-Juego-Programacion-Avanzada-main\\lwjgl3\\src\\musica\\item-39146.wav");
	        		nextLevel.reproducirSonido();
				infoPartida.siguienteNivel();
				infoPartida.incrementarVidas();
	        	crearBloques(2 + infoPartida.getNivel());
	        	ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 3, 3, true);
	        }
	        //dibujar bloques
	        for (Block b : blocks) {
	            b.draw(shape);
	            b.colisionaCon(ball);
	        }
	        // actualizar estado de los bloques
	        for (int i = 0; i < blocks.size(); i++) {
	            Block b = blocks.get(i);
	            if (b.destroyed)
				{
					infoPartida.aumentarPuntaje();
					if (infoPartida.getPuntaje() > infoPartida.getMejorPuntaje())
					{
						infoPartida.setMejorPuntaje(infoPartida.getPuntaje());
					}
	                blocks.remove(b);
	                i--; //para no saltarse 1 tras eliminar del arraylist
	            }
	        }

	        pad.colisionaCon(ball);
	        ball.draw(shape);

	        shape.end();
	        dibujaTextos();
		}

		@Override
		public void dispose () {
		}
	}
