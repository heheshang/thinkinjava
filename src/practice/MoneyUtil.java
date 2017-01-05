/*
* @(#)MoneyUtil.java 2.0 11/11/23
*
* Copyright  2011-2011 Hnapay Information and technology Co., Ltd.
* Sanyunqiao, Chaoyang, Beijing, 100027, China.
* All Rights Reserved.
*
* This software is the confidential and proprietary information of
* Hnapay Information and technology Co., Ltd. (“Confidential Information”).
* You shall not disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into with Hnapay.
*/

package practice;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 金额数字工具类.
 * @author 贾维洲 20110709
 *
 */
public class MoneyUtil {

	/**
	 * No instantiation.
	 */
	protected MoneyUtil() {
		throw new UnsupportedOperationException();
	}

	/** bigNum. */
	private static String[] bigNum = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

	/** wei. */
	private static String[] wei = {"圆", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾",
			"佰", "仟", "兆" };

	/** swei. */
	private static String[] swei = {"角", "分" };

	/**
	 * Money format.
	 *
	 * @param amt
	 *            the amt
	 *
	 * @return 带2个小数位的交易金额
	 */
	public static String moneyFormat(String amt) {
		int index = amt.indexOf(".");
		String temp;
		String returnAmt;
		if (index == -1) {
			returnAmt = amt + ".00";
		} else {
			temp = amt.substring(index + 1);
			if (temp.length() == 1) {
				returnAmt = amt + "0";
			} else {
				returnAmt = amt;
			}
		}
		return returnAmt;
	}

	/**
	 * 返回按指定小数点位数，不足的四舍五入
	 * @param amt
	 * @param length
	 * @return
	 */
	public static String moneyFormat(String amt,int scale){
	    return new BigDecimal(amt).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
	}
	/**
	 * 将金额数字转换为大写汉字表示.
	 * @param money money
	 * @return String
	 */
	public static String convertMoney2ChnWord(BigDecimal money) {
		String strbig = new String();
		String temp = String.valueOf(money.setScale(2,
				BigDecimal.ROUND_HALF_DOWN));
		int bi = temp.indexOf(".");
		int si = temp.length() - (bi + 1);
		int j = bi;
		for (int i = 0; i < bi; i++) {
			strbig += getNUM(temp.substring(i, i + 1));
			strbig += getdanwei(j - 1);
			j--;
		}
//		temp = temp.substring(bi + 1, temp.length());
//		for (int i = 0; i < si; i++) {
//
//			strbig += getNUM(temp.substring(i, i + 1));
//			strbig += swei[i];
//		}
		return strbig;
	}

	/**
	 * getNUM.
	 * @param str str
	 * @return String
	 */
	static String getNUM(String str) {
		int t = Integer.parseInt(str);
		return bigNum[t];
	}

	/**
	 * getdanwei.
	 * @param t t
	 * @return String
	 */
	static String getdanwei(int t) {
		return wei[t];
	}

	/**
	 * 判断是否是合法的金额格式.
	 *
	 * @param str str
	 * @return boolean
	 */
	public static boolean isMoneyStr(String str) {
		if (str.matches("[\\d.]+") && str.length()>1) {
			if (str.substring(0, 1).equals("0")
					&& !(str.substring(1, 2).equals("."))) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * 向上取整
	 * <pre>
	 * MoneyUtil.ceil(new BigDecimal("12.3456"), new BigDecimal("100"))	==>	100
	 * MoneyUtil.ceil(new BigDecimal("12.3456"), new BigDecimal("10"))	==>	20
	 * MoneyUtil.ceil(new BigDecimal("12.3456"), new BigDecimal("1"))		==>	13
	 * MoneyUtil.ceil(new BigDecimal("12.3456"), new BigDecimal("0.1"))	==>	12.4
	 * MoneyUtil.ceil(new BigDecimal("12.3456"), new BigDecimal("0.01"))	==>	12.35
	 * MoneyUtil.ceil(new BigDecimal("12.3456"), new BigDecimal("0.001"))	==>	12.346
	 * MoneyUtil.ceil(new BigDecimal("12.3456"), new BigDecimal("0.0001"))	==>	12.3456
	 * MoneyUtil.ceil(new BigDecimal("12.3456"), new BigDecimal("0.00001"))	==>	12.34560
	 * </pre>
	 * @param d
	 * @param m
	 * @return
	 * @by zhanglei - 2012-4-12 上午11:32:04
	 */
	public static BigDecimal ceil(BigDecimal d, BigDecimal m) {
		return new BigDecimal(Math.ceil(d.divide(m, BigDecimal.ROUND_CEILING).doubleValue())).multiply(m);
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(BigDecimal v, int scale) {
		if (scale < 0) throw new IllegalArgumentException("The scale must be a positive integer or zero");
		BigDecimal one = new BigDecimal("1");
		return v.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
    /**
     * 检查金额格式是否正确 数值范围：0~9999999999.99 小数点后面最多2位小数
     * 
     * @param amt
     *            校验金额
     * @return
     */
//    public static boolean checkAmountFormat(String amt) {
//        if (StringUtils.isBlank(amt)) {
//            return false;
//        }
//        Pattern pattern = Pattern.compile("^\\d{1,10}(\\.\\d{0,2}){0,1}$");
//        Matcher match = pattern.matcher(amt);
//        return match.matches();
//    }
    
    
    
    /**
     * 数字金额大写转换
     */
    public static String convertUppercase(BigDecimal money){
        if(money==null) return "";
        double n = money.doubleValue();
        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = {{"元", "万", "亿"},{"", "拾", "佰", "仟"}};

        String head = n < 0? "负": "";
        n = Math.abs(n);
        
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if(s.length()<1){
            s = "整";    
        }
        int integerPart = (int)Math.floor(n);

        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }
    
    /**
     * 金额格式转换 100 --> 1.00
     * 
     * @param amtStr
     * @return
     */
    public static String formatAmt(String amtStr) {

        // 增加校验功能，防止误操作
        if (amtStr.indexOf(".") > -1) {
            return amtStr;
        }

        BigDecimal amt = null;
        amt = new BigDecimal(amtStr);
        amt = amt.movePointLeft(2);
        return amt.toString();
    }

	public static void main(String[] args){
		System.out.println(convertMoney2ChnWord(new BigDecimal("991092900009")));
	}
}
