package pl.edu.pjatk;

public class Vector {
    private double distance;
    private String species;

    public Vector(double distance, String predictedSpecies) {
        this.distance = distance;
        this.species = predictedSpecies;
    }

    public static double calcDistanceBetweenFlowers(Flower train, Flower test){
        double distance = 0;
        double [] firstFlower, secondFlower;
        firstFlower = train.getMeasurements();
        secondFlower = test.getMeasurements();
        if(firstFlower.length == secondFlower.length){
            for(int i = 0; i < firstFlower.length; i++){
                distance += Math.pow((firstFlower[i] - secondFlower[i]), 2);
            }
        }

        return distance;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "distance=" + distance +
                ", species='" + species + '\'' +
                '}';
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getDistance() {
        return distance;
    }

    public String getSpecies() {
        return species;
    }
}
