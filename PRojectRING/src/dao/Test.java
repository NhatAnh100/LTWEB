package dao;

public class Test {
	
	public static double roundHalf(double number) {
	    double diff = number - (int)number;
	    if (diff < 0.25) return (int)number;
	    else if (diff < 0.75) return (int)number + 0.5;
	    else return (int)number + 1;
	}
	
	public static void main(String[] args) {
		
		double test2 = 3.6;
		float test = Math.round((test2*2)/2.0f);
		System.out.println(roundHalf(test2));
		System.out.println((double)29/3);
	}
}
