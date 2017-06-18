package MetricExtraction.CouplingExtraction;

import Scopes.Scope;

/**
 * Created by saeideh on 1/17/17.
 */
public class Invoc {
    public static enum InvocType {METHODINVOC, ATTRIBUTEINVOC};

    public static enum RelationType {ASSOSIATION, DEPENDENCY,INVALID,STATICMETHOD};

    public InvocType invoctype;
   public RelationType relationType;
    public String name;
    public Scope currentScope;
    public String objectname;
    public String classname;


    public Invoc(String objectname,String  name,InvocType invoc, RelationType relation,Scope currentScope,String classname){
        this.objectname=objectname;
        this.name=name;
        this.invoctype=invoc;
        this.relationType=relation;
        this.currentScope=currentScope;
        this.classname=classname;
    }
}


