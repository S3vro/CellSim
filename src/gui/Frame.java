package gui;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Frame extends JFrame implements ActionListener {

    private Grid grid;
    private JPanel container;
    private JPanel controlPanel;
    private JPanel buttonPanel;
    private JPanel optionPanel;
    private JButton playButton;
    private JButton resetButton;
    private JSpinner stepSpinner;
    private JLabel stepSpinnerLabel;

    public Frame(){

        //build the ui
        grid = new Grid(800,800,10, 10, 3);

        container = new JPanel(new BorderLayout());
        controlPanel = new JPanel(new BorderLayout());

        playButton = new JButton("Start");
        playButton.setMnemonic(KeyEvent.VK_S);
        playButton.setActionCommand("start");
        playButton.addActionListener(this);

        resetButton = new JButton("Reset");
        resetButton.setMnemonic(KeyEvent.VK_R);
        resetButton.setActionCommand("reset");
        resetButton.addActionListener(this);

        SpinnerNumberModel stepSizeModel = new SpinnerNumberModel(250, 50, 1000, 50);
        stepSpinner = new JSpinner(stepSizeModel);
        stepSpinnerLabel = new JLabel("Time per Step (ms): ");
        stepSpinnerLabel.setLabelFor(stepSpinner);
        stepSpinnerLabel.setHorizontalAlignment(JLabel.RIGHT);

        buttonPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        buttonPanel.add(playButton);
        buttonPanel.add(resetButton);
        controlPanel.add(buttonPanel, BorderLayout.WEST);

        optionPanel = new JPanel(new GridLayout(1, 2, 0, 5));
        optionPanel.add(stepSpinnerLabel);
        optionPanel.add(stepSpinner);
        controlPanel.add(optionPanel,BorderLayout.CENTER);

        controlPanel.setPreferredSize(new Dimension(400,75));

        container.add(grid,BorderLayout.CENTER);
        container.add(controlPanel,BorderLayout.SOUTH);

        this.add(container);
        this.setResizable(false);
        this.pack();
    }

    //listen to the ui components and respond to user input
    public void actionPerformed(ActionEvent e){
        if("start".equals(e.getActionCommand())){

            //pathfinding is a long task. we need to run this in a worker
            //thread so we dont block the EDT.
            SwingWorker worker = new SwingWorker<Void,Void>(){
                protected Void doInBackground(){
                    return null;
                }
            };
            playButton.setEnabled(false);
            worker.execute();
        }

        if("reset".equals(e.getActionCommand())){
            playButton.setEnabled(true);
            this.grid.reset();
        }

    }
}

