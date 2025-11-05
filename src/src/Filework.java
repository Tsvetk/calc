package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Filework implements IWorkResult {

    @Override
    public void write(String fileName, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(data);
            writer.close();
        } catch (IOException er) {
            System.out.println("File not found : " + fileName);
        }
    }

    @Override
    public ArrayList<String> read(String fileName) {
        ArrayList<String> line_str = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                line_str.add(line);
            }
        } catch (FileNotFoundException err) {
            System.out.println("File not found : " + fileName);
        } catch (IOException err) {
            System.out.println("File не доступен : " + fileName);
        } finally {
            if ( reader != null ) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Не могу закрыть файл " + fileName);
                }
            }
        }
        return line_str;
    }

    public void write_Number(String fileName, Number num)  {
        String str = num.a + " " + num.b + " " + num.act;
        System.out.println(str);
        this.write(fileName, str);
    }

    public void write_Number(String fileName, List<Number> num) {
        ArrayList<String> list_str = new ArrayList<>();
        for (Number item : num) {
            list_str.add(item.a + " " + item.b + " " + item.act);
        }
        this.write(fileName, String.join(String.format("%n"), list_str));
    }
}
