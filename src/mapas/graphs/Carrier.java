/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas.graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import lists.List;

/**
 *
 * @author Jason
 */
public class Carrier<V> {
    private Vertex<V> startVertex;
    private Vertex<V> endVertex;
    private Point2D.Float startPosition;
    private Point2D.Float endPosition;
    private double dt;
    private double t;
    private boolean moving;    
    private static final double MIN_DR = 4.0;
    private static final double MAX_DR = 5.5;
    private List<Vertex<V>> ruta;
    public Carrier(Vertex<V> startVertex, Vertex<V> endVertex,List<Vertex<V>> lis) {
        this.startVertex = startVertex;
        this.endVertex = startVertex;
        this.startPosition = startVertex.getPosition();
        this.endPosition = endVertex.getPosition();
        this.dt = 0.0;
        this.t = 0.0;
        this.moving = false;
        ruta=lis;
    }

    public List<Vertex<V>> getRuta() {
        return ruta;
    }

    public void setRuta(List<Vertex<V>> ruta) {
        this.ruta = ruta;
    }
    
        public void start() {
        t = 0.0;
        setMoving(true);
    }

    public void move() {
        if (moving = (t <= 1.0)) {
            t += dt;
        }
    }

    public void paint(Graphics2D g) {
        g.setStroke(new BasicStroke(8f));
        g.setColor(Color.RED);
        g.drawOval(
                (int) ((startPosition.x + t * (endPosition.x - startPosition.x)) - 10 / 2),
                (int) ((startPosition.y + t * (endPosition.y - startPosition.y)) - 10 / 2),
                10, 10);
    }

    public Vertex<V> getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex<V> startVertex) {
        this.startVertex = startVertex;
        this.startPosition = startVertex.getPosition();
    }

    public Vertex<V> getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Vertex<V> endVertex) {
        this.endVertex = endVertex;
        this.endPosition = endVertex.getPosition();
    }

    public void recalculateVelocity() {
        Point2D.Float p0 = startVertex.getPosition();
        Point2D.Float p1 = endVertex.getPosition();

        float dx = p1.x - p0.x;
        float dy = p1.y - p0.y;
        double dm = Math.sqrt(dx * dx + dy * dy);
        double dr = MIN_DR + Math.random() * (MAX_DR - MIN_DR);
        dt = dr / dm;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    
    
}
