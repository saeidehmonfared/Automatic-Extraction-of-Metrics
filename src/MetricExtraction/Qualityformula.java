package MetricExtraction;

import ASTGenerator.Inheritancelist;
import MetricExtraction.CouplingExtraction.CouplingMetrics;
import MetricExtraction.CouplingExtraction.Invoc;
import MetricExtraction.CouplingExtraction.Object;
import Symbols.Symbol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import MetricExtraction.CouplingExtraction.*;
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
        Map<String,ArrayList<String>>couplinglisthelper=new LinkedHashMap<>();
        String name04="";
        System.out.println("\ncoupling metric:");

        System.out.println("amiiiiiiiiiiiiiiiiiiiiiiir"+CouplingMetrics.returnvalue);
        System.out.println("class level:");

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

            }
        System.out.println("fffffffffffffffffffffffffffffffffffffffff"+couplinglist);
            int couplingcount=0;
            int dependencycounter=0;
            int assosiationcounter=0;
            int dependencycounter1=0;
            int assosiationcounter1=0;
            for(String value0: couplinglist.keySet()) {
                System.out.println("\nclass name is:" + value0);
                System.out.println("coupling list is:");
                for (String value01 : couplinglist.get(value0).keySet()) {
                    System.out.println("\n" + value01);
                    couplingcount++;
                    Iterator<Invoc> it0 = couplinglist.get(value0).get(value01).get("methodinvocation").iterator();
                    while (it0.hasNext()) {
                        Invoc name0 = it0.next();
                        if (name0.relationType.equals(Invoc.RelationType.DEPENDENCY)) {
                            dependencycounter++;
                        } else if (name0.relationType.equals(Invoc.RelationType.ASSOSIATION)) {
                            assosiationcounter++;
                        }
                        //TODO: count static relation

                    }


                    System.out.println("from method invocation:");
                    System.out.println("number of dependency is:" + dependencycounter);
                    System.out.println("number of assosiation is:" + assosiationcounter);
                    dependencycounter = 0;
                    assosiationcounter = 0;
                    Iterator<Invoc> it01 = couplinglist.get(value0).get(value01).get("attributeinvocation").iterator();
                    while (it01.hasNext()) {
                        Invoc name01 = it01.next();
                        if (name01.relationType.equals(Invoc.RelationType.DEPENDENCY)) {
                            dependencycounter1++;
                        } else if (name01.relationType.equals(Invoc.RelationType.ASSOSIATION)) {
                            assosiationcounter1++;
                        }
                    }
                    System.out.println("\nfrom attribute invocation:");
                    System.out.println("number of dependency is:" + dependencycounter1);
                    System.out.println("number of assosiation is:" + assosiationcounter1);
                    dependencycounter1 = 0;
                    assosiationcounter1 = 0;
                    //TODO: count static relation


                    name04 = "";
                    for (String value03 : couplinglistobjectinstance.keySet()) {
                        String name03 = value03;
                        if (value0.equals(name03)) {
                            name04 = name03;
                            break;
                        }
                    }
                    boolean notfind = false;
                    couplinglisthelper.put(value0, new ArrayList<>());
                    if (name04 != "") {

                        Iterator<String> it03 = couplinglistobjectinstance.get(value0).iterator();
                        while (it03.hasNext()) {
                            String name05 = it03.next();
                            for (String value06 : couplinglist.get(value0).keySet()) {
                                if (!(name05.equals(value06))) {

                                    notfind = true;
                                } else if (name05.equals(value06)) {
                                    notfind = false;
                                }
                            }
                            if (notfind) {
                                couplinglisthelper.get(value0).add(name05);
                                notfind = false;

                            }
                        }
                    }


                }
                System.out.println("coupling count is:" + couplingcount + "\n");
                //TODO: hala bayad in list helper ro ham dar nazar begiri va chap koni
                // System.out.println(",ld,ls,dl:"+couplinglisthelper);

                couplingcount = 0;
            }


        boolean find=false;


       // System.out.println("objectinstances is:"+couplinglistobjectinstance);

        System.out.println("package level:");
    }




    public  static void cohesioncalculation(){
         System.out.println("mmmmmmmmmmmmmmmmmmmmm:"+CohesionMetrics.returnvalue);
        //System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnn"+CohesionMetrics.returnvalue1);
        ArrayList<String>cohesionlisthelper=new ArrayList<>();
        boolean find=false;
        //TODO: kafiye ke az in 2 ta list estefadeh koni ta felan metric cohesion ro hesab koni
        System.out.println("Cohesion metrics:");
        Symbol classname=null;
        for(String value0:CohesionMetrics.returnvalue.keySet()){
            System.out.println("class name isssss:"+value0);

            for(Symbol value1:Staticlistclasslevelmetrics.lisofclasses.keySet()){
                if(value0.equals(value1.name)){
                    classname=value1;
                    break;

                }


            }
            if(classname!=null) {
                int numberofmethods = Staticlistclasslevelmetrics.lisofclasses.get(classname).get("publicmethods").size()+Staticlistclasslevelmetrics.lisofclasses.get(classname).get("privatemethods").size()+Staticlistclasslevelmetrics.lisofclasses.get(classname).get("protectedmethods").size();
                System.out.println("number of methods of class is:" + numberofmethods);
                int numberofattributes=Staticlistclasslevelmetrics.lisofclasses.get(classname).get("publicvariables").size()+Staticlistclasslevelmetrics.lisofclasses.get(classname).get("protectedvariables").size()+Staticlistclasslevelmetrics.lisofclasses.get(classname).get("privatevariables").size();
                System.out.println("number of variables of class is:"+numberofattributes);
            }

            for(String value2:CohesionMetrics.returnvalue.get(value0).keySet()){
                Iterator<String> it0=CohesionMetrics.returnvalue.get(value0).get(value2).get("classvariableused").iterator();
                while (it0.hasNext()){
                    String name=it0.next();
                   // if(cohesionlisthelper.size()==0){
                        cohesionlisthelper.add(name);
                   // }
                   // else if (cohesionlisthelper.size()!=0)
                       // {
                          //  for(int i=0;i<cohesionlisthelper.size();i++){
                             //   if(name.equals(cohesionlisthelper.get(i))){
                              //      find=true;
                               //     break;

                               // }
                          //  }
                           // if(!find){
                             //   cohesionlisthelper.add(name);
                             //   find=false;
                           // }

                       //}

                }

            }
            System.out.println(" number of distnict attribute used:"+cohesionlisthelper.size());

            cohesionlisthelper.clear();

        }
    }

    public static void inheritancecalculation(){

        System.out.println("Inheritance metric is:");
      System.out.println(InheritanceMetrics.returnvalue);
        for(String value0: InheritanceMetrics.returnvalue.keySet()){
            System.out.println("class name is:"+value0);

            int DIT=InheritanceMetrics.returnvalue.get(value0).get("list of parents").size();
            System.out.println("Depth of Inheritance is:"+DIT);


        }


    }
    public static void encapsulationcalculation(){

    }
    public static void polymorphismcalculation(){

    }
    public static void cohesionextractionfromcouplinglist(){
        for(String value: CouplingMetrics.Cohesionlist.keySet()){
            System.out.println("class name is:"+value);
            for(String value1: CouplingMetrics.Cohesionlist.get(value).keySet()){
                System.out.println("method name is:"+value1);
                System.out.println("innerattributes:");
                Iterator<cohesionobject> it=CouplingMetrics.Cohesionlist.get(value).get(value1).get("innerattributes").iterator();
                while (it.hasNext()){
                   cohesionobject name=it.next();
                    System.out.println(name.name);



                }

                System.out.println("outerattributes:");
                Iterator<cohesionobject> it1=CouplingMetrics.Cohesionlist.get(value).get(value1).get("outerattributes").iterator();
                while (it1.hasNext()){
                    cohesionobject name=it1.next();
                    System.out.println(name.name);
                    System.out.println(name.classname);



                }
                System.out.println("innercalla:");
                Iterator<cohesionobject> it2=CouplingMetrics.Cohesionlist.get(value).get(value1).get("innercalls").iterator();
                while (it2.hasNext()){
                    cohesionobject name=it2.next();
                    System.out.println(name.name);




                }
                System.out.println("outercalls:");
                Iterator<cohesionobject> it3=CouplingMetrics.Cohesionlist.get(value).get(value1).get("outercalls").iterator();
                while (it3.hasNext()){
                    cohesionobject name=it3.next();
                    System.out.println(name.name);
                    System.out.println(name.classname);



                }
            }

        }
    }
    public static void complexitycalculation(){
        for(String value: ComplexityMetrics.complexitylist.keySet()){
            System.out.println("\nclass name is:"+value);
            for(String value0: ComplexityMetrics.complexitylist.get(value).keySet()){
                System.out.println("method name is:"+value0);
                for(String value1:ComplexityMetrics.complexitylist.get(value).get(value0).keySet()){
                    System.out.println(value1+":  "+ComplexityMetrics.complexitylist.get(value).get(value0).get(value1));
                }
            }
        }

    }


  /*  public static   void cohesionextractionfromcouplinglist(){
        boolean findclass=false;
        String findedclass="";
        boolean methodfind=false;

        for(String value0: CouplingMetrics.returnvalue.keySet()){
            for(String value1: CouplingMetrics.returnvalue.get(value0).keySet()){
                for(String value2: CouplingMetrics.Cohesionlist.keySet()){
                    if(value1.equals(value2)){
                        findedclass=value2;
                        findclass=true;
                        break;
                    }
                }

                if(findclass){
                    for(String value3: CouplingMetrics.returnvalue.get(value0).get(value1).keySet()){
                        Iterator<Invoc> it=CouplingMetrics.returnvalue.get(value0).get(value1).get(value3).iterator();
                        while (it.hasNext()) {
                            Invoc name = it.next();
                            String methodname1="";
                            for (String methodname : CouplingMetrics.Cohesionlist.get(findedclass).keySet()) {
                                methodname1=methodname;
                                if (name.currentScope.equals(methodname)){
                                    methodfind=true;
                                    break;
                                }
                            }
                            if(methodfind)
                            {
                                if(name.invoctype.equals(Invoc.InvocType.ATTRIBUTEINVOC)){
                                    CouplingMetrics.Cohesionlist.get(findedclass).get(methodname1).get("outerattributes").add(name.name);
                                }

                            }
                            methodfind=false;
                        }
                        findclass=false;
                        findedclass="";

                    }
                }
            }
        }

    }*/

}
