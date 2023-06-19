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
import javax.swing.JOptionPane;
import models.DetailPemesananModel;
import models.DetailSepatuMasukModel;
import models.DistributorModel;
import models.MerkModel;
import models.PemesananModel;
import models.PenggunaModel;
import models.SepatuMasukModel;
import services.SepatuMasukService;

/**
 *
 * @author Ghita Najmi
 */
public class SepatuMasukDao implements SepatuMasukService{
     private Connection connection;
     public SepatuMasukDao(){
        connection = Connections.getConnection();
    }

    @Override
    public void addData(SepatuMasukModel smModel) {
        PreparedStatement st = null;
        String sql = "INSERT INTO Sepatu_masuk(no_masuk, tggl_masuk, total_masuk , id_pengguna, id_distributor) VALUES (?,?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, smModel.getNoMasuk());
            st.setString(2, smModel.getTanggalMasuk());
            st.setDouble(3,  smModel.getTotalMasuk());
            st.setString(4, smModel.getIdPengguna().getIdPengguna());
            st.setString(5, smModel.getIdDistributor().getIdDistributor());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(SepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(SepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

     @Override
    public void deleteData(SepatuMasukModel smModel) {
        PreparedStatement st = null;
        String sql = "DELETE FROM Sepatu_masuk WHERE no_masuk=?";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, smModel.getNoMasuk());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(SepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(SepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }  

    @Override
    public void updateStatus(String id_sepatu) {
        PreparedStatement st = null;
        String sql = "UPDATE Detail_Pemesanan SET status='Barang Telah Datang', WHERE id_sepatu="+id_sepatu+"'";
        try{
            st = connection.prepareStatement(sql);
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Gagal Perbarui Status Barang");
            java.util.logging.Logger.getLogger(SepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public SepatuMasukModel getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SepatuMasukModel> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM Sepatu_masuk";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
                SepatuMasukModel smModel = new SepatuMasukModel();
                PenggunaModel pgModel = new PenggunaModel();
                DistributorModel disModel = new DistributorModel();
                
                smModel.setNoMasuk(rs.getString("no_masuk"));
                smModel.setTanggalMasuk(rs.getString("tggl_masuk"));
                smModel.setTotalMasuk(rs.getDouble("total_masuk"));
                pgModel.setIdPengguna(rs.getString("id_pengguna"));
                disModel.setIdDistributor(rs.getString("id_distributor"));
                
                smModel.setIdPengguna(pgModel);
                list.add(smModel);
            }
            return list;
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(SepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(SepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(SepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<SepatuMasukModel> search(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM Sepatu_masuk WHERE no_masuk LIKE '%"+id+"%' OR tggl_masuk LIKE '%"+id+"%' OR id_distributor LIKE '%"+id+"%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
               PemesananModel psModel = new PemesananModel();
               DistributorModel disModel = new DistributorModel();
               PenggunaModel pgModel = new PenggunaModel();
                 
                psModel.setNoPemesanan(rs.getString("no_masuk"));
                psModel.setTanggalPemesanan(rs.getString("tggl_masuk"));
                psModel.setTotalPemesanan(rs.getDouble("total_masuk"));
                disModel.setIdDistributor(rs.getString("id_distributor"));
                pgModel.setIdPengguna(rs.getString("id_pengguna"));
                
                psModel.setIdDistributor(disModel);
                psModel.setIdPengguna(pgModel);
                list.add(psModel);
            }
            return list;
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(SepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(SepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(SepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public String number() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String order = null;
        
        Date now = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat noFormat = new SimpleDateFormat("yyMM");
        String tgl = tanggal.format(now);
        String no = noFormat.format(now);
        
        String sql = "SELECT RIGHT (no_masuk, 3) AS Nomor "
                + "FROM Sepatu_masuk "
                + "WHERE no_masuk LIKE 'SM"+ no +"%' "
                + "ORDER BY no_masuk DESC "
                + "LIMIT 1";
        
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()){
                int number = Integer.parseInt(rs.getString("Nomor"));
                number ++;
                order = "SM" + no + String.format("%03d", number);
            }
            else {
                order = "SM" + no + "001";
            }
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(DistributorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DistributorModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return order;
    }
}
