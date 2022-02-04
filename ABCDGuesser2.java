import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;
import static java.lang.Math.pow;
import static java.lang.Math.abs;

/**
 *This program utilizes the charming theory by calling for 4 real numbers
 *greater than 1 and finding the respective exponents that will make the
 *equation w^a*x^b*y^c*z^d equal to a chosen universal constant u within 1% error.
 *
 * @author
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
        double num = 0.0;
        boolean flag = true;
        while (flag) {
            out.println("Please enter a positive real number: ");
            String literal = in.nextLine();
            if (FormatChecker.canParseDouble(literal)) {
                if (Double.parseDouble(literal) > 0.0) {
                    num = Double.parseDouble(literal);
                    flag = false;
                } else {
                    out.println("Invalid input.");
                }
            } else {
                out.println("Invalid input.");
            }
        }
        return num;
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
        double num = 0.0;
        boolean flag = true;
        while (flag) {
            out.println("Please enter a positive real number not equal to 1: ");
            String literal = in.nextLine();
            if (FormatChecker.canParseDouble(literal)) {
                if (Double.parseDouble(literal) != 1.0 && Double.parseDouble(literal) > 0.0) {
                    num = Double.parseDouble(literal);
                    flag = false;
                } else {
                    out.println("Invalid input.");
                }
            } else {
                out.println("Invalid input.");
            }
        }
        return num;
    }

    /**
     * Calculates the percent of error between two numbers.
     *
     * @param a
     *            The desired value
     * @param b
     *            The value used to see how close it is to a
     * @return a double in the form of a percent
     * that represents the percent of error of b
     */
    private static double error(double a, double b) {
        final int PERCENT = 100;
        return (abs(a - b) / a) * PERCENT;
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

        final double[] EXP = {-5, -4, -3, -2, -1, (double) -1/2, (double) -1/3, (double) -1/4, 0, (double) 1/4, (double) 1/3, (double) 1/2, 1, 2, 3, 4, 5};

        out.println("Charming Theory:");
        out.println("For the value u: ");
        double u = getPositiveDouble(in, out);
        out.println("For the value w: ");
        double w = getPositiveDoubleNotOne(in, out);
        out.println("For the value x: ");
        double x = getPositiveDoubleNotOne(in, out);
        out.println("For the value y: ");
        double y = getPositiveDoubleNotOne(in, out);
        out.println("For the value z: ");
        double z = getPositiveDoubleNotOne(in, out);

        //variables to hold exponents
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        double d = 0.0;

        double closest = pow(w, EXP[0]) * pow(x, (double) EXP[0]) * pow(y, (double) EXP[0]) * pow(z, EXP[0]);

        //algorithm for finding best group of exponents
        //to produce the closest value
        for (int i = 0; i < EXP.length; i++) {
            for (int j = 0; j < EXP.length; j++) {
                for (int k = 0; k < EXP.length; k++) {
                    for (int m = 0; m < EXP.length; m++) {
                        double temp = pow(w, (double) EXP[i]) * pow(x, (double) EXP[j]) * pow(y, (double) EXP[k]) * pow(z, (double) EXP[m]);
                        if (abs(u - temp) < abs(u - closest)) {
                            a = EXP[i];
                            b = EXP[j];
                            c = EXP[k];
                            d = EXP[m];
                            closest = temp;
                        }
                    }
                }
            }
        }

        //calculate the error and display results
        double err = error(u, closest);
        out.print("The values of the exponents are: ");
        out.println(a + " " + b + " " + c + " " + d);
        out.print("The approximated value is about: ");
        out.println(closest);
        out.print("The percent of error is: ");
        out.println(err, 2, false);

        in.close();
        out.close();
    }

}
