import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args){
//        Set port in case of deploying to services that set port
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if(process.environment().get("PORT")!= null){
            port = Integer.parseInt(process.environment().get("PORT"));
        }else{
            port = 4567;
        }
        port(port);

//        static file directory
        staticFileLocation("public");

//        base template
        String layout = "templates/layout.vtl";

//        home page
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("stylist", request.session().attribute("stylist"));
            model.put("stylists", Stylist.all());
            model.put("template","templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }

}
