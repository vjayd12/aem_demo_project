/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.aemtutorial.components.xfpage;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class customheaderlibs__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_clientlib = null;
Object _global_page = null;
Collection var_collectionvar5_list_coerced$ = null;
Collection var_attrmap16_list_coerced$ = null;
out.write("\n\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\"/>\n");
_global_clientlib = renderContext.call("use", "/libs/granite/sightly/templates/clientlib.html", obj());
{
    Object var_templatevar0 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "js");
    {
        String var_templateoptions1_field$_categories = "aemtutorial.dependencies";
        {
            java.util.Map var_templateoptions1 = obj().with("categories", var_templateoptions1_field$_categories);
            callUnit(out, renderContext, var_templatevar0, var_templateoptions1);
        }
    }
}
out.write("\n");
_global_clientlib = renderContext.call("use", "/libs/granite/sightly/templates/clientlib.html", obj());
{
    Object var_templatevar2 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "css");
    {
        String var_templateoptions3_field$_categories = "aemtutorial.base";
        {
            java.util.Map var_templateoptions3 = obj().with("categories", var_templateoptions3_field$_categories);
            callUnit(out, renderContext, var_templatevar2, var_templateoptions3);
        }
    }
}
out.write("\n\n\n");
{
    Object var_resourcecontent4 = renderContext.call("includeResource", "contexthub", obj().with("resourceType", "granite/contexthub/components/contexthub"));
    out.write(renderContext.getObjectModel().toString(var_resourcecontent4));
}
out.write("\n\n");
_global_page = renderContext.call("use", com.adobe.cq.wcm.core.components.models.Page.class.getName(), obj());
{
    Object var_collectionvar5 = renderContext.getObjectModel().resolveProperty(_global_page, "htmlPageItems");
    {
        long var_size6 = ((var_collectionvar5_list_coerced$ == null ? (var_collectionvar5_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar5)) : var_collectionvar5_list_coerced$).size());
        {
            boolean var_notempty7 = (var_size6 > 0);
            if (var_notempty7) {
                {
                    long var_end10 = var_size6;
                    {
                        boolean var_validstartstepend11 = (((0 < var_size6) && true) && (var_end10 > 0));
                        if (var_validstartstepend11) {
                            if (var_collectionvar5_list_coerced$ == null) {
                                var_collectionvar5_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar5);
                            }
                            long var_index12 = 0;
                            for (Object item : var_collectionvar5_list_coerced$) {
                                {
                                    boolean var_traversal14 = (((var_index12 >= 0) && (var_index12 <= var_end10)) && true);
                                    if (var_traversal14) {
                                        out.write("\n    ");
                                        {
                                            boolean var_testvariable17 = (org.apache.sling.scripting.sightly.compiler.expression.nodes.BinaryOperator.strictEq(renderContext.getObjectModel().resolveProperty(renderContext.getObjectModel().resolveProperty(item, "location"), "name"), "header"));
                                            if (var_testvariable17) {
                                                {
                                                    Object var_tagvar15 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(renderContext.getObjectModel().resolveProperty(item, "element"), "name"), "unsafe");
                                                    if (renderContext.getObjectModel().toBoolean(var_tagvar15)) {
                                                        out.write("<");
                                                        out.write(renderContext.getObjectModel().toString(var_tagvar15));
                                                    }
                                                    if (!renderContext.getObjectModel().toBoolean(var_tagvar15)) {
                                                        out.write("<script");
                                                    }
                                                    {
                                                        Object var_attrmap16 = renderContext.getObjectModel().resolveProperty(item, "attributes");
                                                        {
                                                            java.util.Map var_ignoredattributes18 = obj();
                                                            if (var_attrmap16_list_coerced$ == null) {
                                                                var_attrmap16_list_coerced$ = renderContext.getObjectModel().toCollection(var_attrmap16);
                                                            }
                                                            long var_attrindex21 = 0;
                                                            for (Object var_attrname19 : var_attrmap16_list_coerced$) {
                                                                {
                                                                    Object var_attrnameescaped20 = renderContext.call("xss", var_attrname19, "attributeName");
                                                                    if (renderContext.getObjectModel().toBoolean(var_attrnameescaped20)) {
                                                                        {
                                                                            Object var_isignoredattr22 = var_ignoredattributes18.get(var_attrname19);
                                                                            if (!renderContext.getObjectModel().toBoolean(var_isignoredattr22)) {
                                                                                {
                                                                                    Object var_attrcontent23 = renderContext.getObjectModel().resolveProperty(var_attrmap16, var_attrname19);
                                                                                    {
                                                                                        Object var_attrcontentescaped24 = renderContext.call("xss", var_attrcontent23, "attribute", var_attrnameescaped20);
                                                                                        {
                                                                                            boolean var_shoulddisplayattr25 = (((null != var_attrcontentescaped24) && (!"".equals(var_attrcontentescaped24))) && ((!"".equals(var_attrcontent23)) && (!((Object)false).equals(var_attrcontent23))));
                                                                                            if (var_shoulddisplayattr25) {
                                                                                                out.write(" ");
                                                                                                out.write(renderContext.getObjectModel().toString(var_attrnameescaped20));
                                                                                                {
                                                                                                    boolean var_istrueattr26 = (var_attrcontent23.equals(true));
                                                                                                    if (!var_istrueattr26) {
                                                                                                        out.write("=\"");
                                                                                                        out.write(renderContext.getObjectModel().toString(var_attrcontentescaped24));
                                                                                                        out.write("\"");
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                var_attrindex21++;
                                                            }
                                                        }
                                                        var_attrmap16_list_coerced$ = null;
                                                    }
                                                    out.write(">");
                                                    if (renderContext.getObjectModel().toBoolean(var_tagvar15)) {
                                                        out.write("</");
                                                        out.write(renderContext.getObjectModel().toString(var_tagvar15));
                                                        out.write(">");
                                                    }
                                                    if (!renderContext.getObjectModel().toBoolean(var_tagvar15)) {
                                                        out.write("</script>");
                                                    }
                                                }
                                            }
                                        }
                                        out.write("\n");
                                    }
                                }
                                var_index12++;
                            }
                        }
                    }
                }
            }
        }
    }
    var_collectionvar5_list_coerced$ = null;
}
out.write("\n\n\n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

