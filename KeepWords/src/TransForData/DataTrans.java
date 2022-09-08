package TransForData;
import java.io.*;

public class DataTrans {
	public static String gb2312ToUnicode(String s) {
		try {
			return new String(s.getBytes("ISO8859_1"), "gb2312");
		} catch (UnsupportedEncodingException uee) {
			return s;
		}
	}

	public static String unicodeTogb2312(String s) {
		try {
			return new String(s.getBytes("gb2312"), "ISO8859_1");
		} catch (UnsupportedEncodingException uee) {
			return s;
		}
	}
}