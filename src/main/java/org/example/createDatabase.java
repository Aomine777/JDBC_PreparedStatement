package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class createDatabase {
    public static void createTables(){
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement pstm = null;
        try{
            String createTypeLevel = "CREATE TYPE Level AS ENUM ('Trainee', 'Junior', 'Middle', 'Senior');";
            pstm = connection.prepareStatement(createTypeLevel);
            pstm.executeUpdate();

            String createTableWorker = "CREATE TABLE worker(" +
                    "ID SERIAL PRIMARY KEY," +
                    "NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >=2)," +
                    "BIRTHDAY DATE CHECK (EXTRACT(YEAR FROM BIRTHDAY) > 1900)," +
                    "LEVEL level NOT NULL," +
                    "SALARY INT CHECK (SALARY >= 100 AND SALARY <= 100000" +
                    ");";
            pstm = connection.prepareStatement(createTableWorker);
            pstm.executeUpdate();

            String createTableClient = "CREATE TABLE client(" +
                    "ID SERIAL PRIMARY KEY," +
                    "NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2)" +
                    ");";
            pstm = connection.prepareStatement(createTableClient);
            pstm.executeUpdate();

            String createTableProjectWorker = "create table project_worker(" +
                    "PROJECT_ID INT references project(ID)," +
                    "WORKER_ID INT references worker(ID)," +
                    "primary key (PROJECT_ID, WORKER_ID" +
                    ");";
            pstm = connection.prepareStatement(createTableProjectWorker);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstm != null){
                try{
                    pstm.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
