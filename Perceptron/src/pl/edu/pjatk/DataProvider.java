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

//    public List<Flower> extractData(File file) throws FileNotFoundException {
//        Scanner scan = new Scanner(file);
//        List<Flower> listOfFlowers = new ArrayList<>();
//        while(scan.hasNext()){
//            String lineFromFile = scan.next();
//            String [] lineFromFileToArray = lineFromFile.split(",");
//            Flower flower = new Flower(Double.parseDouble(lineFromFileToArray[0]),
//                    Double.parseDouble(lineFromFileToArray[1]), Double.parseDouble(lineFromFileToArray[2]),
//                    Double.parseDouble(lineFromFileToArray[3]), whichSpecies(lineFromFileToArray[4]));
//
//            listOfFlowers.add(flower);
//
//        }
//        return listOfFlowers;
//    }

    public int howManyDimensions(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        String line = scan.nextLine();

        return line.length() - line.replace(",", "").length();
    }
//    private SpeciesOfIris whichSpecies(String species){
//        SpeciesOfIris speciesOfIris;
//        switch(species){
//            case "Iris-setosa":
//                speciesOfIris = SpeciesOfIris.SETOSA;
//                break;
//            case "Iris-versicolor":
//                speciesOfIris = SpeciesOfIris.VERSICOLOR;
//                break;
//            case "Iris-virginica":
//                speciesOfIris = SpeciesOfIris.VIRGINICA;
//                break;
//            default:
//                System.out.println("chuj ci w dupe");
//                speciesOfIris = SpeciesOfIris.COS_SIE_POPSULO;
//
//        }
//        return speciesOfIris;
//    }
//    public List<Flower> extractOneSpecies(List<Flower> trainingSet, SpeciesOfIris species){
//        List<Flower> list = new ArrayList<>();
//        for(Flower f : trainingSet){
//            if(f.getSpecies().equals(species))
//                list.add(f);
//        }
//        return list;
//    }
    public File getTrainingFile() {
        return trainingFile;
    }

    public void setTrainingFile(File trainingFile) {
        this.trainingFile = trainingFile;
    }

    public File getTestingFile() {
        return testingFile;
    }

    public void setTestingFile(File testingFile) {
        this.testingFile = testingFile;
    }
}
