package br.senai.sp.model;

import java.util.Date;

public class Consulta {
	private int idConsulta;
	private Date data;
	private Date horario;
	private int idMedico;
	private String nomePaciente;
	private String rgPaciente;
	private int idEspecialidade;

	public int getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getRgPaciente() {
		return rgPaciente;
	}

	public void setRgPaciente(String rgPaciente) {
		this.rgPaciente = rgPaciente;
	}

	public int getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(int idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}


}
