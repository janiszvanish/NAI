package pl.edu.pjatk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.function.DoubleBinaryOperator;

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
    private HashMap<TreeMap<Character, Double>, String>vectorXWithAnswer;
    private final String language;

    public Perceptron(double alpha, double theta, HashMap<TreeMap<Character, Double>, String> vectorXWithAnswer,
                      double [] weights, String nameOfLanguage) {
        this.alpha = alpha;
        this.theta = theta;
        this.vectorXWithAnswer = vectorXWithAnswer;
        this.weights = weights;
        this.language = nameOfLanguage;
    }

    public void train(TreeMap<Character, Double> textToCheck){
        Double scalarX = 0.0;
        Double scalarTxtToCheck = 0.0;
        Double [] vectorTxtToChceck = new Double[textToCheck.size()];
        Double [] vectorX = new Double[vectorTxtToChceck.length];
        int index = 0;
        for(Character c : textToCheck.keySet()) {
            vectorTxtToChceck[index++] = textToCheck.get(c);
        }
        scalarTxtToCheck = scalar(vectorTxtToChceck);
        index = 0;
        for(TreeMap<Character, Double> tm : vectorXWithAnswer.keySet()){
            for(Character c : tm.keySet())
                vectorX[index++] = tm.get(c);
            scalarX = scalar(vectorX);
        }

    }

    public double scalar(Double [] x){
        //iloczyn skalarny
        double y = 0.0;
        if(x.length != weights.length)
            throw new NullPointerException();
        for(int i = 0; i < weights.length; i++){
            y += weights[i] * x[i];
        }
        return y;
    }

    public int isScalarHigherThenTheta(Double [] x){
        double y = scalar(x);
        if(y >= theta)
            return 1;
        else
            return 0;
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

    @Override
    public String toString() {
        String text = "";
        for(TreeMap<Character, Double> v : vectorXWithAnswer.keySet()){
            text += "\n" + v + "\n" + vectorXWithAnswer.get(v);

        }
        return "Perceptron{" +
                "alpha=" + alpha +
                ", theta=" + theta +
                ", weights=" + Arrays.toString(weights) +
                ", vectorXWithAnswer=" + text +
                ", language='" + language + '\'' +
                '}';
    }
}
