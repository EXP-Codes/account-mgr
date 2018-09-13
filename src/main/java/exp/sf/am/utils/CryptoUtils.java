package exp.sf.am.utils;

import exp.libs.utils.num.BODHUtils;

/**
 * <PRE>
 * 加解密工具.
 * </PRE>
 * <br/><B>PROJECT : </B> account-mgr
 * <br/><B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   2017-07-11
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class CryptoUtils {

	private final static String _PW_KEY = 
			"BD2D613A170463A35B113684C4606911C29A8200F46B2224126EBC4EE6F3BB6D";
	
	private final static String PW_KEY = new String(BODHUtils.toBytes(
					exp.libs.utils.encode.CryptoUtils.deDES(_PW_KEY)));
	
	private CryptoUtils() {}
	
	public static String encode(String str) {
		return exp.libs.utils.encode.CryptoUtils.toDES(str, PW_KEY);
	}
	
	public static String decode(String str) {
		return exp.libs.utils.encode.CryptoUtils.deDES(str, PW_KEY);
	}
	
}
