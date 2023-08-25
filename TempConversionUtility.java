package info_aidtech;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TempConversionUtility extends JFrame {
    private JTextArea outputTextArea;

    public TempConversionUtility() {
        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);

        String[] temperatureScales = {"Celsius", "Fahrenheit", "Kelvin"};
        JComboBox<String> fromScaleComboBox = new JComboBox<>(temperatureScales);
        JComboBox<String> toScaleComboBox = new JComboBox<>(temperatureScales);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fromScale = (String) fromScaleComboBox.getSelectedItem();
                String toScale = (String) toScaleComboBox.getSelectedItem();

                String input = JOptionPane.showInputDialog("Enter temperature in " + fromScale + ":");
                if (input != null && !input.isEmpty()) {
                    double temperature = Double.parseDouble(input);

                    double result;
                    if (fromScale.equals("Celsius") && toScale.equals("Fahrenheit")) {
                        result = celsiusToFahrenheit(temperature);
                    } else if (fromScale.equals("Celsius") && toScale.equals("Kelvin")) {
                        result = celsiusToKelvin(temperature);
                    } else if (fromScale.equals("Fahrenheit") && toScale.equals("Celsius")) {
                        result = fahrenheitToCelsius(temperature);
                    } else if (fromScale.equals("Fahrenheit") && toScale.equals("Kelvin")) {
                        result = fahrenheitToKelvin(temperature);
                    } else if (fromScale.equals("Kelvin") && toScale.equals("Celsius")) {
                        result = kelvinToCelsius(temperature);
                    } else {
                        result = kelvinToFahrenheit(temperature);
                    }

                    outputTextArea.append(temperature + " " + fromScale + " is equal to " + result + " " + toScale + "\n");
                }
            }

			
        });

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("Convert from:"));
        inputPanel.add(fromScaleComboBox);
        inputPanel.add(new JLabel("Convert to:"));
        inputPanel.add(toScaleComboBox);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(convertButton, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        setTitle("Temperature Conversion Utility");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }
    
    public double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9 / 5 + 32;
    }

    public double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9 + 273.15;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TempConversionUtility().setVisible(true);
            }
        });
    }
}

