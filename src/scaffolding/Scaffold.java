package scaffolding;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import util.DbConfigReader;

public class Scaffold {
    String motdepasse;
    Table table;
    Modele modele;
    String language;
    public static void generateController(String motdepasse, String table, String nomModele,String nompackage,String language) throws Exception{
        Scaffold scaffold = new Scaffold(motdepasse, table, nomModele,nompackage,language);
        DbConfigReader reader = new DbConfigReader();
        DBConfig dbconfigs = reader.read();
        scaffold.getTable().getColonnes(motdepasse,dbconfigs,language);
        scaffold.createControllerFile();
    }
    public static void generate(String motdepasse, String table, String nomModele,String nompackage,String language) throws Exception{
        Scaffold scaffold = new Scaffold(motdepasse, table, nomModele,nompackage,language);
        DbConfigReader reader = new DbConfigReader();
        DBConfig dbconfigs = reader.read();
        scaffold.getTable().getColonnes(motdepasse,dbconfigs,language);
        scaffold.createControllerFile();
    }

    public Scaffold(String motdepasse, String table, String nomModele,String nompackage,String langage) {
        setLanguage(langage);
        setMotdepasse(motdepasse);
        setTable(table);
        if(nomModele.length()==0){
            nomModele = this.table.getNomTable();
        }
        this.setModele(new Modele(nomModele,nompackage));
        
    }
    public void createControllerFile() throws Exception{
        if(language.compareTo("java")==0){
            createSpringController();
        }
        else if(language.compareTo("csharp")==0){
            createCSharpController();            
        }
    }
    public void createModelFile() throws Exception{
        if(language.compareTo("java")==0){
            createJavaModel();
        }
        else if(language.compareTo("csharp")==0){
            createCSharpModel();
        }
    }
    public void createCSharpModel() throws Exception{
        createFile("TemplateCSHARP.cs", "cs");
    }
    public void createJavaModel() throws Exception {
        createFile("TemplateJAVA.java", "java");
    }
    public void createCSharpController() throws Exception{
        createController("TemplateCSHARP.cs", "cs");
    }
    public void createSpringController() throws Exception {
        createController("TemplateSpring.java", "java");
    }
    public void createController(String templateName,String extension) throws Exception{
        String templatePath = "E:/_S5/Framework/scaffolding/scaffolding/template_controller/"+templateName;
        byte[] bytes = Files.readAllBytes(Paths.get(templatePath));
        String contenuTemplate = new String(bytes);
        contenuTemplate = contenuTemplate.replace("##classname##", modele.getFileName());
        contenuTemplate = contenuTemplate.replace("##import##", table.getImportCode(language));
        contenuTemplate = contenuTemplate.replace("##package##", modele.getNompackage());
        contenuTemplate = contenuTemplate.replace("##EntityName##", modele.getFileName());       
        String newPath = modele.getFileName()+"."+extension;
        String chemin = "E:/_S5/ProjectNetBeans/Generating/src/Controller";
        System.out.println("Path Model "+ chemin+"/"+newPath);
        //Files.writeString(Paths.of(chemin+"/"+ newPath), contenuTemplate, StandardOpenOption.CREATE);
        Files.write(Paths.get(chemin, newPath), contenuTemplate.getBytes(), StandardOpenOption.CREATE);
        System.out.println("Scaffolding de controlleur réussi. Le fichier a été créé avec succès : " + newPath);
    }
    
    public void createFile(String templateName,String extension) throws Exception{
        // String templateDIR = System.getenv("templateDIR");
        // if(templateDIR == null){
        //     throw new Exception("Aucune variable d'environnement specifie pour templateDIR");
        // }
        String templatePath = "E:/_S5/Framework/scaffolding/scaffolding/templates/"+templateName;
        //String templatePath = templateDIR+"/"+templateName;
        //String contenuTemplate = Files.readString(Path.of(templatePath));
        byte[] bytes = Files.readAllBytes(Paths.get(templatePath));
        String contenuTemplate = new String(bytes);
        contenuTemplate = contenuTemplate.replace("##classname##", modele.getFileName());
        contenuTemplate = contenuTemplate.replace("##import##", table.getImportCode(language));
        contenuTemplate = contenuTemplate.replace("##package##", modele.getNompackage());
        contenuTemplate = contenuTemplate.replace("##attributs##", table.getAttributsCode());
        contenuTemplate = contenuTemplate.replace("##getterssetters##", table.getGettersSettersCode(language));
        String newPath=modele.getFileName()+"."+extension;
        String chemin = "E:/_S5/ProjectNetBeans/Generating/src/output";
        System.out.println("Path Model "+ chemin+"/"+newPath);
        //Files.writeString(Paths.of(chemin+"/"+ newPath), contenuTemplate, StandardOpenOption.CREATE);
        Files.write(Paths.get(chemin, newPath), contenuTemplate.getBytes(), StandardOpenOption.CREATE);
        System.out.println("Scaffolding réussi. Le fichier a été créé avec succès : " + newPath);
    }

    public String getMotdepasse() {
        return motdepasse;
    }
    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
    public Table getTable() {
        return table;
    }
    public void setTable(String table) {
        this.table = new Table(table);
    }
    public void setTable(Table table) {
        this.table = table;
    }
    public Modele getModele() {
        return modele;
    }
    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
  
}
