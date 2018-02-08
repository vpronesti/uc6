package bean;
import entity.*;

import java.time.LocalDate;
import java.util.Calendar;


public class BeanSpecificheConferenza implements Cloneable {
    private String titoloConferenza;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    //private enum FasciaOraria fasciaoraria;
    private fasciaOraria fasciaOraria; //TODO scrivere come enum, 4 fascie orarie con interi secondo ordine
    private String usernameProf;


    public BeanSpecificheConferenza(String titoloConferenza, LocalDate dataInizio, LocalDate dataFine, fasciaOraria fasciaOraria, String usernameProf) throws Exception {
        this.setTitoloConferenza(titoloConferenza);
        this.setDataInizio(dataInizio);
        this.setDataFine(dataFine);
        this.setFasciaOraria(fasciaOraria);
        this.setUsernameProf(usernameProf);
        //settati attributi
    }

    public String getTitoloConferenza() {
        return titoloConferenza;
    }

    public void setTitoloConferenza(String titoloConferenza) {
        this.titoloConferenza = titoloConferenza;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public fasciaOraria getFasciaOraria() {
        return fasciaOraria;
    }

    public void setFasciaOraria(fasciaOraria fasciaOraria) throws Exception {
        this.fasciaOraria = fasciaOraria;
    }

    public BeanSpecificheConferenza getClone() throws Exception {
        return (BeanSpecificheConferenza) (this.clone());
        //TODO work?
    }

    public String getUsernameProf() {
        return usernameProf;
    }

    public void setUsernameProf(String usernameProf) {
        this.usernameProf = usernameProf;
    }

    @Override
    public String toString() {
        return "BeanSpecificheConferenza{" +
                "titoloConferenza='" + titoloConferenza + '\'' +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", fasciaOraria=" + fasciaOraria +
                ", usernameProf='" + usernameProf + '\'' +
                '}';
    }
}




