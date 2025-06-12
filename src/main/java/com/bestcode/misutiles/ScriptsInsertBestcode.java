/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bestcode.misutiles;

/**
 *
 * @author jimmy
 */
public class ScriptsInsertBestcode {
    
    public static final String INSERT_CONFIG = "INSERT INTO \"config\" (\"idempresa\", \"nombre\", \"valor\", \"tipo\", \"modulo\", \"descripcion\", \"idusuario_actualiza\", \"fecha_actualizacion\", \"valor_anterior\", \"tipo_dato\") VALUES\n" +
"	(1, 'ADVERTIR_ACTUALIZACION', 'false', 'HIDDEN', NULL, '', 1, GETDATE(), 'false', 'SMALLINT'),	\n" +
"	(1, 'CORREOS_ACTIVADOS', 'false', 'HIDDEN', NULL, 'Parámetro para activar o desactivar envío de correos automáticos. true activa y false desactiva', 1, GETDATE(), 'true', 'SMALLINT'),\n" +
"	(1, 'CORREOS_ERRORES_ACTIVADOS', 'false', 'HIDDEN', NULL, 'Activación de envío automático de excepciones al desarrollador', 1, GETDATE(), 'false', 'SMALLINT'),\n" +
"	(1, 'DIAS_LIMPIEZA_DIRECTORIOS', '10', 'HIDDEN', NULL, 'Cantidad de días antes de que los archivos sean borrados del directorio temp.', 1, GETDATE(), 'false', 'SMALLINT'),\n" +
"	(1, 'EMAIL_CUENTA', 'correosautomaticos@dimasa.cl', 'HIDDEN', NULL, 'Cuenta de correo que realiza los envíos automáticos', 1, GETDATE(), 'correosautomaticos@dimasa.cl', 'TEXTO'),\n" +
"	(1, 'EMAIL_CUENTA_ERRORES', 'jimmy.sda@gmail.com', 'HIDDEN', NULL, 'Cuenta de correo donde se enviarán los errores en el proceso de envío', 1, GETDATE(), 'jimmy.sda@gmail.com', 'EMAIL'),\n" +
"	(1, 'EMAIL_DESARROLLADOR', 'jimmy.sda@gmail.com', 'HIDDEN', NULL, 'Dirección de correo para recibir errores.', 1, GETDATE(), 'jimmy.sda@gmail.com', 'TEXTO'),\n" +
"	(1, 'EMAIL_NOMBRE_CUENTA', 'Correos automaticos DIMASA', 'HIDDEN', NULL, 'Nombre de la cuenta que realiza los envíos automáticos', 1, GETDATE(), 'Correos automaticos BESTCODE', 'SMALLINT'),\n" +
"	(1, 'MODO_DEV', 'True', 'HIDDEN', NULL, 'Modo que al estar activado, discrimina algunas opciones que solo deben ser ejecutadas en producción.', 1, GETDATE(), null, 'TEXTO'),\n" +
"	(1, 'RUTA_REPOSITORIOS', 'C:\\\\BESTCODE\\\\files\\\\', 'HIDDEN', NULL, '', 1, GETDATE(), 'E:\\\\SISTEMA\\\\BESTCODE\\\\files\\\\', 'TEXTO'),\n" +
"	(1, 'URL_REPOSITORIO', 'http://localhost:8080/dale/files/', 'HIDDEN', NULL, 'Url del repositorio de archivos', 1, GETDATE(), 'http://localhost:8090/bestcode/files/', 'URL');";

    public static final String INSERT_USUARIO = "INSERT INTO \"usuario\" (\"idusuario\", \"usuario\", \"password\", \"pin\", \"activo\", \"nombre\", \"apellido1\", \"apellido2\", \"fecha_creacion\", \"idusuario_crea\", \"movil\", \"idcargo\", \"email\", \"session_id\", \"fecha_ultimo_acceso\", \"admin\", \"color\", \"ultimo_idmodulo\", \"codigo_erp\", \"fono\", \"ultimo_so\", \"cambiar_password\") VALUES\n" +
"	(1, 'desarrollador', 'adcaec3805aa912c0d0b14a81bedb6ff', 'adcaec3805aa912c0d0b14a81bedb6ff', 1, 'Jimmy', 'Gutiérrez', 'Bevilacqua', GETDATE(), NULL, 0, NULL, 'jgutierrez.bestcode@gmail.com', null, null, 1, '#05278c', 0, 'JYM', '9-------7           ', NULL, 0),\n" +
"	(2, 'ROBOT', NULL, NULL, 1, 'ROBOT', '', NULL, GETDATE(), NULL, 0, NULL, 'robot@bestcode.cl', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, 0);";
    
    public static final String INSERT_MODULO = "INSERT INTO \"modulo\" (\"idmodulo\", \"nombre\", \"fecha_inicio\", \"color\", \"codigo\") VALUES\n" +
"	(0, 'Admin', GETDATE(), 'bluegrey', 'ADMIN');";
    
    public static final String INSERT_USUARIO_MODULO = "INSERT INTO \"usuario_modulo\" (\"idusuario\", \"idmodulo\", \"idusuairo_asigna\", \"fecha_asigna\") VALUES\n" +
"	(1, 0, 1, GETDATE());";
    
    public static final String INSERT_PERMISO = "INSERT INTO \"permiso\" (\"idpermiso\", \"nombre\", \"descripcion\", \"modulo\", \"tipo\", \"activo\", \"idmodulo\") VALUES\n" +
"	(1, 'Eventos del sistema', 'Consulta los registros almacenados de ciertos eventos del sistema, tanto automáticos como manuales. ', 'GEN', 'MENÚ', 1, 1),\n" +
"	(2, 'Parámetros', 'Administración de los parámetros del sistema. Estos parámetros controlan el buen funcionamiento de las distintas funcionalidades', 'GEN', 'MENÚ', 1, 1),\n" +
"	(4, 'Usuarios', 'Administración de los usuarios del sistema', 'GEN', 'MENÚ', 1, 1);";
    
    public static final String INSERT_ENTIDAD_COMODIN = "INSERT INTO entidad (codigo_entidad, razon_social, rut, tipo, activa) VALUES ('BCODE','ENTIDAD NO ENCONTRADA','BCODE','A',1);";
    
    public static final String INSERT_PERFIL = "INSERT INTO \"perfil\" (\"idperfil\", \"nombre\", \"descripcion\", \"idusuario_crea\", \"fecha_creacion\", \"idmodulo\") VALUES\n" +
"	(1, 'Administrador', 'Todos los permisos del sistema. Administra accesos y privilegios dentro de sistema Bestcode', 1, GETDATE(), 1);";
    
    public static final String INSERT_PERMISO_PERFIL = "INSERT INTO \"permiso_perfil\" (\"idpermiso\", \"idperfil\", \"idusuario_crea\", \"fecha_creacion\", \"activo\", \"nombre_permiso\") VALUES\n" +
"	(1, 1, 1, GETDATE(), 1, 'Eventos del sistema'),\n" +
"	(2, 1, 1, GETDATE(), 1, 'Parámetros'),\n" +
"	(4, 1, 1, GETDATE(), 1, 'Usuarios');";
    
    public static final String INSERT_TIPO_EVENTO = "INSERT INTO \"tipo_evento\" (\"idtipoevento\", \"descripcion\") VALUES\n" +
"	(-1, 'EXCEPTION'),\n" +
"	(1001, 'EJECUCIÓN COMANDOS'),	\n" +
"	(1002, 'ACCESO USUARIO'),\n" +
"	(1003, 'RECUPERACIÓN PASS'),\n" +
"	(1004, 'CAMBIO DE PASS'),\n" +
"	(1005, 'ACTIVACION USUARIO'),\n" +
"	(1006, 'BLOQUEO USUARIO'),\n" +
"	(1007, 'CREACIÓN USUARIO'),\n" +
"	(1008, 'ACTUALIZACIÓN USUARIO'),\n" +
"	(1009, 'ERROR DE CONFIGURACIÓN'),\n" +
"	(1010, 'CAMBIO CONFIGURACIÓN'),\n" +
"	(1011, 'PEDIDO SF INTEGRADO'),\n" +
"	(1012, 'PEDIDO SF VALIDADO'),\n" +
"	(1013, 'PEDIDO SF REPROCESADO'),\n" +
"	(1014, 'PEDIDO SF CONVERTIDO EN NVV'),\n" +
"	(1015, 'PEDIDO SF DESCARTADO'),\n" +
"	(1016, 'PEDIDO SF AUTORIZADO (CLIENTE)'),\n" +
"	(1017, 'PEDIDO SF AUTORIZADO (STOCK)'),\n" +
"	(1018, 'PEDIDO SF RECHAZADO POR STOCK INSUFICIENTE'),\n" +
"	(1019, 'IMPORTAR OPORTUNIDADES SALESFORCE'),\n" +
"	(1020, 'CREAR PRODUCTO'),\n" +
"	(1021, 'ACTUALIZACIÓN PRODUCTO'),\n" +
"	(1022, 'CAMBIO DE CÓDIGO DE PRODUCTO'),\n" +
"	(1023, 'BLOQUEO PRODUCTO'),\n" +
"	(1024, 'DESBLOQUEO PRODUCTO'),\n" +
"	(1025, 'PUBLICADO'),\n" +
"	(1026, 'PUBLICACIÓN ELIMINADA'),\n" +
"	(1027, 'PUBLICACIÓN ACTUALIZADA'),\n" +
"	(1028, 'CAMBIO DE ESTADO DE PUBLICACIÓN'),\n" +
"	(1029, 'SINCRONIZACIÓN PRODUCTOS SHOPIFY'),\n" +
"	(1030, 'SINCRONIZACIÓN DE PRODUCTOS'),\n" +
"	(1031, 'SINCRONIZACIÓN DE ENTIDADES'),\n" +
"	(1032, 'SINCRONIZACIÓN DE EMPLEADOS'),\n" +
"	(1033, 'SINCRONIZACIÓN DE CÓDIGOS DE BARRA'),"+
"	(1034, 'LIMPIEZA DIRECTORIOS')"+
        ";";
    
}
