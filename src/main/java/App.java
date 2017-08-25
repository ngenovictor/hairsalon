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

        post("/stylists/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String stylistName = request.queryParams("name");
            String telno = request.queryParams("telno");
            Stylist newStylist = new Stylist(stylistName,telno);
            newStylist.save();
            response.redirect("/");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylist/:stylist_id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params("stylist_id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }

}
