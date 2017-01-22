package MetricExtraction.CouplingExtraction;
import ANTLRParser.*;
import ASTGenerator.Inheritancelist;
import ASTGenerator.StaticList;
import Scopes.ClassScope;
import Scopes.GlobalScope;
import Scopes.Scope;
import Symbols.Symbol;
import java.lang.String;
import com.sun.org.apache.xpath.internal.SourceTree;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by saeideh on 1/14/17.
 */
public class CouplingMetrics extends javaBaseListener {

    ArrayList<Symbol> Couplinglistofclass = new ArrayList<Symbol>();

    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope Currentscope;
    Map<Symbol, String> refrencesofclass = new LinkedHashMap<Symbol, String>();
    Map<String, Symbol> importlistofclass = new LinkedHashMap<String, Symbol>();
// classname;
    String packagename;
    public  Map<String, ArrayList<Invoc>> Couplinglist = new LinkedHashMap<String, ArrayList<Invoc>>();
    public ArrayList<Object> objectinstances = new ArrayList<Object>();


    public ArrayList<String> Inheritancelistofclass = new ArrayList<String>();
    Scope currentScope;
    String assignmentclassname=null;
    String assignmentname=null;
    String leftofassignment;

    public CouplingMetrics(GlobalScope globals, ParseTreeProperty<Scope> Scopes, Map<Symbol, String> refrences, Map<String, Symbol> importlist, ArrayList<String> inheritancelistofclass, ArrayList<Object> objectinstances) {
        this.globals = globals;
        this.scopes = Scopes;
        this.refrencesofclass = refrences;
        this.importlistofclass = importlist;
        this.Inheritancelistofclass = inheritancelistofclass;
        this.objectinstances = objectinstances;

    }


    //-----------------------------------------------------------------------

    public void enterCompilationUnit(javaParser.CompilationUnitContext ctx) {
        Currentscope = globals;

    }

    //--------------------------------------------------------------------------


    public void exitCompilationUnit(javaParser.CompilationUnitContext ctx) {


        //Couplinglist.put(packagename+classname,Couplinglistofclass);


        Iterator<Symbol> it = Couplinglistofclass.iterator();
        while (it.hasNext()) {
            Symbol name1 = it.next();
            for (String value : Couplinglist.keySet()) {
                String name = value;

            }
            //Couplinglist.put(name1.packagename+name1.name,)

        }
        for (String value : Couplinglist.keySet()) {
            String name = value;


            ArrayList<Invoc> mylist = new ArrayList<Invoc>();
            mylist = Couplinglist.get(name);
            System.out.println("coupling list iiiiiiiiiiiiiiiiiiiis:" + name);
            Iterator<Invoc> it1 = Couplinglist.get(name).iterator();
            while (it1.hasNext()) {
                Invoc name1 = it1.next();
                System.out.println(name1.name+"  "+ name1.invoc+" "+name1.relationType.toString());


            }
        }
    }

    //-----------------------------------------------------------------------------
    //public void enterNormalClassDeclaration(javaParser.NormalClassDeclarationContext ctx) {
        //classname = ctx.getText();

       // System.out.println("Name of class is:" + ctx.Identifier());
       // Couplinglist.put(packagename + classname, null);

  //  }
    //----------------------------------------------------------------------------

    @Override
    public void enterPackageDeclaration(javaParser.PackageDeclarationContext ctx) {

        for (int i = 1; i < ctx.getChildCount() - 1; i++) {
            packagename = packagename + ctx.getChild(i).getText();
        }
        packagename = packagename + ".";

        // System.out.println("mypackname:"+packagename);
    }

    @Override
    public void exitPackageDeclaration(javaParser.PackageDeclarationContext ctx) {


    }

    @Override public void enterNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) {
        currentScope=scopes.get(ctx);
    }

    @Override public void exitNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) { }


    @Override public void enterNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {currentScope=scopes.get(ctx); }

    @Override public void exitNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) { }

    //----------------------------------------------------------------------------------------

    @Override
    public void enterMethodDeclaration(javaParser.MethodDeclarationContext ctx) {
        currentScope = scopes.get(ctx);
    }

    @Override
    public void exitMethodDeclaration(javaParser.MethodDeclarationContext ctx) {
       // currentScope = currentScope.getEnclosingScope();
    }
    //----------------------------------------------------------------------------------

    @Override
    public void enterExpertionName2(javaParser.ExpertionName2Context ctx) {

        String name = ctx.ambiguousName().Identifier().toString();

        String classname=null;

        boolean coupling = true;

        Invoc.RelationType relation = Invoc.RelationType.INVALID;

        Iterator<Object> it0 = objectinstances.iterator();
        while (it0.hasNext()) {
            Object name1 = it0.next();
            if (name.equals(name1.symbol.name)) {
                classname = name1.classname;

                if (!(name1.currentscope.getScopeName().equals("Class"))) {
                    relation = Invoc.RelationType.DEPENDENCY;
                } else if(name1.currentscope.getScopeName().equals("Class")) {
                    relation = Invoc.RelationType.ASSOSIATION;
                }
                break;

            }
        }


        Invoc inv = new Invoc(ctx.Identifier().getText(), Invoc.InvocType.ATTRIBUTEINVOC, relation);
        //be in nokte deghat kon k momken ast dar packagehay mokhtalef classhay hamname dashte bashim, felan in ro dar nazar nagerefti
        Symbol s1=null;
        boolean r=false;
        for (Symbol value1 : importlistofclass.values()) {
            s1 = value1;

            if (s1.name.equals(classname)) {
                Iterator<String> it = Inheritancelistofclass.iterator();
                while (it.hasNext()) {
                    String name2 = it.next();
                    if ((classname.equals(name2))) {
                        coupling = false;
                        break;

                    }
                }
                r=true;
                break;





            }
        }
        if (coupling && r) {

            String keyname = s1.packagename + s1.name;
            boolean f = false;
            String s = null;
            for (String value : Couplinglist.keySet()) {
                s = value;
                if (s.equals(keyname)) {
                    f = true;
                    break;
                }
                // Couplinglist.get(s).add()

            }

            if (f) {
                boolean h = true;

                Iterator<Invoc> it3 = Couplinglist.get(keyname).iterator();
                while (it3.hasNext()) {
                    Invoc name2 = it3.next();
                    if ((name2.name.equals(inv.name))) {
                        h = false;
                        break;

                    }
                }

                if (h) {
                    Couplinglist.get(keyname).add(inv);
                }

            }
            else if(!(f)){
                ArrayList<Invoc>invoclist=new ArrayList<Invoc>();

                Couplinglist.put(keyname,invoclist);
                Couplinglist.get(keyname).add(inv);
            }

        }

    }
    //---------------------------------------------------------------------------
    @Override public void enterClassInstanceCreationExpression_lfno_primary(javaParser.ClassInstanceCreationExpression_lfno_primaryContext ctx) {
                assignmentname=ctx.Identifier(0).getText();
               assignmentclassname=ctx.Identifier().get(0).getText();
        //System.out.println(assignmentclassname+"maaaaaaaaaaaaaaan");

    }
    //--------------------------------------------------------

    @Override public void exitClassInstanceCreationExpression_lfno_primary(javaParser.ClassInstanceCreationExpression_lfno_primaryContext ctx) { }

    @Override
    public void exitExpertionName2(javaParser.ExpertionName2Context ctx) {
    }
    //------------------------------------------------------------------------------

    @Override public void enterAssignment(javaParser.AssignmentContext ctx) {


    }
    @Override public void exitAssignment(javaParser.AssignmentContext ctx) {

        assignmentname=null;
        leftofassignment=ctx.leftHandSide().expressionName().getText();

    }

    //--------------------------------------------------------------------------------

    @Override
    public void enterMethodInvoc2(javaParser.MethodInvoc2Context ctx) {

        String objectname = ctx.typeName().getText();
        String classname=null;

        boolean coupling = true;

        Invoc.RelationType relation = Invoc.RelationType.INVALID;

        Iterator<Object> it0 = objectinstances.iterator();
        while (it0.hasNext()) {
            Object name1 = it0.next();
            if (objectname.equals(name1.symbol.name)) {
                classname = name1.classname;

                if (!(name1.currentscope.getScopeName().equals("Class"))) {
                    relation = Invoc.RelationType.DEPENDENCY;
                } else if(name1.currentscope.getScopeName().equals("Class")) {
                    relation = Invoc.RelationType.ASSOSIATION;
                }
                break;

            }
        }


            Invoc inv = new Invoc(ctx.Identifier().getText(), Invoc.InvocType.METHODINVOC, relation);
            //be in nokte deghat kon k momken ast dar packagehay mokhtalef classhay hamname dashte bashim, felan in ro dar nazar nagerefti
        Symbol s1=null;
        boolean r=false;
            for (Symbol value1 : importlistofclass.values()) {
                s1 = value1;
                if (s1.name.equals(classname)) {
                    Iterator<String> it = Inheritancelistofclass.iterator();
                    while (it.hasNext()) {
                        String name2 = it.next();
                        if ((classname.equals(name2))) {
                            coupling = false;
                            break;

                        }
                    }
                         r=true;
                        break;





                }
            }
                    if (coupling && r) {

                        String keyname = s1.packagename + s1.name;
                        boolean f = false;
                        String s = null;
                        for (String value : Couplinglist.keySet()) {
                            s = value;
                            if (s.equals(keyname)) {
                                f = true;
                                break;
                            }
                            // Couplinglist.get(s).add()

                        }

                        if (f) {
                            boolean h = true;

                            Iterator<Invoc> it3 = Couplinglist.get(keyname).iterator();
                            while (it3.hasNext()) {
                                Invoc name2 = it3.next();
                                if ((name2.name.equals(inv.name))) {
                                    h = false;
                                    break;

                                }
                            }

                            if (h) {
                                Couplinglist.get(keyname).add(inv);
                            }

                        }
                        else if(!(f)){
                            ArrayList<Invoc>invoclist=new ArrayList<Invoc>();

                            Couplinglist.put(keyname,invoclist);
                            Couplinglist.get(keyname).add(inv);
                        }

                    }
                }
//--------------------------------------------------------------------
@Override public void enterMethodinvocation_lfno_primary2(javaParser.Methodinvocation_lfno_primary2Context ctx) {

    String objectname = ctx.typeName().getText();
    String classname=null;

    boolean coupling = true;

    Invoc.RelationType relation = Invoc.RelationType.INVALID;

    Iterator<Object> it0 = objectinstances.iterator();
    while (it0.hasNext()) {
        Object name1 = it0.next();
        if (objectname.equals(name1.symbol.name)) {
            classname = name1.classname;

            if (!(name1.currentscope.getScopeName().equals("Class"))) {
                relation = Invoc.RelationType.DEPENDENCY;
            } else if(name1.currentscope.getScopeName().equals("Class")) {
                relation = Invoc.RelationType.ASSOSIATION;
            }
            break;

        }
    }


    Invoc inv = new Invoc(ctx.Identifier().getText(), Invoc.InvocType.METHODINVOC, relation);
    //be in nokte deghat kon k momken ast dar packagehay mokhtalef classhay hamname dashte bashim, felan in ro dar nazar nagerefti
    Symbol s1=null;
    for (Symbol value1 : importlistofclass.values()) {
        s1 = value1;
        if (s1.name.equals(classname)) {
            Iterator<String> it = Inheritancelistofclass.iterator();
            while (it.hasNext()) {
                String name2 = it.next();
                if ((classname.equals(name2))) {
                    coupling = false;
                    break;

                }


            }
            break;
        }
    }
    if (coupling) {

        String keyname = s1.packagename + s1.name;
        boolean f = false;
        String s = null;
        for (String value : Couplinglist.keySet()) {
            s = value;
            if (s.equals(keyname)) {
                f = true;
                break;
            }
            // Couplinglist.get(s).add()

        }

        if (f) {
            boolean h = true;

            Iterator<Invoc> it3 = Couplinglist.get(keyname).iterator();
            while (it3.hasNext()) {
                Invoc name2 = it3.next();
                if ((name2.name.equals(inv.name))) {
                    h = false;
                    break;

                }
            }

            if (h) {
                Couplinglist.get(keyname).add(inv);
            }

        }
        else if(!(f)){
            ArrayList<Invoc>invoclist=new ArrayList<Invoc>();

            Couplinglist.put(keyname,invoclist);
            Couplinglist.get(keyname).add(inv);
        }

    }

}
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitMethodinvocation_lfno_primary2(javaParser.Methodinvocation_lfno_primary2Context ctx) { }







        /*for (Symbol value :refrencesofclass.keySet()) {
            Symbol s = value;
            if (s.name.equals(objectname)) {
                classname = refrencesofclass.get(s);

            }
        }

            for (Symbol value1 :importlistofclass.values()) {
                Symbol s1=value1;
                if(s1.name.equals(classname)){
                    Iterator<String> it=Inheritancelistofclass.iterator();
                    while (it.hasNext()){
                        String name1=it.next();
                        if((classname.equals(name1)))
                        {
                            coupling=false;
                            break;

                        }


                    }
                    if(coupling){


                        boolean b=true;
                        for(int i=0;i<Couplinglistofclass.size();i++)
                        {
                           if(Couplinglistofclass.get(i).name.equals(s1.name))
                            {
                                b=false;
                               break;
                            }
                       }
                       if(b)
                        {
                            Couplinglistofclass.add(s1);

                        }


                        break;

                    }

                }
            }*/








    @Override public void exitMethodInvoc2(javaParser.MethodInvoc2Context ctx) { }
}


