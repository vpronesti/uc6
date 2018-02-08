package controller;
import entity.*;
import Dao.UtenteDao;

public class GestoreLogin {
    ;
    private static GestoreLogin istance;

    public static synchronized GestoreLogin getIstance() {
        if(istance == null) {
            istance = new GestoreLogin();
        }
        return istance;
    }

    public boolean login(String username, String password, String type) {
        if(!type.equals("Professore") && !type.equals("Segreterio")) {
            System.out.println("error"); //mettere un eccezzione
            return false;
        }
        else {

            Utente utente = UtenteDao.findUtente( username, password,  type);
            if(utente == null) {
                return false;
            }
            else {
                return true;
            }

        }

    }



}
