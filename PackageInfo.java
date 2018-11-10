import java.io.File;

public class PackageInfo {

    String packageName;
    int ca = 0;
    int ce = 0;
    int na = 0;
    int nc = 0;

    public double getInstability() {
        if (ce+ca == 0) return 0;
        else return ce/(ca+ce);
    }

    public double getAbstractness() {
        if (nc == 0) return 0;
        else return na/nc;
    }
}