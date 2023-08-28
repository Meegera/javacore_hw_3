package files_3.task_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class CreateMain {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        createDir("//src//main");
        createDir("//src//test");
        createDir("//res//drawables");
        createDir("//res//vectors");
        createDir("//res//icons");
        createDir("//savegames");
        createDir("//temp");

        createFile("//src//main//Main.java");
        createFile("//src//main//Utils.java");
        createFile("//temp//temp.txt");

        try(FileWriter fw = new FileWriter("src//files_3//task_1//Games//temp//temp.txt")){
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }


    }


    static void createDir(String nameOfDir){
        File dir = new File("src//files_3//task_1//Games" + nameOfDir);
        if(dir.mkdirs()){
            sb.append("Дирректория " + dir.getName() + " создана\n");
        }
    }

    static void createFile(String nameOfFile){
        File file = new File("src//files_3//task_1//Games" + nameOfFile);
        try{
            if (file.createNewFile()){
                sb.append("Файл " + file.getName() + " был создан\n");
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
