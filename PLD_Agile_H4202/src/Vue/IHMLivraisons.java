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
import Modele.Troncon;
import Modele.XMLParser;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import tsp.*;
 
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
        jButtonValider = new javax.swing.JButton();
        jButtonViderDL = new javax.swing.JButton();
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
        jButtonAjouter = new javax.swing.JButton();
        jButtonSupprimer = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaMessage = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
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

        jButtonValider.setVisible(false);
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

        jPanelPlanMap.setBackground(new java.awt.Color(153, 153, 153));
        jPanelPlanMap.setPreferredSize(new java.awt.Dimension(551, 530));

        javax.swing.GroupLayout jPanelPlanMapLayout = new javax.swing.GroupLayout(jPanelPlanMap);
        jPanelPlanMap.setLayout(jPanelPlanMapLayout);
        jPanelPlanMapLayout.setHorizontalGroup(
            jPanelPlanMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );
        jPanelPlanMapLayout.setVerticalGroup(
            jPanelPlanMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        jButtonFeuilleDeRoute.setText("Generer feuille de route");
        jButtonFeuilleDeRoute.setEnabled(false);
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "N°", "Adresse", "Duree", "Debut Plage", "Fin Plage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLivraisons.getColumnModel().getColumn(1).setPreferredWidth(10);
        jTableLivraisons.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTableLivraisons.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTableLivraisons.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTableLivraisons.getColumnModel().getColumn(4).setPreferredWidth(50);
        jScrollPane4.setViewportView(jTableLivraisons);

        jButtonModifier.setEnabled(false);
        jButtonModifier.setText("Modifier");
        jButtonModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierActionPerformed(evt);
            }
        });

        jButtonAnnulerModif.setVisible(false);
        jButtonAnnulerModif.setText("Annuler");
        jButtonAnnulerModif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnnulerModifActionPerformed(evt);
            }
        });

        jButtonAjouter.setVisible(false);
        jButtonAjouter.setText("Ajouter");
        jButtonAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterActionPerformed(evt);
            }
        });

        jButtonSupprimer.setVisible(false);
        jButtonSupprimer.setText("Supprimer");
        jButtonSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerActionPerformed(evt);
            }
        });

        jTextAreaMessage.setEditable(false);
        jTextAreaMessage.setColumns(20);
        jTextAreaMessage.setRows(5);
        jScrollPane2.setViewportView(jTextAreaMessage);

        jLabel2.setText("Messages");

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
                .addGap(322, 322, 322)
                .addComponent(jLabelTitre)
                .addGap(158, 158, 158)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(654, 654, 654)
                                .addComponent(jButtonFeuilleDeRoute, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(jButtonViderDL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonCalculerTournee, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jButtonModifier, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSupprimer)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jButtonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAnnulerModif)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelPlanMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonChargerPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonChargerLivraison, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(238, 238, 238))))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTitre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonChargerPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonChargerLivraison, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addComponent(jPanelPlanMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jButtonViderDL, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonFeuilleDeRoute, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCalculerTournee, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonModifier, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAnnulerModif, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                jop = new JOptionPane();
                jop.showMessageDialog(null, exception, "Attention", JOptionPane.WARNING_MESSAGE);
                
                return;
            }
            
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
                   jop = new JOptionPane();
                   jop.showMessageDialog(null, exception, "Attention", JOptionPane.WARNING_MESSAGE);
                   return;
                }
                try {
                    XMLParser parser = new XMLParser();
                    dl = parser.getDL(selectedFile, planActuel);
                } catch (IOException | SAXException | ParserConfigurationException ex) {
                    Logger.getLogger(IHMLivraisons.class.getName()).log(Level.SEVERE, null, ex);
                }
                annulerDL();
                
                DLActuelle = dl; 
                planActuel.setDL(dl);
                jPanelPlanMap.setPlan(planActuel);
                jPanelPlanMap.setDL(DLActuelle);
                jPanelPlanMap.repaint();
                
                jButtonCalculerTournee.setEnabled(true);
                jButtonFeuilleDeRoute.setEnabled(true);
                
                Collection<Livraison> livraisoncollection = dl.getLivraison().values();
                
                jTableLivraisons.getModel().setValueAt("E", 0, 0);
                jTableLivraisons.getModel().setValueAt(
                        dl.getEntrepot().getTroncons().get(0).getNomRue() + 
                                " (" + dl.getEntrepot().getId() + ")", 0, 1);
                
                int indexRow=1;
                for(Livraison livraison :livraisoncollection ){
                    jTableLivraisons.getModel().setValueAt(indexRow, indexRow, 0);
                    String nomRue = livraison.getAdresse().getTroncons().get(0).getNomRue();
                    Long idAdresse = livraison.getAdresse().getId();
                    jTableLivraisons.getModel().setValueAt(nomRue + " (" + idAdresse + ")", indexRow, 1);
                    
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
                    jTableLivraisons.getModel().setValueAt(dureeFormatee, indexRow, 2);
                    
                    if (livraison.getDebutPlage() != null){
                        jTableLivraisons.getModel().setValueAt(livraison.getDebutPlage().toString(), indexRow, 3);
                        jTableLivraisons.getModel().setValueAt(livraison.getFinPlage().toString(), indexRow, 4);
                    }
                    
                    indexRow++;
                }
            }  
        }
    }//GEN-LAST:event_jButtonChargerLivraisonActionPerformed
    
    private void jButtonCalculerTourneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalculerTourneeActionPerformed
      
      
        //Obtenir la solution en intersection
        planActuel.calculSolutionTSP1();
        List<Intersection> chemin = planActuel.getChemin();
        List<Intersection> sol = planActuel.getSolution();
        
//        Intersection un = planActuel.getIntersectionsMap().get((long)1);
//        Intersection deux = planActuel.getIntersectionsMap().get((long)2);
//        Intersection trois = planActuel.getIntersectionsMap().get((long)3);
//        Intersection quatre = planActuel.getIntersectionsMap().get((long)4);
//        Intersection cinq = planActuel.getIntersectionsMap().get((long)5);
//        Intersection six = planActuel.getIntersectionsMap().get((long)6);
        
//        List<Intersection>inter = new ArrayList<Intersection>();
//        inter.add(un);
//        inter.add(quatre);
//        inter.add(cinq);
//        inter.add(six);
//        inter.add(trois);
//        inter.add(deux);
//        inter.add(un);
//        Intersection[] sol = new Intersection[3];
//        sol[0] = un;
//        sol[1] = cinq;
//        sol[2] = six;
//        cheminActuel = inter;
//        //entrepot est l'intersection de depart et d'arrive
//        solutionActuelle = sol;
//        //entrepot apparait qu'une fois
        
        // Affichage de la solution
        jPanelPlanMap.setSolution(sol);
        jPanelPlanMap.setChemin(chemin);
        jPanelPlanMap.repaint();
        
                
                
                
        //réorganise le tableau
        DefaultTableModel model = (DefaultTableModel) jTableLivraisons.getModel();
        int rowCount = model.getRowCount();
        String setvide="";
        for (int i = 0; i < rowCount ; i++){
            jTableLivraisons.getModel().setValueAt(setvide, i, 0);
            jTableLivraisons.getModel().setValueAt(setvide, i, 1);
            jTableLivraisons.getModel().setValueAt(setvide, i, 2);
            jTableLivraisons.getModel().setValueAt(setvide, i, 3);
            jTableLivraisons.getModel().setValueAt(setvide, i, 4);
                    
        }
        int indexRow=0;
        System.out.println(DLActuelle);
        for(Intersection intersection : sol ){
            Livraison livraison = DLActuelle.getLivraison().get(intersection.getId());
            
            if(livraison == null){
                jTableLivraisons.getModel().setValueAt("E", indexRow, 0);
                jTableLivraisons.getModel().setValueAt(
                        intersection.getTroncons().get(0).getNomRue() + 
                                " (" + intersection.getId() + ")", 0, 1);
            }else{
                jTableLivraisons.getModel().setValueAt(indexRow, indexRow, 0);
                String nomRue = livraison.getAdresse().getTroncons().get(0).getNomRue();
                Long idAdresse = livraison.getAdresse().getId();
                jTableLivraisons.getModel().setValueAt(nomRue + " (" + idAdresse + ")", indexRow, 1);

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
                jTableLivraisons.getModel().setValueAt(dureeFormatee, indexRow, 2);

                if (livraison.getDebutPlage() != null){
                    jTableLivraisons.getModel().setValueAt(livraison.getDebutPlage().toString(), indexRow, 3);
                    jTableLivraisons.getModel().setValueAt(livraison.getFinPlage().toString(), indexRow, 4);
                }
            }
            indexRow++;
        }

        
        jButtonModifier.setEnabled(true);
        jButtonAjouter.setVisible(true);
        jButtonAjouter.setEnabled(false);
        jButtonSupprimer.setVisible(true);
        jButtonSupprimer.setEnabled(false);
        jButtonValider.setVisible(true);
        jButtonValider.setEnabled(false);
        jButtonAnnulerModif.setVisible(true);
        jButtonAnnulerModif.setEnabled(false);

    }//GEN-LAST:event_jButtonCalculerTourneeActionPerformed

    private void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderActionPerformed
        jButtonAjouter.setEnabled(false);
        jButtonSupprimer.setEnabled(false);
        jButtonValider.setEnabled(false);
        jButtonAnnulerModif.setEnabled(false);
        jButtonFeuilleDeRoute.setEnabled(true);

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
            jTableLivraisons.getModel().setValueAt(setvide, i, 4);
                    
        }
        DLActuelle=null;
        solutionActuelle=null;
        jButtonCalculerTournee.setEnabled(false);
         
    }
    private void jButtonViderDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViderDLActionPerformed
        annulerDL(); 
        jPanelPlanMap.setPlan(planActuel);
        jPanelPlanMap.setDL(DLActuelle);
        jPanelPlanMap.setSolution(solutionActuelle);
        jPanelPlanMap.repaint();
    }//GEN-LAST:event_jButtonViderDLActionPerformed

    private void jButtonFeuilleDeRouteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFeuilleDeRouteActionPerformed
//                try {
//            Document doc = new Document();
//            PdfWriter.getInstance(doc, new FileOutputStream("table.pdf"));
//            doc.open();
//            PdfPTable pdfTable = new PdfPTable(jTableLivraisons.getColumnCount());
//            //adding table headers
//            for (int i = 0; i < jTableLivraisons.getColumnCount(); i++) {
//                pdfTable.addCell(jTableLivraisons.getColumnName(i));
//            }
//            //extracting data from the JTable and inserting it to PdfPTable
//            for (int rows = 0; rows < jTableLivraisons.getRowCount() - 1; rows++) {
//                for (int cols = 0; cols < jTableLivraisons.getColumnCount(); cols++) {
//                    pdfTable.addCell(jTableLivraisons.getModel().getValueAt(rows, cols).toString());
//
//                }
//            }
//            doc.add(pdfTable);
//            doc.close();
//            System.out.println("done");
//        } catch (DocumentException ex) {
//        } catch (FileNotFoundException ex) 
//        }
//
//     
    }//GEN-LAST:event_jButtonFeuilleDeRouteActionPerformed

    private void jButtonAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAjouterActionPerformed

    private void jButtonSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSupprimerActionPerformed

    private void jButtonModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierActionPerformed
        jButtonAjouter.setEnabled(true);
        jButtonSupprimer.setEnabled(true);
        jButtonValider.setEnabled(true);
        jButtonAnnulerModif.setEnabled(true);
        jButtonFeuilleDeRoute.setEnabled(false);
        
    }//GEN-LAST:event_jButtonModifierActionPerformed

    private void jButtonAnnulerModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerModifActionPerformed
        jButtonAjouter.setEnabled(false);
        jButtonSupprimer.setEnabled(false);
        jButtonValider.setEnabled(false);
        jButtonAnnulerModif.setEnabled(false);
        jButtonFeuilleDeRoute.setEnabled(true);
    }//GEN-LAST:event_jButtonAnnulerModifActionPerformed

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
    private List<Intersection> solutionActuelle;
    private List<Intersection>cheminActuel;
    // /!\ IMPORTANT : changer private javax.swing.JPanel jPanelPlanMap; en private JPanelPlan jPanelPlanMap;
    // netBeans va essayer de le changer
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjouter;
    private javax.swing.JButton jButtonAnnulerModif;
    private javax.swing.JButton jButtonCalculerTournee;
    private javax.swing.JButton jButtonChargerLivraison;
    private javax.swing.JButton jButtonChargerPlan;
    private javax.swing.JButton jButtonFeuilleDeRoute;
    private javax.swing.JButton jButtonModifier;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JButton jButtonViderDL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelTitre;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private JPanelPlan jPanelPlanMap;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableLivraisons;
    private javax.swing.JTextArea jTextAreaMessage;
    // End of variables declaration//GEN-END:variables
}
