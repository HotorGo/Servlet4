package ru.mirea;

import freemarker.cache.FileTemplateLoader;
import freemarker.core.HTMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TodoServlet extends HttpServlet {

    private TodoModel model = new DBTodoModel();

    private final Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

    public TodoServlet() throws IOException {
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(new FileTemplateLoader(new File(".")));
        configuration.setOutputFormat(HTMLOutputFormat.INSTANCE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Template template = configuration.getTemplate("todo.html");
        Map<String, Object> data = new HashMap<>();
        data.put("items", model.getItems());
        try {
            template.process(data, resp.getWriter());
        } catch (TemplateException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskId = req.getParameter("taskId");
        String taskText = req.getParameter("taskText");
        if (taskId != null) {
            int id = Integer.parseInt(taskId);
            model.delete(id);
        } else if (taskText != null) {
            model.add(taskText);
        }
        resp.sendRedirect("todo.html");
    }
}
