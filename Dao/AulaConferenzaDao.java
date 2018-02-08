package Dao;


import bean.BeanCaratteristicheAula;
import bean.BeanIdAula;
import entity.AulaConferenza;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class AulaConferenzaDao {
    private static AulaConferenzaDao aulaDao;
    private static String PASS = "admin";
    private static String USER = "postgres";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/University";

    //attributi per jdbc
    public static synchronized AulaConferenzaDao getIstance() {
        if (aulaDao == null)
            return new AulaConferenzaDao();
        else
            return aulaDao;
        //end singleton pattern :)

        //classe singleto
    }

    public List<AulaConferenza> queryConCaratteristiche(BeanCaratteristicheAula beanCarAula) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        List<AulaConferenza> aule = new ArrayList<>();

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM aulaconferenza WHERE numero_posti >= " +beanCarAula.getNumPosti();
            if(beanCarAula.isLavagna())
                sql = sql + " AND lavagna =" + beanCarAula.isLavagna();
            if(beanCarAula.isMicrofono())
                sql = sql + " AND microfono =" +beanCarAula.isMicrofono();
            if(beanCarAula.isProiettore())
                sql = sql + " AND proiettore =" +beanCarAula.isProiettore();
            if(beanCarAula.isEthernet())
                sql = sql + " AND ethernet =" +beanCarAula.isEthernet();
            if(beanCarAula.isLavagnaInterattiva())
                sql = sql + " AND lavagnainterattiva =" +beanCarAula.isLavagnaInterattiva();
            if(beanCarAula.isPreseCorrente())
                sql = sql + " AND presecorrente =" +beanCarAula.isPreseCorrente();
            //se sono vere le inserisco nell'interogazzione
            //sql = sql +";";
            System.out.println(sql);
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while(result.next()) {
                AulaConferenza a = new AulaConferenza();
                a.setId(result.getInt("id"));
                a.setNumPosti(result.getInt("numero_posti"));
                a.setEthernet(result.getBoolean("ethernet"));
                a.setLavagna(result.getBoolean("lavagna"));
                a.setLavagnaInterattiva(result.getBoolean("lavagnaInterattiva"));
                a.setMicrofono(result.getBoolean("microfono"));
                a.setPreseCorrente(result.getBoolean("preseCorrente"));
                a.setProiettore(result.getBoolean("proiettore"));
                aule.add(a);
            }
            System.out.println(aule.size());
            // STEP 6: Clean-up dell'ambiente
            result.close();
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
        return aule;

    }

    public AulaConferenza queryConId(BeanIdAula beanIdAula) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        AulaConferenza a = new AulaConferenza();
        int id = beanIdAula.getIdAula();

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM aulaconferenza WHERE id = " + id;
            ResultSet result= stmt.executeQuery(sql);


            if(result.next()) {
System.out.println("id query = " + result.getInt("id"));
                a.setId(result.getInt("id"));
                a.setNumPosti(result.getInt("numero_posti"));
                a.setEthernet(result.getBoolean("ethernet"));
                a.setLavagna(result.getBoolean("lavagna"));
                a.setLavagnaInterattiva(result.getBoolean("lavagnaInterattiva"));
                a.setMicrofono(result.getBoolean("microfono"));
                a.setPreseCorrente(result.getBoolean("preseCorrente"));
                a.setProiettore(result.getBoolean("proiettore"));
System.out.println("querying aula> " + a);
            }
            else {
                a = null;
            }
            result.close();
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
        return a;

    }
}