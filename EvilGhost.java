/**
 * 
 * @author chattrj3
 *
 */
public class EvilGhost extends Ghost {
	private int x, y;
	private static int count=0;
	private int id;
	private static String[] arrayID = {"evil_ghost.gif", "evil_ghost2.gif", "evil_ghost3.gif", "evil_ghost_flip.gif", "evil_ghost2_flip.gif", "evil_ghost3_flip.gif"};
	private boolean directionX;
	
	public EvilGhost() {
		this.x=30;
		this.y=30;
		this.id=1;
		count++;
	}
	public EvilGhost(int a, int b, int id) {
		this.x=a;
		this.y=b;
		this.id=id;
		count++;
		this.id=id;
	}
	public void setDirectionX(boolean b) {
		this.directionX=b;
	}
	
	public void drawEvil(int a, int b) {
			if(this.directionX)
				Zen.drawImage(arrayID[id-1], a, b);
			else Zen.drawImage(arrayID[id+2], a, b);
		}
	
	public void setX(int a) {
		this.x=a;
		drawEvil(this.x, this.y);
	}
	public void setY(int a) {
		this.y=a;
		drawEvil(this.x, this.y);
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}

}
