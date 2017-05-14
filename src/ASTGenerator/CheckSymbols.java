package ASTGenerator; /**
 * Created by saeideh on 12/25/16.
 */

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.io.InputStream;
import ANTLRParser.*;
import Symbols.*;
public class CheckSymbols {
    public static Symbol.AccessModifier getAccessmodifierType(int tokenType) {
        switch ( tokenType ) {
            case javaParser.PUBLIC : return  Symbol.AccessModifier.tpublic;
            case javaParser.PRIVATE : return Symbol.AccessModifier.tprivate;
            case javaParser.PROTECTED: return  Symbol.AccessModifier.tprotected;
            case javaParser.FINAL: return Symbol.AccessModifier.TFINAL;
            case javaParser.STATIC:  return  Symbol.AccessModifier.TSTATIC;
            case javaParser.ABSTRACT:  return  Symbol.AccessModifier.TABSTRACT;

        }

        return Symbol.AccessModifier.tINVALID;
    }

}
