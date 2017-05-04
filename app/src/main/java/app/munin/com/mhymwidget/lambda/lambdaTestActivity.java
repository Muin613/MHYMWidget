package app.munin.com.mhymwidget.lambda;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import app.munin.com.mhymwidget.R;
import app.munin.com.mhymwidget.lambda.Bean.III;
import app.munin.com.mhymwidget.lambda.Bean.Person;

/**
 * Created by Administrator on 2017/5/3.
 * jack
 */

public class LambdaTestActivity extends FragmentActivity {
    private ImageView img;
    private Button btn;
    private LinearLayout ll;
    private EditText txt;
    List<Person> javaProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
            add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
            add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
            add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
            add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
            add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
            add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
            add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
        }
    };

    List<Person> phpProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
            add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
            add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
            add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
            add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
            add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
            add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
            add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
            add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
            add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        img = (ImageView) findViewById(R.id.img);
        btn = (Button) findViewById(R.id.btn);
        ll = (LinearLayout) findViewById(R.id.ll);
        txt = (EditText) findViewById(R.id.txt);
        System.out.println("点击了");
        txt.setText("点击了");
//        lambdaTest();
        btn.setOnClickListener(view -> doMine(btn.getText().toString().trim()));


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    void lambdaTest() {
        //        线程方法
        new Thread(() -> System.out.println("thread in java8")).start();
//        run方法
        Runnable run = () -> System.out.println("run in java8");
        run.run();
//        打印
        Consumer<Person> consumer1 = i -> print(i);
//        一个个发送 处理
        System.out.println("");
        javaProgrammers.forEach((i) -> print(i));
        System.out.println("");
//        过滤  个人感觉是在stream里面操作的
        javaProgrammers.stream().filter((i) -> (i.getSalary() > 1400)).forEach(consumer1);
        System.out.println("");
//        限制4个输出
        javaProgrammers.stream().limit(4).forEach(consumer1);
        System.out.println("");
//        根据名字排序限制5个
        List<Person> sortedJavaProgrammers = javaProgrammers
                .stream()
                .sorted((p, p2) -> (p.getFirstName().compareTo(p2.getFirstName())))
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("");
        sortedJavaProgrammers.forEach(consumer1);
        System.out.println("");
        System.out.println("" + javaProgrammers);
        System.out.println("最小");
        Person pers = javaProgrammers
                .stream()
                .min((p1, p2) -> (p1.getSalary() - p2.getSalary()))
                .get();
        doString(pers);
        System.out.println("" + javaProgrammers);
        System.out.println("");
        String phpDevelopers = phpProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(Collectors.joining(" ; "));
        System.out.println("" + phpDevelopers);
        System.out.println("");
        Set<String> javaDevFirstName = javaProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(Collectors.toSet());
        System.out.println("" + javaDevFirstName);
        System.out.println("");
        TreeSet<String> javaDevLastName = javaProgrammers
                .stream()
                .map(Person::getLastName)
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("" + javaDevLastName);
        System.out.println("");
        int totalSalary = javaProgrammers
                .parallelStream()
                .mapToInt(p -> p.getSalary())
                .sum();
        System.out.println("" + totalSalary);
        System.out.println("" + javaProgrammers);
        System.out.println("");
        IntSummaryStatistics stats = javaProgrammers
                .stream()
                .mapToInt((x) -> x.getSalary())
                .summaryStatistics();

        System.out.println("List中最大的数字 : " + stats.getMax());
        System.out.println("List中最小的数字 : " + stats.getMin());
        System.out.println("所有数字的总和   : " + stats.getSum());
        System.out.println("所有数字的平均值 : " + stats.getAverage());
        System.out.println("" + javaProgrammers);
    }

    private void doString(Person i) {
        i.setSalary(i.getSalary() / 100 * 5 + i.getSalary());
        System.out.println("数据:" + i);
    }

    private void print(Person i) {
        System.out.println("数据:" + i);
    }

    void doMine(String conent){
        System.out.println("come");

        setIII((content)-> System.out.println(conent),conent);
    }



    public III<String> iii;
    public void setIII(III ii,String conent){
        ii.delete(conent);
    }
}
