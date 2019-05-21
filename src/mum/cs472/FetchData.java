/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yu Zhou
 */
public class FetchData {
    String searchWord;

    public FetchData(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
    
    public ArrayList<Word> getAllWords(){
        Connection mysqlConn = null;
        Statement stmt = null;
        ResultSet rset = null;
        ArrayList<Word> wordList = new ArrayList<Word>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            mysqlConn = DbConnection.getConnection();
            stmt = mysqlConn.createStatement();

            String sqlStr = "select * from entries where word = "
                    + "'" + searchWord + "'"
                    + " order by wordtype;";

            //out.println("<p>You query is: " + sqlStr + "</p>"); // Echo for debugging
            rset = stmt.executeQuery(sqlStr);
            
            int number = 1;
            while(rset.next()) {
                String type = number + "(" + rset.getString("wordtype") + ") ::";
                String definition = rset.getString("definition");
                Word word = new Word(searchWord, type, definition);
                wordList.add(word);
                number++;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dictServlet.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(dictServlet.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            try {
                if(rset != null){
                    rset.close();
                    rset = null;
                }
                if(stmt != null){
                    stmt.close();
                    stmt = null;
                }
            } catch (SQLException ex){
                Logger.getLogger(dictServlet.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace(); 
            }
        }
        return wordList;
    } 
}
