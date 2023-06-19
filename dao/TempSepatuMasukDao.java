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
import models.DetailSepatuMasukModel;
import models.SepatuMasukModel;
import models.SepatuModel;
import models.TempPemesananModel;
import models.TempSepatuMasukModel;
import services.TempSepatuMasukService;

/**
 *
 * @author Ghita Najmi
 */
public class TempSepatuMasukDao implements TempSepatuMasukService{

     private Connection connection; 
    
     public TempSepatuMasukDao(){
        connection = Connections.getConnection();  
    }
//    @Override
//    public void addData(SepatuMasukModel smModel) {
//        PreparedStatement st = null;
//        String sql = "INSERT INTO Temp_Sepatu_Masuk (id_sepatu, nama_sepatu, harga, jml_masuk, subtotal_masuk) VALUES (?,?,?,?,?)";
//        try{
//            st = connection.prepareStatement(sql);
//          
////            st.setString(1, tmp_smModel.getsModel().getIdSepatu());
////            st.setString(2, tmp_smModel.getsModel().getNamaSepatu());
////            st.setDouble(3, tmp_smModel.getsModel().getHarga());
////            st.setInt(4, tmp_smModel.getdbmModel().getJumlahMasuk());
////            st.setDouble(5, tmp_smModel.getdbmModel().getSubtotalMasuk());
//            st.executeUpdate();
//        }
//        catch(SQLException ex){
//            Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally{
//            if(st!=null){
//                try{
//                    st.close();
//                }
//                catch(SQLException ex){
//                    Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }
//
//    public void updateStatus(SepatuMasukModel smModel) {
//        PreparedStatement st = null;
//        String sql = "UPDATE Temp_Sepatu_Masuk SET id_sepatu=?, harga=?, jml_masuk=?, subtotal_masuk=?)WHERE id_sepatu='"+smModel.getIdPengguna().getIdPengguna()+"'";
//        try{
//            st = connection.prepareStatement(sql);
//          
//
////            st.setString(1, tmp_smModel.getsModel().getNamaSepatu());
////            st.setDouble(2, tmp_smModel.getsModel().getHarga());
////            st.setInt(3, tmp_smModel().getdbmModel().getJumlahMasuk());
////            st.setDouble(4, tmp_smModel.getdbmModel().getSubtotalMasuk());
//            st.executeUpdate();
//        }
//        catch(SQLException ex){
//            JOptionPane.showMessageDialog(null,"Perbarui Data Gagal");
//            Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally{
//            if(st!=null){
//                try{
//                    st.close();
//                }
//                catch(SQLException ex){
//                    Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void deleteData(SepatuMasukModel smModel) {
//        PreparedStatement st = null;
//        String sql = "DELETE FROM Temp_Sepatu_Masuk WHERE id_sepatu=? AND nama_sepatu=? AND harga=? AND jml_masuk=? AND subtotal_masuk=?";
//        try{
//            st = connection.prepareStatement(sql);
//          
////            st.setString(1, tmp_smModel.getsModel().getIdSepatu());
////            st.setString(2, tmp_smModel.getsModel().getNamaSepatu());
////            st.setDouble(3, tmp_smModel.getsModel().getHarga());
////            st.setInt(4, tmp_smModel().getdbmModel().getJumlahMasuk());
////            st.setDouble(5, tmp_smModel.getdbmModel().getSubtotalMasuk());
//            st.executeUpdate();
//        }
//        catch(SQLException ex){
//            java.util.logging.Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally{
//            if(st!=null){
//                try{
//                    st.close();
//                }
//                catch(SQLException ex){
//                    java.util.logging.Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }
//
//    @Override
//    public SepatuMasukModel getByid(String id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public List<SepatuMasukModel> getData() {
//        PreparedStatement st = null;
//        List list = new ArrayList();
//        ResultSet rs = null;
//        String sql = "SELECT FROM Temp_Sepatu_Masuk";
//        try{
//            st = connection.prepareStatement(sql);
//            rs = st.executeQuery();
//            while(rs.next()){
//                TempSepatuMasukModel tmp_smModel = new TempSepatuMasukModel();
//                SepatuModel sModel = new SepatuModel();
//                DetailSepatuMasukModel dbmModel = new DetailSepatuMasukModel();
//                
//                sModel.setIdSepatu(rs.getString("id_sepatu"));
//                sModel.setIdSepatu(rs.getString("nama_sepatu"));
//                sModel.setHarga(rs.getDouble("harga"));
//                
//                dbmModel.setJumlahMasuk(rs.getInt("jml_masuk"));
//                dbmModel.setSubtotalMasuk(rs.getDouble("subtotal_masuk"));
//                
//                tmp_smModel.setDbmModel(dbmModel);
//                
//                list.add(tmp_smModel);
//            }
//           return list;
//        } catch (SQLException ex){
//            java.util.logging.Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);            
//            return null;
//        }finally{
//            if(st!=null){
//            try{
//                st.close();
//            } catch (SQLException ex){
//                java.util.logging.Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//         if(rs!=null){
//            try{
//                rs.close();
//            } catch (SQLException ex){
//                java.util.logging.Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//         }
//   }
//}
//    @Override
//    public List<SepatuMasukModel> search(String id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public String number() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void updateStatus(String id_sepatu) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    

    @Override
    public void addData(TempSepatuMasukModel tmp_smModel) {
        PreparedStatement st = null;
        String sql = "INSERT INTO Temp_Sepatu_Masuk (id_sepatu, nama_sepatu, harga, jml_masuk, subtotal_masuk) VALUES (?,?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
          
            st.setString(1, tmp_smModel.getsModel().getIdSepatu());
            st.setString(2, tmp_smModel.getsModel().getNamaSepatu());
            st.setDouble(3, tmp_smModel.getsModel().getHarga());
            st.setInt(4, tmp_smModel.getDbmModel().getJumlahMasuk());
            st.setDouble(5, tmp_smModel.getDbmModel().getSubtotalMasuk());
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void updateData(TempSepatuMasukModel tmp_smModel) {
         PreparedStatement st = null;
        String sql = "UPDATE Temp_Sepatu_Masuk SET id_sepatu=?, harga=?, jml_masuk=?, subtotal_masuk=?)WHERE id_sepatu='"+tmp_smModel.getsModel().getIdSepatu()+"'";
        try{
            st = connection.prepareStatement(sql);
          

            st.setString(1, tmp_smModel.getsModel().getNamaSepatu());
            st.setDouble(2, tmp_smModel.getsModel().getHarga());
            st.setInt(3, tmp_smModel.getDbmModel().getJumlahMasuk());
            st.setDouble(4, tmp_smModel.getDbmModel().getSubtotalMasuk());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Perbarui Data Gagal");
            Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void deleteData(TempSepatuMasukModel tmp_smModel) {
       PreparedStatement st = null;
        String sql = "DELETE FROM Temp_Sepatu_Masuk WHERE id_sepatu=? AND nama_sepatu=? AND harga=? AND jml_masuk=? AND subtotal_masuk=?";
        try{
            st = connection.prepareStatement(sql);
          
            st.setString(1, tmp_smModel.getsModel().getIdSepatu());
            st.setString(2, tmp_smModel.getsModel().getNamaSepatu());
            st.setDouble(3, tmp_smModel.getsModel().getHarga());
            st.setInt(4, tmp_smModel.getDbmModel().getJumlahMasuk());
            st.setDouble(5, tmp_smModel.getDbmModel().getSubtotalMasuk());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            java.util.logging.Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    java.util.logging.Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public TempSepatuMasukModel getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TempSepatuMasukModel> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT FROM Temp_Sepatu_Masuk";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                TempSepatuMasukModel tmp_smModel = new TempSepatuMasukModel();
                SepatuModel sModel = new SepatuModel();
                DetailSepatuMasukModel dbmModel = new DetailSepatuMasukModel();
                
                sModel.setIdSepatu(rs.getString("id_sepatu"));
                sModel.setIdSepatu(rs.getString("nama_sepatu"));
                sModel.setHarga(rs.getDouble("harga"));
                
                dbmModel.setJumlahMasuk(rs.getInt("jml_masuk"));
                dbmModel.setSubtotalMasuk(rs.getDouble("subtotal_masuk"));
                
                tmp_smModel.setDbmModel(dbmModel);
                
                list.add(tmp_smModel);
            }
           return list;
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);            
            return null;
        }finally{
            if(st!=null){
            try{
                st.close();
            } catch (SQLException ex){
                java.util.logging.Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         if(rs!=null){
            try{
                rs.close();
            } catch (SQLException ex){
                java.util.logging.Logger.getLogger(TempSepatuMasukDao.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
   }
    
   }
    
    
}
