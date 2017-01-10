package ASTGenerator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import Symbols.*;

/**
 * Created by saeideh on 12/27/16.
 */ public   class StaticList {
    public static final Map<String, Symbol> staticlist = new LinkedHashMap<String, Symbol>();
    public static void insert(Symbol symbol){
        staticlist.put(symbol.name,symbol);


    }
     public static Symbol Resolve(String importname,Symbol s1) {

         //System.out.println("importname:"+importname);
        // System.out.println("packname:"+s1.packagename);
             if(s1.packagename.equals(importname))
                 return  s1;

         return null;

     }




}
