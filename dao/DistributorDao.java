
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

/**
 *
 * @author Aby
 */
public class DistributorDao implements services.DistributorService{

    private Connection connection;
    
    public DistributorDao(){
        connection = Connections.getConnection();
    }

    @Override
    public void addData(DistributorModel disModel) {
        PreparedStatement st = null;
        String sql = "INSERT INTO distributor (id_distributor, nama_distributor, telp_distributor, alamat_distributor) VALUES (?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, disModel.getIdDistributor());
            st.setString(2, disModel.getNamaDistributor());
            st.setString(3, disModel.getTelpDistributor());
            st.setString(4, disModel.getAlamatDistributor());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DistributorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(DistributorModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void updateData(DistributorModel disModel) {
        PreparedStatement st = null;
        String sql = "UPDATE distributor SET nama_distributor=?, telp_distributor=?, alamat_distributor=? WHERE id_distributor=?";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, disModel.getIdDistributor());
            st.setString(2, disModel.getNamaDistributor());
            st.setString(3, disModel.getTelpDistributor());
            st.setString(4, disModel.getAlamatDistributor());
            
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DistributorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(DistributorModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void deleteData(DistributorModel disModel) {
        PreparedStatement st = null;
        String sql = "DELETE FROM distributor WHERE id_distributor=?";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, disModel.getIdDistributor());
            
            st.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DistributorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(DistributorModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DistributorModel getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DistributorModel> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM distributor";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
                DistributorModel disModel = new DistributorModel();
                
                disModel.setIdDistributor(rs.getString("id_distributor"));
                disModel.setNamaDistributor(rs.getString("nama_distributor"));
                disModel.setTelpDistributor(rs.getString("telp_distributor"));
                disModel.setAlamatDistributor(rs.getString("alamat_distributor"));
                
                list.add(disModel);
            }
            return list;
        }
        catch(SQLException ex){
            Logger.getLogger(DistributorModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(DistributorModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DistributorModel> search(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        
        String sql = "SELECT RIGHT (id_distributor, 3) AS Nomor "
                + "FROM distributor "
                + "WHERE id_distributor LIKE 'DST"+ no +"%' "
                + "ORDER BY Nomor DESC "
                + "LIMIT 1";
        
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()){
                int number = Integer.parseInt(rs.getString("Nomor"));
                number ++;
                order = "DST" + no + String.format("%03d", number);
            }
            else {
                order = "DST" + no + "001";
            }
        }
        catch(SQLException ex){
            Logger.getLogger(DistributorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }
                catch(SQLException ex){
                    Logger.getLogger(DistributorModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return order;
    }
}
