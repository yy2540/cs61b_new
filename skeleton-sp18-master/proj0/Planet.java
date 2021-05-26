public class Planet{
	public static final double GRAVITATIONAL = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, 
		double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
	}

	public double calcForceExertedBy(Planet p){
		return GRAVITATIONAL * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
	}

	public double calcForceExertedByX(Planet p){
		if (this.xxPos >= p.xxPos){
			return this.calcForceExertedBy(p) * (this.xxPos - p.xxPos) / this.calcDistance(p);
		}else{
			return -this.calcForceExertedBy(p) * (this.xxPos - p.xxPos) / this.calcDistance(p);
		}
	}

	public double calcForceExertedByY(Planet p){
		if (this.yyPos >= p.yyPos){
			return this.calcForceExertedBy(p) * (this.yyPos - p.yyPos) / this.calcDistance(p);
		}else{
			return -this.calcForceExertedBy(p) * (this.yyPos - p.yyPos) / this.calcDistance(p);
		}
	}

	public double calcNetForceExertedByX(Planet[] arr){
		double net = 0;
		for (int i = 0; i < arr.length; i++){
			if (this.equals(arr[i])){
				continue;
			}
			net += this.calcForceExertedByX(arr[i]);
		}
		return net;
	}

	public double calcNetForceExertedByY(Planet[] arr){
		double net = 0;
		for (int i = 0; i < arr.length; i++){
			if (this.equals(arr[i])){
				continue;
			}
			net += this.calcForceExertedByY(arr[i]);
		}
		return net;
	}

	public void update(double dt, double fX, double fY){
		this.xxVel = xxVel + dt * (fX / this.mass);
		this.yyVel = yyVel + dt * (fY / this.mass);
		this.xxPos = xxPos + dt * xxVel;
		this.yyPos = yyPos + dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
	}
}