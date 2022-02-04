import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 *
 * @author Jeremiah Hennessy
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     * 
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.println("Please enter a positive real number: ");
        String num = in.nextLine();
      //will short circuit if the double can't be parsed
        while(!FormatChecker.canParseDouble(num) || !(Double.parseDouble(num) > 0)) {
            out.println("Please enter a positive real number: "); 
            num = in.nextLine();
        }
        return Double.parseDouble(num);
    }
      
    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     * 
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in, SimpleWriter out) {
        out.println("Please enter a positive real number greater than 1: ");
        String num = in.nextLine();
      //will short circuit if the double can't be parsed
        while(!FormatChecker.canParseDouble(num) || !(Double.parseDouble(num) > 0) || !(Double.parseDouble(num) != 1)) {
            out.println("Please enter a positive real number: "); 
            num = in.nextLine();
        }
        return Double.parseDouble(num);
    }
    
    /**
     * Calculates the percent of error between two numbers.
     *
     * @param a
     *            a double not equal to 0
     * @param b
     *            a double to be compared with a
     * @return a double in the form of a percent
     * that represents the percent of error of b
     */
    private static double error(double a, double b) {
        return (Math.abs(a - b) / a) * 100;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        
        final double EXP[] = {-5.0, -4.0, -3.0, -2.0, -1.0, -0.5, (double)-1/3, -0.25, 0.0, 0.25, (double)1/3, (double)1/2, 1.0, 2.0, 3.0, 4.0, 5.0};
        
        out.println("For the universal constant: ");
        double u = getPositiveDouble(in, out);
        out.println("For the value w: ");
        double w = getPositiveDoubleNotOne(in, out);
        out.println("For the value x: ");
        double x = getPositiveDoubleNotOne(in, out);
        out.println("For the value y: ");
        double y = getPositiveDoubleNotOne(in, out);
        out.println("For the value z: ");
        double z = getPositiveDoubleNotOne(in, out);
        
        //best group of exponents
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        //closest number to u
        double closest = 0;
        for (int i = 0; i < EXP.length; i++) {
            for (int j = 0; j < EXP.length; j++) {
                for (int k = 0; k < EXP.length; k++) {
                    for (int m = 0; m < EXP.length; m++) {
                        if(Math.abs((Math.pow(w, EXP[i]) * Math.pow(x, EXP[j]) * Math.pow(y, EXP[k]) * Math.pow(z, EXP[m])) - u) < Math.abs(closest - u)) {
                            a = EXP[i];
                            b = EXP[j];
                            c = EXP[k];
                            d = EXP[m];
                            closest = Math.pow(w, EXP[i]) * Math.pow(x, EXP[j]) * Math.pow(y, EXP[k]) * Math.pow(z, EXP[m]);
                        }
                    }
                }
            }
        }
        
        out.print("The values of the exponents are: ");
        out.println(a + " " + b + " " + c + " " + d);
        out.print("The approximated value is about: ");
        out.println(closest, 2, false);
        out.print("The percent of error is: ");
        out.println(error(u, closest), 2, false);
        
        in.close();
        out.close();
    }

}
