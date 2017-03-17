package net.mindview.util;

import java.util.*;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
public class E17_CharactersInfo {
    public static void main(String[] args){
        Map<Character, Integer> charStats = new HashMap<>();
        for ( String word : new TextFile("F:\\think\\thinkinjava\\src\\net\\mindview\\util\\E17_CharactersInfo.java", "\\W+") ) {
            for ( int i = 0; i < word.length(); i++ ) {
                Character ch = word.charAt(i);
                Integer freq = charStats.get(ch);
                charStats.put(ch, freq == null ? 1 : freq + 1);
            }
        }
        List<Character> keys = Arrays.asList(charStats.keySet().toArray(new Character[0]));
        Collections.sort(keys);
        for ( Character key : keys ) {
            System.out.println(key + " => " + charStats.get(key));

        }

    }
}
