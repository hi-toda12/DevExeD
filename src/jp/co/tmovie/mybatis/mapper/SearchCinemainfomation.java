package jp.co.tmovie.mybatis.mapper;

import java.util.List;

import jp.co.tmovie.dto.SearchCinemaInformationDTO;

/**
 * 劇場情報mapper
 * @author hi-toda
 */
public interface SearchCinemainfomation {

	/**
	 * 劇場情報を取得する
	 * @param searchID　劇場IDを取得する
	 * @return 劇場情報
	 */
	public List<SearchCinemaInformationDTO> selectTheaterInfo(String searchID);

}
