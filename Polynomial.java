import java.io.*;
import java.util.*;
public class Polynomial{
	public double[] coefficients;
	public int[] degrees;
	// maximum size for array
	public final int SIZE;
	public Polynomial(){
		SIZE = 0;
		coefficients = null;
		degrees = null;
	}
	public Polynomial(double[] coefficients, int[] degrees){
		this.SIZE = coefficients.length;
		this.coefficients = coefficients;
		this.degrees = degrees;
	}
	public Polynomial(Polynomial p){
		this.SIZE = p.SIZE;
		this.coefficients = p.coefficients;
		this.degrees = p.degrees;
	}
	private Polynomial strip_zeroes(double[] coefficients){
		int count = 0;
		for(double i : coefficients){
			if(i != 0.0) ++count;
		}
		int[] new_degrees = new int[count];
		double[] new_coefficients = new double[count];
		int j = 0;
		for(int i = 0; i<coefficients.length; i++){
			if(coefficients[i] != 0.0){
				new_degrees[j] = i;
				new_coefficients[j] = coefficients[i];
				j++;
			}
		}
		return new Polynomial(new_coefficients, new_degrees);
	}
	public Polynomial(File f){
		double[] coefficient = new double[1000];
		for(int i = 0; i<1000; i++){
			coefficient[i] = 0;
		}
		try{
			Scanner src = new Scanner(f);
			String str = src.nextLine();
			String[] data = str.split("((?=[+-]))");
			for(String i : data){
				String[] str2 = i.split("x");
				double co = Double.parseDouble(str2[0]);
				if(str2.length == 2){
					int deg = Integer.parseInt(str2[1]);
					coefficient[deg] = co;
				}
				else{
					coefficient[0] = co;
				}
			}
		}
	       	catch(FileNotFoundException e){
			System.out.println("Cannot find file. Try again.");
		}
		catch(java.lang.NumberFormatException e){
			System.out.println("Improper formatting!");
		}
		catch(java.lang.SecurityException e){
			System.out.println("Access denied!");
		}
		catch(Exception e){
			System.out.println("That's not supposed to happen.");
			e.printStackTrace();
		}
		Polynomial p = this.strip_zeroes(coefficient);
		this.coefficients = p.coefficients;
		this.degrees = p.degrees;
		this.SIZE = p.SIZE;
	}
	private int max_degree(Polynomial p){	
		// find max degree of a polynomial
		int max_deg = -1;
		for(int i : p.degrees){
			if(i > max_deg) max_deg = i;
		}
		return max_deg;
	}
	public Polynomial add(Polynomial p){
		int max_deg = max_degree(p) > max_degree(this) ? max_degree(p) : max_degree(this);
		// Create coefficent array
		double[] new_coefficients = new double[max_deg+1];
		for(int i = 0; i<max_deg; i++){
			new_coefficients[i] = 0.0;
		}
		// Add coefficients from each polynomial
		for(int i = 0; i<this.SIZE; i++){
			new_coefficients[this.degrees[i]] += this.coefficients[i];
		}
		for(int i = 0; i<p.SIZE; i++){
			new_coefficients[p.degrees[i]] += p.coefficients[i];
		}
		return strip_zeroes(new_coefficients);	
	}
	public Polynomial multiply(Polynomial p){
		int max_deg1 = max_degree(p);
		int max_deg2 = max_degree(this);
		double[] new_coefficients = new double[max_deg1+max_deg2+1];
		for(int i = 0; i<=(max_deg1+max_deg2); i++) new_coefficients[i] = 0;
		for(int i = 0; i<this.SIZE; i++){
			for(int j = 0; j<p.SIZE; j++){
				int degree = this.degrees[i] + p.degrees[j];
				new_coefficients[degree] += (this.coefficients[i] * p.coefficients[j]);
			}
		}
		return strip_zeroes(new_coefficients);
	}
	public double evaluate(double x){
		double total = 0;
		for(int i = 0; i<SIZE; i++){
			double term = 1;
			for(int j = 0; j<coefficients[i]; j++){
				term *= x;
			}
			term = term * this.coefficients[i];
			total += term;
		}
		return total;
	}
	public boolean hasRoot(double x) {
		return evaluate(x) == 0.0;
	}
	public void saveToFile(String file_name){
		try{
			FileWriter f = new FileWriter(file_name);
			f.write(""+coefficients[0]);
			if(degrees[0] != 0) f.write("x"+degrees[0]);
			for(int i = 1; i<this.SIZE; i++){
				if(degrees[i] == 0){
					f.write((coefficients[i]>0 ? "+" : "")+coefficients[i]);
					continue;
				}
				if(coefficients[i] > 0) f.write("+"+coefficients[i]+"x"+degrees[i]);
				else f.write(""+coefficients[i]+"x"+degrees[i]);
			}
			f.close();
		}
		catch(IOException e){
			System.out.println("File does not exist and could not be created.");
		}
		catch(Exception e){
			System.out.println("Something happened.");
			e.printStackTrace();
		}
	}
}
