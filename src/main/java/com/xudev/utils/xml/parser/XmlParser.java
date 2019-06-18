package com.xudev.utils.xml.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * xml解析类
 *
 * @author Administrator
 */
public class XmlParser {
    /**
     * get item by xml tag
     *
     * @param content
     * @param per
     * @return
     */
    public static List<HashMap<String, String>> getXmlParserContent(
            String content, String per) {
        List<HashMap<String, String>> maplist = null;
        HashMap<String, String> map = null;
        if (content != null && per != null) {
            try {
                XmlPullParser parser = (XmlPullParser) XmlPullParserFactory
                        .newInstance().newPullParser();
                InputStream is = new ByteArrayInputStream(content.getBytes());
                parser.setInput(is, "utf-8");
                int event = parser.getEventType();
                while (event != XmlPullParser.END_DOCUMENT) {
                    switch (event) {
                        case XmlPullParser.START_DOCUMENT:
                            maplist = new ArrayList<HashMap<String, String>>();
                            break;
                        case XmlPullParser.START_TAG:
                            String tag = parser.getName().trim();
                            if (tag.equals(per)) {
                                map = new HashMap<String, String>();
                            } else if (map != null) {
                                map.put(tag, parser.nextText());
                            }

                            break;
                        case XmlPullParser.END_TAG:
                            String endtag = parser.getName();
                            if (endtag.equals(per) && maplist != null
                                    && map != null) {
                                HashMap<String, String> contentMap = new HashMap<String, String>();
                                contentMap.putAll(map);
                                maplist.add(contentMap);

                            }

                            break;
                        default:
                            break;
                    }
                    parser.next();
                    event = parser.getEventType();
                }
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return maplist;
    }
}
