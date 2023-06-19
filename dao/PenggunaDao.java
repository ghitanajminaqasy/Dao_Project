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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.PenggunaModel;
import services.PenggunaService;

/**
 *
 * @author Aby
 */
public class PenggunaDao implements PenggunaService{
    
    private Connection connection;
    
    public PenggunaDao(){
        connection = Connections.getConnection();
    }
    
    @Override
    public void addData(PenggunaModel penggunaModel) {
        PreparedStatement st = null;
        String sql = "INSERT INTO pengguna (id_pengguna, nama_pengguna, username, password, telp_pengguna, alamat_pengguna, level) VALUES (?,?,?,?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, penggunaModel.getIdPengguna());
            st.setString(2, penggunaModel.getNamaPengguna());
            st.setString(3, penggunaModel.getUsername());
            st.setString(4, Encrypt.getmd5java(penggunaModel.getPassword()));
//            st.setString(4, penggunaModel.getPassword());
            st.setString(5, penggunaModel.getTelpPengguna());
            st.setString(6, penggunaModel.getAlamatPengguna());
            st.setString(7, penggunaModel.getLevel());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public void updateData(PenggunaModel penggunaModel) {
        PreparedStatement st = null;
        String sql = "UPDATE pengguna SET nama_pengguna=?, username=?, telp_pengguna=?, alamat_pengguna=?, level=? WHERE id_pengguna=?";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, penggunaModel.getNamaPengguna());
            st.setString(2, penggunaModel.getUsername());
//            st.setString(3, Encrypt.getmd5java(penggunaModel.getPassword()));
            st.setString(3, penggunaModel.getTelpPengguna());
            st.setString(4, penggunaModel.getAlamatPengguna());
            st.setString(5, penggunaModel.getLevel());
            st.setString(6, penggunaModel.getIdPengguna());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void deleteData(PenggunaModel penggunaModel) {
        PreparedStatement st = null;
        String sql = "DELETE FROM pengguna WHERE id_pengguna=?";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, penggunaModel.getIdPengguna());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public PenggunaModel getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PenggunaModel> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM pengguna";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
                PenggunaModel penggunaModel = new PenggunaModel();
                
                penggunaModel.setIdPengguna(rs.getString("id_pengguna"));
                penggunaModel.setNamaPengguna(rs.getString("nama_pengguna"));
                penggunaModel.setUsername(rs.getString("username")); 
//                penggunaModel.setPassword(rs.getString("password"));
                penggunaModel.setTelpPengguna(rs.getString("telp_pengguna"));
                penggunaModel.setAlamatPengguna(rs.getString("alamat_pengguna"));
                penggunaModel.setLevel(rs.getString("level"));
                
                list.add(penggunaModel);
            }
            return list;
        }
        catch(SQLException ex){
            Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<PenggunaModel> search(String id) {
//        PreparedStatement st = null;
//        List list = new ArrayList();
//        ResultSet rs = null;
//        String sql = "SELECT FROM pengguna WHERE id_pengguna LIKE '%"+id+"%' OR nama_pengguna LIKE '%"+id+"%' "
//                + "OR username LIKE '%"+id+"%' OR telp_pengguna LIKE '%"+id+"%' ";
//        try {
//            st = connection.prepareStatement(sql);
//            rs = st.executeQuery();
//            while (rs.next()){
//                PenggunaModel penggunaModel = new PenggunaModel();
//                
//                penggunaModel.setIdPengguna(rs.getString("id_pengguna"));
//                penggunaModel.setNamaPengguna(rs.getString("nama_pengguna"));
//                penggunaModel.setUsername(rs.getString("username")); 
////                penggunaModel.setPassword(rs.getString("password"));
//                penggunaModel.setTelpPengguna(rs.getString("telp_pengguna"));
//                penggunaModel.setAlamatPengguna(rs.getString("alamat_pengguna"));
//                penggunaModel.setLevel(rs.getString("level"));
//                
//                list.add(penggunaModel);
//            }
//            return list;
//        }
//        catch(SQLException ex){
//            Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//        finally{
//            if(rs!=null){
//                try{
//                    rs.close();
//                }
//                catch(SQLException ex){
//                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String number() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String order = null;
        
        Date now = new Date();
        SimpleDateFormat noFormat = new SimpleDateFormat("yyMM");
        String no = noFormat.format(now);
        
        String sql = "SELECT RIGHT (id_pengguna, 3) AS Nomor "
                + "FROM pengguna "
                + "WHERE id_pengguna LIKE 'S" + no + "%' "
                + "ORDER BY id_pengguna DESC "
                + "LIMIT 1";

        
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()){
                int number = Integer.parseInt(rs.getString("Nomor"));
                number++;
                order = "USR" + no + String.format("%03d", number);
            }
            else {
                order = "USR" + no + "001";
            }
        }
        catch(SQLException ex){
            Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return order;
    }

}
