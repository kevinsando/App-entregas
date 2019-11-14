/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas.graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import lists.Iterator;
import lists.List;
import lists.SimpleLinkedList;

/**
 *
 * @author Jason
 */
public class Graph<V, E> {
    private static final double[] W = {0.2, 0.4, 0.6, 0.8, 1.0};
    private static final int DX = 72;
    private static final int DY = 64;
    private static final int MX = 6;
    private int px = 0;
    private Point2D.Float df = new Point2D.Float(0, 0);
    private List<Vertex<V>> vertex;
    private List<Edge<V, E>> edges;
    private List<Vertex<V>> verticesQuemados;
    private final List<Edge<V, E>> aristasQuemadas;
    private int cantV;

    public Graph() {
        verticesQuemados = new SimpleLinkedList<>();
        aristasQuemadas= new SimpleLinkedList<>();
        vertex = verticesQuemados;
        edges = aristasQuemadas;
    }

    public void add(Vertex<V> v) {
        this.vertex.addLast(v);
        cantV++;
    }

    public void add(Edge<V, E> e) {
        this.edges.addLast(e);
    }

    public void add(V h, V t, E info) {
        this.add(getVertex(h), getVertex(t), info);
    }

    public void add(Vertex<V> tail, Vertex<V> head, E info) {
        if (head == null || tail == null) {
            throw new NullPointerException("No existe Vertice");
        }
        edges.addLast(new Edge<>(tail, head, info));
    }

    public int[][] ParseMatrizAdy() {
        int cantidad = this.cantV;
        Iterator<Edge<V, E>> edgeIterator;
        Iterator<Vertex<V>> vertexIterator = vertex.getIterator();
        Vertex<V> vertexAux;
        Edge<V, E> edgeAux;
        int[][] matrizAdy = new int[cantidad][cantidad];
        for (int i = 0; i < cantidad; i++) {

            for (int j = 0; j < cantidad; j++) {

                matrizAdy[i][j] = 0;
            }
        }
          // matrizAdy[i][j] = ((Integer) this.getEdges().get(i).getInfo()) + (this.getEdges().get(j).getHead().getDistancia(this.getEdges().get(j).getTail()));
             while (vertexIterator.hasNext()) {
            vertexAux = vertexIterator.getNext();
           // s.append("[" + vertexAux.getInfo() + "] -->");
            edgeIterator = this.getEdges(vertexAux.getInfo()).getIterator();
            while (edgeIterator.hasNext()) {
                edgeAux = edgeIterator.getNext();
                matrizAdy[(Integer)vertexAux.getInfo()-1][(Integer)edgeAux.getHead().getInfo()-1]=(int) ((Integer)edgeAux.getInfo()+edgeAux.getHead().getDistancia(vertexAux));
               // s.append(edgeAux.getHead().getInfo()+" Peso("+edgeAux.getInfo()+")" + ",");
            }
           // s.append("\n");
    }        return matrizAdy;
}

    public List<Vertex<V>> getVertex() {
        return vertex;
    }

    public void setVertex(List<Vertex<V>> vertex) {
        this.vertex = vertex;
    }

    public List<Edge<V, E>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<V, E>> edges) {
        this.edges = edges;
    }

    public Vertex<V> getVertex(V info) { //BUSCA EL VERTICE QUE CONTIENE EL PARAMETRO DADO, SINO DEVUELVE NULL
        Vertex<V> aux;
        Vertex<V> result = null;
        Iterator<Vertex<V>> iterator = vertex.getIterator();
        while (iterator.hasNext()) {
            aux = iterator.getNext();
            if (aux.getInfo().equals(info)) {
                result = aux;
                break;
            }
        }
        return result;
    }

    public List<Edge<V, E>> getEdges(V info) {//BUSCA TODOS LOS CAMINOS QUE SALEN DEL VERTICE CON EL PARAMETRO INDICADO
        SimpleLinkedList<Edge<V, E>> result = new SimpleLinkedList<>();
        Iterator<Edge<V, E>> iterator = edges.getIterator();
        Edge<V, E> aux;
        while (iterator.hasNext()) {
            aux = iterator.getNext();
            if (aux.getTail().getInfo().equals(info)) {
                result.addLast(aux);
            }
        }
        return result;

    }

    public int size() {
        return cantV;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Iterator<Edge<V, E>> edgeIterator;
        Iterator<Vertex<V>> vertexIterator = vertex.getIterator();
        Vertex<V> vertexAux;
        Edge<V, E> edgeAux;
        while (vertexIterator.hasNext()) {
            vertexAux = vertexIterator.getNext();
            s.append("[" + vertexAux.getInfo() + "] -->");
            edgeIterator = this.getEdges(vertexAux.getInfo()).getIterator();
            while (edgeIterator.hasNext()) {
                edgeAux = edgeIterator.getNext();
                s.append(edgeAux.getHead().getInfo() + " Peso(" + edgeAux.getInfo() + ")" + ",");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public String toString(int[][] mat) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                s.append(mat[i][j] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
    public Rectangle getBounds() {
        float x0, x1, y0, y1;
        x0 = x1 = y0 = y1 = 0f;
        boolean f = false;

        Iterator<Vertex<V>> i = verticesQuemados.getIterator();
        while (i.hasNext()) {
            Vertex<V> v = i.getNext();

            if (!f) {
                x0 = x1 = v.getPosition().x;
                y0 = y1 = v.getPosition().y;
            }
            f = true;

            x0 = Math.min(x0, v.getPosition().x);
            x1 = Math.max(x1, v.getPosition().x);
            y0 = Math.min(y0, v.getPosition().y);
            y1 = Math.max(y1, v.getPosition().y);
        }

        if (!f) {
            throw new IllegalArgumentException();
        }

        Rectangle r = new Rectangle(
                (int) (x0), (int) (y0),
                (int) (x1 - x0), (int) (y1 - y0)
        );
        r.grow(S / 2, S / 2);
        return r;
    }

    public void paint(Graphics bg, Rectangle bounds) {
        Graphics2D g = (Graphics2D) bg;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(Color.GRAY);
        g.setStroke(TRAZO_GUIA);
        Rectangle b = getBounds();
        g.drawRect(b.x, b.y, b.width, b.height);

        g.setFont(TIPO_BASE);
        FontMetrics fm = g.getFontMetrics();
        g.setStroke(TRAZO_BASE);

        Iterator<Edge<V, E>> i = aristasQuemadas.getIterator();
        while (i.hasNext()) {
            Edge<V, E> e = i.getNext();
           // System.out.println("aristas: " + e.toString());
            double r = e.getWeight();
            int p = 0;
            while (r > W[p]) {
                p++;
            }
            g.setColor(EDGE_COLOR[p]);

            g.drawLine(
                    (int) e.getTail().getPosition().x,
                    (int) e.getTail().getPosition().y,
                    (int) e.getHead().getPosition().x,
                    (int) e.getHead().getPosition().y
            );
        }

        Iterator<Vertex<V>> j = verticesQuemados.getIterator();
        while (j.hasNext()) {
            Vertex<V> v = j.getNext();
           // System.out.println("vertices: " + v.toString());
            g.setColor(Color.DARK_GRAY);
            g.fillOval(
                    (int) v.getPosition().x - S / 2 + 4,
                    (int) v.getPosition().y - S / 2 + 4,
                    S, S);
            g.setColor(Color.DARK_GRAY);
            g.fillOval(
                    (int) v.getPosition().x - S / 2,
                    (int) v.getPosition().y - S / 2,
                    S, S);
            g.setColor(Color.LIGHT_GRAY);
            g.drawOval(
                    (int) v.getPosition().x - S / 2,
                    (int) v.getPosition().y - S / 2,
                    S, S);

            String t = String.format("%s", v.getInfo());
            g.setColor(Color.WHITE);
            g.drawString(t,
                    v.getPosition().x - fm.stringWidth(t) / 2,
                    v.getPosition().y + fm.getAscent() / 2);
        }
    }
     private static final float[] DASHES = {4f, 4f};
    private static final Stroke TRAZO_BASE = new BasicStroke(2f);
    private static final Stroke TRAZO_GUIA
            = new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0f, DASHES, 0f);
    private static final Font TIPO_BASE
            = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
    private static final int S = 48;

    private static final Color[] EDGE_COLOR = {
        new Color(96, 96, 255),
        new Color(96, 255, 0),
        new Color(170, 255, 0),
        Color.ORANGE,
        new Color(255, 96, 0)
    };
    public void guardarAristas() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("C:\\Users\\dell\\Desktop\\mapas/aristas.txt");
            pw = new PrintWriter(fichero);

            Iterator<Edge<V, E>> i = edges.getIterator();
            while (i.hasNext()) {
                Edge<V, E> e = i.getNext();

                pw.println(e.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    

    public void guardarVertices() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("C:\\Users\\dell\\Desktop\\mapas/vertices.txt");
            pw = new PrintWriter(fichero);

            Iterator<Vertex<V>> j = vertex.getIterator();

            while (j.hasNext()) {
                Vertex<V> v = j.getNext();
                pw.println(v.toString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public void leerVertices() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File("C:\\Users\\dell\\Documents\\Informatica\\Estructura de Datos\\Semana 14/vertices.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea = "";

            String pos;

            while ((linea = br.readLine()) != null) {
               // System.out.println("Linea vertice: " + linea);
                String aDividir = linea;
                String parts[] = aDividir.split(",");

              //  System.out.println("Parte 1: " + parts[0].toString());
               // System.out.println("Parte 2: " + parts[1].toString());
               // System.out.println("Parte 3: " + parts[2].toString());
                
                Vertex vertice = new Vertex(parts[0].toString());
                vertice.setPosiciones(Float.parseFloat(parts[1].toString()), (float)Integer.parseInt(parts[2].toString()));
                verticesQuemados.addLast(vertice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }
    
    public void leerAristas(){
         File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File("C:\\Users\\dell\\Documents\\Informatica\\Estructura de Datos\\Semana 14/aristas.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea = "";
            String pos;
            while ((linea = br.readLine()) != null) {
                String aDividir = linea;
                String parts[] = aDividir.split(",");

              //  System.out.println("Info 1: " + parts[0].toString());
              //  System.out.println("x    1: " + parts[1].toString());
              //  System.out.println("y    1: " + parts[2].toString());
                
              //  System.out.println("Info 2: " + parts[3].toString());
              //  System.out.println("x    2: " + parts[4].toString());
               // System.out.println("y    2: " + parts[5].toString());
                
              //  System.out.println("Info A: " + parts[6].toString());
                
                Vertex tail = new Vertex(parts[0].toString());
                tail.setPosiciones(Float.parseFloat(parts[1].toString()), Float.parseFloat(parts[2].toString()));
                
                Vertex head = new Vertex(parts[0].toString());
                head.setPosiciones(Float.parseFloat(parts[1].toString()),Float.parseFloat(parts[2].toString()));
                
                Edge arista = new Edge(tail,head,Double.parseDouble(parts[6].toString()));
                aristasQuemadas.addLast(arista);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
