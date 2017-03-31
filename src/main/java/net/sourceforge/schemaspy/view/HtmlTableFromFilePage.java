/*
 * This file is a part of the SchemaSpy project (http://schemaspy.sourceforge.net).
 * Copyright (C) 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2014 John Currier
 *
 * SchemaSpy is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * SchemaSpy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.sourceforge.schemaspy.view;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.sourceforge.schemaspy.model.Database;
import net.sourceforge.schemaspy.util.LineWriter;

/**
 * This page loads data from a file and show the data on a table
 *
 * @author Sergey Chernov
 */
public class HtmlTableFromFilePage extends HtmlFormatter {
    private static HtmlTableFromFilePage instance = new HtmlTableFromFilePage();

    /**
     * Singleton: Don't allow instantiation
     */
    private HtmlTableFromFilePage() {
    }

    /**
     * Singleton accessor
     *
     * @return the singleton instance
     */
    public static HtmlTableFromFilePage getInstance() {
        return instance;
    }

    public void write(Database database, String fileName, LineWriter out) throws IOException {
        writeHeader(database, out);
        writeMenuTree(fileName, out);
        writeFooter(out);
    }

    private void writeHeader(Database database, LineWriter html) throws IOException {
        writeHeader(database, null, "Menu Tree", html);
        html.writeln("<table width='100%'>");
        if (sourceForgeLogoEnabled())
            html.writeln("  <tr><td class='container' align='right' valign='top' colspan='2'><a href='http://sourceforge.net' target='_blank'><img src='http://sourceforge.net/sflogo.php?group_id=137197&amp;type=1' alt='SourceForge.net' border='0' height='31' width='88'></a></td></tr>");
        html.writeln("  <tr><td class='container'><b>Things that might not be 'quite right' about your schema:</b></td></tr>");
        html.writeln("</table>");
        html.writeln("<ul>");
    }

    private void writeMenuTree(String fileName, LineWriter out) throws IOException {
        out.writeln("<li>");
        out.writeln("<b>Columns whose name and type imply a relationship to another table's primary key:</b>");
        int numDetected = 0;

        if (numDetected > 0) {
            out.writeln("<table class='dataTable' border='1' rules='groups'>");
            out.writeln("<colgroup>");
            out.writeln("<colgroup>");
            out.writeln("<thead align='left'>");
            out.writeln("<tr>");
            out.writeln("  <th>Child Column</th>");
            out.writeln("  <th>Implied Parent Column</th>");
            out.writeln("</tr>");
            out.writeln("</thead>");
            out.writeln("<tbody>");

//            for (ForeignKeyConstraint impliedConstraint : impliedConstraints) {
//                Table childTable = impliedConstraint.getChildTable();
//                if (!childTable.isView()) {
//                    out.writeln(" <tr>");
//
//                    out.write("  <td class='detail'>");
//                    String tableName = childTable.getName();
//                    out.write("<a href='tables/");
//                    out.write(urlEncode(tableName));
//                    out.write(".html'>");
//                    out.write(tableName);
//                    out.write("</a>.");
//                    out.write(ForeignKeyConstraint.toString(impliedConstraint.getChildColumns()));
//                    out.writeln("</td>");
//
//                    out.write("  <td class='detail'>");
//                    tableName = impliedConstraint.getParentTable().getName();
//                    out.write("<a href='tables/");
//                    out.write(urlEncode(tableName));
//                    out.write(".html'>");
//                    out.write(tableName);
//                    out.write("</a>.");
//                    out.write(ForeignKeyConstraint.toString(impliedConstraint.getParentColumns()));
//                    out.writeln("</td>");
//
//                    out.writeln(" </tr>");
//                }
//            }

            out.writeln("</tbody>");
            out.writeln("</table>");
        }
        out.writeln("<p></li>");
    }

    @Override
    protected void writeFooter(LineWriter out) throws IOException {
        out.writeln("</ul>");
        super.writeFooter(out);
    }

    @Override
    protected boolean isTableFromFilePage() {
        return true;
    }
}
