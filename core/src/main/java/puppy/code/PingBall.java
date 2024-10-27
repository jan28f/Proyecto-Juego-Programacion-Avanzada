package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall extends ObjectGame
{
	    private int xSpeed;
	    private int ySpeed;
	    private Color color = Color.WHITE;
	    private boolean estaQuieto;
	    
	    public PingBall(int x, int y, int radio, int xSpeed, int ySpeed, boolean iniciaQuieto)
		{
	        super(x, y, radio);
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
	        estaQuieto = iniciaQuieto;
	    }
	    
	    public boolean estaQuieto() {
	    	return estaQuieto;
	    }
	    public void setEstaQuieto(boolean bb) {
	    	estaQuieto=bb;
	    }
	    public void setXY(int x, int y) {
	    	this.x = x;
	        this.y = y;
	    }
	    public int getY() {return y;}
	    
	    public void draw(ShapeRenderer shape){
	        shape.setColor(color);
	        shape.circle(x, y, radio);
	    }
	    
	    public void update() {
	    	if (estaQuieto) return;
	        x += xSpeed;
	        y += ySpeed;
	        if (x-radio < 0 || x+radio > Gdx.graphics.getWidth()) {
	            xSpeed = -xSpeed;
	        }
	        if (y+radio > Gdx.graphics.getHeight()) {
	            ySpeed = -ySpeed;
	        }
	    }

	    public void checkCollision(Paddle paddle) {
	        if(collidesWith(paddle) && ySpeed < 0){
				ySpeed = -ySpeed;
				y = paddle.getY() + paddle.getHeight() + radio;
	            color = Color.GREEN;
	        }
	        else{
	            color = Color.WHITE;
	        }
	    }
	    private boolean collidesWith(Paddle pp) {

	    	boolean intersectaX = (pp.getX() + pp.getWidth() >= x-radio) && (pp.getX() <= x+radio);
	        boolean intersectaY = (pp.getY() + pp.getHeight() >= y-radio) && (pp.getY() <= y+radio);
	    	return intersectaX && intersectaY;
	    }
	    
	    public void checkCollision(Block block) {
	        if(collidesWith(block)){
				if (x >= block.x && x <= block.x + block.width) {
					ySpeed = -ySpeed;
				} else {
					xSpeed = -xSpeed;
				}
	            block.destroyed = true;
	        }
	    }
	    private boolean collidesWith(Block bb) {

	    	boolean intersectaX = (bb.x + bb.width >= x-radio) && (bb.x <= x+radio);
	        boolean intersectaY = (bb.y + bb.height >= y-radio) && (bb.y <= y+radio);
	    	return intersectaX && intersectaY;
	    }
}