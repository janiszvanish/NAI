package pl.edu.pjatk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        HashMap<Double[], String> vectorXWithAnswer = new LinkedHashMap<>();
        HashMap<String, Integer> answers = new LinkedHashMap<>();
        int dimensions = howManyDimensions(file);
        int value = 0;
        while(scan.hasNext()){
            String lineFromFile = scan.next();
            String [] lineFromFileToArray = lineFromFile.split(",");
            Double [] vectorX = new Double[dimensions];

            for(int i = 0; i < vectorX.length; i++)
                vectorX[i] = Double.parseDouble(lineFromFileToArray[i]);

            if(!isInteger(lineFromFileToArray[lineFromFileToArray.length - 1])){
                if(!answers.containsKey(lineFromFileToArray[lineFromFileToArray.length - 1]))
                    answers.put(lineFromFileToArray[lineFromFileToArray.length - 1], value++);
                vectorXWithAnswer.put(vectorX, String.valueOf(answers.get(lineFromFileToArray[lineFromFileToArray.length - 1])));
            }else
                vectorXWithAnswer.put(vectorX, lineFromFileToArray[lineFromFileToArray.length - 1]);

        }
        return vectorXWithAnswer;
    }
    public void seeVector(HashMap<Double [], String> mp){
        for(Double [] values : mp.keySet()){
            for(double v : values)
                System.out.println(v);
            System.out.println(" " + mp.get(values));
        }
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
