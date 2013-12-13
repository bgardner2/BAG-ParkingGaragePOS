/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bag.parkinggaragepos;

import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

/**
 *This class is meant to be the main startup
 * window that a user sees.
 * @author Ben
 */
public class MainGUI extends javax.swing.JFrame {

    private final static String configFilePath = System.getProperty("user.home")
            + File.separatorChar + "BAG-ParkingGaragePOS" + File.separatorChar + "config.properties";
    private final static String dataFilePath = System.getProperty("user.home")
            + File.separatorChar + "BAG-ParkingGaragePOS" + File.separatorChar + "garageData.txt";
    private final static String setupConfigFileMessage = "There seems to be the first time you have run the program. "
                    + "Please setup your garage configuration.\nIf you need to make changes at a later date"
            + " it is located at: " + configFilePath;
    private boolean mainWindowVisibilty = true;

    /**
     * When this constructor is called it initialized components,
     * sets the look of the GUI, and sets up required files if necessary,
     */
    public MainGUI() {
        
        
        initComponents();
        
        this.setGUILook();

        this.setupRequiredFiles();
        this.setVisible(mainWindowVisibilty);

    }

    /**
     * This methods checks to see if the directories %SYSTEM USER
     * HOM%\BAG-ParkingGaragePOS\ exist, if not it will create them. Also it
     * will check to see if the files config.properties and garageData.txt exist
     * in that directory. If not it will create it and prepopulate the
     * config.properties with generic data
     */
    private void setupRequiredFiles() {
        List<String> filesToSetup = new ArrayList<>();
        filesToSetup.add(configFilePath);
        filesToSetup.add(dataFilePath);
        boolean needToSetupConfigFile = false;

        for (String s : filesToSetup) {
            File file = new File(s);
            if (file.exists()) {
                return;
            }
            else {
                needToSetupConfigFile = true;

                List<String> fileDirectories = new ArrayList<>();

                if (!file.exists()) {
                    File newFile = new File(file.getParent());
                    newFile.mkdirs();
                    try {
                        file.createNewFile();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        }

        this.populatePropertiesFile();

        if (needToSetupConfigFile) {
            JOptionPane.showMessageDialog(null, setupConfigFileMessage, "", JOptionPane.INFORMATION_MESSAGE);
            
            new AdministrativeGUI(this).setVisible(true);
            mainWindowVisibilty = false;
        }
    }

    /**
     * If necessary, this method will populate the config.properties file 
     * with all the necessary properties needed to run the program.
     * The user will at some point be required to set these properties for
     * their own garage.
     */
    private void populatePropertiesFile() {
        File configFile = new File(configFilePath);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(configFile)));
            pw.println("garageName=");
            pw.println("garageStreet=");
            pw.println("garageCity=");
            pw.println("garageState=");
            pw.println("garageZip=");

            pw.println("fileWriter=filesystem.TextFileWriter");
            pw.println("converter=filesystem.BensCustomGarageFormatConverter");
            pw.println("fileReader=filesystem.TextFileReader");
            pw.println("feeCalculator=bag.parkinggaragepos.BestValueFeeCalculator");

            pw.println("garageDataFilePath=\""+ dataFilePath + "\"");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                pw.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * This method attempts to set the look and feel of the program
     * to that of the native look of the user's machine.
     */
    private void setGUILook(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
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

        groupStartup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        radioAdmin = new javax.swing.JRadioButton();
        radioAttendence = new javax.swing.JRadioButton();
        btnStartProgram = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Garage Terminal 2000");
        setBounds(new java.awt.Rectangle(500, 200, 0, 0));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("How do you want to start the program?");

        groupStartup.add(radioAdmin);
        radioAdmin.setText("Administrative");

        groupStartup.add(radioAttendence);
        radioAttendence.setSelected(true);
        radioAttendence.setText("Garage Attendence");

        btnStartProgram.setText("Start Program");
        btnStartProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartProgramActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(radioAdmin)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(radioAttendence))
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(btnStartProgram)))
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioAdmin)
                    .addComponent(radioAttendence))
                .addGap(18, 18, 18)
                .addComponent(btnStartProgram)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This method will determine which radio button is checked on the
     * main screen and start up the proper window.
     * @param evt 
     */
    private void btnStartProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartProgramActionPerformed
        if (radioAdmin.isSelected()) {
            new AdministrativeGUI(this).setVisible(true);
            this.setVisible(false);
        }
        else if (radioAttendence.isSelected()) {
            new CheckInGUI(this).setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnStartProgramActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartProgram;
    private javax.swing.ButtonGroup groupStartup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton radioAdmin;
    private javax.swing.JRadioButton radioAttendence;
    // End of variables declaration//GEN-END:variables
}
