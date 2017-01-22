/*******************************************************************************
 * Copyright (c) 2015 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package net.wasdev.samples.ferret.html;

import java.util.Arrays;
import java.util.Map;

public class Table {

    private static final String LF = "\r\n";
    
    private Map<String, Object> table;

    public Table(Map<String, Object> table) {
        this.table = table;
    }

    public String toHtml() {
        return toHtml("");
    }
    
    public String toHtml(String padding) {
        StringBuilder builder = new StringBuilder();
        builder.append(padding).append("<table>").append(LF);
        for (Map.Entry<String, Object> entry : table.entrySet()) {
            builder.append(padding).append("<tr>").append(LF);
            builder.append(padding).append("<td>").append(entry.getKey()).append("</td>").append(LF);            
            Object value = entry.getValue();
            if (value instanceof Map) {
                builder.append(padding).append("<td>").append(LF);
                Table table = new Table((Map)value);
                value = table.toHtml(padding + "  ");
                builder.append(value).append(LF);
                builder.append(padding).append("</td>").append(LF);
            } else if (value != null && value.getClass().isArray()) {
                value = Arrays.toString((Object[])value);
                builder.append(padding).append("<td>").append(value).append("</td>").append(LF);
            } else {
                builder.append(padding).append("<td>").append(value).append("</td>").append(LF);
            }
            builder.append(padding).append("</tr>").append(LF);
        }
        builder.append(padding).append("</table>");
        return builder.toString();
    }

}
