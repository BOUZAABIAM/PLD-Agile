package Vue;
import Modele.CalculTournee;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import Modele.Plan;
import Modele.DemandeLivraison;
import Modele.Intersection;
import Modele.Livraison;
import Modele.XMLParser;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carhiliuc
 */
public class IHMLivraisons extends javax.swing.JDialog {

    /**
     * Creates new form IHMLivraisons
     */
    public IHMLivraisons(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonChargerPlan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jFieldFichierPlan = new javax.swing.JTextPane();
        jButtonValider = new javax.swing.JButton();
        jButtonViderDL = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jFieldFichierLivraison = new javax.swing.JTextPane();
        jButtonChargerLivraison = new javax.swing.JButton();
        jButtonCalculerTournee = new javax.swing.JButton();
        jPanelPlanMap = new Vue.JPanelPlan();
        jButtonFeuilleDeRoute = new javax.swing.JButton();
        jLabelTitre = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableLivraisons = new javax.swing.JTable();
        jButtonModifier = new javax.swing.JButton();
        jButtonAnnulerModif = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jButtonChargerPlan.setText("Charger Plan");
        jButtonChargerPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChargerPlanActionPerformed(evt);
            }
        });

        jFieldFichierPlan.setEditable(false);
        jFieldFichierPlan.setBackground(javax.swing.UIManager.getDefaults().getColor("EditorPane.disabledBackground"));
        jFieldFichierPlan.setAutoscrolls(false);
        jFieldFichierPlan.setEnabled(false);
        jScrollPane1.setViewportView(jFieldFichierPlan);

        jButtonValider.setText("Valider");
        jButtonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderActionPerformed(evt);
            }
        });

        jButtonViderDL.setText("Vider Demande de Livraison");
        jButtonViderDL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViderDLActionPerformed(evt);
            }
        });

        jFieldFichierLivraison.setEditable(false);
        jFieldFichierLivraison.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.disabledBackground"));
        jFieldFichierLivraison.setAutoscrolls(false);
        jFieldFichierLivraison.setEnabled(false);
        jScrollPane3.setViewportView(jFieldFichierLivraison);

        jButtonChargerLivraison.setText("Charger Livraison");
        jButtonChargerLivraison.setEnabled(false);
        jButtonChargerLivraison.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChargerLivraisonActionPerformed(evt);
            }
        });

        jButtonCalculerTournee.setText("Calculer tournée");
        jButtonCalculerTournee.setEnabled(false);
        jButtonCalculerTournee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalculerTourneeActionPerformed(evt);
            }
        });

        jPanelPlanMap.setBackground(new java.awt.Color(192, 226, 151));

        javax.swing.GroupLayout jPanelPlanMapLayout = new javax.swing.GroupLayout(jPanelPlanMap);
        jPanelPlanMap.setLayout(jPanelPlanMapLayout);
        jPanelPlanMapLayout.setHorizontalGroup(
            jPanelPlanMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanelPlanMapLayout.setVerticalGroup(
            jPanelPlanMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        jButtonFeuilleDeRoute.setText("Generer feuille de route");
        jButtonFeuilleDeRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFeuilleDeRouteActionPerformed(evt);
            }
        });

        jLabelTitre.setFont(new java.awt.Font("Elephant", 0, 36)); // NOI18N
        jLabelTitre.setText(" Système de livraison");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Livraisons");

        jTableLivraisons.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Adresse", "Duree", "Debut Plage", "Fin Plage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableLivraisons);

        jButtonModifier.setText("Modifier");

        jButtonAnnulerModif.setText("Annuler");

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonChargerPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelPlanMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(jLabelTitre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonChargerLivraison, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonViderDL, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jButtonFeuilleDeRoute, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonCalculerTournee, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModifier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAnnulerModif)
                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelTitre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonChargerPlan))
                        .addGap(9, 9, 9)
                        .addComponent(jPanelPlanMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel1)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jButtonFeuilleDeRoute, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonChargerLivraison, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonCalculerTournee, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonModifier)
                                .addComponent(jButtonAnnulerModif))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonViderDL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void jButtonChargerPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChargerPlanActionPerformed
        JFileChooser xml_map = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
        xml_map.setFileFilter(filter);
        Plan plandDeVille = null;
        String exception="";
        JOptionPane jop; // fenetre d'alerte
        if (xml_map.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = xml_map.getSelectedFile();
            
            //Vérifier le format du fichier xml ou non
            if(filter.accept(selectedFile)==false){
                exception = "Format Fichier Plan Incorrect !";
                jFieldFichierPlan.setText(exception);
                jop = new JOptionPane();
                jop.showMessageDialog(null, exception, "Attention", JOptionPane.WARNING_MESSAGE);
                
                return;
            }
            
            jFieldFichierPlan.setText(selectedFile.getName());
            try {
                XMLParser parser = new XMLParser();
                plandDeVille = parser.getPlan(selectedFile);
            } catch (IOException | SAXException | ParserConfigurationException ex) {
                Logger.getLogger(IHMLivraisons.class.getName()).log(Level.SEVERE, null, ex);
            }
            annulerDL(); 
            planActuel = plandDeVille;
            jPanelPlanMap.setPlan(plandDeVille);
            jPanelPlanMap.repaint();
            jButtonChargerLivraison.setEnabled(true);
        }
        

    }//GEN-LAST:event_jButtonChargerPlanActionPerformed
    
    private void jButtonChargerLivraisonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChargerLivraisonActionPerformed
        if (planActuel != null){
            JFileChooser xml_DL = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
            xml_DL.setFileFilter(filter);
            DemandeLivraison dl = null;
            String exception="";
            JOptionPane jop; // fenetre d'alerte
            if (xml_DL.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = xml_DL.getSelectedFile();
                
                if(filter.accept(selectedFile)==false){
                   exception = "Format Fichier Livraison Incorrect !";
                   jFieldFichierPlan.setText(exception);
                   jop = new JOptionPane();
                   jop.showMessageDialog(null, exception, "Attention", JOptionPane.WARNING_MESSAGE);
                   return;
                }
                jFieldFichierLivraison.setText(selectedFile.getName());
                try {
                    XMLParser parser = new XMLParser();
                    dl = parser.getDL(selectedFile, planActuel);
                } catch (IOException | SAXException | ParserConfigurationException ex) {
                    Logger.getLogger(IHMLivraisons.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                DLActuelle = dl;    
                jPanelPlanMap.setPlan(planActuel);
                jPanelPlanMap.setDL(DLActuelle);
                jPanelPlanMap.repaint();
                
                annulerDL();
                jButtonCalculerTournee.setEnabled(true);
                
                Collection<Livraison> livraisoncollection = dl.getLivraison().values();
                int indexRow=0;
                jTableLivraisons.getColumnModel().getColumn(0).setPreferredWidth(200);
                jTableLivraisons.getColumnModel().getColumn(1).setPreferredWidth(80);
                jTableLivraisons.getColumnModel().getColumn(2).setPreferredWidth(50);
                jTableLivraisons.getColumnModel().getColumn(3).setPreferredWidth(50);
                for(Livraison livraison :livraisoncollection ){
                    
                    String nomRue = livraison.getAdresse().getTroncons().get(0).getNomRue();
                    Long idAdresse = livraison.getAdresse().getId();
                    jTableLivraisons.getModel().setValueAt(nomRue + " (" + idAdresse + ")", indexRow, 0);
                    
                    String dureeFormatee = "";
                    int seconds = livraison.getDuree() % 60;
                    int totalMinutes = livraison.getDuree() / 60;
                    int minutes = totalMinutes % 60;
                    int hours = totalMinutes / 60;
                    
                    if(hours >0){
                        dureeFormatee += hours + "h ";
                    }
                    if(minutes >0){
                        dureeFormatee += minutes + "min ";
                    }
                    if(seconds >0){
                        dureeFormatee += seconds + "s";
                    }
                    jTableLivraisons.getModel().setValueAt(dureeFormatee, indexRow, 1);
                    
                    if (livraison.getDebutPlage() != null){
                        jTableLivraisons.getModel().setValueAt(livraison.getDebutPlage().toString(), indexRow, 2);
                        jTableLivraisons.getModel().setValueAt(livraison.getFinPlage().toString(), indexRow, 3);
                    }
                    
                    indexRow++;
                }
            }  
        }
    }//GEN-LAST:event_jButtonChargerLivraisonActionPerformed
    
    private void jButtonCalculerTourneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalculerTourneeActionPerformed
//        java.util.List<Livraison> livraisons  = new ArrayList<Livraison>();
//        livraisons.addAll(DLActuelle.getLivraison().values());
//        
//        java.util.List<Intersection> intersections  = new ArrayList<Intersection>();
//        intersections.addAll(planActuel.getIntersection().values());
//        
//        CalculTournee calcul =  new CalculTournee(livraisons, intersections, DLActuelle.getEntrepot());
//        int[][] tab = calcul.graphLivraison();
//        int tpsLimite = 100000000;
//        int nbSommet = intersections.size()+1;
//        TSP tsp = new TSP1();
//        //A CHANGER
//        int[] duree = new int[nbSommet-1];
//        for (int i=0; i<nbSommet-1; i++){
//            duree[i] = 1;
//        }
//        tsp.chercheSolution(tpsLimite, nbSommet, tab, duree);
//        for (int j = 0; j<nbSommet, j++){
//            
//        }
        
    }//GEN-LAST:event_jButtonCalculerTourneeActionPerformed

    private void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonValiderActionPerformed

    private void annulerDL(){
        DefaultTableModel model = (DefaultTableModel) jTableLivraisons.getModel();
        int rowCount = model.getRowCount();
        String setvide="";
        for (int i = 0; i < rowCount ; i++){
            jTableLivraisons.getModel().setValueAt(setvide, i, 0);
            jTableLivraisons.getModel().setValueAt(setvide, i, 1);
            jTableLivraisons.getModel().setValueAt(setvide, i, 2);
            jTableLivraisons.getModel().setValueAt(setvide, i, 3);
                    
        }
        DLActuelle=null; 
        jButtonCalculerTournee.setEnabled(false);
    }
    private void jButtonViderDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViderDLActionPerformed
        annulerDL(); 
        jPanelPlanMap.setPlan(planActuel);
        jPanelPlanMap.setDL(DLActuelle);
        jPanelPlanMap.repaint();
    }//GEN-LAST:event_jButtonViderDLActionPerformed

    private void jButtonFeuilleDeRouteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFeuilleDeRouteActionPerformed

    }//GEN-LAST:event_jButtonFeuilleDeRouteActionPerformed

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
            java.util.logging.Logger.getLogger(IHMLivraisons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IHMLivraisons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IHMLivraisons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IHMLivraisons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IHMLivraisons dialog = new IHMLivraisons(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    private Plan planActuel;
    private DemandeLivraison DLActuelle;
    // /!\ IMPORTANT : changer private javax.swing.JPanel jPanelPlanMap; en private JPanelPlan jPanelPlanMap;
    // netBeans va essayer de le changer
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnnulerModif;
    private javax.swing.JButton jButtonCalculerTournee;
    private javax.swing.JButton jButtonChargerLivraison;
    private javax.swing.JButton jButtonChargerPlan;
    private javax.swing.JButton jButtonFeuilleDeRoute;
    private javax.swing.JButton jButtonModifier;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JButton jButtonViderDL;
    private javax.swing.JTextPane jFieldFichierLivraison;
    private javax.swing.JTextPane jFieldFichierPlan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelTitre;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private JPanelPlan jPanelPlanMap;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableLivraisons;
    // End of variables declaration//GEN-END:variables
}
