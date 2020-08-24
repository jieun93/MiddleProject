package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.Answer_FileVO;
import kr.or.ddit.vo.Category_AVO;
import kr.or.ddit.vo.Com_AnswerVO;
import kr.or.ddit.vo.Com_QuestionVO;
import kr.or.ddit.vo.FileInfoVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.Notice_FileVO;
import kr.or.ddit.vo.Question_FileVO;

public interface InoticeServer extends Remote{
	
	public int getNoticTotalCount()throws RemoteException;
	public int getQuestionTotalcount()throws RemoteException;
	public List<NoticeVO> getPaginNoticeList(Map<String,Integer> pageMap)throws RemoteException;
	public List<Com_AnswerVO> getPaginAnswerList(Map<String,Integer> pageMap)throws RemoteException;// pagein처리
	public List<Com_QuestionVO> getPaginQuestionList(Map<String,Integer> pageMap)throws RemoteException;// pagein처리
	
	public List<Notice_FileVO> getNoticeFileList(String notice_no)throws RemoteException;
	public List<Answer_FileVO> getAnswerFileList(String com_ans_no)throws RemoteException;
	public List<Question_FileVO> getQuestionFileList(String com_que_no)throws RemoteException;
	public List<Com_AnswerVO> getMyAnswerList(String mem_id)throws RemoteException;
	public List<Com_QuestionVO> getMyQuestionList(String mem_id)throws RemoteException;
	
	public List<Category_AVO> getCategory_AList()throws RemoteException;
	
	public int getNoiceSameFile(Map<String,String>map)throws RemoteException;
	
	public boolean insertCom_Answer(Com_AnswerVO com_AnswerVO)throws RemoteException;
	public boolean insertCom_Question(Com_QuestionVO com_QuestionVO)throws RemoteException;
	public boolean insertNotice(NoticeVO noticeVO)throws RemoteException;
	public boolean insertNotice_file2(Notice_FileVO fileVO)throws RemoteException;
	
	public boolean insertAnswer_file(Answer_FileVO answer_FileVO)throws RemoteException;
	public boolean insertQuestion_file(Question_FileVO question_FileVO)throws RemoteException;
	public boolean insertNotice_file(Notice_FileVO fileVO)throws RemoteException;
	
	public boolean deleteNotice_File(Map<String,String> map)throws RemoteException;
	public boolean deleteNotice(String notice_no)throws RemoteException;
	public boolean deleteAllNotice_File(String notice_no)throws RemoteException;
	public boolean updataNotice(NoticeVO noticeVO)throws RemoteException;
	
	public String clientToServer(String folderName, FileInfoVO fileData) throws RemoteException;
	public List<FileInfoVO> answerServerToClient(List<Answer_FileVO> list) throws RemoteException;
	public List<FileInfoVO> questionServerToClient(List<Question_FileVO> list) throws RemoteException;
	public List<FileInfoVO> noticeServerToClient(List<Notice_FileVO> list) throws RemoteException;
	
	public Category_AVO getCategoryInfo(String cat_a_no)throws RemoteException;
	public List<Com_QuestionVO> getCom_QuestionList()throws RemoteException;	
	public void noticeUpCnt(String notice_no) throws RemoteException;
}

