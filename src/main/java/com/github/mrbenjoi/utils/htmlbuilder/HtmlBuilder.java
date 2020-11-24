/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mrbenjoi.utils.htmlbuilder;

import java.util.Stack;

/**
 *
 * @author ben
 */
public class HtmlBuilder {

    private StringBuilder sb;
    private Stack<String> tagStack;

    private boolean isMakingTag = false;
    private boolean isMakingSimpleTag = false;

    public HtmlBuilder() {
        init();
    }

    private void init() {
        sb = new StringBuilder();
        tagStack = new Stack();
    }

    private void switchToHtml() {
        if (isMakingTag) {
            if (isMakingSimpleTag) {
                sb.append("/");
                isMakingSimpleTag = false;
            }

            sb.append(">");
            isMakingTag = false;
        }
    }

    public HtmlBuilder tag(String tag) {
        if (tag != null) {
            switchToHtml();
            tagStack.push(tag);
            sb.append("<").append(tag);
            isMakingTag = true;
        }
        return this;
    }

    public HtmlBuilder div() {
        return tag("div");
    }

    public HtmlBuilder p() {
        return tag("p");
    }

    public HtmlBuilder span() {
        return tag("span");
    }

    public HtmlBuilder html() {
        return tag("html");
    }

    public HtmlBuilder html(String lang) {
        return html().attr("lang", lang);
    }

    public HtmlBuilder head() {
        return tag("head");
    }

    public HtmlBuilder title() {
        return tag("title");
    }

    public HtmlBuilder body() {
        return tag("body");
    }

    public HtmlBuilder styleTag() {
        return tag("style").type("text/css");
    }

    public HtmlBuilder script() {
        return tag("script").type("application/javascript");
    }

    public HtmlBuilder script(String src) {
        return script().src(src);
    }

    public HtmlBuilder form() {
        return tag("form");
    }

    public HtmlBuilder button() {
        return tag("button");
    }

    public HtmlBuilder table() {
        return tag("table");
    }

    public HtmlBuilder thead() {
        return tag("thead");
    }

    public HtmlBuilder tbody() {
        return tag("tbody");
    }

    public HtmlBuilder tr() {
        return tag("tr");
    }

    public HtmlBuilder th() {
        return tag("th");
    }

    public HtmlBuilder th(String value) {
        return th().append(value);
    }

    public HtmlBuilder ths(String... thValues) {
        for (String value : thValues) {
            th(value).close();
        }
        return this;
    }

    public HtmlBuilder ths(Iterable<String> thValues) {
        for (String value : thValues) {
            th(value).close();
        }
        return this;
    }

    public HtmlBuilder td() {
        return tag("td");
    }

    public HtmlBuilder td(String value) {
        return td().append(value);
    }

    public HtmlBuilder tds(String... tdValues) {
        for (String value : tdValues) {
            td(value).close();
        }
        return this;
    }

    public HtmlBuilder tds(Iterable<String> tdValues) {
        for (String value : tdValues) {
            td(value).close();
        }
        return this;
    }

    public HtmlBuilder simpleTag(String tag) {
        if (tag != null) {
            switchToHtml();
            sb.append("<").append(tag);
            isMakingSimpleTag = true;
            isMakingTag = true;
        }
        return this;
    }

    public HtmlBuilder br() {
        return simpleTag("br");
    }

    public HtmlBuilder hr() {
        return simpleTag("hr");
    }

    public HtmlBuilder link() {
        return simpleTag("link");
    }

    public HtmlBuilder link(String rel, String href) {
        return link().rel(rel).href(href);
    }

    public HtmlBuilder linkCss(String href) {
        return link().rel("stylesheet").href(href).type("text/css");
    }

    public HtmlBuilder meta() {
        return simpleTag("meta");
    }

    public HtmlBuilder meta(String name) {
        return meta().attr("name", name);
    }

    public HtmlBuilder meta(String name, String content) {
        return meta(name).attr("content", content);
    }

    public HtmlBuilder img() {
        return simpleTag("img");
    }

    public HtmlBuilder img(String src) {
        return img().src(src);
    }

    public HtmlBuilder img(String src, String alt) {
        return img().src(src).attr("alt", alt);
    }

    public HtmlBuilder img(String src, String alt, String width, String height) {
        return img().src(src).attr("alt", alt).attr("width", width).attr("height", height);
    }

    public HtmlBuilder input() {
        return simpleTag("input");
    }

    public HtmlBuilder input(String type) {
        return input().type(type);
    }

    public HtmlBuilder textInput() {
        return input("text");
    }

    public HtmlBuilder textInput(String placeholder) {
        return textInput().attr("placeholder", placeholder);
    }

    public HtmlBuilder numberInput() {
        return input("number");
    }

    public HtmlBuilder numberInput(Number value) {
        return input("number").value(value);
    }

    public HtmlBuilder numberInput(Number value, Number min, Number max, Number step) {
        return numberInput(value).min(min).max(max).step(step);
    }

    public HtmlBuilder dateInput(String value) {
        return input("date").value(value);
    }

    public HtmlBuilder attr(String name) {
        return attr(name, null);
    }

    public HtmlBuilder attr(String name, String value) {
        if (name != null && isMakingTag) {
            sb.append(" ").append(name);

            if (value != null) {
                sb.append("=\"").append(value).append("\"");
            }
        }
        return this;
    }

    public HtmlBuilder attr(String name, String delimiter, String... values) {
        if (name != null && isMakingTag) {
            sb.append(" ").append(name);

            if (delimiter != null && values != null && values.length > 0) {
                sb.append("=\"").append(String.join(delimiter, values)).append("\"");
            }
        }
        return this;
    }

    public HtmlBuilder id(String id) {
        return attr("id", id);
    }

    public HtmlBuilder clss(String... classes) {
        return attr("class", " ", classes);
    }

    public HtmlBuilder styleAttr(String css) {
        return attr("style", css);
    }

    public HtmlBuilder src(String src) {
        return attr("src", src);
    }

    public HtmlBuilder rel(String rel) {
        return attr("rel", rel);
    }

    public HtmlBuilder href(String href) {
        return attr("href", href);
    }

    public HtmlBuilder type(String type) {
        return attr("type", type);
    }

    public HtmlBuilder value(Object value) {
        return attr("value", value.toString());
    }

    public HtmlBuilder min(Object min) {
        return attr("min", min.toString());
    }

    public HtmlBuilder max(Object max) {
        return attr("max", max.toString());
    }

    public HtmlBuilder step(Object step) {
        return attr("step", step.toString());
    }

    public HtmlBuilder append(String str) {
        if (str != null) {
            switchToHtml();
            sb.append(str);
        }
        return this;
    }

    public HtmlBuilder close() {
        switchToHtml();
        if (!tagStack.isEmpty()) {
            sb.append("</").append(tagStack.pop()).append(">");
        }
        return this;
    }

    public HtmlBuilder close(int times) {
        for (int i = times; i > 0; --i) {
            this.close();
        }
        return this;
    }

    public HtmlBuilder closeAll() {
        while (!tagStack.isEmpty()) {
            this.close();
        }

        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }

}
