package pl.edu.pjatk;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/*
*
*           example1 nie jest liniowo separowalny, ciężko o lepszą dokładność niż 70-75%
*           reszta przy kozystnym wylosowaniu wag jest w stanie nauczyc się do 98% dokladnosci
*
* */
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
        System.out.println("Podaj parametr uczenia z przedziału");
        double alpha = scanner.nextDouble();
        System.out.println("Podaj próg aktywacji z przedziału");
        double theta = scanner.nextDouble();
        System.out.println("Podaj oczekiwana dokladnosc");
        double expectedAccuracy = scanner.nextDouble();

        System.out.println("Wpisz cyfre na ktorych danych chcesz pracowac\n1 - example1\n2 - iris");
        int choice = scanner.nextInt();
        String path = "";
        String testFileName = "test.txt";
        String trainingFileName = "";
        boolean flag = true;
        while(flag){
            switch(choice){
                case 1:
                    path = "D:\\studia\\2021-2022\\letni\\NAI\\Labolatoria\\Perceptron\\src\\data\\example1";
                    trainingFileName = "train.txt";
                    flag = false;
                    break;
                case 2:
                    path = "D:\\studia\\2021-2022\\letni\\NAI\\Labolatoria\\Perceptron\\src\\data\\iris_perceptron";
                    trainingFileName = "training.txt";
                    flag = false;
                    break;
                case 3:
                    path = "D:\\studia\\2021-2022\\letni\\NAI\\Labolatoria\\Perceptron\\src\\data\\example2";
                    trainingFileName = "train.txt";
                    flag = false;
                    break;
                case 4:
                    path = "D:\\studia\\2021-2022\\letni\\NAI\\Labolatoria\\Perceptron\\src\\data\\example2en";
                    trainingFileName = "train.txt";
                    flag = false;
                    break;
                default:
                    System.out.println("Podales bledna opcje");
                    System.out.println("Podaj nowy wybór");
                    choice = scanner.nextInt();
                    break;
            }
        }


        DataProvider dataProvider = new DataProvider(trainingFileName, testFileName, path);
        int dimensions = dataProvider.howManyDimensions(dataProvider.getTrainingFile());
        double [] weights = new double[dimensions];
        int counter = 0;
        while(counter < dimensions){
            double number = Math.random();
            if(number != 0){
                weights[counter++] = number;
            }
        }

        HashMap<Double[], String> trainingSet = dataProvider.extractData(dataProvider.getTrainingFile());
        HashMap<Double[], String> testingSet = dataProvider.extractData(dataProvider.getTestingFile());


        Perceptron perceptron = new Perceptron(alpha, theta, trainingSet, weights);

        flag = perceptron.train(expectedAccuracy);
        System.out.println(perceptron);
        if(!flag){
            System.out.println("Nie udalo sie wytrenowac perceptronu :(");
            System.out.println(perceptron.getMaxAccuracy());
            System.exit(0);
        }
        System.out.println("Yupiiiii udało się wytrenować perceptron!");

//        System.out.println("Podaj dokladnosc jaka oczekujesz od perceptronu");
//        expectedAccuracy = scanner.nextDouble();
        System.out.println("Zbiór testowy:");
        perceptron.setVectorXWithAnswer(testingSet);
        perceptron.test();
        System.out.println(perceptron);


    }
}
