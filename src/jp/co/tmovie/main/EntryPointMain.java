package jp.co.tmovie.main;

import java.io.IOException;
import java.util.Scanner;

import jp.co.tmovie.logic.SearchCinemaInformationLogic;

/**
 * 研修用プログラムの開始クラス
 * @author hi-toda
 */
public class EntryPointMain {

	//上映スケジュール表示用のメニュー番号
	private static final String MENU_SEARCH = "1";
	//システム終了用のメニュー番号
	private static final String END_SELECT = "9";
	//追加用件初級
	private static final String INFO_SEARCH = "8";
	//追加用件中級
	private static final String COUNT_CINAMA = "2";

	/**
	 * 研修用プログラムの開始メソッド
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * メニューを表示して入力を受け取る。
		 * 「上映スケジュール表示」が入力されたらLogicを呼び出す。
		 */

		SearchCinemaInformationLogic searchDate = new SearchCinemaInformationLogic();
		//表示フラグを false で初期化
		boolean displayFlag = false;
		// Scanner
		Scanner scanner = new Scanner(System.in);

		while (displayFlag == false) {
			System.out.println();
			System.out.println("**************************\r\n"
					+ "メニューを選択してください\r\n"
					+ "1：上映スケジュール表示\r\n"
					+ "2：上映予定回数の表示\r\n"
					+ "8：劇場一覧の表示\r\n"
					+ "9：終了");

			// 機能選択メニューを表示する
			System.out.print("入力：");
			// 入力を受け取る
			String selectMenu = scanner.nextLine();

			// 入力値によって分岐する
			if (MENU_SEARCH.equals(selectMenu) || END_SELECT.equals(selectMenu)
					|| INFO_SEARCH.equals(selectMenu) || COUNT_CINAMA.equals(selectMenu)) {
				if (selectMenu.equals(MENU_SEARCH)) {
					try {
						displayFlag = new SearchCinemaInformationLogic().execute();
					} catch (IOException e) {
						e.printStackTrace();
						break;
					}
					//displayFlag = searchDate.execute();
				} else if (selectMenu.equals(END_SELECT)) {
					displayFlag = true;
				} else if (selectMenu.equals(INFO_SEARCH)) {
					displayFlag = searchDate.cinemaInfo();
				} else if (selectMenu.equals(COUNT_CINAMA)) {
					displayFlag = searchDate.countMovie();
				} else {
					System.out.println("値が正しくありません\r\n"
							+ "有効な値を入力してください");
				}

			}
		}
		System.out.println("**************************\r\n"
				+ "システムを終了しました");

		// Scannerは処理の一番最後で一度だけ閉じる
		// 処理途中で閉じるとSystem.inが閉じてしまい、再度開くことができなくなるため
		scanner.close();
	}
}