import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet (value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String timeZoneParam = req.getParameter("timezone");
        String query = timeZoneParam.substring(4);
        String timeZone;
        String pattern = "yyyy-MM-dd HH:mm:ss " + "UTC";
        if (timeZoneParam.contains("+")) {
            timeZone = LocalDateTime.now()
                    .plusHours(Long.parseLong(query))
                    .format(DateTimeFormatter.ofPattern(pattern + "+" + query));
        } else if (timeZoneParam.contains("-")){
            timeZone = LocalDateTime.now()
                    .minusHours(Long.parseLong(query))
                    .format(DateTimeFormatter.ofPattern(pattern + "-" + query));
        } else {
            timeZone = LocalDateTime
                    .now()
                    .format(DateTimeFormatter.ofPattern(pattern + timeZoneParam));
        }

        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(timeZone);
        resp.getWriter().close();
    }
}
