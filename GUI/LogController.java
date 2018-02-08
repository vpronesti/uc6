package GUI;

import boundary.InterfacciaUtente;
import controller.GestoreLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LogController {
    protected static InterfacciaUtente interfacciaUtente;
    @FXML
    private TextField username;
    @FXML
    private PasswordField paswoord;
    @FXML
    Text textArea;

    //@FXML
    //private ComboBox<String> combo;
    @FXML
    private javafx.scene.text.Text negativeOutput;
    /*@FXML
    public void push (ActionEvent event) throws Exception {

        System.out.println("buttonPressed");
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent pane2 = FXMLLoader.load(getClass().getResource("/GUI/SpecificaCarAulaConf.fxml"));
        stage.setScene(new Scene(pane2));
        //old

        //Parent newPane = FXMLLoader.load(getClass().getResource(ViewSwap.VIEW2));
        //loaded new scene? --> new content to bind to the stage
        System.out.println("pushed"+"\tcurrent thread\t\t->"+Thread.currentThread().getId());
        ViewSwap.getIstance().swap( event,ViewSwap.VIEW2);
    }*/// schema base per interfacciarsi con classe swap per cambiare fxml
    @FXML
    public void loggin (ActionEvent event) throws Exception {
        /*
        System.out.println("buttonPressed");
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent pane2 = FXMLLoader.load(getClass().getResource("/GUI/SpecificaCarAulaConf.fxml"));
        stage.setScene(new Scene(pane2));
        //old
        */
        //Parent newPane = FXMLLoader.load(getClass().getResource(ViewSwap.VIEW2));
        //loaded new scene? --> new content to bind to the stage
        System.out.println("pushed" + "\tcurrent thread\t\t->" + Thread.currentThread().getId());
        String usernameEntered = (String) this.username.getCharacters().toString();
        String paswoordEntered = (String) this.paswoord.getCharacters().toString();
        this.paswoord.clear();                                              //clear entered characters
        System.out.println("entered..." + usernameEntered + "\t\t" + paswoordEntered);
        String type = "Professore"; //todo aggiungi alla grafica scelta tipo
        interfacciaUtente = new InterfacciaUtente(usernameEntered, paswoordEntered, type);
        if (interfacciaUtente.loggin()) {
            ViewSwap.getIstance().swap(event, ViewSwap.MENU);
        }
        else {
            this.textArea.setText("LOGGIN ERRATO \n RIPROVARE...");
        }
    }
    private void displayNotFoundUser(){
        this.negativeOutput.setFont(new Font(22));
        this.negativeOutput.setText("error");
    }
}
//TODO USEFUL??
  /*public void eseguiMetodo() {
        System.out.println(areaDiTesto.getText());

        String contenutoCombo = combo.getSelectionModel().getSelectedItem();
        System.out.println(contenutoCombo);

        ObservableList<Persona> persone =
                FXCollections.observableArrayList(
                new Persona("Mario", "Rossi"),
                new Persona("Carla", "Verdi")
        );
        listaElementi.setItems(persone);
    }*/
   /*public void initialize() {
        ObservableList<String> valori =
                FXCollections.observableArrayList("AAA 1", "Option 2", "25");
        combo.setItems(valori);
        listaElementi.setEditable(true);

        TableColumn colonnaNome = new TableColumn("Nome");
        TableColumn colonnaCognome = new TableColumn("Cognome");

        colonnaNome.setCellValueFactory(
                new PropertyValueFactory<sample.Persona,String>("nome")
        );
        colonnaCognome.setCellValueFactory(
                new PropertyValueFactory<Persona,String>("cognome")
        );

        listaElementi.getColumns().addAll(
                colonnaNome, colonnaCognome
        );
    }*/
