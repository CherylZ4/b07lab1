import java.io.*;
import java.util.Arrays;


public class Polynomial{
    public double[] coefficients;
    public int[] exponents;

    public Polynomial(File file) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(file));
        String text = input. readLine();
        String[] split = text.split("[+-]");
        this.coefficients = new double[split.length];
        this.exponents = new int[split.length];

        for (int i=0; i < split.length; i++){
            if (split[i].length()==1){
                this.coefficients[i] = Double.parseDouble(split[i]);
                this.exponents[i] = 0;
            }else{
                this.coefficients[i] = Double.parseDouble(split[i].substring(0,split[i].indexOf("x")));
                this.exponents[i] = Integer.parseInt(split[i].substring(split[i].indexOf("x")+1));

            }
        }


    }

    public Polynomial(){
        this.coefficients = new double[] {0};
        this.exponents = new int[] {0};

    }

    public Polynomial(double[] coefficients,int[] exponents) {
        this.coefficients = coefficients;
        this.exponents = exponents;

    }

    public void print(){
        System.out.println("check this: " + Arrays.toString(this.coefficients));
        System.out.println(Arrays.toString(this.exponents));
    }

    public  Polynomial add(Polynomial polynomial){
            int largest_expo = 0;

            for (int i=0; i < polynomial.exponents.length; i++){
                if (polynomial.exponents[i] >largest_expo){
                    largest_expo = polynomial.exponents[i];
                }
            }

        for (int i=0; i < this.exponents.length; i++){
            if (this.exponents[i] >largest_expo){
                largest_expo = this.exponents[i];
            }
        }

        double[] new_coeff  = new double[largest_expo + 1];
        int[] new_exp = new int[largest_expo + 1];

        for (int i =0; i < largest_expo + 1; i++){
            new_coeff[i] = 0;
            new_exp[i] =i;
        }


        for (int i=0; i < polynomial.exponents.length; i++){
            new_coeff[polynomial.exponents[i]] += polynomial.coefficients[i];
        }

        for (int i=0; i < this.exponents.length; i++){
             new_coeff[this.exponents[i]] += this.coefficients[i];
        }

        int nonZeroNums = 0;

        for (int i=0; i < new_coeff.length; i++){
            if (new_coeff[i] !=0){
                nonZeroNums++;
            }
        }
        double[] new_coefficients  = new double[nonZeroNums];
        int[] new_exponents  = new int[nonZeroNums];

        int j=0;

        for (int i=0; i < new_coeff.length; i++){
            if (new_coeff[i] !=0){
                new_coefficients[j] = new_coeff[i];
                new_exponents[j] = new_exp[i];
                j++;
            }
        }


        Polynomial addedPolynomial = new Polynomial(new_coefficients, new_exponents);


        return addedPolynomial;

    }

    public Polynomial multiply(Polynomial polynomial){
        Polynomial sum = new Polynomial(new double[] {0}, new int[] {0});
        for (int i=0; i < polynomial.coefficients.length;i++){
            for (int j=0; j < this.coefficients.length;j++){
                int[] e = new int[1];
                double[] c = new double[1];
                c[0] = polynomial.coefficients[i] * this.coefficients[i];
                e[0] = polynomial.exponents[i] + this.exponents[i];
                Polynomial temp = new Polynomial(c,e);
                sum = sum.add(temp);
            }
        }

        return sum;
    }

    public void saveToFile(String file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (int i=0; i < this.coefficients.length; i++){
            if (coefficients[i] !=1 && coefficients[i] !=-1){
                if (i ==0 && coefficients[i] > 0){
                    writer.write(Double.toString(coefficients[i]));
                }else{
                    writer.write("+" + coefficients[i]);
                }
            }
            if(coefficients[i] ==-2){
                writer.write("-");
            }
            if (exponents[i] !=0){
                writer.write( "x" + exponents[i]);
            }
        }

        writer.close();

    }


    public  double evaluate(double x){
        double total = 0;
        for (int i=0; i < coefficients.length; i++){
            total += coefficients[i] * Math.pow(x,exponents[i]);
        }
        return total;
    }
    public boolean hasRoot(double potentialRoot){
        double total = evaluate(potentialRoot);

        return total == 0;
    }


}