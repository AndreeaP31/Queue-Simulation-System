package GUI;

import businessLogic.SelectionPolicy;
import businessLogic.SimulationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame {

    private JTextField nrClientsField;
    private JTextField maxArrivalField;
    private JTextField minArrivalField;
    private JTextField minServiceField;
    private JTextField maxServiceField;
    private JTextArea textArea1;
    private JTextField timeField;
    private JComboBox strategyComboBox;
    private JTextField nrQueuesField;
    private JButton submitButton;
    private JTextField timeLimitField;
    private JPanel JPanel1;


    public int getNrClientsFields() {
        return Integer.parseInt(nrClientsField.getText());
    }
    public int getNrQueuesFields() {
        return Integer.parseInt(nrQueuesField.getText());
    }
    public int getTimeLimitField() {
        return Integer.parseInt(timeLimitField.getText());
    }
    public int getMaxArrivalField() {
        return Integer.parseInt(maxArrivalField.getText());
    }
    public int getMinArrivalField() {
        return Integer.parseInt(minArrivalField.getText());
    }
    public int getMinServiceField() {
        return Integer.parseInt(minServiceField.getText());
    }
    public int getMaxServiceField() {
        return Integer.parseInt(maxServiceField.getText());
    }
    public SimulationFrame() {
        strategyComboBox.addItem("SHORTEST_QUEUE");
        strategyComboBox.addItem("SHORTEST_TIME");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SimulationManager gen = new SimulationManager(SimulationFrame.this);
                Thread t = new Thread(gen);
                t.start();
            }
        });
    }
    public void updateView(String text, int time){
        this.textArea1.setText(text);
        this.timeField.setText(Integer.toString(time));
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tema 2");
        frame.setContentPane(new SimulationFrame().JPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 300);
    }

    public JComboBox getStrategyComboBox() {
        return strategyComboBox;
    }

    public void setStrategyComboBox(JComboBox strategyComboBox) {
        this.strategyComboBox = strategyComboBox;
    }
}

