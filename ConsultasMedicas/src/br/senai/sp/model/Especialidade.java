package br.senai.sp.model;

import javafx.scene.layout.Pane;

public class Especialidade {
	private int idEspecialidade;
	private String nome;
	private String descricao;
	private Pane painelOpcoes;
 

	public int getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(int idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pane getPainelOpcoes() {
		return painelOpcoes;
	}

	public void setPainelOpcoes(Pane painelOpcoes) {
		this.painelOpcoes = painelOpcoes;
	}
	
	


}
