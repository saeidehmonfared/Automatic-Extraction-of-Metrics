package ASTGenerator;

import ANTLRParser.*;
import Symbols.Symbol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by saeideh on 1/8/17.
 */
public class Inheritancephase extends javaBaseListener {
    public Map<String, Symbol> importlist = new LinkedHashMap<String, Symbol>();

    public  Inheritancephase(Map<String,Symbol> importlist){
        this.importlist=importlist;

    }

    public ArrayList<String> Implementlist=new ArrayList<String>();
    public ArrayList<Symbol> ImplementSymbollist=new ArrayList<Symbol>();

    String superclassname=null;


    @Override public void enterNormalClassDeclaration(javaParser.NormalClassDeclarationContext ctx) {



    }
    //---------------------------------------------------------------------------------------------------
    @Override public void enterSuperclass(javaParser.SuperclassContext ctx) { }

    @Override public void exitSuperclass(javaParser.SuperclassContext ctx) { superclassname=ctx.classType().getText();
       // System.out.println(superclassname+"amiiiiiiiiiiiiiiiiir");
    }

    //------------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitNormalClassDeclaration(javaParser.NormalClassDeclarationContext ctx) {
        String name=ctx.Identifier().toString();
        Symbol superclass=null;

        if(superclassname!=null) {
           superclass  = Inheritancelist.ResolveSuperClassname(superclassname, importlist);
        }
        for (int i=0;i<Implementlist.size();i++){
            String interfacename=Implementlist.get(i);
            ImplementSymbollist.add(Inheritancelist.ResolveInterfaceName(importlist,interfacename));


        }
       // System.out.println(ImplementSymbollist+"chiiiiiiiiiiiiiiiiiiii");
        if(!(superclass==null && Implementlist!=null)){
        Symbol sym=new Symbol(name,superclass,ImplementSymbollist);
        Inheritancelist.insert(sym);}

    }

    //--------------------------------------------------------------------------------------------

    @Override public void enterInterfaceTypeList(javaParser.InterfaceTypeListContext ctx) {
        Implementlist.clear();
    }

    @Override public void exitInterfaceTypeList(javaParser.InterfaceTypeListContext ctx) { }

    //-------------------------------------------------------------------------------------------------

    @Override public void enterInterfaceType(javaParser.InterfaceTypeContext ctx) { }


    @Override public void exitInterfaceType(javaParser.InterfaceTypeContext ctx) {
        String interfacetype=ctx.classType().getText();
        Implementlist.add(interfacetype);
    }

}
