package MetricExtraction;

import ASTGenerator.Inheritancelist;
import MetricExtraction.CouplingExtraction.CouplingMetrics;
import MetricExtraction.CouplingExtraction.Invoc;
import MetricExtraction.CouplingExtraction.Object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by saeideh on 2/19/17.
 */
public class Qualityformula {
    public static void Expandabilitycalculation(){
        System.out.println("\nExpandability metric:");
        System.out.println("Project level:");
        System.out.println("number of package of project is:"+PackageLevelMetrics.numberofpackages);
        System.out.println("number of interface of project is:"+PackageLevelMetrics.numberofinterfaces);
        System.out.println("number of class of project is:"+PackageLevelMetrics.numberofclasses);
        System.out.println("number of abstract class of project:"+PackageLevelMetrics.numberofabstractclasses);
        System.out.println("number of Abstract methods of project is:"+ClassLevelMetrics.allofabstractmethods);
      //  System.out.println("list of all abstract methods:"+ClassLevelMetrics.allofabstractmethodslist);
          System.out.println("package level:");
        System.out.println(PackageLevelMetrics.returnvalue);

    }

    public static void Generalitycalculation(){

        System.out.println("\nGenerality metric:");
        System.out.println("package level:");
        //TODO: kafiye ke list returnvalue ro biary inja va staticliatclasslevelmetrics ro ham biari va ba estefadeh azz            ona generality ro dar sotoh mokhtalef mohasebeh koni

    }
    public static void simplicitycalculation(){

    }
    public static Map<String,Map<String,Map<String,ArrayList<Invoc>>>> couplinglist=new LinkedHashMap<>();
    public static Map<String,ArrayList<String>> couplinglistobjectinstance=new LinkedHashMap<>();

    public static void couplingcalculation() {
        System.out.println("\ncoupling metric:");


        System.out.println("class level:");
        //TODO: kafiye ke list returnvalue ro barresi koni ta betoni formula ro darbiari

        //System.out.println(CouplingMetrics.returnvalue);
        for (String value : CouplingMetrics.returnvalue.keySet()) {
            String name = value;
            for (String value1 : CouplingMetrics.returnvalue.get(name).keySet()) {
                String name1 = value1;
                couplinglist.put(name1, new LinkedHashMap<>());
                for (String value2 : CouplingMetrics.returnvalue.get(name).get(name1).keySet()) {
                    String name2 = value2;
                    couplinglist.get(name1).put(name2, new LinkedHashMap<>());
                    couplinglist.get(name1).get(name2).put("methodinvocation", new ArrayList<>());
                    couplinglist.get(name1).get(name2).put("attributeinvocation", new ArrayList<>());
                    couplinglist.get(name1).get(name2).put("parametertype", new ArrayList<>());
                    couplinglist.get(name1).get(name2).put("variabletype", new ArrayList<>());

                    Iterator<Invoc> it = CouplingMetrics.returnvalue.get(name).get(name1).get(name2).iterator();

                    while (it.hasNext()) {
                        Invoc name4 = it.next();
                        if (name4.invoctype.equals(Invoc.InvocType.ATTRIBUTEINVOC)) {
                            couplinglist.get(name1).get(name2).get("attributeinvocation").add(name4);

                        } else if (name4.invoctype.equals(Invoc.InvocType.METHODINVOC)) {
                            couplinglist.get(name1).get(name2).get("methodinvocation").add(name4);
                        }
                        //TODO: tikey marbot be static ro ham bad benevis yadet nare

                    }
                }




            }
                int couplingcount=0;
            int dependencycounter=0;
            int assosiationcounter=0;
            int dependencycounter1=0;
            int assosiationcounter1=0;
            for(String value0: couplinglist.keySet()){
                System.out.println("class name is:"+value0);
                System.out.println("coupling list is:");
                for(String value01: couplinglist.get(value0).keySet()){
                    System.out.println("\n"+value01);
                    couplingcount++;
                    Iterator<Invoc> it0=couplinglist.get(value0).get(value01).get("methodinvocation").iterator();
                    while (it0.hasNext()){
                        Invoc name0=it0.next();
                        if(name0.relationType.equals(Invoc.RelationType.DEPENDENCY)){
                            dependencycounter++;
                        }
                        else if(name0.relationType.equals(Invoc.RelationType.ASSOSIATION)){
                            assosiationcounter++;
                        }
                        //TODO: count static relation

                    }
                    System.out.println("from method invocation:");
                    System.out.println("number of dependency is:"+dependencycounter);
                    System.out.println("number of assosiation is:"+assosiationcounter );
                    dependencycounter=0;
                    assosiationcounter=0;
                    Iterator<Invoc> it01=couplinglist.get(value0).get(value01).get("attributeinvocation").iterator();
                    while (it01.hasNext()) {
                        Invoc name01 = it01.next();
                        if (name01.relationType.equals(Invoc.RelationType.DEPENDENCY)) {
                            dependencycounter1++;
                        } else if (name01.relationType.equals(Invoc.RelationType.ASSOSIATION)) {
                            assosiationcounter1++;
                        }
                    }
                        System.out.println("\nfrom attribute invocation:");
                        System.out.println("number of dependency is:"+dependencycounter1);
                        System.out.println("number of assosiation is:"+assosiationcounter1 );
                        dependencycounter1=0;
                        assosiationcounter1=0;
                        //TODO: count static relation


                }
                System.out.println("coupling count is:"+couplingcount+"\n");

                couplingcount=0;

            }
        }
        System.out.println(couplinglist);
        boolean find=false;
     for(String value3:CouplingMetrics.objectinstancevalue.keySet())
       {
           String name5=value3;
           couplinglistobjectinstance.put(name5,new ArrayList<>());

               Iterator<Object> it1 = CouplingMetrics.objectinstancevalue.get(name5).iterator();

               while (it1.hasNext()) {
                   Object name7 = it1.next();
                  // Iterator<String> it2=couplinglistobjectinstance.get(name5).iterator();
                   //String
                   couplinglistobjectinstance.get(name5).add(name7.classname);



           }

       }


        System.out.println("package level:");
    }
    public  static void cohesioncalculation(){
        System.out.println("mmmmmmmmmmmmmmmmmmmmm:"+CohesionMetrics.returnvalue);
        System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnn"+CohesionMetrics.returnvalue1);
        //TODO: kafiye ke az in 2 ta list estefadeh koni ta felan metric cohesion ro hesab koni
    }

    public static void inheritancecalculation(){

        System.out.println("Inheritance metric is:");
        System.out.println(InheritanceMetrics.returnvalue);
    }
    public static void encapsulationcalculation(){

    }
    public static void polymorphismcalculation(){

    }

}
