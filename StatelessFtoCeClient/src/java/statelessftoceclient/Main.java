package statelessftoceclient;

import java.util.Scanner;
import mybean.AddbeanRemote;

/**
 *
 * @author Exia
 */
public class Main {

    @javax.ejb.EJB
    private static AddbeanRemote addbean;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter degree in Fahrenheit: ");
        double fahrenheit = sc.nextDouble();
        double celsius = addbean.add(fahrenheit);
        // Format the result to display only two decimal places
        String formateResultFa = String.format("%.2f", fahrenheit);
        String formateResultCe = String.format("%.2f", celsius);
        System.out.println(formateResultFa + " Fahrenheit = " + formateResultCe + " Celsius");

    }

}
