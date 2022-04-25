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
    private HashMap<Integer, Double> maxAccuracy = new HashMap<>();

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
        return y >= theta ? 1 : 0;
    }

    private int learn(){
        int numberOfGoodPredictions = 0;
        for(Double[] x : vectorXWithAnswer.keySet()){
            int y = scalar(x);
            int d = Integer.parseInt(vectorXWithAnswer.get(x));

            if(y == d) {
                numberOfGoodPredictions++;
            }

            for (int i = 0; i < weights.length; i++) {
                weights[i] += (d - y) * alpha * x[i];
                theta += (d - y) * alpha * x[i];
                //theta -= (d-y) * alpha;
            }
            //lengthOfWeightVectorToOne();

        }
        return numberOfGoodPredictions;
    }
    public boolean train(double expectedAccuracy){
        int counterForMap = 0;
        double accuracy = 0.0;
        int numberOfGoodPredictions;
        int counter = 0;
        boolean flag = true;

        while(accuracy < expectedAccuracy){
            if(counter > 10_000){
                flag = false;
                break;
            }
            numberOfGoodPredictions = learn();
            accuracy = (numberOfGoodPredictions / (double) vectorXWithAnswer.size()) * 100;
            System.out.println("Proba nr " + ++counter + "\n"
                                + "dokladnosc: " + (accuracy) + "%");
            //counter++;
            if(maxAccuracy.isEmpty()){
                maxAccuracy.put(counter, accuracy);
                counterForMap = counter;
            }else{
                if(maxAccuracy.get(counterForMap) <= accuracy){
                    maxAccuracy.remove(counterForMap);
                    maxAccuracy.put(counter, accuracy);
                    counterForMap = counter;
                }
            }
//            if(counter % 10 == 0){
//                lengthOfWeightVectorToOne();
//                System.out.println(lengthOfVector());
//            }
        }
        //lengthOfWeightVectorToOne();
        System.out.println();

        return flag;
    }

    public HashMap<Integer, Double> getMaxAccuracy() {
        return maxAccuracy;
    }

    public void test(){
//        lengthOfWeightVectorToOne();
        double numberOfGoodPredictions = 0.0;
        for(Double [] keys : vectorXWithAnswer.keySet()) {
            int y = scalar(keys);
            int d = Integer.parseInt(vectorXWithAnswer.get(keys));
            if(y == d) {
                numberOfGoodPredictions++;
            }
//
//            System.out.println("Odpowiedz poprawna: " + vectorXWithAnswer.get(keys));
//            System.out.println("Odpowiedz perceptronu: " + y);
//            System.out.println("Czy porpawna: " + flag);
//            System.out.println("************************************");
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
        //System.out.println(length);
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
