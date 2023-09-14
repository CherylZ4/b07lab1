
public class Polynomial{
    public double[] coefficients;

    public Polynomial(){
        this.coefficients = new double[]{0};
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public  Polynomial add(Polynomial polynomial){
        int longer;
        int shorter;
        double[] longerArray;

        if (polynomial.coefficients.length <  this.coefficients.length){
            longer = this.coefficients.length;
            shorter = polynomial.coefficients.length;
            longerArray = this.coefficients;
        }else{
            longer = polynomial.coefficients.length;
            shorter = this.coefficients.length;
            longerArray = polynomial.coefficients;
        }

        double added[] = new double[longer];

        int i;
        for (i =0; i < shorter; i++) {
            added[i] = polynomial.coefficients[i] + this.coefficients[i];
        }

        int diff = longer - i;

        for (int j=i; j < i+diff; j++){
            added[j] = longerArray[j];
        }

        Polynomial addedPolynomial = new Polynomial(added);

        return addedPolynomial;

    }




    public  double evaluate(double x){
        double total = 0;
        for (int i=0; i < coefficients.length; i++){
            total += coefficients[i] * Math.pow(x,i);
        }
        return total;
    }
    public boolean hasRoot(double potentialRoot){
        double total = evaluate(potentialRoot);

        return total == 0;
    }


}