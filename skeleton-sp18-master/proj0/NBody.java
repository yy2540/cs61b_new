import java.util.Scanner;

public class NBody {
    public static double readRadius(String planetsTxtPath){
        In in = new In(planetsTxtPath);
    	int firstItemInFile = in.readInt();
    	double secondItemInFile = in.readDouble();
    	return secondItemInFile;
    }

    public static Planet[] readPlanets(String planetsTxtPath){
        In in = new In(planetsTxtPath);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        // create an empty planet array
        Planet[] arr = new Planet[firstItemInFile];
        for (int i = 0; i < firstItemInFile; i++){
            Planet p = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
            arr[i] = p;
        }
        return arr;
    }

    public static void main(String[] args){
        /*
        Copy from the internet.
         */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] ps = readPlanets(filename);

        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
//        StdDraw.picture(0, 0, "images/starfield.jpg");

        //draw
        double t = 0;
        while (t <= T) {
            double[] xforce = new double[ps.length];
            double[] yforce = new double[ps.length];
            for (int i = 0; i < ps.length; i++) {
                xforce[i] = ps[i].calcNetForceExertedByX(ps);
                yforce[i] = ps[i].calcNetForceExertedByY(ps);
            }

            for (int i = 0; i < ps.length; i++) {
                ps[i].update(dt, xforce[i], yforce[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : ps) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }

        //print the universe
        StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < ps.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                    ps[i].yyVel, ps[i].mass, ps[i].imgFileName);
        }

    }
}
