package Model;

public class Paragraph {
    int id;
    String inputParaGraph;
    String outputParaGraph;
    String inputLang;
    String outputLang;

    public Paragraph(int id, String inputParaGraph, String outputParaGraph, String inputLang, String ouPutLang) {
        this.id = id;
        this.inputParaGraph = inputParaGraph;
        this.outputParaGraph = outputParaGraph;
        this.inputLang = inputLang;
        this.outputLang = ouPutLang;
    }

    public Paragraph() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInputParaGraph() {
        return inputParaGraph;
    }

    public void setInputParaGraph(String inputParaGraph) {
        this.inputParaGraph = inputParaGraph;
    }

    public String getOutputParaGraph() {
        return outputParaGraph;
    }

    public void setOutputParaGraph(String outputParaGraph) {
        this.outputParaGraph = outputParaGraph;
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
