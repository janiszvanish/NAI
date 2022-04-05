package pl.edu.pjatk;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

public class DataProvider {
    private String path;
    private List<String> languages;
    private List<String> text;
    private HashMap<String, String> textWithRightLanguage;
    public DataProvider(String path) {
        this.path = path;
    }

    public List<String> findAllFilesAtCatalog(String type) throws FileNotFoundException {
        // mapa skladajaca sie z tekstu i odpowiedzią jaki to język;
        languages = new ArrayList<>();
        String [] directories = directoriesNames(path);
        List<String> pathsToDir = new ArrayList<>();
        List<String> pathsToReturn = new ArrayList<>();
        for(String directory : directories){
            languages.add(directory);
            pathsToDir.add(path + "\\" + directory + "\\" + type);
        }
        textWithRightLanguage = new HashMap<>();
        for(String fileName : pathsToDir){
            String [] names = directoriesNames(fileName);
            for(String text : names){
                File file = new File(fileName + "\\" + text);
                pathsToReturn.add(fileName + "\\" + text);
                Scanner scanner = new Scanner(file);
                while(scanner.hasNext()){
                    var textFromFile = scanner.nextLine();
                    textWithRightLanguage.put(textFromFile, text.substring(0,2));
                }
            }
        }
        return pathsToReturn;
    }
    public double [] generateWeights(){
        double [] weights = new double[26];
        int counter = 0;
        while(counter < 26){
            double number = Math.random();
            if(number != 0){
                weights[counter++] = number;
            }
        }
        return weights;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String[] directoriesNames(String path){
        File directory = new File(path);
        return directory.list();
    }

    public int howManyLanguages(){
        return directoriesNames(path).length;
    }

    public HashMap<TreeMap<Character, Double>, String> getDataForPerceptron(String lang) throws FileNotFoundException {
        HashMap<TreeMap<Character, Double>, String> alphabetWithLanguage = new HashMap<>();
        for(String s : textWithRightLanguage.keySet()){
            if(textWithRightLanguage.get(s).equals(lang)){
                TreeMap<Character, Double> alphabet = extractData(s);
                String language = textWithRightLanguage.get(s);
                alphabetWithLanguage.put(alphabet, language);
            }

        }
        return alphabetWithLanguage;
    }
    public TreeMap<Character, Double> extractData(String text) throws FileNotFoundException {

        // usuwanie znaków białych, specjalnych i liczb
        text = text.replaceAll("\\W", "");
        text = text.replaceAll("\\d", "");
        text = text.toLowerCase();

        //tworze tablice liter
        char [] lettersArray = new char[26];
        int ascii = 97;
        for(int i = 0; i < 26; i++){
            lettersArray[i] = (char) ascii++;
        }

        // tworzę mapę i uzupełniam literami i ilością ich wystąpień (na razie 0)
        TreeMap<Character, Double> alphabet = new TreeMap<>();
        for(int i = 0; i < lettersArray.length; i++){
            alphabet.put(lettersArray[i], 0.0);
        }

        // zliczam każdą literę
        for(int i = 0; i < text.length(); i++){
            double valueAtI = alphabet.get(text.charAt(i));
            valueAtI += 1.0;
            alphabet.replace(text.charAt(i), valueAtI);
        }

        // % udział każdej litery w danym pliku
        for(char c : alphabet.keySet()){
            double value = alphabet.get(c);
            alphabet.replace(c, (value / text.length()));
        }

        System.out.println(alphabet);

        return alphabet;
    }
}
