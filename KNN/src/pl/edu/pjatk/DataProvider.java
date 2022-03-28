package pl.edu.pjatk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataProvider {
    private String trainingFileName;
    private String testingFileName;
    private String path;
    private File trainingFile;
    private File testingFile;

    public DataProvider(String trainingFileName, String testingFileName, String path) {
        this.trainingFileName = trainingFileName;
        this.testingFileName = testingFileName;
        this.path = path;
        this.trainingFile = new File(this.path + "\\" + this.trainingFileName);
        this.testingFile = new File(this.path + "\\" + this.testingFileName);
    }

    public List<Flower> extractDataWithSpecies(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        List<Flower> listOfFlowers = new ArrayList<>();
        int dimensions = howManyDimensions(file);
        while(scan.hasNext()){
            String lineFromFile = scan.next();
            String [] lineFromFileToArray = lineFromFile.split(",");
            double [] measurements = new double[dimensions];
            for(int i = 0; i < measurements.length; i++){
                measurements[i] = Double.parseDouble(lineFromFileToArray[i]);
            }
            Flower flower = new Flower(measurements, lineFromFileToArray[lineFromFileToArray.length - 1]);

            listOfFlowers.add(flower);

        }
        return listOfFlowers;
    }

    public int howManyDimensions(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        String line = scan.nextLine();

        return line.length() - line.replace(",", "").length();
    }

    public File getTrainingFile() {
        return trainingFile;
    }

    public File getTestingFile() {
        return testingFile;
    }
}
