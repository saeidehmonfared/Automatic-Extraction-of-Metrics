/**
 * Created by saeideh on 12/18/16.
 */
package Scopes;

import java.util.LinkedHashMap;
import java.util.Map;
import Symbols.*;
    public abstract class BaseScope implements Scope {
        Scope enclosingScope; // null if global (outermost) scope
        public Map<String, Symbol> symbols = new LinkedHashMap<String, Symbol>();

        public BaseScope(Scope enclosingScope) { this.enclosingScope = enclosingScope;  }

        public Symbol resolve(String name) {
            Symbol s = symbols.get(name);
            if ( s!=null ) return s;
            // if not here, check any enclosing scope
            if ( enclosingScope != null ) return enclosingScope.resolve(name);
            return null; // not found
        }

        public void define(Symbol sym) {
            symbols.put(sym.name, sym);
            sym.scope = this; // track the scope in each symbol
        }
        public Map<String,Symbol> symboltableshow(){

           return symbols;


        }

        public Scope getEnclosingScope() { return enclosingScope; }

        public String toString() { return getScopeName()+":"+symbols.keySet().toString(); }
    }

