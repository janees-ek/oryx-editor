package org.oryxeditor.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import layout.OryxBPMNLayouter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import de.hpi.bpmn.BPMNDiagram;
import de.hpi.bpmn.Node;
import de.hpi.bpmn.rdf.BPMN11RDFImporter;
import de.hpi.bpmn.rdf.BPMNRDFImporter;
/**
 * Copyright (c) 2009 Philip Effinger
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class BPMNLayouterServlet extends HttpServlet {
	private static final long serialVersionUID = -4589304713944851993L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			res.setContentType("application/xhtml");
			String rdf = req.getParameter("rdf");
			DocumentBuilder builder;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(rdf.getBytes("UTF-8")));
			processDocument(document, res.getWriter());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	protected void processDocument(Document document, PrintWriter writer) {
		String type = new StencilSetUtil().getStencilSet(document);
		BPMNDiagram diagram = null;
		if (type.equals("bpmn.json"))
			diagram = new BPMNRDFImporter(document).loadBPMN();
		else if (type.equals("bpmn1.1.json"))
			diagram = new BPMN11RDFImporter(document).loadBPMN();

		OryxBPMNLayouter layouter = new OryxBPMNLayouter();
		layouter.setDiagram(diagram);
		layouter.doLayout();
		diagram = layouter.getLayout();
		
		JSONArray j = new JSONArray();
		try {
			writeBoundsToJSON(j, diagram.getChildNodes());
			j.write(writer);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * set new bounds of diagram nodes, call itself recursively if node is a container
	 * @param JSONarray arr
	 * @param List<Node> nodes
	 * @throws JSONException
	 */
	private void writeBoundsToJSON(JSONArray arr, List<Node> nodes) throws JSONException{
		for(Node n : nodes){
				JSONObject ob = new JSONObject();
				ob.put("id",n.getResourceId());
				ob.put("bounds", n.getBounds().toString());
				arr.put(ob);
				if(n instanceof de.hpi.bpmn.Container)
					writeBoundsToJSON(arr, ((de.hpi.bpmn.Container) n).getChildNodes());
		}
	}
	
}
