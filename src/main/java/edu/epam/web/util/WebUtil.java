package edu.epam.web.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil {
    public static void forwardJSP(String page, HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/" + page + ".jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
