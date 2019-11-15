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
import java.awt.geom.Line2D;
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
    private List<Vertex<V>> verticesQuemados;
    private final List<Edge<V, E>> aristasQuemadas;
    private int cantV;

    public Graph() {
        verticesQuemados = new SimpleLinkedList<>();
        aristasQuemadas = new SimpleLinkedList<>();
    }

    public void add(Vertex<V> v) {
        this.verticesQuemados.addLast(v);
        cantV++;
    }

    public void add(Edge<V, E> e) {
        this.aristasQuemadas.addLast(e);
    }

    public void add(V h, V t, E info) {
        this.add(getVertex(h), getVertex(t), info);
    }

    public void add(Vertex<V> tail, Vertex<V> head, E info) {
        if (head == null || tail == null) {
            throw new NullPointerException("No existe Vertice");
        }
        this.aristasQuemadas.addLast(new Edge<>(tail, head, info));
    }

    public int[][] ParseMatrizAdy() {
        int cantidad = this.cantV;
        Iterator<Edge<V, E>> edgeIterator;
        Iterator<Vertex<V>> vertexIterator = verticesQuemados.getIterator();
        Vertex<V> vertexAux;
        Edge<V, E> edgeAux;
        int[][] matrizAdy = new int[cantidad][cantidad];
        for (int i = 0; i < cantidad; i++) {

            for (int j = 0; j < cantidad; j++) {
                if (i == j) {
                    matrizAdy[i][j] = 0;
                } else {
                    matrizAdy[i][j] = 1000;
                }
            }
        }
        while (vertexIterator.hasNext()) {
            vertexAux = vertexIterator.getNext();
            edgeIterator = this.getEdges(vertexAux.getInfo()).getIterator();
            while (edgeIterator.hasNext()) {
                edgeAux = edgeIterator.getNext();
                matrizAdy[Integer.parseInt((String) vertexAux.getInfo()) - 1][Integer.parseInt((String) edgeAux.getHead().getInfo()) - 1]
                        = (int) (Math.round(Integer.parseInt(vertexAux.getInfo().toString()))) + edgeAux.getHead().getDistancia(vertexAux);

            }
        }
        return matrizAdy;
    }

    public List<Vertex<V>> getVertex() {
        return verticesQuemados;
    }

    public void setVertex(List<Vertex<V>> vertex) {
        //this.vertex = vertex;
    }

    public List<Edge<V, E>> getEdges() {
        return aristasQuemadas;
    }

    public void setEdges(List<Edge<V, E>> edges) {
        //this.aristasQuemadas = edges;
    }

    public Vertex<V> getVertex(V info) { //BUSCA EL VERTICE QUE CONTIENE EL PARAMETRO DADO, SINO DEVUELVE NULL
        Vertex<V> aux;
        Vertex<V> result = null;
        Iterator<Vertex<V>> iterator = verticesQuemados.getIterator();
        while (iterator.hasNext()) {
            aux = iterator.getNext();
            //System.out.println("--"+aux.getInfo()+"--"+info+"--");
            if (aux.getInfo().equals(info)) {
                result = aux;
                break;
            }
        }
        return result;
    }

    public List<Vertex<V>> getVertex(V info, int lenght) { //BUSCA EL VERTICE QUE CONTIENE EL PARAMETRO DADO, SINO DEVUELVE NULL
        Vertex<V> aux;
        boolean bandera = false;
        int i = 0;
        String value = (String) info;
        List<Vertex<V>> result = new SimpleLinkedList<>();

        int inicio = -3;
        int fin = -1;
        Iterator<Vertex<V>> iterator = verticesQuemados.getIterator();
        while (iterator.hasNext() && lenght > 0) {
            String valor = (String) info;
            aux = iterator.getNext();
            if (lenght == 1)//Si es solo un caracter
            {
                result.addLast(getVertex((V) valor));
                break;
            }
            if (value.charAt(2 * i + 1) == ',')//Si hay valores de un digito
            {
                if (inicio < 0) {
                    inicio += 3;
                } else {
                    inicio += 2;
                }
                bandera = true;
                fin += 2;
                result.addLast(getVertex((V) valor.substring(inicio, fin)));
                lenght -= 2;
            } else {
                if (bandera) {
                    inicio--;
                    bandera = false;
                }
                inicio += 3;
                fin += 3;
                i--;
                //System.out.println("--"+aux.getInfo()+"--"+info+"--");
                result.addLast(getVertex((V) valor.substring(inicio, fin)));
                lenght -= 3;
            }
            i++;
        }
        return result;
    }

    public List<Edge<V, E>> getEdges(V info) {//BUSCA TODOS LOS CAMINOS QUE SALEN DEL VERTICE CON EL PARAMETRO INDICADO
        SimpleLinkedList<Edge<V, E>> result = new SimpleLinkedList<>();
        Iterator<Edge<V, E>> iterator = aristasQuemadas.getIterator();
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
        Iterator<Vertex<V>> vertexIterator = verticesQuemados.getIterator();
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

    public List<Vertex<V>> algoritmoFloyd(int[][] grafo, String inicio, String fin) {
        int vertices = grafo.length;
        int matrizAdy[][] = grafo;
        String caminos[][] = new String[vertices][vertices];
        String caminosAuxiliares[][] = new String[vertices][vertices];
        List<Vertex<V>> list = new SimpleLinkedList<>();
        String caminoRecorrido = "", cadena = "", caminitos = "";
        int temporal1, temporal2, temporal3, temporal4, minimo;
        //INICIALIZANDO MATRICES
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                caminos[i][j] = "";
                caminosAuxiliares[i][j] = "";
            }
        }
        for (int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    temporal1 = matrizAdy[i][j];
                    temporal2 = matrizAdy[i][k];
                    temporal3 = matrizAdy[k][j];
                    temporal4 = temporal2 + temporal3;

                    //Encuentra minimo
                    minimo = Math.min(temporal1, temporal4);
                    if (temporal1 != temporal4) {
                        if (minimo == temporal4) {
                            caminoRecorrido = "";
                            caminosAuxiliares[i][j] = k + "";
                            caminos[i][j] = caminosR(i, k, caminosAuxiliares, caminoRecorrido) + (k + 1);

                        }
                    }
                    matrizAdy[i][j] = minimo;
                }
            }
        }
        //AGREGANDO EL CAMINO MIN A CADENA

        for (int i = 0; i < vertices; i++) {
            cadena += "" + (i + 1) + " ";
            for (int j = 0; j < vertices; j++) {
                cadena = cadena + "[" + matrizAdy[i][j] + "]";
            }
            cadena = cadena + "\n";
        }
        //LOGIC

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (matrizAdy[i][j] != 1000)//nuestro infinito
                {
                    if (i != j)//no son el mismo nodo
                    {
                        if (Integer.toString(i + 1).equals(inicio) && Integer.toString(j + 1).equals(fin)) {//FILTRO DE INICIO Y FIN
                            if (caminos[i][j].equals("")) {
                                caminitos += "De [" + (i + 1) + "--->" + (j + 1) + "] Irse por...[" + (i + 1) + ", " + (j + 1) + "]\n";
                                list.addLast(getVertex((V) Integer.toString(i + 1)));
                                list.addLast(getVertex((V) Integer.toString(j + 1)));
                            } else {
                                caminitos += "De [" + (i + 1) + "--->" + (j + 1) + "] Irse por...[" + (i + 1) + ", " + caminos[i][j] + ", " + (j + 1) + "]\n";
                                list.addLast(getVertex((V) Integer.toString(i + 1)));
                                list.append(getVertex((V) caminos[i][j], caminos[i][j].length()));

                                list.addLast(getVertex((V) Integer.toString(j + 1)));
                            }
                        }
                    }
                }
            }
        }
//        System.out.println(list.toString());
        //System.out.println("La matriz de caminitos mas cortos entre los diferentes vertices es:\n" + cadena);//Matriz de caminos mas cortos
//        if (caminitos.isEmpty()) {
//            return "No hay camino disponible entre esos vertices\n";
//        } else {
        System.out.println("\nLos diferentes caminitos mas cortos entre vertices son:\n" + caminitos);
//        }
        return list;
    }

    private String caminosR(int i, int k, String[][] caminosAuxiliares, String caminoRecorrido) {
        if (caminosAuxiliares[i][k].equals("")) {
            return "";
        } else {
            caminoRecorrido += caminosR(i, Integer.parseInt(caminosAuxiliares[i][k]),
                    caminosAuxiliares, caminoRecorrido) + (Integer.parseInt(caminosAuxiliares[i][k]) + 1) + ",";
        }
        return caminoRecorrido;
    }

    public String toString(int[][] mat) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {

                s.append(mat[i][j]).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

//    public Rectangle getBounds() {
//        float x0, x1, y0, y1;
//        x0 = x1 = y0 = y1 = 0f;
//        boolean f = false;
//
//        Iterator<Vertex<V>> i = verticesQuemados.getIterator();
//        while (i.hasNext()) {
//            Vertex<V> v = i.getNext();
//
//            if (!f) {
//                x0 = x1 = v.getPosition().x;
//                y0 = y1 = v.getPosition().y;
//            }
//            f = true;
//
//            x0 = Math.min(x0, v.getPosition().x);
//            x1 = Math.max(x1, v.getPosition().x);
//            y0 = Math.min(y0, v.getPosition().y);
//            y1 = Math.max(y1, v.getPosition().y);
//        }
//
//        if (!f) {
//            throw new IllegalArgumentException();
//        }
//
//        Rectangle r = new Rectangle(
//                (int) (x0), (int) (y0),
//                (int) (x1 - x0), (int) (y1 - y0)
//        );
//        r.grow(S / 2, S / 2);
//        return r;
//    }
//
//    public void paint(Graphics bg, Rectangle bounds) {
//        Graphics2D g = (Graphics2D) bg;
//
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
//                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//
//        g.setColor(Color.GRAY);
//        g.setStroke(TRAZO_GUIA);
//        Rectangle b = getBounds();
//        g.drawRect(b.x, b.y, b.width, b.height);
//
//        g.setFont(TIPO_BASE);
//        FontMetrics fm = g.getFontMetrics();
//        g.setStroke(TRAZO_BASE);
//
//        Iterator<Edge<V, E>> i = aristasQuemadas.getIterator();
//        while (i.hasNext()) {
//            Edge<V, E> e = i.getNext();
//           // System.out.println("aristas: " + e.toString());
//            double r = e.getWeight();
//            int p = 0;
//            while (r > W[p]) {
//                p++;
//            }
//            g.setColor(EDGE_COLOR[p]);
//
//            g.drawLine(
//                    (int) e.getTail().getPosition().x,
//                    (int) e.getTail().getPosition().y,
//                    (int) e.getHead().getPosition().x,
//                    (int) e.getHead().getPosition().y
//            );
//        }
//
//        Iterator<Vertex<V>> j = verticesQuemados.getIterator();
//        while (j.hasNext()) {
//            Vertex<V> v = j.getNext();
//           // System.out.println("vertices: " + v.toString());
//            g.setColor(Color.DARK_GRAY);
//            g.fillOval(
//                    (int) v.getPosition().x - S / 2 + 4,
//                    (int) v.getPosition().y - S / 2 + 4,
//                    S, S);
//            g.setColor(Color.DARK_GRAY);
//            g.fillOval(
//                    (int) v.getPosition().x - S / 2,
//                    (int) v.getPosition().y - S / 2,
//                    S, S);
//            g.setColor(Color.LIGHT_GRAY);
//            g.drawOval(
//                    (int) v.getPosition().x - S / 2,
//                    (int) v.getPosition().y - S / 2,
//                    S, S);
//
//            String t = String.format("%s", v.getInfo());
//            g.setColor(Color.WHITE);
//            g.drawString(t,
//                    v.getPosition().x - fm.stringWidth(t) / 2,
//                    v.getPosition().y + fm.getAscent() / 2);
//        }
//    }
//     private static final float[] DASHES = {4f, 4f};
//    private static final Stroke TRAZO_BASE = new BasicStroke(2f);
//    private static final Stroke TRAZO_GUIA
//            = new BasicStroke(1.0f,
//                    BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
//                    0f, DASHES, 0f);
//    private static final Font TIPO_BASE
//            = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
//    private static final int S = 48;
//
//    private static final Color[] EDGE_COLOR = {
//        new Color(96, 96, 255),
//        new Color(96, 255, 0),
//        new Color(170, 255, 0),
//        Color.ORANGE,
//        new Color(255, 96, 0)
//    };
    public void paint(Graphics g) {
        Graphics2D media = (Graphics2D) g;
        Iterator<Edge<V, E>> edgeIterator;
        Iterator<Vertex<V>> vertexIterator = verticesQuemados.getIterator();
        Vertex<V> vertexAux;
        Edge<V, E> edgeAux;
        while (vertexIterator.hasNext()) {
            vertexAux = vertexIterator.getNext();
            edgeIterator = getEdges(vertexAux.getInfo()).getIterator();

            while (edgeIterator.hasNext()) {
                edgeAux = edgeIterator.getNext();
                media.setStroke(new BasicStroke(3f));
                media.setColor(Color.blue);
                media.drawLine((int) edgeAux.getTail().getPosition().getX(),
                        (int) edgeAux.getTail().getPosition().getY(),
                        (int) edgeAux.getHead().getPosition().getX(),
                        (int) edgeAux.getHead().getPosition().getY() - 6);
                drawArrowHead(media, (int) edgeAux.getTail().getPosition().getX(),
                        (int) edgeAux.getTail().getPosition().getY(),
                        (int) edgeAux.getHead().getPosition().getX(),
                        (int) edgeAux.getHead().getPosition().getY() - 10, Color.blue);

            }
            media.setStroke(new BasicStroke(4f));
            media.setColor(Color.lightGray);

            //media.fillOval((int) vertexAux.getPosition().x - 5, (int) vertexAux.getPosition().y - 10, 20, 20);
            media.setFont(new Font("Arial Black", 0, 20));
            media.setColor(Color.black);
            media.drawString(vertexAux.getInfo().toString(), (int) vertexAux.getPosition().getX(), (int) vertexAux.getPosition().getY() + 5);
        }

    }

    private void drawArrowHead(Graphics2D g2, int x1, int y1, int x2, int y2, Color color) {
        double phi = Math.toRadians(40);
        int barb = 15;
        g2.setPaint(color);
        double dy = y2 - y1;
        double dx = x2 - x1;
        double theta = Math.atan2(dy, dx);
        double x, y, rho = theta + phi;
        for (int j = 0; j < 2; j++) {
            x = x2 - barb * Math.cos(rho);
            y = y2 - barb * Math.sin(rho);
            g2.draw(new Line2D.Double(x2, y2, x, y));
            rho = theta - phi;
        }
    }

    public void updateCarrier(Carrier m) {
        if (m.isMoving()) {
            m.move();
        } else {
            synchronized (Graph.this) {
                Vertex<V> v0 = m.getEndVertex();
                Iterator<Edge<V, E>> edges = getEdges(v0.getInfo()).getIterator();
                List<Vertex<V>> list = m.getRuta();
//                list=algoritmoFloyd(ParseMatrizAdy(),(String)m.getStartVertex().getInfo(),(String)m.getEndVertex().getInfo());
//                while (edges.hasNext()) {
//                    list.addLast(edges.getNext().getHead());
//                }
                // Se define el criterio para seleccionar
                // el siguiente vértice.
                Vertex<V> v1 = list.getFirst();////////////////usar metodo para elegir
                list.removeFirst();
                m.setRuta(list);
                if (v1 == null) {
                    m.setMoving(false);
                } else {
                    m.setStartVertex(v0);
                    m.setEndVertex(v1);
                    m.recalculateVelocity();
                    m.start();
                }

            }
        }
    }

    public void guardarAristas() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src/aristas2.txt");
            pw = new PrintWriter(fichero);

            Iterator<Edge<V, E>> i = this.aristasQuemadas.getIterator();
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
            fichero = new FileWriter("src/vertices2.txt");
            pw = new PrintWriter(fichero);

            Iterator<Vertex<V>> j = this.verticesQuemados.getIterator();

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
            archivo = new File("src/vertices1.txt");
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
                Vertex vertice = new Vertex(parts[0]);
                vertice.setPosiciones(Float.parseFloat(parts[1].toString()), Float.parseFloat(parts[2].toString()));
                verticesQuemados.addLast(vertice);
                this.cantV++;
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

    public void leerAristas() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File("src/aristas1.txt");
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
                //Vertex tail = this.getVertex((V)parts[0].toString()); //new Vertex(parts[0].toString());
                //tail.setPosiciones(Float.parseFloat(parts[1].toString()), Float.parseFloat(parts[2].toString()));
                //Vertex head = this.getVertex((V)parts[0].toString());//new Vertex(parts[0].toString());
                //head.setPosiciones(Float.parseFloat(parts[1].toString()), Float.parseFloat(parts[2].toString()));
                Edge arista = new Edge(this.getVertex((V) parts[0].toString()), this.getVertex((V) parts[1].toString()), Double.parseDouble(parts[2].toString()));
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

    public Vertex<V> getVertex(String string) {
        Vertex<V> aux;
        Vertex<V> result = null;
        Iterator<Vertex<V>> iterator = verticesQuemados.getIterator();
        while (iterator.hasNext()) {
            aux = iterator.getNext();
            //System.out.println("--"+aux.getInfo()+"--"+info+"--");
            if (aux.getInfo().equals(string)) {
                result = aux;
                break;
            }
        }
        return result;
    }

}
