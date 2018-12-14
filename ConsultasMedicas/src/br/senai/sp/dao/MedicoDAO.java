package br.senai.sp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import br.senai.sp.model.Especialidade;
import br.senai.sp.model.HorarioAtendimento;
import br.senai.sp.model.Medico;
import jdbc.ConexaoBanco;

public class MedicoDAO {

	EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();

	public ArrayList<Medico> selecionarTodos(){

		ArrayList<Medico> lista = new ArrayList<>();

		String sql = "SELECT * FROM medico";

		try {
			PreparedStatement str = ConexaoBanco.abrirConexao().prepareStatement(sql);
			ResultSet rs = str.executeQuery();

			while(rs.next()){
				Medico medico = new Medico();
				medico.setIdMedico(rs.getInt("idMedico"));
				medico.setNome(rs.getString("nome"));
				medico.setRg(rs.getString("rg"));
				medico.setCrm(rs.getString("crm"));

				ArrayList<Especialidade> listaEspecialidade = especialidadeDAO.selecionarPorMedico(medico.getIdMedico());

				medico.setListaEspecialidades(listaEspecialidade);

				String listaEspecialidadesString = "";

				for (Especialidade especialidade : listaEspecialidade) {
					listaEspecialidadesString  += especialidade.getNome() + "    ";
				}

				medico.setEspecialidadesString(listaEspecialidadesString);

				lista.add(medico);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConexaoBanco.fecharConexao();

		return lista;

	}
	
	public int salvarDadosPessoais(Medico medico) {
		
		int idMedico = 0;
		
		String sql = "INSERT INTO medico SET nome = ?, rg = ?, crm = ?";
		
		try {
			PreparedStatement str = ConexaoBanco.abrirConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			str.setString(1, medico.getNome());
			str.setString(2, medico.getRg());
			str.setString(3, medico.getCrm());
			
			str.executeUpdate();
			
			ResultSet rs = str.getGeneratedKeys();
			idMedico = 0;
			if(rs.next()){
				idMedico = rs.getInt(1);
			}		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("IdMedico: " + idMedico);
		
		ConexaoBanco.fecharConexao();
		
		return idMedico;
		
	}
	
	public void salvarHorarioAtendimento(HorarioAtendimento horarioAtendimento) {
		
		String sql = "INSERT INTO horario_atendimento SET horario_inicio = ?, horario_fim = ?, idDiaSemana = ?, idMedico = ?";
		
		try {
			PreparedStatement str = ConexaoBanco.abrirConexao().prepareStatement(sql);
			str.setTime(1, horarioAtendimento.getHorario_inicio());
			str.setTime(2, horarioAtendimento.getHorario_fim());
			str.setInt(3, horarioAtendimento.getIdDiaSemana());
			str.setInt(4, horarioAtendimento.getIdMedico());
			
			str.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConexaoBanco.fecharConexao();
		
	}
	
	public void salvarEspecialidadeMedico(int idMedico, int idEspecialidade) {
		
		String sql = " INSERT INTO especialidade_medico SET idMedico = ?, idEspecialidade = ? ";
		
		try {
			PreparedStatement str = ConexaoBanco.abrirConexao().prepareStatement(sql);
			str.setInt(1, idMedico);
			str.setInt(2, idEspecialidade);
			
			str.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConexaoBanco.fecharConexao();
		
	}
	
	public int deletarMedico(int idMedico) {
		int sucesso = 0;
		
		String sql = "DELETE FROM medico WHERE idMedico = ?";
		
		try {
			PreparedStatement str = ConexaoBanco.abrirConexao().prepareStatement(sql);
			str.setInt(1, idMedico);

			sucesso = str.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConexaoBanco.fecharConexao();
		
		return sucesso;
	}
}
