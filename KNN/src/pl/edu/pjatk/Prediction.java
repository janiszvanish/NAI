package pl.edu.pjatk;

import java.util.*;
import java.util.stream.Collectors;

public class Prediction {

    public static void predict(List<Flower> testingSet, List<Flower> trainingSet, int k, boolean flag) {
        String prediction = "";
        double testListSize = testingSet.size();
        double numberOfGoodGuessed = testListSize;

        List<Vector> vectors;
        Vector vector;
        for (Flower testing : testingSet) {
            vectors = new ArrayList<>();
            //System.out.println("Testowy kwiatek\n" + testing);
            int numberOfSetosa = 0;
            int numberOfVirginica = 0;
            int numberOfVersicolor = 0;

            for (Flower training : trainingSet) {
                vector = new Vector(Vector.calcDistanceBetweenFlowers(training, testing), training.getSpecies());
                vectors.add(vector);
            }

            Collections.sort(vectors, Comparator.comparingDouble(Vector::getDistance));
            vectors = vectors.stream().limit(k).collect(Collectors.toList());

            for (Vector v : vectors) {
                //System.out.println(v);
                if (v.getSpecies().equals("Iris-virginica"))
                    numberOfVirginica++;
                else if (v.getSpecies().equals("Iris-versicolor"))
                    numberOfVersicolor++;
                else
                    numberOfSetosa++;
            }

            if (numberOfVersicolor >= numberOfSetosa && numberOfVersicolor >= numberOfVirginica)
                prediction = "Iris-versicolor";
            else if (numberOfSetosa >= numberOfVirginica && numberOfSetosa >= numberOfVirginica)
                prediction = "Iris-setosa";
            else
                prediction = "Iris-virginica";

            if (!prediction.equals(testing.getSpecies()))
                numberOfGoodGuessed--;

            System.out.println("Predicted species: " + prediction + "\n");
        }
        if(flag){
            System.out.println("Accuracy = " + (numberOfGoodGuessed / testListSize * 100) + "%");
        }

    }
}
