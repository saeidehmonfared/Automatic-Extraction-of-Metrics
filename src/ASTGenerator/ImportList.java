package ASTGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import Symbols.*;

/**
 * Created by saeideh on 12/28/16.
 */
public class ImportList {
    public Map<String, Symbol> importlist = new LinkedHashMap<String, Symbol>();
    public void set(Symbol symbol){
        importlist.put(symbol.name,symbol);
    }
    public void get(){}
}
