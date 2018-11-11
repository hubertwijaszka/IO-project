package pl.put.poznan.transformer.logic;


/**
 * Class Step of out Scenario containing text of the Step and its substeps.
 * Class Step is a subclass of {@link Scenario}
 */

public class Step {
    private String text;
    private  Step substeps[];

    /**
     * @return Text of Step.
     */
    public String getText() {
        return text;
    }

    /**
     * @param text Text to set for the Step.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return Array of substeps.
     */
    public Step[] getSubsteps() {
        return substeps;
    }


    /**
     * @param substeps Array of substeps to set for a Step.
     */
    public void setSubsteps(Step[] substeps) {
        this.substeps = substeps;
    }
}
