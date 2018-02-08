package GUI;

import bean.BeanCaratteristicheAula;
import bean.BeanIdAula;
import bean.BeanRisposta;
import bean.BeanSpecificheConferenza;
import boundary.InterfacciaProfessore;
import javafx.concurrent.Task;

public class BoundaryTaskWrap extends Task<BeanRisposta> {
    private InterfacciaProfessore boundary;
    private BeanCaratteristicheAula beanCar;
    private BeanSpecificheConferenza beanSpecAula;
    private BeanIdAula beanIdAula;


    public BoundaryTaskWrap (InterfacciaProfessore boundary ,BeanSpecificheConferenza beanSpec, BeanCaratteristicheAula beanCar){
        this.boundary=boundary;
        this.beanSpecAula=beanSpec;
        this.beanCar=beanCar;
    }
    
    public BoundaryTaskWrap (InterfacciaProfessore boundary ,BeanSpecificheConferenza beanSpec, BeanIdAula beanIdAula){
        this.boundary=boundary;
        this.beanSpecAula=beanSpec;
        this.beanIdAula = beanIdAula;
    }

    @Override
    protected BeanRisposta call() {
        return boundary.prenotazioneConCaratteristiche(beanSpecAula,beanCar);

    }
}
