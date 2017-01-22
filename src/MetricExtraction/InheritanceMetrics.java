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

    @Override public void enterNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) {nameofclass = ctx.Identifier().toString();
        Symbol s1 = null;

        for (Symbol value : Inheritancelist.inheritanclist.values()) {
            Symbol s = value;
            if (s.name.equals(nameofclass)&& s.Parent!=null) {

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
                    if ((s.name.equals(s1.name)&&(s.Parent!=null))) {
                        s1=s;

                        b = true;
                        break;


                    }

                }
            }
        }


        System.out.println("\nInheritance list of " + nameofclass + " is:" + Inheritancelistofclass);
        //Inheritancelistofclass.clear();


        Iterator<Symbol> it3 = Inheritancelist.inheritanclist.values().iterator();

        while (it3.hasNext()) {
            try {
                Symbol s = it3.next();

                if (s.name.equals(nameofclass) && (s.Implementlist != null)) {
                    System.out.println("implemented interfacec with " + nameofclass + " is:" + s.Implementlist);
                    break;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override public void exitNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) { }

    @Override public void enterNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) { nameofclass = ctx.Identifier().toString();
        Symbol s1 = null;

        for (Symbol value : Inheritancelist.inheritanclist.values()) {
            Symbol s = value;
            if (s.name.equals(nameofclass) && s.Parent!=null) {

                s1 = s;
                break;


            }


        }

        boolean b = true;
        if (!(s1==null)) {
            while (!(s1.equals(null)) && ((b))) {
              // if(!(s1.Parent==null))
                    Inheritancelistofclass.add(s1.Parent.name);
                    s1 = s1.Parent;
                    b = false;
                    for (Symbol value : Inheritancelist.inheritanclist.values()) {
                        Symbol s = value;
                        if ((s.name.equals(s1.name))&&(s.Parent!=null)) {
                            s1 = s;

                            b = true;
                            break;


                        }

                    }


            }
        }


        System.out.println("\nInheritance list of " + nameofclass + " is:" + Inheritancelistofclass);
        //Inheritancelistofclass.clear();

        Iterator<Symbol> it3 = Inheritancelist.inheritanclist.values().iterator();

       while (it3.hasNext()) {
            try {
                Symbol s = it3.next();

                if (s.name.equals(nameofclass) && (s.Implementlist != null)) {
                    System.out.println("implemented interfacec with " + nameofclass + " is:" + s.Implementlist);
                    break;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }



        //Inheritancelistofclass.clear();

    }

    @Override public void exitNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) { }





}




