package org.tonyzampogna.xss.sanitizer.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class XssSanitizerUtilTest {
    @BeforeClass
    public static void setEsapiResources() {
        System.setProperty("org.owasp.esapi.resources", "grails-app/conf");
    }

    @AfterClass
    public static void unsetEsapiResources() {
        System.clearProperty("org.owasp.esapi.resources");
    }

    @Test
    public void shouldStripOutNullCharacters() {
        final String output = XssSanitizerUtil.stripXSS("\0");
        assertThat(output, is(""));
    }

    @Test
    public void shouldStripOutContentOfScriptTags() {
        final String output = XssSanitizerUtil.stripXSS("valid-content<script>xss-content</script>");
        assertThat(output, is("valid-content"));
    }

    @Test
    public void shouldStripOutTrailingScriptTag() {
        final String output = XssSanitizerUtil.stripXSS("</script>valid-content");
        assertThat(output, is("valid-content"));
    }

    @Test
    public void shouldStripOutStartingScriptTag() {
        final String output = XssSanitizerUtil.stripXSS("<script src='xss.js'>valid-content");
        assertThat(output, is("valid-content"));
    }

    @Test
    public void shouldStripOutEvalAttribute() {
        final String output = XssSanitizerUtil.stripXSS("eval('xss-js-content')");
        assertThat(output, is(""));
    }

    @Test
    public void shouldStripOutExpressionAttribute() {
        final String output = XssSanitizerUtil.stripXSS("expression('xss-content')");
        assertThat(output, is(""));
    }

    @Test
    public void shouldStripOutOnloadAttribute() {
        final String output = XssSanitizerUtil.stripXSS("onload=xss.execute()");
        assertThat(output, is("xss.execute()"));
    }

    @Test
    public void shouldStripOutJavascriptProtocol() {
        final String output = XssSanitizerUtil.stripXSS("javascript:xss.execute()");
        assertThat(output, is("xss.execute()"));
    }

    @Test
    public void shouldStripOutVbcriptProtocol() {
        final String output = XssSanitizerUtil.stripXSS("vbscript:xss.execute()");
        assertThat(output, is("xss.execute()"));
    }

    @Test
    public void shouldStripOutSrcAttribute() {
        final String output = XssSanitizerUtil.stripXSS("<img src='xss.jpg'>");
        assertThat(output, is("<img >"));
    }

    @Test
    public void shouldStripOutContentOfIframeTags() {
        final String output = XssSanitizerUtil.stripXSS("<iframe src='xss.html'>xss-content</iframe>");
        assertThat(output, is(""));
    }

    @Test
    public void shouldStripOutContentOfIframeTags() {
        final String output = XssSanitizerUtil.stripXSS("<form action=''><input id='formInjection'></form>");
        assertThat(output, is(""));
    }
}
