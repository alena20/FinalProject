package com.epam.finalproject.tag;

import com.epam.finalproject.model.entity.User;
import com.epam.finalproject.util.SessionAttributeName;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Tag generates code, that adds additional functionality to definite table
 */
public class TableUtilityTag extends SimpleTagSupport {
    private String tableId;
    private int order = 0;
    private String direction = "desc";

    @Override
    public void doTag() throws JspException, IOException {
        JspContext context = getJspContext();
        JspWriter out = context.getOut();
        User user = (User) context.findAttribute(SessionAttributeName.USER);
        String locale = user.getAccount().getLocale().getPostfix();
        String script =
                "<script>\n" +
                        "   $(document).ready(function () {\n" +
                        "       $('#" + tableId + "').DataTable({\n" +
                        "       \"order\": [[ " + order + ", \"" + direction + "\" ]],\n" +
                        "       \"language\": {\"url\": \"/assets/json/dataTables." + locale + ".json\"}\n" +
                        "       });\n" +
                        "       $('.dataTables_length').addClass('bs-select');\n" +
                        "   });\n" +
                        "</script>";
        out.write(script);
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Gets table id.
     *
     * @return the table id
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * Sets table id.
     *
     * @param tableId the table id
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
