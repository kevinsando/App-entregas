/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import java.awt.geom.Point2D;
import mapas.graphs.Graph;
import mapas.graphs.Vertex;
import mapas.view.View;
import mapas.view.seleccionRutas;

/**
 *
 * @author Kevin
 */
public class Mapas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph<Integer, Integer> g1 = new Graph<>();
        
        
        //g1.leerVertices();
        //g1.leerAristas();
        
        View view;
        seleccionRutas rutas = new seleccionRutas(g1);
        rutas.setVisible(true);
        view = rutas.getView();
       // view.addCarrier();
        


        System.out.println("Tamaño: " + g1.size());
        System.out.println("Caminito mas corto: "+g1.algoritmoFloyd(g1.ParseMatrizAdy(),"33","35").toString());
    }

}
