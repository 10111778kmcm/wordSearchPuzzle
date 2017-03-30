
public class WordSearchMethods{
    
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

    public char placement(String word, int row, int col){
        char[] directions = {'N','S','E','W'}; 		//N,S,E,W
        boolean validDirection = false;
        int len = word.length();
        String[] validDirections = new String[3];
        char place = ' ';
        int letter = 0;
        while(!validDirection){
            int direction = (int)((Math.random() * 4) + 1);
            String validPlacement = "";	
            switch(direction){
                case 'N': 
                if(row - len >= 0){		
                    for(int i = row; i > row - len; i--){
                        place = word.charAt(letter);
                        puzzle[i][col] = place;
                        letter++;
                        orientation = "Up";			
                    }		//North
                }
                break;

                case 'S':	
                if(row + len <= puzzle.length - 1){	
                    for(i = row; i < row + len; i++){
                        place = word.charAt(letter);
                        puzzle[i][col] = place;
                        letter++;	
                        orientation = "Down";			
                    }	
                }		
                break;

                case 'E':
                if(col - len >= 0){		
                    for(i = col; i > col - len; i--){
                        place = word.charAt(letter);
                        puzzle[row][i] = place;
                        letter++;	
                        orientation = "Left";			
                    }					//East
                }	
                break;

                case 'W':
                if(col + len <= puzzle[0].length - 1){					//West
                    for(i = col; i < col + len; i++){
                        place = word.charAt(letter);
                        puzzle[row][i] = place;
                        letter++;	
                        orientation = "Right";			
                    }
                }
                break;
            }
        }
        return directions[1];
    }

    private String placeWordInClear(String word, int row, int col, char direction){
        int len = word.length();
        boolean valid = false;
        int i = 0;
        int letter = 0;
        String orientation = "";
        char place = ' ';
        switch(direction){
            case 'N': 
            for(i = row; i > row - len; i--){
                place = word.charAt(letter);
                puzzle[i][col] = place;
                letter++;
                orientation = "Up";			
            }
            case 'S':
            for(i = row; i < row + len; i++){
                place = word.charAt(letter);
                puzzle[i][col] = place;
                letter++;	
                orientation = "Down";			
            }
            case 'E':
            for(i = col; i > col - len; i--){
                place = word.charAt(letter);
                puzzle[row][i] = place;
                letter++;	
                orientation = "Left";			
            }
            case 'W':
            for(i = col; i < col + len; i++){
                place = word.charAt(letter);
                puzzle[row][i] = place;
                letter++;	
                orientation = "Right";			
            }
        }
        return orientation;
    }
    
    private int getRandCoord(){
        int rand = (int)(Math.random() * puzzle.length);
        return rand;
    }
    
    private String validLocation(String word){
        int pos = 0;
        int row = 0;
        int col = 0;
        int len = word.length();
        int i = 0;
        char letter = ' ';
        while(i < puzzleWords.size()){
            word = puzzleWords.get(pos);
            letter = word.charAt(pos);
            row = getRandCoord();
            col = getRandCoord();  
        }
    }
}