package br.com.cidade.model.Cidade.dao.impl;

import br.com.cidade.model.Cidade.Cidade;
import br.com.cidade.model.Cidade.dao.CidadeDAO;
import br.com.cidade.model.Cidade.dao.DAOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CidadeXMLDao implements CidadeDAO {

    private String diretorio = "database/xml/cidades";


    @Override
    public void cadastrar(Cidade cidade) throws DAOException {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document document = builder.newDocument();
            Element elementCidade = document.createElement("Cidade");
            document.appendChild(elementCidade);

            Element elementId = document.createElement("id");
            elementId.setTextContent(cidade.getId().toString());
            elementCidade.appendChild(elementId);

            Element elementNome = document.createElement("Nome");
            elementNome.setTextContent(cidade.getNome());
            elementCidade.appendChild(elementNome);

            Element elementEstado = document.createElement("Estado");
            elementEstado.setTextContent(cidade.getEstado().toString());
            elementCidade.appendChild(elementEstado);



            File arquivo = new File(diretorio, cidade.getId().toString() + ".xml");
            try (FileOutputStream output = new FileOutputStream(arquivo)) {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(output);

                transformer.transform(source, result);
            } catch (IOException | TransformerException e) {
                throw new RuntimeException(e);
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cidade> listar() throws DAOException {
        FilenameFilter filter = (dir, nome) -> nome.endsWith(".xml");
        File diretoRaiz = new File(diretorio);
        List<Cidade> cidades = new ArrayList<>();
        for (File arquivo : diretoRaiz.listFiles(filter)) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();

                Document document = builder.parse(arquivo);

                Element elementPessoa = document.getDocumentElement();
                Node elementId = elementPessoa.getElementsByTagName("id").item(0);
                Node elementNome = elementPessoa.getElementsByTagName("nome").item(0);
                Node elementEstado = elementPessoa.getElementsByTagName("estado").item(0);

                Cidade cidade = new Cidade();
                cidade.setId(UUID.fromString(elementId.getTextContent()));
                cidade.setNome(elementNome.getTextContent());
                cidade.setEstado(elementEstado.getClass());

                cidades.add(cidade);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return cidades;
    }

    @Override
    public Cidade buscar(UUID id) throws DAOException {
        File arquivo = new File(diretorio, id.toString() + ".xml");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(arquivo);
            Element elementCidade = document.getDocumentElement();
            Node elementId = elementCidade.getElementsByTagName("id").item(0);
            Node elementNome = elementCidade.getElementsByTagName("nome").item(0);
            Node elementEstado = elementCidade.getElementsByTagName("estado").item(0);

            Cidade cidade = new Cidade();
            cidade.setId(UUID.fromString(elementId.getTextContent()));
            cidade.setNome(elementNome.getTextContent());
            cidade.setEstado(elementEstado.getClass());
            return cidade;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(UUID id, Cidade cidade) throws DAOException {
        File arquivo = new File(diretorio, id.toString() + ".xml");
        arquivo.delete();

        cidade.setId(id);

        cadastrar(cidade);
    }

    @Override
    public Cidade apagar(UUID id) throws DAOException {
        Cidade cidade = buscar(id);
        if (cidade != null) {
            File arquivo = new File(diretorio, id.toString() + ".xml");
            arquivo.delete();
        }
        return cidade;
    }
}
