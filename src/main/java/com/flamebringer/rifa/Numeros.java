/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flamebringer.rifa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
/**
 *
 * @author Brigido
 */
public class Numeros {
     private StringProperty numero;
    private StringProperty nombre;
    
    public Numeros(String numero, String nombre) {
        this.numero = new SimpleStringProperty(numero);
        this.nombre = new SimpleStringProperty(nombre);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(StringProperty nombre) {
        this.nombre = nombre;
    }

    public Numeros(StringProperty nombre) {
        this.nombre = nombre;
    }
    
    public StringProperty PropertyNombre() {
        return nombre;
    }
    
    
    

    public String getNumero() {
        return numero.get();
    }

    public void setNumero(StringProperty numero) {
        this.numero = numero;
    }

    public StringProperty PropertyNumero() {
        return numero;
    }
    
        
   
    public static void NumerosTabla2(ObservableList<Numeros> numeros) {
        int i = 0;
        while(i < 100){
            if (i < 10) {
                numeros.add(
                    new Numeros(    
                        "0"+String.valueOf(i),
                        "?"
                    )
                );
            } else{
                numeros.add(
                    new Numeros(    
                        String.valueOf(i),
                        "?"
                    )
                );
            }
        i++;
        }
    }
    public static void NumerosTabla3(ObservableList<Numeros> numeros) {
        int i = 0;
        while(i < 1000){
            if (i < 10) {
                numeros.add(
                    new Numeros(    
                        "00"+String.valueOf(i),
                        "?"
                    )
                );
            } if (i >= 10 && i < 100) {
                numeros.add(
                    new Numeros(    
                        "0"+String.valueOf(i),
                        "?"
                    )
                );
            } if (i >= 100){
                numeros.add(
                    new Numeros(    
                        String.valueOf(i),
                        "?"
                    )
                );
            }
        i++;
        }
    }
    
}
