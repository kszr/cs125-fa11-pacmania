/**
 * 
 * @author chattrj3
 *
 */
public class CheeseList {
	private int length = 70;
	private Cheese[] cheese = new Cheese[length];
	
	public CheeseList() {
		for(int i=0; i<cheese.length; i++)
			cheese[i] = new Cheese((int)(600*Math.random()), (int)(400*Math.random()));
	}
	
	public Cheese getCheese(int a) {
		return cheese[a];
	}
	
	public int getLength() {

		return length;
	}
	
	public int cheeseEaten() {
		int num=0;
		for(int i=0; i<cheese.length; i++)
			if(cheese[i].getEaten())
				num++;
		
		return num;
	}
	
	public boolean isListEaten() {
		for(int i=0; i<cheese.length; i++)
			if(!cheese[i].getEaten())
				return false;
		
		return true;
	}

}
