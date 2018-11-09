package pl.put.poznan.transformer.logic;

public class Step {
    private String text;
    private  Step substeps[];

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Step[] getSubsteps() {
        return substeps;
    }

    public void setSubsteps(Step[] substeps) {
        this.substeps = substeps;
    }
}
