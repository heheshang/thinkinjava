package practice;

import net.mindview.util.TextFile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static net.mindview.util.Print.print;


/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class E16_Vowels {
    private final static Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'o', 'u', 'i', 'A', 'E', 'O', 'U', 'I'));

    public static void main(String[] args){
        HashSet<String> proessedWords = new HashSet<>();
        int fileVowels = 0;
        int wordsVowels;
        for ( String word : new TextFile("F:\\think\\thinkinjava\\src\\practice\\E16_Vowels.java", "\\W+") ) {
            wordsVowels = 0;
            for ( char letter : word.toCharArray() ) {
                if ( vowels.contains(letter) )
                    wordsVowels++;
                if ( !proessedWords.contains(word) ) {
                    proessedWords.add(word);
                    print(word + " has " + wordsVowels + " vowel(s)");
                }
                fileVowels += wordsVowels;
            }
            print("Total number of vowels in file: " + fileVowels);
        }
    }
}
