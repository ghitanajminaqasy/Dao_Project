/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Connections;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DistributorModel;
import models.PemesananModel;
import models.PenggunaModel;
import models.SepatuKeluarModel;
import models.SepatuMasukModel;
import services.SepatuKeluarService;

/**
 *
 * @author Ghita Najmi
 */
public class SepatuKeluarDao implements SepatuKeluarService{
 private Connection connection;
     public SepatuKeluarDao(){
        connection = Connections.getConnection();
    }
    @Override
    public void addData(SepatuKeluarModel skModel) {
        PreparedStatement st = null;
        String sql = "INSERT INTO Sepatu_keluar(no_Keluar, tggl_Keluar, total_keluar , id_pengguna) VALUES (?,?,?,?)";
        try{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","SATUDUA");
                System.out.println(con);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            st = connection.prepareStatement(sql);
            
            st.setString(1, skModel.getNoKeluar());
            st.setString(2, skModel.getTanggalKeluar());
            st.setDouble(3,  skModel.getTotalKeluar());
            st.setString(4, skModel.getIdPengguna().getIdPengguna());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public SepatuKeluarModel getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SepatuKeluarModel> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM Sepatu_keluar";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
                SepatuKeluarModel skModel = new SepatuKeluarModel();
                PenggunaModel pgModel = new PenggunaModel();
                
                skModel.setNoKeluar(rs.getString("no_keluar"));
                skModel.setTanggalKeluar(rs.getString("tggl_keluar"));
                skModel.setTotalKeluar(rs.getLong("total_keluar"));
                pgModel.setIdPengguna(rs.getString("id_pengguna"));
                
                
                skModel.setIdPengguna(pgModel);
                list.add(skModel);
            }
            return list;
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<SepatuKeluarModel> search(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM Sepatu_keluar WHERE no_keluar LIKE '%"+id+"%' OR tggl_keluar LIKE '%"+id+"%' OR total_keluar '%"+id+"%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
               SepatuKeluarModel skModel = new SepatuKeluarModel();
                PenggunaModel pgModel = new PenggunaModel();
                
                skModel.setNoKeluar(rs.getString("no_keluar"));
                skModel.setTanggalKeluar(rs.getString("tggl_keluar"));
                skModel.setTotalKeluar(rs.getLong("total_keluar"));
                pgModel.setIdPengguna(rs.getString("id_pengguna"));
                
                
                skModel.setIdPengguna(pgModel);
                list.add(skModel);
            }
            return list;
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public String number() {PreparedStatement st = null;
        ResultSet rs = null;
        String order = null;
        
        Date now = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat noFormat = new SimpleDateFormat("yyMM");
        String tgl = tanggal.format(now);
        String no = noFormat.format(now);
        
        String sql = "SELECT RIGHT (no_keluar, 3) AS Nomor "
                + "FROM Sepatu_keluar "
                + "WHERE no_keluar LIKE 'SK"+ no +"%' "
                + "ORDER BY no_keluar DESC "
                + "LIMIT 1";
        
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()){
                int number = Integer.parseInt(rs.getString("Nomor"));
                number ++;
                order = "SK" + no + String.format("%03d", number);
            }
            else {
                order = "SK" + no + "001";
            }
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return order;
    }

    @Override
    public void deleteData(SepatuKeluarModel skModel) {
        PreparedStatement st = null;
        String sql = "DELETE FROM Sepatu_keluar WHERE no_keluar=?";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, skModel.getNoKeluar());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(SepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    } 
    
}
