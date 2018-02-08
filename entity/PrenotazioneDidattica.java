package entity;

import java.time.LocalDate;

public class PrenotazioneDidattica extends  Prenotazione {
    private String nomeCorso;
    private String tipoSeduta;
    private LocalDate dataInizioAppello;

    public PrenotazioneDidattica(int idAula, LocalDate data, String idProf, entity.fasciaOraria fascia, String nomeCorso, String tipoSeduta, LocalDate dataInizioAppello) {
        super(idAula, data, idProf, fascia);
        this.setNomeCorso(nomeCorso);
        this.setTipoSeduta(tipoSeduta);

        this.dataInizioAppello = dataInizioAppello;

    }


    public String getNomeCorso() {
        return nomeCorso;
    }

    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    public String getTipoSeduta() {
        return tipoSeduta;
    }

    public void setTipoSeduta(String tipoSeduta) {
        this.tipoSeduta = tipoSeduta;
    }

    public LocalDate getDataInizioAppello() {
        return dataInizioAppello;
    }

    public void setDataInizioAppello(LocalDate dataInizioAppello) {
        this.dataInizioAppello = dataInizioAppello;
    }
}
