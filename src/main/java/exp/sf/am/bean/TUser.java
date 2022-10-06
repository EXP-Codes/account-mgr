package exp.sf.am.bean;

import java.sql.Connection;
import java.util.List;

import exp.libs.db.sql.DBUtils;
import exp.sf.am.utils.CryptoUtils;

/**
 * <PRE>
 * Table Name : T_USER
 * Class Name : TUser
 * </PRE>
 * <br/><B>PROJECT : </B> exp-libs
 * <br/><B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   2017-08-10 14:29:16
 * @author    EXP: 272629724@qq.com
 * @since     jdk version : jdk 1.6
 */
public class TUser  {
    
    /** insert sql */
    public final static String SQL_INSERT = 
            "INSERT INTO T_USER(I_ID, S_USERNAME, S_PASSWORD, S_NICKNAME) VALUES(?, ?, ?, ?)";
    
    /** delete sql */
    public final static String SQL_DELETE = 
            "DELETE FROM T_USER WHERE 1 = 1 ";
    
    /** update sql */
    public final static String SQL_UPDATE = 
            "UPDATE T_USER SET S_USERNAME = ?, S_PASSWORD = ?, S_NICKNAME = ? WHERE 1 = 1 ";
    
    /** select sql */
    public final static String SQL_SELECT = 
            "SELECT I_ID AS 'id', S_USERNAME AS 'username', S_PASSWORD AS 'password', S_NICKNAME AS 'nickname' FROM T_USER WHERE 1 = 1 ";

    /** I_ID */
    private Integer id;

    /** S_USERNAME */
    private String username;

    /** S_PASSWORD */
    private String password;

    /** S_NICKNAME */
    private String nickname;

    /**
     * insert the bean of TUser to db.
     * 
     * @param conn : the connection of db
     * @param bean : a bean of the data
     * @return effect of row number
     */
    public static boolean insert(Connection conn, TUser bean) {
        Object[] params = new Object[] {
        		bean.id,
                bean.username,
                bean.password,
                bean.nickname
        };
        return DBUtils.execute(conn, TUser.SQL_INSERT, params);
    }
    
    /**
     * delete some bean of TUser from db with some conditions.
     * 
     * @param conn : the connection of db
     * @param where : 
     *      The conditions of sql, must start with "and" (Ignoring the case).
     *      For example: "and id > 1 and name like Exp%"
     * @return effect of row number
     */
    public static boolean delete(Connection conn, String where) {
        StringBuilder sql = new StringBuilder();
        sql.append(TUser.SQL_DELETE);
        
        if (where != null && !"".equals(where.trim())) {
            if(false == where.toLowerCase().trim().startsWith("and")) {
                sql.append("AND ");
            }
            sql.append(where);
        }
        return DBUtils.execute(conn, sql.toString());
    }
    
    /**
     * update some bean of TUser to db with some conditions.
     * 
     * @param conn : the connection of db
     * @param bean : a bean of the data
     * @param where :
     *      The conditions of sql, must start with "and" (Ignoring the case).
     *      For example: "and id > 1 and name like Exp%"
     * @return effect of row number
     */
    public static boolean update(Connection conn, TUser bean, String where) {
        StringBuilder sql = new StringBuilder();
        sql.append(TUser.SQL_UPDATE);
        
        if (where != null && !"".equals(where.trim())) {
            if(false == where.toLowerCase().trim().startsWith("and")) {
                sql.append("AND ");
            }
            sql.append(where);
        }
        Object[] params = new Object[] {
                bean.username,
                bean.password,
                bean.nickname
        };
        return DBUtils.execute(conn, sql.toString(), params);
    }    
    
    /**
     * query all beans of TUser from db.
     * 
     * @param conn : the connection of db
     * @return all beans of the data set
     */
    public static List<TUser> queryAll(Connection conn) {
        return querySome(conn, null);
    }
    
    /**
     * query a bean of TUser from db with some conditions.
     * If the conditions <B>can't</B> lock the range of one record,
     * you will get <B>the first record</B> or <B>null</B>.
     *
     * @param conn : the connection of db
     * @param where :
     *      The conditions of sql, must start with "and" (Ignoring the case).
     *      For example: "and id > 1 and name like Exp%"
     * @return a beans of the data
     */
    public static TUser queryOne(Connection conn, String where) {
        TUser bean = null;
        List<TUser> beans = querySome(conn, where);
        if(beans != null && beans.size() > 0) {
            bean = beans.get(0);
        }
        return bean;
    }
    
    /**
     * query some beans of TUser from db with some conditions.
     * 
     * @param conn : the connection of db
     * @param where :
     *      The conditions of sql, must start with "and" (Ignoring the case).
     *      For example: "and id > 1 and name like Exp%"
     * @return some beans of the data set
     */
    public static List<TUser> querySome(Connection conn, String where) {
        StringBuilder sql = new StringBuilder();
        sql.append(TUser.SQL_SELECT);
        
        if (where != null && !"".equals(where.trim())) {
            if(false == where.toLowerCase().trim().startsWith("and")) {
                sql.append("AND ");
            }
            sql.append(where);
        }
        return DBUtils.query(TUser.class, conn, sql.toString());
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
     * getUsername
     * @return String
     */
    public String getUsername() {
        return CryptoUtils.decode(this.username);
    }

    /**
     * setUsername
     * @param username username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void encodeUsername(String username) {
        this.username = CryptoUtils.encode(username);
    }

    /**
     * getPassword
     * @return String
     */
    public String getPassword() {
        return CryptoUtils.decode(this.password);
    }

    /**
     * setPassword
     * @param password password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void encodePassword(String password) {
        this.password = CryptoUtils.encode(password);
    }

    /**
     * getNickname
     * @return String
     */
    public String getNickname() {
        return CryptoUtils.decode(this.nickname);
    }

    /**
     * setNickname
     * @param nickname nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public void encodeNickname(String nickname) {
        this.nickname = CryptoUtils.encode(nickname);
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
     * @return S_USERNAME
     */
    public static String getUsername$CN() {
        return "S_USERNAME";
    }

    /**
     * get column name
     * @return S_PASSWORD
     */
    public static String getPassword$CN() {
        return "S_PASSWORD";
    }

    /**
     * get column name
     * @return S_NICKNAME
     */
    public static String getNickname$CN() {
        return "S_NICKNAME";
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
     * @return username
     */
    public static String getUsername$JN() {
        return "username";
    }

    /**
     * get java name
     * @return password
     */
    public static String getPassword$JN() {
        return "password";
    }

    /**
     * get java name
     * @return nickname
     */
    public static String getNickname$JN() {
        return "nickname";
    }

    /**
     * get all column names
     * @return String
     */
    public static String getAllColNames() {
        return "I_ID, S_USERNAME, S_PASSWORD, S_NICKNAME";
    }

    /**
     * get all java names
     * @return String
     */
    public static String getAllJavaNames() {
        return "id, username, password, nickname";
    }

    /**
     * get table name
     * @return String
     */
    public static String getTableName() {
        return "T_USER";
    }

    /**
     * get class name
     * @return String
     */
    public static String getClassName() {
        return "TUser";
    }
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("T_USER/TUser: {\r\n");
        sb.append("\tI_ID/id").append(" = ").append(this.getId()).append("\r\n");
        sb.append("\tS_USERNAME/username").append(" = ").append(this.getUsername()).append("\r\n");
        sb.append("\tS_PASSWORD/password").append(" = ").append(this.getPassword()).append("\r\n");
        sb.append("\tS_NICKNAME/nickname").append(" = ").append(this.getNickname()).append("\r\n");
        sb.append("}\r\n");
        return sb.toString();
    }
}
