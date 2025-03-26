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

public final class customfooterlibs__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_clientlib = null;
Object _global_page = null;
Collection var_collectionvar2_list_coerced$ = null;
Collection var_attrmap13_list_coerced$ = null;
out.write("\n\n");
_global_clientlib = renderContext.call("use", "/libs/granite/sightly/templates/clientlib.html", obj());
out.write("\n    ");
{
    Object var_templatevar0 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "js");
    {
        String var_templateoptions1_field$_categories = "aemtutorial.base";
        {
            java.util.Map var_templateoptions1 = obj().with("categories", var_templateoptions1_field$_categories);
            callUnit(out, renderContext, var_templatevar0, var_templateoptions1);
        }
    }
}
out.write("\n\n\n\n");
_global_page = renderContext.call("use", com.adobe.cq.wcm.core.components.models.Page.class.getName(), obj());
{
    Object var_collectionvar2 = renderContext.getObjectModel().resolveProperty(_global_page, "htmlPageItems");
    {
        long var_size3 = ((var_collectionvar2_list_coerced$ == null ? (var_collectionvar2_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar2)) : var_collectionvar2_list_coerced$).size());
        {
            boolean var_notempty4 = (var_size3 > 0);
            if (var_notempty4) {
                {
                    long var_end7 = var_size3;
                    {
                        boolean var_validstartstepend8 = (((0 < var_size3) && true) && (var_end7 > 0));
                        if (var_validstartstepend8) {
                            if (var_collectionvar2_list_coerced$ == null) {
                                var_collectionvar2_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar2);
                            }
                            long var_index9 = 0;
                            for (Object item : var_collectionvar2_list_coerced$) {
                                {
                                    boolean var_traversal11 = (((var_index9 >= 0) && (var_index9 <= var_end7)) && true);
                                    if (var_traversal11) {
                                        out.write("\n    ");
                                        {
                                            boolean var_testvariable14 = (org.apache.sling.scripting.sightly.compiler.expression.nodes.BinaryOperator.strictEq(renderContext.getObjectModel().resolveProperty(renderContext.getObjectModel().resolveProperty(item, "location"), "name"), "footer"));
                                            if (var_testvariable14) {
                                                {
                                                    Object var_tagvar12 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(renderContext.getObjectModel().resolveProperty(item, "element"), "name"), "unsafe");
                                                    if (renderContext.getObjectModel().toBoolean(var_tagvar12)) {
                                                        out.write("<");
                                                        out.write(renderContext.getObjectModel().toString(var_tagvar12));
                                                    }
                                                    if (!renderContext.getObjectModel().toBoolean(var_tagvar12)) {
                                                        out.write("<script");
                                                    }
                                                    {
                                                        Object var_attrmap13 = renderContext.getObjectModel().resolveProperty(item, "attributes");
                                                        {
                                                            java.util.Map var_ignoredattributes15 = obj();
                                                            if (var_attrmap13_list_coerced$ == null) {
                                                                var_attrmap13_list_coerced$ = renderContext.getObjectModel().toCollection(var_attrmap13);
                                                            }
                                                            long var_attrindex18 = 0;
                                                            for (Object var_attrname16 : var_attrmap13_list_coerced$) {
                                                                {
                                                                    Object var_attrnameescaped17 = renderContext.call("xss", var_attrname16, "attributeName");
                                                                    if (renderContext.getObjectModel().toBoolean(var_attrnameescaped17)) {
                                                                        {
                                                                            Object var_isignoredattr19 = var_ignoredattributes15.get(var_attrname16);
                                                                            if (!renderContext.getObjectModel().toBoolean(var_isignoredattr19)) {
                                                                                {
                                                                                    Object var_attrcontent20 = renderContext.getObjectModel().resolveProperty(var_attrmap13, var_attrname16);
                                                                                    {
                                                                                        Object var_attrcontentescaped21 = renderContext.call("xss", var_attrcontent20, "attribute", var_attrnameescaped17);
                                                                                        {
                                                                                            boolean var_shoulddisplayattr22 = (((null != var_attrcontentescaped21) && (!"".equals(var_attrcontentescaped21))) && ((!"".equals(var_attrcontent20)) && (!((Object)false).equals(var_attrcontent20))));
                                                                                            if (var_shoulddisplayattr22) {
                                                                                                out.write(" ");
                                                                                                out.write(renderContext.getObjectModel().toString(var_attrnameescaped17));
                                                                                                {
                                                                                                    boolean var_istrueattr23 = (var_attrcontent20.equals(true));
                                                                                                    if (!var_istrueattr23) {
                                                                                                        out.write("=\"");
                                                                                                        out.write(renderContext.getObjectModel().toString(var_attrcontentescaped21));
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
                                                                var_attrindex18++;
                                                            }
                                                        }
                                                        var_attrmap13_list_coerced$ = null;
                                                    }
                                                    out.write(">");
                                                    if (renderContext.getObjectModel().toBoolean(var_tagvar12)) {
                                                        out.write("</");
                                                        out.write(renderContext.getObjectModel().toString(var_tagvar12));
                                                        out.write(">");
                                                    }
                                                    if (!renderContext.getObjectModel().toBoolean(var_tagvar12)) {
                                                        out.write("</script>");
                                                    }
                                                }
                                            }
                                        }
                                        out.write("\n");
                                    }
                                }
                                var_index9++;
                            }
                        }
                    }
                }
            }
        }
    }
    var_collectionvar2_list_coerced$ = null;
}
out.write("\n\n\n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

