package br.senai.sp.controller;

import java.util.ArrayList;

import aplication.Main;
import br.senai.sp.dao.EspecialidadeDAO;
import br.senai.sp.model.Especialidade;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class ListaEspecialidades {

	EspecialidadeDAO dao;

	@FXML TextField nomeEspecialidadeCadastro;
	@FXML TextArea descricaoEspecialidadeCadastro;

	@FXML TableView tabelaEspecialidades;
	@FXML TableColumn idEspecialidade;
	@FXML TableColumn opcoesEspecialidade;
	@FXML TableColumn nomeEspecialidade;
	@FXML TableColumn descricaoEspecialidade;

	@FXML Button btnSalvarEspecialidade;
	
	@FXML Label labelStatus;
	
	int idEditar = 0;

	@FXML public void voltarParaHome(){
		Main controller = new Main();
		Main.abrirTela("Main", controller);
	}

	@FXML private void salvarEspecialidade(){
		
		String nome = nomeEspecialidadeCadastro.getText();
		String descricao = descricaoEspecialidadeCadastro.getText();

		Especialidade especialidade = new Especialidade();
		especialidade.setDescricao(descricao);
		especialidade.setNome(nome);

		dao = new EspecialidadeDAO();
		
		if(labelStatus.getText().equals("Nova especialidade")) {
			int sucesso = dao.salvarEspecialidade(especialidade);
			if(sucesso > 0){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Status do cadastro");
				alert.setHeaderText("Mensagem: ");
				alert.setContentText("Especialidade cadastrada com sucesso!!");
				alert.showAndWait();
				atualizarListaEspecialidades();
				limparCamposForm();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Status do cadastro");
				alert.setHeaderText("Mensagem: ");
				alert.setContentText("Erro ao cadastrar especialidade!!");
			}
			
		} else {
			especialidade.setIdEspecialidade(idEditar);
			int sucesso = dao.editarEspecialidade(especialidade);
			if(sucesso > 0){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Edição da especialidade");
				alert.setContentText("Alterações salvas com sucesso!!");
				alert.showAndWait();
				atualizarListaEspecialidades();
				limparCamposForm();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Edição da especialidade");
				alert.setHeaderText("Mensagem: ");
				alert.setContentText("Erro ao editar especialidade!!");
			}

		}
		
		


	}

	public void initialize(){
		labelStatus.setText("Nova especialidade");
		atualizarListaEspecialidades();

	}

	public void atualizarListaEspecialidades(){
		dao = new EspecialidadeDAO();

		idEspecialidade.setCellValueFactory(new PropertyValueFactory<Especialidade, Integer>("idEspecialidade"));
		nomeEspecialidade.setCellValueFactory(new PropertyValueFactory<Especialidade, String>("nome"));
		descricaoEspecialidade.setCellValueFactory(new PropertyValueFactory<Especialidade, String>("descricao"));

		opcoesEspecialidade.setCellValueFactory(new PropertyValueFactory<Especialidade, Pane>("painelOpcoes"));

		ArrayList<Especialidade> lista = dao.selecionarTodos();
		
		for(Especialidade e : lista) {
			
			HBox painel = new HBox();
			
			ImageView imgEditar = new ImageView(new Image("/br/senai/sp/imagens/editar.png"));
			ImageView imgDeletar = new ImageView(new Image("/br/senai/sp/imagens/delete.png"));
			
			imgDeletar.setOnMouseClicked(value -> {
				int sucesso = dao.deletarEspecialidade(e.getIdEspecialidade());
				
				if(sucesso > 0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Deletar especialidade");
					alert.setContentText("Especialidade deletada com sucesso!!");
					alert.showAndWait();
					atualizarListaEspecialidades();
					
					labelStatus.setText("Nova especialidade");
					
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERRO");
					alert.setHeaderText("Mensagem: ");
					alert.setContentText("Erro ao deletar especialidade!!");
					
					labelStatus.setText("Nova especialidade");
				}
			});
			
			imgEditar.setOnMouseClicked(value -> {
				labelStatus.setText("Editar especialidade");
				idEditar = e.getIdEspecialidade();
				
				nomeEspecialidadeCadastro.setText(e.getNome());
				descricaoEspecialidadeCadastro.setText(e.getDescricao());
			});
			
			painel.setAlignment(Pos.CENTER);
			
			painel.getChildren().add(imgEditar);
			painel.getChildren().add(imgDeletar);
			e.setPainelOpcoes(painel);
		}
		
		tabelaEspecialidades.setItems(
				FXCollections.observableArrayList(lista)
				);
	}

	public void limparCamposForm(){
		nomeEspecialidadeCadastro.setText("");
		descricaoEspecialidadeCadastro.setText("");
	}

}
