package Thread10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T2 {
    public static void main(String[] args) {
        //将非同步容器转化成同步容器
        List list = new ArrayList();
        List listS = Collections.synchronizedList(list);
    }
}
