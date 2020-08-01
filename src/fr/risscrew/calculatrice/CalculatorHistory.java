package fr.risscrew.calculatrice;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculatorHistory extends JLabel
{
    CalculatorHistory(String text)
    {
        super(text);
        Font font = new Font("Calibri",Font.PLAIN,20);
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
