package app.munin.com.mhymwidget.JavaDesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.io.IOException;

import app.munin.com.mhymwidget.R;

/**
 * Created by Administrator on 2017/5/5.
 */

public class JavaDesignActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_design);
        protoType();
    }
//基本类型复制不用担心，对非基本类型最好深复制
    void protoType(){
        Prototype mode=new Prototype();
        mode.setString("1");
        mode.setObj(new Prototype.SerializableObject());
        mode.getObj().num=1;
        System.out.println("origin:"+mode);
        try {
            Prototype mode1=(Prototype)mode.clone();
            System.out.println("soft copy:"+ mode1.getObj().num);
            mode1.getObj().num=2;
            System.out.println("change soft copy:"+mode1.getObj().num);
            System.out.println("change origin:"+mode.getObj().num);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println("origin:"+mode.getObj().num);

        try {
            Prototype mode1=(Prototype)mode.deepClone();
            System.out.println("deep copy:"+mode1.getObj().num);
            mode1.getObj().num=21;
            System.out.println("change deep copy:"+mode1.getObj().num);
            System.out.println("change origin:"+mode.getObj().num);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
