package MetricExtraction.CouplingExtraction;

import Scopes.Scope;

/**
 * Created by saeideh on 1/17/17.
 */
public class Invoc {
    public static enum InvocType {METHODINVOC, ATTRIBUTEINVOC};

    public static enum RelationType {ASSOSIATION, DEPENDENCY,INVALID,STATICMETHOD};

    InvocType invoctype;
    RelationType relationType;
    String name;
    Scope currentScope;


    public Invoc(String name,InvocType invoc, RelationType relation,Scope currentScope){
        this.name=name;
        this.invoctype=invoc;
        this.relationType=relation;
        this.currentScope=currentScope;
    }
}


