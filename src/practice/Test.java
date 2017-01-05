package practice;

import java.math.BigDecimal;

/**
 * 类Test.java的功能描述:TODO
 * @author ssk 2016-11-23 上午 9:24 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
public class Test {
    /**
     * 大写汉字.
     */
    private static String[] bigNum = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    /**
     * 单位
     */
    private static String[] wei = {"元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆"};

    public static String convertMoney2ChnWord(BigDecimal money){
        String strbig = "";
        String temp = String.valueOf(money);
        int bi = temp.length();
        int j = bi;
        for ( int i = 0; i < bi; i++ ) {
            strbig += getNUM(temp.substring(i, i + 1));
            if ( temp.substring(i, i + 1).equals("0") ) {
                strbig += "";
            } else {
                strbig += getdanwei(j - 1);
            }
            j--;
        }
        if ( strbig.substring(strbig.length() - 1, strbig.length()).equals("零") ) {
            strbig += getdanwei(0);
        }
        return strbig;
    }

    static String getNUM(String str){
        int t = Integer.parseInt(str);
        return bigNum[t];
    }

    static String getdanwei(int t){
        return wei[t];
    }

    public static void main(String[] args){
        BigDecimal decimal;
        try {
            decimal = new BigDecimal("3333220030000");
            System.out.println(convertMoney2ChnWord(decimal));
        }catch ( ArrayIndexOutOfBoundsException e ){
            e.printStackTrace();
        }

    }
}
