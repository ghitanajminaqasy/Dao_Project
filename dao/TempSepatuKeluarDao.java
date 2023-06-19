/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Connections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.SepatuMasukModel;
import models.TempSepatuKeluarModel;
import services.TempSepatuKeluarService;

/**
 *
 * @author Ghita Najmi
 */
public class TempSepatuKeluarDao implements TempSepatuKeluarService{
     private Connection connection;
     public TempSepatuKeluarDao(){
        connection = Connections.getConnection();
     }
    @Override
    public void addData(TempSepatuKeluarModel tmp_skModel) {
    
    }

    @Override
    public void updateData(TempSepatuKeluarModel tmp_skModel) {
        PreparedStatement st = null;
        String sql = "INSERT INTO Temp_Sepatu_Keluar (id_sepatu, nama_sepatu, harga, jml_masuk, subtotal_masuk) VALUES (?,?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
          
//            st.setString(1, tmp_skModel.getsModel().getIdSepatu());
//            st.setString(2, tmp_skModel.getsModel().getNamaSepatu());
//            st.setDouble(3, tmp_skModel.getsModel().getHarga());
//            st.setInt(4, tmp_skModel.getdbmModel().getJumlahMasuk());
//            st.setDouble(5, tmp_skModel.getdbmModel().getSubtotalMasuk());
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(TempSepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(TempSepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void deleteData(TempSepatuKeluarModel tmp_skModel) {
        PreparedStatement st = null;
        String sql = "DELETE FROM Temp_Sepatu_Keluar WHERE id_sepatu=? AND nama_sepatu=? AND harga=? AND jml_masuk=? AND subtotal_masuk=?";
        try{
            st = connection.prepareStatement(sql);
          
//            st.setString(1, tmp_skModel.getsModel().getIdSepatu());
//            st.setString(2, tmp_skModel.getsModel().getNamaSepatu());
//            st.setDouble(3, tmp_skModel.getsModel().getHarga());
//            st.setInt(4, tmp_skModel.getdbmModel().getJumlahMasuk());
//            st.setDouble(5, tmp_skModel.getdbmModel().getSubtotalMasuk());
            st.executeUpdate();
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(TempSepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(TempSepatuKeluarDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public TempSepatuKeluarModel getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TempSepatuKeluarModel> getData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
