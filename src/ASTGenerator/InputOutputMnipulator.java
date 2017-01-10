package ASTGenerator;

import java.io.File;
import java.io.IOException;
import java.util.Date;
//import org.apache.commons.io.FileUtils;

    public class InputOutputMnipulator {
        private String inDir;
        private String outDir;

        public InputOutputMnipulator(String inDir, String outDir) throws Exception {
            this.inDir = inDir;
            this.outDir = outDir;
            boolean okIn = this.checkInDir(inDir);
            boolean okOut = this.checkOutDir(outDir);
            boolean initOk = okIn && okOut;
            if(initOk) {
                System.out.println("Creating InputOutputManipulator (" + (new Date()).toString() + ")");
            } else {
                throw new Exception("Unable to create InputOutputManipulator");
            }
        }

        private boolean checkInDir(String inDir) {
            File f = new File(inDir);
            if(!f.exists()) {
                System.out.println("InputOutputManipulator error, " + inDir + " does not exist");
                return false;
            } else if(!f.isDirectory()) {
                System.out.println("InputOutputManipulator error, " + inDir + " is not directory");
                return false;
            } else if(!f.canRead()) {
                System.out.println("InputOutputManipulator error, " + inDir + " cannot be read");
                return false;
            } else {
                this.inDir = f.getAbsolutePath();
                return true;
            }
        }

        private boolean checkOutDir(String outDir) {
            File f = new File(outDir);
            if(f.exists() && f.isDirectory()) {
                System.out.println("InputOutputManipulator warning, outDir " + outDir + " already exists");
                System.out.println("Cleaning " + outDir);

                //try {
                //  FileUtils.cleanDirectory(f);
                //  } catch (IOException var4) {
                //     System.out.println("InputOutputManipulator error, IOException during directory cleaning");
                //   return false;
                // }

                this.outDir = f.getAbsolutePath();
                return true;
            } else {
                boolean created = f.mkdirs();
                if(!created) {
                    System.out.println("InputOutputManipulator error, directory " + outDir + " cannot be created");
                } else {
                    this.outDir = f.getAbsolutePath();
                }

                return created;
            }
        }

        public String getPureFileName(String compilationUnitPath) {
            int lastSlash = compilationUnitPath.lastIndexOf(File.separatorChar);
            return compilationUnitPath.substring(lastSlash + 1);
        }

        public String prepareOutdir(String compUnit) {
            String tmp = compUnit.substring(this.inDir.length());
            int lastSlash = tmp.lastIndexOf(File.separatorChar);
            String dirName = this.outDir + tmp.substring(0, lastSlash);
            File dir = new File(dirName);
            if(!dir.exists()) {
                boolean created = dir.mkdirs();
                if(!created) {
                    System.out.println("Output manipulator error, unable to prepare out directory " + dirName);
                }
            }

            return dirName;
        }
    }

