package com.xu.utils.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import com.xu.utils.xml.XMLStreamUtil.XMLType;

import android.content.Context;
import android.util.Xml;

/**
 * XML节点解析管理类(基于pull解析)
 * 
 * @author: laohu
 * @time: 2014-9-15下午10:10:07
 */
public class XMLAnalysisManager {

	/**
	 * 将XML节点对象转换成XML文件并存储在输出流所代表的位置
	 * 
	 * @author: laohu
	 * @time: 2014-9-15下午10:10:35
	 * @param node
	 *            XML节点对象
	 * @param os
	 *            生成XML文件的输出流
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static void saveAsXML(XMLNode node, OutputStream os)
			throws IllegalArgumentException, IllegalStateException, IOException {
		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(os, "UTF-8");
		serializer.startDocument("UTF-8", true);

		createXMLNode(node, serializer);

		serializer.endDocument();
	}

	/**
	 * 根据复杂节点对象生成对应的XML节点
	 * 
	 * @author: laohu
	 * @time: 2014-9-15下午10:16:00
	 * @param nodes
	 *            XML节点对象
	 * @param serializer
	 *            XML序列化对象
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private static void createXMLNode(XMLNode nodes, XmlSerializer serializer)
			throws IllegalArgumentException, IllegalStateException, IOException {
		if (isEmpty(nodes.getNodeName())) {
			throw new IllegalArgumentException("XML节点名称不能为空");
		}
		serializer.startTag(null, nodes.getNodeName());// 生成该节点的开始标签

		createXMLAttribute(nodes, serializer);// 生成该节点的属性值
		serializer.text(nodes.getNodeValue());// 生成该节点的文本内容

		if (nodes.isHasChild()) {// 是否有下级子节点
			for (XMLNode node : nodes.getChildList()) {
				createXMLNode(node, serializer);// 生成该节点的子节点
			}
		}
		serializer.endTag(null, nodes.getNodeName());// 生成该节点的结束标签
	}

	/**
	 * 根据节点生成XML节点对应的属性值
	 * 
	 * @author: laohu
	 * @time: 2014-9-15下午10:16:49
	 * @param node
	 *            节点对象
	 * @param serializer
	 *            XML序列化对象
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private static void createXMLAttribute(XMLNode node, XmlSerializer serializer)
			throws IllegalArgumentException, IllegalStateException, IOException {
		if (node.isHasAttribute()) {// 该节点是否有属性
			XMLNodeAtt att = node.getAttribute();// 获取属性对象
			Set<String> keys = att.getAttributeNames();// 得到属性对象的键值集合
			for (String key : keys) {
				if (isEmpty(key)) {
					throw new IllegalArgumentException("XML属性名称不能为空");
				}
				serializer.attribute(null, key, att.getAttributeValue(key));// 设置XML节点的属性
			}
		}
	}

	/**
	 * 获取XML某个节点的值
	 * 
	 * @param is
	 *            XML文件输入流
	 * @param tagName
	 *            节点的标签名称
	 * @return 没有找到该节点返回null
	 * @throws Exception
	 *             XML文件找不到或标签名为空时
	 */
	public static String getNodeValue(InputStream is, String tagName) throws Exception {
		if (isEmpty(tagName)) {
			throw new Exception("节点名称为空");
		}

		XmlPullParser pullParser = Xml.newPullParser();
		pullParser.setInput(is, "UTF-8");

		String value = null;
		boolean isGet = false;

		// 得到事件类型
		int event = pullParser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if (tagName.equals(pullParser.getName())) {
					value = pullParser.nextText();
					isGet = true;
				}
				break;
			}
			if (isGet) {
				break;
			}
			event = pullParser.next();
		}

		return value;
	}

	/**
	 * 获取XML的某个标签的某个属性值
	 * 
	 * @param is
	 *            XML文件输入流
	 * @param tagName
	 *            属性所在的节点标签名
	 * @param attTagName
	 *            属性名称
	 * @return 属性值，找不到该节点标签或找不到该属性时返回null
	 * @throws Exception
	 *             XML文件找不到或节点名称为空或属性名称为空时
	 */
	public static String getAttributeValue(InputStream is, String tagName, String attTagName) throws Exception {
		if (isEmpty(tagName)) {
			throw new Exception("节点名称为空");
		}

		if (isEmpty(attTagName)) {
			throw new Exception("属性名称为空");
		}

		XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
		pullParser.setInput(is, "UTF-8");

		String value = null;
		boolean isGet = false;
		// 得到事件类型
		int event = pullParser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_TAG:
				if (tagName.equals(pullParser.getName())) {
					value = pullParser.getAttributeValue(null, attTagName);
					isGet = true;
				}
				break;
			}
			if (isGet) {
				break;
			}
			event = pullParser.next();
		}

		return value;
	}

	/**
	 * 获取XML文件的输入流
	 * 
	 * @author: laohu
	 * @time: 2014-9-17下午4:26:47
	 * @param context
	 * @param xmlName
	 *            XML文件的完整路径
	 * @param type
	 *            XML文件所在目录类型，分如下三种：src目录下，assets目录下，SDCard下
	 * @return
	 * @throws Exception
	 */
	public static XMLNode getXMLNode(Context context, String xmlName, XMLType type) throws Exception {
		InputStream is = XMLStreamUtil.getInstance().getXMLInputStream(context, xmlName, type);

		return XMLAnalysisManager.getXMLNode(is);
	}

	/**
	 * 将整个XML文件解析为XML节点对象
	 * 
	 * @author: laohu
	 * @time: 2014-9-17上午10:24:29
	 * @param is
	 *            XML文件输入流
	 * @return
	 * @throws Exception
	 */
	public static XMLNode getXMLNode(InputStream is) throws Exception {
		XmlPullParser pullParser = Xml.newPullParser();
		pullParser.setInput(is, "UTF-8");

		Stack<XMLNode> stack = new Stack<XMLNode>();// 开始标签栈
		Stack<List<XMLNode>> child = new Stack<List<XMLNode>>();// 每个开始标签对应的子节点

		XMLNodeAtt nodeAtt = null;

		String text = "";

		// 得到事件类型
		int event = pullParser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				int attCount = pullParser.getAttributeCount();
				nodeAtt = new XMLNodeAtt();
				for (int i = 0; i < attCount; i++) {// 得到当前节点的属性
					nodeAtt.addAttribute(pullParser.getAttributeName(i), pullParser.getAttributeValue(i));
				}
				XMLNode cnode = new XMLNode(pullParser.getName(), nodeAtt);// 当前节点
				stack.push(cnode);

				List<XMLNode> list = new ArrayList<XMLNode>();// 当前节点的子节点集合
				child.push(list);
				break;
			case XmlPullParser.TEXT:
				text = pullParser.getText();
				boolean isEquals = Pattern.matches("\\s*|\t|\r|\n", text);
				if (isEquals) {
					break;
				}
				stack.peek().setNodeValue(pullParser.getText());
				break;
			case XmlPullParser.END_TAG:
				if (pullParser.getName().equals(stack.peek().getNodeName())) {
					XMLNode ccnode = stack.pop();
					ccnode.setChildList(child.pop());
					if (!child.isEmpty()) {
						child.peek().add(ccnode);
					}
					if (stack.isEmpty()) {
						stack.push(ccnode);
					}
				}
				break;
			}
			event = pullParser.next();
		}

		if (stack.size() <= 0) {
			throw new Exception("XML文件为空");
		}

		return stack.pop();
	}

	public static boolean isEmpty(String content) {
		if (content == null) {
			return false;
		} else if (content.equals("")) {
			return false;
		}
		return true;

	}
}
