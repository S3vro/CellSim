package model.gui;


import implementations.Wireworld;
import model.algorithm.Algorithm;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.swing.*;


public class Frame extends JFrame implements ActionListener {

    private static final String FILE_EXT = ".cell";
    private final Grid grid;
    private final JPanel container;
    private final JPanel controlPanel;
    private final JPanel buttonPanel;
    private final JPanel optionPanel;
    private final JButton playButton;
    private final JButton resetButton;
    private final JButton saveButton;
    private final JButton loadButton;
    private final JButton runButton;
    private final JButton stopButton;

    private final JSpinner stepSpinner;
    private final JLabel stepSpinnerLabel;
    private ScheduledExecutorService currentService;

    public Frame(Algorithm algorithm){

        grid = new Grid(800,800,7,7, algorithm);

        container = new JPanel(new BorderLayout());
        controlPanel = new JPanel(new BorderLayout());

        playButton = new JButton("Step");
        playButton.setMnemonic(KeyEvent.VK_S);
        playButton.setActionCommand("start");
        playButton.addActionListener(this);

        resetButton = new JButton("Reset");
        resetButton.setMnemonic(KeyEvent.VK_R);
        resetButton.setActionCommand("reset");
        resetButton.addActionListener(this);

        loadButton = new JButton("Load");
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);

        saveButton = new JButton("Save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        runButton = new JButton("Run");
        runButton.setActionCommand("run");
        runButton.addActionListener(this);

        stopButton = new JButton("Stop");
        stopButton.setActionCommand("stop");
        stopButton.addActionListener(this);
        stopButton.setEnabled(false);

        SpinnerNumberModel stepSizeModel = new SpinnerNumberModel(250, 50, 1000, 50);
        stepSpinner = new JSpinner(stepSizeModel);
        stepSpinnerLabel = new JLabel("Time per Step (ms): ");
        stepSpinnerLabel.setLabelFor(stepSpinner);
        stepSpinnerLabel.setHorizontalAlignment(JLabel.RIGHT);

        buttonPanel = new JPanel(new GridLayout(2, 3, 0, 10));
        buttonPanel.add(playButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(runButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(stopButton);
        controlPanel.add(buttonPanel, BorderLayout.WEST);

        optionPanel = new JPanel(new GridLayout(1, 2, 0, 5));
        optionPanel.add(stepSpinnerLabel);
        optionPanel.add(stepSpinner);
        controlPanel.add(optionPanel, BorderLayout.CENTER);

        controlPanel.setPreferredSize(new Dimension(400,75));

        container.add(grid,BorderLayout.CENTER);
        container.add(controlPanel,BorderLayout.SOUTH);

        this.add(container);
        this.setResizable(false);
        this.pack();
    }

    public void actionPerformed(ActionEvent e){
        if("start".equals(e.getActionCommand())){
            grid.step();
        }

        if("reset".equals(e.getActionCommand())){
            playButton.setEnabled(true);
            this.grid.reset();
        }

        if("load".equals(e.getActionCommand())) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                if (file.getName().endsWith(FILE_EXT)) {
                    try {
                        List<String> content = Files.readAllLines(file.toPath());
                        List<List<Integer>> states = new ArrayList<>();
                        for (String s : content) {
                            List<Integer> values = Arrays.stream(s.split(" ")).map(Integer::valueOf).toList();
                            Collections.addAll(states, values);
                        }
                        this.grid.load(states);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }

        if("save".equals(e.getActionCommand())) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showSaveDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                this.grid.save(file);
            }
        }

        if ("run".equals(e.getActionCommand())) {
            this.runButton.setEnabled(false);
            this.saveButton.setEnabled(false);
            this.resetButton.setEnabled(false);
            this.playButton.setEnabled(false);
            this.stopButton.setEnabled(true);
            this.loadButton.setEnabled(false);
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            this.currentService = executorService;
            executorService.scheduleAtFixedRate(grid::step,0,(Integer) stepSpinner.getValue(), TimeUnit.MILLISECONDS);
        }

        if ("stop".equals(e.getActionCommand())) {
            this.runButton.setEnabled(true);
            this.saveButton.setEnabled(true);
            this.resetButton.setEnabled(true);
            this.playButton.setEnabled(true);
            this.stopButton.setEnabled(false);
            this.loadButton.setEnabled(true);
            this.currentService.shutdownNow();
        }
    }
}

