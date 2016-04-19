
public class Drawing {
	 public static void main(String[] args) {

	        // number of line segments to plot
	        int N = 15;

	        // the function y = sin(4x) + sin(20x), sampled at N points
	        // between x = 0 and x = pi
	        double[] x = new double[N+1];
	        double[] y = new double[N+1];
	        for(int i=0;i<=N;i++) {
	            x[i] = i;
	        }
	        	y[0] = 0;
	            y[1] = 0.4999908546640918;
	            y[2] = 0.49982381865539105;
	            y[3] = 0.49792100900678854;
	            y[4] =0.48606186827489084;
	            y[5] = 0.45328758568007266;
	            y[6] = 0.40239293067003895;
	            y[7]=0.34128900178598226;
	            y[8] = 0.2782078750411206;
	            y[9] = 0.21972394733373096;
	            y[10] = 0.16985698785894182;
	            y[11] = 0.1288349007206608;
	            y[12] = 0.09643193761216769;
	            y[13] = 0.07184886409496608;
	            y[14] = 0.05355309644482261;
	            y[15] = 0.04009632809698708;
	        // rescale the coordinate system
	        StdDraw.setXscale(0, Math.PI);
	        StdDraw.setYscale(-2.0, +2.0);

	        // plot the approximation to the function
	        for (int i = 0; i < N; i++) {
	            StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
	        }
	    }
}
