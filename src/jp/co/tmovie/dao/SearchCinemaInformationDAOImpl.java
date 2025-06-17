package jp.co.tmovie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.tmovie.common.database.DataBaseManager;
import jp.co.tmovie.dto.SearchCinemaInformationDTO;

/**
 * 上映スケジュールの検索で使うDAO
 * SQLを組み立てて発行する。
 * 取得したテーブルデータをDTOに格納してReturnする。
 * @author r-mori
 */
public class SearchCinemaInformationDAOImpl implements SearchCinemaInformationDAO {

	/**
	 * 上映スケジュール検索
	 * @param theaterId 劇場ID
	 * @return DB取得結果
	 */
	@Override // オーバーライドしたメソッドであることを明示するアノテーション
	public List<SearchCinemaInformationDTO> searchCinemaInformation(String theaterId) {
		// DBインスタンスを取得する
		DataBaseManager dbManager = DataBaseManager.getInstance();
		// DBコネクションを取得する
		Connection dbConnection = dbManager.getConnection();

		// 検索結果を格納する
		List<SearchCinemaInformationDTO> SearchCinemaInformationDtoList = new ArrayList<SearchCinemaInformationDTO>();

		try {
			// 上映情報を取得するSQL
			String sql = "SELECT"
					+ " theater_nm,"
					+ " movie_start_dt, "
					+ " screen_nm, "
					+ " movie_start_tm, "
					+ " movie_nm "
					+ " FROM showing_mng"
					+ " INNER JOIN theater_info"
					+ " ON showing_mng.theater_id = theater_info.theater_id"
					+ " INNER JOIN screen_info"
					+ " ON showing_mng.screen_id = screen_info.screen_id"
					+ " INNER JOIN movie_info"
					+ " ON showing_mng.movie_id = movie_info.movie_id"
					+ " WHERE THEATER_INFO.theater_id = ?"
					// システム日付を条件とするとhitしないためコメントアウト
					//+ " AND SHOWING_MNG.movie_start_dt = CURRENT_DATE"
					+ " AND SHOWING_MNG.movie_start_tm >= CURRENT_TIMESTAMP"
					+ " ORDER BY screen_nm ASC,"
					+ " movie_start_tm ASC,"
					+ " movie_nm ASC";

			// SQLのインスタンスを生成する
			PreparedStatement statement = dbConnection.prepareStatement(sql);

			// パラメーターを設定する
			statement.setString(1, theaterId);

			// SQLを実行する
			ResultSet result = statement.executeQuery();

			//resultをSearchCinemaInformationDtoListに格納
			while (result.next()) {
				SearchCinemaInformationDTO dto = new SearchCinemaInformationDTO();
				dto.setTheaterNm(result.getString("theater_nm"));
				dto.setMovieStartDt(result.getString("movie_start_dt"));
				dto.setScreenNm(result.getString("screen_nm"));
				dto.setMovieStartTm(result.getString("movie_start_tm"));
				dto.setMovieNm(result.getString("movie_nm"));
				SearchCinemaInformationDtoList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return SearchCinemaInformationDtoList;
	}

	@Override // オーバーライドしたメソッドであることを明示するアノテーション
	public List<SearchCinemaInformationDTO> CinemaInformation() {

		DataBaseManager dbManager = DataBaseManager.getInstance();
		// DBコネクションを取得する
		Connection dbConnection = dbManager.getConnection();

		// 検索結果を格納する
		List<SearchCinemaInformationDTO> CinemaInformationDtoList = new ArrayList<SearchCinemaInformationDTO>();

		try {
			// 上映情報を取得するSQL
			String sql = "SELECT DISTINCT"
					+ " theater_nm,"
					+ " theater_info.theater_id "
					+ " FROM showing_mng"
					+ " INNER JOIN theater_info"
					+ " ON showing_mng.theater_id = theater_info.theater_id"
					+ " INNER JOIN screen_info"
					+ " ON showing_mng.screen_id = screen_info.screen_id"
					+ " INNER JOIN movie_info"
					+ " ON showing_mng.movie_id = movie_info.movie_id"
					//+ " WHERE SHOWING_MNG.movie_start_dt = CURRENT_DATE"
					//+ " AND SHOWING_MNG.movie_start_tm >= CURRENT_TIMESTAMP"
					+ " ORDER BY theater_info.theater_id";

			// SQLのインスタンスを生成する
			PreparedStatement statement = dbConnection.prepareStatement(sql);

			// SQLを実行する
			ResultSet result = statement.executeQuery();

			//resultをSearchCinemaInformationDtoListに格納
			while (result.next()) {
				SearchCinemaInformationDTO dto = new SearchCinemaInformationDTO();
				dto.setTheaterNm(result.getString("theater_nm"));
				dto.setTheaterId(result.getString("theater_id"));
				CinemaInformationDtoList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CinemaInformationDtoList;
	}

	public List<SearchCinemaInformationDTO> countChinema(String theaterId) {
		DataBaseManager dbManager = DataBaseManager.getInstance();
		// DBコネクションを取得する
		Connection dbConnection = dbManager.getConnection();

		// 検索結果を格納する
		List<SearchCinemaInformationDTO> countMovieDtoList = new ArrayList<SearchCinemaInformationDTO>();

		try {
			// 上映情報を取得するSQL
			String sql = "SELECT"
					+ " theater_nm,"
					+ " movie_info.movie_nm, "
					+ " count(movie_info.movie_nm) AS cnt"
					+ " FROM showing_mng"
					+ " INNER JOIN theater_info"
					+ " ON showing_mng.theater_id = theater_info.theater_id"
					+ " INNER JOIN screen_info"
					+ " ON showing_mng.screen_id = screen_info.screen_id"
					+ " INNER JOIN movie_info"
					+ " ON showing_mng.movie_id = movie_info.movie_id"
					+ " WHERE THEATER_INFO.theater_id = ?"
					//					+ " AND SHOWING_MNG.movie_start_dt = CURRENT_DATE"
					//					+ " AND SHOWING_MNG.movie_start_tm >= CURRENT_TIMESTAMP"
					+ " GROUP BY movie_nm"
					+ " ORDER BY THEATER_INFO.theater_id ASC;";

			// SQLのインスタンスを生成する
			PreparedStatement statement = dbConnection.prepareStatement(sql);

			// パラメーターを設定する
			statement.setString(1, theaterId);

			// SQLを実行する
			ResultSet result = statement.executeQuery();

			//resultをSearchCinemaInformationDtoListに格納
			while (result.next()) {
				SearchCinemaInformationDTO dto = new SearchCinemaInformationDTO();
				dto.setTheaterNm(result.getString("theater_nm"));
				dto.setMovieNm(result.getString("movie_info.movie_nm"));
				dto.setCountMv(result.getString("cnt"));
				countMovieDtoList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return countMovieDtoList;
	}
}