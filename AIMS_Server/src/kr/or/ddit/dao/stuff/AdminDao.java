package kr.or.ddit.dao.stuff;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.BuildedSqlMapClient;
import kr.or.ddit.vo.A_articleVO;
import kr.or.ddit.vo.CategoryAVO;
import kr.or.ddit.vo.CategoryBVO;
import kr.or.ddit.vo.CategoryCVO;
import kr.or.ddit.vo.CourtVO;
import kr.or.ddit.vo.ImageVO;



public class AdminDao implements IAdminDao{
//------------------------------------------------------------------------------------------
	private SqlMapClient smc;
	
	private static AdminDao dao;
	
	private AdminDao() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static AdminDao getInstance() {
		if(dao == null) dao = new AdminDao();
		return dao;
	}
//------------------------------------------------------------------------------------------
	@Override
	public int insertStuff(A_articleVO articleVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("admin.insertStuff", articleVo);
			
			if(obj == null) {
				cnt = 1;
				System.out.println("inset작업 성공");
			}else {
				cnt = 0;
				System.out.println("insert작업 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("insert 실패");
		}
		
		return cnt;
	}

	@Override
	public int deleteStuff(String a_art_no) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("admin.deleteStuff", a_art_no);
			if(cnt > 0) {
				System.out.println("delete작업 성공");
			}else {
				System.out.println("delete작업 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("delete실패");
		}
		
		return cnt;
	}

	@Override
	public int updateStuff(A_articleVO articleVo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("admin.updateStuff", articleVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
			System.out.println("update실패");
		}
		
		return cnt;
	}

	@Override
	public List<A_articleVO> NumSearch(String a_art_no) {
		// Map의 구조
		// key		value
		// field	검색할 컬럼명
		// search	검색할 내용
		List<A_articleVO> stuffList = null;
		
		try {
			stuffList = smc.queryForList("admin.NumSearch", a_art_no);
		} catch (SQLException e) {
			stuffList = null;
			e.printStackTrace();
			System.out.println("물건번호로 검색 실패");
		}
		
		return stuffList;
	}

	@Override
	public List<A_articleVO> getAllStuff() {
		List<A_articleVO> stuffList = null;
		
		try {
			stuffList = smc.queryForList("admin.getAllStuff");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("모든 물건정보 실패");
		}
		
		return stuffList;
	}

	@Override
	public List<CategoryAVO> getAllACategory() {
		List<CategoryAVO> Alist = null;
		
		try {
			Alist = smc.queryForList("admin.getAllACategory");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("카테고리 A실패");
		}
		
		return Alist;
	}

	@Override
	public List<CategoryBVO> getAllBCategory(String cat_a_no) {
		List<CategoryBVO> Blist = null;
		
		try {
			Blist = smc.queryForList("admin.getAllBCategory", cat_a_no);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("카테고리 B실패");
		}
		
		return Blist;
	}

	@Override
	public List<CategoryCVO> getAllCCategory(String cat_b_no) {
		List<CategoryCVO> Clist = null;
		
		try {
			Clist = smc.queryForList("admin.getAllCCategory", cat_b_no);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("카테고리 C실패");
		}
		
		return Clist;
	}

	@Override
	public List<CourtVO> getAllCourt(String add) {
		List<CourtVO> courtList = null;
		
		try {
			courtList = smc.queryForList("admin.getAllCourt", add);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("소유 법원 실패");
		}
		
		return courtList;
	}
	
	@Override
	public List<String> courtAdd() {
		List<String> deoList = null;
		
		try {
			deoList = smc.queryForList("admin.courtAdd");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return deoList;
	}

	@Override
	public int insertImageFile(ImageVO imageVO) {
		int cnt = 0;
		try {
			Object obj = smc.insert("admin.insertImageFile",imageVO);
			if(obj == null) cnt =1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	@Override
	public int insertImageFile1(ImageVO imageVO) {
		int cnt = 0;
		try {
			Object obj = smc.insert("admin.insertImageFile1",imageVO);
			if(obj == null) cnt =1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<ImageVO> getArticleImageList(String a_art_no) {
		List<ImageVO> deoList = null;
		try {
			deoList = smc.queryForList("admin.getArticleImageList",a_art_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deoList;
	}

	@Override
	public int deleteImg_File(Map<String,String> map){
		int cnt = 0;
		try {
			cnt = smc.delete("admin.deleteImg_File",map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public String getcourtAdd(String add) {
		String s = null;
		
		try {
			s = (String) smc.queryForObject("admin.getcourtAdd", add);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return s;
	}


	

}
