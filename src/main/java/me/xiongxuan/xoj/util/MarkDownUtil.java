package me.xiongxuan.xoj.util;

import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.util.Arrays;

public class MarkDownUtil {
    /**
     * 直接将markdown语义的文本转为html格式输出
     * @param content markdown语义文本
     * @return
     */
    public static String markdownToHtml(String content) {
        if (content == null) {
            return null;
        }
        return parse(content);
    }
    /**
     * markdown to image
     * @param content markdown contents
     * @return parse html contents
     */
    public static String parse(String content) {
        if (content == null) {
            return null;
        }
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        //enable table parse!
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create()));
//        options.set(HtmlRenderer)
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(content);
        return renderer.render(document);
    }
}
