package pl.edu.pjatk;

import java.io.FileNotFoundException;
import java.util.List;

public class Network {
    private Perceptron [] perceptrons;
    private List<String> listOfAllFiles;
    private DataProvider dataProvider;

    public Network(Perceptron[] perceptrons, double expectedAccuracy, List<String> listOfAllFiles, DataProvider dataProvider) throws FileNotFoundException {
        this.perceptrons = perceptrons;
        this.listOfAllFiles = listOfAllFiles;
        this.dataProvider = dataProvider;

        System.out.println("*********************************************");
        int index = 0;
        for(Perceptron p : this.perceptrons)
            dataProvider.extractData(listOfAllFiles.get(index++));
           // System.out.println(p);
    }

}
