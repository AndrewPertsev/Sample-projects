package by.epam.heritage.ap.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom tag copyright
 * @see TagSupport
 */
public class CopyrightTag extends TagSupport {
	private static final Logger logger = LogManager.getLogger(CopyrightTag.class);
	private static final String FOOTER = "Heritage 2022";
	private static final String FOOTER_TAG_START = "<footer>";
	private static final String FOOTER_TAG_END = "</footer>";
	private static final String P_TAG_START = "<p>";
	private static final String P_TAG_END = "</p>";

	@Override
	public int doStartTag() throws JspTagException {
		try {
			JspWriter out = pageContext.getOut();
			out.write(FOOTER_TAG_START);
			out.write(P_TAG_START);
			out.write(FOOTER);
			out.write(P_TAG_END);
			out.write(FOOTER_TAG_END);

		} catch (IOException e) {
			logger.error(" IO custom tag error occurs", e);
			throw new JspTagException(e);
		}
		return SKIP_BODY;
	}


}
