package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        //Три игры для сохранения:
        GameProgress gameProgress1 = new GameProgress(10, 3, 2, 152.4);
        GameProgress gameProgress2 = new GameProgress(100, 5, 8, 1500);
        GameProgress gameProgress3 = new GameProgress(50, 3, 3, 201);

        //Сохранение игры в папку savegame:
        //Путь до основной папки:
        String pathToSave = "D://GAME_JAVA//savegames";
        //Пути с именами файлов для сохранения:
        String pathToSave1 = pathToSave + "//save1.dat";
        String pathToSave2 = pathToSave + "//save2.dat";
        String pathToSave3 = pathToSave + "//save3.dat";

        //Вызов функций сохранения:
        saveGame(pathToSave1, gameProgress1);
        saveGame(pathToSave2, gameProgress2);
        saveGame(pathToSave3, gameProgress3);
        //__________


        //Список файлов для архивирования:
        java.util.List<String> listSaves = new ArrayList<>();
        listSaves.add(pathToSave1);
        listSaves.add(pathToSave2);
        listSaves.add(pathToSave3);
        //Путь с именем файла создаваемого архива:
        String pathZipFile = pathToSave + "//ZipSaves.zip";
        //Вызов функции архивирования:
        zipSave(pathZipFile, listSaves);
        //__________

        //Удаление файлов сохранений (.dat):
        File dirToSave = new File(pathToSave);
        System.out.println("Каталог savegames содержит:");
        for (File fileName : dirToSave.listFiles()) {
            System.out.println(fileName);
            if (fileName.getName().endsWith("dat")) {
                fileName.delete();
            }
        } //endforeach

    } //endofmain

    //Функция сохранения игры в файл:
    public static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Функция архивирования файлов сохранений игры:
    public static void zipSave(String pathZip, List<String> pathSave) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(pathZip))) {

            for (int i = 0; i < pathSave.size(); i++) {

                try (FileInputStream fis = new FileInputStream(pathSave.get(i))) {
                    ZipEntry entry = new ZipEntry("pack_save" + (i + 1) + ".dat");
                    zos.putNextEntry(entry);

                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);

                    zos.write(buffer);
                    zos.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }//endfor

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    } //endofZipSave

}
