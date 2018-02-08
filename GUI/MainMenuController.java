package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {
    //logged to acces this controller
    @FXML
    Button PrenotazioneConId;
    @FXML
    Button PrenotazioneConCaratteristiche;
    @FXML
    Button loggout;

    @FXML
    protected void loggout(ActionEvent event) throws Exception {
        //todo destroy possible boundaries
        //...
        //swap view (fxml)
        LogController.interfacciaUtente.loggout();
        ViewSwap.getIstance().swap(event,ViewSwap.LOGGIN);
    }
    @FXML
    protected void PrenotazioneConId(ActionEvent event) throws Exception {
        //todo GOTO PRENOTAZIONE CON ID CONTROLLER.... SUBTIPE OF SPECIFICACARAULACONTROLLER?
        ViewSwap.getIstance().swap(event, ViewSwap.INPUTID);
    }
    @FXML
    protected void prenotazioneConCaratteristiche(ActionEvent event) throws Exception {
        //goto another controller
        //todo states to take - send ?
        ViewSwap.getIstance().swap(event,ViewSwap.INPUTCONFERENZA);
    }
}
