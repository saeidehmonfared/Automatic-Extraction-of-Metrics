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
    public ArrayList<String>interfaceextendedlist=new ArrayList<String>();





    //---------------------------------------------------------------------------------------------------

    @Override public void enterNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) { }

   @Override public void exitNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) {

        String superclassname=null;
        //superclassname=ctx.superclass().classType().getText();
        String name=ctx.Identifier().toString();
        Symbol superclass=null;

        if(superclassname!=null) {
            superclass  = Inheritancelist.ResolveSuperClassname(superclassname, importlist);
        }
        //System.out.println(Implementlist+"tttttttttttttttttttttttttt");
        for (int i=0;i<Implementlist.size();i++){
            String interfacename=Implementlist.get(i);
            ImplementSymbollist.add(Inheritancelist.ResolveInterfaceName(importlist,interfacename));


        }
        //System.out.println(ImplementSymbollist+"chiiiiiiiiiiiiiiiiiiii");
        if(!(superclass==null && Implementlist==null)){
            Symbol sym=new Symbol(name,superclass,ImplementSymbollist);
            Inheritancelist.insert(sym);}
        importlist.clear();


    }

    //-----------------------------------------------------------------------------------------------------



    @Override public void enterNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {

    }

    @Override public void exitNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {


        String superclassname=null;
        superclassname=ctx.superclass().classType().getText();
        String name=ctx.Identifier().toString();
        Symbol superclass=null;

        if(superclassname!=null) {
            superclass  = Inheritancelist.ResolveSuperClassname(superclassname, importlist);
        }
        //System.out.println(Implementlist+"tttttttttttttttttttttttttt");
        for (int i=0;i<Implementlist.size();i++){
            String interfacename=Implementlist.get(i);
            ImplementSymbollist.add(Inheritancelist.ResolveInterfaceName(importlist,interfacename));


        }
        //System.out.println(ImplementSymbollist+"chiiiiiiiiiiiiiiiiiiii");
        if((superclass!=null || Implementlist!=null)){
            Symbol sym=new Symbol(name,superclass,ImplementSymbollist);
            Inheritancelist.insert(sym);}
        importlist.clear();
    }
    //-----------------------------------------------------------------------------


    //------------------------------------------------------------------------------
    @Override public void enterNormalInterfaceDeclaration2(javaParser.NormalInterfaceDeclaration2Context ctx) {


    }


    @Override public void exitNormalInterfaceDeclaration2(javaParser.NormalInterfaceDeclaration2Context ctx) {//String name = ctx.Identifier().toString();
       // Symbol superclass = null;

       // if (interfaceextendedlist != null) {

          //  for (int i = 0; i < interfaceextendedlist.size(); i++) {
             //   String interfacename2 = interfaceextendedlist.get(i);
               // System.out.println(interfacename2+"dddddddddggggggggggggggggggggggggg");
               // ImplementSymbollist.add(Inheritancelist.ResolveSuperInterfacename(interfacename2, importlist));
           // }
           // System.out.println(ImplementSymbollist + "kkkkkkkkkkkkkkkkkkkkkkk");

            // System.out.println(ImplementSymbollist+"chiiiiiiiiiiiiiiiiiiii");

            //Symbol sym = new Symbol(name, ImplementSymbollist, null);
            //Inheritancelist.insert(sym);
      //  }
    }


    //--------------------------------------------------------------------------------------------
    @Override public void enterSuperinterfaces(javaParser.SuperinterfacesContext ctx) { }

    @Override public void exitSuperinterfaces(javaParser.SuperinterfacesContext ctx) {
       // System.out.println(interfaceextendedlist+"ffffffffffffffffffffff");
       // interfaceextendedlist.clear();
    }
    //-------------------------------------------------------------------------------------

    @Override public void enterExtendsInterfaces(javaParser.ExtendsInterfacesContext ctx) { }

    @Override public void exitExtendsInterfaces(javaParser.ExtendsInterfacesContext ctx) {
        //System.out.println(interfaceextendedlist+"ffffffffffffffffffffffffffffffffffffff");
    }
//-------------------------------------------------------------------------------------------

    @Override public void enterInterfaceTypeList(javaParser.InterfaceTypeListContext ctx) {
      //  Implementlist.clear();
    }

    @Override public void exitInterfaceTypeList(javaParser.InterfaceTypeListContext ctx) { }

    //-------------------------------------------------------------------------------------------------

    @Override public void enterInterfaceType(javaParser.InterfaceTypeContext ctx) { }


    @Override public void exitInterfaceType(javaParser.InterfaceTypeContext ctx) {
        String interfacetype=ctx.classType().getText();
        Implementlist.add(interfacetype);
       // System.out.println(Implementlist+"khaaaaaaaaaaaaaar");
       // interfaceextendedlist.add(interfacetype);
    }



}
