/*
 * Metadata Editor
 * @author Jiri Kremser
 * 
 * 
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Jiri Kremser (kremser@mzk.cz)
 * Moravian Library in Brno
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 
 */

package cz.fi.muni.xkremser.editor.client.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.regexp.shared.SplitResult;
import com.google.gwt.user.client.DOM;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;

import cz.fi.muni.xkremser.editor.client.CreateObjectException;
import cz.fi.muni.xkremser.editor.client.mods.StringPlusAuthorityClient;
import cz.fi.muni.xkremser.editor.client.mods.StringPlusAuthorityPlusTypeClient;
import cz.fi.muni.xkremser.editor.client.view.CreateObjectMenuView.SubstructureTreeNode;
import cz.fi.muni.xkremser.editor.client.view.other.RecentlyModifiedRecord;

import cz.fi.muni.xkremser.editor.shared.domain.DigitalObjectModel;
import cz.fi.muni.xkremser.editor.shared.rpc.MetadataBundle;
import cz.fi.muni.xkremser.editor.shared.rpc.NewDigitalObject;
import cz.fi.muni.xkremser.editor.shared.rpc.RecentlyModifiedItem;
import cz.fi.muni.xkremser.editor.shared.rpc.TreeStructureBundle.TreeStructureNode;

/**
 * @author Jiri Kremser
 */
public class ClientUtils {

    /**
     * <p>
     * Converts a String to a boolean (optimised for performance).
     * </p>
     * <p>
     * <code>'true'</code>, <code>'on'</code> or <code>'yes'</code> (case
     * insensitive) will return <code>true</code>. Otherwise, <code>false</code>
     * is returned.
     * </p>
     * <p>
     * This method performs 4 times faster (JDK1.4) than
     * <code>Boolean.valueOf(String)</code>. However, this method accepts 'on'
     * and 'yes' as true values.
     * 
     * <pre>
	 *   BooleanUtils.toBoolean(null)    = false
	 *   BooleanUtils.toBoolean("true")  = true
	 *   BooleanUtils.toBoolean("TRUE")  = true
	 *   BooleanUtils.toBoolean("tRUe")  = true
	 *   BooleanUtils.toBoolean("on")    = true
	 *   BooleanUtils.toBoolean("yes")   = true
	 *   BooleanUtils.toBoolean("false") = false
	 *   BooleanUtils.toBoolean("x gti") = false
	 * </pre>
     * 
     * @param str
     *        the String to check
     * @return the boolean value of the string, <code>false</code> if no match
     */
    public static boolean toBoolean(String str) {
        // Previously used equalsIgnoreCase, which was fast for interned 'true'.
        // Non interned 'true' matched 15 times slower.
        //
        // Optimisation provides same performance as before for interned 'true'.
        // Similar performance for null, 'false', and other strings not length
        // 2/3/4.
        // 'true'/'TRUE' match 4 times slower, 'tRUE'/'True' 7 times slower.
        if (str == "true") {
            return true;
        }
        if (str == null) {
            return false;
        }
        switch (str.length()) {
            case 2: {
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                return (ch0 == 'o' || ch0 == 'O') && (ch1 == 'n' || ch1 == 'N');
            }
            case 3: {
                char ch = str.charAt(0);
                if (ch == 'y') {
                    return (str.charAt(1) == 'e' || str.charAt(1) == 'E')
                            && (str.charAt(2) == 's' || str.charAt(2) == 'S');
                }
                if (ch == 'Y') {
                    return (str.charAt(1) == 'E' || str.charAt(1) == 'e')
                            && (str.charAt(2) == 'S' || str.charAt(2) == 's');
                }
                return false;
            }
            case 4: {
                char ch = str.charAt(0);
                if (ch == 't') {
                    return (str.charAt(1) == 'r' || str.charAt(1) == 'R')
                            && (str.charAt(2) == 'u' || str.charAt(2) == 'U')
                            && (str.charAt(3) == 'e' || str.charAt(3) == 'E');
                }
                if (ch == 'T') {
                    return (str.charAt(1) == 'R' || str.charAt(1) == 'r')
                            && (str.charAt(2) == 'U' || str.charAt(2) == 'u')
                            && (str.charAt(3) == 'E' || str.charAt(3) == 'e');
                }
            }
        }
        return false;
    }

    /**
     * To record.
     * 
     * @param item
     *        the item
     * @return the recently modified record
     */
    public static RecentlyModifiedRecord toRecord(RecentlyModifiedItem item) {
        return new RecentlyModifiedRecord(item.getUuid(),
                                          item.getName(),
                                          item.getDescription(),
                                          item.getModel());
    }

    /**
     * Escape html.
     * 
     * @param maybeHtml
     *        the maybe html
     * @return the string
     */
    public static String escapeHtml(String maybeHtml) {
        final com.google.gwt.user.client.Element div = DOM.createDiv();
        DOM.setInnerText(div, maybeHtml);
        return DOM.getInnerHTML(div);
    }

    /**
     * To list of list of strings.
     * 
     * @param frequency
     *        the frequency
     * @return the list
     */
    public static List<List<String>> toListOfListOfStrings(List<StringPlusAuthorityClient> frequency) {
        if (frequency == null) return null;
        List<List<String>> outerList = new ArrayList<List<String>>(frequency.size());
        for (StringPlusAuthorityClient value : frequency) {
            if (value == null) continue;
            List<String> innerList = new ArrayList<String>(2);
            innerList.add(value.getValue());
            innerList.add(value.getAuthority());
            outerList.add(innerList);
        }
        return outerList;
    }

    /**
     * To list of list of strings.
     * 
     * @param toConvert
     *        the to convert
     * @param something
     *        the something
     * @return the list
     */
    public static List<List<String>> toListOfListOfStrings(List<StringPlusAuthorityPlusTypeClient> toConvert,
                                                           boolean something) {
        if (toConvert == null) return null;
        List<List<String>> outerList = new ArrayList<List<String>>(toConvert.size());
        for (StringPlusAuthorityPlusTypeClient value : toConvert) {
            if (value == null) continue;
            List<String> innerList = new ArrayList<String>(2);
            innerList.add(value.getValue());
            innerList.add(value.getType());
            innerList.add(value.getAuthority());
            outerList.add(innerList);
        }
        return outerList;
    }

    /**
     * Subtract.
     * 
     * @param whole
     *        the whole
     * @param part
     *        the part
     * @return the list grid record[]
     */
    public static ListGridRecord[] subtract(ListGridRecord[] whole, ListGridRecord[] part) {
        if (whole == null || whole.length == 0) return null;
        if (part == null || part.length == 0) return whole;
        List<ListGridRecord> list = new ArrayList<ListGridRecord>();
        for (ListGridRecord record : whole) {
            boolean add = true;
            for (ListGridRecord counterpart : part) {
                if (record == counterpart) {
                    add = false;
                }
            }
            if (add) list.add(record);
        }
        return list.toArray(new ListGridRecord[] {});
    }

    public static NewDigitalObject createTheStructure(MetadataBundle bundle, Tree tree, boolean visible)
            throws CreateObjectException {

        Map<String, NewDigitalObject> processedPages = new HashMap<String, NewDigitalObject>();

        TreeNode root = tree.findById(SubstructureTreeNode.ROOT_OBJECT_ID);
        if (root == null) {
            return null;
        }

        String name = root.getAttribute(Constants.ATTR_NAME);
        if (name == null || "".equals(name)) {
            throw new CreateObjectException("unknown name");
        }
        TreeNode[] children = tree.getChildren(root);
        if (children.length == 0) {
            return new NewDigitalObject(name);
        }
        String modelString = root.getAttribute(Constants.ATTR_TYPE_ID);
        if (modelString == null || "".equals(modelString)) {
            throw new CreateObjectException("unknown type");
        }
        DigitalObjectModel model = null;
        try {
            model = DigitalObjectModel.parseString(modelString);
        } catch (RuntimeException ex) {
            throw new CreateObjectException("unknown type");
        }
        boolean exists = root.getAttributeAsBoolean(Constants.ATTR_EXIST);
        NewDigitalObject newObj = new NewDigitalObject(0, name, model, bundle, exists ? name : null, exists);
        newObj.setVisible(visible);
        int lastPageIndex = -1;
        for (TreeNode child : children) {
            NewDigitalObject newChild =
                    createTheStructure(bundle, tree, child, visible, lastPageIndex, processedPages);
            newObj.getChildren().add(newChild);
            lastPageIndex = newChild.getPageIndex();
        }
        return newObj;
    }

    private static NewDigitalObject createTheStructure(MetadataBundle bundle,
                                                       Tree tree,
                                                       TreeNode node,
                                                       boolean visible,
                                                       int lastPageIndex,
                                                       Map<String, NewDigitalObject> processedPages)
            throws CreateObjectException {

        String modelString = node.getAttribute(Constants.ATTR_TYPE_ID);
        if (modelString == null || "".equals(modelString)) {
            throw new CreateObjectException("unknown type");
        }
        DigitalObjectModel model = null;
        try {
            model = DigitalObjectModel.parseString(modelString);
        } catch (RuntimeException ex) {
            throw new CreateObjectException("unknown type");
        }
        String imgUuid = node.getAttribute(Constants.ATTR_PICTURE);

        if (!processedPages.containsKey(imgUuid)) {

            int pageIndex = -1;
            if (model == DigitalObjectModel.PAGE) {
                if (imgUuid == null || "".equals(imgUuid)) {
                    throw new CreateObjectException("unknown uuid");
                }
                if (lastPageIndex < 0) {
                    lastPageIndex = 0;
                }
                pageIndex = ++lastPageIndex;
            }

            String name = node.getAttribute(Constants.ATTR_NAME);
            if (name == null || "".equals(name)) {
                throw new CreateObjectException("unknown name");
            }

            Boolean exists = node.getAttributeAsBoolean(Constants.ATTR_EXIST);

            NewDigitalObject newObj = new NewDigitalObject(pageIndex, name, model, bundle, null, exists);

            newObj.setVisible(visible);
            String dateIssued = node.getAttribute(Constants.ATTR_DATE_ISSUED);
            if (dateIssued != null && !"".equals(dateIssued)) {
                newObj.setDateIssued(dateIssued);
            }

            if (exists) {
                if (imgUuid != null && !"".equals(imgUuid)) {
                    newObj.setUuid(imgUuid.startsWith("uuid:") ? imgUuid.substring("uuid:".length())
                            : imgUuid);
                } else {
                    throw new CreateObjectException("unknown uuid of an existing object");
                }
            }

            String altoPath = node.getAttribute(Constants.ATTR_ALTO_PATH);
            if (altoPath != null && !"".equals(altoPath)) {
                newObj.setAltoPath(altoPath);
            }

            String ocrPath = node.getAttribute(Constants.ATTR_OCR_PATH);
            if (ocrPath != null && !"".equals(ocrPath)) {
                newObj.setOcrPath(ocrPath);
            }

            newObj.setPath(imgUuid);
            newObj.setPageType(node.getAttribute(Constants.ATTR_PAGE_TYPE));
            TreeNode[] children = tree.getChildren(node);

            int childrenLastPageIndex = -1;
            for (TreeNode child : children) {
                NewDigitalObject newChild =
                        createTheStructure(bundle,
                                           tree,
                                           child,
                                           visible,
                                           childrenLastPageIndex,
                                           processedPages);
                newObj.getChildren().add(newChild);
                childrenLastPageIndex = newChild.getPageIndex();
            }

            if (model == DigitalObjectModel.PAGE) {
                processedPages.put(imgUuid, newObj);
            }
            return newObj;
        } else {
            return new NewDigitalObject(processedPages.get(imgUuid));
        }
    }

    public static String toStringTree(NewDigitalObject node) {
        final StringBuilder buffer = new StringBuilder();
        return toStringTreeHelper(node, buffer, new LinkedList<Iterator<NewDigitalObject>>()).toString();
    }

    private static String toStringTreeDrawLines(List<Iterator<NewDigitalObject>> parentIterators,
                                                boolean amLast) {
        StringBuilder result = new StringBuilder();
        Iterator<Iterator<NewDigitalObject>> it = parentIterators.iterator();
        while (it.hasNext()) {
            Iterator<NewDigitalObject> anIt = it.next();
            if (anIt.hasNext() || (!it.hasNext() && amLast)) {
                result.append("   |");
            } else {
                result.append("    ");
            }
        }
        return result.toString();
    }

    private static StringBuilder toStringTreeHelper(NewDigitalObject node,
                                                    StringBuilder buffer,
                                                    List<Iterator<NewDigitalObject>> parentIterators) {
        if (!parentIterators.isEmpty()) {
            boolean amLast = !parentIterators.get(parentIterators.size() - 1).hasNext();
            buffer.append("\n");
            String lines = toStringTreeDrawLines(parentIterators, amLast);
            buffer.append(lines);
            buffer.append("\n");
            buffer.append(lines);
            buffer.append("- ");
        }
        buffer.append(node.toString());
        if (node.getChildren() != null && node.getChildren().size() > 0) {
            Iterator<NewDigitalObject> it = node.getChildren().iterator();
            parentIterators.add(it);
            while (it.hasNext()) {
                NewDigitalObject child = it.next();
                toStringTreeHelper(child, buffer, parentIterators);
            }
            parentIterators.remove(it);
        }
        return buffer;
    }

    private static final String[] RCODE_1 = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V",
            "IV", "I"};

    private static final String[] RCODE_2 = {"M", "DCCCC", "D", "CCCC", "C", "LXXXX", "L", "XXXX", "X",
            "VIIII", "V", "IIII", "I"};
    private static final int[] BVAL = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    public static String decimalToRoman(int binary, boolean oldFashion) {
        if (binary <= 0 || binary >= 4000) {
            throw new NumberFormatException("Value outside roman numeral range.");
        }
        String roman = "";

        // Loop from biggest value to smallest, successively subtracting,
        // from the binary value while adding to the roman representation.
        for (int i = 0; i < RCODE_1.length; i++) {
            while (binary >= BVAL[i]) {
                binary -= BVAL[i];
                roman += oldFashion ? RCODE_2[i] : RCODE_1[i];
            }
        }
        return roman;
    }

    public static int romanToDecimal(String roman) {
        int number = 0;
        String romanSubstring = roman;
        for (int i = 0; i < RCODE_1.length && !"".equals(romanSubstring); i++) {
            if (romanSubstring.startsWith(RCODE_1[i])) {
                number += BVAL[i];
                romanSubstring = romanSubstring.substring(RCODE_1[i].length());
                i--;
            }
        }
        for (int i = 0; i < RCODE_2.length && !"".equals(romanSubstring); i++) {
            if (romanSubstring.startsWith(RCODE_2[i])) {
                number += BVAL[i];
                romanSubstring = romanSubstring.substring(RCODE_2[i].length());
                i--;
            }
        }
        if ("".equals(romanSubstring)) return number;
        return Integer.MIN_VALUE;
    }

    /**
     * String.format is not accessible on the gwt client-side
     * 
     * @param format
     * @param args
     * @return formatted string
     */
    public static String format(final String format, final char escapeCharacter, final Object... args) {
        final RegExp regex = RegExp.compile("%" + escapeCharacter);
        final SplitResult split = regex.split(format);
        final StringBuffer msg = new StringBuffer();
        for (int pos = 0; pos < split.length() - 1; pos += 1) {
            msg.append(split.get(pos));
            msg.append(args[pos].toString());
        }
        msg.append(split.get(split.length() - 1));
        return msg.toString();
    }

    public static class SimpleDateFormat {

        private DateTimeFormat format;

        public SimpleDateFormat() {
            super();
        }

        protected SimpleDateFormat(DateTimeFormat format) {
            this.format = format;
        }

        public SimpleDateFormat(String pattern) {
            applyPattern(pattern);
        }

        public void applyPattern(String pattern) {
            this.format = DateTimeFormat.getFormat(pattern);
        }

        public String format(Date date) {
            return format.format(date);
        }

        /**
         * Parses text and returns the corresponding date object.
         */
        public Date parse(String source) {
            return format.parse(source);
        }

    }

    public static List<TreeStructureNode> toNodes(Record[] records) {
        List<TreeStructureNode> retList = new ArrayList<TreeStructureNode>();
        for (int i = 0; i < records.length; i++) {
            retList.add(toNode(records[i], i));
        }
        return retList;
    }

    private static TreeStructureNode toNode(Record treeNode, int order) {
        String type = treeNode.getAttribute(Constants.ATTR_TYPE);

        return new TreeStructureNode(type == null ? null : treeNode.getAttribute(Constants.ATTR_ID),
                                     treeNode.getAttribute(Constants.ATTR_PARENT),
                                     trimLabel(treeNode.getAttribute(Constants.ATTR_NAME), 255),
                                     treeNode.getAttribute(Constants.ATTR_PICTURE),
                                     type == null ? treeNode.getAttribute(Constants.ATTR_MODEL) : type,
                                     type == null ? String.valueOf(order) : treeNode
                                             .getAttribute(Constants.ATTR_TYPE_ID),
                                     treeNode.getAttribute(Constants.ATTR_PAGE_TYPE),
                                     treeNode.getAttribute(Constants.ATTR_DATE_ISSUED),
                                     treeNode.getAttribute(Constants.ATTR_ALTO_PATH),
                                     type == null ? treeNode.getAttribute(Constants.ATTR_ID) : treeNode
                                             .getAttribute(Constants.ATTR_OCR_PATH),
                                     treeNode.getAttributeAsBoolean(Constants.ATTR_EXIST));
    }

    public static TreeNode toRecord(TreeStructureNode node, boolean page) {
        TreeNode rec = new TreeNode();
        if (!page) rec.setAttribute(Constants.ATTR_ID, node.getPropId());
        rec.setAttribute(Constants.ATTR_PARENT, node.getPropParent());
        rec.setAttribute(Constants.ATTR_NAME, node.getPropName());
        rec.setAttribute(Constants.ATTR_PICTURE, node.getPropPicture());
        rec.setAttribute(page ? Constants.ATTR_MODEL : Constants.ATTR_TYPE, node.getPropType());
        if (!page) rec.setAttribute(Constants.ATTR_TYPE_ID, node.getPropTypeId());
        rec.setAttribute(Constants.ATTR_PAGE_TYPE, node.getPropPageType());
        rec.setAttribute(Constants.ATTR_DATE_ISSUED, node.getPropDateIssued());
        rec.setAttribute(Constants.ATTR_ALTO_PATH, node.getPropAltoPath());
        rec.setAttribute(page ? Constants.ATTR_ID : Constants.ATTR_OCR_PATH, node.getPropOcrPath());
        rec.setAttribute(Constants.ATTR_EXIST, toBoolean(node.getPropName()));
        return rec;
    }

    public static String recordsToString(Record[] records) {
        if (records == null || records.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < records.length; i++) {
            sb.append(" ");
            String fyzNum = String.valueOf(i + 1);
            for (int j = fyzNum.length(); j < 3; j++) {
                sb.append(' ');
            }
            sb.append(fyzNum);
            sb.append(records[i].getAttribute(Constants.ATTR_PAGE_TYPE));
            sb.append("  --  ");
            sb.append(records[i].getAttribute(Constants.ATTR_NAME));
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String trimLabel(String originalString, int maxLength) {
        if (originalString.length() <= maxLength) {
            return originalString;
        } else {
            return originalString.substring(0, maxLength - Constants.OVER_MAX_LENGTH_SUFFIX.length())
                    + Constants.OVER_MAX_LENGTH_SUFFIX;
        }
    }
}
