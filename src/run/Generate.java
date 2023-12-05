package run;

import scaffolding.Scaffold;

public class Generate {
    public static void main(String[] args) {
        try {
            System.out.println("generating...");
            //Scaffold.generate("nba", "joueur", "joueur","t","java");
            //Scaffold.generate("commerce", "service", "service","t","java");
            Scaffold.generateController("commerce", "service", "service","t","java");
//Scaffold.generate(args[0], args[1], args[2],args[3],"csharp");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
