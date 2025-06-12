/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.PDF417Writer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author jimmy
 */
public class ZxingUtiles {

    public static BufferedImage generatePDF417Barcode(String encodedData, int width, int height) throws Exception {
//        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//        BitMatrix bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.PDF_417, width, height);
//        BufferedImage barcodeImage = toBufferedImage(bitMatrix);
//        return barcodeImage;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(encodedData, BarcodeFormat.PDF_417, width, height);
        BufferedImage barcodeImage = toBufferedImage(bitMatrix);
        return barcodeImage;
    }

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    public static void main(String[] args) {
        try {
            String dataToEncode = "<DTE version=\"1.0\"><Documento ID=\"F78927T33\">\n" +
"    <Encabezado>\n" +
"        <IdDoc>\n" +
"           <TipoDTE>33</TipoDTE>\n" +
"           <Folio>78927</Folio>\n" +
"           <FchEmis>2023-08-03</FchEmis>\n" +
"<FmaPago>1</FmaPago><TermPagoGlosa>Contado</TermPagoGlosa><FchVenc>2023-08-03</FchVenc>        </IdDoc>\n" +
"        <Emisor>\n" +
"           <RUTEmisor>76789935-1</RUTEmisor>\n" +
"           <RznSoc>COMERCIALIZADORA LA FAVORITA LTDA.</RznSoc>\n" +
"           <GiroEmis>VENTA DE PRODUCTOS CARNICOS, IMPORTACION Y EXPORTACION DE PRODUCTOS.</GiroEmis>\n" +
"           <Acteco>463012</Acteco>\n" +
"           <DirOrigen>DEPARTAMENTAL 0805 - LA FLORIDA</DirOrigen>\n" +
"           <CmnaOrigen>La Florida</CmnaOrigen>\n" +
"           <CiudadOrigen>Región Metropolitana</CiudadOrigen>\n" +
"        </Emisor>\n" +
"        <Receptor>\n" +
"           <RUTRecep>06175639-6</RUTRecep>\n" +
"           <RznSocRecep>HECTOR DEL CARMEN PARDO CATALAN</RznSocRecep>\n" +
"           <GiroRecep>CARNICERIA</GiroRecep>      \n" +
"           <DirRecep>BILBAO 826</DirRecep>     \n" +
"           <CmnaRecep>PeNaflor</CmnaRecep>       \n" +
"           <CiudadRecep>Región Metropol</CiudadRecep>     \n" +
"        </Receptor>\n" +
"        <Totales>\n" +
"           <MntNeto>820000</MntNeto>\n" +
"           <TasaIVA>19</TasaIVA>\n" +
"           <IVA>155800</IVA>\n" +
"           <MntTotal>975800</MntTotal>\n" +
"        </Totales>\n" +
"    </Encabezado>\n" +
"    <Detalle>\n" +
"        <NroLinDet>1</NroLinDet>\n" +
"        <CdgItem>\n" +
"            <TpoCodigo>INTERNA</TpoCodigo>\n" +
"            <VlrCodigo>200-022</VlrCodigo>\n" +
"        </CdgItem>\n" +
"        <NmbItem>CHULETA VETADA CALIB. 25</NmbItem>\n" +
"        <DscItem>ODD:  000008169 SubOdd: 00015 Cant.:           150,00 Asignado :           175,00</DscItem>\n" +
"        <QtyItem>175</QtyItem>\n" +
"        <UnmdItem>KG</UnmdItem>\n" +
"        <PrcItem>2400</PrcItem>\n" +
"        <MontoItem>420000</MontoItem>\n" +
"    </Detalle>\n" +
"    <Detalle>\n" +
"        <NroLinDet>2</NroLinDet>\n" +
"        <CdgItem>\n" +
"            <TpoCodigo>INTERNA</TpoCodigo>\n" +
"            <VlrCodigo>300-018</VlrCodigo>\n" +
"        </CdgItem>\n" +
"        <NmbItem>FILETILLO DE POLLO 15 KG</NmbItem>\n" +
"        <DscItem>ODD:  000008169 SubOdd: 00016 Cant.:           150,00 Asignado :           150,00</DscItem>\n" +
"        <QtyItem>150</QtyItem>\n" +
"        <UnmdItem>KG</UnmdItem>\n" +
"        <PrcItem>1900</PrcItem>\n" +
"        <MontoItem>285000</MontoItem>\n" +
"    </Detalle>\n" +
"    <Detalle>\n" +
"        <NroLinDet>3</NroLinDet>\n" +
"        <CdgItem>\n" +
"            <TpoCodigo>INTERNA</TpoCodigo>\n" +
"            <VlrCodigo>200-001</VlrCodigo>\n" +
"        </CdgItem>\n" +
"        <NmbItem>CHULETA CENTRO CALIB.25</NmbItem>\n" +
"        <DscItem>ODD:  000008169 SubOdd: 00017 Cant.:            50,00 Asignado :            50,00</DscItem>\n" +
"        <QtyItem>50</QtyItem>\n" +
"        <UnmdItem>KG</UnmdItem>\n" +
"        <PrcItem>2300</PrcItem>\n" +
"        <MontoItem>115000</MontoItem>\n" +
"    </Detalle>\n" +
"<TED version=\"1.0\"><DD><RE>76789935-1</RE><TD>33</TD><F>78927</F><FE>2023-08-03</FE><RR>06175639-6</RR><RSR>HECTOR DEL CARMEN PARDO CATALAN</RSR><MNT>975800</MNT><IT1>CHULETA VETADA CALIB. 25</IT1><CAF version=\"1.0\"><DA><RE>76789935-1</RE><RS>COMERCIALIZADORA  LA FAVORITA LIMITADA</RS><TD>33</TD><RNG><D>70001</D><H>79000</H></RNG><FA>2023-03-01</FA><RSAPK><M>yrM+AsgcypyTVlgn59XAJNYXuHbwuH62vU4yfu679J9+J5ONn8u0Hnx7gQDvWIMJqVzoTp4M77NHo5NAYwIivQ==</M><E>Aw==</E></RSAPK><IDK>300</IDK></DA><FRMA algoritmo=\"SHA1withRSA\">Zi8hxAK7FRspXfmAHc7dIaj00f/Og7eX08LozlRxBsa/IjFvT0Plwi0j7r5IljxB8cAGc+aMPDvXErRyKAQMYQ==</FRMA></CAF><TSTED>2023-08-03T21:08:21</TSTED></DD><FRMT algoritmo=\"SHA1withRSA\">VZusrWu4Xw+7HXYNNlCjEFm4xiIREowNUbXo5VQTTCyZksErnB/GHNW4wY5mqnz1\n" +
"l5P+imERvCER2xD3v/QRtw==</FRMT></TED>\n" +
"<TmstFirma>2023-08-03T21:08:21</TmstFirma>\n" +
"</Documento><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
"<SignedInfo>\n" +
"<CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"></CanonicalizationMethod>\n" +
"<SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"></SignatureMethod>\n" +
"<Reference URI=\"#F78927T33\">\n" +
"<DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></DigestMethod>\n" +
"<DigestValue>mpafLFEquiampXgMd0baA0svjrM=</DigestValue>\n" +
"</Reference>\n" +
"</SignedInfo>\n" +
"<SignatureValue>\n" +
"VLNSpv3vWRBCeeTckpbF427b+2tGCFl0rKiehXIf5bEkQ2pC9pP1ngrpnnos/Q6yLUbQXhG/BxQE\n" +
"bMypbIq59zreMhdDmS8eBAdQ8uNs/PmzdEoGTiKp9NAeIXRYdTPa5Phn0gqbHUjylGiDEoMEeNGy\n" +
"qaHNtYiok2UQ/+OQFxSEpUlfRkUhhJuHMv/9z9E2lbyJGsnereho5c9tocdpeQyJfAGoKrxZbCeX\n" +
"YMFM8QA6xO+sH1rpJHLZxadBGtjOPV23GaB8VtekKDvtoSxewg4wAmy3bA5nyLbXb0tNxSHY2jyF\n" +
"BkIoZSWvp3CTzVZApyJyq18+ppipXpXjfz6Mhg==\n" +
"</SignatureValue>\n" +
"<KeyInfo>\n" +
"<KeyValue>\n" +
"<RSAKeyValue>\n" +
"<Modulus>\n" +
"qR2X/qZTQszj4m1Xx3Rp4yePVOt+/6jlZbdjd9kJtdpiD9i/RJeri+h0GD+vt1eLyRpFD/uGBdeX\n" +
"jXtEIBggGxAt8Y4r1TH9wKQ6S6qTxduagaWlZbhctumlaWeGaUTSBdfHpYIdLoBxPkeG3M2pG5Eh\n" +
"5BnqEgPNAdmqOXfXDbX9zRCjWyw7pHXUHdUeMTh+d4f/WAVCyTSo3E0jKeYLH88AmM/w5dimiS0A\n" +
"jEX+OiXfFdZ8SxASehsp8nHSdvsoSvj4WIgmZki3t5BmGDn1uJMhvccxl2gs8184UJ8uaz8ya/md\n" +
"NhVaF2csj5X87QhNr5NKQmxEYRSAU5/Q+7lddQ==\n" +
"</Modulus>\n" +
"<Exponent>AQAB</Exponent>\n" +
"</RSAKeyValue>\n" +
"</KeyValue>\n" +
"<X509Data>\n" +
"<X509Certificate>\n" +
"MIIGgzCCBWugAwIBAgIDAsZ2MA0GCSqGSIb3DQEBCwUAMIGmMQswCQYDVQQGEwJDTDEYMBYGA1UE\n" +
"ChMPQWNlcHRhLmNvbSBTLkEuMUgwRgYDVQQDEz9BY2VwdGEuY29tIEF1dG9yaWRhZCBDZXJ0aWZp\n" +
"Y2Fkb3JhIENsYXNlIDMgUGVyc29uYSBOYXR1cmFsIC0gRzQxHjAcBgkqhkiG9w0BCQEWD2luZm9A\n" +
"YWNlcHRhLmNvbTETMBEGA1UEBRMKOTY5MTkwNTAtODAeFw0yMjExMDQxNTI0MDZaFw0yNTExMDQx\n" +
"NTI0MDZaMIGRMQswCQYDVQQGEwJDTDEYMBYGA1UEDBMPUEVSU09OQSBOQVRVUkFMMSowKAYDVQQD\n" +
"EyFDUklTVE9CQUwgRkVSTkFORE8gQ0FCRVpBUyBWQVJFTEExJzAlBgkqhkiG9w0BCQEWGENDQUJF\n" +
"WkFTVkFSRUxBQEdNQUlMLkNPTTETMBEGA1UEBRMKMTc0MDc0MDUtNDCCASIwDQYJKoZIhvcNAQEB\n" +
"BQADggEPADCCAQoCggEBAKkdl/6mU0LM4+JtV8d0aeMnj1Trfv+o5WW3Y3fZCbXaYg/Yv0SXq4vo\n" +
"dBg/r7dXi8kaRQ/7hgXXl417RCAYIBsQLfGOK9Ux/cCkOkuqk8XbmoGlpWW4XLbppWlnhmlE0gXX\n" +
"x6WCHS6AcT5HhtzNqRuRIeQZ6hIDzQHZqjl31w21/c0Qo1ssO6R11B3VHjE4fneH/1gFQsk0qNxN\n" +
"IynmCx/PAJjP8OXYpoktAIxF/jol3xXWfEsQEnobKfJx0nb7KEr4+FiIJmZIt7eQZhg59biTIb3H\n" +
"MZdoLPNfOFCfLms/Mmv5nTYVWhdnLI+V/O0ITa+TSkJsRGEUgFOf0Pu5XXUCAwEAAaOCAsswggLH\n" +
"MB8GA1UdIwQYMBaAFKr9vcXpN032mU1XjsFxGvnrwwbjMB0GA1UdDgQWBBQuRacdQ2pYUGJ1RjgZ\n" +
"q+r12/51qjALBgNVHQ8EBAMCBPAwHQYDVR0lBBYwFAYIKwYBBQUHAwIGCCsGAQUFBwMEMBEGCWCG\n" +
"SAGG+EIBAQQEAwIFoDCB+gYDVR0gBIHyMIHvMIHsBggrBgEEAbVrAjCB3zAxBggrBgEFBQcCARYl\n" +
"aHR0cHM6Ly9hY2c0LmFjZXB0YS5jb20vQ1BTLUFjZXB0YWNvbTCBqQYIKwYBBQUHAgIwgZwwFhYP\n" +
"QWNlcHRhLmNvbSBTLkEuMAMCAQIagYFFbCB0aXR1bGFyIGhhIHNpZG8gdmFsaWRhZG8gZW4gZm9y\n" +
"bWEgcHJlc2VuY2lhbCwgcXVlZGFuZG8gaGFiaWxpdGFkbyBlbCBDZXJ0aWZpY2FkbyBwYXJhIHVz\n" +
"byB0cmlidXRhcmlvLCBwYWdvcywgY29tZXJjaW8geSBvdHJvcy4wWgYDVR0SBFMwUaAYBggrBgEE\n" +
"AcEBAqAMFgo5NjkxOTA1MC04oCQGCCsGAQUFBwgDoBgwFgwKOTY5MTkwNTAtOAYIKwYBBAHBAQKB\n" +
"D2luZm9AYWNlcHRhLmNvbTBjBgNVHREEXDBaoBgGCCsGAQQBwQEBoAwWCjE3NDA3NDA1LTSgJAYI\n" +
"KwYBBQUHCAOgGDAWDAoxNzQwNzQwNS00BggrBgEEAcEBAoEYQ0NBQkVaQVNWQVJFTEFAR01BSUwu\n" +
"Q09NMEcGCCsGAQUFBwEBBDswOTA3BggrBgEFBQcwAYYraHR0cHM6Ly9hY2c0LmFjZXB0YS5jb20v\n" +
"YWNnNC9vY3NwL0NsYXNlMy1HNDA/BgNVHR8EODA2MDSgMqAwhi5odHRwczovL2FjZzQuYWNlcHRh\n" +
"LmNvbS9hY2c0L2NybC9DbGFzZTMtRzQuY3JsMA0GCSqGSIb3DQEBCwUAA4IBAQBmleE4IhVtqyKk\n" +
"34msSfdFmo6dT+gSi03VCCCQ9Xlp4DOYdMmwUXbPOLJtqGKYkMj0qSG53m4mytAg1FK/7steC30c\n" +
"e6a8dAhS8ODHw0iq62UCZE562OfbzJPqelSXIKMHY3ep8cMP9q2ogXTlU463V6GyVbWUFnkNZULy\n" +
"Pze2MY5PERH9v3AH2pk6lCgWn4GlSWWEejNMmxCuc47GiUpx75cWrvcsGHU5WNd5sQ0+G5/uuhE9\n" +
"LYm7oA5y2rM176txrbH/ZSK3oJkuLU9zwN01wC4iiMqCPl0ew2SKi6lNvREKoZUFaBAultd63faZ\n" +
"DQ5QJrtEIvVeRXZWj+eVhbus\n" +
"</X509Certificate>\n" +
"</X509Data>\n" +
"</KeyInfo>\n" +
"</Signature></DTE>";
            int width = 900;  // Ancho de la imagen
            int height = 300; // Altura de la imagen

            BufferedImage pdf417Image = generatePDF417Barcode(dataToEncode, width, height);
    
            String outputPath = "C:\\bestcode\\files\\fcvIndividual\\qr.png"; // Cambia la ruta y el nombre del archivo según tu preferencia
        
            saveImageToFile(pdf417Image, outputPath);

            // Ahora puedes trabajar con la imagen generada, como mostrarla en una GUI o guardarla en un archivo.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static void saveImageToFile(BufferedImage image, String outputPath) {
        try {
            File outputFile = new File(outputPath);
            ImageIO.write(image, "png", outputFile);
            System.out.println("Imagen guardada exitosamente en: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
