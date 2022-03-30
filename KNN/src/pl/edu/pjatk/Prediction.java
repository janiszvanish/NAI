package pl.edu.pjatk;

import java.util.*;
import java.util.stream.Collectors;

public class Prediction {

    public static void predict(List<Flower> testingSet, List<Flower> trainingSet, int k, boolean flag,
                               DataProvider dataProvider) {
        String prediction = "";
        double testListSize = testingSet.size();
        double numberOfGoodGuessed = testListSize;

        List<Vector> vectors;
        Vector vector;
        HashMap<String, Integer> mapOfSpeciesToCount = dataProvider.getMapOfSpeciesToCount();
        for (Flower testing : testingSet) {
            vectors = new ArrayList<>();
            System.out.println("Testowy kwiatek\n" + testing);

            mapOfSpeciesToCount = dataProvider.makeEvryValue0(mapOfSpeciesToCount);

            for (Flower training : trainingSet) {
                vector = new Vector(Vector.calcDistanceBetweenFlowers(training, testing), training.getSpecies());
                vectors.add(vector);
            }

            Collections.sort(vectors, Comparator.comparingDouble(Vector::getDistance));
            vectors = vectors.stream().limit(k).collect(Collectors.toList());

            for (Vector v : vectors) {
                int value = mapOfSpeciesToCount.get(v.getSpecies());
                value++;
                mapOfSpeciesToCount.replace(v.getSpecies(), value);

            }

            System.out.println(k + " najblizszych sasiadow " + mapOfSpeciesToCount);

            int maxValue = 0;

            for(String s : mapOfSpeciesToCount.keySet()){
                if(mapOfSpeciesToCount.get(s).intValue() >= maxValue){
                    prediction = s;
                    maxValue = mapOfSpeciesToCount.get(s).intValue();
                }
            }
            if (!prediction.equals(testing.getSpecies()))
                    numberOfGoodGuessed--;

            System.out.println("Predicted species: " + prediction + "\n");
        }
        if(flag){
            System.out.println("Accuracy = " + (numberOfGoodGuessed / testListSize * 100) + "%");
        }

    }


}
