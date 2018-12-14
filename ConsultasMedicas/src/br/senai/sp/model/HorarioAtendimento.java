package br.senai.sp.model;

import java.sql.Time;

public class HorarioAtendimento {
	private int idHorarioAtendimento;
	private Time horario_inicio;
	private Time horario_fim;
	private int idDiaSemana;
	private int idMedico;
	
	public int getIdHorarioAtendimento() {
		return idHorarioAtendimento;
	}
	public void setIdHorarioAtendimento(int idHorarioAtendimento) {
		this.idHorarioAtendimento = idHorarioAtendimento;
	}
	public Time getHorario_inicio() {
		return horario_inicio;
	}
	public void setHorario_inicio(Time horario_inicio) {
		this.horario_inicio = horario_inicio;
	}
	public Time getHorario_fim() {
		return horario_fim;
	}
	public void setHorario_fim(Time horario_fim) {
		this.horario_fim = horario_fim;
	}
	public int getIdDiaSemana() {
		return idDiaSemana;
	}
	public void setIdDiaSemana(int idDiaSemana) {
		this.idDiaSemana = idDiaSemana;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	
	
}


