package kr.or.ddit.dao.member;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDao {

	
	// 입력 넣는거 
	public int insertMemberJoin(MemberVO memVO);
	
	
	// 중복확인 하는거 
	public MemberVO getMemberId(String mem_id);
	
}