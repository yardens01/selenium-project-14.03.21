import java.io.File;

public class newLoggerFolder {
    static int i=1;

    //create a new logger folder in the existing project folder.
   public static void makeNewFolder()
    {
        File f=new File("loggerFolder");
        if(f.mkdir()){
        System.out.println("logger created");
        i++;}
        else{
            System.out.println("folder cannot be created");
        }
        
    } 


}
