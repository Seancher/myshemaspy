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
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

import net.sourceforge.schemaspy.model.Database;
import net.sourceforge.schemaspy.util.LineWriter;

/**
 * This page loads data from a file and show the data on a table
 *
 * @author Sergey Chernov
 */
public class HtmlTmsParamsPage extends HtmlFormatter {
    private static HtmlTmsParamsPage instance = new HtmlTmsParamsPage();

    /**
     * Singleton: Don't allow instantiation
     */
    private HtmlTmsParamsPage() {
    }

    /**
     * Singleton accessor
     *
     * @return the singleton instance
     */
    public static HtmlTmsParamsPage getInstance() {
        return instance;
    }

    public void write(Database database, String fileName, LineWriter out) throws IOException {
        writeHeader(database, out);
        writeTmsParams(fileName, out);
        writeFooter(out);
    }

    private void writeHeader(Database database, LineWriter html) throws IOException {
        writeHeader(database, null, "TMS Params", html);
        html.writeln("<table width='100%'>");
        if (sourceForgeLogoEnabled())
            html.writeln("  <tr><td class='container' align='right' valign='top' colspan='2'><a href='http://sourceforge.net' target='_blank'><img src='http://sourceforge.net/sflogo.php?group_id=137197&amp;type=1' alt='SourceForge.net' border='0' height='31' width='88'></a></td></tr>");
//        html.writeln("  <tr><td class='container'><b>Things :</b></td></tr>");
        html.writeln("</table>");
        html.writeln("<ul>");
    }

    private void writeTmsParams(String fileName, LineWriter out) throws IOException {
        int columnCounter = 0;
        int idxCurFrom = 0;
        int idxCurTo = 0;
        int numCols = 1;
        boolean even;
        String line;
        File flTable = new File("/Users/schernov/Dropbox/_Study&Work/YEAR 2016-.../Qvantel/projects/schemaspy/" + fileName);
        FileReader frd = new FileReader(flTable);
        BufferedReader brd = new BufferedReader(frd);

        out.writeln("<table class='dataTable' border='1' rules='groups'>");

        line = brd.readLine();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '|') {
                numCols = numCols + 1;
                out.writeln("<colgroup>");
            }
        }
        out.writeln("<colgroup>");

        // write header
        out.writeln("<thead align='left'>");
        out.writeln("<tr>");
        for (int i = 0; i < numCols; i++) {
            idxCurTo = line.indexOf('|', idxCurFrom) == -1 ? line.length(): line.indexOf('|', idxCurFrom);
            writeTableHeader(line, idxCurFrom, idxCurTo, out);
            idxCurFrom = idxCurTo + 1;
        }
        out.writeln("</tr>");
        out.writeln("</thead>");
        out.writeln("<tbody>");

        while ((line = brd.readLine()) != null) {
            if (columnCounter++ % 2 == 0)
                out.write("  <tr class='even'>");
            else
                out.write("  <tr class='odd'>");

            idxCurFrom = 0;
            idxCurTo = 0;
            for (int i = 0; i < numCols; i++) {
                idxCurTo = line.indexOf('|', idxCurFrom) == -1 ? line.length(): line.indexOf('|', idxCurFrom);
                writeTableLine(line, idxCurFrom, idxCurTo, out);
                idxCurFrom = idxCurTo + 1;
            }

            out.writeln(" </tr>");
        }
        brd.close();
        frd.close();

        out.writeln("</tbody>");
        out.writeln("</table>");
    }

    private void writeTableHeader(String line, int idxFrom, int idxTo, LineWriter out) throws IOException {
        out.writeln("  <th>");
        if (line != null) {
            for (int i = idxFrom; i < idxTo; ++i)
                out.write(line.charAt(i));
        }
        out.writeln("  </th>");
    }

    private void writeTableLine(String line, int idxFrom, int idxTo, LineWriter out) throws IOException {
        out.write(" <td class='detail'>");
        if (line != null) {
            for (int i = idxFrom; i < idxTo; ++i)
                out.write(line.charAt(i));
        }
        out.writeln("</td>");
    }

    @Override
    protected void writeFooter(LineWriter out) throws IOException {
        out.writeln("</ul>");
        super.writeFooter(out);
    }

    @Override
    protected boolean isTmsParamsPage() {
        return true;
    }
}
