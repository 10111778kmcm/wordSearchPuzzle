import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
public class WordSearchMethods{
    private char[][] puzzle;
    private ArrayList<String> puzzleWords;
    private ArrayList<String> testWords;
    public int totalChars = 0;
    public int nextWordLoc = 0;

    public WordSearchMethods(String[] userSpecifiedWords) {
        for(int i = 0; i < userSpecifiedWords.length; i++){  //For loop takes each passed string and 
            String word = userSpecifiedWords[i];             //adds the length of each to totalChars
            totalChars += word.length();                     //this value will be used to generate the
        }                                                    //grid

        puzzleWords = new ArrayList<String>(Arrays.asList(userSpecifiedWords));  
        testWords = new ArrayList<String>();
        //New arrayList is a copy of the passed array
        generateWordSearchPuzzle(totalChars);
    }

    public WordSearchMethods(String wordFile, int wordCount, int shortest, int longest) {
        puzzleWords = new ArrayList<String>();              //New arrayList created
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

    public int getRand(){
        int rand = (int)(Math.random() * puzzle.length);
        return rand;
    }

    private int getRandCoord(){
        int rand = (int)(Math.random() * puzzle.length);
        return rand;
    }

    public String getWord(){
        String word = puzzleWords.get(nextWordLoc);
        nextWordLoc++;
        return word;
    }

    public char validPlacement(String word){
        ArrayList<Character> validDirections;
        validDirections = new ArrayList<Character>();
        int len = word.length();
        int i = 0;
        int rand = 0;
        boolean north = true;
        boolean south = true;
        boolean east = true;
        boolean west = true;
        char valid = ' ';

        while(validDirections == null){
            int testRow = getRand();
            int testCol = getRand();

            for(i = testRow; i > testRow - (len - 1) && north; i--){
                if(puzzle[i][testCol] != '\u0000'){
                    north = false;
                }
            }

            for(i = testRow; i < testRow + (len - 1) && south; i++){
                if(puzzle[i][testCol] != '\u0000'){
                    south = false;
                }
            }

            for(i = testRow; i > testCol - (len - 1) && east; i--){
                if(puzzle[i][testCol] != '\u0000'){
                    east = false;
                }
            }

            for(i = testRow; i < testCol + (len - 1) && west; i++){
                if(puzzle[i][testCol] != '\u0000'){
                    west = false;
                }
            }

            if(north){
                validDirections.add('N');
            }

            if(south){
                validDirections.add('S');
            }

            if(east){
                validDirections.add('E');
            }

            if(west){
                validDirections.add('W');
            }

        }

        rand = (int)(Math.random() * validDirections.size());
        valid = validDirections.get(rand);

        return valid;    
    }

    private boolean placeWord(String word, char direction, int row, int col){
        boolean placed = false;
        char toPlace = ' ';
        int i = 0;
        int letPos = 0;
        int len = word.length();

        switch(direction){
            case 'N':
            letPos = 0;
            for(i = row; i > row - (len - 1); i--){
                toPlace = word.charAt(letPos); 
                puzzle[i][col] = toPlace;
                letPos++;
            } 
            break;

            case 'S':
            letPos = 0;
            for(i = row; i < row + (len - 1); i++){
                toPlace = word.charAt(letPos);
                puzzle[i][col] = toPlace;
                letPos++;
            }
            break;

            case 'E':
            letPos = 0;
            for(i = col; i > col - (len - 1); i--){
                toPlace = word.charAt(letPos);
                puzzle[row][i] = toPlace;
                letPos++;
            }
            break;
            
            case 'W':
            for(i = col; i < col - (len - 1); i++){
                toPlace = word.charAt(letPos);
                puzzle[row][i] = toPlace;
                letPos++;
            }
            break;
        }
        if(i == word.length() - 1){
            placed = true; 
        }
        return placed;
    }
}