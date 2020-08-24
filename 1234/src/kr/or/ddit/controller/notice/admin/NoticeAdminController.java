package kr.or.ddit.controller.notice.admin;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.ddit.service.InoticeServer;
import kr.or.ddit.vo.NoticeVO;

public class NoticeAdminController {
	private int rowsPerPage = 31; // 한 화면에 보여줄 데이터 개수
	private int totalCount; // 전체 레코드 수가 저장될 변수
	private int pageCount; // 페이지 수
	private InoticeServer service;
	private AnchorPane dialog;

	public AnchorPane getDialog() {
		return dialog;
	}

	public void setDialog(AnchorPane dialog) {
		this.dialog = dialog;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btn_Add;

	@FXML
	private TableView<NoticeVO> tb_notice;

	@FXML
	private TableColumn<?, ?> col_no;

	@FXML
	private TableColumn<?, ?> col_title;

	@FXML
	private TableColumn<?, ?> col_cnt;

	@FXML
	private TableColumn<?, ?> col_date;

	@FXML
	private Pagination paging;

	@FXML
	void onClickeBtn_Add(ActionEvent event) {
		try {
			Stage stage = (Stage) btn_Add.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(
					NoticeAdminMainController.class.getResource("../../../fxml/notice/admin/NoticeAddAdmin.fxml"));
			Parent root = loader.load();
			NoticeAddAdminController controller = loader.getController();

			controller.setController(this);

			Scene scene = new Scene(root);
			Stage popupDialog = new Stage();

			popupDialog.initModality(Modality.WINDOW_MODAL);
			popupDialog.initOwner(stage);

			popupDialog.setScene(scene);
			popupDialog.setTitle("공지사항");
			popupDialog.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changeTableView(int index) {
		int start = index * rowsPerPage; // 시작번호
		int end = Math.min(start + rowsPerPage, totalCount); // 끝 번호

		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("start", start);
		pageMap.put("end", end);

		// 테이블에 갖고온 데이터 설정하기
		try {
			tb_notice.setItems(FXCollections.observableArrayList(service.getPaginNoticeList(pageMap)));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onClickedTb_Notice(MouseEvent event) {
//    	Scene scene = btn_Add.getScene();
		Stage stage = (Stage) btn_Add.getScene().getWindow();
		try {
			FXMLLoader loader = new FXMLLoader(NoticeAdminMainController.class.getResource("../../../fxml/notice/admin/NoticModifyAdmin.fxml"));
			Parent root = loader.load();
			NoticeModifyAdminController controller = loader.getController();
			controller.setDialog(getDialog());
			controller.setInfoData(tb_notice.getSelectionModel().getSelectedItem());
			System.out.println(dialog);
			for(int i = 0 ; i < dialog.getChildren().size();i++) {
				
				dialog.getChildren().remove(i);
			}
			dialog.getChildren().add(root);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void getNoticeList() {
		try {
			totalCount = service.getNoticTotalCount();
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		pageCount = (int) Math.ceil((double) totalCount / rowsPerPage);
		System.out.println(pageCount);
		paging.setPageCount(pageCount);
		paging.setCurrentPageIndex(0);
		changeTableView(0);
	}

	@FXML
	void initialize() {
		try {
			Registry reg = LocateRegistry.getRegistry("localhost", 8888);
			service = (InoticeServer) reg.lookup("notice");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		col_no.setCellValueFactory(new PropertyValueFactory<>("notice_no"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("notice_title"));
		col_cnt.setCellValueFactory(new PropertyValueFactory<>("notice_cnt"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("notice_date"));

		paging.currentPageIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changeTableView(newValue.intValue());
			}
		});
		getNoticeList();
		

	}

}