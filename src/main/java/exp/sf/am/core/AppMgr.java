package exp.sf.am.core;

import exp.libs.warp.ui.SwingUtils;

/**
 * <PRE>
 * 应用管理器.
 * </PRE>
 * <br/><B>PROJECT : </B> account-mgr
 * <br/><B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   2017-07-11
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class AppMgr {

	
	// TODO: 获取备份到。。。
	// 从...导入备份
	
	private AppMgr() {}
	
	/**
	 * 初始化
	 */
	public static void createInstn() {
		if(DBMgr.initEnv()) {
			new _LoginWin();
			
		} else {
			SwingUtils.warn("程序无法启动: 初始化失败");
		}
	}
	
}
