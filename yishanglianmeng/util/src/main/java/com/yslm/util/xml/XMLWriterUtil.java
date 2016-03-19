package com.yslm.util.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yslm.util.log.LogUtil;
import com.yslm.util.validate.ValidateUtil;


public class XMLWriterUtil {
	
	private static LogUtil logger = LogUtil.instance(XMLWriterUtil.class);

	/**
	 * 递归子节点
	 * 
	 * @param element
	 * @param xml
	 * @param doc
	 * @return
	 */
	private static Element createElement(Element element, XMLElement xml,
			Document doc) {

		List<XMLElement> subs = xml.getElements();
		if (subs != null && !subs.isEmpty()) {
			for (XMLElement sub : subs) {
				String name = sub.getTagName();
				if (ValidateUtil.isEmpty(name)) {
					throw new RuntimeException(
							"xml file create failed, tag name is null");
				}
				Element e = doc.createElement(name);
				Map<String, String> map = sub.getParams();
				if (map != null && !map.isEmpty()) {
					for (String key : map.keySet()) {
						String value = map.get(key);
						e.setAttribute(key, value);
					}
				}
				element.appendChild(e);
				if (sub.getElements() != null && !sub.getElements().isEmpty()) {
					createElement(e, sub, doc);
				}
			}
		}
		return element;

	}

	/**
	 * 文档格式
	 * 
	 * @param doc
	 * @param w
	 * @param encoding
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	private static void writeDo(Document doc, Writer w, String encoding)
			throws TransformerFactoryConfigurationError, TransformerException {
		Source source = new DOMSource(doc);
		Result result = new StreamResult(w);

		Transformer xformer = TransformerFactory.newInstance().newTransformer();
		xformer.setOutputProperty(OutputKeys.ENCODING, encoding);
		xformer.transform(source, result);

	}

	/**
	 * 写入或者更新xml文件
	 * 
	 * @param outfile
	 * @param xml
	 * @param encoding
	 * @throws ParserConfigurationException
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void writeXMLFile(String outfile, XMLElement xml,
			String encoding) throws ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException,
			IOException, SAXException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document doc = null;
		File file = new File(outfile);
		if (!file.exists()) {
			// 创建root节点
			doc = builder.newDocument();
			createRootElement(doc, encoding, outfile);
			file = new File(outfile);
		}
		doc = builder.parse(file);
		String rootName = xml.getTagName();
		if (ValidateUtil.isEmpty(rootName)) {
			// tag name 不可为空
			throw new RuntimeException(
					"xml file create failed, tag name is null");
		}
		Element root = doc.createElement(rootName);
		Map<String, String> params = xml.getParams();
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				String value = params.get(key);
				root.setAttribute(key, value);
			}
		}
		root = createElement(root, xml, doc);
		doc.getDocumentElement().appendChild(root);

		FileOutputStream fos = new FileOutputStream(outfile);
		OutputStreamWriter outwriter = new OutputStreamWriter(fos);
		writeDo(doc, outwriter, encoding);
		outwriter.close();
		fos.close();
	}

	private static void createRootElement(Document doc, String encoding,
			String outfile) throws TransformerFactoryConfigurationError,
			TransformerException, IOException {
		Element root = doc.createElement("root");
		doc.appendChild(root);
		FileOutputStream fos = new FileOutputStream(outfile);
		OutputStreamWriter outwriter = new OutputStreamWriter(fos);
		writeDo(doc, outwriter, encoding);
		outwriter.close();
		fos.close();
	}
	
	/**
	 * 更新xml节点total、assetsinfo、yestoday 的 value值
	 * @param outfile	文件路径
	 * @param userId	用户id
	 * @param elementName	：total、assetsinfo、yestoday
	 * @param time	时间    yyyy-MM-dd
	 * @param value	待更新的value值
	 * @return
	 * @throws Exception
	 */
	public static void updateNode(String outfile, String userId, String elementName, String time, String value)
			throws IOException, SAXException, ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException {
		if (userId == null || "".equals(userId)){ // 参数异常
			logger.infoLog("The param is error!");
			return ;
		}
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		File file = new File(outfile);
		if (!file.exists()) { // 文件不存在
			logger.infoLog("The file does not exist");
			return ;
		}
		Document doc = builder.parse(file);
		doc.normalize();
		
		NodeList employees = doc.getChildNodes();
		for (int i = 0; i < employees.getLength(); i++) {
			Node employee = employees.item(i);
			NodeList employeeInfo = employee.getChildNodes();
			for (int j = 0; j < employeeInfo.getLength(); j++) {
				Node node = employeeInfo.item(j);
				if (node.getNodeName().equals("elements")
						&& userId.equals(node.getAttributes().getNamedItem("userid").getNodeValue())) {
					NodeList employeeMeta = node.getChildNodes();
					for (int k = 0; k < employeeMeta.getLength(); k++) {
						if (elementName.trim().equals(employeeMeta.item(k).getNodeName())) {
							if (employeeMeta.item(k).getAttributes() != null
									&& time.trim().equals(employeeMeta.item(k).getAttributes().getNamedItem("date").getNodeValue())) {
								employeeMeta.item(k).getAttributes().getNamedItem("value").setNodeValue(value);
								// node.getParentNode().removeChild(node);
							}
						}
					}
				}
			}
		}

		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		transformer.setOutputProperty("encoding", "UTF-8");
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(outfile);
		transformer.transform(source, result);
		logger.infoLog("updateNode finish");
	}
	
	public static void parserXml(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
             
            NodeList employees = document.getChildNodes();
            for (int i = 0; i < employees.getLength(); i++) {
                Node employee = employees.item(i);
                NodeList employeeInfo = employee.getChildNodes();
                for (int j = 0; j < employeeInfo.getLength(); j++) {
                    Node node = employeeInfo.item(j);
                    NodeList employeeMeta = node.getChildNodes();
                    for (int k = 0; k < employeeMeta.getLength(); k++) {
                        System.out.println(employeeMeta.item(k).getNodeName() + ":" + employeeMeta.item(k).getAttributes());
                        if(employeeMeta.item(k).getAttributes()!=null)
                        	System.out.println(employeeMeta.item(k).getAttributes().getNamedItem("date"));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

	public static void main(String args[]) {
		String outfile = "/Users/routine/Documents/temp/stucopy.xml";
		XMLElement xml = new XMLWriterUtil().new XMLElement();
		xml.setTagName("ssss");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "parent");
		map.put("value", "3");
		map.put("date", new Date().toString());
		xml.setParams(map);
		XMLElement xml1 = new XMLWriterUtil().new XMLElement();
		xml1.setTagName("xml1");
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("name", "xml1");
		xml1.setParams(map1);
		XMLElement xml2 = new XMLWriterUtil().new XMLElement();
		xml2.setTagName("xml2");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "xml2");
		xml2.setParams(map2);
		XMLElement xml3 = new XMLWriterUtil().new XMLElement();
		xml3.setTagName("xml3");
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("name", "xml3");
		xml3.setParams(map3);
		List<XMLElement> xmls = new ArrayList<XMLElement>();
		xmls.add(xml3);
		xmls.add(xml2);
		xmls.add(xml1);
		xml.setElements(xmls);

		XMLElement xml3_1 = new XMLWriterUtil().new XMLElement();
		xml3_1.setTagName("xml3_1");
		Map<String, String> map3_1 = new HashMap<String, String>();
		map3_1.put("name", "xml3_1");
		xml3_1.setParams(map3_1);
		List<XMLElement> xmls3 = new ArrayList<XMLElement>();
		xmls3.add(xml3_1);
		xml3.setElements(xmls3);

		try {
			writeXMLFile(outfile, xml, "utf-8");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public class XMLElement {
		// 标签名称
		private String tagName;
		// 其他属性和值
		private Map<String, String> params;
		// 子元素
		private List<XMLElement> elements;

		/**
		 * @return the tagName
		 */
		public String getTagName() {
			return tagName;
		}

		/**
		 * @param tagName
		 *            the tagName to set
		 */
		public void setTagName(String tagName) {
			this.tagName = tagName;
		}

		/**
		 * @return the params
		 */
		public Map<String, String> getParams() {
			return params;
		}

		/**
		 * @param params
		 *            the params to set
		 */
		public void setParams(Map<String, String> params) {
			this.params = params;
		}

		/**
		 * @return the elements
		 */
		public List<XMLElement> getElements() {
			return elements;
		}

		/**
		 * @param elements
		 *            the elements to set
		 */
		public void setElements(List<XMLElement> elements) {
			this.elements = elements;
		}

	}
}

