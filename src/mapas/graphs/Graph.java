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

    public List<Edge<V, E>> getEdges() {
        return aristasQuemadas;
    }

    public Vertex<V> getVertex(V info) { //BUSCA EL VERTICE QUE CONTIENE EL PARAMETRO DADO, SINO DEVUELVE NULL
        Vertex<V> aux;
        Vertex<V> result = null;
        Iterator<Vertex<V>> iterator = verticesQuemados.getIterator();
        while (iterator.hasNext()) {
            aux = iterator.getNext();
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
            if (valor.length() == 1)//Si es solo un caracter
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
 public List<Vertex<V>> buscarConexionNodos(int[][] grafo, String inicio, String fin)
    {
        Vertex<V> auxInicio=getVertex((V)inicio);
        Vertex<V> auxFinal=getVertex((V)fin);
        List<Vertex<V>> list = new SimpleLinkedList<>();
        if(getEdges(auxFinal.getInfo()).getFirst().getTail()!=auxInicio)
        {      list.append(algoritmoFloyd(grafo,inicio,fin));
        }
        return list;
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
                            Vertex<V> begin=getVertex((V) Integer.toString(i + 1));//PRIMER NODO
                            list.addLast(begin);
                            List<Vertex<V>> encloche = getVertex((V) ((V) caminos[i][j]+","), caminos[i][j].length());//CODIGO INTERMEDIO
                            Vertex<V> salvaTandas=encloche.getFirst();
                            encloche.removeLast();
                            encloche.append(buscarConexionNodos(grafo,(String)salvaTandas.getInfo(),(String)getVertex((V) Integer.toString(j + 1)).getInfo()));//ULTIMO DE ENCLOCHE,Y Ultimo nodo
                            list.append(encloche);
                        }
                         }
                    }
                }
            }
        }
        System.out.println(list.toString());
        if (caminitos.isEmpty()) {
            System.out.println("No hay camino disponible entre esos vertices\n");
        } else {
            System.out.println("\nLos diferentes caminitos mas cortos entre vertices son:\n" + caminitos);
        }
        return list;
    }
    
    public Edge buscarArista(V tail, V head){
        Iterator<Edge<V,E>> iterator = this.aristasQuemadas.getIterator();
        Edge<V,E> aux = null;
        while(iterator.hasNext()){
            aux=iterator.getNext();
            if(aux.getTail().equals(tail)&&aux.getHead().equals(head)){
                break;
            }
        }
        return aux;
    }

    private String caminosR(int i, int k, String[][] caminosAuxiliares, String caminoRecorrido) {
        if (caminosAuxiliares[i][k].equals("")) {
            return "";
        } else {
            caminoRecorrido += caminosR(i, Integer.parseInt(caminosAuxiliares[i][k]),
                    caminosAuxiliares, caminoRecorrido) + (Integer.parseInt(caminosAuxiliares[i][k]) + 1) + ",";
            return caminoRecorrido;

        }
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
                        (int) edgeAux.getHead().getPosition().getY() - 10, Color.GREEN);

            }
            media.setStroke(new BasicStroke(4f));
            media.setColor(Color.lightGray);

            media.setFont(new Font("Arial Black", 0, 20));
            media.setColor(Color.black);
            media.drawString(vertexAux.getInfo().toString(), (int) vertexAux.getPosition().getX(), (int) vertexAux.getPosition().getY() + 5);
        }

    }
    public void printInfo(Graphics g){
        Graphics2D media = (Graphics2D) g;
        Iterator<Vertex<V>> vertexIterator = verticesQuemados.getIterator();
        Vertex<V> vertexAux;
        while (vertexIterator.hasNext()) {
            vertexAux = vertexIterator.getNext();
            media.setStroke(new BasicStroke(4f));
            media.setColor(Color.lightGray);

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
                List<Vertex<V>> list = m.getRuta();
                Vertex<V> v1 = list.getFirst();
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

    public void leerVertices(String direccion) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File(direccion);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea = "";

            String pos;

            while ((linea = br.readLine()) != null) {
                String aDividir = linea;
                String parts[] = aDividir.split(",");
                Vertex vertice = new Vertex(parts[0]);
                vertice.setPosiciones(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
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

    public void leerAristas(String direccion) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File(direccion);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea = "";
            while ((linea = br.readLine()) != null) {
                String aDividir = linea;
                String parts[] = aDividir.split(",");
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
            if (aux.getInfo().equals(string)) {
                result = aux;
                break;
            }
        }
        return result;
    }

}
