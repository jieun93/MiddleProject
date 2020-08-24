package kr.or.ddit.controller.InformationUse;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import kr.or.ddit.service.InformationUse.IAgencyService;
import kr.or.ddit.service.InformationUse.IFAQService;
import kr.or.ddit.service.InformationUse.IManagerAgencyService;
import kr.or.ddit.vo.AgencyVO;


public class ManagerAgencyController {
	private AnchorPane dialog;
	public AgencyVO AgencyVoInfo;
	private IManagerAgencyService service;
	private ObservableList<AgencyVO> AgencyCmboProrList1; // 콤보박스이 데이터를 저장할 리스트(법원이름)
	private ObservableList<AgencyVO> AgencyCmboProrList2; // 콤보박스이 데이터를 저장할 리스트(법원이름)
	private ObservableList<AgencyVO> AgencyTableList; // TableView의 데이터를 저장할 리스트
	

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
	private AnchorPane contentsArea;

	@FXML
	private ComboBox<String> comboArea;

	@FXML
	private Button btnSearch;

	@FXML
	private ComboBox<String> comboProvince;

	@FXML
	private TableView<AgencyVO> tableView;

	@FXML
	private TableColumn<?, ?> colName;

	@FXML
	private TableColumn<?, ?> colAddr;

	@FXML
	private Pagination pagintion;

	@FXML
	private Button btnAdd;

	// 데이터 가지고 다니려고 만드는것
	// private AnchorPane contentsArea;

	public AgencyVO getAgencyVoInfo() {
		return AgencyVoInfo;
	}

	public void setAgencyVoInfo(AgencyVO AgencyVoInfo) {
		this.AgencyVoInfo = AgencyVoInfo;
	}

	@FXML
	void btnAddClicked(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(
					ManagerAgencyController.class.getResource("../../fxml/InformationUse/ManagerAgencyDetails.fxml"));
			Parent root = loader.load();
			ManagerAgencyDeitalsController mAgenCon = loader.getController();
			mAgenCon.setDialog(dialog);
			mAgenCon.setName(new AgencyVO(), "등록");

			if (dialog.getChildren().size() != 0) {
				for (int i = 0; i < dialog.getChildren().size(); i++) {
					dialog.getChildren().remove(i);
				}
			}
			dialog.getChildren().add(root);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void btnSearchClicked(ActionEvent event) {

		List<AgencyVO> List = new ArrayList<AgencyVO>();
    	
    	Map<String, String> paraMap = new HashMap<String, String>();
    	paraMap.put("para1", comboArea.getValue());
		paraMap.put("para2", comboProvince.getValue());
		try {
			List = service.getCourtBoth(paraMap);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		AgencyCmboProrList1 = FXCollections.observableArrayList(List);
		System.out.println(AgencyCmboProrList1);
		tableView.setItems(AgencyCmboProrList1);
//    	tableView.setItems(service.getCourtBoth(paraMap));
	}

	@FXML
	void comboAreaClicked(MouseEvent event) {

	}

	@FXML
	void comboProvinceClicked(MouseEvent event) {

	}

	@FXML
	void paginationCliked(MouseEvent event) {

	}

	@FXML
	void tableViewClicked(MouseEvent event) {
		try {
			if (tableView.getSelectionModel().isEmpty())
				return;

			AgencyVO agencyVo = tableView.getSelectionModel().getSelectedItem();

			FXMLLoader loader = new FXMLLoader(
					ManagerAgencyController.class.getResource("../../fxml/InformationUse/ManagerAgencyDetails.fxml"));

			Parent root = loader.load();
			ManagerAgencyDeitalsController adCon = loader.getController();
			// 데이터 가지고 다닐려고 넣어준것   
			adCon.setDialog(dialog);

			// 뭐가 있으면 지워주기
			int size = dialog.getChildren().size();
			if (size != 0) {
				for (int i = 0; i < size; i++) {
					dialog.getChildren().remove(0);
				}
			}
			dialog.getChildren().add(root);

			// 값 넣어주기(Deatils에서 쓸 값 미리 넣어준것)
			adCon.setName(agencyVo, "수정");

		} catch (IOException e) {
			e.printStackTrace();
		}
//    	
	}

	@FXML
	void initialize() {
		// 넣어주기 > 이걸로 검색할 수 있게
		//service객체 생성
    	try {
			Registry reg = LocateRegistry.getRegistry(8888);
			service = (IManagerAgencyService) reg.lookup("ManagerAgency");
		} catch (RemoteException e) {
			e.printStackTrace();
			
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
    	

    	colName.setCellValueFactory(new PropertyValueFactory<>("cou_name")); 
    	colAddr.setCellValueFactory(new PropertyValueFactory<>("cou_loc"));
    	
    	String StringcomboArea[] = { "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시", "경기도",
				"강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "전체"};
    	
    	ObservableList<String> observableList = FXCollections.observableArrayList(StringcomboArea);
    	comboArea.setItems(observableList);
    	comboArea.setValue("전체");
    	// comboArea.setCellFactory(new PropertyValueFactory<>("StringcomboArea"));
		// StringcomboArea가 comboArea에 들어가게 셋팅해준거

		// comboArea의 값 가져오기
    	String comboAddr = comboArea.getValue();
    	// 가져온 값으로 sql문 돌려서 observaleList에 저장하기
    	if(comboAddr.equals("전체")) {
    		try {
				AgencyTableList = FXCollections.observableArrayList(service.getAllCourt());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else {
    		try {
				AgencyTableList = FXCollections.observableArrayList(service.getAreaSearch(comboAddr));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    	
    	// 콤보박스에 이 값들을 셋팅
    	tableView.setItems(AgencyTableList);
    	
    	// 이 목록들을 VO에 저장하고 tableView에 나오게 하기 >
//    	/AddListener : 함수를 불러온다. 콜백
    	comboArea.valueProperty().addListener(new ChangeListener<String>() {
    		
    		@Override
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			// 현재 선택된 콤보값 구하기
				// 현재 선택한 콤보값에 맞는 상품 데이터를 구해서 TableView에 셋팅하기
    			List<AgencyVO> List = new ArrayList<AgencyVO>();
    	    	
    	    	Map<String, String> paraMap = new HashMap<String, String>();
    			
				try {
					 ;
					comboProvince.setItems(FXCollections.observableArrayList(service.getCourtSi(newValue)));
					
					
					paraMap.put("para1", comboArea.getValue());
					paraMap.put("para2", comboProvince.getValue());	
					//AgencyCmboProrList2 = (ObservableList<AgencyVO>) service.getCourtBoth(paraMap);
					//service.getCourtBoth(paraMap);
					List = service.getCourtBoth(paraMap);
					
//					AgencyCmboProrList1 = FXCollections.observableArrayList(List);
//					tableView.setItems(AgencyCmboProrList1);
//					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
    			
				
    		}
    		
		});
	}
}