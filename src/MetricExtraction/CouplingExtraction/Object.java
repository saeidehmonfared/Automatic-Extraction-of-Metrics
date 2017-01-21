package MetricExtraction.CouplingExtraction;

import Scopes.Scope;
import Symbols.Symbol;

/**
 * Created by saeideh on 1/17/17.
 */
public class Object {

    public Scope currentscope;
    public String classname;
    public Symbol symbol;

    public Object(String name,Symbol symbol,Scope currentscope)
    {
        this.classname=name;
        this.symbol=symbol;
        this.currentscope=currentscope;
    }
}
