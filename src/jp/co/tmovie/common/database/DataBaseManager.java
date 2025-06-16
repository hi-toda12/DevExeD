package jp.co.tmovie.common.database;
 
 
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
 
/**

* @author r-kitada

*/

public final class DataBaseManager {

	/** 静的インスタンスの生成 */

	private static DataBaseManager instance = new DataBaseManager();

	/** DBプロパティファイルのファイルパス */

	private final String PROP_MYSQL = "resources/db/mysql.properties";

	/** データソース情報 */

	private DataSource ds;

	/**

	 * コンストラクタの不可視

	 */

	private DataBaseManager() {

		Properties dbProp = getProperties(PROP_MYSQL);

		try {

			// データソース情報オブジェクトを生成します

			// コネクションプーリングを行うため、Commons DBCPを利用します。

			this.ds = BasicDataSourceFactory.createDataSource(dbProp);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}
 
	/**

	 * シングルトンのインスタンスを返します.

	 * 

	 * @return シングルトンのインスタンス

	 */

	public static DataBaseManager getInstance() {

		return instance;

	}
 
	/**

	 * Database接続コネクション取得処理.

	 * 

	 * @return DataBase接続コネクション

	 */

	public Connection getConnection() {

		Connection conn = null;

		try {

			// データベースへの接続を確立する

			conn = this.ds.getConnection();

			// 自動コミットをオフにする

			conn.setAutoCommit(false);

		}

		catch (SQLException e) {

			e.printStackTrace();

		}

		// 接続コネクションを返却する

		return conn;

	}
 
	/**

	 * プロパティファイルの読込処理.

	 * 

	 * @param fileName

	 * @return

	 */

	private Properties getProperties(String fileName) {

		Properties props = new Properties();

		// 相対パス指定によりクラスフォルダからプロパティファイルを読み込む

		InputStream is = DataBaseManager.class.getClassLoader().getResourceAsStream(fileName);

		try {

			props.load(is);

		}

		catch (IOException e) {

			e.printStackTrace();

		}

		return props;

	}

}

 