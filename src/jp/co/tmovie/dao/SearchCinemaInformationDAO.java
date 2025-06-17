package jp.co.tmovie.dao;

import java.util.List;

import jp.co.tmovie.dto.SearchCinemaInformationDTO;

/**
 * DAOのinterface
 * @author hi-toda
 */
public interface SearchCinemaInformationDAO {
	/**
	 * 上映スケジュール検索
	 * @param theaterId 劇場ID
	 * @return DB取得結果
	 */
	public List<SearchCinemaInformationDTO> searchCinemaInformation(String theaterId);
	
	/**
	 * @return
	 */
	public List<SearchCinemaInformationDTO> CinemaInformation();
	
	public List<SearchCinemaInformationDTO> countChinema(String theaterId);
}
