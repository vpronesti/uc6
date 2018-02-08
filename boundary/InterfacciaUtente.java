package boundary;

import controller.GestoreLogin;

public class InterfacciaUtente {
    String username;
    String paswoord;
    String type;
    boolean isLog;

    public InterfacciaUtente(String username, String paswoord, String type) {
        this.username = username;
        this.paswoord = paswoord;
        this.type = type;
    }

    public boolean loggin () {
        if(GestoreLogin.getIstance().login(this.username,this.paswoord,this.type)) {
            this.isLog=true;
            return true;
        }
        else
            return false;
    }
    public void loggout(){
        this.isLog=false;
        this.username=null;
        this.paswoord=null;
    }
    public InterfacciaProfessore getInterfacciaProf(){
        //todo controllo che non dovrebbe essere necessario
         /*if (this.isLog==false){
            throw Exception;
        }*/
        return new InterfacciaProfessore(this.username);
    }
}