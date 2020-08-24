package kr.or.ddit.dao.aution;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.BuildedSqlMapClient;
import kr.or.ddit.vo.A_articleVO;
import kr.or.ddit.vo.Auctioning_RecordVO;

public class auctionDAOImpl implements IauctionDAO {
	private static auctionDAOImpl instance;
	private SqlMapClient smc;
	
	private auctionDAOImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static auctionDAOImpl getInstance() {
		if(instance ==null) {
			instance = new auctionDAOImpl();
		}
		return instance;
	}


	@Override
	public List<A_articleVO> getTotalArticleList() {
		List<A_articleVO> dataList = null;
		try {
			dataList = smc.queryForList("auction.getTotalArticleList");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	


	@Override
	public A_articleVO getArticleInfo(String a_art_no) {
		A_articleVO	a_articleVO = null ;
		try{
			a_articleVO = (A_articleVO) smc.queryForObject("auction.getArticleInfo",a_art_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a_articleVO;
	}
	
	@Override
	public List<A_articleVO> getMyArticleList(String mem_id) {
		List<A_articleVO> dataList = null;
		try {
			dataList = smc.queryForList("auction.getMyArticleList",mem_id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	@Override
	public int insertArticleResult(Map<String, String> info) {
		int cnt = 0;
		try {
			Object obj=smc.insert("auction.insertArticleResult",info);
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public void insertRecording(Auctioning_RecordVO recordVO) {
		try {
			smc.insert("auction.insertRecording",recordVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
