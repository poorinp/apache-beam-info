import java.io.File;

//Using Apache Beam
public class Main {

    //insert directory before org/
    final static String dir = "/Users/shoot/Developer/KU_3-1/Workgroup_Software/Assignment_8_Package_Dependency_Design/beam-master/sdks/java/extensions/google-cloud-platform-core/src/main/java/";
    //insert directory from org/
    final static String bigPackage = "org/apache/beam/sdk/extensions/gcp/";

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
