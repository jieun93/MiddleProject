package kr.or.ddit.dao.knowledge;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.BuildedSqlMapClient;
import kr.or.ddit.vo.A_FormJoinForm_FileVO;
import kr.or.ddit.vo.A_FormVO;
import kr.or.ddit.vo.A_TerminologyVO;
import kr.or.ddit.vo.Form_FileVO;
import kr.or.ddit.vo.Related_LawVO;

public class KnowledgeDaoImpl implements IKnowledgeDao {

	private SqlMapClient smc;
	
	private static KnowledgeDaoImpl dao;
	
	private KnowledgeDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static KnowledgeDaoImpl getInstance() {
		if(dao == null) dao = new KnowledgeDaoImpl();
		return dao;
	}

	@Override
	public List<A_TerminologyVO> searchTerms(String a_ter_name) {
		List<A_TerminologyVO> termList = null;
		try {
			termList = smc.queryForList("admin_knowledge.searchTerms", a_ter_name);
		} catch (SQLException e) {
			termList = null;
			e.printStackTrace();
		}
		return termList;
	}

	@Override
	public List<Related_LawVO> searchLaw(String rel_l_name) {
		List<Related_LawVO> lawList = null;
		try {
			lawList = smc.queryForList("admin_knowledge.searchLaw", rel_l_name);
		} catch (SQLException e) {
			lawList = null;
			e.printStackTrace();
		}
		return lawList;
	}

	@Override
	public List<A_TerminologyVO> getAllTerm() {
		List<A_TerminologyVO> termList = null;
		try {
			termList = smc.queryForList("admin_knowledge.getAllTerm");
		} catch (SQLException e) {
			termList = null;
			e.printStackTrace();
		}
		return termList;
	}
	
	@Override
	public List<A_TerminologyVO> getTerms() {
		List<A_TerminologyVO> termList = null;
		try {
			termList = smc.queryForList("admin_knowledge.getTerms");
		} catch (SQLException e) {
			termList = null;
			e.printStackTrace();
		}
		return termList;
	}

	@Override
	public int insertTerms(A_TerminologyVO termVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("admin_knowledge.insertTerms", termVo);
			if(obj == null){
				cnt = 1;
			}else{
				cnt = 0;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}		
		return cnt;
	}

	@Override
	public int updateTerms(A_TerminologyVO termVo) {
		int cnt = 0;
		try {
			cnt = smc.update("admin_knowledge.updateTerms", termVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteTerms(A_TerminologyVO termVo) {
		int cnt = 0;
		try {
			cnt = smc.delete("admin_knowledge.deleteTerms", termVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<A_FormVO> getAllForm() {
		List<A_FormVO> formList = null;
		try {
			formList = smc.queryForList("admin_knowledge.getAllForm");
		} catch (SQLException e) {
			formList = null;
			e.printStackTrace();
		}
		return formList;
	}

	@Override
	public List<Form_FileVO> getAllFile() {
		List<Form_FileVO> fileList = null;
		try {
			fileList = smc.queryForList("admin_knowledge.getAllFile");
		} catch (SQLException e) {
			fileList = null;
			e.printStackTrace();
		}
		return fileList;
	}

	@Override
	public String insertForm(A_FormVO formVo) {
		String cnt = "";
		try {
			cnt =(String) smc.insert("admin_knowledge.insertForm", formVo);
//			if(obj == null){
//				cnt = 1;
//			}else{
//				cnt = 0;
//			}
		} catch (SQLException e) {
			cnt = "";
			e.printStackTrace();
		}		
		return cnt;
	}

	@Override
	public int insertFile(Form_FileVO fileVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("admin_knowledge.insertFile", fileVo);
			if(obj == null){
				cnt = 1;
			}else{
				cnt = 0;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}		
		return cnt;
	}

	@Override
	public int updateForm(A_FormVO formVo) {
		int cnt = 0;
		try {
			cnt = smc.update("admin_knowledge.updateForm", formVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateFile(Form_FileVO fileVo) {
		int cnt = 0;
		try {
			cnt = smc.update("admin_knowledge.updateFile", fileVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteForm(A_FormVO formVo) {
		int cnt = 0;
		try {
			cnt = smc.delete("admin_knowledge.deleteForm", formVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteFile(Form_FileVO fileVo) {
		int cnt = 0;
		try {
			cnt = smc.delete("admin_knowledge.deleteFile", fileVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Related_LawVO> getAllLaw() {
		List<Related_LawVO> lawList = null;
		try {
			lawList = smc.queryForList("admin_knowledge.getAllLaw");
		} catch (SQLException e) {
			lawList = null;
			e.printStackTrace();
		}
		return lawList;
	}

	@Override
	public int insertLaw(Related_LawVO lawVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("admin_knowledge.insertLaw", lawVo);
			if(obj == null){
				cnt = 1;
			}else{
				cnt = 0;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}		
		return cnt;
	}

	@Override
	public int updateLaw(Related_LawVO lawVo) {
		int cnt = 0;
		try {
			cnt = smc.update("admin_knowledge.updateLaw", lawVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteLaw(Related_LawVO lawVO) {
		int cnt = 0;
		try {
			cnt = smc.delete("admin_knowledge.deleteLaw", lawVO);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<A_FormJoinForm_FileVO> joinForm() {
		List<A_FormJoinForm_FileVO> joinList = null;
		try {
			joinList = smc.queryForList("admin_knowledge.joinForm");
		} catch (SQLException e) {
			joinList = null;
			e.printStackTrace();
		}
		return joinList;
	}

	@Override
	public List<Form_FileVO> getFormList(String a_form_no) {
		List<Form_FileVO> joinList = null;
		try {
			joinList = smc.queryForList("admin_knowledge.getFormList",a_form_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joinList;
	}

}