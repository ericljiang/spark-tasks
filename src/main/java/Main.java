import static spark.Spark.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.sql2o.Sql2o;
import org.sql2o.quirks.PostgresQuirks;

import model.LocalDateTimeConverter;
import model.TaskDao;
import model.TaskDaoSQL;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {
    public static void main(String[] args) {
        VelocityTemplateEngine templateEngine = new VelocityTemplateEngine();
        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost/tasks", "ericjiang", "",
                new PostgresQuirks() {
            {
                converters.put(LocalDateTime.class, new LocalDateTimeConverter());
            }
        });
        TaskDao dao = new TaskDaoSQL(sql2o);
        
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("tasks", dao.getAllTasks());
            return new ModelAndView(model, "index.vm");
        }, templateEngine);
    }
}