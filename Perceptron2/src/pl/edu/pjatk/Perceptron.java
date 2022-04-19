package pl.edu.pjatk;

import java.util.ArrayList;
import java.util.List;

public class Perceptron {
    List<Double> weights = new ArrayList<>();

    public Perceptron(int dimensions) {

        for (int i = 0; i <= dimensions; i++) {
            weights.add(0.0);
        }
    }

    public int train(Double[] vector, int d, double alpha) {
        int numberOfGoodPrediction = 0;

        List<Double> w = new ArrayList<>();

        double length = 0;

        int y = isGreaterThenTheta(vector);
        if(y == d)
            numberOfGoodPrediction++;
        for (int i = 0; i < weights.size() - 1; i++) {
            double temp = weights.get(i) + (d - y) * alpha * vector[i];
            w.add(temp);
            length += temp * temp;
        }
        double temp = weights.get(weights.size() - 1) + (d - y) * alpha * -1.0;
        w.add(temp);
        length += temp * temp;
        weights = w;

        if (length != 0) {
            length = Math.sqrt(length);
            for (int i = 0; i < weights.size(); i++) {
                weights.set(i, weights.get(i) / length);
            }
        }
        return numberOfGoodPrediction;
    }

    public int isGreaterThenTheta(Double... vector) {
        double w = 0;

        for (int i = 0; i < weights.size() - 1; i++) {
            w += weights.get(i) * vector[i];
        }
        w += weights.get(weights.size() - 1) * -1.0;
        return w >= 0 ? 1 : 0;
    }
}
