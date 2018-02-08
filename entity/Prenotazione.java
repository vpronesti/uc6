package entity;


import java.time.LocalDate;

public abstract class Prenotazione {
    private int idAula;
    private LocalDate data;
    private String idProf;
    private fasciaOraria fasciaOraria;
    //fascia oraria 0<-->3
  
    public Prenotazione(int idAula, LocalDate data, String idProf, fasciaOraria fascia) {
        this.setIdAula(idAula);
        this.setData(data);
        this.setIdProf(idProf);
        this.setFasciaOraria(fascia);
    }

    public Prenotazione(int idAula, LocalDate data, fasciaOraria fasciaOraria) {
        this.idAula = idAula;
        this.data = data;
        this.fasciaOraria = fasciaOraria;
        this.idProf = null;
    }


    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getIdProf() {
        return idProf;
    }

    public void setIdProf(String idProf) {
        this.idProf = idProf;
    }

    public entity.fasciaOraria getFasciaOraria() {
        return fasciaOraria;
    }

    public void setFasciaOraria(entity.fasciaOraria fasciaOraria) {
        this.fasciaOraria = fasciaOraria;
    }

   /* public Prenotazione(int idAula, LocalDate data,  fasciaOraria fascia) {
        //costruttore per Oggetti prenotazione senza id (non ancora in strato di persistenza
        this.idAula = idAula;
        this.data = data;
        this.idPrenotazione = -1;
        this.fasciaOraria = fascia;
    } */

}

