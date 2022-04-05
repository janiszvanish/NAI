package pl.edu.pjatk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String path = "D:\\studia\\2021-2022\\letni\\NAI\\Labolatoria\\Jednowarstwowa_siec_neuronowa\\src\\pl\\edu\\pjatk\\data";

        System.out.println("Podaj parametr uczenia z przedziału (0;1)");
        double alpha = scanner.nextDouble();
        System.out.println("Podaj próg aktywacji z przedziału (0;1)");
        double theta = scanner.nextDouble();
        System.out.println("Podaj oczekiwana dokladnosc");
        double expectedAccuracy = scanner.nextDouble();

        DataProvider dataProvider = new DataProvider(path);
        dataProvider.directoriesNames(path);
        List<String> listOfFiles = dataProvider.findAllFilesAtCatalog("train");

        Perceptron [] perceptrons = new Perceptron[dataProvider.howManyLanguages()];

        // w tym miejscu są stworzone 3 perceotrony en, fi i pl
        // każdy ma histogram liter i nazwe jezyka;
        for(int i = 0; i < perceptrons.length; i++){
            perceptrons[i] = new Perceptron(alpha, theta, dataProvider.getDataForPerceptron(dataProvider.getLanguages().get(i)),
                    dataProvider.generateWeights(), dataProvider.getLanguages().get(i));
        }

        Network network = new Network(perceptrons, expectedAccuracy, listOfFiles, dataProvider);


    }
}