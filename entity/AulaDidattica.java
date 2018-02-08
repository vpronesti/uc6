package entity;

public class AulaDidattica extends Aula {
    private String typeAulaDIdattica;

    public AulaDidattica(int id, int numPosti, boolean proiettore, boolean microfono, boolean lavagna, boolean lavagnaInterattiva, boolean preseCorrente, boolean ethernet, String typeAulaDIdattica) {
        super(id, numPosti, proiettore, microfono, lavagna, lavagnaInterattiva, preseCorrente, ethernet);
        this.typeAulaDIdattica = typeAulaDIdattica;
    }

    public AulaDidattica(int id, String typeAulaDIdattica) {
        super(id);
        this.typeAulaDIdattica = typeAulaDIdattica;
    }
}
