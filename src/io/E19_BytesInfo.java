package io;

import net.mindview.util.BinaryFile;

import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
public class E19_BytesInfo {
    public static void main(String[] args) throws IOException{
        Map<Byte, Integer> bytesStat = new HashMap<Byte, Integer>();
        for ( Byte bt : BinaryFile.read("F:\\think\\thinkinjava\\web\\WEB-INF\\classes\\io\\E19_BytesInfo.class") ) {
            Integer freq = bytesStat.get(bt);
            bytesStat.put(bt, freq == null ? 1 : freq + 1);
        }
        List<Byte> keys = new ArrayList<Byte>(bytesStat.keySet());
        Collections.sort(keys);
        for ( Byte key : keys )
            System.out.println(key + " => " + bytesStat.get(key));
    }
}
