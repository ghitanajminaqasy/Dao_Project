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
import models.DistributorModel;
import models.PemesananModel;
import models.PenggunaModel;
import services.PemesananService;

/**
 *
 * @author Lenovo
 */

public class PemesananDao implements PemesananService {
    
    private Connection connection; 
    
    public PemesananDao(){
        connection = Connections.getConnection();  
    }

    @Override
    public void addData(PemesananModel pModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PemesananModel getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PemesananModel> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM Pemesanan";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
                PemesananModel pModel = new PemesananModel();
                DistributorModel disModel = new DistributorModel();
                PenggunaModel pngModel = new PenggunaModel();
                
                pModel.setNoPemesanan(rs.getString("no_pemesanan"));
                pModel.setTanggalPemesanan(rs.getString("tggl_pemesanan"));
                pModel.setTotalPemesanan(rs.getLong("total_pemesanan"));
                disModel.setIdDistributor(rs.getString("id_distributor"));
                pngModel.setIdPengguna(rs.getString("id_pengguna"));
                
                pModel.setIdDistributor(disModel);
                pModel.setIdPengguna(pngModel);
               
                list.add(pModel);
            }
            return list;
        }
        catch(SQLException ex){
            Logger.getLogger(PemesananDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(PemesananDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(PemesananDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    

    @Override
    public List<PemesananModel> search(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String number() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String order = null;
        
        Date now = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat noFormat = new SimpleDateFormat("yyMMdd");
        String tgl = tanggal.format(now);
        String no = noFormat.format(now);
        
        String sql = "SELECT RIGHT (no_pemesanan, 3) AS Nomor "
                + "FROM pemesanan "
                + "WHERE no_pemesanan LIKE 'PS"+ no +"%' "
                + "ORDER BY no_pemesanan DESC "
                + "LIMIT 1";
        
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()){
                int number = Integer.parseInt(rs.getString("Nomor"));
                number ++;
                order = "PS" + no + String.format("%03d", number);
            }
            else {
                order = "PS" + no + "001";
            }
        }
        catch(SQLException ex){
            Logger.getLogger(PemesananModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(PemesananModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return order;
    }

    @Override
    public void updateData(PemesananModel pModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteData(PemesananModel pModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
