package pl.edu.pjatk;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;
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
        System.out.println("Podaj parametr uczenia");
        double alpha = scanner.nextDouble();
        System.out.println("Podaj próg aktywacji");
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

        Perceptron perceptron = new Perceptron(dimensions);
        double accuracy = 0.0;
        int counter = 0;
        while(accuracy < expectedAccuracy){
            int goodPredictions = 0;
            for(Double [] vector : dataProvider.extractData(dataProvider.getTrainingFile()).keySet()){
                goodPredictions += perceptron
                        .train(vector, Integer.parseInt(dataProvider.extractData(dataProvider.getTrainingFile()).get(vector)),
                            alpha);
            }
            counter++;
            accuracy = goodPredictions / dataProvider.extractData(dataProvider.getTrainingFile()).size() * 100;
            System.out.println("Proba nr " + counter + "\nDokladnosc: " + accuracy);
        }
    }
}
