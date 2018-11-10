import java.io.File;

//Using Apache Beam
public class Main {

    //insert directory before org/
    final static String dir = "C:/Users/PooRi/Downloads/Compressed/beam-master/sdks/java/core/src/main/java/";
    //insert directory from org/
    final static String bigPackage = "org/apache/beam/sdk/";

    public static void main(String[] args) {
        Util util = new Util();
        File file = new File(dir + bigPackage);
        String[] filesName = file.list();
        for (String fileName : filesName) {
            if(!fileName.contains(".")) {
                util.read(dir , fileName, bigPackage);
            }
        }
    }
}
