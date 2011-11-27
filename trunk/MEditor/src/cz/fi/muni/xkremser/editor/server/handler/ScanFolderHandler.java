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

package cz.fi.muni.xkremser.editor.server.handler;

import java.io.File;
import java.io.FileFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import javax.inject.Inject;

import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import org.apache.log4j.Logger;

import cz.fi.muni.xkremser.editor.client.util.Constants;

import cz.fi.muni.xkremser.editor.server.ServerUtils;
import cz.fi.muni.xkremser.editor.server.DAO.ImageResolverDAO;
import cz.fi.muni.xkremser.editor.server.config.EditorConfiguration;
import cz.fi.muni.xkremser.editor.server.config.EditorConfigurationImpl;
import cz.fi.muni.xkremser.editor.server.exception.DatabaseException;

import cz.fi.muni.xkremser.editor.shared.rpc.ImageItem;
import cz.fi.muni.xkremser.editor.shared.rpc.action.ScanFolderAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.ScanFolderResult;

// TODO: Auto-generated Javadoc
/**
 * The Class ScanFolderHandler.
 */
public class ScanFolderHandler
        implements ActionHandler<ScanFolderAction, ScanFolderResult> {

    /** The logger. */
    private static final Logger LOGGER = Logger.getLogger(ScanFolderHandler.class.getPackage().toString());

    /** The configuration. */
    private final EditorConfiguration configuration;

    /** The input queue dao. */
    @Inject
    private ImageResolverDAO imageResolverDAO;

    @Inject
    private Provider<HttpServletRequest> requestProvider;

    /**
     * Instantiates a new scan input queue handler.
     * 
     * @param configuration
     *        the configuration
     */
    @Inject
    public ScanFolderHandler(final EditorConfiguration configuration) {
        this.configuration = configuration;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.gwtplatform.dispatch.server.actionhandler.ActionHandler#execute(com
     * .gwtplatform.dispatch.shared.Action,
     * com.gwtplatform.dispatch.server.ExecutionContext)
     */
    @Override
    public ScanFolderResult execute(final ScanFolderAction action, final ExecutionContext context)
            throws ActionException {
        // parse input
        final String model = action.getModel();
        final String code = action.getCode();
        if (model == null || code == null) {
            return null;
        }
        final String base = configuration.getScanInputQueuePath();
        LOGGER.debug("Processing input queue: (model = " + model + ", code = " + code + ")");
        HttpServletRequest req = requestProvider.get();
        ServerUtils.checkExpiredSession(req.getSession());

        if (base == null || "".equals(base)) {
            LOGGER.error("Scanning folder: Action failed because attribut "
                    + EditorConfiguration.ServerConstants.INPUT_QUEUE + " is not set.");
            throw new ActionException("Scanning input queue: Action failed because attribut "
                    + EditorConfiguration.ServerConstants.INPUT_QUEUE + " is not set.");
        }
        String[] imageTypes = configuration.getImageExtensions();
        String prefix = base + File.separator + model + File.separator + code + File.separator;
        List<String> imgFileNames = scanDirectoryStructure(prefix, imageTypes);
        Collections.sort(imgFileNames);
        // due to gwt performance issues, more
        // concrete interface is used
        ArrayList<ImageItem> result = new ArrayList<ImageItem>(imgFileNames.size());
        ArrayList<ImageItem> toAdd = new ArrayList<ImageItem>();
        ArrayList<String> resolvedIdentifiers;

        try {
            resolvedIdentifiers = imageResolverDAO.resolveItems(imgFileNames);
            for (int i = 0; i < resolvedIdentifiers.size(); i++) {
                String newIdentifier = null;
                String resolvedIdentifier = resolvedIdentifiers.get(i);
                if (resolvedIdentifier == null) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(model).append('#').append(code).append('#').append(i);
                    newIdentifier = UUID.nameUUIDFromBytes(sb.toString().getBytes()).toString();
                    sb = new StringBuffer();
                    sb.append(EditorConfigurationImpl.DEFAULT_IMAGES_LOCATION).append(newIdentifier)
                            .append(Constants.JPEG_2000_EXTENSION);
                    resolvedIdentifier = sb.toString();
                    toAdd.add(new ImageItem(newIdentifier, resolvedIdentifier, imgFileNames.get(i)));
                }
                String uuid =
                        newIdentifier != null ? newIdentifier : resolvedIdentifier
                                .substring(resolvedIdentifier.lastIndexOf('/') + 1,
                                           resolvedIdentifier.lastIndexOf('.'));
                result.add(new ImageItem(uuid, resolvedIdentifier, imgFileNames.get(i)));
            }
            if (!toAdd.isEmpty()) {
                imageResolverDAO.insertItems(toAdd);
            }
        } catch (DatabaseException e) {
            throw new ActionException(e);
        }

        return new ScanFolderResult(result, toAdd);
    }

    /**
     * Scan directory structure.
     * 
     * @param pathPrefix
     *        the path prefix
     * @param relativePath
     *        the relative path
     * @param list
     *        the list
     * @param level
     *        the level
     * @return the list
     */
    private List<String> scanDirectoryStructure(String path, final String[] imageTypes) {
        File dir = new File(path);
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                for (String suffix : imageTypes) {
                    if (pathname.getName().endsWith("." + suffix)) {
                        return true;
                    }
                }
                return false;
            }

        };
        File[] imgs = dir.listFiles(filter);
        ArrayList<String> list = new ArrayList<String>(imgs != null ? imgs.length : 0);
        for (int i = 0; i < imgs.length; i++) {
            list.add(path + imgs[i].getName());
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.gwtplatform.dispatch.server.actionhandler.ActionHandler#getActionType
     * ()
     */
    @Override
    public Class<ScanFolderAction> getActionType() {
        return ScanFolderAction.class;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.gwtplatform.dispatch.server.actionhandler.ActionHandler#undo(com.
     * gwtplatform.dispatch.shared.Action,
     * com.gwtplatform.dispatch.shared.Result,
     * com.gwtplatform.dispatch.server.ExecutionContext)
     */
    @Override
    public void undo(ScanFolderAction action, ScanFolderResult result, ExecutionContext context)
            throws ActionException {
        // TODO Auto-generated method stub

    }

}