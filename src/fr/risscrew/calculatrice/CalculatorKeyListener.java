package fr.risscrew.calculatrice;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;

public class CalculatorKeyListener implements KeyListener
{
    private static final List<Character> keys = Arrays.asList('0','1','2','3','4','5','6','7','8','9','+','-','*','/','=','ù','%',',','.',(char)8, (char)10, (char)127);
    private final CalculatorProgram prog;

    CalculatorKeyListener(CalculatorProgram prog)
    {
        this.prog = prog;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        char key = e.getKeyChar();
        if (keys.contains(key))
        {
            switch (key)
            {
                case (char)8:
                    key = '←';
                    break;
                case (char)10:
                    key = '=';
                    break;
                case (char)127:
                    key = 'C';
                    break;
                case 'ù':
                    key = '%';
                    break;
                case ',':
                    key = '.';
                    break;
            }
            prog.pressButton(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
