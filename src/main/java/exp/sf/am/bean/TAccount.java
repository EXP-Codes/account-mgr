package exp.sf.am.bean;

import java.sql.Connection;
import java.util.List;

import exp.libs.utils.time.TimeUtils;
import exp.libs.warp.db.sql.DBUtils;
import exp.sf.am.utils.CryptoUtils;

/**
 * <PRE>
 * Table Name : T_ACCOUNT
 * Class Name : TAccount
 * </PRE>
 * <br/><B>PROJECT : </B> exp-libs
 * <br/><B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   2017-08-10 14:29:16
 * @author    EXP: 272629724@qq.com
 * @since     jdk version : jdk 1.6
 */
public class TAccount  {
    
    /** insert sql */
    public final static String SQL_INSERT = 
            "INSERT INTO T_ACCOUNT(I_ID, I_USER_ID, S_APP_NAME, S_URL, S_LOGIN_USERNAME, S_LOGIN_PASSWORD, S_QUERY_PASSWORD, S_ATM_PASSWORD, S_PAY_PASSWORD, S_SERVICE_PASSWORD, S_EMAIL, S_PHONE, S_IDCARD_NUM, S_IDCARD_NAME, S_QUESTION1, S_ANSWER1, S_QUESTION2, S_ANSWER2, S_QUESTION3, S_ANSWER3, S_REMARK, S_UPDATE_TIME) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    /** delete sql */
    public final static String SQL_DELETE = 
            "DELETE FROM T_ACCOUNT WHERE 1 = 1 ";
    
    /** update sql */
    public final static String SQL_UPDATE = 
            "UPDATE T_ACCOUNT SET I_USER_ID = ?, S_APP_NAME = ?, S_URL = ?, S_LOGIN_USERNAME = ?, S_LOGIN_PASSWORD = ?, S_QUERY_PASSWORD = ?, S_ATM_PASSWORD = ?, S_PAY_PASSWORD = ?, S_SERVICE_PASSWORD = ?, S_EMAIL = ?, S_PHONE = ?, S_IDCARD_NUM = ?, S_IDCARD_NAME = ?, S_QUESTION1 = ?, S_ANSWER1 = ?, S_QUESTION2 = ?, S_ANSWER2 = ?, S_QUESTION3 = ?, S_ANSWER3 = ?, S_REMARK = ?, S_UPDATE_TIME = ? WHERE 1 = 1 ";
    
    /** select sql */
    public final static String SQL_SELECT = 
            "SELECT I_ID AS 'id', I_USER_ID AS 'userId', S_APP_NAME AS 'appName', S_URL AS 'url', S_LOGIN_USERNAME AS 'loginUsername', S_LOGIN_PASSWORD AS 'loginPassword', S_QUERY_PASSWORD AS 'queryPassword', S_ATM_PASSWORD AS 'atmPassword', S_PAY_PASSWORD AS 'payPassword', S_SERVICE_PASSWORD AS 'servicePassword', S_EMAIL AS 'email', S_PHONE AS 'phone', S_IDCARD_NUM AS 'idcardNum', S_IDCARD_NAME AS 'idcardName', S_QUESTION1 AS 'question1', S_ANSWER1 AS 'answer1', S_QUESTION2 AS 'question2', S_ANSWER2 AS 'answer2', S_QUESTION3 AS 'question3', S_ANSWER3 AS 'answer3', S_REMARK AS 'remark', S_UPDATE_TIME AS 'updateTime' FROM T_ACCOUNT WHERE 1 = 1 ";

    /** I_ID */
    private Integer id;

    /** I_USER_ID */
    private Integer userId;

    /** S_APP_NAME */
    private String appName;

    /** S_URL */
    private String url;

    /** S_LOGIN_USERNAME */
    private String loginUsername;

    /** S_LOGIN_PASSWORD */
    private String loginPassword;

    /** S_QUERY_PASSWORD */
    private String queryPassword;

    /** S_ATM_PASSWORD */
    private String atmPassword;

    /** S_PAY_PASSWORD */
    private String payPassword;

    /** S_SERVICE_PASSWORD */
    private String servicePassword;

    /** S_EMAIL */
    private String email;

    /** S_PHONE */
    private String phone;

    /** S_IDCARD_NUM */
    private String idcardNum;

    /** S_IDCARD_NAME */
    private String idcardName;

    /** S_QUESTION1 */
    private String question1;

    /** S_ANSWER1 */
    private String answer1;

    /** S_QUESTION2 */
    private String question2;

    /** S_ANSWER2 */
    private String answer2;

    /** S_QUESTION3 */
    private String question3;

    /** S_ANSWER3 */
    private String answer3;

    /** S_REMARK */
    private String remark;

    /** S_UPDATE_TIME */
    private String updateTime;
    
    public TAccount() {}
    
    public TAccount(int userId) {
    	this.userId = userId;
    }
    
    /**
     * insert the bean of TAccount to db.
     * 
     * @param conn : the connection of db
     * @param bean : a bean of the data
     * @return effect of row number
     */
    public static boolean insert(Connection conn, TAccount bean) {
        Object[] params = new Object[] {
                bean.getId(),
                bean.getUserId(),
                bean.appName,
                bean.url,
                bean.loginUsername,
                bean.loginPassword,
                bean.queryPassword,
                bean.atmPassword,
                bean.payPassword,
                bean.servicePassword,
                bean.email,
                bean.phone,
                bean.idcardNum,
                bean.idcardName,
                bean.question1,
                bean.answer1,
                bean.question2,
                bean.answer2,
                bean.question3,
                bean.answer3,
                bean.remark, 
                TimeUtils.getSysDate()
        };
        return DBUtils.execute(conn, TAccount.SQL_INSERT, params);
    }
    
    /**
     * delete some bean of TAccount from db with some conditions.
     * 
     * @param conn : the connection of db
     * @param where : 
     *      The conditions of sql, must start with "and" (Ignoring the case).
     *      For example: "and id > 1 and name like Exp%"
     * @return effect of row number
     */
    public static boolean delete(Connection conn, String where) {
        StringBuilder sql = new StringBuilder();
        sql.append(TAccount.SQL_DELETE);
        
        if (where != null && !"".equals(where.trim())) {
            if(false == where.toLowerCase().trim().startsWith("and")) {
                sql.append("AND ");
            }
            sql.append(where);
        }
        return DBUtils.execute(conn, sql.toString());
    }
    
    /**
     * update some bean of TAccount to db with some conditions.
     * 
     * @param conn : the connection of db
     * @param bean : a bean of the data
     * @param where :
     *      The conditions of sql, must start with "and" (Ignoring the case).
     *      For example: "and id > 1 and name like Exp%"
     * @return effect of row number
     */
    public static boolean update(Connection conn, TAccount bean, String where) {
        StringBuilder sql = new StringBuilder();
        sql.append(TAccount.SQL_UPDATE);
        
        if (where != null && !"".equals(where.trim())) {
            if(false == where.toLowerCase().trim().startsWith("and")) {
                sql.append("AND ");
            }
            sql.append(where);
        }
        Object[] params = new Object[] {
                bean.getUserId(),
                bean.appName,
                bean.url,
                bean.loginUsername,
                bean.loginPassword,
                bean.queryPassword,
                bean.atmPassword,
                bean.payPassword,
                bean.servicePassword,
                bean.email,
                bean.phone,
                bean.idcardNum,
                bean.idcardName,
                bean.question1,
                bean.answer1,
                bean.question2,
                bean.answer2,
                bean.question3,
                bean.answer3,
                bean.remark, 
                TimeUtils.getSysDate()
        };
        return DBUtils.execute(conn, sql.toString(), params);
    }    
    
    /**
     * query all beans of TAccount from db.
     * 
     * @param conn : the connection of db
     * @return all beans of the data set
     */
    public static List<TAccount> queryAll(Connection conn) {
        return querySome(conn, null);
    }
    
    /**
     * query a bean of TAccount from db with some conditions.
     * If the conditions <B>can't</B> lock the range of one record,
     * you will get <B>the first record</B> or <B>null</B>.
     *
     * @param conn : the connection of db
     * @param where :
     *      The conditions of sql, must start with "and" (Ignoring the case).
     *      For example: "and id > 1 and name like Exp%"
     * @return a beans of the data
     */
    public static TAccount queryOne(Connection conn, String where) {
        TAccount bean = null;
        List<TAccount> beans = querySome(conn, where);
        if(beans != null && beans.size() > 0) {
            bean = beans.get(0);
        }
        return bean;
    }
    
    /**
     * query some beans of TAccount from db with some conditions.
     * 
     * @param conn : the connection of db
     * @param where :
     *      The conditions of sql, must start with "and" (Ignoring the case).
     *      For example: "and id > 1 and name like Exp%"
     * @return some beans of the data set
     */
    public static List<TAccount> querySome(Connection conn, String where) {
        StringBuilder sql = new StringBuilder();
        sql.append(TAccount.SQL_SELECT);
        
        if (where != null && !"".equals(where.trim())) {
            if(false == where.toLowerCase().trim().startsWith("and")) {
                sql.append("AND ");
            }
            sql.append(where);
        }
        return DBUtils.query(TAccount.class, conn, sql.toString());
    }
    
    /**
     * getId
     * @return Integer
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * setId
     * @param id id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * getUserId
     * @return Integer
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * setUserId
     * @param userId userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * getAppName
     * @return String
     */
    public String getAppName() {
        return CryptoUtils.decode(this.appName);
    }

    /**
     * setAppName
     * @param appName appName to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }
    
    public void encodeAppName(String appName) {
        this.appName = CryptoUtils.encode(appName);
    }

    /**
     * getUrl
     * @return String
     */
    public String getUrl() {
        return CryptoUtils.decode(this.url);
    }

    /**
     * setUrl
     * @param url url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    public void encodeUrl(String url) {
        this.url = CryptoUtils.encode(url);
    }

    /**
     * getLoginUsername
     * @return String
     */
    public String getLoginUsername() {
        return CryptoUtils.decode(this.loginUsername);
    }

    /**
     * setLoginUsername
     * @param loginUsername loginUsername to set
     */
    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }
    
    public void encodeLoginUsername(String loginUsername) {
        this.loginUsername = CryptoUtils.encode(loginUsername);
    }

    /**
     * getLoginPassword
     * @return String
     */
    public String getLoginPassword() {
        return CryptoUtils.decode(this.loginPassword);
    }

    /**
     * setLoginPassword
     * @param loginPassword loginPassword to set
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    
    public void encodeLoginPassword(String loginPassword) {
        this.loginPassword = CryptoUtils.encode(loginPassword);
    }

    /**
     * getQueryPassword
     * @return String
     */
    public String getQueryPassword() {
        return CryptoUtils.decode(this.queryPassword);
    }

    /**
     * setQueryPassword
     * @param queryPassword queryPassword to set
     */
    public void setQueryPassword(String queryPassword) {
        this.queryPassword = queryPassword;
    }
    
    public void encodeQueryPassword(String queryPassword) {
        this.queryPassword = CryptoUtils.encode(queryPassword);
    }

    /**
     * getAtmPassword
     * @return String
     */
    public String getAtmPassword() {
        return CryptoUtils.decode(this.atmPassword);
    }

    /**
     * setAtmPassword
     * @param atmPassword atmPassword to set
     */
    public void setAtmPassword(String atmPassword) {
        this.atmPassword = atmPassword;
    }
    
    public void encodeAtmPassword(String atmPassword) {
        this.atmPassword = CryptoUtils.encode(atmPassword);
    }

    /**
     * getPayPassword
     * @return String
     */
    public String getPayPassword() {
        return CryptoUtils.decode(this.payPassword);
    }

    /**
     * setPayPassword
     * @param payPassword payPassword to set
     */
    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }
    
    public void encodePayPassword(String payPassword) {
        this.payPassword = CryptoUtils.encode(payPassword);
    }

    /**
     * getServicePassword
     * @return String
     */
    public String getServicePassword() {
        return CryptoUtils.decode(this.servicePassword);
    }

    /**
     * setServicePassword
     * @param servicePassword servicePassword to set
     */
    public void setServicePassword(String servicePassword) {
        this.servicePassword = servicePassword;
    }
    
    public void encodeServicePassword(String servicePassword) {
        this.servicePassword = CryptoUtils.encode(servicePassword);
    }

    /**
     * getEmail
     * @return String
     */
    public String getEmail() {
        return CryptoUtils.decode(this.email);
    }

    /**
     * setEmail
     * @param email email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void encodeEmail(String email) {
        this.email = CryptoUtils.encode(email);
    }

    /**
     * getPhone
     * @return String
     */
    public String getPhone() {
        return CryptoUtils.decode(this.phone);
    }

    /**
     * setPhone
     * @param phone phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void encodePhone(String phone) {
        this.phone = CryptoUtils.encode(phone);
    }

    /**
     * getIdcardNum
     * @return String
     */
    public String getIdcardNum() {
        return CryptoUtils.decode(this.idcardNum);
    }

    /**
     * setIdcardNum
     * @param idcardNum idcardNum to set
     */
    public void setIdcardNum(String idcardNum) {
        this.idcardNum = idcardNum;
    }
    
    public void encodeIdcardNum(String idcardNum) {
        this.idcardNum = CryptoUtils.encode(idcardNum);
    }

    /**
     * getIdcardName
     * @return String
     */
    public String getIdcardName() {
        return CryptoUtils.decode(this.idcardName);
    }

    /**
     * setIdcardName
     * @param idcardName idcardName to set
     */
    public void setIdcardName(String idcardName) {
        this.idcardName = idcardName;
    }
    
    public void encodeIdcardName(String idcardName) {
        this.idcardName = CryptoUtils.encode(idcardName);
    }

    /**
     * getQuestion1
     * @return String
     */
    public String getQuestion1() {
        return CryptoUtils.decode(this.question1);
    }

    /**
     * setQuestion1
     * @param question1 question1 to set
     */
    public void setQuestion1(String question1) {
        this.question1 = question1;
    }
    
    public void encodeQuestion1(String question1) {
        this.question1 = CryptoUtils.encode(question1);
    }

    /**
     * getAnswer1
     * @return String
     */
    public String getAnswer1() {
        return CryptoUtils.decode(this.answer1);
    }

    /**
     * setAnswer1
     * @param answer1 answer1 to set
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
    
    public void encodeAnswer1(String answer1) {
        this.answer1 = CryptoUtils.encode(answer1);
    }

    /**
     * getQuestion2
     * @return String
     */
    public String getQuestion2() {
        return CryptoUtils.decode(this.question2);
    }

    /**
     * setQuestion2
     * @param question2 question2 to set
     */
    public void setQuestion2(String question2) {
        this.question2 = question2;
    }
    
    public void encodeQuestion2(String question2) {
        this.question2 = CryptoUtils.encode(question2);
    }

    /**
     * getAnswer2
     * @return String
     */
    public String getAnswer2() {
        return CryptoUtils.decode(this.answer2);
    }

    /**
     * setAnswer2
     * @param answer2 answer2 to set
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
    
    public void encodeAnswer2(String answer2) {
        this.answer2 = CryptoUtils.encode(answer2);
    }

    /**
     * getQuestion3
     * @return String
     */
    public String getQuestion3() {
        return CryptoUtils.decode(this.question3);
    }

    /**
     * setQuestion3
     * @param question3 question3 to set
     */
    public void setQuestion3(String question3) {
        this.question3 = question3;
    }
    
    public void encodeQuestion3(String question3) {
        this.question3 = CryptoUtils.encode(question3);
    }

    /**
     * getAnswer3
     * @return String
     */
    public String getAnswer3() {
        return CryptoUtils.decode(this.answer3);
    }

    /**
     * setAnswer3
     * @param answer3 answer3 to set
     */
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
    
    public void encodeAnswer3(String answer3) {
        this.answer3 = CryptoUtils.encode(answer3);
    }

    /**
     * getRemark
     * @return String
     */
    public String getRemark() {
        return CryptoUtils.decode(this.remark);
    }

    /**
     * setRemark
     * @param remark remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public void encodeRemark(String remark) {
        this.remark = CryptoUtils.encode(remark);
    }
    
    /**
     * getUpdateTime
     * @return String
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * setUpdateTime
     * @param updateTime updateTime to set
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    
    /**
     * get column name
     * @return I_ID
     */
    public static String getId$CN() {
        return "I_ID";
    }

    /**
     * get column name
     * @return I_USER_ID
     */
    public static String getUserId$CN() {
        return "I_USER_ID";
    }

    /**
     * get column name
     * @return S_APP_NAME
     */
    public static String getAppName$CN() {
        return "S_APP_NAME";
    }

    /**
     * get column name
     * @return S_URL
     */
    public static String getUrl$CN() {
        return "S_URL";
    }

    /**
     * get column name
     * @return S_LOGIN_USERNAME
     */
    public static String getLoginUsername$CN() {
        return "S_LOGIN_USERNAME";
    }

    /**
     * get column name
     * @return S_LOGIN_PASSWORD
     */
    public static String getLoginPassword$CN() {
        return "S_LOGIN_PASSWORD";
    }

    /**
     * get column name
     * @return S_QUERY_PASSWORD
     */
    public static String getQueryPassword$CN() {
        return "S_QUERY_PASSWORD";
    }

    /**
     * get column name
     * @return S_ATM_PASSWORD
     */
    public static String getAtmPassword$CN() {
        return "S_ATM_PASSWORD";
    }

    /**
     * get column name
     * @return S_PAY_PASSWORD
     */
    public static String getPayPassword$CN() {
        return "S_PAY_PASSWORD";
    }

    /**
     * get column name
     * @return S_SERVICE_PASSWORD
     */
    public static String getServicePassword$CN() {
        return "S_SERVICE_PASSWORD";
    }

    /**
     * get column name
     * @return S_EMAIL
     */
    public static String getEmail$CN() {
        return "S_EMAIL";
    }

    /**
     * get column name
     * @return S_PHONE
     */
    public static String getPhone$CN() {
        return "S_PHONE";
    }

    /**
     * get column name
     * @return S_IDCARD_NUM
     */
    public static String getIdcardNum$CN() {
        return "S_IDCARD_NUM";
    }

    /**
     * get column name
     * @return S_IDCARD_NAME
     */
    public static String getIdcardName$CN() {
        return "S_IDCARD_NAME";
    }

    /**
     * get column name
     * @return S_QUESTION1
     */
    public static String getQuestion1$CN() {
        return "S_QUESTION1";
    }

    /**
     * get column name
     * @return S_ANSWER1
     */
    public static String getAnswer1$CN() {
        return "S_ANSWER1";
    }

    /**
     * get column name
     * @return S_QUESTION2
     */
    public static String getQuestion2$CN() {
        return "S_QUESTION2";
    }

    /**
     * get column name
     * @return S_ANSWER2
     */
    public static String getAnswer2$CN() {
        return "S_ANSWER2";
    }

    /**
     * get column name
     * @return S_QUESTION3
     */
    public static String getQuestion3$CN() {
        return "S_QUESTION3";
    }

    /**
     * get column name
     * @return S_ANSWER3
     */
    public static String getAnswer3$CN() {
        return "S_ANSWER3";
    }

    /**
     * get column name
     * @return S_REMARK
     */
    public static String getRemark$CN() {
        return "S_REMARK";
    }
    
    /**
     * get column name
     * @return S_UPDATE_TIME
     */
    public static String getUpdateTime$CN() {
        return "S_UPDATE_TIME";
    }

    /**
     * get java name
     * @return id
     */
    public static String getId$JN() {
        return "id";
    }

    /**
     * get java name
     * @return userId
     */
    public static String getUserId$JN() {
        return "userId";
    }

    /**
     * get java name
     * @return appName
     */
    public static String getAppName$JN() {
        return "appName";
    }

    /**
     * get java name
     * @return url
     */
    public static String getUrl$JN() {
        return "url";
    }

    /**
     * get java name
     * @return loginUsername
     */
    public static String getLoginUsername$JN() {
        return "loginUsername";
    }

    /**
     * get java name
     * @return loginPassword
     */
    public static String getLoginPassword$JN() {
        return "loginPassword";
    }

    /**
     * get java name
     * @return queryPassword
     */
    public static String getQueryPassword$JN() {
        return "queryPassword";
    }

    /**
     * get java name
     * @return atmPassword
     */
    public static String getAtmPassword$JN() {
        return "atmPassword";
    }

    /**
     * get java name
     * @return payPassword
     */
    public static String getPayPassword$JN() {
        return "payPassword";
    }

    /**
     * get java name
     * @return servicePassword
     */
    public static String getServicePassword$JN() {
        return "servicePassword";
    }

    /**
     * get java name
     * @return email
     */
    public static String getEmail$JN() {
        return "email";
    }

    /**
     * get java name
     * @return phone
     */
    public static String getPhone$JN() {
        return "phone";
    }

    /**
     * get java name
     * @return idcardNum
     */
    public static String getIdcardNum$JN() {
        return "idcardNum";
    }

    /**
     * get java name
     * @return idcardName
     */
    public static String getIdcardName$JN() {
        return "idcardName";
    }

    /**
     * get java name
     * @return question1
     */
    public static String getQuestion1$JN() {
        return "question1";
    }

    /**
     * get java name
     * @return answer1
     */
    public static String getAnswer1$JN() {
        return "answer1";
    }

    /**
     * get java name
     * @return question2
     */
    public static String getQuestion2$JN() {
        return "question2";
    }

    /**
     * get java name
     * @return answer2
     */
    public static String getAnswer2$JN() {
        return "answer2";
    }

    /**
     * get java name
     * @return question3
     */
    public static String getQuestion3$JN() {
        return "question3";
    }

    /**
     * get java name
     * @return answer3
     */
    public static String getAnswer3$JN() {
        return "answer3";
    }

    /**
     * get java name
     * @return remark
     */
    public static String getRemark$JN() {
        return "remark";
    }
    
    /**
     * get java name
     * @return updateTime
     */
    public static String getUpdateTime$JN() {
        return "updateTime";
    }

    /**
     * get all column names
     * @return String
     */
    public static String getAllColNames() {
        return "I_ID, I_USER_ID, S_APP_NAME, S_URL, S_LOGIN_USERNAME, S_LOGIN_PASSWORD, S_QUERY_PASSWORD, S_ATM_PASSWORD, S_PAY_PASSWORD, S_SERVICE_PASSWORD, S_EMAIL, S_PHONE, S_IDCARD_NUM, S_IDCARD_NAME, S_QUESTION1, S_ANSWER1, S_QUESTION2, S_ANSWER2, S_QUESTION3, S_ANSWER3, S_REMARK, S_UPDATE_TIME";
    }

    /**
     * get all java names
     * @return String
     */
    public static String getAllJavaNames() {
        return "id, userId, appName, url, loginUsername, loginPassword, queryPassword, atmPassword, payPassword, servicePassword, email, phone, idcardNum, idcardName, question1, answer1, question2, answer2, question3, answer3, remark, updateTime";
    }

    /**
     * get table name
     * @return String
     */
    public static String getTableName() {
        return "T_ACCOUNT";
    }

    /**
     * get class name
     * @return String
     */
    public static String getClassName() {
        return "TAccount";
    }
    
    public boolean contains(String keyword) {
    	boolean contains = false;
    	if(getAppName().contains(keyword) ||
				getUrl().contains(keyword) || 
				getLoginUsername().contains(keyword) || 
				getLoginPassword().contains(keyword) || 
				getQueryPassword().contains(keyword) || 
				getAtmPassword().contains(keyword) || 
				getPayPassword().contains(keyword) || 
				getServicePassword().contains(keyword) || 
				getEmail().contains(keyword) || 
				getPhone().contains(keyword) || 
				getIdcardNum().contains(keyword) || 
				getIdcardName().contains(keyword) || 
				getQuestion1().contains(keyword) || 
				getAnswer1().contains(keyword) || 
				getQuestion2().contains(keyword) || 
				getAnswer2().contains(keyword) || 
				getQuestion3().contains(keyword) || 
				getAnswer3().contains(keyword) || 
				getRemark().contains(keyword) || 
				getUpdateTime().contains(keyword)) {
    		contains = true;
		}
    	return contains;
    }
    
    public String toInfo() {
    	StringBuilder sb = new StringBuilder();
        sb.append("详细帐密信息(").append(getUpdateTime()).append(") : \r\n");
        sb.append(" 应用名称").append(" : ").append(this.getAppName()).append("\r\n");
        sb.append(" 相关网址").append(" : ").append(this.getUrl()).append("\r\n");
        sb.append(" 登陆账号").append(" : ").append(this.getLoginUsername()).append("\r\n");
        sb.append(" 登陆密码").append(" : ").append(this.getLoginPassword()).append("\r\n");
        sb.append(" 查询密码").append(" : ").append(this.getQueryPassword()).append("\r\n");
        sb.append(" 取款密码").append(" : ").append(this.getAtmPassword()).append("\r\n");
        sb.append(" 支付密码").append(" : ").append(this.getPayPassword()).append("\r\n");
        sb.append(" 服务密码").append(" : ").append(this.getServicePassword()).append("\r\n");
        sb.append(" 绑定邮箱").append(" : ").append(this.getEmail()).append("\r\n");
        sb.append(" 绑定手机").append(" : ").append(this.getPhone()).append("\r\n");
        sb.append(" 绑定身份证号码").append(" : ").append(this.getIdcardNum()).append("\r\n");
        sb.append(" 绑定身份证姓名").append(" : ").append(this.getIdcardName()).append("\r\n");
        sb.append(" 密码提示问题1").append(" : ").append(this.getQuestion1()).append("\r\n");
        sb.append(" 密码提示答案1").append(" : ").append(this.getAnswer1()).append("\r\n");
        sb.append(" 密码提示问题2").append(" : ").append(this.getQuestion2()).append("\r\n");
        sb.append(" 密码提示答案2").append(" : ").append(this.getAnswer2()).append("\r\n");
        sb.append(" 密码提示问题3").append(" : ").append(this.getQuestion3()).append("\r\n");
        sb.append(" 密码提示答案3").append(" : ").append(this.getAnswer3()).append("\r\n");
        sb.append(" 备注").append(" : ").append(this.getRemark()).append("\r\n");
        return sb.toString();
    }
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("T_ACCOUNT/TAccount: {\r\n");
        sb.append("\tI_ID/id").append(" = ").append(this.getId()).append("\r\n");
        sb.append("\tI_USER_ID/userId").append(" = ").append(this.getUserId()).append("\r\n");
        sb.append("\tS_APP_NAME/appName").append(" = ").append(this.getAppName()).append("\r\n");
        sb.append("\tS_URL/url").append(" = ").append(this.getUrl()).append("\r\n");
        sb.append("\tS_LOGIN_USERNAME/loginUsername").append(" = ").append(this.getLoginUsername()).append("\r\n");
        sb.append("\tS_LOGIN_PASSWORD/loginPassword").append(" = ").append(this.getLoginPassword()).append("\r\n");
        sb.append("\tS_QUERY_PASSWORD/queryPassword").append(" = ").append(this.getQueryPassword()).append("\r\n");
        sb.append("\tS_ATM_PASSWORD/atmPassword").append(" = ").append(this.getAtmPassword()).append("\r\n");
        sb.append("\tS_PAY_PASSWORD/payPassword").append(" = ").append(this.getPayPassword()).append("\r\n");
        sb.append("\tS_SERVICE_PASSWORD/servicePassword").append(" = ").append(this.getServicePassword()).append("\r\n");
        sb.append("\tS_EMAIL/email").append(" = ").append(this.getEmail()).append("\r\n");
        sb.append("\tS_PHONE/phone").append(" = ").append(this.getPhone()).append("\r\n");
        sb.append("\tS_IDCARD_NUM/idcardNum").append(" = ").append(this.getIdcardNum()).append("\r\n");
        sb.append("\tS_IDCARD_NAME/idcardName").append(" = ").append(this.getIdcardName()).append("\r\n");
        sb.append("\tS_QUESTION1/question1").append(" = ").append(this.getQuestion1()).append("\r\n");
        sb.append("\tS_ANSWER1/answer1").append(" = ").append(this.getAnswer1()).append("\r\n");
        sb.append("\tS_QUESTION2/question2").append(" = ").append(this.getQuestion2()).append("\r\n");
        sb.append("\tS_ANSWER2/answer2").append(" = ").append(this.getAnswer2()).append("\r\n");
        sb.append("\tS_QUESTION3/question3").append(" = ").append(this.getQuestion3()).append("\r\n");
        sb.append("\tS_ANSWER3/answer3").append(" = ").append(this.getAnswer3()).append("\r\n");
        sb.append("\tS_REMARK/remark").append(" = ").append(this.getRemark()).append("\r\n");
        sb.append("\tS_UPDATE_TIME/updateTime").append(" = ").append(this.getUpdateTime()).append("\r\n");
        sb.append("}\r\n");
        return sb.toString();
    }
}
