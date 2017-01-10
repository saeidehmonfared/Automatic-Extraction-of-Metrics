/**
 * Created by saeideh on 12/18/16.
 */
package Scopes;

import Symbols.Symbol;

import java.util.Map;

public class BlockScope extends MethodScope {
    public BlockScope(Scope enclosingScope) { super(enclosingScope);
        this.enclosingScope = enclosingScope;
    }
    public Map<String,Symbol> symboltableshow(){ return symbols;

    }
    public String getScopeName() { return "Block"; }
}
