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

//        routes
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Star Hair Salon");
            model.put("template","templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("stylist", request.session().attribute("stylist"));
            model.put("stylists", Stylist.all());
            model.put("title", "Star Salon");
            model.put("template","templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("clients", Client.all());
            model.put("stylists", Stylist.all());
            model.put("title", "Clients");
            model.put("template", "templates/clients.vtl");
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
            model.put("title",stylist.getName());
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylist/:stylist_id/client/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params("stylist_id")));
            String name = request.queryParams("name");
            String telno = request.queryParams("telno");
            Client newClient = new Client(name, telno, stylist.getId());
            newClient.save();
            String url = String.format("/stylist/%d",stylist.getId());
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("clients/:client_id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Client thisClient = Client.find(Integer.parseInt(request.params("client_id")));
            model.put("client", thisClient);
            model.put("title",thisClient.getName());
            model.put("stylists", Stylist.all());
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylist/:stylist_id/edit", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params("stylist_id")));
            String name = request.queryParams("name");
            String telno = request.queryParams("telno");
            stylist.update(name,telno);

            String url = String.format("/stylist/%d",stylist.getId());
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylist/:stylist_id/delete", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params("stylist_id")));
            stylist.delete();
            response.redirect("/");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/client/:client_id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Integer clientid = Integer.parseInt(request.params("client_id"));
            Client client = Client.find(clientid);
            Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylist")));
            String name = request.queryParams("name");
            String telno = request.queryParams("telno");
            client.update(name, telno, stylist.getId());

            String url = String.format("/clients/%d",clientid);
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/client/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String telno = request.queryParams("telno");
            int stylistId = Integer.parseInt(request.queryParams("stylist"));
            Client newClient = new Client(name, telno, stylistId);
            newClient.save();
            response.redirect("/clients");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/client/:client_id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Client thisClient = Client.find(Integer.parseInt(request.params("client_id")));
            thisClient.delete();
            response.redirect("/clients");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }

}
