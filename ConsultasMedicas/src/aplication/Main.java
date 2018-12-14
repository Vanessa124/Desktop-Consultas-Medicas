package aplication;

import br.senai.sp.controller.ListaEspecialidades;
import br.senai.sp.controller.ListaMedicos;
import br.senai.sp.model.Especialidade;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	static Stage primaryStage;


	@Override
	public void start(Stage primaryStage) {

		Main.primaryStage = primaryStage;

		Main controller = new Main();

		abrirTela("Main", controller);

	}

	public static void abrirTela(String fileName, Object controller){
		//Abrir tela:
		Parent tela;

		try{

			FXMLLoader loader = new FXMLLoader(Main.class.getResource("../br/senai/sp/view/" + fileName+".fxml"));

			//definindo controller
			loader.setController(controller);

			//carregar o arquivo XML
			tela = loader.load();
			
			//Criando a cena
			Scene sc = new Scene(tela);
			
			//carregando o css
			sc.getStylesheets().add("/br/senai/sp/css/style.css");

			//Exibindo a cena no stage principal
			primaryStage.setScene(sc);
			primaryStage.setResizable(false);
			primaryStage.show();



		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@FXML public void gerenciarEspecialidades() {
		ListaEspecialidades controller = new ListaEspecialidades();
		abrirTela("listaEspecialidades", controller);

	}

	@FXML public void abrirTelaMedico(){
		ListaMedicos controller = new ListaMedicos();
		abrirTela("listaMedicos", controller);
	}

}
