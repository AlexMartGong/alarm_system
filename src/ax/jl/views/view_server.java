package ax.jl.views;

import ax.jl.connection.DatabaseConnection;
import ax.jl.dboperations.OperationsDB;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class view_server extends javax.swing.JFrame {

    OperationsDB operations = new OperationsDB();
    DatabaseConnection conne = new DatabaseConnection();
    java.sql.Connection conn = conne.connectMySQL();

    public view_server() {
        initComponents();
        cargarTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAlarm = new javax.swing.JTable();
        txtHour = new javax.swing.JTextField();
        txtMinute = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        pbtnAgregar = new javax.swing.JPanel();
        lbtnAgregar = new javax.swing.JLabel();
        pbtnEliminar = new javax.swing.JPanel();
        lbtnEliminar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableAlarm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Hour", "Minute"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableAlarm);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 390, 230));

        txtHour.setBorder(null);
        jPanel1.add(txtHour, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 36, 110, 20));

        txtMinute.setBorder(null);
        jPanel1.add(txtMinute, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 36, 110, 20));

        jLabel1.setText("Hora");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setText("Minuto");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ax/jl/images/reloj1.jpg"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 170, 160));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 110, 20));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 110, 20));

        pbtnAgregar.setBackground(new java.awt.Color(255, 82, 62));

        lbtnAgregar.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lbtnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        lbtnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtnAgregar.setText("Agregar");
        lbtnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbtnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbtnAgregarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pbtnAgregarLayout = new javax.swing.GroupLayout(pbtnAgregar);
        pbtnAgregar.setLayout(pbtnAgregarLayout);
        pbtnAgregarLayout.setHorizontalGroup(
            pbtnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        pbtnAgregarLayout.setVerticalGroup(
            pbtnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(pbtnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, -1, 30));

        pbtnEliminar.setBackground(new java.awt.Color(255, 82, 62));

        lbtnEliminar.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        lbtnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtnEliminar.setText("Eliminar");
        lbtnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbtnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbtnEliminarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pbtnEliminarLayout = new javax.swing.GroupLayout(pbtnEliminar);
        pbtnEliminar.setLayout(pbtnEliminarLayout);
        pbtnEliminarLayout.setHorizontalGroup(
            pbtnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        pbtnEliminarLayout.setVerticalGroup(
            pbtnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(pbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbtnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbtnAgregarMouseClicked
        int hour, minute;
        hour = Integer.parseInt(txtHour.getText());
        minute = Integer.parseInt(txtMinute.getText());

        operations.insertDB(0, hour, minute);
        clean();
        cargarTable();
    }//GEN-LAST:event_lbtnAgregarMouseClicked

    private void lbtnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbtnEliminarMouseClicked
        System.out.println("Eliminar");
        cargarTable();
    }//GEN-LAST:event_lbtnEliminarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(view_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(view_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(view_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(view_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new view_server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbtnAgregar;
    private javax.swing.JLabel lbtnEliminar;
    private javax.swing.JPanel pbtnAgregar;
    private javax.swing.JPanel pbtnEliminar;
    private javax.swing.JTable tableAlarm;
    private javax.swing.JTextField txtHour;
    private javax.swing.JTextField txtMinute;
    // End of variables declaration//GEN-END:variables

    private void clean() {
        txtHour.setText("");
        txtMinute.setText("");
    }

    private void cargarTable() {

        DefaultTableModel modelTable = (DefaultTableModel) tableAlarm.getModel();
        modelTable.setRowCount(0);

        String sql;
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        try {
            sql = "SELECT * FROM alarms";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rsmd = (ResultSetMetaData) rs.getMetaData();
            columnas = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int indice = 0; indice < columnas; indice++) {
                    fila[indice] = rs.getObject(indice + 1);
                }
                modelTable.addRow(fila);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

}
