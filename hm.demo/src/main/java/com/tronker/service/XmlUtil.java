package com.tronker.service;

import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.apache.log4j.Logger;

public class XmlUtil {

	/* 全局变量 */
	static Logger logger = Logger.getLogger("XmlOper");

	public static String getNodeElementVal(InputStream path, String parmentName, String nodeName) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// 创建DocumentBuilder对象
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(path);// .parse(new
			Element root=document.getDocumentElement();
			NodeList nodelist = root.getElementsByTagName(parmentName);

			if (nodelist.getLength() > 0) {
				for (int i = 0; i < nodelist.getLength(); i++) {
					NodeList nodes = nodelist.item(i).getChildNodes();
					for (int j = 0; j < nodes.getLength(); j++) {
						if (nodes.item(j).getNodeName().equals(nodeName)) {
							return nodes.item(j).getFirstChild().getNodeValue();
						}
					}
				}
			}
			Node node = nodelist.item(0);
			return node.getFirstChild().getNodeValue();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 方法名称：getNodeList 方法功能：获取父结点parent的所有子结点
	 **/
	public static NodeList getNodeList(Element parent) {
		return parent.getChildNodes();
	}

	/**
	 * 方法名称：getElementsByName 方法功能：在父结点中查询指定名称的结点集
	 **/
	public static Element[] getElementsByName(Element parent, String name) {
		ArrayList resList = new ArrayList();
		NodeList nl = getNodeList(parent);
		for (int i = 0; i < nl.getLength(); i++) {
			Node nd = nl.item(i);
			if (nd.getNodeName().equals(name)) {
				resList.add(nd);
			}
		}
		Element[] res = new Element[resList.size()];
		for (int i = 0; i < resList.size(); i++) {
			res[0] = (Element) resList.get(i);
		}
		logger.debug(parent.getNodeName() + "'s children of " + name + "'s num:" + res.length);
		return res;
	}

	/**
	 * 方法名称：getElementName 方法功能：获取指定Element的名称
	 **/
	public static String getElementName(Element element) {
		return element.getNodeName();
	}

	/**
	 * 方法名称：getElementValue 方法功能：获取指定Element的值
	 **/
	public static String getElementValue(Element element) {
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeType() == Node.TEXT_NODE)// 是一个Text Node
			{
				logger.debug(element.getNodeName() + " has a Text Node.");
				return element.getFirstChild().getNodeValue();
			}
		}
		logger.error(element.getNodeName() + " hasn't a Text Node.");
		return null;
	}

	/**
	 * 方法名称：getElementAttr 方法功能：获取指定Element的属性attr的值
	 **/
	public static String getElementAttr(Element element, String attr) {
		return element.getAttribute(attr);
	}

	/**
	 * 方法名称：setElementValue 方法功能：设置指定Element的值
	 **/
	public static void setElementValue(Element element, String val) {
		Node node = element.getOwnerDocument().createTextNode(val);
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node nd = nl.item(i);
			if (nd.getNodeType() == Node.TEXT_NODE)// 是一个Text Node
			{
				nd.setNodeValue(val);
				logger.debug("modify " + element.getNodeName() + "'s node value succe.");
				return;
			}
		}
		logger.debug("new " + element.getNodeName() + "'s node value succe.");
		element.appendChild(node);
	}

	/**
	 * 方法名称：setElementAttr 方法功能：设置结点Element的属性
	 **/
	public static void setElementAttr(Element element, String attr, String attrVal) {
		element.setAttribute(attr, attrVal);
	}

	/**
	 * 方法名称：addElement 方法功能：在parent下增加结点child
	 **/
	public static void addElement(Element parent, Element child) {
		parent.appendChild(child);
	}

	/**
	 * 方法名称：addElement 方法功能：在parent下增加字符串tagName生成的结点
	 **/
	public static void addElement(Element parent, String tagName) {
		Document doc = parent.getOwnerDocument();
		Element child = doc.createElement(tagName);
		parent.appendChild(child);
	}

	/**
	 * 方法名称：addElement 方法功能：在parent下增加tagName的Text结点，且值为text
	 **/
	public static void addElement(Element parent, String tagName, String text) {
		Document doc = parent.getOwnerDocument();
		Element child = doc.createElement(tagName);
		setElementValue(child, text);
		parent.appendChild(child);
	}

	/**
	 * 方法名称：removeElement 方法功能：将父结点parent下的名称为tagName的结点移除
	 **/
	public static void removeElement(Element parent, String tagName) {
		logger.debug("remove " + parent.getNodeName() + "'s children by tagName " + tagName + " begin...");
		NodeList nl = parent.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node nd = nl.item(i);
			if (nd.getNodeName().equals(tagName)) {
				parent.removeChild(nd);
				logger.debug("remove child '" + nd + "' success.");
			}
		}
		logger.debug("remove " + parent.getNodeName() + "'s children by tagName " + tagName + " end.");
	}

}
