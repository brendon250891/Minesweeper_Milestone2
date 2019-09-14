/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.UI;

import Minesweeper.UI.UITile;

/**
 *
 * @author brendon
 */
public class ApplicationView extends javax.swing.JFrame {
    private UITile[][] tiles;
    
    private UIHexagonalTile[][] hexTiles;

    /**
     * Creates new form ApplicationFrame
     */
    public ApplicationView() {
        initComponents();
        configureSettingsPanel();
    }
    
    private void configureSettingsPanel() {
        gameSettingsPanel.setVisible(false);
    }
    
    public void addRestartGameButtonEventHandler(java.awt.event.ActionListener actionListener) {
        restartGameButton.addActionListener(actionListener);
    }
    
    public void addGameSettingsMenuButtonEventHandler(java.awt.event.ActionListener actionListener) {
        gameSettingsMenuButton.addActionListener(actionListener);
    }
    
    public void addGameSettingsSaveButtonEventHandler(java.awt.event.ActionListener actionListener) {
        gameSettingsMenuSaveButton.addActionListener(actionListener);
    }
    
    public void addGameSettingsCancelButtonEventHandler(java.awt.event.ActionListener actionListener) {
        gameSettingsMenuCancelButton.addActionListener(actionListener);
    }
    
    public void toggleGameSettingsPanel() {
        boolean visible = gameSettingsPanel.isVisible();
        System.out.println(visible);
        gameSettingsPanel.setVisible(!visible);
        gameAreaContainer.setComponentZOrder(gameSettingsPanel, visible ? 1 : 0);
        toggleMinefield(visible);
    }
    
    public String getSelectedGameType() {
        return gameTypeComboBox.getSelectedItem().toString();
    }
    
    public String getSelectedGameDifficulty() {
        return difficultyComboBox.getSelectedItem().toString();
    }
    
    public void revealTile(int positionX, int positionY, String tileLabel) {
        tiles[positionX][positionY].setTileText(tileLabel);
        disableTile(tiles[positionX][positionY]);
    }
    
    public void toggleMinefield(boolean isInteractive) {
        for (UITile[] tileArrayOne : tiles) {
            for (UITile tile : tileArrayOne) {
                tile.setEnabled(isInteractive);
            }
        }
    }
    
    /**
     * Initialises the minefield with {@code UITiles}.
     * @param width - The width of the minefield.
     * @param height - The height of the minefield.
     * @param mouseListener - Mouse event listener to add to each {@code UITile}
     */
    public void initialiseSquareTileGrid(int width, int height, java.awt.event.MouseListener mouseListener) {
        minefieldPanel.removeAll();
        tiles = new UISquareTile[width][height];
        int tileWidth = (minefieldPanel.getWidth() / width) - 1;
        int tileHeight = (minefieldPanel.getHeight() / height) - 1;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                UITile tile = new UISquareTile(new java.awt.Point(x, y), tileWidth, tileHeight);
                tile.addMouseListener(mouseListener);
                tile.setPreferredSize(new java.awt.Dimension(tileWidth, tileHeight));
                tiles[x][y] = tile;
                minefieldPanel.add(tile);
            }
        }
        minefieldPanel.revalidate();
        minefieldPanel.repaint();
    }
    
    public void initialiseHexagonalTileGrid(int width, int height, java.awt.event.MouseListener mouseListener) {
        minefieldPanel.removeAll();
        tiles = new UIHexagonalTile[width][height];
        int tileWidth = (minefieldPanel.getWidth() / width) - 1;
        int tileHeight = (minefieldPanel.getHeight() / height) - 1;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                UIHexagonalTile tile = new UIHexagonalTile(new java.awt.Point(x, y), tileWidth, tileHeight, y % 2 == 0);
                tile.addMouseListener(mouseListener);
                tile.setPreferredSize(new java.awt.Dimension(tileWidth, tileHeight));
                tiles[x][y] = tile;
                minefieldPanel.add(tile);
            }
        }
        minefieldPanel.revalidate();
        minefieldPanel.repaint();
    }    

    public void minefieldTileWasEntered(UITile tile) {
        if (tile.isEnabled()) {
            tile.setBackgroundColor(TileColor.HOVER);
            tile.repaint();
        }
    }
    
    public void minefieldTileWasExited(UITile tile) {
        if (tile.isEnabled()) {
            tile.setBackgroundColor(TileColor.NORMAL);
            tile.repaint();
        }
    }
    
    public void disableTile(UITile tile) {
        tile.setEnabled(false);
        tile.setBackgroundColor(TileColor.CLICKED);
        tile.repaint();
    }
    
    public void minefieldTileLeftClicked(UITile tile) {
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        applicationPanel = new javax.swing.JPanel();
        titleBarPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        gameSettingsMenuButton = new javax.swing.JButton();
        restartGameButton = new javax.swing.JButton();
        gameAreaContainer = new javax.swing.JPanel();
        minefieldPanel = new javax.swing.JPanel();
        gameSettingsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        difficultyComboBox = new javax.swing.JComboBox<>();
        gameTypeComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        gameSettingsMenuCancelButton = new javax.swing.JButton();
        gameSettingsMenuSaveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(770, 900));
        setSize(new java.awt.Dimension(770, 900));

        applicationPanel.setBackground(new java.awt.Color(51, 51, 51));
        applicationPanel.setMaximumSize(new java.awt.Dimension(800, 800));
        applicationPanel.setMinimumSize(new java.awt.Dimension(800, 800));
        applicationPanel.setPreferredSize(new java.awt.Dimension(800, 800));
        applicationPanel.setSize(new java.awt.Dimension(800, 800));
        applicationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleBarPanel.setBackground(new java.awt.Color(9, 68, 109));
        titleBarPanel.setPreferredSize(new java.awt.Dimension(800, 65));
        titleBarPanel.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Score -");
        titleBarPanel.add(jLabel2);
        jLabel2.setBounds(310, 20, 90, 30);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("0");
        titleBarPanel.add(jLabel4);
        jLabel4.setBounds(410, 20, 20, 30);

        gameSettingsMenuButton.setText("Settings");
        titleBarPanel.add(gameSettingsMenuButton);
        gameSettingsMenuButton.setBounds(650, 20, 95, 29);

        restartGameButton.setText("Restart");
        titleBarPanel.add(restartGameButton);
        restartGameButton.setBounds(30, 20, 88, 29);

        applicationPanel.add(titleBarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, -1));

        gameAreaContainer.setBackground(new java.awt.Color(51, 51, 51));
        gameAreaContainer.setMaximumSize(new java.awt.Dimension(700, 700));
        gameAreaContainer.setMinimumSize(new java.awt.Dimension(700, 700));
        gameAreaContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        minefieldPanel.setBackground(new java.awt.Color(51, 51, 51));
        minefieldPanel.setPreferredSize(new java.awt.Dimension(700, 700));
        minefieldPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));
        gameAreaContainer.add(minefieldPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        gameSettingsPanel.setBackground(new java.awt.Color(51, 51, 51));
        gameSettingsPanel.setPreferredSize(new java.awt.Dimension(450, 250));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Game Settings");

        difficultyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Beginner", "Intermediate", "Expert" }));
        difficultyComboBox.setPreferredSize(new java.awt.Dimension(135, 30));

        gameTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular", "Hexagonal", "Colouring Problem" }));
        gameTypeComboBox.setPreferredSize(new java.awt.Dimension(173, 30));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Game Type:");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Difficulty:");

        gameSettingsMenuCancelButton.setText("Cancel");
        gameSettingsMenuCancelButton.setPreferredSize(new java.awt.Dimension(100, 29));

        gameSettingsMenuSaveButton.setText("Save");
        gameSettingsMenuSaveButton.setPreferredSize(new java.awt.Dimension(100, 29));

        javax.swing.GroupLayout gameSettingsPanelLayout = new javax.swing.GroupLayout(gameSettingsPanel);
        gameSettingsPanel.setLayout(gameSettingsPanelLayout);
        gameSettingsPanelLayout.setHorizontalGroup(
            gameSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameSettingsPanelLayout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gameSettingsPanelLayout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addGroup(gameSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(gameSettingsMenuCancelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(gameSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(difficultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gameTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(gameSettingsPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(gameSettingsMenuSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
        gameSettingsPanelLayout.setVerticalGroup(
            gameSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameSettingsPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(gameSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gameTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gameSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(difficultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(47, 47, 47)
                .addGroup(gameSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gameSettingsMenuSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gameSettingsMenuCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        gameAreaContainer.add(gameSettingsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 225, -1, -1));

        applicationPanel.add(gameAreaContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 900, 730));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(applicationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(applicationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel applicationPanel;
    private javax.swing.JComboBox<String> difficultyComboBox;
    private javax.swing.JPanel gameAreaContainer;
    private javax.swing.JButton gameSettingsMenuButton;
    private javax.swing.JButton gameSettingsMenuCancelButton;
    private javax.swing.JButton gameSettingsMenuSaveButton;
    private javax.swing.JPanel gameSettingsPanel;
    private javax.swing.JComboBox<String> gameTypeComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel minefieldPanel;
    private javax.swing.JButton restartGameButton;
    private javax.swing.JPanel titleBarPanel;
    // End of variables declaration//GEN-END:variables
}


