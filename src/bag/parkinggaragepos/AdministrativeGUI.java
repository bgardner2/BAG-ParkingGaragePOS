/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bag.parkinggaragepos;



import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Ben
 */
public class AdministrativeGUI extends javax.swing.JFrame {

    File configFile = new File("src/config.properties");
    Properties props = new Properties();
    FileInputStream input;
    FileOutputStream output;

    /**
     * Creates new form AdministrativeGUI
     */
    public AdministrativeGUI() {

        initComponents();

        this.getPropertiesFromFile();
        

    }

    private void getPropertiesFromFile(){
        try {
            this.input = new FileInputStream(configFile);
            props.load(input);
            input.close();
            
            ParkingFeeCalculatorStrategy configCalculator = (ParkingFeeCalculatorStrategy)Class.
                    forName(props.getProperty("feeCalculator")).newInstance();
            txtGarageName.setText(props.getProperty("garageName"));
            txtGarageStreet.setText(props.getProperty("garageStreet"));
            txtGarageCity.setText(props.getProperty("garageCity"));
            cboGarageState.setSelectedItem(props.getProperty("garageState"));
            txtGarageZip.setText(props.getProperty("garageZip"));
            cboCalculatorOptions.setSelectedItem(configCalculator);

        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(rootPane, fnfe.getMessage());
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(rootPane, ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(rootPane, cnfe.getMessage());
        } catch (InstantiationException ie) {
            JOptionPane.showMessageDialog(rootPane, ie.getMessage());
        } catch (IllegalAccessException iae) {
            JOptionPane.showMessageDialog(rootPane, iae.getMessage());
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

        btnSaveSettings = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtGarageName = new javax.swing.JTextField();
        txtGarageStreet = new javax.swing.JTextField();
        txtGarageCity = new javax.swing.JTextField();
        cboGarageState = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboCalculatorOptions = new javax.swing.JComboBox(getCalculators());
        jLabel6 = new javax.swing.JLabel();
        txtGarageZip = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(500, 200, 0, 0));

        btnSaveSettings.setText("Save");
        btnSaveSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSettingsActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Garage Settings and Maintenance", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        txtGarageName.setNextFocusableComponent(txtGarageStreet);
        txtGarageName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFieldFocusGained(evt);
            }
        });

        txtGarageStreet.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFieldFocusGained(evt);
            }
        });

        txtGarageCity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFieldFocusGained(evt);
            }
        });

        cboGarageState.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" }));

        jLabel1.setText("Company Name");

        jLabel2.setText("Street");

        jLabel3.setText("City");

        jLabel4.setText("State");

        jLabel5.setText("Zip Code");

        jLabel6.setText("Fee Calculator Model");

        txtGarageZip.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFieldFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(248, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboCalculatorOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGarageZip, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cboGarageState, 0, 100, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGarageCity, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(txtGarageStreet)
                            .addComponent(txtGarageName, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                    .addContainerGap(399, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtGarageZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboCalculatorOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(37, 37, 37)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addGap(21, 21, 21))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGarageName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGarageStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtGarageCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cboGarageState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(47, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(btnSaveSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaveSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSettingsActionPerformed
        props.setProperty("garageName", txtGarageName.getText());
        props.setProperty("garageStreet", txtGarageStreet.getText());
        props.setProperty("garageCity", txtGarageCity.getText());
        props.setProperty("garageState", cboGarageState.getSelectedItem().toString());
        props.setProperty("garageZip", txtGarageZip.getText());
        props.setProperty("feeCalculator", cboCalculatorOptions.getSelectedItem().getClass().getName());
        try {
            output = new FileOutputStream(configFile);
            props.store(output, null);
            output.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSaveSettingsActionPerformed

    private void txtFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFieldFocusGained

        if (evt.getComponent().equals(txtGarageName)) {
            txtGarageName.select(0, txtGarageName.getText().length());
        }
        else if (evt.getComponent().equals(txtGarageStreet)) {
            txtGarageStreet.select(0, txtGarageStreet.getText().length());
        }
        else if (evt.getComponent().equals(txtGarageCity)) {
            txtGarageCity.select(0, txtGarageCity.getText().length());
        }
        else if (evt.getComponent().equals(txtGarageZip)) {
            txtGarageZip.select(0, txtGarageZip.getText().length());
        }

    }//GEN-LAST:event_txtFieldFocusGained

    private Vector getCalculators() {
        File dir = new File("src/bag/parkinggaragepos");
        String[] files = dir.list();
        Vector calculators = new Vector<ParkingFeeCalculatorStrategy>();
        ParkingFeeCalculatorStrategy calculator = null;
        for (String s : files) {
            try {
                if (s.endsWith("Calculator.java")) {
                    s = "bag.parkinggaragepos."+s.substring(0, s.length()-5);
                    calculator = (ParkingFeeCalculatorStrategy) Class.forName(s).newInstance();
                    System.out.println(calculator.toString());
                    calculators.add(calculator);
                    //System.out.println(s);
                }
            } catch (ClassNotFoundException cnfe) {
                JOptionPane.showMessageDialog(rootPane, cnfe.getMessage());
            } catch (InstantiationException ie) {
                JOptionPane.showMessageDialog(rootPane, ie.getMessage());
            } catch (IllegalAccessException iae) {
                JOptionPane.showMessageDialog(rootPane, iae.getMessage());
            }
        }
        
        return calculators;

    }

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
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdministrativeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrativeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrativeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrativeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrativeGUI().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSaveSettings;
    private javax.swing.JComboBox cboCalculatorOptions;
    private javax.swing.JComboBox cboGarageState;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtGarageCity;
    private javax.swing.JTextField txtGarageName;
    private javax.swing.JTextField txtGarageStreet;
    private javax.swing.JTextField txtGarageZip;
    // End of variables declaration//GEN-END:variables
}