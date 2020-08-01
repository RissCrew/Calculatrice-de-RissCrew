package fr.risscrew.calculatrice;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CalculatorPanel extends JPanel
{
    private CalculatorButton button;
    private final CalculatorLabel textLabel = new CalculatorLabel("0");
    private final CalculatorHistory historyLabel = new CalculatorHistory("");
    CalculatorProgram prog = new CalculatorProgram(this);

    CalculatorPanel()
    {
        addKeyListener(new CalculatorKeyListener(prog));
        setFocusable(true);
        requestFocusInWindow();
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#222831"));
        initComponents();
    }

    private void initComponents()
    {
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = 0.2;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        this.add(historyLabel, c);



        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.add(textLabel, c);

        createRow(0,2, c, Arrays.asList("%", "←", "C"));
        createColumn(3,2, c,Arrays.asList("/","*","-","+","="));

        int number = 10;
        for(int i = 0; i < 3; i++)
        {
            for (int j = 2; j >= 0; j--)
            {
                number--;
                button = new CalculatorButton(Integer.toString(number), prog);
                c.gridx = j;
                c.gridy = i+3;
                this.add(button, c);
            }
        }

        createRow(0,6,c, Arrays.asList("±","0","."));

    }

    private void createRow(int x, int y, GridBagConstraints c, List<String> texts)
    {
        c.gridy = y;
        c.gridwidth = 1;
        for (int i = 0; i < texts.size(); i++)
        {
            String text = texts.get(i);
            button = new CalculatorButton(text, prog);
            c.gridx = x+i;
            this.add(button, c);
        }
    }

    private void createColumn(int x, int y, GridBagConstraints c, List<String> texts)
    {
        c.gridx = x;
        c.gridwidth = 1;
        for (int i = 0; i < texts.size(); i++)
        {
            String text = texts.get(i);
            button = new CalculatorButton(text, prog);
            c.gridy = i+y;
            this.add(button, c);
        }
    }

    public void updateText(String text)
    {
        textLabel.setText(text);
    }

    public void updateHistory(String text)
    {
        historyLabel.setText(text);
    }
}
