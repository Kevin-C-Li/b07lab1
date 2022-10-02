import java.io.*;
import java.util.*;
public class Driver { 
 public static void main(String [] args) { 
  Polynomial p = new Polynomial();
  File f = new File("test_poly.txt");
  Polynomial file_polynomial = new Polynomial(f);
  for(int i = 0;i<file_polynomial.SIZE; i++){
    System.out.println(file_polynomial.coefficients[i] + "\t" + file_polynomial.degrees[i]);
  } 
  System.out.println(p.evaluate(3)); 
  double [] c1 = {6,5};
  int[] d1 = {0,3}; 
  Polynomial p1 = new Polynomial(c1, d1); 
  double [] c2 = {4, -2, -9, 5}; 
  int[] d2 = {1,3,4, 0};
  Polynomial p2 = new Polynomial(c2, d2); 
  Polynomial s = p1.add(p2); 
  System.out.println("s(0.1) = " + s.evaluate(0.1));
  s.saveToFile("output.txt");
  Polynomial t = p1.multiply(p2);
  System.out.println("");
  for(int i = 0; i<t.SIZE; i++){
    System.out.println(t.coefficients[i] + "\t" + t.degrees[i]);
  }
  int[] d3 = {0,1,2};
  double[] c3 = {1,2,1}; 
  Polynomial u = new Polynomial(c3, d3);
  System.out.println();
  if(u.hasRoot(-1)) 
   System.out.println("1 is a root of s"); 
  else 
   System.out.println("1 is not a root of s"); 
 } 
} 
