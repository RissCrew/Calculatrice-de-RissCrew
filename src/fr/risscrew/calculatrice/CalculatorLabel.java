package fr.risscrew.calculatrice;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorLabel extends JLabel
{
    CalculatorLabel(String text)
    {
        super(text);
        Font font = new Font("Calibri",Font.PLAIN,72);
        setBorder(new EmptyBorder(0,0,0,10));
        setForeground(Color.decode("#ececec"));
        setFont(font);
        setFocusable(false);
        setHorizontalAlignment(SwingConstants.RIGHT);
    }

    @Override
    public void setText(String text)
    {
        super.setText(text);
    }

    @Override
    public String getText()
    {
        return super.getText();
    }
}
