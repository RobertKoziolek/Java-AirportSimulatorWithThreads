package pckg;

public class Airport {
	private float x, y;
	private boolean runwayFree = true;
	private String name;
	private boolean [] hangar = new boolean[8];

	public Airport(float x, float y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public synchronized boolean takeRunway(boolean landed) {
		try {
			if (landed)
				wait(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (runwayFree) {
			runwayFree = false;
			return true;
		}
		return false;
	}

	public void freeRunway() {
		runwayFree = true;
	}
	
	public synchronized int getHangarSpace(){
		for (int i=0;i<hangar.length;++i){
			if (hangar[i]==false){
				hangar[i]=true;
				return i;
			}
		}
		return 0;
	}

	public synchronized void freeHangarSpace(int i){
		if (i<hangar.length){
			hangar[i]=false;
		}
	}
	public float getDistance(float x2, float y2) {
		return (float) Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public String getName() {
		return name;
	}
}
