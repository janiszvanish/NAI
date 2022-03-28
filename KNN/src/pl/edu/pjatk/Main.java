package pl.edu.pjatk;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
//        Wykomentowane bo szybciej jest mi używać statycznej ścieżki itd.
//
//        System.out.println("Podaj lokalizacje plików");
//        String path = scanner.nextLine();
//        System.out.println("Podaj nazwe pliku testowego");
//        String testFileName = scanner.nextLine();
//        System.out.println("Podaj nazwe pliku treningowego");
//        String trainingFileName = scanner.nextLine();

        System.out.println("Podaj k:");
        int k = scanner.nextInt();

        String path = "D:\\studia\\2021-2022\\letni\\NAI\\Labolatoria\\KNN\\src\\data";
        String testFileName = "test.txt";
        String trainingFileName = "train.txt";

        DataProvider dataProvider = new DataProvider(trainingFileName, testFileName, path);

        int dimensions = dataProvider.howManyDimensions(dataProvider.getTrainingFile());

        List<Flower> trainingSet = dataProvider.extractDataWithSpecies(dataProvider.getTrainingFile());
        List<Flower> testingSet = dataProvider.extractDataWithSpecies(dataProvider.getTestingFile());

        Prediction.predict(testingSet, trainingSet, k, true);

        System.out.println("Jeśli chcesz wprowadzic samemu dane kwiata wpisz t/T jesli nie, wcisnij dowolny klawisz");
        boolean answer = readKey();
        while(answer){
            System.out.println("Wprowadź " + dimensions + " wartosci dlugosci lisci i platkow po enterze");

            double [] flowerMeasurement = new double[dimensions];
            for(int i = 0; i < flowerMeasurement.length; i++){
                flowerMeasurement[i] = scanner.nextDouble();
            }

//            System.out.println("Jaki przewidujesz rodzaj kwiatka?" +
//                    "\nWpisz pelna nazwe" +
//                    "\nIris-setosa" +
//                    "\nIris-versicolor" +
//                    "\nIris-virginica");
//
//            String species = scanner.next();
//
//            if(!(species.equals("Iris-setosa") || species.equals("Iris-versicolor") || species.equals("Iris-virginica"))){
//                System.out.println("Niepoprawna nazwa gatunku jesli chcesz zaczac od nowa wpisz t");
//                answer = readKey();
//                if(!answer)
//                    break;
//            }

            List<Flower> checkFlower = new ArrayList<>();
            checkFlower.add(new Flower(flowerMeasurement));
            System.out.println("Podaj k:");
            k = scanner.nextInt();
            Prediction.predict(checkFlower, trainingSet, k, false);

            System.out.println("Czy chcesz jeszcze raz? (wcisij t)");
            answer = readKey();
        }
    }
    public static boolean readKey(){
        Scanner scanner = new Scanner(System.in);
        String answer;
        answer = String.valueOf(scanner.next().charAt(0));
        answer = answer.toLowerCase();

        if(answer.equals("t"))
            return true;
        else
            return false;
    }
}
