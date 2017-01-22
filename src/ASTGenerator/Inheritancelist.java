package ASTGenerator;

import Symbols.Symbol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by saeideh on 1/8/17.
 */
public class Inheritancelist {

    public static final Map<String, Symbol> inheritanclist = new LinkedHashMap<String, Symbol>();

    public static void insert(Symbol symbol) {
        inheritanclist.put(symbol.name, symbol);
    }

    public static Symbol ResolveInterfaceName(Map<String, Symbol> importlist, String interfacename) {

        Iterator<Symbol> it = importlist.values().iterator();
        while (it.hasNext()) {

            Symbol s2 = it.next();

            if (interfacename.equals(s2.name)) {

                return s2;

            }

        }


        return null;
    }

    public static Symbol ResolveSuperClassname(String superclassname, Map<String, Symbol> importlist) {


        Iterator<Symbol> it = importlist.values().iterator();
        while (it.hasNext()) {

            Symbol s2 = it.next();

            if (superclassname.equals(s2.name)) {

                return s2;

            }

        }


        return null;


    }

    public static Symbol ResolveSuperInterfacename( String superinterfacename, Map<String, Symbol> importlist) {
        String s1 = "";

            s1 = superinterfacename;
            Iterator<Symbol> it = importlist.values().iterator();
            while (it.hasNext()) {

                Symbol s2 = it.next();

                if (s1.equals(s2.name)) {

                    return s2;

                }


            }




        return null;


    }
}

