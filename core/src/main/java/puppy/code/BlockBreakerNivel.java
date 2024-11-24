package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

public class BlockBreakerNivel extends Nivel {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shape;
    private PingBall ball;
    private Paddle pad;
    private ArrayList<Block> blocks = new ArrayList<Block>();
    private InfoGame infoPartida;
    private BajarBloques bajarBloques = new BajarBloques();

    @Override
    protected void inicializarNivel()
    {
        if (camera == null)
        {
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 800, 480);
        }
        if (batch == null) batch = new SpriteBatch();
        if (font == null) font = new BitmapFont();
        if (shape == null) shape = new ShapeRenderer();
        ball = new PingBall(Gdx.graphics.getWidth() / 2 - 10, 41, 10, 3, 3, true);
        pad = new Paddle(Gdx.graphics.getWidth() / 2 - 50, 40, 100, 10);
        infoPartida = InfoGame.getIntancia();
        crearBloques(2 + infoPartida.getNivel());
        ContextCamBlock cambio = new ContextCamBlock(new AumentoDurabilidad());
        cambio.ejecutarCambioEstado(blocks);
        cambio.cambiar(bajarBloques);
        cambio.ejecutarCambioEstado(blocks);


    }

    @Override
    protected boolean nivelFinalizado()
    {
        if (infoPartida.getVidas() <= 0)
        {
            // Reinicia puntaje, vidas y nivel.
            infoPartida.reiniciar();
            crearBloques(2 + infoPartida.getNivel());
            Sonidos perdidas = new Sonidos();
            perdidas.cargarSonido("/musica/gameOver.wav");
            perdidas.reproducirSonido();
            bajarBloques.reiniciar(blocks);
            return true;
        }
        if (blocks.size() == 0)
        {
            Sonidos nextLevel = new Sonidos();
            nextLevel.cargarSonido("/musica/nivelTerminado.wav");
            nextLevel.reproducirSonido();
            infoPartida.siguienteNivel();
            infoPartida.incrementarVidas();
            crearBloques(2 + infoPartida.getNivel());
            ball = new PingBall(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11, 10, 3, 3, true);
            bajarBloques.reiniciar(blocks);
            return true;
        }
        for (int i = 0; i < blocks.size(); i++)
        {
            if (pad.verificarColisionBloques(blocks.get(i)))
            {
                infoPartida.reiniciar();
                crearBloques(2 + infoPartida.getNivel());
                Sonidos perdidas = new Sonidos();
                perdidas.cargarSonido("/musica/nivelTerminado.wav");
                perdidas.reproducirSonido();
                bajarBloques.reiniciar(blocks);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void revisarEntradas()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
        {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
                pad.moverLigeroIzquierda();
            else
                pad.moverIzquierda();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
        {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
                pad.moverLigeroDerecha();
            else
                pad.moverDerecha();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && ball.estaQuieto())
        {
            ball.setEstaQuieto(false);
        }
    }

    @Override
    protected void actualizarElementos()
    {
        if (!ball.estaQuieto())
            ball.update();
        else
            ball.setXY(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 10);

        if (ball.getY() < 0)
        {
            infoPartida.disminuirVidas();
            ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 3, 3, true);
            Sonidos caida = new Sonidos();
            caida.cargarSonido("/musica/caidaDePelota.wav");
            caida.reproducirSonido();
        }

        pad.colisionaCon(ball);
        for (Block block : blocks)
            block.colisionaCon(ball);

        for (int i = 0; i < blocks.size(); i++)
        {
            Block b = blocks.get(i);
            if (b.destroyed)
            {
                infoPartida.aumentarPuntaje();
                if (infoPartida.getPuntaje() > infoPartida.getMejorPuntaje())
                {
                    infoPartida.setMejorPuntaje(infoPartida.getPuntaje());
                }
                blocks.remove(b);
                i--;
            }
        }
    }

    @Override
    protected void renderizarElementos()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        pad.draw(shape);
        ball.draw(shape);
        for (Block block : blocks)
        {
            block.draw(shape);
        }
        shape.end();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.getData().setScale(1.5f);
        font.draw(batch, "Puntaje: " + infoPartida.getPuntaje(), 10, 45);
        font.draw(batch, "Mejor puntaje: " + infoPartida.getMejorPuntaje(), 10, 25);
        font.draw(batch, "Nivel: " + infoPartida.getNivel(), Gdx.graphics.getWidth()-10, 45);
        font.draw(batch, "Vidas : " + infoPartida.getVidas(), Gdx.graphics.getWidth()-10, 25);
        batch.end();
    }

    @Override
    protected void finalizarNivel()
    {
        inicializarNivel();
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        font.dispose();
        shape.dispose();
    }

    private void crearBloques(int filas)
    {
        blocks.clear();
        int blockWidth = 70;
        int blockHeight = 26;
        int y = Gdx.graphics.getHeight();
        FabricaBloques fabricaBloques = new FabricaBloquesAleatorios();
        for (int cont = 0; cont < filas; cont++)
        {
            y -= blockHeight + 10;
            for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10)
            {
                blocks.add(fabricaBloques.crearBloque(x, y, blockWidth, blockHeight));

            }
        }
    }
}
