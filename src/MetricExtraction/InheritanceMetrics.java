package MetricExtraction;
import ANTLRParser.*;
import ASTGenerator.Inheritancelist;
import ASTGenerator.StaticList;
import Scopes.ClassScope;
import Scopes.GlobalScope;
import Symbols.Symbol;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by saeideh on 1/11/17.
 */
public class InheritanceMetrics extends javaBaseListener {
  public   ArrayList<String> Inheritancelistofclass = new ArrayList<String>();

    String nameofclass;
    String Aslnameofclass;

    public InheritanceMetrics() {
      //  Aslnameofclass = name;


    }



    public void enterCompilationUnit(javaParser.CompilationUnitContext ctx) {

    }

    @Override
    public void exitCompilationUnit(javaParser.CompilationUnitContext ctx) {

    }

    public void enterNormalClassDeclaration(javaParser.NormalClassDeclarationContext ctx) {

       nameofclass = ctx.Identifier().toString();
        Symbol s1 = null;

        for (Symbol value : Inheritancelist.inheritanclist.values()) {
            Symbol s = value;
            if (s.name.equals(nameofclass)) {

                s1 = s;
                break;


            }


        }

        boolean b = true;
        if (!(s1==null)) {
            while (!(s1.equals(null)) && ((b))) {

               Inheritancelistofclass.add(s1.Parent.name);
                s1 = s1.Parent;
                b = false;
                for (Symbol value : Inheritancelist.inheritanclist.values()) {
                    Symbol s = value;
                    if ((s.name.equals(s1.name))) {
                        s1=s;

                        b = true;
                        break;


                    }

                }
            }
        }


            System.out.println("Inheritance list of" + nameofclass + "is:" + Inheritancelistofclass);
        Inheritancelistofclass.clear();

        }


    }


