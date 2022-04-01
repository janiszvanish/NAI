package pl.edu.pjatk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Perceptron {
//    spr czy wynik z greaterOrLessThenTheta jest taki sam jak d? jesli nie to zmieniaj parametry
//    wektor wag i theta inicjalizowac losowymi liczbami > 0, ale małe (mniej niż 1)
//    warto dodać normalizację wektorów czyli po każdej zmianie wektor wag ma dł 1
//    weights = lengthOfWeightVectorToOne();
//    wektor wag potrzebny jest tylko do kierunków, odległością zajmuje się próg
//    żeby znormalizować wektor trzeba podzielić każdą współrzędną przez długość wektora
    private double alpha;
    private double theta; // atrybut
    private double [] weights; // atrybut
    private HashMap<Double[], String> vectorXWithAnswer;

    public Perceptron(double alpha, double theta, HashMap<Double[], String> vectorXWithAnswer, double [] weights) {
        this.alpha = alpha;
        this.theta = theta;
        this.vectorXWithAnswer = vectorXWithAnswer;
        this.weights = weights;
    }


    public int greaterOrLessThenTheta(Double [] x){
        //iloczyn skalarny
        double y = 0.0;
        if(x.length != weights.length)
            throw new NullPointerException();
        for(int i = 0; i < weights.length; i++){
            y += this.weights[i] * x[i];
        }
        if(y >= this.theta)
            return 1;
        else
            return 0;
    }

    private int learn(){
        int numberOfGoodPredictions = 0;
        for(Double[] x : vectorXWithAnswer.keySet()){
            int y = greaterOrLessThenTheta(x);
            int d = Integer.parseInt(vectorXWithAnswer.get(x));

//            if(y != d){
//                if(d == 1)
//                    this.theta *= (-0.05);
//                else
//                    this.theta *= 0.05;
//            }else
            if(d == y)
                numberOfGoodPredictions++;

            for(int i = 0; i < weights.length; i++){
                weights[i] += (d - y) * alpha * x[i];
            }
            lengthOfWeightVectorToOne();
        }
        return numberOfGoodPredictions;
    }
    public void train(double expectedAccuracy){
        double accuracy = 0.0;
        int numberOfGoodPredictions;
        int counter = 0;
        while(accuracy < expectedAccuracy){
            if(counter > 1000)
                break;
            numberOfGoodPredictions = learn();
            accuracy = (numberOfGoodPredictions / (double) vectorXWithAnswer.size()) * 100;
            System.out.println("Proba nr " + counter + "\n"
                                + "dokladnosc: " + (accuracy) + "%");
            counter++;
        }
        System.out.println("Udalo sie wytrenowac perceptron:");
        System.out.println("\n" + toString());

    }

    private void lengthOfWeightVectorToOne(){
        double length = 0;
        for(int i = 0; i < weights.length; i++){
            length += Math.pow(weights[i], 2);
        }
        length = Math.sqrt(length);

        for(int i = 0; i < weights.length; i++){
            weights[i] = weights[i] / length;
        }
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
