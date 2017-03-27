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