package br.senai.sp.model;

import java.util.ArrayList;

import javafx.scene.layout.Pane;

public class Medico {
	private int idMedico;
	private String nome;
	private String rg;
	private ArrayList<Especialidade> listaEspecialidades;
	private ArrayList<HorarioAtendimento> listaHorarioAtendimento;
	private String especialidadesString;
	private String crm;
	private Pane painelOpcoes;

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public ArrayList<Especialidade> getListaEspecialidades() {
		return listaEspecialidades;
	}

	public void setListaEspecialidades(ArrayList<Especialidade> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}

	public ArrayList<HorarioAtendimento> getListaHorarioAtendimento() {
		return listaHorarioAtendimento;
	}

	public void setListaHorarioAtendimento(ArrayList<HorarioAtendimento> listaHorarioAtendimento) {
		this.listaHorarioAtendimento = listaHorarioAtendimento;
	}

	public String getEspecialidadesString() {
		return especialidadesString;
	}

	public void setEspecialidadesString(String especialidadesString) {
		this.especialidadesString = especialidadesString;
	}

	public Pane getPainelOpcoes() {
		return painelOpcoes;
	}

	public void setPainelOpcoes(Pane painelOpcoes) {
		this.painelOpcoes = painelOpcoes;
	}



}
