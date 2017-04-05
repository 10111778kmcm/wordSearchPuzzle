import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
public class WordSearchMethods{
    private char[][] puzzle;
    public ArrayList<String> puzzleWords;
    public int totalChars = 0;
    public int nextWordLoc = 0;
    public static int testCol = 0;
    public static int testRow = 0;

    public WordSearchMethods(String[] userSpecifiedWords) {
        for(int i = 0; i < userSpecifiedWords.length; i++){  //For loop takes each passed string and 
            String word = userSpecifiedWords[i];             //adds the length of each to totalChars
            totalChars += word.length();                     //this value will be used to generate the
        }                                                    //grid

        puzzleWords = new ArrayList<String>(Arrays.asList(userSpecifiedWords));  
        //New arrayList is a copy of the passed array
        generateWordSearchPuzzle(totalChars);
    }

    public WordSearchMethods(String wordFile, int wordCount, int shortest, int longest) {
        puzzleWords = new ArrayList<String>();              //New arrayList created
        ArrayList<String> validWords = new ArrayList<String>();
        File words = new File(wordFile);        
        BufferedReader br = null;
        int i = 0;
        int rand = 0;
        try{
            FileReader fr = new FileReader(words);
            br = new BufferedReader(fr);
            String word = br.readLine();
            while(word != null){
                if(word.length() >= shortest){
                    if(word.length() <= longest){           //All the words that satisfy the length arguments
                        validWords.add(word);               //moved to a new ArrayList
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
        while(i < wordCount){
            rand = (int)(Math.random() * validWords.size());    //Randomly select words from the created validWords list
            String validWord = validWords.get(rand);            //Add to the puzzleWords list. Stop when wordCount is 
            totalChars += validWord.length();                   //satisfied
            puzzleWords.add(validWord);
            i++;
        }

        Collections.sort(validWords);

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
        boolean listPopulated = false;
        int len = word.length();
        boolean north = true;
        boolean south = true;
        boolean east = true;
        boolean west = true;
        int i = 0;
        int rand = 0;
        char valid = ' ';

        while(!listPopulated){
            testRow = getRand();
            testCol = getRand();

            north = true;
            south = true;
            east = true;
            west = true;

            for(i = testRow; i >= testRow - (len - 1) && north; i--){
                if(i >= 0 && i < puzzle.length){
                    if(puzzle[i][testCol] != '\u0000'){
                        north = false;
                    }
                }else{
                    north = false;
                }
            }

            for(i = testRow; i <= testRow + (len - 1) && south; i++){
                if(i >= 0 && i < puzzle.length){
                    if(puzzle[i][testCol] != '\u0000'){
                        south = false;
                    }
                }else{
                    south = false;
                }   
            }

            for(i = testRow; i >= testCol - (len - 1) && west; i--){
                if(i >= 0 && i < puzzle.length){
                    if(puzzle[testRow][i] != '\u0000'){
                        west = false;
                    }
                }else{
                    west = false;
                }
            }

            for(i = testRow; i <= testCol + (len - 1) && east; i++){
                if(i >= 0 && i < puzzle.length){
                    if(puzzle[testRow][i] != '\u0000'){
                        east = false;
                    }
                }else{
                    east = false;
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

            if(validDirections.size() > 0){
                listPopulated = true;
            }

        }

        rand = (int)(Math.random() * validDirections.size());
        valid = validDirections.get(rand);

        return valid;    
    }

    public boolean placeWord(String word, char direction){
        boolean placed = false;
        char toPlace = ' ';
        int i = 0;
        int letPos = 0;
        int len = word.length();
        int row = testRow;
        int col = testCol;

        switch(direction){
            case 'N':
            letPos = 0;
            for(i = row; i >= row - (len - 1); i--){
                toPlace = word.charAt(letPos); 
                puzzle[i][col] = toPlace;
                letPos++;
            } 
            break;

            case 'S':
            letPos = 0;
            for(i = row; i <= row + (len - 1); i++){
                toPlace = word.charAt(letPos);
                puzzle[i][col] = toPlace;
                letPos++;
            }
            break;

            case 'E':
            letPos = 0;
            for(i = col; i <= col + (len - 1); i++){
                toPlace = word.charAt(letPos);
                puzzle[row][i] = toPlace;
                letPos++;
            }
            break;

            case 'W':
            for(i = col; i >= col - (len - 1); i--){
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

    public int getTestCol(){
        return testCol;
    }

    public int getTestRow(){
        return testRow;
    }

    public void display(){
        for(int i = 0; i < puzzle.length; i++){
            for(int j = 0; j < puzzle.length; j++) {
                System.out.print(puzzle[i][j]+" ");
            }
            System.out.println("");
        }

    }

}


