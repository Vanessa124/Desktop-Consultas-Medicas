package br.senai.sp.controller;

import java.util.ArrayList;

import aplication.Main;
import br.senai.sp.dao.MedicoDAO;
import br.senai.sp.model.Especialidade;
import br.senai.sp.model.Medico;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ListaMedicos {

	@FXML TableView tabelaMedicos;
	@FXML TableColumn codigoMedico;
	@FXML TableColumn nomeMedico;
	@FXML TableColumn especialidadeMedico;
	@FXML TableColumn rgMedico;
	@FXML TableColumn crmMedico;
	@FXML TableColumn opcoesMedico;



	public void initialize(){
		atualizarListaMedicos();
		
	}

	@FXML public void voltarHome(){
		Main controller = new Main();
		Main.abrirTela("Main", controller);
	}
	
	@FXML public void abrirFormularioCadastro() {
		CadastroMedico controller = new CadastroMedico();
		Main.abrirTela("CadastroMedico", controller);
	}
	
	public void atualizarListaMedicos() {
		MedicoDAO dao = new MedicoDAO();

		ArrayList<Medico> lista = dao.selecionarTodos();
		codigoMedico.setCellValueFactory(new PropertyValueFactory<Medico, Integer>("idMedico"));
		nomeMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("nome"));
		especialidadeMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("especialidadesString"));
		rgMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("rg"));
		crmMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("crm"));
		opcoesMedico.setCellValueFactory(new PropertyValueFactory<Medico, Pane>("painelOpcoes"));
		
		for(Medico medico : lista) {
			
			HBox painel = new HBox();
			
			ImageView imgEditar = new ImageView(new Image("/br/senai/sp/imagens/editar.png"));
			ImageView imgDeletar = new ImageView(new Image("/br/senai/sp/imagens/delete.png"));
			
			imgDeletar.setOnMouseClicked(value -> {
				int sucesso = dao.deletarMedico(medico.getIdMedico());
				
				if(sucesso > 0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Deletar médico");
					alert.setContentText("Médico deletado com sucesso!!");
					alert.showAndWait();
					atualizarListaMedicos();
					
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERRO");
					alert.setHeaderText("Mensagem: ");
					alert.setContentText("Erro ao deletar médico!!");
				}
			});
			
			painel.setAlignment(Pos.CENTER);
			
			painel.getChildren().add(imgEditar);
			painel.getChildren().add(imgDeletar);
			medico.setPainelOpcoes(painel);
			
		}
		
		tabelaMedicos.setItems(
				FXCollections.observableArrayList(lista)
				);
	}

}
