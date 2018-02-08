package entity;

import bean.BeanSpecificheConferenza;

public class Factory {


    public Prenotazione createPrenotazioni(int tipo, int idAula, BeanSpecificheConferenza beanConf){
        //0->DIDATTICA 1-> CONFERENZA
        Prenotazione p = null;
       // if (tipo==0)
            //p=this.createPrenotazioniDidattica();
        if (tipo==1){
            p=this.createPrenotazioneConferenza(idAula,beanConf);

        }
        //else
        //    throw Exception;
            //tipo errato
        return p;
    }
    /*public PrenotazioneDidattica createPrenotazioniDidattica(){
        // scrivere prenotazione didattica
    }*/
    public PrenotazioneConferenza createPrenotazioneConferenza(int idAula,BeanSpecificheConferenza bean){
        return new PrenotazioneConferenza(idAula,bean.getDataInizio(), bean.getFasciaOraria(), bean.getTitoloConferenza());

    }
}
