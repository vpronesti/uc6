package Dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.concurrent.locks.Lock;


import entity.Prenotazione;
import entity.PrenotazioneConferenza;

public class PrenotazioneDao {
    private static PrenotazioneDao prenotazioneDao = null;
    private static String PASS = "admin";
    private static String USER = "postgres";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/University";
    private Lock lock; //TODO preso alla lettura lasciato dopo la persistenza


    public static synchronized PrenotazioneDao getIstance() {
        if (prenotazioneDao == null)
            return new PrenotazioneDao();
        else
            return prenotazioneDao;
        //end singleton pattern :)

        //classe singleton
    }

    /*public List<Aula> verificaAule(BeanSpecificheConferenza beanSpeConf) {
        List<Integer> listaAule = new ArrayList<Integer>;
        //jdbc query
        //NB SIMULAZIONE
        for (int i = 3; i < 5; i++) {
            listaAule.add(i);

        }
        return listaAule;


    }
    //obsoleto*/
    public boolean queryEsistenzaPrenotazioneConferenza(Prenotazione prenotazione){       //jdbc
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM prenotazione WHERE idaula = " + prenotazione.getIdAula() +" AND data = '" +
                    prenotazione.getData() +"' AND fasciaOraria = '"+ prenotazione.getFasciaOraria()+ "';" ;
            ResultSet resultSet = stmt.executeQuery(sql);
            if(resultSet.next()) { // se non è vuoto
               return false;
            }
            // STEP 6: Clean-up dell'ambiente
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione

            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver

            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return true;
    }

    public void persistiPrenotazioneConferenza(PrenotazioneConferenza prenotazione) {

     // STEP 1: dichiarazioni
    Statement stmt = null;
    Connection conn = null;

        try {
        // STEP 2: loading dinamico del driver mysql
        Class.forName("org.postgresql.Driver");

        // STEP 3: apertura connessione
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        String sql =  "insert into prenotazione(idaula, data, fasciaoraria, username) " +
                "values(" + prenotazione.getIdAula()+ ",'" +  prenotazione.getData() +"','" +prenotazione.getFasciaOraria() +
                "','" + prenotazione.getIdProf() +"')";
        String sql2 = "insert into prenotazioneconvegni(idaula, data, fasciaoraria, titoloevento, tipoevento) " +
                "values(" + prenotazione.getIdAula()+ ",'" +  prenotazione.getData() +"','" +prenotazione.getFasciaOraria()
                      + "','"+ prenotazione.getTitoloConferenza()+"','Conferenza')";
        stmt.executeUpdate(sql);
        stmt.executeUpdate(sql2);
            // STEP 6: Clean-up dell'ambiente

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione

            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver

            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


    }
}
