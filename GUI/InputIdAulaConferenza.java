package GUI;

import bean.BeanIdAula;
import bean.BeanRisposta;
import bean.BeanSpecificheConferenza;
import boundary.InterfacciaProfessore;
import entity.fasciaOraria;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InputIdAulaConferenza {
    @FXML
    TextField titoloConferenza;
    @FXML
    TextField idAula;
    @FXML
    DatePicker dataInizio;
    @FXML
    DatePicker dataFine;
    @FXML
    ComboBox<fasciaOraria> fasciaOrariaComboBox;
    @FXML
    Button okButton;
    @FXML
    Button backToMenuButton;
    @FXML
    TextArea negativeTextArea;
    
    @FXML
    public void backToMenu(ActionEvent event) throws Exception {
        //ritorna al menu
        ViewSwap.getIstance().swap(event,ViewSwap.MENU);
    }
    
    private BeanSpecificheConferenza wrapSpecifConferenza() throws Exception {
        //todo fasciaoraria
        //tmp fasciaOraria autoset to prima
        fasciaOraria fasciaOraria= entity.fasciaOraria.prima;
        String titoloConf = this.titoloConferenza.getText();
        //getting dates :)
        /* converting local date to date.....
        LocalDate DateTmp = dataInizio.getValue();
        Instant instantTmp = Instant.from(DateTmp.atStartOfDay(ZoneId.systemDefault()));
        Date inizio = Date.from(instantTmp);
        DateTmp = dataFine.getValue();
        instantTmp = Instant.from(DateTmp.atStartOfDay(ZoneId.systemDefault()));
        Date fine = Date.from(instantTmp); */
        LocalDate inizio= this.dataInizio.getValue();
        LocalDate fine= this.dataFine.getValue();
        BeanSpecificheConferenza beanSpConf= new BeanSpecificheConferenza (titoloConf,inizio,fine,fasciaOraria,GuiMain.profId);

        return beanSpConf;
    }
    
    private BeanIdAula wrapIdAulaField() {
        int idAula = Integer.parseInt(this.idAula.getText());
        return new BeanIdAula(idAula);
        
    }
    
    @FXML
    public void queryToBoundary(javafx.event.ActionEvent event) throws Exception {
        //TODO POSSIBILE IN CASO DI RISPOSTA NEGATIVA RESTARE QUI E MODIFICARE CAMPI?
        //|--> io penso che logica di trovare modo di migliorare prenotazione va in altra classe/controller...

        //questo metodo prende caratteristiche richiesta per queries
        //e si collega con boundary

        System.out.println("buttonPressed");
        // fields taken wrapped to beans
        BeanIdAula beanIdAula= this.wrapIdAulaField();
        BeanSpecificheConferenza beanSpecificheConferenza= this.wrapSpecifConferenza();
        //debug print
        System.out.println("caratterestiche>"+beanIdAula+"\n\n"+beanSpecificheConferenza);


        InterfacciaProfessore boundaryUCGroup= LogController.interfacciaUtente.getInterfacciaProf();
        BoundaryTaskWrap boundaryTaskWrap = new BoundaryTaskWrap(boundaryUCGroup,beanSpecificheConferenza,beanIdAula);
        /* javafx multithread version
        boundaryTaskWrap.call();
        BeanRisposta risposta = boundaryTaskWrap.getValue();
        System.out.println(boundaryTaskWrap.getState());*/
        //sequental version...
        BeanRisposta risposta = boundaryUCGroup.prenotazioneConId(beanSpecificheConferenza, beanIdAula);
        System.out.println(risposta);
        if (risposta.isValid()==false){
            this.gestistiRispostaNegativa(risposta);
            return;
            //N.B. in caso di risposta negativa non si accede a rispsota controller
        }
        System.out.println("debug...bean di risposta"+risposta);
        RispostaController rispostaController = new RispostaController(risposta);
        //passato stato di bean di risposta a altro controller
        ViewSwap.getIstance().swap(event,ViewSwap.RISPOSTA, rispostaController);
        //swapped to RispostaController with state... inside instance under reference rispostaCOntroller

    }

    private void gestistiRispostaNegativa(BeanRisposta risposta) {
        //TODO SAREBBE BELLO SE LABEL E TEXT AREA COMPARISSERO SOLO DA QUESTO METODO IN POI
        this.negativeTextArea.setText(risposta.toString());
    }
}
