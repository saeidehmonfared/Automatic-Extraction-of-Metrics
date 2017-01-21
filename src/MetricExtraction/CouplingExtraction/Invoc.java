package MetricExtraction.CouplingExtraction;

/**
 * Created by saeideh on 1/17/17.
 */
public class Invoc {
    public static enum InvocType {METHODINVOC, ATTRIBUTEINVOC};

    public static enum RelationType {ASSOSIATION, DEPENDENCY,INVALID};

    InvocType invoc;
    RelationType relationType;
    String name;


    public Invoc(String name,InvocType invoc, RelationType relation){
        this.name=name;
        this.invoc=invoc;
        this.relationType=relation;
    }
}


