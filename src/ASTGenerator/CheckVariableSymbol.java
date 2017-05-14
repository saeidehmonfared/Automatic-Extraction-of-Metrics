package ASTGenerator;
import ANTLRParser.*;
import Symbols.*;

/**
 * Created by saeideh on 12/25/16.
 */
public class CheckVariableSymbol {
    public static VariableSymbol.TYPE getType(int tokenType) {
        switch ( tokenType ) {
            case javaParser.INT:
                return VariableSymbol.TYPE.TINT;
            case javaParser.FLOAT:
                return VariableSymbol.TYPE.TFLOAT;
            case javaParser.DOUBLE:
                return VariableSymbol.TYPE.TDOUBLE;
            case javaParser.CHAR:
                return VariableSymbol.TYPE.TCHAR;
            case javaParser.LONG:
                return VariableSymbol.TYPE.TLONG;
            case javaParser.SHORT:
                return VariableSymbol.TYPE.TSHORT;
            case javaParser.BYTE:
                return VariableSymbol.TYPE.TBYTE;
            case javaParser.BOOLEAN:
                return VariableSymbol.TYPE.TBOOLean;
            case javaParser.Identifier:
                return VariableSymbol.TYPE.TREFRENCE;
            case javaParser.VOID: return VariableSymbol.TYPE.TVOid;
        }
        return VariableSymbol.TYPE.TINVALID;


    }
}
