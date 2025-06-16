package jp.co.tmovie.logic;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import jp.co.tmovie.dao.SearchCinemaInformationDAO;
import jp.co.tmovie.dao.SearchCinemaInformationDAOImpl;
import jp.co.tmovie.dto.SearchCinemaInformationDTO;
import jp.co.tmovie.mybatis.MybatisDataBaseManager;
import jp.co.tmovie.mybatis.mapper.SearchCinemainfomation;

/**
 * 映画館の上映スケジュールを検索し、検索結果を表示するロジック
 * @author r-kitada
 */
public class SearchCinemaInformationLogic {
	/**
	 * 検索処理を実行する
	 */

	//メニューに戻るためのメニュー番号
	private static final String MENU_BACK = "0";
	//システム終了用のメニュー番号
	private static final String END_SELECT = "9";

	/**
	 * 
	 * @return
	 */
	public boolean execute() throws IOException {
		/**
		 * SQLのWHEREに必要な値を取得する。
		 * DAOを呼び出してテーブルからデータを取得する。
		 * 取得したテーブルデータの表示を行う。
		 */

		// Scanner
		@SuppressWarnings("resource") // 「scannerが閉じられることはありません」ワーニングが出ないようにするアノテーション
		Scanner scanner = new Scanner(System.in);

		System.out.println("**************************");
		System.out.println("劇場IDを入力してください");
		System.out.print("入力：");

		//劇場IDを取得
		String theaterId = scanner.nextLine();

		/*
		 * 劇場IDを送ってDBで検索
		 */
		//DAOのインスタンス作成
		//		SearchCinemaInformationDAO dao = new SearchCinemaInformationDAOImpl();

		//上映スケジュールを取得
		//		List<SearchCinemaInformationDTO> seachCinemaInfoDtoList = dao.searchCinemaInformation(theaterId);

		// SqlSessionからMapperを取得
		SearchCinemainfomation mapper = MybatisDataBaseManager.getSession().getMapper(SearchCinemainfomation.class);

		// 映画情報の取得
		List<SearchCinemaInformationDTO> seachCinemaInfoDtoList = mapper.selectTheaterInfo(theaterId);

		//上映スケジュールを取得できたかをチェック
		if (seachCinemaInfoDtoList.isEmpty()) {
			System.out.println("該当する上映スケジュールはありません。");
		} else {
			//上映スケジュール表示
			//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
			System.out.println("--- 検索結果 ---");
			System.out.println("**************************\r\n"
					+ "《上映スケジュール》\r\n"
					+ "  " + seachCinemaInfoDtoList.get(0).getTheaterNm()
					//		+ dateFormat.format(
					+ "   " + seachCinemaInfoDtoList.get(0).getMovieStartDt().replace("-", "/")
					+ "\r\n"
					+ "\r\n"
					+ "  スクリーン名 " + "   上映開始時間  " + "                     映画名 ");
			for (SearchCinemaInformationDTO info : seachCinemaInfoDtoList) {
				String dispMessage = "   " + info.getScreenNm() + "   | "
						+ "   " + info.getMovieStartTm() + "   | "
						+ "   " + info.getMovieNm();
				System.out.println(dispMessage);
			}
		}

		//再表示フラグをfalseで初期化
		boolean reDisplayFlag = false;
		//メニュー番号を定義
		String menuNo = null;

		//flagがfalseの間、以下を繰り返す
		while (reDisplayFlag == false) {
			System.out.println("0：メニューに戻る");
			System.out.println("9：終了");
			System.out.print("入力：");

			//メニュー番号を取得
			menuNo = scanner.next();

			//メニュー番号が 0 or 9 ならループ終了
			if (menuNo.equals(MENU_BACK) || menuNo.equals(END_SELECT)) {
				reDisplayFlag = true;
			} else {
				System.out.println("値が正しくありません");
				System.out.println("有効な値を入力してください");
			}
		}

		//menuNoが9(終了)ならtrueを返す。
		if (menuNo.equals(END_SELECT)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @return
	 */
	public boolean cinemaInfo() {

		@SuppressWarnings("resource") // 「scannerが閉じられることはありません」ワーニングが出ないようにするアノテーション
		Scanner scanner = new Scanner(System.in);

		System.out.println("**************************\n"
				+ "劇場名"
				+ "            劇場ID");

		/*
		 * 劇場IDを送ってDBで検索
		 */
		//DAOのインスタンス作成
		SearchCinemaInformationDAO dao = new SearchCinemaInformationDAOImpl();

		//上映スケジュールを取得
		List<SearchCinemaInformationDTO> CinemaInfoDtoList = dao.CinemaInformation();

		for (SearchCinemaInformationDTO info : CinemaInfoDtoList) {
			System.out.print(format(info.getTheaterNm(), 15) + "|    " + info.getTheaterId() + "\n");
		}

		//再表示フラグをfalseで初期化
		boolean reDisplayFlag = false;
		//メニュー番号を定義
		String menuNo = null;

		//flagがfalseの間、以下を繰り返す
		while (reDisplayFlag == false) {
			System.out.println("0：メニューに戻る");
			System.out.println("9：終了");
			System.out.print("入力：");

			//メニュー番号を取得
			menuNo = scanner.next();

			//メニュー番号が 0 or 9 ならループ終了
			if (menuNo.equals(MENU_BACK) || menuNo.equals(END_SELECT)) {
				reDisplayFlag = true;
			} else {
				System.out.println("値が正しくありません");
				System.out.println("有効な値を入力してください");
			}
		}

		//menuNoが9(終了)ならtrueを返す。
		if (menuNo.equals(END_SELECT)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean countMovie() {
		// Scanner
		@SuppressWarnings("resource") // 「scannerが閉じられることはありません」ワーニングが出ないようにするアノテーション
		Scanner scanner = new Scanner(System.in);

		System.out.println("**************************");
		System.out.println("劇場IDを入力してください");
		System.out.print("入力：");

		//劇場IDを取得
		String theaterId = scanner.nextLine();

		/*
		 * 劇場IDを送ってDBで検索
		 */
		//DAOのインスタンス作成
		SearchCinemaInformationDAO dao = new SearchCinemaInformationDAOImpl();

		//上映スケジュールを取得
		List<SearchCinemaInformationDTO> countMovieDtoList = dao.countChinema(theaterId);

		//上映スケジュールを取得できたかをチェック
		if (countMovieDtoList.isEmpty()) {
			System.out.println("該当する上映スケジュールはありません。");
		} else {
			//上映スケジュール表示
			//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
			System.out.println("--- 検索結果 ---");
			System.out.println("**************************\r\n"
					+ "《上映スケジュール》\r\n"
					+ "  " + countMovieDtoList.get(0).getTheaterNm()
					+ "\r\n\r\n"
					+ "  映画名  "
					+ "                                              上映回数  ");
			for (SearchCinemaInformationDTO info : countMovieDtoList) {
				System.out.print(format(info.getMovieNm(), 55) + "|    " + info.getCountMv() + "\n");
			}
		}

		//再表示フラグをfalseで初期化
		boolean reDisplayFlag = false;
		//メニュー番号を定義
		String menuNo = null;

		//flagがfalseの間、以下を繰り返す
		while (reDisplayFlag == false) {
			System.out.println("0：メニューに戻る");
			System.out.println("9：終了");
			System.out.print("入力：");

			//メニュー番号を取得
			menuNo = scanner.next();

			//メニュー番号が 0 or 9 ならループ終了
			if (menuNo.equals(MENU_BACK) || menuNo.equals(END_SELECT)) {
				reDisplayFlag = true;
			} else {
				System.out.println("値が正しくありません");
				System.out.println("有効な値を入力してください");
			}
		}

		//menuNoが9(終了)ならtrueを返す。
		if (menuNo.equals(END_SELECT)) {
			return true;
		} else {
			return false;
		}
	}

	private static String format(String target, int length) {
		int byteDiff = (getByteLength(target, Charset.forName("UTF-8")) - target.length()) / 2;
		return String.format("%-" + (length - byteDiff) + "s", target);
	}

	private static int getByteLength(String string, Charset charset) {
		return string.getBytes(charset).length;
	}

}