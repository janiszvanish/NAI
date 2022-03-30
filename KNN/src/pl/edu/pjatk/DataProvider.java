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
    private HashMap<String, Integer> mapOfSpeciesToCount;

    public DataProvider(String trainingFileName, String testingFileName, String path) throws FileNotFoundException {
        this.trainingFileName = trainingFileName;
        this.testingFileName = testingFileName;
        this.path = path;
        this.trainingFile = new File(this.path + "\\" + this.trainingFileName);
        this.testingFile = new File(this.path + "\\" + this.testingFileName);
        this.mapOfSpeciesToCount = setMapOfSpeciesToCount(trainingFile);
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

    public HashMap<String, Integer> getMapOfSpeciesToCount() {
        return mapOfSpeciesToCount;
    }

    public HashMap<String, Integer> setMapOfSpeciesToCount(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);

        HashMap<String, Integer> mapOfSpeciesToCount = new LinkedHashMap<>();
        while (scan.hasNext()) {
            String lineFromFile = scan.next();
            String[] lineFromFileToArray = lineFromFile.split(",");
            for (int i = 0; i < lineFromFileToArray.length; i++) {
                mapOfSpeciesToCount.put(lineFromFileToArray[lineFromFileToArray.length - 1], 0);
            }
        }
        return mapOfSpeciesToCount;
    }

    public HashMap<String, Integer> makeEvryValue0(HashMap<String, Integer> map){
        for(String s : map.keySet()){
            map.replace(s, 0);
        }
        return map;
    }

    public File getTrainingFile() {
        return trainingFile;
    }

    public File getTestingFile() {
        return testingFile;
    }

}
