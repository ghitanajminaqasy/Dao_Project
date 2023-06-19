/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Main.MenuUtama;
import config.Connections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.LoginModel;
import services.LoginService;
import View.FormLogin;
import java.sql.SQLException;

/**
 *
 * @author Aby
 */
public class LoginDao implements LoginService{
    
    private Connection connection;
    
    public LoginDao(){
        connection = Connections.getConnection();
    }

    @Override
    public void loginProcess(LoginModel lModel) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String id = null;
        String nama = null;
        String level2 = null;
        String sql = "SELECT * FROM pengguna WHERE ((id_pengguna='"+lModel.getIdUser()+"'"
                + "OR username='"+lModel.getUsername()+"')"
//                + "AND password='"+Encrypt.getmd5java(lModel.getPassUser())+"')"
                + "AND password='"+lModel.getPassUser()+"')";
        
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()){
                id = rs.getString("id_pengguna");
                nama = rs.getString("nama_pengguna");
                level2 = rs.getString("level");
                
                MenuUtama menu = new MenuUtama(id, nama, level2);
                menu.setVisible(true);
                menu.revalidate();
                
                FormLogin lg  = new FormLogin();
                lg.close = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Username dan Password salah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                FormLogin lg = new FormLogin();
                lg.close = false;
            }
    }
        catch(SQLException ex){
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
