package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        ACalc calc = new ACalc();
        String[] temp_str = {"", "", ""};
        String str = "";
        Filework filew = new Filework();
        Scanner scan = new Scanner(System.in);

        DBwork db = new DBwork("base.sqlite");

        ArrayList<Number> num_list = new ArrayList<>();
        for (int i = 0; i < args.length / 3; i++) {
            num_list.add(new Number(Arrays.copyOfRange(args, i * 3, i * 3 + 3)));
        }
        for (Number n : num_list) {
            System.out.print(n);
            System.out.println(calc.toString(n));
        }

        CIR:
        do {
            System.out.printf("Введите имя файла и действие (r/w).%n" +
                    " add добавить. %n" +
                    "Calc для калькуляции. print показать.%n" +
                    "Generate Сгенерировать массив %n" +
                    "write_db  read_db truncate %n" +
                    " Exit выход) :");

            str = scan.next();
            System.out.println("Вы ввели действие: " + str);

            switch (str) {
                case "w":
                    System.out.print("Введите имя файла: ");
                    str = scan.next();
                    System.out.println("Вы ввели: " + str);

                    filew.write_Number(str, num_list);
                    break;
                case "r":
                    System.out.print("Введите имя файла: ");
                    str = scan.next();
                    System.out.println("Вы ввели имя файла: " + str);

                    for (String str_n : filew.read(str)) {
                        num_list.add(new Number(str_n));
                    }
                    break;
                case "print":
                    for (Number n : num_list) {
                        System.out.println(n);
                    }
                    break ;

                case "write_db":
                    db.write_Number(str, num_list);
                    break ;
                case "truncate":
                    db.truncate("Numbers");
                    break ;
                case "read_db":
                    for (String str_n : db.read(str)) {
                        num_list.add(new Number(str_n));
                    }
                    break ;

                case "Generate":
                    System.out.print("Количество записей: ");
                    int size = scan.nextInt();
                    System.out.print("Введите начало диапазона: ");
                    int from = scan.nextInt();
                    System.out.print("Введите конец дипазона: ");
                    int to = scan.nextInt();
                    num_list.addAll(Number.get_list(size, from, to));
                    break ;

                case "add" :
                    CIR_INPUT:
                    do {
                        System.out.println("Введите действие (+-*/ OR AND NAND и два числа. Exit выход) :");
                        for (int i = 0; i < 3; i++) {
                            System.out.print("Введите :");
                            temp_str[i] = scan.next();
                            System.out.println("Вы ввели : " + temp_str[i]);
                            if (Objects.equals(temp_str[i], "Exit")) {
                                break CIR_INPUT;
                            }
                        }
                        num_list.add(new Number(temp_str));
                        System.out.print(num_list.getLast());
                        System.out.println(calc.toString(num_list.getLast()));

                    } while (true);
                    break ;
                case "Exit":
                    break CIR;
                case "Calc":
                    for (Number n : num_list) {
                        System.out.print(n);
                        System.out.println(calc.toString(n));
                    }
                    break;
            }

        } while (true);
        db.close();
    }
}