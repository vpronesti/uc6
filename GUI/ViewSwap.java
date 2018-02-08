package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class ViewSwap {
    //RESPONSABILITY OF SWAP SCENE IN THE ONLY STAGE
    //singleton instance because getClass cannot be used from static context
    protected static String LOGGIN="/GUI/loggin.fxml";
    protected static String INPUTCONFERENZA= "/GUI/SpecificaCarAulaConf.fxml";
    protected static String RISPOSTA= "/GUI/Risposta.fxml";
    protected static String MENU="/GUI/Menu.fxml";
    protected static String INPUTID = "/GUI/SpecificaIdAulaConf.fxml";
    protected static ViewSwap viewSwap;

    public static ViewSwap getIstance() {
        //SINGLETON INSTANCE :)

        if (ViewSwap.viewSwap==null) {
            ViewSwap.viewSwap = new ViewSwap();
            return ViewSwap.viewSwap;
        }
        else
        {
         return ViewSwap.viewSwap;
        }
    }


    public void swap (  ActionEvent event, String url) throws  Exception{
        //swap scene inside stage to newly created stage taken from url
        //NB -> url have to be in static attribute of swapClass :)
        //event needed to access to main Stage
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //stage -> main windows of GUI
        Parent newRoot = FXMLLoader.load(getClass().getResource(url));
        stage.setScene(new Scene(newRoot));
        //change scene in the stage=====> other controller loaded
        //DONE

    }
    public void swap (  ActionEvent event,String url, Initializable controller) throws  Exception{
        //version to swapWithState
        //todo nB CAST PER CONTROLLER in caller
        //swap scene inside stage to newly created stage taken from url
        //set fxml controller to instantiated controller controller (casted from caller)
        //to pass data among controllers instatiates...
        //NB -> url have to be in static attribute of swapClass :)
        //event needed to access to main Stage
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //stage -> main windows of GUI
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        loader.setController(controller); //set controller to fxml from instance of controller passed
        Parent newRoot = loader.load();
        //todo controller implement initialize so every  time will be callsed initialize metod
        //this belong to swap logic... :))
        controller.initialize();
        //polymporfism from interface :) xD
        stage.setScene(new Scene(newRoot));
        //change scene in the stage=====> other controller loaded
        //DONE

    }

}
