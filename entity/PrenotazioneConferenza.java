package entity;


import java.time.LocalDate;

public class PrenotazioneConferenza extends Prenotazione {

    private String titoloConferenza;

    public PrenotazioneConferenza(int idAula, LocalDate data, fasciaOraria fasciaOraria, String titoloConferenza) {
        super(idAula, data,  fasciaOraria);
        this.setTitoloConferenza(titoloConferenza);
    }

    public String getTitoloConferenza() {
        return titoloConferenza;
    }

    public void setTitoloConferenza(String titoloConferenza) {
        this.titoloConferenza = titoloConferenza;
    }



   /* public PrenotazioneConferenza(int idAula, LocalDate data, fasciaOraria fascia, String titoloConferenza) {
    super (idAula,data, fascia);
    this.titoloConferenza=titoloConferenza;
    } */
}


