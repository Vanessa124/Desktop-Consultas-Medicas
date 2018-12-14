package br.senai.sp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sp.model.Especialidade;
import jdbc.ConexaoBanco;

public class EspecialidadeDAO {

	public ArrayList<Especialidade> selecionarTodos(){
		ArrayList<Especialidade> lista = new ArrayList<>();

		String sql = "SELECT * FROM especialidade ORDER BY idEspecialidade DESC";

		try {
			PreparedStatement str = ConexaoBanco.abrirConexao().prepareStatement(sql);

			ResultSet rs = str.executeQuery();

			while(rs.next()){
				Especialidade e = new Especialidade();
				e.setDescricao(rs.getString("descricao"));
				e.setNome(rs.getString("nome"));
				e.setIdEspecialidade(rs.getInt("idEspecialidade"));

				lista.add(e);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConexaoBanco.fecharConexao();
		
		return lista;
	}

	public int salvarEspecialidade(Especialidade especialidade){

		int sucesso = 0;

		String sql = " INSERT INTO especialidade SET nome = ?, descricao = ?";

		try {
			PreparedStatement str = ConexaoBanco.abrirConexao().prepareStatement(sql);
			str.setString(1, especialidade.getNome());
			str.setString(2, especialidade.getDescricao());

			sucesso = str.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConexaoBanco.fecharConexao();

		return sucesso;

	}
	
	public int editarEspecialidade(Especialidade especialidade) {
		
		int sucesso = 0;
		
		String sql = " UPDATE especialidade SET nome = ?, descricao = ? WHERE idEspecialidade = ? ";
		
		try {
			PreparedStatement str = ConexaoBanco.abrirConexao().prepareStatement(sql);
			str.setString(1, especialidade.getNome());
			str.setString(2, especialidade.getDescricao());
			str.setInt(3, especialidade.getIdEspecialidade());

			sucesso = str.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConexaoBanco.fecharConexao();
		
		return sucesso;
		
	}

	public ArrayList<Especialidade> selecionarPorMedico(int idMedico){
		ArrayList<Especialidade> lista = new ArrayList<>();

		String sql = "SELECT e.nome, e.descricao, e.idEspecialidade FROM medico AS m "
				+ "INNER JOIN especialidade_medico AS e_m ON e_m.idMedico = m.idMedico "
				+ "INNER JOIN especialidade AS e ON e.idEspecialidade = e_m.idEspecialidade "
				+ "WHERE m.idMedico = ?";


		try {
			PreparedStatement str = ConexaoBanco.abrirConexao().prepareStatement(sql);
			str.setInt(1, idMedico);

			ResultSet rs = str.executeQuery();

			while(rs.next()){
				Especialidade e = new Especialidade();
				e.setDescricao(rs.getString("descricao"));
				e.setNome(rs.getString("nome"));
				e.setIdEspecialidade(rs.getInt("idEspecialidade"));

				lista.add(e);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConexaoBanco.fecharConexao();

		return lista;
	}
	
	public int deletarEspecialidade(int idEspecialidade) {
		
		int sucesso = 0;
		
		String sql = " DELETE FROM especialidade WHERE idEspecialidade = ? ";
		
		try {
			PreparedStatement str = ConexaoBanco.abrirConexao().prepareStatement(sql);
			str.setInt(1, idEspecialidade);

			sucesso = str.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConexaoBanco.fecharConexao();
		
		return sucesso;
		
	}

}
