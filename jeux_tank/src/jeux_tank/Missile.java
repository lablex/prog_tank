package jeux_tank;

public class Missile {
	private double X0;
	private double Y0;
	private double V0;
	private double xNew = 0;
	private double yNew = 0;
	private double theta;
	private double g = 10.0;
	private volatile boolean bool = true;
	private volatile boolean running = true;
	private Point pointPosition;
	private String name;
	private Tank tank;
	private final int PLUS = 50 * (106 / 2) / 180;
	private final int PLUS2 = 50 * 106 / 180;
	private final int PLUS2s = 50 * 206 / 180;
	private final int PLUS3 = 100 * 150 / 500;
	private int i = 0;
	private double t = 0;

	public Missile(Tank tank,String name) {
		pointPosition = new Point(0, 0);
		this.tank=tank;
		this.name=name;
	}

	
	public Point setPosition() {
		if(running){

				i++;
				t=i * 0.005;
				xNew = X0 + V0 * Math.cos(theta) * t;
				pointPosition.setPointX(xNew);
		
				yNew = +Y0 - 0.5 * g * Math.pow(t, 2) + V0 * Math.sin(theta) * t;
				pointPosition.setPointY(yNew);
			}
			if (-Terrain.getTerrainY((int)getPositionX())>getPositionY()) {
				running = false;
				i = 0;

			}

		return pointPosition;
	}


	public void setMissile(double V0) {
		pointPosition = new Point(0, 0);
		this.V0 = V0;
		this.X0 = Terrain.getTerrainX(tank.getXInt())+ (int) (PLUS2 * Math.sin(tank.getAngleTank()))-10;
		this.Y0 = -Terrain.getTerrainY(tank.getXInt())+ (int) (PLUS2 * Math.cos(tank.getAngleTank()))+10;
		this.theta = -tank.getAngleCanon()-tank.getAngleTank();
		yNew = 0;
		xNew = 0;
		

	}

	public boolean getRunning() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running=running;
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
