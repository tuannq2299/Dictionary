package Model;

public class TranslateParagraphHistory {
    private int id;
    private String inputParagraph;
    private String outputParagraph;
    private String inputLang;
    private String outputLang;

    public TranslateParagraphHistory(String inputParagraph, String outputParagraph, String inputLang, String outputLang) {
        this.inputParagraph = inputParagraph;
        this.outputParagraph = outputParagraph;
        this.inputLang = inputLang;
        this.outputLang = outputLang;
    }

    public TranslateParagraphHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInputParagraph() {
        return inputParagraph;
    }

    public void setInputParagraph(String inputParagraph) {
        this.inputParagraph = inputParagraph;
    }

    public String getOutputParagraph() {
        return outputParagraph;
    }

    public void setOutputParagraph(String outputParagraph) {
        this.outputParagraph = outputParagraph;
    }

    public String getInputLang() {
        return inputLang;
    }

    public void setInputLang(String inputLang) {
        this.inputLang = inputLang;
    }

    public String getOutputLang() {
        return outputLang;
    }

    public void setOutputLang(String outputLang) {
        this.outputLang = outputLang;
    }
}
