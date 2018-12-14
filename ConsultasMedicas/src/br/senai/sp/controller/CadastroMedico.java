package br.senai.sp.controller;


import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aplication.Main;
import br.senai.sp.dao.EspecialidadeDAO;
import br.senai.sp.dao.MedicoDAO;
import br.senai.sp.model.Especialidade;
import br.senai.sp.model.HorarioAtendimento;
import br.senai.sp.model.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public class CadastroMedico {
	
	int idDomingo = 1;
	int idSegunda = 2;
	int idTerca = 3;
	int idQuarta = 4;
	int idQuinta = 5;
	int idSexta = 6;
	int idSabado = 7;

	@FXML Pane painelEspecialidades;
	@FXML ComboBox<String> domInicio;
	@FXML ComboBox<String> domFim;
	@FXML ComboBox<String> segInicio;
	@FXML ComboBox<String> segFim;
	@FXML ComboBox<String> terInicio;
	@FXML ComboBox<String> terFim;
	@FXML ComboBox<String> quaInicio;
	@FXML ComboBox<String> quaFim;
	@FXML ComboBox<String> quinInicio;
	@FXML ComboBox<String> quinFim;
	@FXML ComboBox<String> sexInicio;
	@FXML ComboBox<String> sexFim;
	@FXML ComboBox<String> sabInicio;
	@FXML ComboBox<String> sabFim;
	
	@FXML ComboBox<String> crmUF;
	
	@FXML TextField txtNomeMedico;
	@FXML TextField txtRgMedico;
	@FXML TextField crmNumero;
	
	DateFormat formatter = new SimpleDateFormat("HH");
	
	MedicoDAO dao = new MedicoDAO();
	
	@FXML private void initialize() {
		listarEspecialidades();
		
		List<ComboBox<String>> listaCombos = Arrays.asList(domFim, domInicio, segFim, segInicio, terFim, terInicio, quaFim,
				quaInicio, quinFim, quinInicio, sexFim, sexInicio, sabFim, sabInicio);
		
		preencherHorasSelect(listaCombos);
		preencherSiglasSelect();
		
		domInicio.valueProperty().addListener((value) -> {
			if(domInicio.getValue().equals("Dia livre")) {
				domFim.setDisable(true);
			} else {
				domFim.setDisable(false);
			}
		});
		
		segInicio.valueProperty().addListener((value) -> {
			if(segInicio.getValue().equals("Dia livre")) {
				segFim.setDisable(true);
			} else {
				segFim.setDisable(false);
			}
		});
		
		terInicio.valueProperty().addListener((value) -> {
			if(terInicio.getValue().equals("Dia livre")) {
				terFim.setDisable(true);
			} else {
				terFim.setDisable(false);
			}
		});
		
		quaInicio.valueProperty().addListener((value) -> {
			if(quaInicio.getValue().equals("Dia livre")) {
				quaFim.setDisable(true);
			} else {
				quaFim.setDisable(false);
			}
		});
		
		quinInicio.valueProperty().addListener((value) -> {
			if(quinInicio.getValue().equals("Dia livre")) {
				quinFim.setDisable(true);
			} else {
				quinFim.setDisable(false);
			}
		});
		
		sexInicio.valueProperty().addListener((value) -> {
			if(sexInicio.getValue().equals("Dia livre")) {
				sexFim.setDisable(true);
			} else {
				sexFim.setDisable(false);
			}
		});
		
		sabInicio.valueProperty().addListener((value) -> {
			if(sabInicio.getValue().equals("Dia livre")) {
				sabFim.setDisable(true);
			} else {
				sabFim.setDisable(false);
			}
		});
	}
	
	private void listarEspecialidades() {
		
		EspecialidadeDAO dao = new EspecialidadeDAO();
		
		ArrayList<Especialidade> lista = dao.selecionarTodos();
		
		for (Especialidade especialidade : lista) {
			
			CheckBox checkBox = new CheckBox(especialidade.getNome());
			checkBox.setUserData(especialidade.getIdEspecialidade());
			checkBox.getStyleClass().add("checkbox");
			
			painelEspecialidades.getChildren().add(checkBox);
		}
		
		
	}
	
	
	private void preencherHorasSelect(List<ComboBox<String>> listaCombos) {
		
		for (ComboBox<String> comboBox : listaCombos) {
			ArrayList<String> listaHoras = new ArrayList<>();
			
			listaHoras.add("Dia livre");
			listaHoras.add("09");
			listaHoras.add("10");
			listaHoras.add("11");
			listaHoras.add("12");
			listaHoras.add("13");
			listaHoras.add("14");
			listaHoras.add("15");
			listaHoras.add("16");
			listaHoras.add("17");
			listaHoras.add("18");
			
			comboBox.setItems(FXCollections.observableArrayList(listaHoras));;
			
		}
		
	}
	
	private void preencherSiglasSelect() {
		
		ArrayList<String> lista = new ArrayList<>();
		lista.add("AC");
		lista.add("AL");
		lista.add("AP");
		lista.add("AM");
		lista.add("BA");
		lista.add("CE");
		lista.add("DF");
		lista.add("ES");
		lista.add("GO");
		lista.add("MA");
		lista.add("MT");
		lista.add("MS");
		lista.add("MG");
		lista.add("PA");
		lista.add("PB");
		lista.add("PR");
		lista.add("PE");
		lista.add("PI");
		lista.add("RJ");
		lista.add("RN");
		lista.add("RS");
		lista.add("RO");
		lista.add("RR");
		lista.add("SC");
		lista.add("SP");
		lista.add("SE");
		lista.add("TO");
		
		crmUF.setItems(FXCollections.observableArrayList(lista));
		
	}
	
	@FXML private void salvarDadosMedico() {
		
		Medico medico = new Medico();
		
		String crm = crmNumero.getText() + "/" + crmUF.getValue();
		
		medico.setCrm(crm);
		medico.setNome(txtNomeMedico.getText());
		medico.setRg(txtRgMedico.getText());
		
		//Salva os dados pessoais e retorna o id do novo medico
		int idMedico = dao.salvarDadosPessoais(medico);
		
		if(idMedico > 0) {
			//Salvando as especialidades do médico
			ObservableList<Node> listaEspecialidades = painelEspecialidades.getChildren();
			
			for (Node node : listaEspecialidades) {
				
				CheckBox checkBox = (CheckBox) node;
				
				int idCheckbox = (int) checkBox.getUserData();
				
				if(checkBox.isSelected()) {
					dao.salvarEspecialidadeMedico(idMedico, idCheckbox);
				}
				
			}
			
			//Salvando os horários de atendimento do médico
			try {
				/*Domingo*/
				if(!domInicio.getValue().equals("Dia livre")) {
					Time domInicioTime = new Time(formatter.parse(domInicio.getValue()).getTime());
					Time domFimTime= new Time(formatter.parse(domFim.getValue()).getTime());
					
					salvarHorariosAtendimento(domInicioTime, domFimTime, idDomingo, idMedico);
				}
				
				/*Segunda*/
				if(!segInicio.getValue().equals("Dia livre")) {
					Time segInicioTime = new Time(formatter.parse(segInicio.getValue()).getTime());
					Time segFimTime = new Time(formatter.parse(segFim.getValue()).getTime());
					
					salvarHorariosAtendimento(segInicioTime, segFimTime, idSegunda, idMedico);
				}
				
				/*Terça*/
				if(!terInicio.getValue().equals("Dia livre")) {
					Time terInicioTime = new Time(formatter.parse(terInicio.getValue()).getTime());
					Time terFimTime = new Time(formatter.parse(terFim.getValue()).getTime());
					
					salvarHorariosAtendimento(terInicioTime, terFimTime, idTerca, idMedico);
				}
				
				/*Quarta*/
				if(!quaInicio.getValue().equals("Dia livre")) {
					Time quaInicioTime = new Time(formatter.parse(quaInicio.getValue()).getTime());
					Time quaFimTime = new Time(formatter.parse(quaFim.getValue()).getTime());
					
					salvarHorariosAtendimento(quaInicioTime, quaFimTime, idQuarta, idMedico);
				}
				
				/*Quinta*/
				if(!quinInicio.getValue().equals("Dia livre")) {
					Time quinInicioTime = new Time(formatter.parse(quinInicio.getValue()).getTime());
					Time quinFimTime = new Time(formatter.parse(quinFim.getValue()).getTime());
					
					salvarHorariosAtendimento(quinInicioTime, quinFimTime, idQuinta, idMedico);
				}
				
				
				/*Sexta*/
				if(!sexInicio.getValue().equals("Dia livre")) {
					Time sexInicioTime = new Time(formatter.parse(sexInicio.getValue()).getTime());
					Time sexFimTime = new Time(formatter.parse(sexFim.getValue()).getTime());
					
					salvarHorariosAtendimento(sexInicioTime, sexFimTime, idSexta, idMedico);
				}
				
				
				/*Sabado*/
				if(!sabInicio.getValue().equals("Dia livre")) {
					Time sabInicioTime = new Time(formatter.parse(sabInicio.getValue()).getTime());
					Time sabFimTime = new Time(formatter.parse(sabFim.getValue()).getTime());
					
					salvarHorariosAtendimento(sabInicioTime, sabFimTime, idSabado, idMedico);
				}
				
				/** Alerta de sucesso e redirecionamento de tela **/
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Status do cadastro");
				alert.setHeaderText("Mensagem: ");
				alert.setContentText("Médico cadastrado com sucesso!!");
				alert.showAndWait();
				
				ListaMedicos controller = new ListaMedicos();
				Main.abrirTela("listaMedicos", controller);
			
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			//Erro ao salvar médico
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Status do cadastro");
			alert.setHeaderText("Mensagem: ");
			alert.setContentText("Erro ao cadastrar médico!");
			alert.showAndWait();
		}
		
		
	}
	
	private void salvarHorariosAtendimento(Time horario_inicio, Time horario_fim, int idDiaSemana, int idMedico) {
		
		HorarioAtendimento horarioAtendimento = new HorarioAtendimento();
		horarioAtendimento.setHorario_fim(horario_fim);
		horarioAtendimento.setHorario_inicio(horario_inicio);
		horarioAtendimento.setIdDiaSemana(idDiaSemana);
		horarioAtendimento.setIdMedico(idMedico);
		
		dao.salvarHorarioAtendimento(horarioAtendimento);
		
	}
}

