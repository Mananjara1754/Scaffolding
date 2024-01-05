package run;

import java.util.List;
import scaffolding.DynamicClassGenerator;
import scaffolding.MyClasse;
import scaffolding.Scaffold;
import util.StringUtil;

public class Generate {
    public static void main(String[] args) {
        try {
            System.out.println("generating...");
//            List<String> ls = StringUtil.getLibraries("spring");
//            for (String l : ls) {
//                System.out.println(l);
//            }
          
            //Scaffold.generate("nba", "joueur", "joueur","t","java");
            //Scaffold.generate("commerce", "service", "service","t","java");
            Scaffold.generateController("baovola", "look", "look","t","dotnet");
            //Scaffold.generate(args[0], args[1], args[2],args[3],"csharp");
            //MyClasse m = DynamicClassGenerator.getClass("postgres", "java","timestamp");
            //System.out.println(" " + m.getClassName() + " "+m.getPackageName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
