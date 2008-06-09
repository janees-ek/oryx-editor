package de.hpi.petrinet.pnml;

import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.hpi.petrinet.FlowRelationship;
import de.hpi.petrinet.LabeledTransition;
import de.hpi.petrinet.PetriNet;
import de.hpi.petrinet.Place;
import de.hpi.petrinet.Transition;

public class PetriNetPNMLExporter {

	public void savePetriNet(Document doc, PetriNet net) {
		Node root = doc.appendChild(doc.createElement("pnml"));
		Element netnode = (Element)root.appendChild(doc.createElement("net"));
		
		handlePetriNetAttributes(doc, netnode, net);

		for (Iterator<Place> it=net.getPlaces().iterator(); it.hasNext(); ) {
			appendPlace(doc, netnode, net, it.next());
		}
		
		for (Iterator<Transition> it=net.getTransitions().iterator(); it.hasNext(); ) {
			Transition t = it.next();
			appendTransition(doc, netnode, t);
		}
		
		for (Iterator<FlowRelationship> it=net.getFlowRelationships().iterator(); it.hasNext(); ) {
			appendFlowRelationship(doc, netnode, it.next());
		}
	}

	protected void handlePetriNetAttributes(Document doc, Element node, PetriNet net) {
		node.setAttribute("id", "Net-One");
		node.setAttribute("type", "Petri net");
	}

	protected Element appendPlace(Document doc, Node netnode, PetriNet net, Place place) {
		Element pnode = (Element)netnode.appendChild(doc.createElement("place"));
		pnode.setAttribute("id", place.getId());
		
		return pnode;
	}

	protected Element appendTransition(Document doc, Node netnode, Transition transition) {
		Element tnode = (Element)netnode.appendChild(doc.createElement("transition"));
		tnode.setAttribute("id", transition.getId());
		if (transition instanceof LabeledTransition) {
			Node n1node = tnode.appendChild(doc.createElement("name"));
			addContentElement(doc, n1node, "value", ((LabeledTransition)transition).getLabel());
			addContentElement(doc, n1node, "text", ((LabeledTransition)transition).getLabel());
		}
		return tnode;
	}

	protected Element appendFlowRelationship(Document doc, Node netnode, FlowRelationship rel) {
		Element fnode = (Element)netnode.appendChild(doc.createElement("arc"));
		fnode.setAttribute("id", "from "+rel.getSource().getId()+" to "+rel.getTarget().getId());
		fnode.setAttribute("source", rel.getSource().getId());
		fnode.setAttribute("target", rel.getTarget().getId());
		if (rel.getMode()==FlowRelationship.RELATION_MODE_READTOKEN){
			fnode.setAttribute("type", "read");
		}
		Node insnode = fnode.appendChild(doc.createElement("inscription"));
		addContentElement(doc, insnode, "value", "1");
		
		return fnode;
	}
	
	protected void addContentElement(Document doc, Node node, String tagName, String content) {
		Node cnode = node.appendChild(doc.createElement(tagName));
		cnode.appendChild(doc.createTextNode(content));
	}

}
