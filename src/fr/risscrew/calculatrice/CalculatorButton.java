package fr.risscrew.calculatrice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorButton extends JButton
{
    CalculatorButton(String text, CalculatorProgram prog)
    {
        super(text);
        Font font = new Font("Calibri",Font.PLAIN,48);
        setBackground(Color.decode("#222831"));
        setForeground(Color.decode("#ececec"));
        setFont(font);
        setFocusPainted(false);
        setFocusable(false);
        addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                prog.pressButton(e.getActionCommand().charAt(0));
            }
        });
    }
}
