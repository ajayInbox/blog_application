package com.aj.blog.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.encoder.Encode;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Collection;
import java.util.regex.Pattern;

public class SanitizeDTO {

    private static final Logger logger = LogManager.getLogger(SanitizeDTO.class);
    private static final Pattern HTML_TAG_PATTERN = Pattern.compile("<[^>]*>");

    public static <T> T sanitizeDTOClass(T oldObj){
        try{
            Class<?> clazz = oldObj.getClass();
            T sanitizedObj = (T) clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for(Field field: fields){
                logger.info("field value" +field);
                field.setAccessible(true);
                Object value = field.get(oldObj);
                logger.info("old object value: " +value.toString());
                Object sanitizedValue = value;
                if (value == null || value instanceof Long || value instanceof Boolean || value instanceof LocalDate) {
                    sanitizedValue = value; // If the object is primitive or a number, return it directly
                }
                else if( value instanceof String){
                    sanitizedValue = Encode.forHtml(value.toString());
                    logger.info("sanitized value: "+sanitizedValue.toString());
                } else if (value != null && !field.getType().isPrimitive()) {
                    sanitizedValue = sanitizeObject(value);
                }
                field.set(sanitizedObj, sanitizedValue);
            }
            return sanitizedObj;
        } catch (InstantiationException |IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object sanitizeObject(Object object) throws IllegalAccessException {
        if (object == null || object instanceof Long || object instanceof Boolean || object instanceof LocalDate) {
            return object; // If the object is primitive or a number, return it directly
        }
        else if(object instanceof Collection){
            logger.info("in collection if block");
            Collection<?> collection = (Collection<?>) object;
            for (Object item : collection) {
                if (item != null && !item.getClass().isPrimitive()) {
                    sanitizeObject(item);
                }
            }
        } else {
            logger.info("in class object block");
            Class<?> clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(object);
                logger.info("value from sanitizeObject method: "+value.toString());
                Object sanitizedValue = value; // Default to original value
                if (value instanceof String) {
                    sanitizedValue = Encode.forHtml(value.toString());
                    logger.info("sanitized value from sanitizeObject method: "+value.toString());
                } else if (value != null && !field.getType().isPrimitive()) {
                    // If the field is not primitive and not null, recursively sanitize it
                    sanitizedValue = sanitizeObject(value);
                }
                field.set(object, sanitizedValue); // Set sanitized value to field
            }
        }
        logger.info("out from sanitized object method");
        return object;
        }

    public static <T> T sanitizeDTO(T dto) {
        logger.info("in sanitizeDTO method");
        try {
            T sanitizedDTO = cloneDTO(dto); // Create a new instance of DTO
            sanitizeFields(sanitizedDTO); // Sanitize fields recursively
            return sanitizedDTO;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace(); // Handle exception
            return null;
        }
    }

    // Clone DTO object
    private static <T> T cloneDTO(T dto) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = dto.getClass();
        T clonedDTO = (T) clazz.newInstance(); // Create a new instance of DTO
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(dto);
            field.set(clonedDTO, value); // Copy field value to cloned DTO
        }
        return clonedDTO;
    }

    // Sanitize fields recursively
    private static <T> void sanitizeFields(T dto) throws IllegalAccessException {
        Class<?> clazz = dto.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(dto);
            if (value instanceof String) {
                field.set(dto, sanitizeString((String) value)); // Sanitize string field
            } else if (value instanceof Long || value instanceof Integer || value instanceof Boolean) {
                field.set(dto, value);
            } else if (value instanceof Collection) {
                sanitizeCollection((Collection<?>) value); // Sanitize collection field
            } else if (value != null && !field.getType().isPrimitive()) {
                sanitizeFields(value); // Recursively sanitize nested object
            }
        }
    }

    // Sanitize string method (e.g., remove HTML tags)
    private static String sanitizeString(String value) {
        return HTML_TAG_PATTERN.matcher(value).replaceAll("");
    }

    // Sanitize collection elements recursively
    private static void sanitizeCollection(Collection<?> collection) throws IllegalAccessException {
        for (Object item : collection) {
            if (item != null && !item.getClass().isPrimitive()) {
                sanitizeFields(item);
            }
        }
    }
}
