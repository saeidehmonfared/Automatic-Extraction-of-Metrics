package MetricExtraction;

import Symbols.Symbol;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by saeideh on 1/25/17.
 */
public class Staticlistclasslevelmetrics {
    public static Map<Symbol,Map<String,ArrayList<String>>> lisofclasses=new LinkedHashMap<Symbol,Map<String, ArrayList<String>>>();
    public static void put(Symbol classname,Map<String,ArrayList<String>>list)
    {
        lisofclasses.put(classname,list);

    }
    public static void show(){
        int count=0;
        for(Symbol value: lisofclasses.keySet()){
            Symbol classname=value;
            System.out.println("\n");
            System.out.println("name of class:"+classname.name);

            System.out.println(lisofclasses.get(classname));
            count++;
        }
        System.out.println("\nnumbetofclasses is"+count);
    }

}
