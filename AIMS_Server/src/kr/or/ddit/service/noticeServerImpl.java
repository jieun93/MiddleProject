package kr.or.ddit.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dao.noticeDAOImpl;
import kr.or.ddit.vo.Answer_FileVO;
import kr.or.ddit.vo.Category_AVO;
import kr.or.ddit.vo.Com_AnswerVO;
import kr.or.ddit.vo.Com_QuestionVO;
import kr.or.ddit.vo.FileInfoVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.Notice_FileVO;
import kr.or.ddit.vo.Question_FileVO;

public class noticeServerImpl extends UnicastRemoteObject implements InoticeServer {
	static noticeServerImpl instance;
	private noticeDAOImpl dao;

	private noticeServerImpl() throws RemoteException {
		dao = noticeDAOImpl.getInstance();
	}

	public static noticeServerImpl getInstance() {

		if (instance == null) {
			try {
				instance = new noticeServerImpl();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	@Override
	public int getNoticTotalCount() throws RemoteException {

		return dao.getNoticTotalCount();
	}

	@Override
	public List<NoticeVO> getPaginNoticeList(Map<String, Integer> pageMap) throws RemoteException {

		return dao.getPaginNoticeList(pageMap);
	}
	@Override
	public List<Com_AnswerVO> getPaginAnswerList(Map<String, Integer> pageMap) throws RemoteException {

		return dao.getPaginAnswerList(pageMap);
	}
	@Override
	public List<Com_QuestionVO> getPaginQuestionList(Map<String, Integer> pageMap) throws RemoteException {

		return dao.getPaginQuestionList(pageMap);
	}
	

	@Override
	public List<Notice_FileVO> getNoticeFileList(String notice_no) throws RemoteException {

		return dao.getNoticeFileList(notice_no);
	}

	@Override
	public List<Answer_FileVO> getAnswerFileList(String com_ans_no) throws RemoteException {

		return dao.getAnswerFileList(com_ans_no);
	}

	@Override
	public List<Question_FileVO> getQuestionFileList(String com_que_no) throws RemoteException {
		return dao.getQuestionFileList(com_que_no);
	}

	@Override
	public List<Com_AnswerVO> getMyAnswerList(String mem_id) throws RemoteException {
		return dao.getMyAnswerList(mem_id);
	}

	@Override
	public List<Com_QuestionVO> getMyQuestionList(String mem_id) throws RemoteException {
		return dao.getMyQuestionList(mem_id);
	}

	@Override
	public boolean insertCom_Answer(Com_AnswerVO com_AnswerVO) throws RemoteException {
		return dao.insertCom_Answer(com_AnswerVO);
	}

	@Override
	public boolean insertCom_Question(Com_QuestionVO com_QuestionVO) throws RemoteException {
		return dao.insertCom_Question(com_QuestionVO);
	}

	@Override
	public boolean insertNotice(NoticeVO noticeVO) throws RemoteException {
		return dao.insertNotice(noticeVO);
	}

	@Override
	public boolean insertAnswer_file(Answer_FileVO answer_FileVO) throws RemoteException {
		return dao.insertAnswer_file(answer_FileVO);
	}

	@Override
	public boolean insertQuestion_file(Question_FileVO question_FileVO) throws RemoteException {
		return dao.insertQuestion_file(question_FileVO);
	}

	@Override
	public boolean insertNotice_file(Notice_FileVO fileVO) throws RemoteException {
		return dao.insertNotice_file(fileVO);
	}

	@Override
	public String clientToServer(String folderName, FileInfoVO fileData) throws RemoteException {

		File file = new File("D:/D_Other/dataStage/" + folderName + "/" + fileData.getFileName());
		byte[] data = fileData.getFileData();
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.getPath();
	}

	@Override
	public List<Category_AVO> getCategory_AList() throws RemoteException {
		for (Category_AVO vo : dao.getCategory_AList()) {
			System.out.println(vo.getCat_a_name());
		}
		return dao.getCategory_AList();
	}

	@Override
	public List<FileInfoVO> answerServerToClient(List<Answer_FileVO> list) throws RemoteException {
		List<FileInfoVO> fileList = new ArrayList<FileInfoVO>();

		for (Answer_FileVO fileInfo : list) {
			File file = new File(fileInfo.getAns_f_addr());
			try {
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[(int) file.length()];
				fis.read(buffer);
				FileInfoVO filedate = new FileInfoVO();
				filedate.setFileData(buffer);
				filedate.setFileName(file.getName());
				fileList.add(filedate);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return fileList;
	}

	@Override
	public List<FileInfoVO> questionServerToClient(List<Question_FileVO> list) throws RemoteException {
		List<FileInfoVO> fileList = new ArrayList<FileInfoVO>();

		for (Question_FileVO fileInfo : list) {
			File file = new File(fileInfo.getQue_f_addr());
			try {
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[(int) file.length()];
				fis.read(buffer);
				FileInfoVO filedate = new FileInfoVO();
				filedate.setFileData(buffer);
				filedate.setFileName(file.getName());
				fileList.add(filedate);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return fileList;
	}

	@Override
	public List<FileInfoVO> noticeServerToClient(List<Notice_FileVO> list) throws RemoteException {
		List<FileInfoVO> fileList = new ArrayList<FileInfoVO>();

		for (Notice_FileVO fileInfo : list) {
			File file = new File(fileInfo.getFile_addr());
			try {
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[(int) file.length()];
				fis.read(buffer);
				FileInfoVO filedate = new FileInfoVO();
				filedate.setFileData(buffer);
				filedate.setFileName(file.getName());
				fileList.add(filedate);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return fileList;
	}

	@Override
	public int getNoiceSameFile(Map<String, String> map) throws RemoteException {
		return dao.getNoiceSameFile(map);
	}

	@Override
	public boolean insertNotice_file2(Notice_FileVO fileVO) throws RemoteException {
		return dao.insertNotice_file2(fileVO);
	}

	@Override
	public boolean deleteNotice_File(Map<String, String> map) throws RemoteException {
		return dao.deleteNotice_File(map);
	}

	@Override
	public boolean deleteNotice(String notice_no) throws RemoteException {
		return dao.deleteNotice(notice_no);
	}

	@Override
	public boolean deleteAllNotice_File(String notice_no) throws RemoteException {
			return dao.deleteAllNotice_File(notice_no);
	}

	@Override
	public boolean updataNotice(NoticeVO noticeVO) throws RemoteException {
			return dao.updataNotice(noticeVO);
	}

	@Override
	public Category_AVO getCategoryInfo(String cat_a_no) throws RemoteException {
			return dao.getCategoryInfo(cat_a_no);
	}
	
	@Override
	public List<Com_QuestionVO> getCom_QuestionList() throws RemoteException {
		return dao.getCom_QuestionList();
	}

	@Override
	public int getQuestionTotalcount() throws RemoteException {
		return dao.getQuestionTotalcount();
	}

	@Override
	public void noticeUpCnt(String notice_no) throws RemoteException {
		dao.noticeUpCnt(notice_no);
	}

}
