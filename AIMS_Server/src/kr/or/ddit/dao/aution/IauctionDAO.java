package kr.or.ddit.dao.aution;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.A_articleVO;
import kr.or.ddit.vo.Auctioning_RecordVO;

public interface IauctionDAO {
	public List<A_articleVO> getMyArticleList(String mem_id);
	public List<A_articleVO> getTotalArticleList();
	
	public A_articleVO getArticleInfo(String a_art_no); //카테고리와 법원을 조인한 쿼리문 호출 
	
	public int insertArticleResult(Map<String,String> info);
	public void insertRecording(Auctioning_RecordVO recordVO);
}
