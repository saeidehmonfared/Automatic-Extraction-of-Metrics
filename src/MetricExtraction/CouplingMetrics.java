package MetricExtraction;
import ANTLRParser.*;
import Scopes.GlobalScope;
import Scopes.Scope;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

/**
 * Created by saeideh on 1/14/17.
 */
public class CouplingMetrics extends javaBaseListener{

    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope currentScope;
    public CouplingMetrics(GlobalScope globals, ParseTreeProperty<Scope> Scopes) {
        this.globals = globals;
        this.scopes = Scopes;

    }

}
