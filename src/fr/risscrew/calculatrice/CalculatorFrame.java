package fr.risscrew.calculatrice;


import javax.swing.*;
import java.util.Arrays;
import java.util.List;

class CalculatorFrame extends JFrame
{

    CalculatorFrame()
    {
        setTitle("Calculatrice");
        setSize(500,714);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        getContentPane().add(new CalculatorPanel());

        setVisible(true);

    }

}
