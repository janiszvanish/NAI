package pl.edu.pjatk;

import java.util.Arrays;

public class Flower {
    private double [] measurements;
    private String species;

    public Flower(double[] measurements, String species) {
        this.measurements = measurements;
        this.species = species;

    }

    public Flower(double[] measurements) {
        this.measurements = measurements;
    }

    public double[] getMeasurements() {
        return measurements;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "measurements=" + Arrays.toString(measurements) +
                ", species='" + species + '\'' +
                '}';
    }
}

