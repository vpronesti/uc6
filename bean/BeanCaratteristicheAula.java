package bean;

public class BeanCaratteristicheAula {
    private int numPosti;
    private boolean proiettore;
    private boolean microfono;
    private boolean lavagna;
    private boolean lavagnaInterattiva;
    private boolean preseCorrente;
    private boolean ethernet;

    public BeanCaratteristicheAula(int numPosti) {
        this.numPosti = numPosti;
        this.proiettore = false;
        this.microfono = false;
        this.lavagnaInterattiva = false;
        this.preseCorrente = false;
        this.ethernet = false;
    }

    public BeanCaratteristicheAula(int numPosti, boolean proiettore) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = false;
        this.lavagna = false;
        this.lavagnaInterattiva = false;
        this.preseCorrente = false;
        this.ethernet = false;

    }

    public BeanCaratteristicheAula(int numPosti, boolean proiettore, boolean microfono) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = microfono;
        this.lavagna = false;
        this.lavagnaInterattiva = false;
        this.preseCorrente = false;
        this.ethernet = false;
    }

    public BeanCaratteristicheAula(int numPosti, boolean proiettore, boolean microfono, boolean lavagna) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = microfono;
        this.lavagna = lavagna;
        this.lavagnaInterattiva = false;
        this.preseCorrente = false;
        this.ethernet = false;
    }

    public BeanCaratteristicheAula(int numPosti, boolean proiettore, boolean microfono, boolean lavagna, boolean lavagnaInterattiva) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = microfono;
        this.lavagna = lavagna;
        this.lavagnaInterattiva = lavagnaInterattiva;
        this.preseCorrente = false;
        this.ethernet = false;
    }

    public BeanCaratteristicheAula(int numPosti, boolean proiettore, boolean microfono, boolean lavagna, boolean lavagnaInterattiva, boolean preseCorrente) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = microfono;
        this.lavagna = lavagna;
        this.lavagnaInterattiva = lavagnaInterattiva;
        this.preseCorrente = preseCorrente;
        this.ethernet = false;
    }

    public BeanCaratteristicheAula(int numPosti, boolean proiettore, boolean microfono, boolean lavagna, boolean lavagnaInterattiva,
                                   boolean preseCorrente, boolean ethernet) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = microfono;
        this.lavagna = lavagna;
        this.lavagnaInterattiva = lavagnaInterattiva;
        this.preseCorrente = preseCorrente;
        this.ethernet = ethernet;

    }

    public int getNumPosti() {
        return numPosti;
    }

    public void setNumPosti(int numPosti) {
        this.numPosti = numPosti;
    }

    public boolean isProiettore() {
        return proiettore;
    }

    public void setProiettore(boolean proiettore) {
        this.proiettore = proiettore;
    }

    public boolean isMicrofono() {
        return microfono;
    }

    public void setMicrofono(boolean microfono) {
        this.microfono = microfono;
    }

    public boolean isLavagna() {
        return lavagna;
    }

    public void setLavagna(boolean lavagna) {
        this.lavagna = lavagna;
    }

    public boolean isLavagnaInterattiva() {
        return lavagnaInterattiva;
    }

    public void setLavagnaInterattiva(boolean lavagnaInterattiva) {
        this.lavagnaInterattiva = lavagnaInterattiva;
    }

    public boolean isPreseCorrente() {
        return preseCorrente;
    }

    public void setPreseCorrente(boolean preseCorrente) {
        this.preseCorrente = preseCorrente;
    }

    public boolean isEthernet() {
        return ethernet;
    }

    public void setEthernet(boolean ethernet) {
        this.ethernet = ethernet;
    }

    @Override
    public String toString() {
        return "BeanCaratteristicheAula{" +
                "numPosti=" + numPosti +
                ", proiettore=" + proiettore +
                ", microfono=" + microfono +
                ", lavagna=" + lavagna +
                ", lavagnaInterattiva=" + lavagnaInterattiva +
                ", preseCorrente=" + preseCorrente +
                ", ethernet=" + ethernet +
                '}';
    }
}
