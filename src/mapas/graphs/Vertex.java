/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas.graphs;

import java.awt.geom.Point2D;

/**
 *
 * @author Jason
 */
public class Vertex<V> {
    private V info;
    private Point2D.Float position;
    
    public Vertex(V info,Point2D.Float pos){
        this.info=info;
        this.position = pos;
    }

    public V getInfo() {
        return info;
    }

    public void setInfo(V info) {
        this.info = info;
    }

    public Point2D.Float getPosition() {
        return position;
    }

    public void setPosition(Point2D.Float position) {
        this.position = position;
    }
}