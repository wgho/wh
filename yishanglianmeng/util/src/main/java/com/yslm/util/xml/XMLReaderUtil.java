package com.yslm.util.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yslm.util.log.LogUtil;

/**
 * xml 读取工具类，返回一个树的结构
 * 
 * 
 */
public class XMLReaderUtil {

	private static LogUtil logger = LogUtil.instance(XMLReaderUtil.class);

	private File file;

	private XMLReaderUtil() {
	}

	private XMLReaderUtil(File file) {
		this.file = file;
	}

	public static XMLReaderUtil instance(File file) {
		return new XMLReaderUtil(file);
	}
	
	public static XMLReaderUtil instance() {
		return new XMLReaderUtil();
	}

	public TreeNode read() {
		try {
			Element root = rootElement();
			NodeBean noteBean = configNodes(root);
			// root element
			return new TreeNode(root.getName(), noteBean);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * @param root
	 * @return
	 */
	public TreeNode read(Element root) {
		NodeBean noteBean = configNodes(root);
		// root element
		return new TreeNode(root.getName(), noteBean);
	}

	private NodeBean configNodes(Element element) {
		// current element
		return configNodeBean(element);
	}

	@SuppressWarnings("unchecked")
	private NodeBean configNodeBean(Element element) {
		NodeBean nodeBean = new NodeBean();
		String key = element.getName();
		nodeBean.setKey(key);
		String value = element.getTextTrim();
		nodeBean.setValue(value);
		// System.out.println("~~~key="+key+"###"+value+"&&"+element.isRootElement());
		Iterator<Attribute> iterator = element.attributeIterator();
		Map<String, String> map = new HashMap<String, String>();
		while (iterator.hasNext()) {
			Attribute attribute = iterator.next();
			// System.out.println(attribute.getName() + "_" +
			// attribute.getValue());
			map.put(attribute.getName(), attribute.getValue());
		}
		nodeBean.setMap(map);
		List<NodeBean> list = new ArrayList<NodeBean>();
		Iterator<Element> elements = element.elementIterator();
		while (elements.hasNext()) {
			list.add(configNodes(elements.next()));
		}
		nodeBean.setChilds(list);
		return nodeBean;
	}

	private Document getDocument() throws DocumentException {
		if (file == null || !file.exists()) {
			logger.errorLog("read file error : can't found xml file");
			throw new RuntimeException("unknown.xml.file");
		}
		SAXReader saxReader = new SAXReader();
		return saxReader.read(file);
	}

	private Element rootElement() throws DocumentException {
		return getDocument().getRootElement();
	}

	public static void main(String[] args) {
		File file = new File("/Users/routine/Documents/temp/files/18855_userloansinterest.xml");
		TreeNode tree = XMLReaderUtil.instance(file).read();
		System.out.println("~~~~" + tree.getKey());
		sysout(tree.getRoot());
	}

	private static void sysout(NodeBean root) {
		//System.out.println("=================");
		//System.out.println("key=:" + root.getKey() + "~~~value="
		//		+ root.getValue());
		for (String str : root.getMap().keySet()) {
			System.out.println("attr:[name:" + str + ";value:"
					+ root.getMap().get(str) + "]");
		}
		for (NodeBean bean : root.getChilds()) {
			sysout(bean);
		}

	}

	/**
	 * 树的根节点，包含多个子节点
	 * 
	 * @author jinkai.xie
	 * 
	 */
	public class TreeNode {

		/**
		 * key - root name defalut named root
		 */
		private String key;

		/**
		 * root - root element
		 */
		private NodeBean root;

		TreeNode(String key, NodeBean root) {
			this.key = key == null ? "root" : key;
			this.root = root;
		}

		public String getKey() {
			return key;
		}

		public NodeBean getRoot() {
			return root;
		}
	}

	/**
	 * 节点元素，包含所有的key-value 键值对
	 * 
	 * @author jinkai.xie
	 * 
	 */
	public class NodeBean {
		/**
		 * key - node bean
		 * 
		 * @see name no default value, element named
		 * 
		 * @if null {throws exception}
		 */
		private String key;

		/**
		 * value - <>xxx</>
		 * 
		 * @if null {equals null}
		 */
		private String value;

		/**
		 * childs - sub nodebean
		 */
		private List<NodeBean> childs;

		/**
		 * map - params-name/value
		 */
		private Map<String, String> map;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public List<NodeBean> getChilds() {
			return childs;
		}

		public void setChilds(List<NodeBean> childs) {
			this.childs = childs;
		}

		public Map<String, String> getMap() {
			return map;
		}

		public void setMap(Map<String, String> map) {
			this.map = map;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
