public class Polynomial{
	double[] coefficients;
	// maximum size for array
	private final int MAX_SIZE;
	public Polynomial(){
		MAX_SIZE = 100;
		coefficients = new double[MAX_SIZE];
		for(int i = 0; i<MAX_SIZE; i++){
			coefficients[i] = 0;
		}
	}
	public Polynomial(double[] coefficients){
		MAX_SIZE = coefficients.length;
		this.coefficients = coefficients;
	}
	public Polynomial add(Polynomial p){
		if(p.coefficients.length >= MAX_SIZE){
			for(int i = 0; i<MAX_SIZE; i++){
				p.coefficients[i] += this.coefficients[i];
			}
			return p;
		}
		else{
			for(int i = 0; i<p.coefficients.length; i++){
				this.coefficients[i] += p.coefficients[i];
			}
		}
		return this;
		
	}
	public double evaluate(double x){
		double total = 0;
		for(int i = 0; i<MAX_SIZE; i++){
			double term = 1;
			for(int j = 0; j<i; j++){
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
}
