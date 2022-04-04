package pl.edu.pjatk;

import java.util.Arrays;
import java.util.HashMap;

public class Perceptron {
//    spr czy wynik z greaterOrLessThenTheta jest taki sam jak d? jesli nie to zmieniaj parametry
//    wektor wag i theta inicjalizowac losowymi liczbami > 0, ale małe (mniej niż 1)
//    warto dodać normalizację wektorów czyli po każdej zmianie wektor wag ma dł 1
//    weights = lengthOfWeightVectorToOne();
//    wektor wag potrzebny jest tylko do kierunków, odległością zajmuje się próg
//    żeby znormalizować wektor trzeba podzielić każdą współrzędną przez długość wektora
    private final double alpha;
    private double theta; // atrybut
    private double [] weights; // atrybut
    private HashMap<Double[], String> vectorXWithAnswer;

    public Perceptron(double alpha, double theta, HashMap<Double[], String> vectorXWithAnswer, double [] weights) {
        this.alpha = alpha;
        this.theta = theta;
        this.vectorXWithAnswer = vectorXWithAnswer;
        this.weights = weights;
    }


    public int scalar(Double [] x){
        //iloczyn skalarny
        double y = 0.0;
        if(x.length != weights.length)
            throw new NullPointerException();
        for(int i = 0; i < weights.length; i++){
            y += weights[i] * x[i];
        }
        if(y >= theta)
            return 1;
        else
            return 0;
    }

    private int learn(){
        int numberOfGoodPredictions = 0;
        for(Double[] x : vectorXWithAnswer.keySet()){
            int y = scalar(x);
            int d = Integer.parseInt(vectorXWithAnswer.get(x));
            System.out.println("vector x: " + x[0] + ", " + x[1] + /*", " + x[0] + ", " + x[0] +*/ ", " + " odpowiedz: " + d + "\nperceptron: " + y);
            if(y != d){
                if(d == 1)
                    theta -= (theta * 0.01);
                else
                    theta += (theta * 0.01);
            }else
                numberOfGoodPredictions++;

            for(int i = 0; i < weights.length; i++){
                weights[i] += (d - y) * alpha * x[i];
            }
            //lengthOfWeightVectorToOne();
        }
        return numberOfGoodPredictions;
    }
    public boolean train(double expectedAccuracy){
        double accuracy = 0.0;
        int numberOfGoodPredictions;
        int counter = 0;
        boolean flag = true;

        while(accuracy < expectedAccuracy){
            if(counter > 500_000){
                flag = false;
                break;
            }
            numberOfGoodPredictions = learn();
            accuracy = (numberOfGoodPredictions / (double) vectorXWithAnswer.size()) * 100;
            System.out.println("Proba nr " + counter + "\n"
                                + "dokladnosc: " + (accuracy) + "%");
            counter++;
        }

        System.out.println();

        return  flag;
    }
    public void test(){
        lengthOfWeightVectorToOne();
        double numberOfGoodPredictions = 0.0;
        boolean flag;
        for(Double [] keys : vectorXWithAnswer.keySet()) {
            flag = false;
            int y = scalar(keys);
            int d = Integer.parseInt(vectorXWithAnswer.get(keys));
            if(y == d) {
                numberOfGoodPredictions++;
                flag = true;
            }

            for(Double values : keys)
                System.out.println(values + " ");

            System.out.println("Odpowiedz poprawna: " + vectorXWithAnswer.get(keys));
            System.out.println("Odpowiedz perceptronu: " + y);
            System.out.println("Czy porpawna: " + flag);
            System.out.println("************************************");
        }
        double accuracy = numberOfGoodPredictions / vectorXWithAnswer.size();
        System.out.println("Dokladnosc jaką osiągnieto: " + accuracy * 100);

    }

    private double lengthOfVector(){
        double length = 0;
        for(int i = 0; i < weights.length; i++){
            length += Math.pow(weights[i], 2);
        }
        return Math.sqrt(length);
    }
    private void lengthOfWeightVectorToOne(){
        double length = lengthOfVector();
        if(length != 1.0){
            for(int i = 0; i < weights.length; i++){
                weights[i] = weights[i] / length;
            }
        }
        System.out.println(length);
    }

    public void setVectorXWithAnswer(HashMap<Double[], String> vectorXWithAnswer) {
        this.vectorXWithAnswer = vectorXWithAnswer;
    }

    @Override
    public String toString() {
        return "Perceptron{" + "\n" +
                "alpha=" + alpha + "\n" +
                "theta=" + theta + "\n" +
                "weights=" + Arrays.toString(weights) + "\n" +
                '}';
    }

}
