package pe.unjfsc.daw.logical;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pe.unjfsc.daw.entity.CEMatriculadoFIISI;

public class CLCargarXML {

	private static final Logger LOG = LoggerFactory.getLogger(CLCargarXML.class);

	public void cargarData(ArrayList<CEMatriculadoFIISI> matriculados) throws Exception {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// Elemento raíz
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("root");
		doc.appendChild(rootElement);

		for (CEMatriculadoFIISI poCEMatriculadoFIISI : matriculados) {
			Element fac = doc.createElement("FACULTAD");
			fac.setTextContent(poCEMatriculadoFIISI.getFACULTAD());
			rootElement.appendChild(fac);
			Element datos = doc.createElement("APELLIDOS_Y_NOMBRES");
			datos.setTextContent(poCEMatriculadoFIISI.getAPELLIDOS_Y_NOMBRES());
			rootElement.appendChild(datos);
			Element correo = doc.createElement("CORREO_INSTITUCIONAL");
			correo.setTextContent(poCEMatriculadoFIISI.getCORREO_INSTITUCIONAL());
			rootElement.appendChild(correo);
			Element ciclo = doc.createElement("CICLO");
			ciclo.setTextContent(String.valueOf(poCEMatriculadoFIISI.getCICLO()));
			rootElement.appendChild(ciclo);
			Element proc = doc.createElement("DISTRITO_PROCEDENCIA");
			proc.setTextContent(poCEMatriculadoFIISI.getDISTRITO_PROCEDENCIA());
			rootElement.appendChild(proc);
			Element edad = doc.createElement("EDAD");
			edad.setTextContent(String.valueOf(poCEMatriculadoFIISI.getEDAD()));
			rootElement.appendChild(edad);
			Element curso = doc.createElement("CURSO_A_CARGO");
			curso.setTextContent(poCEMatriculadoFIISI.getCURSO_A_CARGO());
			rootElement.appendChild(curso);
			Element mod = doc.createElement("MODALIDAD_INGRESO");
			mod.setTextContent(poCEMatriculadoFIISI.getMODALIDAD_INGRESO());
			rootElement.appendChild(mod);
		}
		// Se escribe el contenido del XML en un archivo
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("src/main/resources/fuente/input/prueba.xml"));
		transformer.transform(source, result);
		LOG.info("[EVL] Se ha cargado los datos.");
	}

}
