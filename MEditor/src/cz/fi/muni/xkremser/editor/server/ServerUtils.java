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

package cz.fi.muni.xkremser.editor.server;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import javax.xml.bind.JAXBElement;

import com.google.inject.Provider;
import com.gwtplatform.dispatch.shared.ActionException;

import org.apache.log4j.Logger;

import cz.fi.muni.xkremser.editor.client.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerUtils.
 */
public class ServerUtils {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(ServerUtils.class);

    /**
     * Checks if is caused by exception.
     * 
     * @param t
     *        the t
     * @param type
     *        the type
     * @return true, if is caused by exception
     */
    public static boolean isCausedByException(Throwable t, Class<? extends Exception> type) {
        if (t == null) return false;
        Throwable aux = t;
        while (aux != null) {
            if (type.isInstance(aux)) return true;
            aux = aux.getCause();
        }
        return false;
    }

    public static void checkExpiredSession(Provider<HttpSession> httpSessionProvider) throws ActionException {
        checkExpiredSession(httpSessionProvider.get());
    }

    public static void checkExpiredSession(HttpSession session) throws ActionException {
        if (session.getAttribute(HttpCookies.SESSION_ID_KEY) == null) {
            throw new ActionException(Constants.SESSION_EXPIRED_FLAG + URLS.ROOT()
                    + (URLS.LOCALHOST() ? URLS.LOGIN_LOCAL_PAGE : URLS.LOGIN_PAGE));
        }
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        if (clazz == null || clazz.getName().contains("java.util.List")
                || clazz.getName().contains("java.lang.String")) {
            return Collections.<Field> emptyList();
        }
        List<Field> fields = new ArrayList<Field>();
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        if (clazz.getSuperclass() != null) {
            fields.addAll(getAllFields(clazz.getSuperclass()));
        }
        return fields;
    }

    private static boolean hasOnlyNullFields(Object object) {
        if (object == null) {
            return true;
        }
        boolean isNull = true;
        try {
            if (object.getClass().getName().contains("JAXBElement")) {
                JAXBElement<?> elem = (JAXBElement<?>) object;
                if (!elem.isNil()) {
                    return hasOnlyNullFields(elem.getValue());
                } else
                    return false;
            } else if (object.getClass().getName().contains("java.lang.String")) {
                return "".equals(((String) object).trim());
            }
            List<Field> fields = getAllFields(object.getClass());

            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                if (field.get(object) != null) {
                    if (field.getType().getName().contains("java.util.List")
                            && !((List<?>) field.get(object)).isEmpty()) {
                        List<Object> list = (List<Object>) field.get(object);
                        for (int i = 0; i < list.size(); i++) {
                            if (!hasOnlyNullFields(list.get(i))) {
                                isNull = false;
                                break;
                            }
                        }
                        if (!isNull) {
                            break;
                        }
                    } else if (field.getType().getName().contains("java.lang.String")
                            && !"".equals(((String) field.get(object)).trim())) {
                        isNull = false;
                        break;
                    } else if (field.getType().getName().contains("CodeOrText")
                            || field.getType().getName().contains("NameTypeAttribute")
                            || field.getType().getName().contains("PlaceAuthority")
                            || field.getType().getName().contains("Yes")) {
                        if (!"".equals(field.getClass().getField("value").get(field.get(object)))) {
                            isNull = false;
                            break;
                        }
                    } else if (field.getType().getName().contains("long")) {
                        continue;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("Unable to inspect the structure via reflection", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("Unable to inspect the structure via reflection", e);
        } catch (SecurityException e) {

        } catch (NoSuchFieldException e) {

        }
        return isNull;
    }

    public static <T> T collapseStructure(T object) {
        if (hasOnlyNullFields(object)) {
            return null;
        }
        List<Field> fields = getAllFields(object.getClass());
        try {
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                if (field.get(object) != null) {
                    if (field.getType().getName().contains("java.util.List")) {
                        List<Object> list = (List<Object>) field.get(object);
                        List<Object> newList = new ArrayList<Object>(list.size());
                        boolean isNull = true;
                        for (int i = 0; i < list.size(); i++) {
                            Object o = collapseStructure(list.get(i));
                            if (o != null) {
                                isNull = false;
                                newList.add(o);
                            }
                        }
                        field.set(object, isNull ? null : newList);
                        continue;
                    } else if (field.getType().getName().contains("java.lang.String")) {
                        if ("".equals(((String) field.get(object)).trim())) {
                            field.set(object, null);
                        }
                        continue;
                    } else if (field.getType().getName().contains("CodeOrText")
                            || field.getType().getName().contains("NameTypeAttribute")
                            || field.getType().getName().contains("PlaceAuthority")
                            || field.getType().getName().contains("Yes")) {
                        if ("".equals(field.getClass().getField("value").get(field.get(object)))) {
                            field.set(object, null);
                        }
                        continue;
                    } else {
                        collapseStructure(field.get(object));
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("Unable to inspect the structure via reflection", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("Unable to inspect the structure via reflection", e);
        } catch (SecurityException e) {
            LOGGER.error("Unable to inspect the structure via reflection", e);
        } catch (NoSuchFieldException e) {
            LOGGER.error("Unable to inspect the structure via reflection", e);
        }
        if (hasOnlyNullFields(object)) {
            return null;
        }
        return object;
    }
}
