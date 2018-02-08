package controller;

import Dao.AulaConferenzaDao;
import bean.*;
import boundary.*;
import entity.AulaConferenza;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;


public class GestoreRichieste {
    private InterfacciaProfessore prof;
    private List<GestorePrenotazioneConferenza> gestorePrenotazioni;
    private boolean persist=true;
    //diventa false se una prenotazione è null...
    private List<LocalDate> LocalDateNonDisponibili;
    //conterra la lista di LocalDate non buone per la prenotazione
    public int durataEvento; // x ora è public
    private LocalDate dataInizio;

    public GestoreRichieste(InterfacciaProfessore boundary){
        this.prof=boundary;
        this.gestorePrenotazioni=new ArrayList<GestorePrenotazioneConferenza>();
        this.LocalDateNonDisponibili=new ArrayList<>();
    }
    public BeanRisposta gestioneRichieste(BeanCaratteristicheAula beanCaratteristiche,BeanSpecificheConferenza beanInfoTemporali){
        //separare richieste su piu giorni analizzando
        //versione con caratteristiche
        this.dataInizio=beanInfoTemporali.getDataInizio();
        Period intevallo= Period.between(beanInfoTemporali.getDataInizio(), beanInfoTemporali.getDataFine());//TODO check durata in classe LocalDate
        this.durataEvento=intevallo.getDays();
        System.out.println(durataEvento);
        List<AulaConferenza> auleTmp= this.ricercaAulaConCaratteristiche(beanCaratteristiche,beanInfoTemporali);
        if (auleTmp.isEmpty())
            return new BeanRispostaNegativa(true,false);

        //aule temporanee aderenti alle caratteristiche fisiche inserite
        for(int i=0;i<=this.durataEvento;i++){
            BeanSpecificheConferenza beanTemp=null;
            try {


            beanTemp=beanInfoTemporali.getClone();
            }
            catch (Exception E) {
                E.printStackTrace();
            }
            beanTemp.setDataFine(beanInfoTemporali.getDataInizio().plusDays(i));
            beanTemp.setDataInizio(beanInfoTemporali.getDataInizio().plusDays(i));
            //bean temp rappresenta una suddivisione della richiesta su un giorno solo
            GestorePrenotazioneConferenza gestorePrentTemp=new GestorePrenotazioneConferenza();
            gestorePrenotazioni.add(gestorePrentTemp);
            gestorePrentTemp.setAule(auleTmp);
            gestorePrentTemp.verificaAulaConCaratteristiche(beanTemp);

            //la chiamata comportera il set del attributo   prenotazione...
            //alla fine delle richiste su tutti i giorni va controllato tale attributo
            //anche se una richiesta di prenotazione fallisce il for deve finire per dare info dettagliate alla boundary

            }

            //tutte le richieste su piu giorni sono state effetuate...
        this.joinPrenotazioni();
        return this.creaRisposta();
        //  la grafica si deve castare il bean di risposta in affermativo o negativo
        // in base al flag contenuto all interno...isValid

    }

    public BeanRisposta gestioneRichieste(BeanSpecificheConferenza beanInfo,
                                          BeanIdAula beanId) {
        this.dataInizio=beanInfo.getDataInizio();
        Period intevallo= Period.between(beanInfo.getDataInizio(), beanInfo.getDataFine());//TODO check durata in classe LocalDate
        this.durataEvento=intevallo.getDays();
        // verifica esistenza aula
        AulaConferenza aulaTmp= this.ricercaAulaConId(beanId);
        if (aulaTmp == null)
            return new BeanRispostaNegativa(true,false);
        List<AulaConferenza> aule = new ArrayList<>();
        aule.add(aulaTmp);
        for (int i = 0; i <= this.durataEvento; i++) {
            BeanSpecificheConferenza beanTemp = null;
            try {
                beanTemp = beanInfo.getClone();
            } catch (Exception e) {
                e.printStackTrace();
            }
            beanTemp.setDataFine(beanInfo.getDataInizio().plusDays(i));
            beanTemp.setDataInizio(beanInfo.getDataInizio().plusDays(i));
            GestorePrenotazioneConferenza gestorePrenTemp =
                    new GestorePrenotazioneConferenza();
            gestorePrenotazioni.add(gestorePrenTemp);
            gestorePrenTemp.setAule(aule);
            gestorePrenTemp.verificaDisponibilitaId(beanTemp);
        }
        this.joinPrenotazioniId();
        return this.creaRisposta();
    }

    private void joinPrenotazioniId(){
        Iterator<GestorePrenotazioneConferenza> i=this.gestorePrenotazioni.iterator();
        int j=0;
        while (i.hasNext()){
            if (i.next().getPrenotazione()==null){
                LocalDate dataNonDisponibile=(this.dataInizio.plusDays(j));
                this.LocalDateNonDisponibili.add(dataNonDisponibile);
                this.persist=false;
                //non verra effetuata alcuna persistenza dato che esiste una data non disponibile
                ++j;
            }
        }
        if (persist){
            i=this.gestorePrenotazioni.iterator();

            while(i.hasNext()){
                GestorePrenotazioneConferenza k = i.next();
                k.persisti(prof.getUsernameProf());

                    //manda in persistenza le singole prenotazioni su piu giorni
                }
            }
    }
    
    private BeanRisposta creaRisposta() {
        BeanRisposta beanRisposta;
        List<Integer> idAule = new ArrayList<>();
        List<LocalDate> LocalDate = new ArrayList<>() ;
        if(this.persist) {
            Iterator<GestorePrenotazioneConferenza> i = this.gestorePrenotazioni.iterator();
            while(i.hasNext()) {
                GestorePrenotazioneConferenza p = i.next();
                idAule.add(p.getPrenotazione().getIdAula());
                LocalDate.add(p.getPrenotazione().getData());
            }
            beanRisposta = new BeanRispostaAffermativa(idAule,true,LocalDate);
        }
        else {
             beanRisposta = new BeanRispostaNegativa(this.LocalDateNonDisponibili,false);
        }
        return beanRisposta;
    }

    private void joinPrenotazioni(){
        Iterator<GestorePrenotazioneConferenza> i=this.gestorePrenotazioni.iterator();
        int j=0;
        while (i.hasNext()){

            GestorePrenotazioneConferenza controllerPren=i.next();
            if (controllerPren.getPrenotazione()==null){

                LocalDate dataNonDisponibile=(this.dataInizio.plusDays(j));
                this.LocalDateNonDisponibili.add(dataNonDisponibile);
                this.persist=false;
                //non verra effetuata alcuna persistenza dato che esiste una data non disponibile
                ++j;
            }
            if (persist){
                i=this.gestorePrenotazioni.iterator();

                while(i.hasNext()){
                    GestorePrenotazioneConferenza k = i.next();

                    k.persisti(prof.getUsernameProf());

                    //manda in persistenza le singole prenotazioni su piu giorni

                }


            }

        }

    }

    private List<AulaConferenza> ricercaAulaConCaratteristiche(BeanCaratteristicheAula beanCarAula, BeanSpecificheConferenza beanSpeConf) {
        return  AulaConferenzaDao.getIstance().queryConCaratteristiche(beanCarAula);
    }

    // controlla se l'aula con id specificato esiste, 
    // se non esiste ritorna null
    private AulaConferenza ricercaAulaConId(BeanIdAula beanIdAula) {
    //System.out.println(beanIdAula);
        return  AulaConferenzaDao.getIstance().queryConId(beanIdAula);
    }
}
