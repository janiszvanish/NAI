package pl.edu.pjatk;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj parametr uczenia z przedziału (0;1)");
        int alpha = scanner.nextInt();
        System.out.println("Wpisz cyfre na ktorych danych chcesz pracowac\n1 - example1\n2 - iris");
        int choice = scanner.nextInt();
        String path = "";
        String testFileName = "test.txt";
        String trainingFileName = "";

        switch(choice){
            case 1:
                path = "D:\\studia\\2021-2022\\letni\\NAI\\Labolatoria\\lab3\\Perceptron\\src\\data\\example1";
                trainingFileName = "train.txt";
                break;
            case 2:
                path = "D:\\studia\\2021-2022\\letni\\NAI\\Labolatoria\\lab3\\Perceptron\\src\\data\\iris_perceptron";
                trainingFileName = "training.txt";
                break;
        }

        DataProvider dataProvider = new DataProvider(trainingFileName, testFileName, path);

    }
}
