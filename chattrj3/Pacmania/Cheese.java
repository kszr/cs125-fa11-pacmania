/**
 * 
 * @author chattrj3
 *
 */
public class Cheese {
	private int x, y;
	private boolean isEaten = false;
	public Cheese(int x, int y) {
		this.x=x;
		this.y=y;
		drawCheese(this.x, this.y);
	}
	public void setX(int a) {
		this.x=a;
		if(!isEaten)
			drawCheese(this.x, this.y);
	}
	public void setY(int a) {
		this.y=a;
		if(!isEaten)
			drawCheese(this.x, this.y);
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void setEaten(Ghost g) {
		if(!isEaten)
			this.isEaten = Math.abs(g.getX()-this.x)<=47 && Math.abs(g.getY()-y)<=50;
	}
	public boolean getEaten() {
		return this.isEaten;
	}
	public static void drawCheese(int a, int b) {
			Zen.drawImage("cheese.gif", a, b);
	}

}
