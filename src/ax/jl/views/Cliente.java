package ax.jl.views;

import ax.jl.connection.DatabaseConnection;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class Cliente extends javax.swing.JFrame {

    DatabaseConnection conne = new DatabaseConnection();
    java.sql.Connection conn = conne.connectMySQL();

    private ArrayList<Alarma> listaAlarmas;
    private Timer timer;
    private Socket socketServidor;
    private ArrayList<JPanel> listaPanelesAlarmas;

    public Cliente() {
        initComponents();
        listaAlarmas = new ArrayList<>();
        listaPanelesAlarmas = new ArrayList<>(); // Inicializa la lista de JPanel
        mostrarHoraActual();
        conectarAServidor();
    }

    private void mostrarHoraActual() {
        timer = new Timer(1000, (ActionEvent e) -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String horaConAMPM = dateFormat.format(new Date());
            lblreloj.setText(horaConAMPM);
            for (Alarma alarma : listaAlarmas) {
                if (alarma.getHora().equals(horaConAMPM) && alarma.isActiva()) {
                    mostrarIconoAlarma();
                    break;
                }
            }
        });
        timer.start();
    }

    private void mostrarIconoAlarma() {
        // Reproducir el sonido de la alarma continuamente
        new Thread(() -> {
            try {
                // Obtener el sonido predeterminado del sistema (solo en Windows)
                Object sound = Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.default");
                if (sound != null) {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                }

                // Crear y configurar la ventana emergente de la alarma
                JFrame frame = new JFrame("Alarma");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(300, 200);
                frame.setLocationRelativeTo(null);

                // Agregar el mensaje de la alarma
                JLabel label = new JLabel("¡Alarma activada!");
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 20));
                frame.add(label);

                // Mostrar la ventana emergente
                frame.setVisible(true);

                // Esperar hasta que se cierre la ventana para detener la reproducción del sonido
                while (frame.isVisible()) {
                    Thread.sleep(100); // Pequeña pausa para evitar uso innecesario del procesador
                }

                // Cerrar la ventana emergente
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();

    }

    private void conectarAServidor() {

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    socketServidor = new Socket("localhost", 5001);
                    recibirAlarmaDesdeServidor(socketServidor);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        };
        worker.execute();

    }

    private void eliminarAlarmaCliente(String alarma) {
        for (Alarma a : listaAlarmas) {
            if (a.getHora().equals(alarma)) {
                a.setActiva(false);
                break;
            }
        }
    }

    private void recibirAlarmaDesdeServidor(Socket servidor) throws IOException {
        DataInputStream leer = new DataInputStream(servidor.getInputStream());
        String mensaje = leer.readUTF();
        String[] partes = mensaje.split(",");
        String accion = partes[0];
        String alarma = partes[1];
        if (accion.equals("eliminar")) {
            eliminarAlarmaCliente(alarma);
        } else if (accion.equals("agregar")) { // Cambio aquí
            // Agregar la alarma a la lista
            listaAlarmas.add(new Alarma(alarma, true));
        }

    }

    private static class Alarma {

        private String hora;
        private boolean activa;

        public Alarma(String hora, boolean activa) {
            this.hora = hora;
            this.activa = activa;
        }

        public String getHora() {
            return hora;
        }

        public boolean isActiva() {
            return activa;
        }

        public void setActiva(boolean activa) {
            this.activa = activa;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        label2 = new java.awt.Label();
        panel2 = new java.awt.Panel();
        lblreloj = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlarms = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 51, 102));

        panel1.setBackground(new java.awt.Color(204, 255, 204));

        label2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        label2.setText("Cliente");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel2.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
        );

        lblreloj.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lblreloj.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        jLabel1.setText("Hora Actual Sistema");

        tblAlarms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "HORA", "MINUTO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAlarms);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblreloj, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblreloj)
                        .addGap(0, 142, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label2;
    private javax.swing.JLabel lblreloj;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JTable tblAlarms;
    // End of variables declaration//GEN-END:variables


}
