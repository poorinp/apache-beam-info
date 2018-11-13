import java.io.File;

public class PackageInfo {

    String packageName;
    double ca = 0;
    double ce = 0;
    double na = 0;
    double nc = 0;

    public double getInstability() {
        if (ce+ca == 0) return 0;
        else return ce/(ca+ce);
    }

    public double getAbstractness() {
        if (nc == 0) return 0;
        else return na/nc;
    }
}