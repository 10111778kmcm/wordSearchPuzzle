import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordSearchPuzzle {
    private char[][] puzzle ;
    private ArrayList<String> puzzleWords;
    public int totalChars = 0;

    public static void main(String[] args){
        WordSearchPuzzle wordSearchPuzzle = new WordSearchPuzzle("WordsList.txt", 10, 5, 10);
        int i = 0;
        int testRow = 0;
        int testCol = 0; 

    }

    public WordSearchPuzzle(String[] userSpecifiedWords) {
        for(int i = 0; i < userSpecifiedWords.length; i++){  //For loop takes each passed string and 
            String word = userSpecifiedWords[i];             //adds the length of each to totalChars
            totalChars += word.length();                     //this value will be used to generate the
        }                                                	 //grid

        puzzleWords = new ArrayList<String>(Arrays.asList(userSpecifiedWords));      
        //New arrayList is a copy of the passed array
        generateWordSearchPuzzle(totalChars);
    }

    public WordSearchPuzzle(String wordFile, int wordCount, int shortest, int longest) {
        puzzleWords = new ArrayList<String>();  			//New arrayList created
        File words = new File(wordFile);        
        BufferedReader br = null;
        int i = 0;
        try{
            FileReader fr = new FileReader(words);
            br = new BufferedReader(fr);
            String word = br.readLine();
            while(word != null && i < wordCount){
                if(word.length() >= shortest){
                    if(word.length() <= longest){
                        puzzleWords.add(word);
                        totalChars += word.length();
                        i++;
                    }
                }
                word = br.readLine();            
            }      
        }
        catch(FileNotFoundException e){
            System.out.println("File not found: " + words.toString());
        }
        catch(IOException e){
            System.out.println("Unable to close file: " + words.toString());
        }
        generateWordSearchPuzzle(totalChars);
    }

    private void generateWordSearchPuzzle(int totalChars){
        double size = totalChars * 1.75;
        int dimension = (int)(Math.sqrt(size));
        puzzle = new char[dimension][dimension];
    }

    public void getRand(){
        int row = (int)(Math.random() * puzzle.length);
        int col = (int)(Math.random() * puzzle.length);
    }

    
}
