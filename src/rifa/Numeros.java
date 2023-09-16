/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rifa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author brigido
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

