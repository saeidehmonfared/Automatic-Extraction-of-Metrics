package MetricExtraction.CouplingExtraction;

/**
 * Created by saeideh on 5/7/17.
 */
public class cohesionobject {
   public String name;
   public String classname=null;
   public cohesionobject(String name,String classname){
       this.name=name;
       this.classname=classname;
   }
   public cohesionobject(String name){
       this.name=name;
   }

}
