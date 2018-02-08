package entity;

public class AulaConferenza extends Aula {


    public AulaConferenza(int id, int numPosti, boolean proiettore, boolean microfono, boolean lavagna, boolean lavagnaInterattiva, boolean preseCorrente, boolean ethernet) {
        super(id, numPosti, proiettore, microfono, lavagna, lavagnaInterattiva, preseCorrente, ethernet);
    }
    public AulaConferenza() {
        super();
    }

    public AulaConferenza(int id) {
        super(id);
    }  
}
