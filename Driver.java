import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String [] args) throws IOException {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {4,5,6};
        int [] e1 = {0,3,2};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {-3,10};
        int [] e2 = { 1, 2};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s = p2.add(p1);
        s.print();
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        double[] c3 = {5,3,2};
        int[] e3 = {5,1,0};
        Polynomial p3 = new Polynomial(c3, e3);
        double[] c4 = {4,0};
        int[] e4 = {2,4};
        Polynomial p4 = new Polynomial(c4, e4);
        Polynomial answer = p3.multiply(p4);
        for (int i = 0; i < answer.coefficients.length; i++){
            System.out.println("mult co: " + answer.coefficients[i] + " ex: " + answer.exponents[i]);
        }
//
        File f = new File("/Users/cherylz/b07lab1/polynomial.txt");
        Polynomial p5 = new Polynomial(f);
        for (int i = 0; i < p5.coefficients.length; i++){
            System.out.println("co: " + p5.coefficients[i] + " ex: " + p5.exponents[i]);
        }
//
        p1.saveToFile("/Users/cherylz/b07lab1/testpoly.txt");
   }
}