/**
 * 
 * @author chattrj3
 *
 */
public class CheeseList {
	private int length = 70;
	private Cheese[] cheese;
	
	public CheeseList() {
		cheese=new Cheese[length];
		for(int i=0; i<cheese.length; i++)
			cheese[i] = new Cheese((int)(600*Math.random()), (int)(400*Math.random()));
	}
	public CheeseList(int[][] array) {
		length=array.length*array[0].length;
		cheese=new Cheese[length];
		for(int i=0; i<array.length; i++)
			for(int j=0; j<array[0].length; j++ )
				cheese[array[0].length*i +j] = new Cheese(array[i][j]/100, array[i][j]%100);
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
