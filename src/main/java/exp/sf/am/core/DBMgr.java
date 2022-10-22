package exp.sf.am.core;

import java.io.File;
import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import exp.libs.db.sql.SqliteUtils;
import exp.libs.db.sql.bean.DataSourceBean;
import exp.libs.envm.Charset;
import exp.libs.envm.DBType;
import exp.libs.utils.file.FileUtils;
import exp.libs.utils.file.JarUtils;
import exp.libs.utils.str.StrUtils;
import exp.sf.am.bean.TAccount;
import exp.sf.am.bean.TUser;
import exp.sf.am.utils.CryptoUtils;

/**
 * <PRE>
 * 数据库管理器.
 * </PRE>
 * <br/><B>PROJECT : </B> account-mgr
 * <br/><B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   2017-07-11
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.8
 */
class DBMgr {

	private final static String ENV_DB_SCRIPT = "/exp/sf/am/bean/AM-DB.sql";
	
	private final static String ENV_DB_DIR = "./data/";
	
	private final static String ENV_DB_NAME = ".AM";
	
	private final static String ENV_DB_PATH = ENV_DB_DIR.concat(ENV_DB_NAME);
	
	private final static DataSourceBean ds = new DataSourceBean();
	static {
		ds.setDriver(DBType.SQLITE.DRIVER);
		ds.setName(ENV_DB_PATH);
	}
	
	private DBMgr() {}
	
	/**
	 * 初始化数据库环境
	 * @return
	 */
	protected static boolean initEnv() {
		boolean isOk = true;
		File dbFile = new File(ENV_DB_PATH);
		if(!dbFile.exists()) {
			FileUtils.createDir(ENV_DB_DIR);
			Connection conn = SqliteUtils.getConn(ds);
			String script = JarUtils.read(ENV_DB_SCRIPT, Charset.ISO);
			String[] sqls = script.split(";");
			for(String sql : sqls) {
				if(StrUtils.isNotTrimEmpty(sql)) {
					isOk &= SqliteUtils.execute(conn, sql);
				}
			}
			SqliteUtils.close(conn);
			
			FileUtils.hide(dbFile);
		}
		return isOk;
	}
	
	/**
	 * 查找此软件的用户
	 * @param username 软件登陆账号
	 * @param password 软件登陆密码
	 * @return
	 */
	protected static TUser findUser(String username, String password) {
		String enUN = CryptoUtils.encode(username);
		String enPW = CryptoUtils.encode(password);
		String where = StrUtils.concat(
				TUser.getUsername$CN(), " = '", enUN, "'", " AND ", 
				TUser.getPassword$CN(), " = '", enPW, "'");
		
		Connection conn = SqliteUtils.getConn(ds);
		TUser user = TUser.queryOne(conn, where);
		SqliteUtils.close(conn);
		return user;
	}
	
	/**
	 * 注册此软件的新用户
	 * @param username 软件登陆账号
	 * @param password 软件登陆密码
	 * @return
	 */
	protected static TUser register(String username, String password) {
		String enUsername = CryptoUtils.encode(username);
		String sql = StrUtils.concat("SELECT COUNT(1) FROM ", TUser.getTableName(),  
				" WHERE ", TUser.getUsername$CN(), " = '", enUsername, "'");
		
		TUser user = null;
		Connection conn = SqliteUtils.getConn(ds);
		if(SqliteUtils.queryInt(conn, sql) == 0) {
			user = new TUser();
			user.encodeUsername(username);
			user.encodePassword(password);
			user.encodeNickname(username);
			if(TUser.insert(conn, user)) {
				user = findUser(username, password);
			} else {
				user = null;
			}
		}
		SqliteUtils.close(conn);
		return user;
	}
	
	/**
	 * 更新用户昵称
	 * @param user
	 * @param nickName
	 */
	protected static void updateNickName(TUser user, String nickName) {
		String where = StrUtils.concat(TUser.getId$CN(), " = ", user.getId());
		user.encodeNickname(nickName);
		
		Connection conn = SqliteUtils.getConn(ds);
		TUser.update(conn, user, where);
		SqliteUtils.close(conn);
	}
	
	/**
	 * 通过关键字查询用户的相关帐密记录
	 * @param user 软件用户
	 * @param keyword 关键字
	 * @return 相关帐密记录
	 */
	protected static List<TAccount> queryAccounts(TUser user, String keyword) {
		String where = StrUtils.concat(TAccount.getUserId$CN(), " = ", user.getId());
		Connection conn = SqliteUtils.getConn(ds);
		List<TAccount> accounts = TAccount.querySome(conn, where);
		if(accounts != null && StrUtils.isNotEmpty(keyword)) {
			Iterator<TAccount> its = accounts.iterator();
			while(its.hasNext()) {
				TAccount account = its.next();
				if(!account.contains(keyword)) {
					its.remove();
				}
			}
		}
		SqliteUtils.close(conn);
		return (accounts == null ? new LinkedList<TAccount>() : accounts);
	}
	
	/**
	 * 编辑帐密记录
	 * @param account
	 * @return
	 */
	protected static boolean edit(TAccount account) {
		boolean isOk = false;
		Connection conn = SqliteUtils.getConn(ds);
		if(account.getId() == null) {
			isOk = TAccount.insert(conn, account);
			
		} else {
			String where = StrUtils.concat(TAccount.getId$CN(), " = ", account.getId());
			isOk = TAccount.update(conn, account, where);
		}
		SqliteUtils.close(conn);
		return isOk;
	}
	
	/**
	 * 删除帐密记录
	 * @param account
	 * @return
	 */
	protected static boolean delete(TAccount account) {
		Connection conn = SqliteUtils.getConn(ds);
		String where = StrUtils.concat(TAccount.getId$CN(), " = ", account.getId());
		boolean isOk = TAccount.delete(conn, where);
		SqliteUtils.close(conn);
		return isOk;
	}
	
}
