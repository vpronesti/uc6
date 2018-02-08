package GUI;
//TODO ora qui non saranno piu stampate risposte negative
import bean.BeanRisposta;
import bean.BeanRispostaAffermativa;
import bean.BeanRispostaNegativa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
/*
NOT CHECKED YET :(
 */
public class RispostaController implements Initializable {
    @FXML
    TextArea outputArea;
    @FXML
    Button loggout;
    @FXML
    Button Menu;
    BeanRisposta beanRisposta;
    public RispostaController(BeanRisposta beanRisposta) {
        this.beanRisposta=beanRisposta;

        //setted bean di risposta as state
        //data of bean passed from another controller
        //this newly istance will be passed to ViewSwap to swap fxml(view) with a setted controller istance passed
        //donw by setController method

    }
    //todo con l integrazione dei uc di gruppo questo pulsante rimandera al menu principale
    public void initialize(){
        this.outputArea.setText(this.displayBeanRisposta());
    }
    protected String displayBeanRisposta(){
        if (beanRisposta.isValid()) {
            BeanRispostaAffermativa beanRispostaAffermativa= (BeanRispostaAffermativa) beanRisposta;
            //cast di risposta affermativa
            //this.outputArea.setText(beanRispostaAffermativa.toString());
            return (beanRispostaAffermativa.toString());
        }
        else {
            BeanRispostaNegativa beanRispostaNegativa = (BeanRispostaNegativa) beanRisposta;
            //this.outputArea.setText(beanRispostaNegativa.toString());
            return (beanRispostaNegativa.toString());
        }
        //todo to string bean di risposta update
    }
    @FXML
    protected void loggout(javafx.event.ActionEvent event) throws Exception {
        //todo boundary has to die ....
        //swapping view (fxml loggin)
        LogController.interfacciaUtente.loggout();
        ViewSwap.getIstance().swap(event,ViewSwap.LOGGIN);
    }
    @FXML
    protected void gotoMenu(javafx.event.ActionEvent event) throws Exception {
        //todo boundary has to die ....
        //swapping view (fxml loggin)
        ViewSwap.getIstance().swap(event,ViewSwap.MENU);
    }

}
