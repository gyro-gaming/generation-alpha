package com.generation_alpha.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen {

    JFrame window;
    ImageIcon backgroundImage;
    Container container;
    JPanel titleNamePanel, startButtonPanel, instructionsButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    JLabel titleNameLabel, hpLabel, hpLabelNumber, inventoryLabel, inventoryLabelName, myLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    JButton startButton, instructionsButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    int playerHP;
    String inventory;

    TitleScreenHandler tsHandler = new TitleScreenHandler();


    public static void main(String[] args) {
        new SplashScreen();
    }
    public SplashScreen() {

        backgroundImage = new ImageIcon("resources/backgroundImage.png");
        myLabel = new JLabel(backgroundImage);
        myLabel.setSize(800, 600);
        window = new JFrame("Generation Alpha");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        container = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(150, 100, 500, 100);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("Generation Alpha");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        instructionsButtonPanel = new JPanel();
        instructionsButtonPanel.setBounds(250, 350, 280, 100);
        instructionsButtonPanel.setBackground(Color.black);

        instructionsButton = new JButton("INSTRUCTIONS");
        instructionsButton.setBackground(Color.black);
        instructionsButton.setForeground(Color.black);
        instructionsButton.setFont(normalFont);
//        instructionsButton.addActionListener(tsHandler);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(350, 400, 150, 50);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.black);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        titleNamePanel.add(titleNameLabel);
        instructionsButtonPanel.add(instructionsButton);
        startButtonPanel.add(startButton);

        container.add(titleNamePanel);
        container.add(instructionsButtonPanel);
        container.add(startButtonPanel);
        container.add(myLabel);
    }

    public void createGameScreen() {

        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        instructionsButtonPanel.setVisible(false);
        myLabel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        container.add(mainTextPanel);

        mainTextArea = new JTextArea("This is the main text area. This game is going to be great. I'm sure of it!!");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        container.add(choiceButtonPanel);

        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.black);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choiceButtonPanel.add(choice1);

        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.black);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choiceButtonPanel.add(choice2);

        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.black);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choiceButtonPanel.add(choice3);

        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.black);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choiceButtonPanel.add(choice4);

        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        container.add(playerPanel);
        hpLabel = new JLabel("HP:");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);
        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);
        inventoryLabel = new JLabel("Inventory:");
        inventoryLabel.setFont(normalFont);
        inventoryLabel.setForeground(Color.white);
        playerPanel.add(inventoryLabel);
        inventoryLabelName = new JLabel();
        inventoryLabelName.setFont(normalFont);
        inventoryLabelName.setForeground(Color.white);
        playerPanel.add(inventoryLabelName);

        playerSetup();

    }
    public void playerSetup() {
        playerHP = 15;
        inventory = "Knife";
        inventoryLabelName.setText(inventory);
        hpLabelNumber.setText("" + playerHP);

        navigate();
    }

    public void navigate() {
        mainTextArea.setText("You are here. \nWhere do you want to go next?");

        choice1.setText("go north");
        choice2.setText("go east");
        choice3.setText("go south");
        choice4.setText("go west");
    }

    public class TitleScreenHandler implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            createGameScreen();
        }
    }

}