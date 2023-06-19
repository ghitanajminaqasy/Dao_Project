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
import models.DetailPemesananModel;
import models.DetailSepatuKeluarModel;
import models.DetailSepatuMasukModel;
import models.SepatuKeluarModel;
import models.SepatuMasukModel;
import models.SepatuModel;
import services.DetailSepatuKeluarService;

/**
 *
 * @author Ghita Najmi
 */
public class DetailSepatuKeluarDao implements DetailSepatuKeluarService{
    
 private Connection connection;
    public DetailSepatuKeluarDao(){
        connection = Connections.getConnection();
    }

    @Override
    public void addData(DetailSepatuKeluarModel dskModel) {
    PreparedStatement st = null;
        String sql = "INSERT INTO detail_sepatu_keluar (no_keluar, id_sepatu, jml_keluar, subtotal_keluar) VALUES (?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
             st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DetailSepatuMasukModel.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(DetailSepatuMasukModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }

    @Override
    public void sumTotal(DetailSepatuKeluarModel dskModel) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT sum(subtotal_keluar) FROM TempSepatuMasuk";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
               dskModel.setSubtotalKeluar(rs.getDouble(1));
            }
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(DetailSepatuKeluarModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DetailSepatuKeluarModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void tempDelete(DetailSepatuKeluarModel dskModel) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "DELETE FROM TempSepatuMasuk";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(DetailSepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DetailSepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DetailSepatuKeluarModel getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    @Override
    public List<DetailSepatuKeluarModel> search(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean stockValidation(DetailSepatuKeluarModel dskModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetailSepatuKeluarModel> getData(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT Detail_sepatu_keluar.no_keluar, Detail_sepatu_keluar.id_sepatu, s.nama_sepatu, "
                + "s.harga, Detail_sepatu_keluar.jml_keluar, Detail_sepatu_keluar.subtotal_keluar, "
                + "FROM Detail_sepatu_keluar det_kel "
                + "INNER JOIN Sepatu_keluar skl ON skl.no_keluar = Detail_sepatu_keluar.no_keluar "
                + "INNER JOIN Sepatu s ON s.id_sepatu = Detail_sepatu_keluar.id_sepatu "
                + "WHERE Detail_sepatu_keluar.no_keluar='"+id+"' ORDER BY no_keluar ASC";
        
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
                SepatuKeluarModel skModel = new SepatuKeluarModel();
                DetailSepatuKeluarModel dskModel = new DetailSepatuKeluarModel();
                SepatuModel sModel = new SepatuModel();
                
                skModel.setNoKeluar(String.valueOf(rs.getString("det_psn.no_pemesanan")));
                dskModel.setNoKeluar(skModel);
                
                sModel.setIdSepatu(rs.getString("id_sepatu"));
                sModel.setNamaSepatu(rs.getString("nama_sepatu"));
                sModel.setHarga(rs.getDouble("harga"));
                dskModel.setJmlKeluar(rs.getInt("jml_pemesanan"));
                dskModel.setSubtotalKeluar(rs.getDouble("subtotal_pemesanan"));
                
                dskModel.setIdSepatu(sModel);
                
                list.add(dskModel);
            }
            return list;
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(DetailSepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DetailSepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DetailSepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
    

