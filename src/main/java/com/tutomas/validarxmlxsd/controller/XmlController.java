package com.tutomas.validarxmlxsd.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/xml")
public class XmlController {

    @PostMapping("/validar")
    String validar(
            @RequestParam("xml") MultipartFile xml
            ) throws IOException, SAXException {
        Source source = new StreamSource(xml.getInputStream());
        File xsdFile = new File("UBL-2.3/xsd/maindoc/UBL-Invoice-2.3.xsd");
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Schema schema = schemaFactory.newSchema(xsdFile);
        Validator validator = schema.newValidator();
        validator.validate(source);
        return "XML válido";
    }

}
