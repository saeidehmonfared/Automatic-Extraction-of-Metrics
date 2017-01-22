package Scopes;

import Symbols.Symbol;

import java.util.Map;

/**
 * Created by saeideh on 1/21/17.
 */
public class InterfaceScope extends GlobalScope {
    public InterfaceScope(Scope enclosingScope) {
        super(enclosingScope);
        this.enclosingScope = enclosingScope;
    }

    public String getScopeName() { return "Interface"; }
    public Map<String,Symbol> symboltableshow(){

        return symbols;


    }
}
