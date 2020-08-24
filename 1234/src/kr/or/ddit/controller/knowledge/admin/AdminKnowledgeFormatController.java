package kr.or.ddit.controller.knowledge.admin;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.knowledge.IKnowledgeService;
import kr.or.ddit.vo.A_FormJoinForm_FileVO;

public class AdminKnowledgeFormatController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<A_FormJoinForm_FileVO> formTable;

    @FXML
    private TableColumn<Integer, Integer> numCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnHome;

    private A_FormJoinForm_FileVO joinVo;
    
    private FormatEditController formatController;
    private IKnowledgeService service;
    private ObservableList<A_FormJoinForm_FileVO> data;
    
    private void setData() {
    	try {
    		List<A_FormJoinForm_FileVO> joinVo = service.joinForm();
    		data = FXCollections.observableArrayList(joinVo);
    		formTable.setItems(data);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    @FXML
    void ListClick(MouseEvent event) {
    	try {
			
    	if(formTable.getSelectionModel().isEmpty()) {
    		return;
    	}
    	try {
    		Stage mainStage = (Stage) formTable.getScene().getWindow();
    		FXMLLoader loader = new FXMLLoader(AdminKnowledgeFormatController.class.getResource("../../../fxml/knowledge/admin/formatEdit.fxml"));
        	Parent root = loader.load();
        	formatController = loader.getController();
        	
        	if(!formTable.getSelectionModel().isEmpty()) {
        		A_FormJoinForm_FileVO joinVo = formTable.getSelectionModel().getSelectedItem();
        		
        		formatController.show(joinVo);
        	}
        	
        	Stage stage = new Stage(StageStyle.DECORATED);
        	stage.initModality(Modality.APPLICATION_MODAL);
        	stage.initOwner(mainStage);
        	
        	Scene scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    		
        	stage.showingProperty().addListener(new ChangeListener<Boolean>() {
    			@Override
    			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
    				setData();
    			}
    		});
		} catch (IOException e2) {
		  e2.printStackTrace();
		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void btnAddClick(ActionEvent event) throws IOException {
    	Stage stage = (Stage) btnAdd.getScene().getWindow();
    	Parent add = FXMLLoader.load(AdminKnowledgeFormatController.class.getResource("../../../fxml/knowledge/admin/formatAdd.fxml"));
    	
    	Scene scene = new Scene(add);
    	stage.setScene(scene);
    	stage.show();
    	
    }

    @FXML
    void btnHomeClick(ActionEvent event) throws IOException {
    	Stage stage = (Stage) btnHome.getScene().getWindow();
    	Parent main = FXMLLoader.load(AdminKnowledgeFormatController.class.getResource("../../../fxml/knowledge/admin/adminKnowledgeMain.fxml"));
    	
    	Scene scene = new Scene(main);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void initialize() {
    	try {
			Registry reg = LocateRegistry.getRegistry(8888);
			
			service = (IKnowledgeService) reg.lookup("knowledge");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
        
        numCol.setCellValueFactory(new PropertyValueFactory<>("rownum"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("a_form_name"));
        
        setData();
        
    }
}
