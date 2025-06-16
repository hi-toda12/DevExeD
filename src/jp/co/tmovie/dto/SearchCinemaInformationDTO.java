package jp.co.tmovie.dto;

/**
 * 映画館情報DTO
 * @author koug-yamaguchi
 */
public class SearchCinemaInformationDTO {
	/**劇場名*/
	private String theaterNm;
	/**上映開始日*/
	private String movieStartDt;
	/**スクリーン名*/
	private String screenNm;
	/**上映開始時間*/
	private String movieStartTm;
	/**映画名*/
	private String movieNm;
	/**劇場ID*/
	private String theaterId;
	/**上映回数*/
	private String countMv;
	
	
	/**
	 * @return countMv
	 */
	public String getCountMv() {
		return countMv;
	}
	/**
	 * @param countMv セットする countMv
	 */
	public void setCountMv(String countMv) {
		this.countMv = countMv;
	}
	/**
	 * @return theaterId
	 */
	public String getTheaterId() {
		return theaterId;
	}
	/**
	 * @param theaterId セットする theaterId
	 */
	public void setTheaterId(String theaterId) {
		this.theaterId = theaterId;
	}
	/**
	 * 劇場名を取得する
	 * @return 劇場名
	 */
	public String getTheaterNm() {
		return theaterNm;
	}
	/**
	 * 劇場名を設定する
	 * @param theaterNm 劇場名
	 */
	public void setTheaterNm(String theaterNm) {
		this.theaterNm = theaterNm;
	}
	/**
	 * 上映開始日を取得する
	 * @return 上映開始日
	 */
	public String getMovieStartDt() {
		return movieStartDt;
	}
	/**
	 * 上映開始日を設定する
	 * @param movieStartDt 上映開始日
	 */
	public void setMovieStartDt(String movieStartDt) {
		this.movieStartDt = movieStartDt;
	}
	/**
	 * スクリーン名を取得する
	 * @return スクリーン名
	 */
	public String getScreenNm() {
		return screenNm;
	}
	/**
	 * スクリーン名を設定する
	 * @param screenNm スクリーン名
	 */
	public void setScreenNm(String screenNm) {
		this.screenNm = screenNm;
	}
	/**
	 * 上映開始時間を取得する
	 * @return 上映開始時間
	 */
	public String getMovieStartTm() {
		return movieStartTm;
	}
	/**
	 * 上映開始時間を設定する
	 * @param movieStartTm 上映開始時間
	 */
	public void setMovieStartTm(String movieStartTm) {
		this.movieStartTm = movieStartTm;
	}
	/**
	 * 映画名
	 * @return 映画名
	 */
	public String getMovieNm() {
		return movieNm;
	}
	/**
	 * 映画名を設定する
	 * @param movieNm 映画名
	 */
	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}
}
