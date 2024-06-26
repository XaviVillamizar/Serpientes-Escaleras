/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package serpienteescalera;

import javax.swing.JOptionPane;

/**
 *
 * @author xavis
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Mai
     */
    public Main() {
        initComponents();
        this.setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        inicio = new javax.swing.JButton();
        tableros = new javax.swing.JComboBox<>();
        jugadores = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("&");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Escaleras");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Serpientes ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        inicio.setFont(new java.awt.Font("Microsoft YaHei Light", 3, 24)); // NOI18N
        inicio.setText("INICIAR JUEGO");
        inicio.setBorder(null);
        inicio.setBorderPainted(false);
        inicio.setContentAreaFilled(false);
        inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioActionPerformed(evt);
            }
        });
        jPanel1.add(inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 220, 65));

        tableros.setBackground(new java.awt.Color(198, 45, 171));
        tableros.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        tableros.setForeground(new java.awt.Color(255, 255, 255));
        tableros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tamaño Tablero", "10x10", "13x13", "15x15" }));
        jPanel1.add(tableros, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 170, 46));

        jugadores.setBackground(new java.awt.Color(40, 162, 185));
        jugadores.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jugadores.setForeground(new java.awt.Color(255, 255, 255));
        jugadores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Numero de Jugadores", "2", "3", "4" }));
        jPanel1.add(jugadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 100, 190, 48));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/serpienteescalera/Bg Main.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 380));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioActionPerformed
        // TODO add your handling code here:
       String tamañoSeleccionado = (String) tableros.getSelectedItem();
String jugadorSeleccionado = (String) jugadores.getSelectedItem();
if (tamañoSeleccionado == null || jugadorSeleccionado == null || tamañoSeleccionado.equals("Tamaño Tablero") || jugadorSeleccionado.equals("Numero de Jugadores")) {
    JOptionPane.showMessageDialog(this, "Por favor, selecciona un tamaño de tablero y número de jugadores.", "Error", JOptionPane.ERROR_MESSAGE);
} else {
    int tamañoTablero = Integer.parseInt(tamañoSeleccionado.split("x")[0]);
    int numJugadores = Integer.parseInt(jugadorSeleccionado);

    JuegoFrame frame = new JuegoFrame(tamañoTablero, numJugadores);
    frame.setVisible(true);
    this.dispose();
}

    }//GEN-LAST:event_inicioActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> jugadores;
    private javax.swing.JComboBox<String> tableros;
    // End of variables declaration//GEN-END:variables
}
