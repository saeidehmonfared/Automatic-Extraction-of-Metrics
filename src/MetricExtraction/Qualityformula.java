package MetricExtraction;

import ASTGenerator.Inheritancelist;
import MetricExtraction.CouplingExtraction.CouplingMetrics;

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

    public static void couplingcalculation(){
        System.out.println("\ncoupling metric:");


        System.out.println("class level:");
        //TODO: kafiye ke list returnvalue ro barresi koni ta betoni formula ro darbiari

        //System.out.println(CouplingMetrics.returnvalue);



        System.out.println("package level:");

    }
    public  static void cohesioncalculation(){
        System.out.println("mmmmmmmmmmmmmmmmmmmmm:"+CohesionMetrics.returnvalue);
        System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnn"+CohesionMetrics.returnvalue1);
        //TODO: kafiye ke az in do ta list estefadeh koni ta felan metric cohesion ro hesab koni
    }

    public static void inheritancecalculation(){

        System.out.println("Inheritance metric is:");
        System.out.println(InheritanceMetrics.returnvalue);
    }

}
