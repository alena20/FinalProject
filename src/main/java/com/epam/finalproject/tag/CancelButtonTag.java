package com.epam.finalproject.tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Tag generates code of button, that has cancel training functionality.
 */
public class CancelButtonTag extends SimpleTagSupport {
    private int trainingId;
    private int clientId;
    private String tooltip = "cancel";

    @Override
    public void doTag() throws IOException {
        JspContext context = getJspContext();
        JspWriter out = context.getOut();
        String html =
                "<form action=\"/mainController\" method=\"post\">\n" +
                        "   <input type=\"hidden\" name=\"command\" value=\"cancel_training\"/>\n" +
                        "   <input type=\"hidden\" name=\"trainingId\" value=\"" + trainingId + "\"/>\n" +
                        "   <input type=\"hidden\" name=\"clientId\" value=\"" + clientId + "\"/>\n" +
                        "   <button type=\"submit\" rel=\"tooltip\" class=\"btn btn-outline-danger btn-round\" data-toggle=\"tooltip\" title=\"" + tooltip + "\">\n" +
                        "       <i class=\"material-icons\">close</i>\n" +
                        "   </button>\n" +
                        "</form>";
        out.write(html);
    }

    /**
     * Gets training id.
     *
     * @return the training id
     */
    public int getTrainingId() {
        return trainingId;
    }

    /**
     * Sets training id.
     *
     * @param trainingId the training id
     */
    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    /**
     * Gets tooltip.
     *
     * @return the tooltip
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * Sets tooltip.
     *
     * @param tooltip the tooltip
     */
    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * Gets clientId.
     *
     * @return the client id
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets clientId.
     *
     * @param clientId the client id
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
