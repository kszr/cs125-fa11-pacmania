/**
 * 
 * @author chattrj3
 *
 */
public class GhostList {
	private EvilGhost[] list = new EvilGhost[1];
	private int size=0;
	
	public void add(EvilGhost g) {
		EvilGhost[] temp;
		size++;
		if(size>list.length)
			temp = new EvilGhost[size*2];
		
		else temp = new EvilGhost[size];
		
		for(int i=0; i<size-1; i++)
			temp[i] = list[i];
		
		temp[size-1] = g;
		
		list=temp;
		
	}
	
	public EvilGhost getGhost(int a) {
		return list[a];
	}
	
	public int getSize() {
		return this.size;
	}
}
