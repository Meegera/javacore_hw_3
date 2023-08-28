package files_3.task_2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress progress1 = new GameProgress(94, 10, 2, 254.8);
        GameProgress progress2 = new GameProgress(60, 12, 2, 355.57);
        GameProgress progress3 = new GameProgress(77, 5, 5, 454.8);

        ArrayList<String> saveFiles = new ArrayList<>();
        saveFiles.add("src//files_3//task_1//Games//savegames//save1.dat");
        saveFiles.add("src//files_3//task_1//Games//savegames//save2.dat");
        saveFiles.add("src//files_3//task_1//Games//savegames//save3.dat");

        saveGame(saveFiles.get(0), progress1);
        saveGame(saveFiles.get(1), progress2);
        saveGame(saveFiles.get(2), progress3);

        zipFile("src//files_3//task_1//Games//savegames//zipgames.zip", saveFiles);

        File dir = new File("src//files_3//task_1//Games//savegames");

        Arrays.stream(dir.listFiles()).forEach(element -> {
            if(!(element.getName().substring(element.getName().lastIndexOf(".")).equals(".zip"))){
                element.delete();
            }
        });
    }

    static void saveGame(String nameFile, GameProgress progress){
        try (FileOutputStream fos = new FileOutputStream(nameFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(progress);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    static void zipFile(String nameFile, ArrayList<String> files){
        try(FileOutputStream fos = new FileOutputStream(nameFile);
                ZipOutputStream zout = new ZipOutputStream(fos)){
            int i = 0;
            while (files.size() > i){
                try(FileInputStream fis = new FileInputStream(files.get(i))){
                    String name = files.get(i);
                    ZipEntry entry = new ZipEntry("zip_" + name.substring(name.lastIndexOf("//") + 2, name.lastIndexOf(".")) + ".dat");
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                    i++;
                } catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
