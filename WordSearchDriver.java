public class WordSearchDriver {
    public static void main(String[] args){
        int i = 0;
        int j = 0;
        String word = "/0";
        char toPlace = ' ';
        WordSearchMethods wordSearch = new WordSearchMethods("WordsList.txt", 10, 5, 10);

        while(i < wordSearch.puzzleWords.size()){
            word = wordSearch.getWord();
            toPlace = wordSearch.validPlacement(word);
            wordSearch.placeWord(word, toPlace); 
            wordSearch.display();
            i++;
        }//How to get the coordinates?
        wordSearch.fillGrid();
        wordSearch.display();
    }   
}

