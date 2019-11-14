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

    public Vertex(V info, Point2D.Float pos) {
        this.info = info;
        this.position = pos;
    }
    public Vertex(V info) {
        this(info, new Point2D.Float(0f, 0f));
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

    public int getDistancia(Vertex<V> v) {
        double cateto1 = position.getX() - v.getPosition().getX();
        double cateto2 = position.getY() - v.getPosition().getY();
        int hipotenusa = (int) Math.sqrt(cateto1 * cateto1 + cateto2 * cateto2);
        return  hipotenusa;
    }
    public void setPosiciones(float x, float y){
        this.position.setLocation(x, y);
    }

    @Override
    public String toString() {
        return String.format("%s,%4.2f,%4.2f",
                getInfo(), getPosition().getX(), getPosition().getY());
    }
}
