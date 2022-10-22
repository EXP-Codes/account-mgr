package exp.sf.am;

import exp.libs.ui.BeautyEyeUtils;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

import exp.sf.am.core.AppMgr;

/**
 * <PRE>
 * 帐密管理工具.
 * </PRE>
 * <br/><B>PROJECT : </B> account-mgr
 * <br/><B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   2017-07-11
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.8
 */
public class Main {
	
	/**
	 * 程序入口
	 * @param args
	 */
	public static void main(String[] args) {
		BeautyEyeUtils.init(FrameBorderStyle.translucencySmallShadow);
		AppMgr.createInstn();
	}
	
}
