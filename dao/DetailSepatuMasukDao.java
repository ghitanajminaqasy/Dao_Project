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
import models.PemesananModel;
import models.SepatuKeluarModel;
import models.SepatuMasukModel;
import models.SepatuModel;
import services.DetailSepatuKeluarService;
import services.DetailSepatuMasukService;

/**  
 *
 * @author Ghita Najmi
 */
public class DetailSepatuMasukDao implements DetailSepatuMasukService{
 
    private Connection connection;
    
    public DetailSepatuMasukDao(){
        connection = Connections.getConnection();
    }

    @Override
    public void addData(DetailSepatuMasukModel dbmModel) {
        PreparedStatement st = null;
        String sql = "INSERT INTO detail_sepatu_masuk(no_masuk, id_sepatu, jml_masuk, subtotal_masuk) SELECT '"+dbmModel.getNoMasuk().getNoMasuk()+"',id_sepatu, jml_masuk, subtotal_masuk FROM Temp_Pemesanan";
        
        try{
            st = connection.prepareStatement(sql);
             st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DetailSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(DetailSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public void sumTotal(DetailSepatuMasukModel dbmModel) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT sum(subtotal_masuk) FROM TempSepatuMasuk";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
               dbmModel.setSubtotalMasuk(rs.getDouble(1));
            }
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(DetailSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DetailSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void tempDelete(DetailSepatuMasukModel dbmModel) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "DELETE FROM TempSepatuMasuk";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(DetailSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DetailSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DetailSepatuMasukModel getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetailSepatuMasukModel> getData(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT Detail_sepatu_masuk.no_masuk, Detail_sepatu_masuk.id_sepatu, s.nama_sepatu, "
                + "s.harga, Detail_sepatu_masuk.jml_masuk, Detail_sepatu_masuk.subtotal_masuk, "
                + "FROM Detail_sepatu_masuk det_smk "
                + "INNER JOIN Sepatu_masuk smk ON smk.no_masuk= Detail_sepatu_masuk.no_masuk "
                + "INNER JOIN Sepatu s ON s.id_sepatu = Detail_sepatu_masuk.id_sepatu "
                + "WHERE Detail_sepatu_masuk.no_masuk='"+id+"' ORDER BY no_masuk ASC";
        
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
                SepatuMasukModel smModel = new SepatuMasukModel();
                DetailSepatuMasukModel dbmModel = new DetailSepatuMasukModel();
                SepatuModel sModel = new SepatuModel();
                
                smModel.setNoMasuk(String.valueOf(rs.getString("det_psn.no_pemesanan")));
                dbmModel.setNoMasuk(smModel);
                
                sModel.setIdSepatu(rs.getString("id_sepatu"));
                sModel.setNamaSepatu(rs.getString("nama_sepatu"));
                sModel.setHarga(rs.getDouble("harga"));
                dbmModel.setJumlahMasuk(rs.getInt("jml_pemesanan"));
                dbmModel.setSubtotalMasuk(rs.getDouble("subtotal_pemesanan"));
                
                dbmModel.setIdSepatu(sModel);
                
                list.add(dbmModel);
            }
            return list;
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(DetailSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DetailSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DetailSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

   

}
    