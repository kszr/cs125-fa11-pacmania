
public class CheeseRing {
	private Cheese[] ring = new Cheese[6];
	
	private int x0, y0;
	
	public CheeseRing(EvilGhost e) {
		x0 = (int)(e.getX()+28-25);
		y0 = (int)(e.getY()+30-25);
		int r=60;
		for(int i=0; i<ring.length; i++) {
			ring[i]=new Cheese(x0+(int)(r*Math.cos(i*Math.PI/3)), y0+(int)(r*Math.sin(i*Math.PI/3)));
		}
	}
	public void setXY(EvilGhost e) {
		x0=(int)(e.getX()+28-25);
		y0=(int)(e.getY()+30-25);
		for(int i=0; i<ring.length; i++) {
			ring[i].setX(x0+(int)(60*Math.cos(i*Math.PI/3)));
			ring[i].setY(y0+(int)(60*Math.sin(i*Math.PI/3)));
		}
	}
	public Cheese getCheese(int a) {
		return this.ring[a];
	}
	public int getLength() {
		return this.ring.length;
	}
	
	public int cheeseEaten() {
		int num=0;
		for(int i=0; i<ring.length; i++)
			if(ring[i].getEaten())
				num++;
		
		return num;
	}
	
	public boolean isRingEaten() {
		for(int i=0; i<ring.length; i++)
			if(!ring[i].getEaten())
				return false;
		
		return true;
	}

}
