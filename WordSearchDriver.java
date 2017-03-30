public class WordSearchDriver {
    public static void main(String[] args){
        WordSearchMethods wordSearch = new WordSearchMethods("WordsList.txt", 10, 5, 10);
        String word = wordSearch.getWord();
        char toPlace = wordSearch.validPlacement(word);
        wordSearch.placeWord(word, toPlace, //testRow, testCol); //How to get the coordinates? 
    }   
}

