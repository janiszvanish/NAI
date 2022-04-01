package pl.edu.pjatk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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

    public HashMap<Double[], String> extractData(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        HashMap<Double[], String> vectorXWithAnswer = new HashMap<>();
        int dimensions = howManyDimensions(file);
        while(scan.hasNext()){
            String lineFromFile = scan.next();
            String [] lineFromFileToArray = lineFromFile.split(",");
            Double [] vectorX = new Double[dimensions];
            for(int i = 0; i < vectorX.length; i++){
                vectorX[i] = Double.parseDouble(lineFromFileToArray[i]);
            }
            if(!isInteger(lineFromFileToArray[lineFromFileToArray.length - 1])){
                if(lineFromFileToArray[lineFromFileToArray.length - 1].equals("Iris-versicolor"))
                    vectorXWithAnswer.put(vectorX, "1");
                else
                    vectorXWithAnswer.put(vectorX, "0");
            }else
                vectorXWithAnswer.put(vectorX, lineFromFileToArray[lineFromFileToArray.length - 1]);

        }
        return vectorXWithAnswer;
    }
    private boolean isInteger(String s){
        try {
            int iVal = Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException e) {
        }
        return false;
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
