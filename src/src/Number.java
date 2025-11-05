package src;

import java.util.ArrayList;


public class Number {
    private static String[] bin_list = {"OR","AND","NAND"};
    private static String[] act_list = {"/","*","-","+","OR","AND","NAND"};
    public double a = Double.NaN;
    public double b = Double.NaN;
    public String act = "";

    @Override
    public String toString() {
        if (this.act.equals("OR") || this.act.equals("AND") ||this.act.equals("NAND") )
        {
            return "Number a : " + Integer.toBinaryString((int)this.a) +
                   " Number b : " + Integer.toBinaryString((int)this.b) + " : action : " + this.act;
        }
        else {
            return "Number a : " + this.a + " Number b : " + this.b + " : action : " + this.act;
        }
    }

    public static ArrayList<Number> get_list(int size, int from, int to ){
        ArrayList<Number> temp = new ArrayList<>();
        for (int i = 0 ; i < size; i++)
        {
            temp.add(new Number(from, to));
        }
        return temp;
    }

    public Number(int from, int to){
        this.a = (Math.random() * (to - from)) + from;
        this.b = (Math.random() * (to - from)) + from;
        this.act = act_list[(int)(Math.random() * act_list.length)] ;
    }
    public Number (String str){
        this(str.split(" "));
    }

    public Number(String[] str_list) {
        double temp = 0;
        for (String str : str_list) {
            try {
                temp = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                act = str;
                continue;
            }
            if (Double.isNaN(this.a)) {
                this.a = temp;
            } else {
                this.b = temp;
            }
        }

    }
}
