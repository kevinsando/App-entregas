package mapas.view;

import mapas.graphs.Graph;

/**
 *
 * @author Kevin
 */
public class seleccionRutas extends javax.swing.JFrame {
    View view;
    public seleccionRutas(Graph g) {
        initComponents();
        this.view = new View();
        view.setGraph(g);
        //view.getGraph().leerVertices("src/vertices1.txt");
        //view.getGraph().leerAristas("src/aristas1.txt");
        
    }

    public View getView() {
        return view;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cities = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Rutas Disponibles");

        jButton1.setText("Cargar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cities.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Naranjo Alajuela", "San Rafael Heredia", "Santa Bárbara Heredia" }));
        cities.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                citiesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(cities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(cities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addGap(91, 91, 91))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(cities.getSelectedIndex()==0){
            view.setRuta("src/mapas/view/map.png");
            view.getGraph().leerVertices("src/vertices1.txt");
            view.getGraph().leerAristas("src/aristas1.txt");
        }
        if(cities.getSelectedIndex()==1){
            view.setRuta("src/mapas/view/SanRafa.png");
            view.getGraph().leerVertices("src/vertices2.txt");
            view.getGraph().leerAristas("src/aristas2.txt");
        }
        
        view.init();
        view.setVisible(true);
        view.addCarrier();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void citiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_citiesActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cities;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
