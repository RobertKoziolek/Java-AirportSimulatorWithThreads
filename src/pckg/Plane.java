package pckg;

public class Plane extends Thread {
	private static int planeCounter = 0, baseFuel;
	final private float speed;
	private float x, y;
	private int hangarSpaceTaken, fuel, maxFuel, progress = 0;
	private double angle;
	private Airport airport;
	private boolean alive = true, tookRunway = false, circlingLeft = false;;
	
	private enum State {
		FLYING, CIRCLING, LANDING, LANDED, STARTING
	};

	private State state;

	{
		maxFuel = (int) (baseFuel * (Math.random() * 0.5 + 0.75));
		fuel = maxFuel;
	}

	public Plane() {
		super(Integer.toString(planeCounter++));
		airport = AirportManager.getRandom(null);
		state = State.LANDED;
		takePlaceInHangar();
		speed = 7.f;

		start();
	}

	public void run() {
		while (alive) {
			switch (state) {
			case FLYING:
				fly();
				break;
			case CIRCLING:
				circle();
				break;
			case LANDING:
				tryLanding();
				break;
			case LANDED:
				askForStart();
				break;
			case STARTING:
				tryStarting();
				break;
			}

			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private void askForStart() {
		if (airport.takeRunway(true)) {
			tookRunway = true;
			state = State.STARTING;
			x = airport.getX() + 20;
			y = airport.getY() + 40;
		}
	}

	private void tryStarting() {
		x -= 4;
		if (progress++ > 6) {
			airport.freeRunway();
			airport.freeHangarSpace(hangarSpaceTaken);
			tookRunway = false;
			state = State.FLYING;
			progress = 0;
			flyTo(AirportManager.getRandom(airport));
		}
	}

	private void tryLanding() {
		x += 5;
		if (progress++ > 6) {
			state = State.LANDED;
			fuel = maxFuel;
			takePlaceInHangar();
			progress = 0;
			airport.freeRunway();
			tookRunway = false;
		}
	}

	private void takePlaceInHangar() {
		hangarSpaceTaken = airport.getHangarSpace();

		x = airport.getX() + 38 + hangarSpaceTaken % 2 * 20;
		y = airport.getY() - 6 + hangarSpaceTaken / 2 * 15;
	}

	private void fly() {
		if (airport.getDistance(x, y) > 5.f) {
			x += speed * Math.sin(angle);
			y += speed * Math.cos(angle);
		} else {
			state = State.CIRCLING;
			return;
		}
		burnFuel();
	}

	private void circle() {
		if (airport.takeRunway(false)) {
			tookRunway = true;
			state = State.LANDING;
			x = airport.getX() - 10;
			y = airport.getY() + 40;
			return;
		} else {
			if (circlingLeft){
				x-=4;
				if (x<airport.getX()-20) circlingLeft=false;
			}else{
				x+=4;
				if (x>airport.getX()+20) circlingLeft=true;
			}
		}
		burnFuel();
	}

	private void burnFuel() {
		if (fuel-- < 0) {
			if (tookRunway)
				airport.freeRunway();
			alive = false;
			fuel = 0;
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void flyTo(Airport port) {
		airport = port;
		angle = Math.atan2(airport.getX() - x, airport.getY() - y);
	}

	public static void setBaseFuel(int fuel) {
		baseFuel = fuel;
	}

	public boolean isLanded() {
		return state == State.LANDED;
	}

	public int getFuelPercentage() {
		float f = (float) fuel / (float) maxFuel;
		if (f<0) return 0;
		return (int) (100.f * f);
	}
}
