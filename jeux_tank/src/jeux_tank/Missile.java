package jeux_tank;

public class Missile extends Thread {
	private volatile double X0;
	private volatile double Y0;
	private volatile double V0;
	volatile double VyNew = 0;
	volatile double xNew = 0;
	volatile double yNew = 0;
	volatile double VyHold;
	volatile double xHold;
	volatile double yHold;
	double theta;
	double g = 10.0;
	volatile double Vx;
	volatile boolean bool = true;
	protected volatile boolean running = true;
	Point pointPosition;
	private String name;

	public Missile(double V0, double X0, double Y0, double theta,String name) {
		pointPosition = new Point(0, 0);
		this.V0 = V0;
		this.X0 = X0;
		this.Y0 = Y0;
		this.theta = theta * (3.14 / 180);
		this.name=name;
	}

	public Point position(double t) {

		xNew = X0 + V0 * Math.cos(theta) * t;
		pointPosition.setPointX(xNew);

		yNew = +Y0 - 0.5 * g * Math.pow(t, 2) + V0 * Math.sin(theta) * t;
		pointPosition.setPointY(yNew);

		return pointPosition;
	}

	public void run() {
		try {
			int i = 0;
			while (running) {
				if (bool) {

					i++;

					position(i * 0.005);
					sleep(1);
					
					if (-Terrain.getTerrainY((int)getPositionX())>getPositionY()) {
						bool = false;
						i = 0;

					}
				}
			}
		} catch (InterruptedException e) {
		}
	}

	public void setMissile(double V0, double X0, double Y0, double theta) {
		pointPosition = new Point(0, 0);
		this.V0 = V0;
		this.X0 = X0;
		this.Y0 = Y0;
		this.theta = -theta;
		VyNew = 0;
		yNew = 0;
		xNew = 0;

	}

	public boolean getRunning() {
		return running;
	}

	public Point getPosition() {
		return pointPosition;

	}

	public double getPositionX() {
		return pointPosition.getPointX();
	}

	public double getPositionY() {
		return pointPosition.getPointY();
	}

	public void setPositionX(double positionX) {
		this.pointPosition.setPointX(positionX);
	}

	public void setPositionY(double positionY) {
		this.pointPosition.setPointY(positionY);
	}
}
