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
import models.SepatuModel;
import models.TempPemesananModel;
import services.TempPemesananService;

/**
 *
 * @author Lenovo
 */
public class TempPemesananDao implements TempPemesananService{
    
    private final Connection connection; 
    
    public TempPemesananDao(){
        connection = Connections.getConnection();  
    }

    @Override
    public void addData(TempPemesananModel tmp_pModel) {
        PreparedStatement st = null;
        String sql = "INSERT INTO temp_pemesanan (id_sepatu, nama_sepatu, harga, jml_pemesanan, subtotal_pemesanan) VALUES (?,?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
          
            st.setString(1, tmp_pModel.getsModel().getIdSepatu());
            st.setString(2, tmp_pModel.getsModel().getNamaSepatu());
            st.setDouble(3, tmp_pModel.getsModel().getHarga());
            st.setInt(4, tmp_pModel.getDpModel().getJmlPemesanan());
            st.setDouble(5, tmp_pModel.getDpModel().getSubtotalPemesanan());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(TempPemesananModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(TempPemesananModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void updateData(TempPemesananModel tmp_pModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteData(TempPemesananModel tmp_pModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TempPemesananModel getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TempPemesananModel> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM Temp_Pemesanan";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
                TempPemesananModel tempModel = new TempPemesananModel();
                SepatuModel brgModel = new SepatuModel();
                DetailPemesananModel detModel = new DetailPemesananModel();
                
                brgModel.setIdSepatu(rs.getString("id_sepatu"));
                brgModel.setNamaSepatu(rs.getString("nama_sepatu"));
                brgModel.setHarga(rs.getDouble("harga"));
                
                
                detModel.setJmlPemesanan(rs.getInt("jml_pemesanan"));
                detModel.setSubtotalPemesanan(rs.getInt("subtotal_Pemesanan"));
                
                tempModel.setsModel(brgModel);
                tempModel.setDpModel(detModel);
                
                list.add(tempModel);
            }
            return list;
        }
        catch(SQLException ex){
            Logger.getLogger(TempPemesananModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(TempPemesananModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(TempPemesananModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        }
    }
    

