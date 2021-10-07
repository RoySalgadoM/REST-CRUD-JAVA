package mx.edu.utez.model.productLines;

import javax.servlet.http.Part;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ProductLines")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductLines {
    @XmlElement
    private String productLine;
    @XmlElement
    private String textDescription;
    @XmlElement
    private String htmlDescription;
    @XmlElement
    private Part binary;
    @XmlElement
    private String base64Image;

    public ProductLines(String productLine, String textDescription, String htmlDescription, Part binary, String base64Image) {
        this.productLine = productLine;
        this.textDescription = textDescription;
        this.htmlDescription = htmlDescription;
        this.binary = binary;
        this.base64Image = base64Image;
    }

    public ProductLines() {
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public Part getBinary() {
        return binary;
    }

    public void setBinary(Part binary) {
        this.binary = binary;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
