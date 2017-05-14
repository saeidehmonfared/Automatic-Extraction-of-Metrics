package ASTGenerator;

public class Main {

    public static void main(String[] args) throws Exception {
        String inDir = args[0];
        String outDir = args[1];


        ASTGenerator a= new ASTGenerator(inDir,outDir);
        a.Parse();


        // write your code here
    }
}
