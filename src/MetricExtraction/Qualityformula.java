package MetricExtraction;

/**
 * Created by saeideh on 2/19/17.
 */
public class Qualityformula {
    public static void show(){
        System.out.println("number of Abstract methods of project is:"+ClassLevelMetrics.allofabstractmethods);
        System.out.println("list of all abstract methods:"+ClassLevelMetrics.allofabstractmethodslist);
    }
}
