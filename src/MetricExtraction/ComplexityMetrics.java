package MetricExtraction;
import ANTLRParser.*;
import Scopes.GlobalScope;
import Scopes.Scope;
import Symbols.Symbol;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by saeideh on 5/13/17.
 */
public class ComplexityMetrics extends javaBaseListener {
    public static Map<String,Map<String,Map<String,String>>>complexitylist=new LinkedHashMap<>();
    ParseTreeProperty<Scope> Scopes = new ParseTreeProperty<Scope>();
    GlobalScope globals;
    Scope currentScope;
    public String packagename="";
    public String classname="";
    public String methodname="";
    int iterationstatements=0;
    int selectionstatements=0;
    int jumpstatements=0;
    int declarations=0;
    int expressionStatement=0;

    public ComplexityMetrics(GlobalScope globals, ParseTreeProperty<Scope> Scopes){
        this.globals=globals;
        this.Scopes=Scopes;
    }

    public void enterCompilationUnit(javaParser.CompilationUnitContext ctx) {
        currentScope = this.globals;


    }

    public void exitCompilationUnit(javaParser.CompilationUnitContext ctx) {


    }
    //------------------------------------------------------------------------
    @Override public void enterPackageDeclaration(javaParser.PackageDeclarationContext ctx) {

        for(int i=1;i<ctx.getChildCount()-1;i++){
            packagename=packagename+ctx.getChild(i).getText();
        }
        packagename=packagename+".";

        // System.out.println("mypackname:"+packagename);
    }

    @Override public void exitPackageDeclaration(javaParser.PackageDeclarationContext ctx) {



    }
    //-----------------------------------------------------------------------------
    @Override public void enterNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) {
        currentScope = Scopes.get(ctx);
        classname=ctx.Identifier().getText();
        complexitylist.put(classname,new LinkedHashMap<>());
    }
    @Override public void exitNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) {

        classname="";
    }
    //-------------------------------------------------------------------------------
    @Override public void enterNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {
        currentScope=Scopes.get(ctx);
        classname=ctx.Identifier().getText();
        complexitylist.put(classname,new LinkedHashMap<>());
    }
    @Override public void exitNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {
        classname="";
    }
    //--------------------------------------------------------------------------------------------
    public void enterMethodDeclaration(javaParser.MethodDeclarationContext ctx) {
        currentScope = Scopes.get(ctx);

    }

    @Override
    public void exitMethodDeclaration(javaParser.MethodDeclarationContext ctx) {
        currentScope = currentScope.getEnclosingScope();
        if(classname!=null && methodname!=""){
        complexitylist.get(classname).get(methodname).put("iterationstatements",String.valueOf(iterationstatements));
        complexitylist.get(classname).get(methodname).put("selectionstatements",String.valueOf(selectionstatements));
        complexitylist.get(classname).get(methodname).put("jumpstatements",String.valueOf(jumpstatements));
        complexitylist.get(classname).get(methodname).put("declarations",String.valueOf(declarations));
        complexitylist.get(classname).get(methodname).put("expressionstatements",String.valueOf(expressionStatement));}
        selectionstatements=0;
        declarations=0;
        jumpstatements=0;
        expressionStatement=0;
        iterationstatements=0;
        methodname="";


    }




    //-----------------------------------------------------------------------------------------
    @Override public void enterMethodDeclarator(javaParser.MethodDeclaratorContext ctx) {
        methodname=ctx.Identifier().getText();
        if(classname!="") {
            complexitylist.get(classname).put(methodname, new LinkedHashMap<>());
            complexitylist.get(classname).get(methodname).put("selectionstatements", new String());
            complexitylist.get(classname).get(methodname).put("iterationstatements", new String());
            complexitylist.get(classname).get(methodname).put("jumpstatements", new String());
            complexitylist.get(classname).get(methodname).put("expressionstatements", new String());
            complexitylist.get(classname).get(methodname).put("declarations", new String());
        }

    }

    @Override public void exitMethodDeclarator(javaParser.MethodDeclaratorContext ctx) {


    }
    //------------------------------------------------------------------------------------------
    @Override public void enterConstructorDeclaration(javaParser.ConstructorDeclarationContext ctx) {
        methodname = ctx.constructorDeclarator().simpleTypeName().Identifier().toString();
        complexitylist.get(classname).put(methodname,new LinkedHashMap<>());
        if(classname!=null) {
            complexitylist.get(classname).put(methodname, new LinkedHashMap<>());
            complexitylist.get(classname).get(methodname).put("selectionstatements", new String());
            complexitylist.get(classname).get(methodname).put("iterationstatements", new String());
            complexitylist.get(classname).get(methodname).put("jumpstatements", new String());
            complexitylist.get(classname).get(methodname).put("expressionstatements", new String());
            complexitylist.get(classname).get(methodname).put("declarations", new String());
        }
    }
    @Override public void exitConstructorDeclaration(javaParser.ConstructorDeclarationContext ctx) {

//        currentScope = currentScope.getEnclosingScope();
        if(classname!=null){
            complexitylist.get(classname).get(methodname).put("iterationstatements",String.valueOf(iterationstatements));
            complexitylist.get(classname).get(methodname).put("selectionstatements",String.valueOf(selectionstatements));
            complexitylist.get(classname).get(methodname).put("jumpstatements",String.valueOf(jumpstatements));
            complexitylist.get(classname).get(methodname).put("declarations",String.valueOf(declarations));
            complexitylist.get(classname).get(methodname).put("expressionstatements",String.valueOf(expressionStatement));}
        selectionstatements=0;
        declarations=0;
        jumpstatements=0;
        expressionStatement=0;
        iterationstatements=0;
        methodname="";


        methodname="";
    }
    //----------------------------------------------------------------------------------------------------




    //for statements




    //------------------------------------------------------------------------------------------------
    @Override public void enterForStatement(javaParser.ForStatementContext ctx) {
        iterationstatements++;

    }

    @Override public void exitForStatement(javaParser.ForStatementContext ctx) {

    }
    //-----------------------------------------------------------------------------------
    @Override public void enterWhileStatement(javaParser.WhileStatementContext ctx) {
        iterationstatements++;
    }

    @Override public void exitWhileStatement(javaParser.WhileStatementContext ctx) { }
    //----------------------------------------------------------------------------------
    @Override public void enterDoStatement(javaParser.DoStatementContext ctx) {
        iterationstatements++;
    }

    @Override public void exitDoStatement(javaParser.DoStatementContext ctx) { }
    //-----------------------------------------------------------------------------------
    @Override public void enterForStatementNoShortIf(javaParser.ForStatementNoShortIfContext ctx) {
        iterationstatements++;
    }

    @Override public void exitForStatementNoShortIf(javaParser.ForStatementNoShortIfContext ctx) { }
    //--------------------------------------------------------------------------------------------
    @Override public void enterWhileStatementNoShortIf(javaParser.WhileStatementNoShortIfContext ctx) {
        iterationstatements++;
    }

    @Override public void exitWhileStatementNoShortIf(javaParser.WhileStatementNoShortIfContext ctx) { }

    //----------------------------------------------------------------------------------------------



    //selection statements

    @Override public void enterIfThenStatement(javaParser.IfThenStatementContext ctx) {
        selectionstatements++;
    }

    @Override public void exitIfThenStatement(javaParser.IfThenStatementContext ctx) { }
    //-----------------------------------------------------------------------------------
    @Override public void enterIfThenElseStatement(javaParser.IfThenElseStatementContext ctx) {
        selectionstatements++;
    }

    @Override public void exitIfThenElseStatement(javaParser.IfThenElseStatementContext ctx) { }
    //-------------------------------------------------------------------------------------
    @Override public void enterIfThenElseStatementNoShortIf(javaParser.IfThenElseStatementNoShortIfContext ctx) {
        selectionstatements++;
    }

    @Override public void exitIfThenElseStatementNoShortIf(javaParser.IfThenElseStatementNoShortIfContext ctx) { }
    //----------------------------------------------------------------------------------------

    @Override public void enterSwitchStatement(javaParser.SwitchStatementContext ctx) {
        selectionstatements++;
    }

    @Override public void exitSwitchStatement(javaParser.SwitchStatementContext ctx) { }


    //----------------------------------------------------------------------------------
    @Override public void enterTryStatement(javaParser.TryStatementContext ctx) {
        selectionstatements++;
    }

    @Override public void exitTryStatement(javaParser.TryStatementContext ctx) { }
    //---------------------------------------------------------------------------
    @Override public void enterCatchClause(javaParser.CatchClauseContext ctx) {
        selectionstatements++;
    }

    @Override public void exitCatchClause(javaParser.CatchClauseContext ctx) { }
    //-------------------------------------------------------------------------


    //jump statements

    @Override public void enterBreakStatement(javaParser.BreakStatementContext ctx) {
        jumpstatements++;
    }

    @Override public void exitBreakStatement(javaParser.BreakStatementContext ctx) { }
    //---------------------------------------------------------------------------------

    @Override public void enterContinueStatement(javaParser.ContinueStatementContext ctx) {
        jumpstatements++;
    }

    @Override public void exitContinueStatement(javaParser.ContinueStatementContext ctx) { }
    //---------------------------------------------------------------------------------------

    @Override public void enterReturnStatement(javaParser.ReturnStatementContext ctx) {
        jumpstatements++;
    }

    @Override public void exitReturnStatement(javaParser.ReturnStatementContext ctx) { }
    //------------------------------------------------------------------------------------

    @Override public void enterThrowStatement(javaParser.ThrowStatementContext ctx) {
        jumpstatements++;
    }

    @Override public void exitThrowStatement(javaParser.ThrowStatementContext ctx) { }
    //---------------------------------------------------------------------------------------
    @Override public void enterSynchronizedStatement(javaParser.SynchronizedStatementContext ctx) {
        jumpstatements++;
    }

    @Override public void exitSynchronizedStatement(javaParser.SynchronizedStatementContext ctx) { }
    //--------------------------------------------------------------------------------------







    //expressionStatement


    @Override public void enterMethodInvoc1(javaParser.MethodInvoc1Context ctx) {
        expressionStatement++;
    }

    @Override public void exitMethodInvoc1(javaParser.MethodInvoc1Context ctx) {}
    //------------------------------------------------------------------------------
    @Override public void enterMethodInvoc2(javaParser.MethodInvoc2Context ctx) {
        expressionStatement++;
    }

    @Override public void exitMethodInvoc2(javaParser.MethodInvoc2Context ctx) { }
    //-----------------------------------------------------------------
    @Override public void enterMethodInvoc3(javaParser.MethodInvoc3Context ctx) {
        expressionStatement++;
    }
    @Override public void exitMethodInvoc3(javaParser.MethodInvoc3Context ctx) { }
    //------------------------------------------------------------------------
    @Override public void enterMethodInvoc4(javaParser.MethodInvoc4Context ctx) {
        expressionStatement++;
    }
    @Override public void exitMethodInvoc4(javaParser.MethodInvoc4Context ctx) { }
    //---------------------------------------------------------------------

    @Override public void enterMethodIncoc5(javaParser.MethodIncoc5Context ctx) {
        expressionStatement++;
    }

    @Override public void exitMethodIncoc5(javaParser.MethodIncoc5Context ctx) { }
    //-------------------------------------------------------------------------------
    @Override public void enterMethodInvoc6(javaParser.MethodInvoc6Context ctx) {
        expressionStatement++;
    }

    @Override public void exitMethodInvoc6(javaParser.MethodInvoc6Context ctx) { }
    //-----------------------------------------------------------------------------
    @Override public void enterMethodInvocation_lf_primary(javaParser.MethodInvocation_lf_primaryContext ctx) {
        expressionStatement++;
    }

    @Override public void exitMethodInvocation_lf_primary(javaParser.MethodInvocation_lf_primaryContext ctx) { }
    //-----------------------------------------------------------------------------
    @Override public void enterMethodinvocation_lfno_primary1(javaParser.Methodinvocation_lfno_primary1Context ctx) {
        expressionStatement++;
    }

    @Override public void exitMethodinvocation_lfno_primary1(javaParser.Methodinvocation_lfno_primary1Context ctx) { }
    //-----------------------------------------------------------------------------
    @Override public void enterMethodinvocation_lfno_primary2(javaParser.Methodinvocation_lfno_primary2Context ctx) {
        expressionStatement++;
    }

    @Override public void exitMethodinvocation_lfno_primary2(javaParser.Methodinvocation_lfno_primary2Context ctx) { }
    //-----------------------------------------------------------------------------
    @Override public void enterMethodinvocation_lfno_primary3(javaParser.Methodinvocation_lfno_primary3Context ctx) {
        expressionStatement++;
    }

    @Override public void exitMethodinvocation_lfno_primary3(javaParser.Methodinvocation_lfno_primary3Context ctx) { }
    //-----------------------------------------------------------------------------
    @Override public void enterMethodinvocation_lfno_primary4(javaParser.Methodinvocation_lfno_primary4Context ctx) {
        expressionStatement++;
    }

    @Override public void exitMethodinvocation_lfno_primary4(javaParser.Methodinvocation_lfno_primary4Context ctx) { }
    //-----------------------------------------------------------------------------
    @Override public void enterMethodinvocation_lfno_primary5(javaParser.Methodinvocation_lfno_primary5Context ctx) {
        expressionStatement++;
    }
       @Override public void exitMethodinvocation_lfno_primary5(javaParser.Methodinvocation_lfno_primary5Context ctx) { }







    //-----------------------------------------------------------------------------
    //@Override public void enterAssignmentExpression(javaParser.AssignmentExpressionContext ctx) {

  //  }

  //  @Override public void exitAssignmentExpression(javaParser.AssignmentExpressionContext ctx) { }
    //---------------------------------------------------------------------------------------------



    @Override public void enterAssignment1(javaParser.Assignment1Context ctx) { expressionStatement++; }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitAssignment1(javaParser.Assignment1Context ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterAssignment2(javaParser.Assignment2Context ctx) { expressionStatement++; }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitAssignment2(javaParser.Assignment2Context ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterAssignment3(javaParser.Assignment3Context ctx) {  expressionStatement++;}
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitAssignment3(javaParser.Assignment3Context ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */





    //declarations

    @Override public void enterLocalVariableDeclarationStatement(javaParser.LocalVariableDeclarationStatementContext ctx) {
        declarations++;
    }

    @Override public void exitLocalVariableDeclarationStatement(javaParser.LocalVariableDeclarationStatementContext ctx) { }

    //----------------------------------------------------------------------------------------------------------
    @Override public void enterClassInstanceCreationExpression(javaParser.ClassInstanceCreationExpressionContext ctx) {

        //TODO:new shodan masalan dakhele paranteze yek method ke dare call mishe ro ham baresi kon k joze declarationnha dar nazar begiri ya na?
    }

    @Override public void exitClassInstanceCreationExpression(javaParser.ClassInstanceCreationExpressionContext ctx) { }


}
