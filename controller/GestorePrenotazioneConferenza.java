package controller;

import Dao.AulaConferenzaDao;

import Dao.PrenotazioneDao;
import bean.*;
import entity.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorePrenotazioneConferenza implements Runnable{
    private PrenotazioneConferenza prenotazione;
    private List<AulaConferenza> aule; //manterra riferimenti a aule con caratteristiche (NON TEMPORALI ) o cambiato in conferenza perchè tanto il caso d'uso parla di conferenze
  
    public GestorePrenotazioneConferenza() {

        this.aule = new ArrayList<AulaConferenza>();
    }

    public void persisti(String usernameProf){
        //fa persistere in db l'ogg prenotaizone
        PrenotazioneDao prenotazioneDao=new PrenotazioneDao();

        this.getPrenotazione().setIdProf(usernameProf);
        prenotazioneDao.persistiPrenotazioneConferenza(this.getPrenotazione());
    }
    /*
    public Boolean ricercaAulaConCaratteristiche(BeanCaratteristicheAula beanCarAula, BeanSpecificheConferenza beanSpeConf) {
        aule = AulaConferenzaDao.getIstance().queryConCaratteristiche(beanCarAula);
        if (!aule.isEmpty()) { //se caratteristiche richiesta hanno prodotto risultati parziali
             this.verificaAulaConCaratteristiche(beanSpeConf);
            //è possibile gestire prenotazioni su piu giorni e piu aule iterando per ogni giorno
            //verificaAUleconCaratteristiche (ottenendo
            return true;
        }
        return false;

    }
    //OLD VERSION... le aule sono le stesse per tutte le richieste su piu giorni
    //vengono ricavate dal GestoreRichieste
    */

    //private boolean verificaDisponibilitaId() {

    public void setAule(List<AulaConferenza> aule){
        this.aule=aule;
    }

    protected void verificaAulaConCaratteristiche(BeanSpecificheConferenza beanSpecificheConferenza) {
        Iterator<AulaConferenza> I = this.aule.iterator();
        while(I.hasNext()){
            Aula aula=I.next();
            PrenotazioneConferenza prenotazione;
            Factory factory=new Factory();
            prenotazione = (PrenotazioneConferenza)factory.createPrenotazioni(1,aula.getId(),beanSpecificheConferenza);
            // controller per PrenotazioneAulaConferenza
            //ma funzionale anche con prenotazione didattica vista la unica differenza di un attributo
            PrenotazioneDao prenotazioneDao=new PrenotazioneDao();
            if(prenotazioneDao.queryEsistenzaPrenotazioneConferenza(prenotazione))
            {
                this.setPrenotazione(prenotazione);
                //prenotazione ok-->setta come attributo
                break;
            }
        }
        return;

    }

    public void verificaDisponibilitaId(BeanSpecificheConferenza beanInfo) {
        if (aule.get(0) == null) {
            System.out.println("non si e' specificato per quale aula "
                    + "controllare la disponibilita'");
            return;
        } else {
            Factory factory = new Factory();
            PrenotazioneConferenza prenotazione = (PrenotazioneConferenza)factory.
                    createPrenotazioni(1, aule.get(0).getId(), beanInfo);
            PrenotazioneDao prenotazioneDao = new PrenotazioneDao();
            if (prenotazioneDao.queryEsistenzaPrenotazioneConferenza(prenotazione)) {
                this.setPrenotazione(prenotazione);
            }
            return;
        }
    }

    public PrenotazioneConferenza getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(PrenotazioneConferenza prenotazione) {
        this.prenotazione = prenotazione;
    }

    public void run() {} //lanciato per ogni giorno spezzato.
}
