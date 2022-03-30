package pl.edu.pjatk;

public class Perceptron {
    int alpha;
    int theta;
    double [] data;
    int dimensions;

    public Perceptron(int alpha, int theta, double[] data, int dimensions) {
        this.alpha = alpha;
        this.theta = theta;
        this.dimensions = dimensions;
        this.data = data;
    }
}
