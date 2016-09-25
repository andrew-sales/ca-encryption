/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.encryption;

import java.awt.event.ActionListener;

/**
 *
 * @author Andrew
 */
public class EncryptionView extends javax.swing.JPanel {

    /**
     * Creates new form EncryptionView
     */
    public EncryptionView() {
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

        jScrollPane10 = new javax.swing.JScrollPane();
        cipherTextAsHexTextArea = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        saveCipherTextToFileButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        plainTextAsTextArea = new javax.swing.JTextArea();
        jToolBar2 = new javax.swing.JToolBar();
        savePlainTextToFileButton = new javax.swing.JButton();
        loadPlainTextFromFileButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1129, 525));

        cipherTextAsHexTextArea.setColumns(20);
        cipherTextAsHexTextArea.setLineWrap(true);
        cipherTextAsHexTextArea.setRows(5);
        cipherTextAsHexTextArea.setToolTipText("");
        jScrollPane10.setViewportView(cipherTextAsHexTextArea);

        jLabel14.setText("Cipher Text as Hex");

        jLabel24.setText("Plain Text");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        saveCipherTextToFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/miniToolbarIcons/Disk.png"))); // NOI18N
        saveCipherTextToFileButton.setFocusable(false);
        saveCipherTextToFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveCipherTextToFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(saveCipherTextToFileButton);

        plainTextAsTextArea.setColumns(20);
        plainTextAsTextArea.setLineWrap(true);
        plainTextAsTextArea.setRows(5);
        plainTextAsTextArea.setText("Alice was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do: once or twice she had peeped into the book her sister was reading, but it had no pictures or conversations in it, 'and what is the use of a book,' thought Alice 'without pictures or conversations?'");
        plainTextAsTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(plainTextAsTextArea);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        savePlainTextToFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/miniToolbarIcons/Disk.png"))); // NOI18N
        savePlainTextToFileButton.setToolTipText("Save Plain Text to File");
        savePlainTextToFileButton.setFocusable(false);
        savePlainTextToFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        savePlainTextToFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(savePlainTextToFileButton);

        loadPlainTextFromFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/miniToolbarIcons/Folder.png"))); // NOI18N
        loadPlainTextFromFileButton.setToolTipText("Load Plain Text From File");
        loadPlainTextFromFileButton.setFocusable(false);
        loadPlainTextFromFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loadPlainTextFromFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(loadPlainTextFromFileButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(397, 397, 397)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea cipherTextAsHexTextArea;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton loadPlainTextFromFileButton;
    private javax.swing.JTextArea plainTextAsTextArea;
    private javax.swing.JButton saveCipherTextToFileButton;
    private javax.swing.JButton savePlainTextToFileButton;
    // End of variables declaration//GEN-END:variables


public void addLoadPlainTextFromFileButtonActionListener(ActionListener listenerForLoadPlainTextFromFileButton){  
    loadPlainTextFromFileButton.addActionListener(listenerForLoadPlainTextFromFileButton);    
}

public void addSavePlainTextToFileButtonActionListener(ActionListener listenerForSavePlainTextToFileButton){  
    savePlainTextToFileButton.addActionListener(listenerForSavePlainTextToFileButton);    
}
    
void addSaveCipherTextToFileButtonActionListener(ActionListener listenerForSaveCipherTextToFileButtonBlockButton){   
    saveCipherTextToFileButton.addActionListener(listenerForSaveCipherTextToFileButtonBlockButton);    
} 
    
public String getPlainTextAsTextArea () {   
    return plainTextAsTextArea.getText();
}

public void setPlainTextAsTextArea (String textValue) {    
    plainTextAsTextArea.setText(textValue);    
}

public String getCipherTextAsHexTextArea () {   
    return cipherTextAsHexTextArea.getText();    
}

public void setCipherTextAsHexTextArea (String textValue) {    
    cipherTextAsHexTextArea.setText(textValue);   
}

}
