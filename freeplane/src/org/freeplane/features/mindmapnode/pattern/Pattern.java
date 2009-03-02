package org.freeplane.features.mindmapnode.pattern;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.freeplane.core.util.LogTool;
import org.freeplane.features.common.edge.EdgeModel;
import org.freeplane.n3.nanoxml.IXMLElement;
import org.freeplane.n3.nanoxml.IXMLParser;
import org.freeplane.n3.nanoxml.IXMLReader;
import org.freeplane.n3.nanoxml.StdXMLReader;
import org.freeplane.n3.nanoxml.XMLElement;
import org.freeplane.n3.nanoxml.XMLException;
import org.freeplane.n3.nanoxml.XMLParserFactory;
import org.freeplane.n3.nanoxml.XMLWriter;

// TODO ARCH rladstaetter 02.03.2009 class will vanish over time and be replaced by its attributes where feasible.
public class Pattern implements Cloneable {
	public static Pattern unMarshall(final IXMLElement xmlPattern) {
		final Pattern pattern = new Pattern();
		pattern.unMarshallImpl(xmlPattern);
		return pattern;
	}

	public static Pattern unMarshall(final String patternString) {
		try {
			final IXMLParser parser = XMLParserFactory.createDefaultXMLParser();
			final IXMLReader xmlReader = new StdXMLReader(new StringReader(patternString));
			parser.setReader(xmlReader);
			final IXMLElement xml = (IXMLElement) parser.parse();
			return Pattern.unMarshall(xml);
		}
		catch (final XMLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String name;
	private final Edge edge = new Edge();
	//// TODO rladstaetter 18.02.2009 make following attributes typesafe, see edge as an example
	private PatternProperty child;	
	private String icon;
	private PatternProperty nodeBackgroundColor;
	private PatternProperty nodeColor;
	private PatternProperty nodeFontBold;
	private PatternProperty nodeFontItalic;
	private PatternProperty nodeFontName;
	private PatternProperty nodeFontSize;
	private PatternProperty nodeStyle;
	private PatternProperty nodeText;
	private PatternProperty script;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getName() {
		return name;
	}

	public PatternProperty getPatternChild() {
		return child;
	}

	public String getPatternEdgeColor() {
		return edge.getColor();
	}

	public String getPatternEdgeStyle() {
		return edge.getStyle();
	}

	public String getPatternEdgeWidth() {
		return edge.getWidth();
	}


	public String getPatternIcon() {
		return icon;
	}

	public PatternProperty getPatternNodeBackgroundColor() {
		return nodeBackgroundColor;
	}

	public PatternProperty getPatternNodeColor() {
		return nodeColor;
	}

	public PatternProperty getPatternNodeFontBold() {
		return nodeFontBold;
	}

	public PatternProperty getPatternNodeFontItalic() {
		return nodeFontItalic;
	}

	public PatternProperty getPatternNodeFontName() {
		return nodeFontName;
	}

	public PatternProperty getPatternNodeFontSize() {
		return nodeFontSize;
	}

	public PatternProperty getPatternNodeStyle() {
		return nodeStyle;
	}

	public PatternProperty getPatternNodeText() {
		return nodeText;
	}

	public PatternProperty getPatternScript() {
		return script;
	}

	private void marshall(final IXMLElement xml, final String string, final Object pattern) {
		// FIXME rladstaetter method not implemented
		//		throw new UnsupportedOperationException();
		LogTool.warn("not implemented for " + xml.getClass().getSimpleName() + "," + string + "," + pattern);
	}

	
	// TODO ARCH rladstaetter 02.03.2009 get to a common stream handling, e.g.: public Writer marshall(Writer w), or use streams or stringbuilder
	public String marshall() {
		final IXMLElement xml = new XMLElement("pattern");
		xml.setAttribute("name", name);
		marshall(xml, "pattern_node_background_color", nodeBackgroundColor);
		marshall(xml, "pattern_node_color", nodeColor);
		marshall(xml, "pattern_node_style", nodeStyle);
		marshall(xml, "pattern_node_text", nodeText);
		marshall(xml, "pattern_node_font_name", nodeFontName);
		marshall(xml, "pattern_node_font_bold", nodeFontBold);
		marshall(xml, "pattern_node_font_italic", nodeFontItalic);
		marshall(xml, "pattern_node_font_size", nodeFontSize);
		marshall(xml, "pattern_node_icon", icon);
		marshall(xml, "pattern_node_edge_color", edge.getColor());
		marshall(xml, "pattern_node_edge_style", edge.getStyle());
		marshall(xml, "pattern_node_wdge_width", edge.getWidth());
		marshall(xml, "pattern_node_child", child);
		marshall(xml, "pattern_node_script", script);
		final StringWriter string = new StringWriter();
		final XMLWriter writer = new XMLWriter(string);
		try {
			writer.write(xml);
			return string.toString();
		}
		catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPatternChild(final PatternProperty patternChild) {
		this.child = patternChild;
	}

	public void setPatternIcon(final String patternIcon) {
		this.icon = icon;
	}

	public void setPatternNodeBackgroundColor(final PatternProperty patternNodeBackgroundColor) {
		this.nodeBackgroundColor = patternNodeBackgroundColor;
	}

	public void setPatternNodeColor(final PatternProperty patternNodeColor) {
		this.nodeColor = patternNodeColor;
	}

	public void setPatternNodeFontBold(final PatternProperty patternNodeFontBold) {
		this.nodeFontBold = patternNodeFontBold;
	}

	public void setPatternNodeFontItalic(final PatternProperty patternNodeFontItalic) {
		this.nodeFontItalic = patternNodeFontItalic;
	}

	public void setPatternNodeFontName(final PatternProperty patternNodeFontName) {
		this.nodeFontName = patternNodeFontName;
	}

	public void setPatternNodeFontSize(final PatternProperty patternNodeFontSize) {
		this.nodeFontSize = patternNodeFontSize;
	}

	public void setPatternNodeStyle(final PatternProperty patternNodeStyle) {
		this.nodeStyle = patternNodeStyle;
	}

	public void setPatternNodeText(final PatternProperty patternNodeText) {
		this.nodeText = patternNodeText;
	}

	public void setPatternScript(final PatternProperty patternScript) {
		this.script = patternScript;
	}

	private void unMarshallImpl(final IXMLElement xmlPattern) {
		name = xmlPattern.getAttribute("name", null);
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_node_background_color");
			if (xmlProperty != null) {
				nodeBackgroundColor = new PatternProperty();
				nodeBackgroundColor.value = xmlProperty.getAttribute("value", null);
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_node_color");
			if (xmlProperty != null) {
				nodeColor = new PatternProperty();
				nodeColor.value = xmlProperty.getAttribute("value", null);
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_node_style");
			if (xmlProperty != null) {
				nodeStyle = new PatternProperty();
				nodeStyle.value = xmlProperty.getAttribute("value", null);
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_node_textr");
			if (xmlProperty != null) {
				nodeText = new PatternProperty();
				nodeText.value = xmlProperty.getAttribute("value", null);
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_node_font_name");
			if (xmlProperty != null) {
				nodeFontName = new PatternProperty();
				nodeFontName.value = xmlProperty.getAttribute("value", null);
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_font_bold");
			if (xmlProperty != null) {
				nodeFontBold = new PatternProperty();
				nodeFontBold.value = xmlProperty.getAttribute("value", null);
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_font_italic");
			if (xmlProperty != null) {
				nodeFontItalic = new PatternProperty();
				nodeFontItalic.value = xmlProperty.getAttribute("value", null);
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_font_size");
			if (xmlProperty != null) {
				nodeFontSize = new PatternProperty();
				nodeFontSize.value = xmlProperty.getAttribute("value", null);
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_icon");
			if (xmlProperty != null) {
				icon  = xmlProperty.getAttribute("value", null);
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_edge_color");
			if (xmlProperty != null) {
				edge.setColor(xmlProperty.getAttribute("value", null));
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_edge_style");
			if (xmlProperty != null) {
				edge.setStyle(xmlProperty.getAttribute("value", null));
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_edge_width");
			if (xmlProperty != null) {
				edge.setWidth(xmlProperty.getAttribute("value",null));
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_child");
			if (xmlProperty != null) {
				child = new PatternProperty();
				child.value = xmlProperty.getAttribute("value", null);
			}
		}
		{
			final IXMLElement xmlProperty = xmlPattern.getFirstChildNamed("pattern_script");
			if (xmlProperty != null) {
				script = new PatternProperty();
				script.value = xmlProperty.getAttribute("value", null);
			}
		}
	}

	public Edge getEdge() {
		return edge;
	}

	public void setPatternEdgeColor(String color) {
		edge.setColor(color);
    }

	public void setPatternEdgeStyle(String style) {
		edge.setStyle(style);	    
    }

	public void setPatternEdgeWidth(String width) {
	    edge.setWidth(width);
    }
}
