package GUI;

import bean.*;
import boundary.InterfacciaProfessore;
import entity.fasciaOraria;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InputSpecificheAulaConferenza {
    @FXML
    Button button;
    @FXML
    CheckBox proiettoreFlag;
    @FXML
    CheckBox micFlag;
    @FXML
    CheckBox lavagnaFlag;
    @FXML
    CheckBox lavagnaInterFlag;
    @FXML
    CheckBox preseFlag;
    @FXML
    CheckBox ethernetFlag  ;
    @FXML
    TextField titoloConferenza;
    @FXML
    TextField numeroPosti;
    @FXML
    DatePicker dataInizio;
    @FXML
    DatePicker dataFine;
    @FXML
    ComboBox<fasciaOraria> fasciaOrariaComboBox;
    @FXML
    TextArea negativeTextArea;
    @FXML
    Button backToMenu;
    @FXML
    Label rispostaNegativa;
    //todo ---- fascia oraria wrap logic ....

    @FXML
    public void queryToBoundary(javafx.event.ActionEvent event) throws Exception {
        //TODO POSSIBILE IN CASO DI RISPOSTA NEGATIVA RESTARE QUI E MODIFICARE CAMPI?
        //|--> io penso che logica di trovare modo di migliorare prenotazione va in altra classe/controller...

        //questo metodo prende caratteristiche richiesta per queries
        //e si collega con boundary

        System.out.println("buttonPressed");
        // fields taken wrapped to beans
        BeanCaratteristicheAula beanCaratteristicheAula= this.wrapCarAulaFields();
        BeanSpecificheConferenza beanSpecificheConferenza= this.wrapSpecifConferenza();
        //debug print
        System.out.println("caratterestiche"+beanCaratteristicheAula+"\n\n"+beanSpecificheConferenza);


        InterfacciaProfessore boundaryUCGroup= LogController.interfacciaUtente.getInterfacciaProf();
        BoundaryTaskWrap boundaryTaskWrap = new BoundaryTaskWrap(boundaryUCGroup,beanSpecificheConferenza,beanCaratteristicheAula);
        /* javafx multithread version
        boundaryTaskWrap.call();
        BeanRisposta risposta = boundaryTaskWrap.getValue();
        System.out.println(boundaryTaskWrap.getState());*/
        //sequental version...
        BeanRisposta risposta = boundaryUCGroup.prenotazioneConCaratteristiche(beanSpecificheConferenza,beanCaratteristicheAula);
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

    private BeanCaratteristicheAula wrapCarAulaFields() {
        //test
        //System.out.println(this.ethernetFlag.isSelected()+"preso checkbox stato ethernetFlag");
        //isSelected() ritorna boolean in base se è stato (non) triggerato il checkbox
        String titoloConf =this.titoloConferenza.getText();
        System.out.println(titoloConf);
        String numeroPosti=this.numeroPosti.getText();
        int numPosti=1;             //default valude only for debug
        if (!numeroPosti.equals(""))
        { //todo error of invalid input data has to be genereted
        numPosti = Integer.parseInt(this.numeroPosti.getText());}
        boolean proiettoreFlag= this.proiettoreFlag.isSelected();
        boolean  micFlag= this.micFlag.isSelected();
        boolean  lavagnaFlag= this.lavagnaFlag.isSelected();
        boolean  lavagnaInterFlag= this.lavagnaInterFlag.isSelected();
        boolean  preseFlag= this.preseFlag.isSelected();
        boolean  ethernetFlag = this.ethernetFlag.isSelected() ;
        String type="Conferenza";
        BeanCaratteristicheAula beanCaratteristicheAula =
                new BeanCaratteristicheAula(numPosti,proiettoreFlag,micFlag,lavagnaFlag,lavagnaInterFlag,preseFlag,ethernetFlag);
        //omioDioquantaroba :(

        return beanCaratteristicheAula;

    }
}