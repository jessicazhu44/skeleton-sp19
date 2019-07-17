/* the body class and constructor */

public class Body{
	/* All method are non-static */
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static final double g = 6.67e-11;

	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		/* Calculate distance between two bodies (double) */
		double dx = xxPos - b.xxPos;
		double dy = yyPos - b.yyPos;
		return Math.sqrt(dx*dx+dy*dy);
	}

	public double calcForceExertedBy(Body b){
		/* Takes in a body and returns a double describing
		the force exerted on this body by a given body */
		double r = calcDistance(b);
		double force = (g *b.mass*mass)/ (r*r);
		return force;
	}

	public double calcForceExertedByX(Body b){
    	/* Describe the force exerted in xy direction with signs */
    	double force = calcForceExertedBy(b);
    	double r = calcDistance(b);
    	double xforce = (force *(b.xxPos-xxPos))/(r);
    	return xforce;
	}

	public double calcForceExertedByY(Body b){
    	/* Describe the force exerted in xy direction with signs */
    double force = calcForceExertedBy(b);
    double r = calcDistance(b);
    double yforce = (force *(b.yyPos-yyPos))/(r);
    return yforce;
	}

	public double calcNetForceExertedByX(Body[] bodies){
		/* Takes in an array of bodies and calculate 
		the Net X and Y forces exerted by all bodies */
		int index = 0;
		double force = 0;
		while (index < bodies.length){
			if (!this.equals(bodies[index])){
			force = force + calcForceExertedByX(bodies[index]);	
			}
			
			index = index +1;
		}
		return force;
	}

	public double calcNetForceExertedByY(Body[] bodies){
		/* Calculate net force exerted by y */
		int index = 0;
		double force = 0;
		while (index < bodies.length){			
			if (!this.equals(bodies[index])){
			force = force + calcForceExertedByY(bodies[index]);	
			}
			index = index +1;
		}
		return force;
	}
	
	public void update(double dt, double fX, double fY){
		/* Updates the body's position and velocity variables,
		doesnt return anything */
		/* Calculate acceleration using the provided x, y forces */
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		xxVel = this.xxVel + dt*ax;
		yyVel = this.yyVel + dt*ay;
		xxPos = this.xxPos + dt*xxVel;
		yyPos = this.yyPos + dt*yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
	}

}












