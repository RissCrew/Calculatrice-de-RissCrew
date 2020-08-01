package fr.risscrew.calculatrice;

import java.util.Arrays;
import java.util.List;

public class CalculatorProgram
{
    private static final List<Character> numbers = Arrays.asList('0','1','2','3','4','5','6','7','8','9');
    private static final List<Character> operators = Arrays.asList('+','-','*','/');
    private static final Holder number1 = new Holder(0D);
    private static final Holder number2 = new Holder(null);
    private static boolean decimalWriting = false;
    private static boolean toClear = false;
    private static long decimal;
    private static String toRender = "0";
    private static String history = "";
    private static Character operator = null;
    private final CalculatorPanel panel;


    public CalculatorProgram(CalculatorPanel panel)
    {
        this.panel = panel;
    }

    public void pressButton(char character)
    {
        if (numbers.contains(character))
        {
            if (number2.value == null)
            {
                if (toClear)
                {
                    number1.value = 0D;
                    toClear = false;
                }
                if (operator == null)
                {
                    editValue(number1, character);
                }
                else //Case start writing number 2
                {
                    number2.value = Double.parseDouble(String.valueOf(character));
                    toRender = getRenderOfNumber(number2.value);
                    decimal = 0L;
                    render();
                }
            }
            else
            {
                editValue(number2, character);
            }
        }
        else if (operators.contains(character))
        {
            decimalWriting = false;
            toClear = false;
            operator = character;
        }
        else if (character == '.')
        {
            toRender += '.';
            decimalWriting = true;
        }
        else if (character == '=')
        {
            if (operators.contains(operator) && number2.value != null)
            {
                history = ""+ getRenderOfResult(number1.value);
                switch (operator)
                {
                    case '+':
                        number1.value+=number2.value;
                        break;
                    case '-':
                        number1.value-=number2.value;
                        break;
                    case '*':
                        number1.value*=number2.value;
                        break;
                    case '/':
                        number1.value/=number2.value;
                        break;
                }
                history += " "+operator+" "+ getRenderOfResult(number2.value)+" = "+ getRenderOfResult(number1.value);
                number2.value = null;
                operator = null;
                decimalWriting = false;
                toRender = getRenderOfResult(number1.value);
                toClear = true;
                render();
                renderHistory();
            }
        }
        else if (character == '%')
        {
            if (number2.value == null)
            {
                if (number1.value != 0)
                {
                    number1.value /= 100;
                    toRender = getRenderOfResult(number1.value);
                }
            }
            else if (number2.value != 0)
            {
                number2.value /= 100;
                toRender = getRenderOfResult(number2.value);
            }
            render();
        }
        else if (character == '←')
        {
            if (number2.value == null)
            {
                if (number1.value == 0) return;
                if (!decimalWriting)
                {
                    String numToStr = String.format("%.0f", number1.value);
                    numToStr = numToStr.substring(0, numToStr.length()-1);
                    number1.value = numToStr.equals("") ? 0D :Double.parseDouble(numToStr);
                }
                else if (Long.toString(decimal).length() == 1)
                {
                    decimal = 0L;
                    decimalWriting = false;
                    number1.value = Double.parseDouble(Long.toString(number1.value.longValue()));
                }
                else
                {
                    String numToStr = Long.toString(decimal);
                    numToStr = numToStr.substring(0, numToStr.length()-1);
                    decimal = Long.parseLong(numToStr);
                    number1.value = Double.parseDouble(String.format("%.0f", number1.value)+"."+decimal);
                    toRender = getRenderOfNumber(number1.value);
                }
                toRender = getRenderOfNumber(number1.value);
            }
            else
            {
                if (number2.value == 0) return;
                if (!decimalWriting)
                {
                    String numToStr = String.format("%.0f", number2.value);
                    numToStr = numToStr.substring(0, numToStr.length()-1);
                    number2.value = numToStr.equals("") ? 0D :Double.parseDouble(numToStr);
                }
                else if (Long.toString(decimal).length() == 1)
                {
                    decimal = 0L;
                    decimalWriting = false;
                    number2.value = Double.parseDouble(Long.toString(number2.value.longValue()));
                }
                else
                {
                    String numToStr = Long.toString(decimal);
                    numToStr = numToStr.substring(0, numToStr.length()-1);
                    decimal = Long.parseLong(numToStr);
                    number2.value = Double.parseDouble(String.format("%.0f", number2.value)+"."+decimal);
                }
                toRender = getRenderOfNumber(number2.value);
            }
            render();
        }
        else if (character == 'C')
        {
            if (number2.value == null) number1.value = 0D;
            else number2.value = 0D;
            decimalWriting = false;
            decimal = 0;
            toRender = "0";
            render();
        }
        else if (character == '±')
        {
            if (number2.value == null)
            {
                number1.value = -number1.value;
                toRender = getRenderOfNumber(number1.value);
            }
            else
            {
                number2.value = -number2.value;
                toRender = getRenderOfNumber(number2.value);
            }
            render();
        }


    }

    private void render()
    {
        panel.updateText(toRender);
    }

    private void renderHistory()
    {
        panel.updateHistory(history);
    }

    private int getDecimalLength(double number)
    {
        return Math.min(String.valueOf(number).substring(String.valueOf(number).indexOf(".") + 1).length(), 9);
    }

    private String getRenderOfNumber(Double number)
    {
        String result;
        if(decimalWriting) result = String.format("%."+String.valueOf(decimal).length()+"f", number);
        else result = String.format("%.0f", number);
        return result;
    }

    private String getRenderOfResult(Double number)
    {
        return (number.longValue() == number)?String.format("%.0f", number):String.format("%."+ getDecimalLength(number)+"f", number);
    }

    static class Holder {
        Double value;
        Holder(Double value) {
            this.value = value;
        }
    }

    private void editValue(Holder number, char character)
    {
        if (!decimalWriting) //Writing integer part of number
        {
            if(!(character == '0' &&  number.value == 0D))//We don't want to add useless zeros
            {
                number.value = number.value*10+Double.parseDouble(String.valueOf(character));
                toRender = getRenderOfNumber(number.value);
                render();
            }
        }
        else //Writing decimal part of number
        {
            decimal = decimal*10+Long.parseLong(String.valueOf(character));
            number.value = Double.parseDouble(String.format("%.0f", number.value)+"."+decimal);
            toRender = getRenderOfNumber(number.value);
            render();
        }
    }


}
