/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Connections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.MerkModel;
import services.MerkService;

/**
 *
 * @author Aby
 */
public class MerkDao implements MerkService{
    
    private Connection connection;
    
    public MerkDao(){
        connection = Connections.getConnection();
    }

    @Override
    public void addData(MerkModel merkModel) {
        PreparedStatement st = null;
        String sql = "INSERT INTO merk_sepatu (kode_merk, nama_merk) VALUES (?,?)";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, merkModel.getKodeMerk());
            st.setString(2, merkModel.getNamaMerk());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(SepatuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(SepatuDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void updateData(MerkModel merkModel) {
        PreparedStatement st = null;
        String sql = "UPDATE merk_sepatu SET nama_merk=? WHERE kode_merk=?";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, merkModel.getNamaMerk());
            st.setString(2, merkModel.getKodeMerk());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(MerkModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(MerkModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void deleteData(MerkModel merkModel) {
        PreparedStatement st = null;
        String sql = "DELETE FROM merk_sepatu WHERE kode_merk=?";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, merkModel.getKodeMerk());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(MerkModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(MerkModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public MerkModel getByid(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        MerkModel merkModel1 = null;
        String sql = "SELECT * FROM merk_sepatu WHERE kode_merk = ?";
        try {
            st = connection.prepareStatement(sql);
            MerkModel merkModel2 = new MerkModel();
            st.setString(1, merkModel2.getKodeMerk());
            st.setString(2, merkModel2.getNamaMerk());
            
            rs = st.executeQuery();
            while (rs.next()){
                merkModel1 = new MerkModel();
                
                merkModel1.setKodeMerk(rs.getString("kode_merk"));
                merkModel1.setNamaMerk(rs.getString("nama_merk"));
            }
            return merkModel1;
        }
        catch(SQLException ex){
            Logger.getLogger(MerkModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(MerkModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(MerkModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<MerkModel> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM merk_sepatu";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
                MerkModel merkModel1 = new MerkModel();
                
                merkModel1.setKodeMerk(rs.getString("kode_merk"));
                merkModel1.setNamaMerk(rs.getString("nama_merk"));
                
                list.add(merkModel1);
            }
            return list;
        }
        catch(SQLException ex){
            Logger.getLogger(MerkModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(MerkModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(MerkModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<MerkModel> search(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String number() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean stockValidation(MerkModel merkModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
