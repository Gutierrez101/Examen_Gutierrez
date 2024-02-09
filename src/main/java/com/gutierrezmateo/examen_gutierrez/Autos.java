/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gutierrezmateo.examen_gutierrez;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author labctr
 */
public class Autos {
    
    //constructor lleno
    public Autos(int Id, String Marca, String Modelo, String Cilindraje, int AñoFabricacion, String Placa, float Costo) {
        this.Id = Id;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.Cilindraje = Cilindraje;
        this.AñoFabricacion = AñoFabricacion;
        this.Placa = Placa;
        this.Costo = Costo;
    }
    
    //construcotr vacio
    public Autos(){
        
    }
    
 
    int Id;
    String Marca;
    String Modelo;
    String Cilindraje;
    int AñoFabricacion;
    String Placa;
    float Costo;
    
    //metodos getter ans setter

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getCilindraje() {
        return Cilindraje;
    }

    public void setCilindraje(String Cilindraje) {
        this.Cilindraje = Cilindraje;
    }

    public int getAñoFabricacion() {
        return AñoFabricacion;
    }

    public void setAñoFabricacion(int AñoFabricacion) {
        this.AñoFabricacion = AñoFabricacion;
    }


    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public float getCosto() {
        return Costo;
    }

    public void setCosto(float Costo) {
        this.Costo = Costo;
    }
    
    public void GuardarAuto(){
        Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "INSERT INTO autos(Id, marca, modelo,cilindraje,añofabricacion,placa,costo) VALUES (null,?,?,?,?,?,?)";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getMarca());
            parametro.setString(2, this.getModelo());
            parametro.setString(3, this.getCilindraje());
            parametro.setInt(4, this.getAñoFabricacion());
            parametro.setString(5, this.getPlaca());
            parametro.setFloat(6, this.getCosto());
            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ModificarAutos(){
         Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "UPDATE autos SET marca=?, modelo=?,cilindraje=?,añofabricacion=?,placa=?,costo=? WHERE Id=?";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getMarca());
            parametro.setString(2, this.getModelo());
            parametro.setString(3, this.getCilindraje());
            parametro.setInt(4, this.getAñoFabricacion());
            parametro.setString(5, this.getPlaca());
            parametro.setFloat(6, this.getCosto());
            parametro.setInt(7,this.getId());


            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Autos> ObtenerAuto(){
        Connection conexionDb = ConexionDb.getConnection();
        ResultSet rsAuto;
        
        var autos = new ArrayList<Autos>();
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "SELECT * FROM autos";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            
            //Ejecutar la sentecia SQL
             rsAuto=parametro.executeQuery();           
            
            while(rsAuto.next()){              
                    autos.add(new Autos(rsAuto.getInt("Id"),rsAuto.getString("Marca"),rsAuto.getString("Modelo"),rsAuto.getString("Cilindraje"),rsAuto.getInt("AñoFabricacion"),rsAuto.getString("Placa"),rsAuto.getFloat("Costo")));

            }
            
            conexionDb.close();
            return autos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        
    }
    
    void EliminarAuto(){
        Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "DELETE FROM autos WHERE Id=?";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setInt(1, this.getId());

            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
    
    

