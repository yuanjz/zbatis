package com.z.zbatis.utils;

import com.z.zbatis.config.Configuration;
import com.z.zbatis.mapping.BoundSql;
import com.z.zbatis.mapping.ResultMap;
import com.z.zbatis.mapping.ResultMapping;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yuanjz
 * @Date: 2022-07-29 16:51
 * @Description:
 */
public class Resources {

    public static Configuration loadConfig(String configPath) throws Exception {
        InputStream inputStream = Resources.class.getClassLoader().getResourceAsStream(configPath);
        Configuration config = new Configuration();
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        Element element = document.getDocumentElement();
        NodeList propertyList = element.getElementsByTagName("property");
        for (int i = 0; i < propertyList.getLength(); i++) {
            Node node = propertyList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                String name = node.getAttributes().getNamedItem("name").getTextContent();
                if ("driver".equals(name)) {
                    config.setDriver(node.getAttributes().getNamedItem("value").getTextContent());
                } else if ("url".equals(name)) {
                    config.setUrl(node.getAttributes().getNamedItem("value").getTextContent());
                } else if ("username".equals(name)) {
                    config.setUsername(node.getAttributes().getNamedItem("value").getTextContent());
                } else if ("password".equals(name)) {
                    config.setPassword(node.getAttributes().getNamedItem("value").getTextContent());
                }
            }
        }
        NodeList mapperList = element.getElementsByTagName("mapper");
        Map<String, BoundSql> mapper = new HashMap<>();
        for (int i = 0; i < mapperList.getLength(); i++) {
            Node node = mapperList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                String xmlResource = node.getAttributes().getNamedItem("resource").getTextContent();
                mapper.putAll(loadMapper(xmlResource));
            }
        }
        config.setMapper(mapper);
        inputStream.close();
        return config;
    }

    public static Map<String, BoundSql> loadMapper(String xml) throws Exception {
        Map<String, BoundSql> mapper = new HashMap<>();
        int indexOf = xml.indexOf("classpath:");
        if (indexOf == -1) {
            throw new RuntimeException("Cannot find mapper");
        }
        InputStream inputStream = Resources.class.getClassLoader().getResourceAsStream(xml.substring(10));
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        Element element = document.getDocumentElement();
        String namespace = element.getAttribute("namespace");
        NodeList selectNodeList = element.getElementsByTagName("select");
        for (int i = 0; i < selectNodeList.getLength(); i++) {
            BoundSql boundSql = new BoundSql();
            boundSql.setSql(selectNodeList.item(i).getTextContent());
            ResultMap resultMap = new ResultMap();
            NamedNodeMap namedNodeMap = selectNodeList.item(i).getAttributes();
            String selectId = namedNodeMap.getNamedItem("id").getNodeValue();
            Node resultTypeNode = namedNodeMap.getNamedItem("resultType");
            String resultType = null;
            List<ResultMapping> resultMappings = null;
            if (resultTypeNode == null) {
                String resultMapId = namedNodeMap.getNamedItem("resultMap").getNodeValue();
                NodeList resultMapNodeList = element.getElementsByTagName("resultMap");
                for (int j = 0; j < resultMapNodeList.getLength(); j++) {
                    Node node = resultMapNodeList.item(j);
                    NamedNodeMap attributes = node.getAttributes();
                    if (attributes.getNamedItem("id").getNodeValue().equals(resultMapId)) {
                        resultType = attributes.getNamedItem("type").getNodeValue();
                        resultMappings = buildResultMapping(node);
                        break;
                    }
                }
            } else {
                resultType = resultTypeNode.getNodeValue();
            }
            if (resultType == null) {
                throw new RuntimeException("Cannot match result type.");
            }
            resultMap.setResultType(Class.forName(resultType));
            resultMap.setResultMappings(resultMappings);
            boundSql.setResultMap(resultMap);
            mapper.put(namespace + "." + selectId, boundSql);
        }
        return mapper;
    }

    private static List<ResultMapping> buildResultMapping(Node node) {
        NodeList nodeList = node.getChildNodes();
        List<ResultMapping> resultMappings = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (Node.ELEMENT_NODE == item.getNodeType()) {
                String column = item.getAttributes().getNamedItem("column").getNodeValue();
                String property = item.getAttributes().getNamedItem("property").getNodeValue();
                ResultMapping resultMapping = new ResultMapping(column, property);
                resultMappings.add(resultMapping);
            }
        }
        return resultMappings;
    }
}
