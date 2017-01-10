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
}
