import java.awt.event.KeyEvent;

/**
 * THE GAME: You are the blue ghost Inky. Your goal is to collect as many wedges of cheese as you can
 * while evading the evil ghosts.
 * 
 * THE BACK-STORY: After spending over 30 fruitless years chasing Pacman with his friends, Inky decides 
 * to pursue his passion for cheese in a different, less friendly neighborhood, in which unfriendly 
 * ghosts abound.)
 * 
 * @author chattrj3
 *
 */

public class PlayGame {
	private static final long START_TIME=System.currentTimeMillis();

	private static GhostList evilG = new GhostList();

	private static int level=1;	
	private static int cheeseEaten=0;
	private static int totalScore=0;
	private static int score=0;
	private static int lives=3;
	private static boolean hasWon;

	public static void main(String[] args) {
		theGame();
	}

	public static  void theGame() {
		Zen.create(840, 450, "");

		Ghost g = new Ghost(580, 20);
		evilG.add(new EvilGhost());

		CheeseList cheese = new CheeseList();
		
		boolean proceed=true;
		
		while(Zen.isRunning()) {

			while(proceed) {
				g.drawGhost(g.getX(), g.getY());

				for(int i=0; i<evilG.getSize(); i++)
					evilG.getGhost(i).drawEvil(evilG.getGhost(i).getX(), evilG.getGhost(i).getY());

				for(int i=0; i<cheese.getLength(); i++)
					if(!cheese.getCheese(i).getEaten())
						Cheese.drawCheese(cheese.getCheese(i).getX(), cheese.getCheese(i).getY());

				move(g);

				boolean gotcha=false;

				for(int i=0; i<evilG.getSize(); i++) {
					moveEvil(evilG.getGhost(i), g,i+1);
					if(gotcha(evilG.getGhost(i), g)) {
						gotcha=true;
						break;
					}
				}

				if(gotcha) { 
					lives--;
					Zen.sleep(200);
					g.setX(580);
					g.setY(20);
					gotcha=false;
				}

				if(lives==0) {
					hasWon=false;
					break;
				}

				for(int i=0; i<cheese.getLength(); i++)
					cheese.getCheese(i).setEaten(g);

				score = cheese.cheeseEaten();

				scoreEtc();			


				if(cheese.isListEaten() && level<2) {
					Zen.sleep(500);
					level++;
					totalScore+=score;
					score=0;
					evilG.add(new EvilGhost(580, 420, 2));
					evilG.getGhost(0).setX(30);
					evilG.getGhost(0).setY(30);
					g.setX(580);
					g.setY(20);
					cheeseEaten=0;
					cheese = new CheeseList();
					continue;
				}

				else if(cheese.isListEaten() && level==2) {
					Zen.sleep(500);
					totalScore+=score;
					score=0;
					levelThree();
				}

				if(level==3)
					break;

				Zen.sleep(10);
				Zen.flipBuffer();
			}
		
		proceed=false;
		
		endOfGameMessage(hasWon);
		
		}

	}
	
	public static void levelThree() {
		level++;
		
		Ghost g = new Ghost(580, 20);
		EvilGhost e = new EvilGhost(40, 40, 1);
		EvilGhost e2 = new EvilGhost(580, 420, 2);
		
		CheeseRing ring = new CheeseRing(e);
		CheeseRing ring2 = new CheeseRing(e2);
		
		while(Zen.isRunning()) {
			g.drawGhost(g.getX(), g.getY());
			
			e.drawEvil(e.getX(), e.getY());
			e2.drawEvil(e2.getX(), e2.getY());
			
			for(int i=0; i<ring.getLength(); i++)
				if(!ring.getCheese(i).getEaten())
					Cheese.drawCheese(ring.getCheese(i).getX(), ring.getCheese(i).getY());
			
			for(int i=0; i<ring2.getLength(); i++)
				if(!ring2.getCheese(i).getEaten())
					Cheese.drawCheese(ring2.getCheese(i).getX(), ring2.getCheese(i).getY());
			
			move(g);
			
			moveEvil(e, g, 1);
			moveEvil(e2, g, 2);
			
			ring.setXY(e);
			ring2.setXY(e2);
			
			boolean gotcha=false;
			
			if(gotcha(e, g) || gotcha(e2,g))
				gotcha=true;
			
			if(gotcha) {
				lives--;
				Zen.sleep(200);
				g.setX(580);
				g.setY(20);
				gotcha=false;				
			}
			
			if(lives==0) { 
				hasWon=false;
				break;
			}
			
			for(int i=0; i<ring.getLength(); i++)
				ring.getCheese(i).setEaten(g);
			
			for(int i=0; i<ring2.getLength(); i++)
				ring2.getCheese(i).setEaten(g);

			score = ring.cheeseEaten() + ring2.cheeseEaten();
			
			scoreEtc();
			
			if(ring.isRingEaten() && ring2.isRingEaten()) {
				hasWon=true;
				break;
			}
			
			Zen.sleep(10);
			Zen.flipBuffer();
		}
		
	}

	/**
	 * Use arrow keys to move the protagonist Inky.
	 * @param g
	 */
	public static void move(Ghost g) {
		if(Zen.isVirtualKeyPressed(KeyEvent.VK_RIGHT))
			g.setDirectionX(true);
		if(Zen.isVirtualKeyPressed(KeyEvent.VK_LEFT))
			g.setDirectionX(false);

		if(Zen.isVirtualKeyPressed(KeyEvent.VK_UP) && g.getY()>=10)
			g.setY(g.getY()-5);

		if(Zen.isVirtualKeyPressed(KeyEvent.VK_DOWN) && g.getY()<=370)
			g.setY(g.getY()+5);

		if(Zen.isVirtualKeyPressed(KeyEvent.VK_LEFT) && g.getX()>=10)
			g.setX(g.getX()-5);

		if(Zen.isVirtualKeyPressed(KeyEvent.VK_RIGHT) && g.getX()<=580)
			g.setX(g.getX()+5);

	}

	/**
	 * Because the evil ghost needs to chase the protagonist.
	 * @param e
	 * @param g
	 */
	public static void moveEvil(EvilGhost e, Ghost g, int a) {

		boolean directionX = g.getX()-e.getX()>0;

		e.setDirectionX(directionX);

		boolean directionY = g.getY()-e.getY()>0;

		if(directionX)
			e.setX(e.getX()+a);
		else e.setX(e.getX()-a);

		if(directionY)
			e.setY(e.getY()+a);
		else e.setY(e.getY()-a);

	}

	/**
	 * When the evil ghost catches the protagonist.
	 * @param e
	 * @param g
	 * @return
	 */
	public static boolean gotcha(EvilGhost e, Ghost g) {
		return Math.abs(e.getX()-g.getX())<=47 && Math.abs(e.getY()-g.getY())<=50;
	}

	
	public static void scoreEtc() {
		int seconds = (int) (System.currentTimeMillis() - START_TIME)/1000;

		Zen.setColor(100, 100, 100);
		Zen.fillRect(660, 15, 170, 410);

		Zen.setColor(250, 0, 0);

		Zen.drawText("Level: " + level, 670, 35);

		if(seconds/60<10 && seconds%60<10)
			Zen.drawText("Time: 0" + seconds/60+":0"+seconds%60, 670, 55);
		else if(seconds/60<10)
			Zen.drawText("Time: 0" + seconds/60+":"+seconds%60, 670, 55);
		else if(seconds%60<10)
			Zen.drawText("Time: " + seconds/60+":0"+seconds%60, 670, 55);
		else Zen.drawText("Time: " + seconds/60+":"+seconds%60, 670, 55);

		if(level==3)
			score+=10*cheeseEaten;
		else score+=cheeseEaten;	
		
		Zen.drawText("Score: " +(totalScore+score), 670, 75);
		
		Zen.drawText("Lives: " + lives, 670, 95);
		
		drawLives();

	}
	
	public static void drawLives() {
		int[] positions = {675105, 675175, 675245};
		for(int i=0; i<lives; i++)
			Zen.drawImage("ghost_inky_flip.gif", positions[i]/1000, positions[i]%1000);
	}
	
	public static void endOfGameMessage(boolean b) {
		Zen.setColor(0,0,0);
		Zen.fillRect(0, 0, 840, 450);
		
		Zen.setColor(250, 250, 250);
		Zen.setFont("Times-50");
		
		if(b)
			Zen.drawText("Congratulations! You won!\nYour Score: "+totalScore, 250, 200);
		else Zen.drawText("You lost.", 250, 200);
		
	}

}
