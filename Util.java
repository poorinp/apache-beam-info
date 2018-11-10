import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class Util {

    public void read(String dir, String curPkgName, String outerPackage){
        PackageInfo pkgInfo = new PackageInfo();
        pkgInfo.packageName = curPkgName;
        String bigPackage = outerPackage;

        System.out.println(dir + bigPackage + pkgInfo.packageName);

        File file = new File(dir + bigPackage + pkgInfo.packageName);
        String[] files = file.list();
        System.out.println("\nLocation: "+dir);
        System.out.println("Package name: "+pkgInfo.packageName);
        System.out.println("Files: "+files.length);

        for(String fileName : files)
        {
            if(fileName.endsWith(".java")){
                readNormal(file.getPath()+"/"+fileName, pkgInfo);
            }
            else if(!fileName.contains(".")){
                Util.recur(file.getPath(), fileName, pkgInfo);
            }
        }

        File fileCa = new File(dir);
        String[] filesCa = fileCa.list();
        for(String fileName : filesCa){
            if(fileName.endsWith(".java")){
                readCa(fileCa.getPath()+"/"+fileName, pkgInfo, bigPackage);
            }
            else if(fileName.equals(pkgInfo.packageName)){

            }
            else if(!fileName.contains(".")){
                recurCa(fileCa.getPath(), fileName, pkgInfo, bigPackage);
            }
        }
        System.out.println("Ca = "+pkgInfo.ca);
        System.out.println("Ce = "+pkgInfo.ce);
        System.out.println("Na = "+pkgInfo.na);
        System.out.println("Nc = "+pkgInfo.nc);
        System.out.println("A  = "+pkgInfo.getAbstractness());
        System.out.println("I  = "+pkgInfo.getInstability());
    }

    public static void recur(String path, String folder, PackageInfo info){
        String newPath = path+"/"+folder;
        File file = new File(newPath);
        String[] files = file.list();
        for(String fileName : files) {
            if (fileName.endsWith(".java")) {
                readNormal(file.getPath() + "/" +fileName, info);
            } else if (!fileName.contains(".")) {
                recur(file.getPath(), fileName, info);
            }
        }
    }

    public static void recurCa(String path, String folder,PackageInfo info, String outerPackage){
        String newPath = path+"/"+folder;
        File file = new File(newPath);
        String[] files = file.list();
        for(String fileName : files)
        {
            if(fileName.endsWith(".java")){
                readCa(file.getPath()+"/"+fileName, info, outerPackage);
            }
            else if(!fileName.contains(".")){
                recurCa(file.getPath(), fileName, info, outerPackage);
            }
        }
    }

    public static void readNormal(String file, PackageInfo pkgInfo) {
        String eachline = "";

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //find abstract class
            while ((eachline = bufferedReader.readLine()) != null) {
                if (eachline.contains("abstract")) {
                    pkgInfo.na++;
                    break;
                }
            }

            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((eachline = bufferedReader.readLine()) != null) {
                if (eachline.contains("import ")) {
                    String[] tmp = eachline.split(" ");
                    String pkgName = pkgInfo.packageName.replace("/", ".");
                    if (!tmp[1].contains(pkgName)) {
                        pkgInfo.ce++;
                    }
                    break;
                }
            }

            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((eachline = bufferedReader.readLine()) != null) {
                if (eachline.contains("class ")) {
                    pkgInfo.nc++;
                }
            }

            bufferedReader.close();
        }
        catch(Exception e) { System.out.print(e.getMessage());}
    }

    public static void readCa(String file, PackageInfo pkgInfo, String bigPackage){
        String eachline = "";
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((eachline = bufferedReader.readLine()) != null) {
                if (eachline.contains("import ")) {
                    String[] tmp = eachline.split(" ");
                    String mainPkg = bigPackage+pkgInfo.packageName;
                    String pkgName = mainPkg.replace("/", ".");
                    if (tmp[1].startsWith(pkgName)) {
                        pkgInfo.ca++;
                    }
                    break;
                }
            }
            bufferedReader.close();
        }
        catch(Exception e) { System.out.print(e.getMessage());}
    }
}
