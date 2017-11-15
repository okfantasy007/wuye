package util;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * DB工具类
 * @author Administrator
 *
 */
public class DB {
	private static Properties properties = new Properties();
	private static String dataType = null;
	private static String driver = null;
	private static String url = null;
	private static String user = null;
	private static String pwd = null;
	
	static{
		try {
			properties.load(DB.class.getClassLoader().getResourceAsStream("DataBaseUtil.properties"));
			dataType = properties.getProperty("datatype");
			driver = properties.getProperty(dataType+"Driver");
			url = properties.getProperty(dataType+"Url");
			user = properties.getProperty(dataType+"User");
			pwd = properties.getProperty(dataType+"Password");
			Class.forName(driver);
			System.out.println("----- dataType : "+dataType+" -----");
			System.out.println("----- driver : "+driver+" -----");
			System.out.println("----- url : "+url+" -----");
			System.out.println("----- user : "+user+" -----");
			System.out.println("----- pwd : "+pwd+" -----");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过四要素获取connection
	 * @return
	 */
	public static Connection getConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * 关闭connection
	 * @param connection
	 */
	public static void closeConnection(Connection connection){
		if (null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 根据connection拿到statement
	 * @param connection
	 * @return
	 */
	public static Statement getStatement(Connection connection){
		if (null == connection) {
			connection = getConnection();
		}
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	/**
	 * 通过connection和一个sql语句拿到预处理清单对象
	 * @param connection
	 * @param sql
	 * @return
	 */
	public static PreparedStatement getPreparedStatement(Connection connection,String sql){
		if (null == connection) {
			connection = getConnection();
		}
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}
	
	/**
	 * 关闭清单对象
	 * @param statement
	 */
	public static void closeStatement(Statement statement){
		if (null != statement) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭结果集
	 * @param resultSet
	 */
	public static void closeResultSet(ResultSet resultSet){
		if (null != resultSet) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭所有，释放资源
	 * @param connection
	 * @param statement
	 * @param resultSet
	 */
	public static void closeAll(Connection connection,Statement statement,ResultSet resultSet){
		closeResultSet(resultSet);
		closeStatement(statement);
		closeResultSet(resultSet);
	}
}
