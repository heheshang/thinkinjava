package io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class FileTest {
	// Throw exceptions to console:
	public static String read(String filename,String f1) throws IOException {
		// Reading input by lines:
//		FileInputStream in = new FileInputStream(new File(filename));
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while ( ( s = in.readLine() ) != null ){
//            String s1 = s.substring(0,s.indexOf("000000010000"));
//			int len = "3202000000000001".length();
			int len = "3202000000000001|23743700|100|0101|20210720|".length();
			int len2 = "3202000000005001|90352900|100|0101|20210720|000000010000".length();
			String s2 = String.valueOf(Integer.parseInt(s.substring(len,len2)));
//
//			String s2 = s.substring(0,len);
//            String sql = " INSERT INTO T_CARD_INFO" +
//                    "        (RECORD_NO," +
//                    "         CARD_NO," +
//                    "         ISSUE_ORG_CODE," +
//                    "         ISSUE_ORG_NAME," +
//                    "         CARD_CODE," +
//                    "         CARD_NAME," +
//                    "         FACE_VALUE," +
//                    "         MEDIA_TYPE," +
//                    "         HAS_PWD," +
//                    "         INIT_PINKEY," +
//                    "         INIT_PINKEY_MODIFY," +
//                    "         CARD_PINKEY," +
//                    "         MAX_AMT," +
//                    "         VALID_DATE," +
//                    "         ISSUE_TIME," +
//                    "         OPEN_ACCOUNT_FLAG," +
//                    "         STATUS_MAP," +
//                    "         RESV_FLD1," +
//                    "         RESV_FLD2," +
//                    "         NEED_REAL_NAME)" +
//                    "    VALUES" +
//                    "            (S_T_CARD_INFO.NEXTVAL," +
//                    "     '3202000000000001'," +
//                    "             '100370000000409'," +
//                    "             '青岛四季百货贸易有限公司'," +
//                    "             '00370083'," +
//                    "             '香港四季百货'," +
//                    "             '10000'," +
//                    "             '1'," +
//                    "             '1'," +
//                    "             '23743ssss'," +
//                    "             '0'," +
//                    "             '237437ddd'," +
//                    "             '100000'," +
//                    "             '20210720'," +
//                    "             sysdate," +
//                    "     '1'," +
//                    "                     '10000'," +
//                    "                     null," +
//                    "                     null," +
//                    "                     '1');";

			String sql = "INSERT INTO T_ACC_ACCOUNT_INFO (ACCOUNT_NO, MASTER_ACCOUNT_NO, ACCOUNT_NAME, OPEN_DATE, CLOSE_DATE, VALID_DATE, LOCK_END_DATE, PAY_ORG_CODE, SUPPLY_ORG_CODE, AREA_CODE, CITY_CODE, STATUS_MAP, ACCOUNT_TYPE, CURRENCY_CODE, BALANCE, AVAILABLE_BALANCE, FROZEN_AMOUNT, ENCRYPTED_MSG, LAST_UPDATE_TIME, LAST_TXN_TIME, RSVD_AMT1, RSVD_AMT2, RSVD_TEXT1, RSVD_TEXT2)\n" +
					" VALUES (1137+S_ACCOUNT_NO.NEXTVAL,NULL,'联机账户',SYSDATE,SYSDATE,SYSDATE,SYSDATE, NULL,NULL,'370000','370200','1000','1','156',10000--,10000--,0,NULL,   SYSDATE,NULL,0,0,NULL,NULL);";

//			String sql2 = "INSERT INTO T_CARD_ACCOUNT_REL (RECORD_NO, ISSUE_ORG_CODE, ISSUE_ORG_NAME, CARD_NO, ACCOUNT_NO, ACCOUNT_SCENE_TYPE, BINDING_FLAG, BINDING_METHOD, BINDING_TIME, UNBINDING_TIME, BINDING_OP_ORG_CODE, BINDING_OP_UID, BINDING_OP_TIME, UNBINDING_OP_ORG_CODE, UNBINDING_OP_UID, UNBINDING_OP_TIME)\n" +
//					"VALUES(S_T_CARD_ACCOUNT_REL.NEXTVAL,'100370000000409','青岛四季百货贸易有限公司','3202000000000001---',S_T_CARD_INFO.NEXTVAL,'1','1','1',sysdate, NULL,'100370000000409','100370000000409',sysdate,NULL,NULL,NULL);";
			sb.append(sql.replace("10000--",s2).replace("10000--",s2)+ "\n");


		}


		FileChannel fc = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\sql2\\"+f1).getChannel();
		fc.write(ByteBuffer.wrap(sb.toString().getBytes("UTF-8")));
		fc.close();

		return sb.toString();
	}

	public static void main(String[] args) throws IOException{
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\100-1.txt","100-1.txt"));
		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\100-2.txt","100-2.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\100-3.txt","100-3.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\100-4.txt","100-4.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\200-1.txt","200-1.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\200-2.txt","200-2.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\200-3.txt","200-3.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\200-4.txt","200-4.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\200-5.txt","200-5.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\200-6.txt","200-6.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\500-1.txt","500-1.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\500-2.txt","500-2.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\500-3.txt","500-3.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\500-4.txt","500-4.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\1000-1.txt","1000-1.txt"));
//		System.out.print(read("C:\\Users\\Administrator\\Desktop\\四季卡开卡文件\\1000-2.txt","1000-2.txt"));
	}
}
