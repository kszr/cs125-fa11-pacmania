/**
 * 
 * @author chattrj3
 *
 */
public class Ghost {

	private int x, y;
	private static int count=0;
	private boolean directionX;
	
	public Ghost() {
		count++;
		drawGhost(this.x, this.y);
	}
	public Ghost(int x, int y) {
		this.x=x;
		this.y=y;
		drawGhost(this.x, this.y);
		count++;
	}
	public void setDirectionX(boolean b) {
		this.directionX=b;
	}
	public void drawGhost(int a, int b) {
		if(directionX)
			Zen.drawImage("ghost_inky.gif", a, b);
		else Zen.drawImage("ghost_inky_flip.gif", a, b);
	}
	public void setX(int x) {
		this.x=x;
		drawGhost(this.x, this.y);
	}
	public void setY(int y) {
		this.y=y;
		drawGhost(this.x, this.y);
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getCount() {
		return count;
	}
}
