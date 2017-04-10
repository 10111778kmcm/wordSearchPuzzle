public class WordSearchDriver {
    public static void main(String[] args){
        int i = 0;
        int j = 0;
        String word = "/0";
        char toPlace = ' ';
        WordSearchMethods wordSearch = new WordSearchMethods("WordsList.txt", 10, 5, 10);
        wordSearch.generateWordSearchPuzzle();
        wordSearch.showWordSearchPuzzle();
    }   
}

