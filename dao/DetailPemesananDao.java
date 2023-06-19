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
import models.DetailPemesananModel;
import models.PemesananModel;
import models.SepatuModel;
import models.TempPemesananModel;
import services.DetailPemesananService;

/**
 *
 * @author Lenovo
 */
public class DetailPemesananDao implements DetailPemesananService{
       
    private final Connection connection;
    
    public DetailPemesananDao(){
        connection = Connections.getConnection();  
    }
    
    
    @Override
    public void addData(DetailPemesananModel dpModel) {
        PreparedStatement st = null;
        String sql = "INSERT INTO detail_sepatu_pemesanan(no_pemesanan, id_sepatu, jml_pemesanan, subtotal_pemesanan, status) SELECT '"+dpModel.getNoPemesanan()+"',id_sepatu, jml_pemesanan, subtotal_pemesanan FROM Temp_Pemesanan";
        
        try{
            st = connection.prepareStatement(sql);
             st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DetailPemesananDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(DetailPemesananDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void sumTotal(DetailPemesananModel dpModel) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT sum(subtotal_Pemesanan) FROM Temp_Pemesanan";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
               dpModel.setSubtotalPemesanan(rs.getDouble(1));
            }
           
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(DetailPemesananDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DetailPemesananDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void tempDelete(DetailPemesananModel dpModel) {
        PreparedStatement st = null;
        String sql = "DELETE FROM TempSepatuMasuk";
        try{
            st = connection.prepareStatement(sql);
            st.executeQuery();
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(DetailPemesananDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DetailPemesananDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DetailPemesananModel getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetailPemesananModel> getData(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT Detail_Pemesanan.no_pemesanan, Detail_Pemesanan.id_sepatu, brg.nama_sepatu, "
                + "brg.harga, Detail_Pemesanan.jml_pemesanan, Detail_Pemesanan.subtotal_pemesanan, Detail_Pemesanan.status "
                + "FROM detail_pemesanan det_psn "
                + "INNER JOIN pemesanan psn ON psn.no_pemesanan = Detail_Pemesanan.no_pemesanan "
                + "INNER JOIN sepatu brg ON brg.id_sepatu = Detail_Pemesanan.id_sepatu "
                + "WHERE Detail_Pemesanan.no_pemesanan='"+id+"' ORDER BY no_pemesanan ASC";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
                PemesananModel psnModel = new PemesananModel();
                DetailPemesananModel dtlModel = new DetailPemesananModel();
                SepatuModel sptModel = new SepatuModel();
                
                psnModel.setNoPemesanan(String.valueOf(rs.getString("det_psn.no_pemesanan")));
                dtlModel.setNoPemesanan(psnModel);
                
                sptModel.setIdSepatu(rs.getString("id_sepatu"));
                sptModel.setNamaSepatu(rs.getString("nama_sepatu"));
                sptModel.setHarga(rs.getDouble("harga"));
                dtlModel.setJmlPemesanan(rs.getInt("jml_pemesanan"));
                dtlModel.setSubtotalPemesanan(rs.getDouble("subtotal_pemesanan"));
                dtlModel.setStatus(rs.getString("status"));
                
                dtlModel.setIdSepatu(sptModel);
                
                list.add(dtlModel);
            }
            return list;
        }
        catch(SQLException ex){
            Logger.getLogger(DetailPemesananDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(DetailPemesananDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DetailPemesananModel> search(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
