/**
 * Created by saeideh on 12/18/16.
 */
package Scopes;

import Symbols.Symbol;
import com.sun.javafx.collections.MappingChange;

import java.util.Map;

public class ClassScope extends GlobalScope {
    public ClassScope(Scope enclosingScope) {
        super(enclosingScope);
        this.enclosingScope = enclosingScope;
    }

    public String getScopeName() { return "Class"; }
    public Map<String,Symbol> symboltableshow(){

        return symbols;


    }
    int id=0;
    public void define(Symbol sym) {
        id++;
        symbols.put(sym.name+id, sym); //TODO
        //***sym.scope = this; // track the scope in each symbol
    }
}
