/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jimmy
 */
public class UtilesJson {

    private static Logger log = LoggerFactory.getLogger(UtilesJson.class);
    private ObjectMapper mapper = new ObjectMapper();

    public UtilesJson() {
        mapper = new ObjectMapper();
    }

    /**
     * Retorna el valor String del campo indicado
     *
     * @param json
     * @return
     */
    public static String getString(String json, String campo) throws ClassCastException {
        JsonElement jsonElement = new JsonParser().parse(json);

        JsonObject obj = jsonElement.getAsJsonObject();
        return obj.get(campo).getAsString();
    }

    /**
     * * Retorna el valor String que está dentro del sub objeto que está en la
     * raíz.
     *
     * @param json
     * @param subobject
     * @param campo
     * @return
     * @throws ClassCastException
     */
    public static String getStringEnObject(String json, String subobject, String campo) throws ClassCastException {
        JsonElement jsonElement = new JsonParser().parse(json);

        JsonObject obj = jsonElement.getAsJsonObject();
        if (obj.has(subobject)) {
            JsonObject obj2 = obj.getAsJsonObject(subobject);
            if (obj2.has(campo)) {
                return obj2.get(campo).getAsString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Retorna el valor Integer del campo indicado
     *
     * @param json
     * @return
     */
    public static Integer getInteger(String json, String campo) throws ClassCastException {
        JsonElement jsonElement = new JsonParser().parse(json);

        JsonObject obj = jsonElement.getAsJsonObject();
        return obj.get(campo).getAsInt();
    }

    /**
     * Retorna el valor Integer que está dentro del sub objeto que está en la
     * raíz.
     *
     * @param json
     * @param subobject
     * @param campo
     * @return
     * @throws ClassCastException
     */
    public static Integer getIntegerEnObject(String json, String subobject, String campo) throws ClassCastException {
        JsonElement jsonElement = new JsonParser().parse(json);

        JsonObject obj = jsonElement.getAsJsonObject();
        if (obj.has(subobject)) {
            JsonObject obj2 = obj.getAsJsonObject(subobject);
            if (obj2.has(campo)) {
                return obj2.get(campo).getAsInt();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Retorna el valor Double del campo indicado
     *
     * @param json
     * @return
     */
    public static Double getDouble(String json, String campo) throws ClassCastException {
        JsonElement jsonElement = new JsonParser().parse(json);

        JsonObject obj = jsonElement.getAsJsonObject();
        return obj.get(campo).getAsDouble();
    }

    /**
     * * Retorna el valor Double que está dentro del sub objeto que está en la
     * raíz.
     *
     * @param json
     * @param subobject
     * @param campo
     * @return
     * @throws ClassCastException
     */
    public static Double getDoubleEnObject(String json, String subobject, String campo) throws ClassCastException {
        JsonElement jsonElement = new JsonParser().parse(json);

        JsonObject obj = jsonElement.getAsJsonObject();
        if (obj.has(subobject)) {
            JsonObject obj2 = obj.getAsJsonObject(subobject);
            if (obj2.has(campo)) {
                return obj2.get(campo).getAsDouble();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Retorna el valor Long del campo indicado
     *
     * @param json
     * @return
     */
    public static Long getLong(String json, String campo) throws ClassCastException {
        JsonElement jsonElement = new JsonParser().parse(json);

        JsonObject obj = jsonElement.getAsJsonObject();
        return obj.get(campo).getAsLong();
    }

    /**
     * * Retorna el valor Long que está dentro del sub objeto que está en la
     * raíz.
     *
     * @param json
     * @param subobject
     * @param campo
     * @return
     * @throws ClassCastException
     */
    public static Long getLongEnObject(String json, String subobject, String campo) throws ClassCastException {
        JsonElement jsonElement = new JsonParser().parse(json);

        JsonObject obj = jsonElement.getAsJsonObject();
        if (obj.has(subobject)) {
            JsonObject obj2 = obj.getAsJsonObject(subobject);
            if (obj2.has(campo)) {
                return obj2.get(campo).getAsLong();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public String mappingObject(Object obj) throws NullPointerException {
        try {
            ObjectWriter writer = mapper.writer().with(JsonGenerator.Feature.ESCAPE_NON_ASCII);
            return writer.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            log.error(UtilesString.getDetalleException(ex));
            return null;
        }
    }
}
