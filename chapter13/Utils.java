package javaInAction.chapter13;

import java.util.List;

public class Utils {
    public static void paint(List<Resizable> l) {
        l.forEach(resizable -> {
            resizable.setAbsoluteSize(42,42);
//            resizable.draw();
        });
    }

}
